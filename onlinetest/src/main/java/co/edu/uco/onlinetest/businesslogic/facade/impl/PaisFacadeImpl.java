package co.edu.uco.onlinetest.businesslogic.facade.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import co.edu.uco.onlinetest.businesslogic.businesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.dto.PaisDTOAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.businesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.PaisDTO;

public class PaisFacadeImpl implements PaisFacade {

	private final DAOFactory daoFactory;
	private final PaisBusinessLogic paisBusinessLogic;

	public PaisFacadeImpl() throws OnlineTestException {
		daoFactory = DAOFactory.getFactory(Factory.AZURE_SQL);
		paisBusinessLogic = new PaisBusinessLogicImpl(daoFactory);
	}

	@Override
	public void registrarNuevoPais(PaisDTO pais) throws OnlineTestException, SQLException {

		try {
			daoFactory.iniciarTransaccion();
			
			var paisDomain = PaisDTOAssembler.getInstance().toDomain(pais);
			paisBusinessLogic.registrarNuevoPais(paisDomain);
			
			daoFactory.confirmarTransaccion();
		} catch (OnlineTestException exception) {
			daoFactory.cancelarTransaccion();
			throw exception;
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al registrar el país con el identififcador deseado.";
			var mensajeTecnico = "Error genérico en INSERT Pais: " + exception.getMessage();
			throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} finally {
			daoFactory.cerrarConexion();
		}
	}

	@Override
	public void modificarPaisExistente(UUID id, PaisDTO pais) throws OnlineTestException {
		// Mapear DTO a Domain
		PaisDomain domain = new PaisDomain(pais.getId(), pais.getNombre());
		// Delegar a la capa de negocio
		paisBusinessLogic.modificarPaisExistente(id, domain);
	}

	@Override
	public void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException {
		// Delegar a la capa de negocio
		paisBusinessLogic.darBajaDefinitivamentePaisExistente(id);
	}

	@Override
	public PaisDTO consultarPaisPorId(UUID id) throws OnlineTestException, SQLException {

		try {
			try {
				
				@SuppressWarnings("unused")
				var paisDomainResultado = paisBusinessLogic.consultarPaisPorId(id);
			} catch (OnlineTestException exception) {
				throw exception;
			} catch (Exception exception) {
				
				var mensajeUsuario = "Se produjo un error inesperado al consultar la información del país con el identificador deseado.";
				var mensajeTecnico = "Error genérico en INSERT Pais: " + exception.getMessage();
				
				throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
			} finally {
				daoFactory.cerrarConexion();
			}
		} catch (OnlineTestException e) {
			e.printStackTrace();
		}
		
		PaisDomain domain = paisBusinessLogic.consultarPaisPorId(id);
		if (domain == null) {
			return null;
		}
		return new PaisDTO(domain.getId(), domain.getNombre());
	}

	@Override
	public List<PaisDTO> consultarPaises(PaisDTO filtro) throws OnlineTestException, SQLException {
		// Mapear filtro DTO a Domain
		PaisDomain filterDomain = new PaisDomain(filtro.getId(), filtro.getNombre());
		// Consultar capa de negocio
		List<PaisDomain> domains = paisBusinessLogic.consultarPaises(filterDomain);
		// Mapear resultado a DTOs
		return domains.stream().map(d -> new PaisDTO(d.getId(), d.getNombre())).collect(Collectors.toList());
	}
}
