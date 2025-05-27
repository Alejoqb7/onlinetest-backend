package co.edu.uco.onlinetest.entity;

import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public class PaisEntity {

	private UUID id;
	private String nombre;
	private boolean objetoVacio;

	public PaisEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		objetoVacio = true;
	}

	public PaisEntity(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}

	public PaisEntity(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public static PaisEntity obtenerValorDefecto() {
		return new PaisEntity();
	}

	public static PaisEntity obtenerValorDefecto(final PaisEntity pais) {
		return UtilObjeto.getInstance().obtenerValorDefecto(pais, obtenerValorDefecto());
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
	}

	public boolean isObjetoVacio() {
		return objetoVacio;
	}

	public boolean isObjetoDefecto() {
		return UtilUUID.esValorDefecto(getId()) && UtilTexto.getInstance().esValorDefecto(getNombre());
	}
}
