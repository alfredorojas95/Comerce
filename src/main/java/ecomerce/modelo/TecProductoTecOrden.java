package ecomerce.modelo;

public class TecProductoTecOrden {
	private Integer topCantidad;
	private Integer topSubtotal;
	public TecProducto prod;
	public TecOrden ord;

    public Integer getTopCantidad() {
        return topCantidad;
    }

    public void setTopCantidad(Integer topCantidad) {
        this.topCantidad = topCantidad;
    }

    public Integer getTopSubtotal() {
        return topSubtotal;
    }

    public void setTopSubtotal(Integer topSubtotal) {
        this.topSubtotal = topSubtotal;
    }

    public TecProducto getProd() {
        return prod;
    }

    public void setProd(TecProducto prod) {
        this.prod = prod;
    }

    public TecOrden getOrd() {
        return ord;
    }

    public void setOrd(TecOrden ord) {
        this.ord = ord;
    }
        
        
}