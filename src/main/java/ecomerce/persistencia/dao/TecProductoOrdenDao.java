/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.dao;

import ecomerce.modelo.TecProductoTecOrden;
import java.util.ArrayList;

/**
 *
 * @author wilfr
 */
public interface TecProductoOrdenDao {
    
    public TecProductoTecOrden buscar(String nombre);

    public TecProductoTecOrden buscar(int id);

    public ArrayList<TecProductoTecOrden> listar();

    public boolean guardar(TecProductoTecOrden prodOrden);

    public boolean editar(TecProductoTecOrden prodOrden);

    public boolean borrar(int id);
}
