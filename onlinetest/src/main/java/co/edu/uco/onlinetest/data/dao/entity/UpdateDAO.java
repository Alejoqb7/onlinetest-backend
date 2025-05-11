package co.edu.uco.onlinetest.data.dao.entity;

public interface UpdateDAO<ID, E> {
	
	void update(ID id, E entity);

}
