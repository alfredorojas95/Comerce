package ecomerce.modelo;

import java.util.ArrayList;

public class TecProducto {

    private int prodId;
    private String proNombre;
    private String proDescripcion;
    private Integer prPrecio;
    private String proUltimaUctualizacion;
    public TecCategoria cat;
    public ArrayList<TecProductoTecOrden> tecProductoTecOrden;

    public TecProducto() {
        tecProductoTecOrden = new ArrayList<TecProductoTecOrden>();
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProNombre() {
        return proNombre;
    }

    public void setProNombre(String proNombre) {
        this.proNombre = proNombre;
    }

    public String getProDescripcion() {
        return proDescripcion;
    }

    public void setProDescripcion(String proDescripcion) {
        this.proDescripcion = proDescripcion;
    }

    public Integer getPrPrecio() {
        return prPrecio;
    }

    public void setPrPrecio(Integer prPrecio) {
        this.prPrecio = prPrecio;
    }

    public String getProUltimaUctualizacion() {
        return proUltimaUctualizacion;
    }

    public void setProUltimaUctualizacion(String proUltimaUctualizacion) {
        this.proUltimaUctualizacion = proUltimaUctualizacion;
    }


    public int getid() {
        return cat.getCatId();
    }
    
    public TecCategoria getCat(){
        return this.cat;
    }

    public void setCat(TecCategoria cat) {
        this.cat = cat;
    }

    public ArrayList<TecProductoTecOrden> getTecProductoTecOrden() {
        return tecProductoTecOrden;
    }

    public void setTecProductoTecOrden(ArrayList<TecProductoTecOrden> tecProductoTecOrden) {
        this.tecProductoTecOrden = tecProductoTecOrden;
    }

}
