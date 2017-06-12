package ecomerce.persistencia.impl;

import ecomerce.modelo.TecUsuario;
import ecomerce.persistencia.dao.TecUsuarioDao;
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
public class TecUsuarioImp implements TecUsuarioDao {

    private final Connection conn;

    public TecUsuarioImp() {
        this.conn = MysqlDaoFactory.createConnection();
    }

    @Override
    public TecUsuario buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TecUsuario buscar(int idCat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TecUsuario> listar() {
        ArrayList<TecUsuario> usuarios = new ArrayList<>();

        ResultSet rs;
        String sql = "SELECT * FROM tec_usuario";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                System.out.println("NO HAY DATOS");

            } else {
                do {
                    TecUsuario usu = new TecUsuario();
                    usu.setCliId(rs.getInt("cli_id"));
                    usu.setCliNombre(rs.getString("cli_nombre"));
                    usu.setCliApellido(rs.getString("cli_apellido"));
                    usu.setCliTelefono(rs.getString("cli_telefono"));
                    usu.setCliDireccion(rs.getString("cli_direccion"));
                    usu.setCliComuna(rs.getString("cli_comuna"));
                    usuarios.add(usu);

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    @Override
    public boolean guardar(TecUsuario prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(TecUsuario prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Map the current row of the given ResultSet to an User.
     *
     * @param resultSet The ResultSet of which the current row is to be mapped
     * to an User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static TecUsuario map(ResultSet resultSet) throws SQLException {
        TecUsuario usu = new TecUsuario();
        usu.setCliId(resultSet.getInt("cli_id"));
        usu.setCliNombre(resultSet.getString("cli_nombre"));
        usu.setCliApellido(resultSet.getString("cli_apellido"));
        usu.setCliTelefono(resultSet.getString("cli_telefono"));
        usu.setCliDireccion(resultSet.getString("cli_direccion"));
        usu.setCliComuna(resultSet.getString("cli_comuna"));
        return usu;
    }

}
