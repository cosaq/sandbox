package se.cosaq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// connection to simple-webapp database

public class DbFactory {

	private static String MYSQL = "mysql";

	private static String dbms = DbFactory.MYSQL;
	private static String userName = "root";
	private static String password = "Conovan";
	private static String dbUrl = "jdbc:mysql://localhost:3306/simplewebapp";

	private static String dbName = "";

	private static Connection getConnection() throws SQLException,
			ClassNotFoundException {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", DbFactory.userName);
		connectionProps.put("password", DbFactory.password);

		if (DbFactory.dbms.equals(DbFactory.MYSQL)) {
			conn = DriverManager
					.getConnection(DbFactory.dbUrl, connectionProps);
			if (conn != null)
				System.out.println("Connected to mysql database");
			else
				System.out.println("Failed to connect to mysql database");
		} else if (DbFactory.dbms.equals("derby")) {
			conn = DriverManager.getConnection("jdbc:" + DbFactory.dbms + ":"
					+ DbFactory.dbName + ";create=true", connectionProps);
		}
		return conn;

	}

	public static ProductDB getProduct(int idProduct) {
		ProductDB aProd = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection conn = DbFactory.getConnection();
			if (conn != null) {
				// Statements allow to issue SQL queries to the database
				statement = conn.createStatement();
				// Result set get the result of the SQL query
				String query = "select Product.productId, Product.productName from Product where productId="
						+ idProduct;
				System.out.println("query=" + query);
				resultSet = statement.executeQuery(query);
				if (resultSet.first()) {
					System.out.println("resultSet.first()=true");
					int id = resultSet.getInt(1);
					String name = resultSet.getString("productName");
					aProd = new ProductDB(id, name);
				} else
					System.out.println("resultSet.first()=false");

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return aProd;
	}

	public static List<ProductDB> getAllProducts() {
		List<ProductDB> prodList = new ArrayList<ProductDB>();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			conn = DbFactory.getConnection();
			if (conn != null) {
				// Statements allow to issue SQL queries to the database
				statement = conn.createStatement();
				// Result set get the result of the SQL query
				String query = "select Product.productId, Product.productName from Product ";
				;
				System.out.println("query=" + query);
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					System.out.println("resultSet.first()=true");
					int id = resultSet.getInt(1);
					String name = resultSet.getString("productName");
					ProductDB aProd = new ProductDB(id, name);
					prodList.add(aProd);
				}

			}
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return prodList;
	}
}
