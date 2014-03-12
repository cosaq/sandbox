package se.cosaq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

// connection to simple-webapp database

public class DbFactory {

	private static DbFactory theInstance = null;
	private static String MYSQL = "mysql";
	private static String dbms = DbFactory.MYSQL;
	private static String userName = "root";
	private static String password = "Conovan";
	private static String dbUrl = "jdbc:mysql://localhost:3306/simplewebapp";
	private static String dbName = "";

	// this is a singleton
	public static DbFactory getInstance() {
		if (theInstance == null) {
			theInstance = new DbFactory();
		}
		return theInstance;
	}

	// we all share the same connection
	private Connection theConnection = null;

	private Connection getConnection() throws SQLException,
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

		theConnection = conn;
		return theConnection;

	}

	public ProductDB getProduct(int idProduct) {
		ProductDB aProd = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection conn = DbFactory.getInstance().getConnection();
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

				conn.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		if (aProd != null) {
			aProd.setPartDBList(getPartForPRODUCT(idProduct));
		}

		return aProd;
	}

	public List<PartDB> getPartForPRODUCT(int productID) {
		List<PartDB> partList = new ArrayList<PartDB>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection conn = DbFactory.getInstance().getConnection();
			if (conn != null) {
				// Statements allow to issue SQL queries to the database
				statement = conn.createStatement();
				// Result set get the result of the SQL query
				String query = "select Part.idPart, Part.partName from Part JOIN PartProduct where PartProduct.idProduct="
						+ productID + " AND PartProduct.idPart=Part.idPart";
				;
				System.out.println("query=" + query);
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
					System.out.println("resultSet.first()=true");
					int id = resultSet.getInt(1);
					String name = resultSet.getString("partName");
					PartDB aProd = new PartDB(id, name);
					partList.add(aProd);
				}
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return partList;
	}

	public List<ProductDB> getAllProducts() {
		List<ProductDB> prodList = new ArrayList<ProductDB>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection conn = DbFactory.getInstance().getConnection();
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

				// for all products, get their parts
				Iterator<ProductDB> prodIter = prodList.iterator();
				while (prodIter.hasNext()) {
					ProductDB aProd = prodIter.next();
					if (aProd != null) {
						aProd.setPartDBList(getPartForPRODUCT(aProd
								.getProductId()));
					}
				}
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return prodList;
	}
}
