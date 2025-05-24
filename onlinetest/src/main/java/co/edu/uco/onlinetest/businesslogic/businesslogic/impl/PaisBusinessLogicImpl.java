package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import co.edu.uco.onlinetest.businesslogic.businesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.entity.PaisEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisBusinessLogicImpl implements PaisBusinessLogic {

    private final DAOFactory factory;

    public PaisBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoPais(PaisDomain pais) throws OnlineTestException {
        validarIntegridadInformacionParaRegistrarNuevoPais(pais);
        validarIntegridadNombrePais(pais.getNombre());
        validarPaisConMismoNombre(pais.getNombre());

        var paisEntity = PaisEntityAssembler.getInstance().toEntity(pais);
        paisEntity.setId(generarIdentificadorNuevoPais()); // Assign generated ID
        factory.getPaisDAO().create(paisEntity);
        var id = generarIdentificadorNuevoPais();
        var paisDomainCrear = new PaisDomain(id, pais.getNombre()); 
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

    private void validarPaisConMismoNombre(String nombrePais) throws OnlineTestException {
        var filtro = new PaisEntity();
        filtro.setNombre(nombrePais);

        var listaResultados = factory.getPaisDAO().listByFilter(filtro);

        if (!listaResultados.isEmpty()) { // Corrected condition
            throw BusinessLogicOnlineTestException.reportar(
                "Ya existe un país registrado con el mismo nombre."
            );
        }
    }

    private UUID generarIdentificadorNuevoPais() throws OnlineTestException {
        UUID nuevoId;
        boolean existeId;

        do {
            nuevoId = UUID.randomUUID(); // Generate a new UUID
            var pais = factory.getPaisDAO().listById(nuevoId);
            existeId = pais != null; // Check if the ID already exists
        } while (existeId);

        return nuevoId;
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
    public PaisDomain consultarPaisPorId(UUID id) throws OnlineTestException {
        PaisEntity paisEntity = factory.getPaisDAO().listById(id);
        if (paisEntity == null) {
            return null;
        }
        return PaisEntityAssembler.getInstance().toDomain(paisEntity);
    }

    @Override
    public List<PaisDomain> consultarPaises(PaisDomain filtro) throws OnlineTestException {
        PaisEntity filterEntity = PaisEntityAssembler.getInstance().toEntity(filtro);
        List<PaisEntity> entidades = factory.getPaisDAO().listByFilter(filterEntity);
        return entidades.stream()
                        .map(PaisEntityAssembler.getInstance()::toDomain)
                        .collect(Collectors.toList());
    }

    public boolean existePais(UUID id) throws OnlineTestException {
        return factory.getPaisDAO().listById(id) != null;
    }

    public int contarPaises() throws OnlineTestException {
        return factory.getPaisDAO().count();
    }

    public List<PaisDomain> consultarTodosLosPaises() throws OnlineTestException {
        List<PaisEntity> entidades = factory.getPaisDAO().listAll();
        return entidades.stream()
                        .map(PaisEntityAssembler.getInstance()::toDomain)
                        .collect(Collectors.toList());
    }
}
