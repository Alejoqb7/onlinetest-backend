package co.edu.uco.onlinetest.crosscutting.excepciones;

public class ApiOnlineTestException extends OnlineTestException {

	private static final long serialVersionUID = 6950855047355313810L;

	private ApiOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.CROSSCUTTING);
	}
	
	public static OnlineTestException reportar(String mensajeUsuario) {
		return new ApiOnlineTestException(mensajeUsuario, mensajeUsuario, new Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
		return new ApiOnlineTestException(mensajeUsuario, mensajeTecnico, new Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		return new ApiOnlineTestException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
		
}
