package com.monstarlab.jdbctraining1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 
 * @author Francis Santos
 * 
 *         This is the stored procedure definition
 * 
 *         use training;
 * 
 *         DROP PROCEDURE IF EXISTS INSERT_DELETE_BASE_ORDER; DELIMITER //
 *         CREATE PROCEDURE IF NOT EXISTS INSERT_DELETE_BASE_ORDER(x varchar(8))
 *         BEGIN INSERT INTO base_order (order_number, type, createdate, status,
 *         user_id) VALUES (x, x, CURRENT_DATE, x, 1); DELETE FROM base_order
 *         where order_number=x and type=x; END // DELIMITER ;
 *
 */

public class SantosFrancisExercise3 {

	private final static String SERVER_NAME = "localhost";
	private final static int PORT = 3306;
	private final static String DATABASE_NAME = "training";
	private final static String USER = "root";
	private final static String PASSWORD = "";

	private final static String STR_SELECT = "SELECT order_number, type FROM base_order";

	private final static String STR_UPDATE = "UPDATE base_order bo SET bo.type = ? WHERE bo.type = ?";

	private final static String STR_DELETE = "DELETE FROM base_order WHERE id=?";

	private final static String STR_INSERT = "INSERT INTO base_order (order_number, type, createdate, status, user_id) VALUES (?, ?, ?, ?, ?);";

	static Connection connection = null;

	static MysqlDataSource ds = new MysqlDataSource();

	public static void main(String[] args) {

		ds.setServerName(SERVER_NAME);
		ds.setPortNumber(PORT);
		ds.setDatabaseName(DATABASE_NAME);
		ds.setUser(USER);
		ds.setPassword(PASSWORD);

		try {
			connection = ds.getConnection();

		} catch (SQLException e) {
			System.out.println("Connection unsuccessful");
			e.printStackTrace();
		}

		System.out.println("Connection successful");

		doInsert();
		doUpdate();
		doSelect();
		doDelete();
		doStoredProcedure();

		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Disconnection unsuccessful");
			e.printStackTrace();
		}
		System.out.println("Disconnection successful");
	}

	private static void doInsert() {
		try {
			System.out.println("BEFORE INSERT");
			String query = new String(STR_SELECT);
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}

			query = STR_INSERT;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			//Hardcoded values for the INSERT statement
			preparedStatement.setString(1, "Order # 7");
			preparedStatement.setString(2, "SALES");
			preparedStatement.setString(3, "2017-06-01 16:10:00");
			preparedStatement.setString(4, "CLOSED");
			preparedStatement.setInt(5, 1);
			preparedStatement.executeUpdate();

			System.out.println("AFTER INSERT");
			query = new String(STR_SELECT);
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}

		} catch (SQLException ex) {
			System.out.println("An error occurred during insert");
			ex.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private static void doUpdate() {
		try {
			System.out.println("BEFORE UPDATE");
			String query = new String(STR_SELECT);
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}

			query = STR_UPDATE;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			//Hardcoded update from SERVICE to SERV
			preparedStatement.setString(1, "SERV");
			preparedStatement.setString(2, "SERVICE");
			preparedStatement.executeUpdate();

			System.out.println("AFTER UPDATE");
			query = new String(STR_SELECT);
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}

		} catch (SQLException ex) {
			System.out.println("An error occurred during update.");
			ex.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private static void doDelete() {
		try {
			System.out.println("BEFORE DELETE");
			String query = new String(STR_SELECT);
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}

			query = STR_DELETE;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			// Hardcoded delete of the id=6 from base_order
			preparedStatement.setInt(1, 6);
			preparedStatement.executeUpdate();

			System.out.println("AFTER DELETE");
			query = new String(STR_SELECT);
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}

		} catch (SQLException ex) {
			System.out.println("An error occurred during delete");
			ex.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private static void doSelect() {
		try {
			System.out.println("PERFORMING SELECT");
			String query = new String(STR_SELECT);
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String orderNumber = rs.getString("order_number");
				String type = rs.getString("type");
				System.out.println("Order Number=" + orderNumber + ", Type=" + type);
			}
		} catch (SQLException ex) {
			System.out.println("An error occurred during select");
			ex.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private static void doStoredProcedure() {
		try {
			System.out.println("BEFORE STORED PROCEDURE");
			String query = new String(STR_SELECT);

			query = "CALL INSERT_DELETE_BASE_ORDER(?)";

			CallableStatement callableStatement = connection.prepareCall(query);
			callableStatement.setString(1, "x");
			ResultSet rs = callableStatement.executeQuery();

			System.out.println("AFTER STORED PROCEDURE");

		} catch (SQLException ex) {
			System.out.println("An error occurred during stored procedure");
			ex.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}