package co.edu.uco.onlinetest.businesslogic.businesslogic;

import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface CiudadBusinessLogic {

    void registrarNuevaCiudad(CiudadDomain ciudad) throws OnlineTestException;

    void modificarCiudadExistente(UUID id, CiudadDomain ciudad) throws OnlineTestException;

    void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException;

    CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException;

    List<CiudadDomain> consultarCiudades(CiudadDomain filtro) throws OnlineTestException;
}
