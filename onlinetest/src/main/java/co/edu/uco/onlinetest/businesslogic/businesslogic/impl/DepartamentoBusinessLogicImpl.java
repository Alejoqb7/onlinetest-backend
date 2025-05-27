package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.departamento.entity.DepartamentoEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

public class DepartamentoBusinessLogicImpl implements DepartamentoBusinessLogic {

    private final DAOFactory factory;

    public DepartamentoBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException {
        // Traducir Domain -> Entity y delegar CREATE
        DepartamentoEntity entity = DepartamentoEntityAssembler.getInstance().toEntity(departamento);
        factory.getDepartamentoDAO().create(entity);
    }

    @Override
    public void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException {
        // Traducir Domain -> Entity y delegar UPDATE
        DepartamentoEntity entity = DepartamentoEntityAssembler.getInstance().toEntity(departamento);
        factory.getDepartamentoDAO().update(entity, id);
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException {
        // Delegar DELETE por id
        factory.getDepartamentoDAO().delete(id);
    }

    @Override
    public DepartamentoDomain consultarDepartamentoPorId(UUID id) throws OnlineTestException, SQLException {
        // Delegar SELECT BY ID y traducir Entity -> Domain
        DepartamentoEntity entity = factory.getDepartamentoDAO().listById(id);
        if (entity == null) {
            return null;
        }
        return DepartamentoEntityAssembler.getInstance().toDomain(entity);
    }

    @Override
    public List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro) throws OnlineTestException, SQLException {
        // Traducir filtro Domain -> Entity
        DepartamentoEntity filterEntity = DepartamentoEntityAssembler.getInstance().toEntity(filtro);
        // Delegar SELECT BY FILTER
        List<DepartamentoEntity> entities = factory.getDepartamentoDAO().listByFilter(filterEntity);
        // Traducir manualmente cada Entity -> Domain
        List<DepartamentoDomain> lista = new ArrayList<>();
        for (DepartamentoEntity e : entities) {
            lista.add(DepartamentoEntityAssembler.getInstance().toDomain(e));
        }
        return lista;
    }
}
