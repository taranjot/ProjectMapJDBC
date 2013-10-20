package org.mapjdbc.util;

/**
 * 
 * Description: This is just basic universal connection pool program
 * 
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.mapjdbc.exceptions.ApplicationException;
import oracle.ucp.jdbc.
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class BasicConnectionPool {
	
	public static void main(String args[]) throws SQLException {
		try
		{
		/*Creating pool-enabled data source instance*/
		PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
		/*set the connection properties on the data source*/
		pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
		pds.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
		pds.setUser("system");
		pds.setPassword("system"); 
		
		//Get a database connection from the datasource.
		Connection conn = pds.getConnection();
		System.out.println("\nConnection obtained from UniversalConnectionPool\n");
		//some work with the connection.
		Statement stmt = conn.createStatement();
		stmt.execute("select * from foo");
		//Close the Connection.
		conn.close();
		conn=null;
		System.out.println("Connection returned to the UniversalConnectionPool\n");
		}
		
		catch(SQLException e)
		{
			new ApplicationException(e.getErrorCode(),e.getMessage());
		}
	}
}
