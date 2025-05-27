package co.edu.uco.onlinetest.businesslogic.businesslogic;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface CiudadBusinessLogic {

    void registrarNuevaCiudad(CiudadDomain ciudad) throws OnlineTestException, SQLException;

    void modificarCiudadExistente(UUID id, CiudadDomain ciudad) throws OnlineTestException, SQLException;

    void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException, SQLException;

    CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException, SQLException;

    List<CiudadDomain> consultarCiudades(CiudadDomain filtro) throws OnlineTestException, SQLException;
}
