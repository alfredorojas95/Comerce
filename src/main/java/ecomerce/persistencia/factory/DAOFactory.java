package ecomerce.persistencia.factory;

import ecomerce.persistencia.dao.TecCategoriaDao;
import ecomerce.persistencia.dao.TecProductoDao;
import ecomerce.persistencia.dao.TecUsuarioDao;
import ecomerce.persistencia.impl.TecCategoriaImpl;
import ecomerce.persistencia.impl.TecProductoImpl;
import ecomerce.persistencia.impl.TecUsuarioImp;
import javax.servlet.ServletContext;

public abstract class DAOFactory {

    public static DAOFactory getFactory(TipoBD bd, ServletContext context) {
        
        switch (bd) {

            case DERBY:
                return new DerbyDaoFactory();
            case MYSQL:
                return new MysqlDaoFactory(context);
            case ORACLE:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException();
        }

    }

    public TecCategoriaDao getTecCategoriaDao() { 
        return new TecCategoriaImpl(); 
    }

    public TecProductoDao getProductoDao() {
        return new TecProductoImpl(); 
    }
    
    public TecUsuarioDao getUsuarioDao() {
        return new TecUsuarioImp(); 
    }
}
