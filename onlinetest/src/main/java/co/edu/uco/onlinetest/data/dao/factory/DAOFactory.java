package co.edu.uco.onlinetest.data.dao.factory;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.data.dao.factory.azuresql.AzureSQLDAOFactory;

public abstract class DAOFactory {

    public static DAOFactory getFactory(Factory factory) throws OnlineTestException {
        switch (factory) {
            case AZURE_SQL:
                return new AzureSQLDAOFactory();
            default:
                var mensajeUsuario = "Se ha presentado un problema tratando de obtener la información de la fuente de datos contra la que se llevarán a cabo las operaciones.";
                var mensajeTecnico = "Se solicitó la factoría " + factory + " pero no está implementada en el sistema.";
                throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    protected abstract void abrirConexion() throws OnlineTestException;

    public abstract void iniciarTransaccion() throws OnlineTestException;

    public abstract void confirmarTransaccion() throws OnlineTestException;

    public abstract void cancelarTransaccion() throws OnlineTestException;

    public abstract void cerrarConexion() throws OnlineTestException;

    public abstract PaisDAO getPaisDAO() throws OnlineTestException;

    public abstract DepartamentoDAO getDepartamentoDAO() throws OnlineTestException;

    public abstract CiudadDAO getCiudadDAO() throws OnlineTestException;
}
