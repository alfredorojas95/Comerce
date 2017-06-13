/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.dao;

import ecomerce.modelo.TecOrden;
import java.util.ArrayList;

/**
 *
 * @author wilfr
 */
public interface TecOrdenDao {
    public TecOrden buscar(String nombre);

    public TecOrden buscar(int idCat);

    public ArrayList<TecOrden> listar();

    public boolean guardar(TecOrden orden);

    public boolean editar(TecOrden orden);

    public boolean borrar(int id);
}
