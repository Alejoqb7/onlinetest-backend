package co.edu.uco.onlinetest.businesslogic.businesslogic;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface DepartamentoBusinessLogic {
	
	void registrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException, SQLException;
	
	void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException, SQLException;
	
	void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException, SQLException;
	
	DepartamentoDomain consultarDepartamentoPorId(UUID id) throws OnlineTestException, SQLException;
	
	List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro) throws OnlineTestException, SQLException;
}
