/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecomerce.persistencia.impl;

import ecomerce.modelo.TecCategoria;
import ecomerce.modelo.TecProducto;
import ecomerce.persistencia.dao.TecProductoDao;
import ecomerce.persistencia.factory.MysqlDaoFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilfr
 */
public class TecProductoImpl implements TecProductoDao {

    private final Connection conn;

    public TecProductoImpl() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecProducto buscar(String nombre) {
        return null;
    }

    @Override
    public TecProducto buscar(int idCat) {
        TecProducto prod = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_producto WHERE prod_id = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idCat);
            rs = pstm.executeQuery();
            

            if (!rs.next()) {
                Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    prod = new TecProducto();
                    prod.setProdId(rs.getInt("prod_id"));
                    
                    int idCategoria = rs.getInt("cat_id");
                    TecCategoriaImpl catImp = new TecCategoriaImpl();
                    TecCategoria cat = catImp.buscar(idCategoria);
                    prod.setCat(cat);
                    prod.setProNombre(rs.getString("pro_nombre"));
                    prod.setProDescripcion(rs.getString("pro_descripcion"));
                    prod.setPrPrecio(rs.getInt("pro_precio"));
                    prod.setProUltimaUctualizacion(rs.getString("pro_ultima_actualizacion"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }

    @Override
    public ArrayList<TecProducto> listar() {
        ArrayList<TecProducto> productos = new ArrayList<>();

        ResultSet rs;
        String sql = "SELECT * FROM tec_producto";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    TecProducto prod = new TecProducto();
                    prod.setProdId(rs.getInt("prod_id"));
                    
                    int id = rs.getInt("cat_id");
                    TecCategoriaImpl catImp = new TecCategoriaImpl();
                    TecCategoria cat = catImp.buscar(id);
                    prod.setCat(cat);
                    prod.setProNombre(rs.getString("pro_nombre"));
                    prod.setProDescripcion(rs.getString("pro_descripcion"));
                    prod.setPrPrecio(rs.getInt("pro_precio"));
                    prod.setProUltimaUctualizacion(rs.getString("pro_ultima_actualizacion"));
                    productos.add(prod);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecCategoriaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    @Override
    public boolean guardar(TecProducto prod) {
        boolean resultado = false;
        String sql = "INSERT INTO tec_producto(cat_id, pro_nombre, pro_descripcion, pro_precio, pro_ultima_actualizacion) values(?,?,?,?,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, prod.getProdId());
            pstm.setString(2, prod.getProNombre());
            pstm.setString(3, prod.getProDescripcion());
            pstm.setInt(4, prod.getPrPrecio());
            pstm.setString(5, prod.getProUltimaUctualizacion());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;


    }

    @Override
    public boolean editar(TecProducto prod) {
        boolean result = false;
        String sql = "UPDATE tec_producto SET cat_id = ?, pro_nombre = ?, pro_descripcion = ?, pro_precio = ?, pro_ultima_actualizacion = ?  WHERE prod_id = ?";
        Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "Categroai editar {0}", prod);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, prod.getCat().getCatId());
            pstm.setString(2, prod.getProNombre());
            pstm.setString(3, prod.getProDescripcion());
            pstm.setInt(4, prod.getPrPrecio());
            pstm.setString(5, prod.getProUltimaUctualizacion());
            pstm.setInt(6, prod.getProdId());

            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean borrar(int id) {
        boolean result = false;
        String sql = "DELETE FROM tec_producto WHERE prod_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
