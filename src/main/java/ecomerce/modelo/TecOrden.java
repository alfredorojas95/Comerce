package ecomerce.modelo;

import java.util.ArrayList;

public class TecOrden {
	private int ordId;
	private String ordFcreacion;
	private Integer ordFumConfirmacion;
	private Integer ordPrecioTotal;
	public TecUsuario cli;
	public ArrayList<TecProductoTecOrden> tecProductoTecOrden = new ArrayList<TecProductoTecOrden>();
}