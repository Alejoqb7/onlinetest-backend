package co.edu.uco.onlinetest.data.dao.entity.pais.impl.azuresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisAzureSQLDAO implements PaisDAO {

	private Connection conexion;

	public PaisAzureSQLDAO(Connection conexion) {
		this.conexion = conexion;
	}

	@Override
	public void create(PaisEntity entity) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();

		sentenciaSQL.append("INSERT INTO Pais(id, nombre) VALUES (?, ?)");

		try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

			sentenciaPreparada.setObject(1, entity.getId());
			sentenciaPreparada.setString(2, entity.getNombre());

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exception) {
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un problema INESPERADO tratando de registrar la información de un nuevo país.";
			var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de hacer un INSERT en la tabla país. Para más información, consulte el log de errores.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}

	}

	@Override
	public List<PaisEntity> listByFilter(PaisEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisEntity> listAll() {
		var PaisEntityRetorno = new PaisEntity();
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT  id, nombre from PAIS WHERE id = ?");
		
		try(var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())){
			
			Object id;
			sentenciaPreparada.setObject(1, id);
			
			try(var cursorResultados = sentenciaPreparada.executeQuery()){
				
				if(cursorResultados.next()) {
					PaisEntityRetorno.setId(UtilUUID.convertirAUUID((String) cursorResultados.getObject("id")));
					PaisEntityRetorno.setNombre(cursorResultados.getString("nombre"));
				}
				
			} catch (SQLException exception) {
				var mensajeUsuario = "Se produjo un problema tratando de consultar la información del país con el identificador deseado.";
				var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer un SELECT en la tabla país por id. Para más información, consulte el log de errores.";

				throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
			} catch (Exception exception) {
				var mensajeUsuario = "Se produjo un problema INESPERADO tratando de consultar la información de un nuevo país.";
				var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de hacer un SELECT en la tabla país. Para más información, consulte el log de errores.";

	            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		return null;
	}

	@Override
	public PaisEntity listById(UUID id) throws OnlineTestException {

		var PaisEntityRetorno = new PaisEntity();
		var sentenciaSQL = new StringBuilder();
		sentenciaSQL.append("SELECT  id, nombre from PAIS WHERE id = ?");

		try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

			sentenciaPreparada.setObject(1, id);

			try (var cursorResultados = sentenciaPreparada.executeQuery()) {

				if (cursorResultados.next()) {
					PaisEntityRetorno.setId(UtilUUID.convertirAUUID((String) cursorResultados.getObject("id")));
					PaisEntityRetorno.setNombre(cursorResultados.getString("nombre"));
				}

			} catch (SQLException exception) {
				var mensajeUsuario = "Se produjo un problema tratando de consultar la información del país con el identificador deseado.";
				var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer un SELECT en la tabla país por id. Para más información, consulte el log de errores.";

				throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
			} catch (Exception exception) {
				var mensajeUsuario = "Se produjo un problema INESPERADO tratando de consultar la información de un nuevo país.";
				var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de hacer un SELECT en la tabla país. Para más información, consulte el log de errores.";

				throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

			}

		} catch (SQLException exception) {
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un problema INESPERADO tratando de registrar la información de un nuevo país.";
			var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de hacer un INSERT en la tabla país. Para más información, consulte el log de errores.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
		return PaisEntityRetorno;
	}

	@Override
	public void update(PaisEntity entity, UUID id) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();

		sentenciaSQL.append("UPDATE Pais SET nombre = ?  WHERE id = ?");

		try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

			sentenciaPreparada.setString(1, entity.getNombre());
			sentenciaPreparada.setObject(2, id);

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exception) {
			var mensajeUsuario = "Se produjo un problema tratando de modificar la información de un nuevo país.";
			var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer un UPDATE en la tabla país. Para más información, consulte el log de errores.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un problema INESPERADO tratando de modificar la información de un nuevo país.";
			var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de hacer un UPDATE en la tabla país. Para más información, consulte el log de errores.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void delete(UUID id) throws OnlineTestException {
		var sentenciaSQL = new StringBuilder();

		sentenciaSQL.append("DELETE Pais WHERE id = ?");

		try (var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

			sentenciaPreparada.setObject(1, id);

			sentenciaPreparada.executeUpdate();

		} catch (SQLException exception) {
			var mensajeUsuario = "Se produjo un problema tratando de eliminar de forma definitiva la información de un nuevo país.";
			var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer un DELETE en la tabla país. Para más información, consulte el log de errores.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un problema INESPERADO tratando de eliminar de forma definitiva la información de un nuevo país.";
			var mensajeTecnico = "Se presentó una NO CONTROLADA excepción de tipo Exception tratando de hacer un DELETE en la tabla país. Para más información, consulte el log de errores.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}
}
