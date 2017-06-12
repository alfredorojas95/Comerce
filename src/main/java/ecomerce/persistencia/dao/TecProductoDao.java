/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.dao;

import ecomerce.modelo.TecProducto;
import java.util.ArrayList;

/**
 *
 * @author wilfr
 */
public interface TecProductoDao {
    
    public TecProducto buscar(String nombre);

    public TecProducto buscar(int idCat);

    public ArrayList<TecProducto> listar();

    public boolean guardar(TecProducto prod);

    public boolean editar(TecProducto prod);

    public boolean borrar(int id);
}
