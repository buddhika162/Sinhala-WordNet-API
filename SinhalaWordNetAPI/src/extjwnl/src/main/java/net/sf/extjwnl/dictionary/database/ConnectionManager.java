package net.sf.extjwnl.dictionary.database;

import net.sf.extjwnl.JWNLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection manager for database-backed dictionaries.
 *
 * @author John Didion <jdidion@didion.net>
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class ConnectionManager {

    /**
	 * @uml.property  name="driverClass"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.sql.Driver"
	 */
    private String driverClass;
    /**
	 * @uml.property  name="url"
	 */
    private String url;
    /**
	 * @uml.property  name="userName"
	 */
    private String userName;
    /**
	 * @uml.property  name="password"
	 */
    private String password;
    /**
	 * @uml.property  name="registered"
	 */
    private boolean registered;
    /**
	 * @uml.property  name="connection"
	 */
    private Connection connection;
    /**
	 * @uml.property  name="jndi"
	 */
    private String jndi;

    public ConnectionManager(String driverClass, String url, String userName, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public ConnectionManager(String jndi) {
        this.jndi = jndi;
    }


    public Query getQuery(String sql) throws SQLException, JWNLException {
        return new Query(sql, getConnection());
    }

    /**
	 * @return
	 * @throws SQLException
	 * @throws JWNLException
	 * @uml.property  name="connection"
	 */
    public Connection getConnection() throws SQLException, JWNLException {
        if (null != connection) {
            return connection;
        }
        if (jndi != null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                DataSource ds = (DataSource) envContext.lookup(jndi);
                if (ds != null) {
                    connection = ds.getConnection();
                    connection.setReadOnly(true);
                    return connection;
                }
            } catch (NamingException ne) {
                throw new JWNLException("JNDI_NAMING_EXCEPTION", ne);
            }
        }
        registerDriver();
        if (userName == null) {
            connection = DriverManager.getConnection(url);
            connection.setReadOnly(true);
            return connection;
        } else {
            connection = DriverManager.getConnection(url, userName, (password != null) ? password : "");
            connection.setReadOnly(true);
            return connection;
        }
    }

    private void registerDriver() throws JWNLException {
        if (!registered) {
            try {
                Driver driver = (Driver) Class.forName(driverClass).newInstance();
                DriverManager.registerDriver(driver);
                registered = true;
            } catch (Exception e) {
                throw new JWNLException("DICTIONARY_EXCEPTION_024", e);
            }
        }
    }

    public void close() {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                //nop
            }
            connection = null;
        }
    }
}