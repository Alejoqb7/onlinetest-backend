package co.edu.uco.onlinetest.data.dao.entity.pais.impl.azuresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisAzureSQLDAO implements PaisDAO {
	
	private Connection conexion;
	
	public PaisAzureSQLDAO(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public void create(PaisEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaisEntity> listByFilter(PaisEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisEntity> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaisEntity listById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
		

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PaisEntity id, UUID entity) {
		// TODO Auto-generated method stub
		
	}

}
