package co.edu.uco.onlinetest.data.dao.entity;

import java.sql.SQLException;
import java.util.List;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface RetrieveDAO<E, ID> {
	
	List<E> listByFilter(E filter) throws OnlineTestException, SQLException;
	
	List<E> listAll() throws OnlineTestException, SQLException;
	
	E listById(ID id) throws OnlineTestException, SQLException;

}
