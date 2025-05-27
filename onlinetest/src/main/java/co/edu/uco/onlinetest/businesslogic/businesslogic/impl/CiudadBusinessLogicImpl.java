package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.ciudad.entity.CiudadEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.CiudadEntity;

public class CiudadBusinessLogicImpl implements CiudadBusinessLogic {

    private final DAOFactory factory;

    public CiudadBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaCiudad(CiudadDomain ciudad) throws OnlineTestException {
        // Traducir Domain -> Entity y delegar CREATE
        CiudadEntity entity = CiudadEntityAssembler.getInstance().toEntity(ciudad);
        factory.getCiudadDAO().create(entity);
    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDomain ciudad) throws OnlineTestException {
        // Traducir Domain -> Entity y delegar UPDATE
        CiudadEntity entity = CiudadEntityAssembler.getInstance().toEntity(ciudad);
        factory.getCiudadDAO().update(entity, id);
    }

    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException {
        // Delegar DELETE por id
        factory.getCiudadDAO().delete(id);
    }

    @Override
    public CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException, SQLException {
        // Delegar SELECT BY ID y traducir Entity -> Domain
        CiudadEntity entity = factory.getCiudadDAO().listById(id);
        if (entity == null) {
            return null;
        }
        return CiudadEntityAssembler.getInstance().toDomain(entity);
    }

    @Override
    public List<CiudadDomain> consultarCiudades(CiudadDomain filtro) throws OnlineTestException, SQLException {
        // Traducir filtro Domain -> Entity
        CiudadEntity filterEntity = CiudadEntityAssembler.getInstance().toEntity(filtro);
        // Delegar SELECT BY FILTER
        List<CiudadEntity> entities = factory.getCiudadDAO().listByFilter(filterEntity);
        // Traducir manualmente cada Entity -> Domain
        List<CiudadDomain> lista = new ArrayList<>();
        for (CiudadEntity e : entities) {
            lista.add(CiudadEntityAssembler.getInstance().toDomain(e));
        }
        return lista;
    }
}
