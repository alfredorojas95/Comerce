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
    public TecUsuario buscar(int idUsu) {
        TecUsuario usuario = null;
        ResultSet rs;
        String sql = "SELECT * FROM tec_usuario WHERE cli_id = ?";

        try {

            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, idUsu);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, "NO HAY DATOS");

            } else {
                do {
                    usuario = new TecUsuario();
                    usuario.setCliId(rs.getInt("cli_id"));
                    usuario.setCliNombre(rs.getString("cli_nombre"));
                    usuario.setCliApellido(rs.getString("cli_apellido"));
                    usuario.setCliTelefono(rs.getString("cli_telefono"));
                    usuario.setCliDireccion(rs.getString("cli_direccion"));
                    usuario.setCliComuna(rs.getString("cli_comuna"));

                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
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
    public boolean guardar(TecUsuario usu) {
        boolean resultado = false;
        String sql = "INSERT INTO tec_usuario(cli_id, cli_nombre, cli_apellido, cli_telefono, cli_direccion, cli_comuna) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, usu.getCliId());
            pstm.setString(2, usu.getCliNombre());
            pstm.setString(3, usu.getCliApellido());
            pstm.setString(4, usu.getCliTelefono());
            pstm.setString(5, usu.getCliDireccion());
            pstm.setString(6, usu.getCliComuna());
            pstm.executeUpdate();
            resultado = true;

        } catch (SQLException ex) {
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    @Override
    public boolean editar(TecUsuario prod) {
        boolean result = false;
        String sql = "UPDATE tec_usuario SET cli_nombre = ?, cli_apellido = ?, cli_telefono = ?"
                + ", cli_direccion = ?, cli_comuna = ?  WHERE cli_id = ?";

        Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, "Cliente editar {0}", prod);

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setString(1, prod.getCliNombre());
            pstm.setString(2, prod.getCliApellido());
            pstm.setString(3, prod.getCliTelefono());
            pstm.setString(4, prod.getCliDireccion());
            pstm.setString(5, prod.getCliComuna());
            pstm.setInt(6, prod.getCliId());

            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, "Edita {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public boolean borrar(int id) {
        boolean result = false;
        String sql = "DELETE FROM tec_usuario WHERE cli_id = ?";

        try {
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int filasAfectadas = pstm.executeUpdate();
            result = (filasAfectadas != 0);
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, "BORRA {0}", result);

        } catch (SQLException ex) {
            Logger.getLogger(TecUsuarioImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
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
