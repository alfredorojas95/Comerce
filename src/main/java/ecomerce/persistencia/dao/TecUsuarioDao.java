/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.dao;

import ecomerce.modelo.TecUsuario;
import java.util.ArrayList;

/**
 *
 * @author wilfr
 */
public interface TecUsuarioDao {

    public TecUsuario buscar(String nombre);

    public TecUsuario buscar(int idCat);

    public ArrayList<TecUsuario> listar();

    public boolean guardar(TecUsuario prod);

    public boolean editar(TecUsuario prod);

    public boolean borrar(int id);
}
