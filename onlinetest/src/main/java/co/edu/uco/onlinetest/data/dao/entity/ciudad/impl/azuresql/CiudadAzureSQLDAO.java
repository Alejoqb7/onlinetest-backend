package co.edu.uco.onlinetest.data.dao.entity.ciudad.impl.azuresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.entity.CiudadEntity;

public class CiudadAzureSQLDAO implements CiudadDAO {

	private final Connection conexion;

	public CiudadAzureSQLDAO(final Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public void create(final CiudadEntity entity) {
	}

	@Override
	public List<CiudadEntity> listByFilter(final CiudadEntity filter) {
		return null;
	}

	@Override
	public List<CiudadEntity> listAll() {
		return null;
	}

	@Override
	public CiudadEntity listById(final UUID id) {
		return null;
	}

	@Override
	public void delete(final UUID id) {
	}

	@Override
	public void update(CiudadEntity id, UUID entity) {
		// TODO Auto-generated method stub
		
	}
}
