/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.impl;

import ecomerce.modelo.TecProductoTecOrden;
import ecomerce.persistencia.dao.TecProductoOrdenDao;
import ecomerce.persistencia.factory.MysqlDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilfr
 */
public class TecProductoTecOrdenImp implements TecProductoOrdenDao {

    private final Connection conn;

    public TecProductoTecOrdenImp() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecProductoTecOrden buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecProductoTecOrden buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TecProductoTecOrden> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean guardar(TecProductoTecOrden prodOrden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(TecProductoTecOrden prodOrden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        boolean result = false;
        String sql = "DELETE FROM tec_producto_tec_orden WHERE ord_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
