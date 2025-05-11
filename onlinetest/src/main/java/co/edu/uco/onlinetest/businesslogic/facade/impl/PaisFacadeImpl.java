package co.edu.uco.onlinetest.businesslogic.facade.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.PaisDTO;

public class PaisFacadeImpl implements PaisFacade {
	
	private DAOFactory daoFactory;
	private PaisBusinessLogic paisBusinessLogic;
	
	public PaisFacadeImpl() {
		daoFactory = DAOFactory.getFactory(Factory.AZURE_SQL);
		paisBusinessLogic = new PaisBusinessLogicImpl(daoFactory);
	}

	@Override
	public void registrarNuevoPais(PaisDTO pais) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarPaisExistente(UUID id, PaisDTO pais) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void darBajaDefinitivamentePaisExistente(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PaisDTO consultarPaisPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDTO> consultarPaises(PaisDTO filtro) {
		// TODO Auto-generated method stub
		return null;
	}

}
