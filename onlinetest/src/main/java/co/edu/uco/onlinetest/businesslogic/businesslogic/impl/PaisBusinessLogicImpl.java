package co.edu.uco.onlinetest.businesslogic.businesslogic.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisBusinessLogicImpl implements PaisBusinessLogic {

	private DAOFactory factory;

	public PaisBusinessLogicImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public void registrarNuevoPais(PaisDomain pais) {
		// TODO Auto-generated method stub
		PaisEntity paisEntity = null; // Traductir de domain -> entity
		factory.getPaisDAO().create(paisEntity);
	}

	@Override
	public void modificarPaisExistente(UUID id, PaisDomain pais) {
		// TODO Auto-generated method stub
		PaisEntity paisEntity = null; // Traductir de domain -> entity
		factory.getPaisDAO().update(paisEntity, id);
	}

	@Override
	public void darBajaDefinitivamentePaisExistente(UUID id) {
		// TODO Auto-generated method stub
		factory.getPaisDAO().delete(id);
	}

	@Override
	public PaisDomain consultarPaisPorId(UUID id) {
		// TODO Auto-generated method stub

		PaisEntity paisEntity = null;
		factory.getPaisDAO().listById(id);

		return null;
	}

	@Override
	public List<PaisDomain> consultarPaises(PaisDomain filtro) {
		// TODO Auto-generated method stub

		PaisEntity paisFilter = null;
		List<PaisEntity> paisEntities = factory.getPaisDAO().listByFilter(paisFilter);
		List<PaisDomain> datosARetornar = null; // Traductir de entity -> domain
		return datosARetornar;
	}

}
