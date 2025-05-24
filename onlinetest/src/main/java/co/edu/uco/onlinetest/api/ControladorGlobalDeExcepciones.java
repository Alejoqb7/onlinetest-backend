package co.edu.uco.onlinetest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

@ControllerAdvice
public class ControladorGlobalDeExcepciones {
	
	@ExceptionHandler(OnlineTestException.class)
	public ResponseEntity<String> controlarOnlineTestException(OnlineTestException exception) {
	
		return ResponseEntity<>(excepcion.getMensajeUsuario(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OnlineTestException.class)
	public ResponseEntity<String> controlarOnlineTestException(OnlineTestException exception) {
		var mensajeUsuario = exception.getMensajeUsuario();
		var mensajeTecnico = exception.getMensajeTecnico();
		
		return ResponseEntity<>(excepcion.getMensajeUsuario(), HttpStatus.BAD_REQUEST);
}
