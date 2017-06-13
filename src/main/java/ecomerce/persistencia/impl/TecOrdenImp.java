/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.impl;

import ecomerce.modelo.TecOrden;
import ecomerce.modelo.TecUsuario;
import ecomerce.persistencia.dao.TecOrdenDao;
import ecomerce.persistencia.factory.MysqlDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilfr
 */
public class TecOrdenImp implements TecOrdenDao {

    private final Connection conn;

    public TecOrdenImp() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecOrden buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecOrden buscar(int idOrden) {
        TecOrden orden = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_orden WHERE ord_id = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idOrden);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    orden = new TecOrden();
                    orden.setOrdId(rs.getInt("ord_id"));

                    int id = Integer.parseInt(rs.getString("cli_id"));
                    TecUsuarioImp usuImp = new TecUsuarioImp();
                    TecUsuario usu = usuImp.buscar(id);
                    orden.setCli(usu);
                    orden.setOrdFcreacion(rs.getString("ord_fcreacion"));
                    orden.setOrdFumConfirmacion(rs.getInt("ord_num_confirmacion"));
                    orden.setOrdPrecioTotal(rs.getInt("ord_precio_total"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orden;

    }

    @Override
    public ArrayList<TecOrden> listar() {
        ArrayList<TecOrden> ordenes = new ArrayList<>();

        ResultSet rs;
        String sql = "SELECT * FROM tec_orden";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    TecOrden orden = new TecOrden();
                    orden.setOrdId(rs.getInt("ord_id"));

                    int id = rs.getInt("cli_id");
                    TecUsuarioImp usuImp = new TecUsuarioImp();
                    TecUsuario usu = usuImp.buscar(id);
                    orden.setCli(usu);
                    orden.setOrdFcreacion(rs.getString("ord_fcreacion"));
                    orden.setOrdFumConfirmacion(rs.getInt("ord_num_confirmacion"));
                    orden.setOrdPrecioTotal(rs.getInt("ord_precio_total"));
                    ordenes.add(orden);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordenes;
    }

    @Override
    public boolean guardar(TecOrden orden) {
        boolean resultado = false;
        String sql = "INSERT INTO tec_orden(cli_id, ord_fcreacion, ord_num_confirmacion, ord_precio_total) values(?,?,?,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, orden.getIdCli());
            pstm.setString(2, orden.getOrdFcreacion());
            pstm.setInt(3, orden.getOrdFumConfirmacion());
            pstm.setInt(4, orden.getOrdPrecioTotal());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean editar(TecOrden orden) {
        boolean result = false;
        String sql = "UPDATE tec_orden SET cli_id = ?, ord_fcreacion = ?, ord_num_confirmacion = ?, ord_precio_total = ?  WHERE ord_id = ?";
        Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, "Categroai editar {0}", orden);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, orden.getCli().getCliId());
            pstm.setString(2, orden.getOrdFcreacion());
            pstm.setInt(3, orden.getOrdFumConfirmacion());
            pstm.setInt(4, orden.getOrdPrecioTotal());
            pstm.setInt(5, orden.getOrdId());

            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecOrdenImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean borrar(int id) {

        boolean result = false;
        String sql = "DELETE FROM tec_orden WHERE ord_id = ?";

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
