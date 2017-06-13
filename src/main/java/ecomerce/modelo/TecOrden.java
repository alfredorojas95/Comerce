package ecomerce.modelo;

import java.util.ArrayList;

public class TecOrden {
	private int ordId;
	private String ordFcreacion;
	private Integer ordFumConfirmacion;
	private Integer ordPrecioTotal;
	public TecUsuario cli;
	public ArrayList<TecProductoTecOrden> tecProductoTecOrden = new ArrayList<TecProductoTecOrden>();

    public TecOrden() {
    }

        
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public String getOrdFcreacion() {
        return ordFcreacion;
    }

    public void setOrdFcreacion(String ordFcreacion) {
        this.ordFcreacion = ordFcreacion;
    }

    public Integer getOrdFumConfirmacion() {
        return ordFumConfirmacion;
    }

    public void setOrdFumConfirmacion(Integer ordFumConfirmacion) {
        this.ordFumConfirmacion = ordFumConfirmacion;
    }

    public Integer getOrdPrecioTotal() {
        return ordPrecioTotal;
    }

    public void setOrdPrecioTotal(Integer ordPrecioTotal) {
        this.ordPrecioTotal = ordPrecioTotal;
    }

    public TecUsuario getCli() {
        return cli;
    }
    
     public int getIdCli() {
        return cli.getCliId();
    }

    public void setCli(TecUsuario cli) {
        this.cli = cli;
    }


}