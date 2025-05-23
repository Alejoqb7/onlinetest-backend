package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class DepartamentoBusinessLogicImpl implements DepartamentoBusinessLogic {

	private DAOFactory factory;

	public DepartamentoBusinessLogicImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public void registrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException {
		// TODO Auto-generated method stub
		PaisEntity paisEntity = null; // Traducir de domain -> entity
		factory.getPaisDAO().create(paisEntity);
	}

	@Override
	public void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException {
		// TODO Auto-generated method stub
		PaisEntity paisEntity = null; // Traducir de domain -> entity
		factory.getPaisDAO().update(paisEntity, id);
	}

	@Override
	public void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException {
		// TODO Auto-generated method stub
		factory.getPaisDAO().delete(id);
	}

	@Override
	public DepartamentoDomain consultarDepartamentoPorId(UUID id) throws OnlineTestException {
		// TODO Auto-generated method stub

		DepartamentoEntity departamentoEntity = null;
		factory.getDepartamentoDAO().listById(id);

		return null;
	}

	@Override
	public List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro) throws OnlineTestException {
		// TODO Auto-generated method stub

		DepartamentoEntity departamentoFilter = null;
		List<DepartamentoEntity> departamentoEntities = factory.getDepartamentoDAO().listByFilter(departamentoFilter);
		List<DepartamentoDomain> datosARetornar = null; // Traducir de entity -> domain
		return datosARetornar;
	}

}
