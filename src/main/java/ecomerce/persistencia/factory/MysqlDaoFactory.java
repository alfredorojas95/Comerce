package ecomerce.persistencia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author oancan
 */
public class MysqlDaoFactory extends DAOFactory {

    private static String BDNAME;
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String CLAVE;

    public MysqlDaoFactory(ServletContext servletContext) {

        MysqlDaoFactory.DRIVER = (String) servletContext.getInitParameter("jdbcDriver");
        MysqlDaoFactory.URL = (String) servletContext.getInitParameter("jdbcUrl");
        MysqlDaoFactory.BDNAME = (String) servletContext.getInitParameter("jdbcBD");
        MysqlDaoFactory.USER = (String) servletContext.getInitParameter("jdbcUser");
        MysqlDaoFactory.CLAVE = (String) servletContext.getInitParameter("jdbcPassword");
        System.out.println(MysqlDaoFactory.DRIVER);
        System.out.println(MysqlDaoFactory.URL);
        System.out.println(MysqlDaoFactory.BDNAME);
        System.out.println(MysqlDaoFactory.USER);
        System.out.println(MysqlDaoFactory.CLAVE+"*");
    }

    public static Connection createConnection() {

        Connection con = null;
        try {
            Class.forName(MysqlDaoFactory.DRIVER);
            con = DriverManager.getConnection(MysqlDaoFactory.URL + MysqlDaoFactory.BDNAME,
                    MysqlDaoFactory.USER, MysqlDaoFactory.CLAVE);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DerbyDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
}
