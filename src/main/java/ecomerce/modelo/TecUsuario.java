package ecomerce.modelo;

import java.util.ArrayList;

public class TecUsuario {
	private int cliId;
	private String cliNombre;
	private String cliApellido;
	private String cliTelefono;
	private String cliDireccion;
	private String cliComuna;
	public ArrayList<TecOrden> tecOrden = new ArrayList<TecOrden>();

	public TecUsuario() {
		throw new UnsupportedOperationException();
	}

	public int getCliId() {
		return this.cliId;
	}

	public void setCliId(int cliId) {
		this.cliId = cliId;
	}

	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	public String getCliApellido() {
		return this.cliApellido;
	}

	public void setCliApellido(String cliApellido) {
		this.cliApellido = cliApellido;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliComuna() {
		return this.cliComuna;
	}

	public void setCliComuna(String cliComuna) {
		this.cliComuna = cliComuna;
	}
}