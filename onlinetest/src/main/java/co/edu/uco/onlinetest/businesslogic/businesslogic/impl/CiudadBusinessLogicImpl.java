package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.CiudadEntity;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

public class CiudadBusinessLogicImpl implements CiudadBusinessLogic {

    private DAOFactory factory;

    public CiudadBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaCiudad(CiudadDomain ciudad) throws OnlineTestException {
        // TODO Auto-generated method stub
        DepartamentoEntity departamentoEntity = null; // Traducir de domain -> entity
        factory.getDepartamentoDAO().create(departamentoEntity);
    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDomain ciudad) throws OnlineTestException {
        // TODO Auto-generated method stub
        DepartamentoEntity departamentoEntity = null; // Traducir de domain -> entity
        factory.getDepartamentoDAO().update(departamentoEntity, id);
    }

    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException {
        // TODO Auto-generated method stub
        factory.getCiudadDAO().delete(id);
    }

    @Override
    public CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException {
        // TODO Auto-generated method stub
        CiudadEntity ciudadEntity = null;
        factory.getCiudadDAO().listById(id);
        return null;
    }

    @Override
    public List<CiudadDomain> consultarCiudades(CiudadDomain filtro) throws OnlineTestException {
        // TODO Auto-generated method stub
        CiudadEntity ciudadFilter = null;
        List<CiudadEntity> ciudadEntities = factory.getCiudadDAO().listByFilter(ciudadFilter);
        List<CiudadDomain> datosARetornar = null; // Traducir de entity -> domain
        return datosARetornar;
    }
}
