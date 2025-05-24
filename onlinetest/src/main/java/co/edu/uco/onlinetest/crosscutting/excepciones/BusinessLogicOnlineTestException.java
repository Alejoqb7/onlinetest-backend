package co.edu.uco.onlinetest.crosscutting.excepciones;

public class BusinessLogicOnlineTestException extends OnlineTestException {

    private static final long serialVersionUID = 6950855047355313810L;

    private BusinessLogicOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.BUSINESS_LOGIC);
    }

    public static OnlineTestException reportar(String mensajeUsuario) {
        return new BusinessLogicOnlineTestException(
            mensajeUsuario,
            mensajeUsuario,
            new Exception()
        );
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new BusinessLogicOnlineTestException(
            mensajeUsuario,
            mensajeTecnico,
            new Exception()
        );
    }

    public static OnlineTestException reportar(String mensajeUsuario, Exception excepcionRaiz) {
        return new BusinessLogicOnlineTestException(
            mensajeUsuario,
            mensajeUsuario,
            excepcionRaiz
        );
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new BusinessLogicOnlineTestException(
            mensajeUsuario,
            mensajeTecnico,
            excepcionRaiz
        );
    }
}
