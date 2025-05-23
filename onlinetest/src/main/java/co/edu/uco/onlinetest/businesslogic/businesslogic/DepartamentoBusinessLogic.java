package co.edu.uco.onlinetest.businesslogic.businesslogic;

import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface DepartamentoBusinessLogic {
	
	void registrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException;
	
	void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException;
	
	void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException;
	
	DepartamentoDomain consultarDepartamentoPorId(UUID id) throws OnlineTestException;
	
	List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro) throws OnlineTestException;
}
