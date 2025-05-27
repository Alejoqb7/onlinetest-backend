package co.edu.uco.onlinetest.data.dao.entity.pais.impl.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisAzureSQLDAO implements PaisDAO {

	private final Connection conexion;

	public PaisAzureSQLDAO(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public void create(PaisEntity entity) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("INSERT INTO Pais(id, nombre) VALUES (?, ?)");

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setObject(1, entity.getId());
			sentenciaPreparada.setString(2, entity.getNombre());
			sentenciaPreparada.executeUpdate();
		} catch (SQLException exception) {
			var mensajeUsuario = "No fue posible registrar el nuevo país. Intente más tarde.";
			var mensajeTecnico = "SQLException en INSERT Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al registrar el país.";
			var mensajeTecnico = "Error genérico en INSERT Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public List<PaisEntity> listByFilter(PaisEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisEntity> listAll() throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT id, nombre FROM Pais");
		var lista = new ArrayList<PaisEntity>();

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());
				ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {

			while (cursorResultados.next()) {
				var entidad = new PaisEntity();
				entidad.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
				entidad.setNombre(cursorResultados.getString("nombre"));
				lista.add(entidad);
			}
			return lista;

		} catch (SQLException exception) {
			var mensajeUsuario = "No fue posible listar los países. Intente más tarde.";
			var mensajeTecnico = "SQLException en SELECT ALL Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al listar los países.";
			var mensajeTecnico = "Error genérico en SELECT ALL Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public PaisEntity listById(UUID id) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT id, nombre FROM Pais WHERE id = ?");
		var resultado = new PaisEntity();

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setObject(1, id);

			try (ResultSet cursorResultados = sentenciaPreparada.executeQuery()) {
				if (cursorResultados.next()) {
					resultado.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
					resultado.setNombre(cursorResultados.getString("nombre"));
				}
			} catch (SQLException exception) {
				var mensajeUsuario = "No fue posible consultar el país por su identificador.";
				var mensajeTecnico = "SQLException en SELECT Pais BY ID: " + exception.getMessage();
				throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
			} catch (Exception exception) {
				var mensajeUsuario = "Se produjo un error inesperado al consultar el país por ID.";
				var mensajeTecnico = "Error genérico en SELECT Pais BY ID: " + exception.getMessage();
				throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
			}

			return resultado;

		} catch (SQLException exception) {
			var mensajeUsuario = "No fue posible preparar la consulta de país por ID.";
			var mensajeTecnico = "SQLException al preparar SELECT Pais BY ID: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado antes de consultar el país por ID.";
			var mensajeTecnico = "Error genérico al preparar SELECT Pais BY ID: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void update(PaisEntity entity, UUID id) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("UPDATE Pais SET nombre = ? WHERE id = ?");

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setString(1, entity.getNombre());
			sentenciaPreparada.setObject(2, id);
			sentenciaPreparada.executeUpdate();
		} catch (SQLException exception) {
			var mensajeUsuario = "No fue posible actualizar el país. Intente más tarde.";
			var mensajeTecnico = "SQLException en UPDATE Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al actualizar el país.";
			var mensajeTecnico = "Error genérico en UPDATE Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void delete(UUID id) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("DELETE FROM Pais WHERE id = ?");

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {
			sentenciaPreparada.setObject(1, id);
			sentenciaPreparada.executeUpdate();
		} catch (SQLException exception) {
			var mensajeUsuario = "No fue posible eliminar el país. Intente más tarde.";
			var mensajeTecnico = "SQLException en DELETE Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al eliminar el país.";
			var mensajeTecnico = "Error genérico en DELETE Pais: " + exception.getMessage();
			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}
}
