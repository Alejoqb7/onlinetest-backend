package co.edu.uco.onlinetest.crosscutting.excepciones;

public class CrossCuttingOnlineTestException extends OnlineTestException {

	private static final long serialVersionUID = 6950855047355313810L;

	private CrossCuttingOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.API);
	}
	
	public static OnlineTestException reportar(String mensajeUsuario) {
		return new CrossCuttingOnlineTestException(mensajeUsuario, mensajeUsuario, new Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
		return new CrossCuttingOnlineTestException(mensajeUsuario, mensajeTecnico, new Exception());
	}
	
	public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
		return new CrossCuttingOnlineTestException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
	}
		
}
