package de.feu.plib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class OracleConnectionTest {

	/**
	 * Connection test to oracle database
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.13.130:1521:XE", "admin", "admin");

			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT name FROM DE_COMPANY");

			while (rs.next()) {
				System.out.println("name: " + rs.getString("name"));
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
