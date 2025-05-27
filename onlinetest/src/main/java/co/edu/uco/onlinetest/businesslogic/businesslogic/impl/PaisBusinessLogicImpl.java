package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.entity.PaisEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisBusinessLogicImpl implements PaisBusinessLogic {

    private final DAOFactory factory;

    public PaisBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoPais(PaisDomain pais) throws OnlineTestException, SQLException {	 
        validarIntegridadInformacionParaRegistrarNuevoPais(pais);
        validarIntegridadNombrePais(pais.getNombre());
        validarPaisConMismoNombre(pais.getNombre());

        // Generar y asignar nuevo ID
        UUID nuevoId = generarIdentificadorNuevoPais();
        pais.setId(nuevoId);

        // Traducir Domain -> Entity y crear
        PaisEntity paisEntity = PaisEntityAssembler.getInstance().toEntity(pais);
        factory.getPaisDAO().create(paisEntity);
    }

    @Override
    public void modificarPaisExistente(UUID id, PaisDomain pais) throws OnlineTestException {
        validarIntegridadInformacionParaRegistrarNuevoPais(pais);
        validarIntegridadNombrePais(pais.getNombre());

        PaisEntity paisEntity = PaisEntityAssembler.getInstance().toEntity(pais);
        factory.getPaisDAO().update(paisEntity, id);
    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException {
        factory.getPaisDAO().delete(id);
    }

    @Override
    public PaisDomain consultarPaisPorId(UUID id) throws OnlineTestException, SQLException {
        PaisEntity paisEntity = factory.getPaisDAO().listById(id);
        if (paisEntity == null) {
            return null;
        }
        return PaisEntityAssembler.getInstance().toDomain(paisEntity);
    }

    @Override
    public List<PaisDomain> consultarPaises(PaisDomain filtro) throws OnlineTestException, SQLException {
        PaisEntity entidadFiltro = PaisEntityAssembler.getInstance().toEntity(filtro);
        List<PaisEntity> entidades = factory.getPaisDAO().listByFilter(entidadFiltro);

        // Convertir manualmente cada entidad a dominio
        List<PaisDomain> lista = new ArrayList<>();
        for (PaisEntity e : entidades) {
            PaisDomain d = PaisEntityAssembler.getInstance().toDomain(e);
            lista.add(d);
        }
        return lista;
    }

    private void validarIntegridadInformacionParaRegistrarNuevoPais(PaisDomain pais) throws OnlineTestException {
        if (pais == null || pais.getNombre() == null || pais.getNombre().isBlank()) {
            throw BusinessLogicOnlineTestException.reportar(
                "El nombre del país no puede estar vacío."
            );
        }
    }

    private void validarIntegridadNombrePais(String nombrePais) throws OnlineTestException {
        if (UtilTexto.getInstance().estaVacia(nombrePais)) {
            throw BusinessLogicOnlineTestException.reportar(
                "El nombre del país no puede estar vacío."
            );
        }
        if (UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombrePais).length() > 50) {
            throw BusinessLogicOnlineTestException.reportar(
                "El nombre del país no puede exceder 50 caracteres."
            );
        }
        if (!UtilTexto.getInstance().contieneSoloLetrasEspacios(nombrePais)) {
            throw BusinessLogicOnlineTestException.reportar(
                "El nombre del país solo puede contener letras y espacios."
            );
        }
    }

    private void validarPaisConMismoNombre(String nombrePais) throws OnlineTestException, SQLException {
        PaisEntity filtro = new PaisEntity();
        filtro.setNombre(nombrePais);

        List<PaisEntity> coincidencias = factory.getPaisDAO().listByFilter(filtro);
        if (coincidencias != null && !coincidencias.isEmpty()) {
            throw BusinessLogicOnlineTestException.reportar(
                "Ya existe un país registrado con ese nombre."
            );
        }
    }

    private UUID generarIdentificadorNuevoPais() throws OnlineTestException, SQLException {
        UUID nuevoId;
        boolean existe;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            PaisEntity existente = factory.getPaisDAO().listById(nuevoId);
            existe = (existente != null && !UtilUUID.esValorDefecto(existente.getId()));
        } while (existe);
        return nuevoId;
    }
}
