package co.edu.uco.onlinetest.crosscutting.utilitarios;

public final class UtilTexto {

	private static UtilTexto instancia = new UtilTexto();
	public static final String VACIO = "";
	public final static String PATRON_SOLO_LETRAS_ESPACIOS = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ ]+$";

	private UtilTexto() {

	}
	
	public boolean estaVacia(final String valor) {
		return VACIO.equals(quitarEspaciosEnBlancoInicioFin(valor));
	}

	public static UtilTexto getInstance() {
		return instancia;
	}

	
	public boolean patronEsValido(final String valor, final String patron) {
		return obtenerValorDefecto(valor).matches(patron);
	}
	
	public boolean contieneSoloLetrasEspacios(final String valor) {
		return patronEsValido(valor, PATRON_SOLO_LETRAS_ESPACIOS);
	}

	public boolean esNula(final String valor) {
		return UtilObjeto.getInstance().esNulo(valor);
	}

	public String obtenerValorDefecto(final String valorOriginal, final String valorDefecto) {
		return UtilObjeto.getInstance().obtenerValorDefecto(valorOriginal, valorDefecto);
	}

	public String obtenerValorDefecto(final String valor) {
		return obtenerValorDefecto(valor, VACIO);
	}
	
	public String obtenerValorDefecto() {
		return VACIO;
	}

	public String quitarEspaciosEnBlancoInicioFin(final String valor) {
		return obtenerValorDefecto(valor).trim();	
	}
	
	public boolean esValorDefecto(final String valor) {
		return obtenerValorDefecto(valor).equals(obtenerValorDefecto());
	}
}
