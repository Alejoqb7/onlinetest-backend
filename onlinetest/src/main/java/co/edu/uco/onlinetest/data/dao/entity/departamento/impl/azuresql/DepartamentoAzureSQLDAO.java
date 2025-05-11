package co.edu.uco.onlinetest.data.dao.entity.departamento.impl.azuresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

public class DepartamentoAzureSQLDAO implements DepartamentoDAO {
	
	private Connection conexion;
	
	public DepartamentoAzureSQLDAO(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public void create(DepartamentoEntity entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<DepartamentoEntity> listByFilter(DepartamentoEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartamentoEntity> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartamentoEntity listById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(DepartamentoEntity id, UUID entity) {
		// TODO Auto-generated method stub
		
	}
}
