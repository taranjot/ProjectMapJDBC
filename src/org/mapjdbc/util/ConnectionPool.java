package org.mapjdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;

import org.mapjdbc.exceptions.ApplicationException;

/**
 * This class is used to fetch the database connections and pool them in a list
 * and provide them to a user. The Max number of connections is kept to be 20
 * for in this case which can be used.
 * 
 * @author boyka
 * */
public class ConnectionPool {
	private static final int MAX_CONNECTIONS = 15;
	private static List<Connection> listConnections = null;
	private static ConnectionPool instance = null;

	/**
	 * This function is used to remove all the connection from the database.
	 * Should be run at the end of the application to clear the memory.
	 * 
	 * @author boyka
	 * 
	 * */
	public synchronized void removeAllConnections() throws ApplicationException {
		try {
			if (listConnections == null)
				return;
			int sz = listConnections.size();
			for (int i = 0; i < sz; i++) {
				Connection c = (Connection) listConnections.get(i);
				c.close();
			}
			listConnections = null;
		} catch (SQLException sqe) {
			throw new ApplicationException(100,
					"Cannot connect to the database to close the connections");
		}
	}

	/**
	 * Making a class Singleton for controlling the connection access.
	 * 
	 * @author boyka
	 * */
	public static synchronized ConnectionPool getInstance() {
		return instance == null ? new ConnectionPool() : instance;
	}

	/**
	 * Used to initialize all the connection. Check point if already initialized.
	 * 
	 * @author boyka
	 * */
	public synchronized void initialize() throws ApplicationException {
		if (listConnections == null) {
			try {
				String userName = "taran";
				String password = "mapjdbc";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				OracleDriver oracleDriver = new OracleDriver();
				DriverManager.registerDriver(oracleDriver);
				listConnections = new ArrayList<Connection>();
				System.out.println("Connection Established");
				int count = 0;
				while (count < MAX_CONNECTIONS) {
					Connection connection = DriverManager.getConnection(url, userName,
							password);
					listConnections.add(connection);
					count++;
				}
			} catch (Exception sqe) {
				throw new ApplicationException(101,
						"Cannot fetch the connection from the database");
			}
		}
	}

	/**
	 * This function is used to get the connection from the list of the
	 * connections initiated before.
	 * 
	 * @author boyka
	 * 
	 * */
	public synchronized Connection getConnection() {
		Connection conn = null;
		if (listConnections == null)
			return null;
		if (listConnections.size() > 0) {
			conn = (Connection) listConnections.get(0);
			listConnections.remove(0);
		}
		return conn;
	}

	/**
	 * This will be used in case if a user decides to put the connection after
	 * use. After using a connection need to put it back.
	 * 
	 * @author boyka
	 * */
	public synchronized void putConnection(Connection c) {
		listConnections.add(c);
		notifyAll();
	}
}