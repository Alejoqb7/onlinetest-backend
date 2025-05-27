package co.edu.uco.onlinetest.businesslogic.businesslogic;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface PaisBusinessLogic {
	
	void registrarNuevoPais(PaisDomain pais) throws OnlineTestException, SQLException;
	
	void modificarPaisExistente(UUID id, PaisDomain pais) throws OnlineTestException;
	
	void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException;
	
	PaisDomain consultarPaisPorId(UUID id) throws OnlineTestException, SQLException;
	
	List<PaisDomain> consultarPaises(PaisDomain filtro)throws OnlineTestException,SQLException;
}
