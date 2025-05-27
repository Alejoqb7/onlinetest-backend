package co.edu.uco.onlinetest.businesslogic.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.dto.PaisDTO;

public interface PaisFacade {

	void registrarNuevoPais(PaisDTO pais) throws OnlineTestException, SQLException;

	void modificarPaisExistente(UUID id, PaisDTO pais) throws OnlineTestException, SQLException;

	void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException, SQLException;

	PaisDTO consultarPaisPorId(UUID id) throws OnlineTestException, SQLException;

	List<PaisDTO> consultarPaises(PaisDTO filtro) throws OnlineTestException, SQLException;
}
