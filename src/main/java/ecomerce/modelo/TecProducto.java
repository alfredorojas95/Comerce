package ecomerce.modelo;

import java.util.ArrayList;

public class TecProducto {
	private int prodId;
	private String proNombre;
	private String proDescripcion;
	private Integer prPrecio;
	private String proUltimaUctualizacion;
	public TecCategoria cat;
	public ArrayList<TecProductoTecOrden> tecProductoTecOrden = new ArrayList<TecProductoTecOrden>();
}