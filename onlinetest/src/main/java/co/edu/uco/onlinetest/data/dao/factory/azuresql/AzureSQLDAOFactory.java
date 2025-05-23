package co.edu.uco.onlinetest.data.dao.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.impl.azuresql.CiudadAzureSQLDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.impl.azuresql.DepartamentoAzureSQLDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.impl.azuresql.PaisAzureSQLDAO;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;

public class AzureSQLDAOFactory extends DAOFactory {

	private Connection conexion;
	private boolean transaccionEstaIniciada;
	private boolean conexionEstaAbierta;

	public AzureSQLDAOFactory() throws OnlineTestException {
		abrirConexion();
		transaccionEstaIniciada = false;
		conexionEstaAbierta = false;
	}

	@Override
	protected void abrirConexion() throws OnlineTestException {
		var baseDatos = "ONLINETESTDB";
		var servidor = "ORION.UCO.EDU.CO";

		try {
			DriverManager.getConnection("");
			conexionEstaAbierta = true;
		} catch (SQLException exception) {
			var mensajeUsuario = "Ocurrió un error al intentar conectar con la base de datos. Por favor, intente más tarde o contacte al administrador.";
			var mensajeTecnico = "SQLException al intentar establecer conexión con la base de datos " + baseDatos + " en el servidor " + servidor + ".";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al conectar con la base de datos. Comuníquese con soporte técnico.";
			var mensajeTecnico = "Excepción no controlada al conectar con la base de datos " + baseDatos + " en el servidor " + servidor + ".";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void iniciarTransaccion() throws OnlineTestException {
		asegurarConexionAbierta();

		try {
			conexion.setAutoCommit(false);
			transaccionEstaIniciada = true;
		} catch (SQLException exception) {
			var mensajeUsuario = "No se pudo iniciar la transacción con la base de datos. Intente de nuevo más tarde.";
			var mensajeTecnico = "SQLException al iniciar la transacción: fallo al desactivar AutoCommit en la conexión actual.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al iniciar la transacción.";
			var mensajeTecnico = "Excepción no controlada al establecer la transacción: posible error de conexión o estado de la base de datos.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void confirmarTransaccion() throws OnlineTestException {
		try {
			asegurarTransaccionIniciada();
			asegurarConexionAbierta();
			conexion.commit();
			DriverManager.getConnection("");
		} catch (OnlineTestException exception) {
			throw exception;
		} catch (SQLException exception) {
			var mensajeUsuario = "No se pudo confirmar la transacción. Verifique el estado de la operación o intente nuevamente.";
			var mensajeTecnico = "SQLException durante commit(): fallo al confirmar la transacción en la conexión activa.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al confirmar la transacción.";
			var mensajeTecnico = "Excepción no controlada durante commit(): posible error de conexión o integridad transaccional.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void cancelarTransaccion() throws OnlineTestException {
		asegurarConexionAbierta();

		try {
			asegurarTransaccionIniciada();
			conexion.rollback();
		} catch (OnlineTestException exception) {
			throw exception;
		} catch (SQLException exception) {
			var mensajeUsuario = "No fue posible cancelar la transacción. La operación no se completó correctamente.";
			var mensajeTecnico = "SQLException durante rollback(): error al revertir cambios pendientes.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al cancelar la transacción.";
			var mensajeTecnico = "Excepción no controlada durante rollback(): posible inconsistencia en el estado de la conexión.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	@Override
	public void cerrarConexion() throws OnlineTestException {
		try {
			asegurarConexionAbierta();
			conexion.close();
		} catch (SQLException exception) {
			var mensajeUsuario = "No se pudo cerrar correctamente la conexión con la base de datos.";
			var mensajeTecnico = "SQLException al cerrar la conexión: posible conexión inválida o error de red.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		} catch (Exception exception) {
			var mensajeUsuario = "Se produjo un error inesperado al cerrar la conexión con la base de datos.";
			var mensajeTecnico = "Excepción no controlada durante el cierre de la conexión: posible estado inconsistente.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
		}
	}

	private void asegurarTransaccionIniciada() throws OnlineTestException {
		if (!transaccionEstaIniciada) {
			var mensajeUsuario = "No se ha iniciado una transacción. La operación solicitada requiere una transacción activa.";
			var mensajeTecnico = "Validación fallida: se intentó hacer COMMIT o ROLLBACK sin haber llamado previamente a iniciarTransaccion().";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
		}
	}

	private void asegurarConexionAbierta() throws OnlineTestException {
		if (!conexionEstaAbierta) {
			var mensajeUsuario = "No hay una conexión activa con la base de datos. Por favor, inicie una conexión primero.";
			var mensajeTecnico = "Validación fallida: la conexión requerida está cerrada o no se ha establecido.";

			throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
		}
	}

	@Override
	public PaisDAO getPaisDAO() throws OnlineTestException {
		asegurarConexionAbierta();
		return new PaisAzureSQLDAO(conexion);
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() throws OnlineTestException {
		asegurarConexionAbierta();
		return new DepartamentoAzureSQLDAO(conexion);
	}

	@Override
	public CiudadDAO getCiudadDAO() throws OnlineTestException {
		asegurarConexionAbierta();
		return new CiudadAzureSQLDAO(conexion);
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
