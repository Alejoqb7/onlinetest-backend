package co.edu.uco.onlinetest.data.dao.entity;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface UpdateDAO<ID, E> {
	
	void update(ID id, E entity) throws OnlineTestException;

}
