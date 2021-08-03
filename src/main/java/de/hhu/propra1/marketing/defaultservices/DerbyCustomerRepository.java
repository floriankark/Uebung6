package de.hhu.propra1.marketing.defaultservices;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DerbyCustomerRepository {
	private Connection connection;

	public DerbyCustomerRepository() {
		connectToDerby();
	}

	private void connectToDerby() {
		String dbUrl = "jdbc:derby:customers;create=true";
		try {
			connection = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Collection<String> getMailAddresses() {
		ArrayList<String> res = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				String mail = rs.getString("mail");
				res.add(mail);
			}
		} catch (SQLException e) {
			// return an empty result
		}
		return res;
	}

	public void addMailAddress(String adr) {
		try {
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (mail) VALUES ?");
			stmt.setString(1, adr);
			stmt.execute();
		} catch (SQLException e) {
			// Use Logger
			System.err.println("Insert of " + adr + " failed.");
		}
	}

	public void initDatabase() {
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("CREATE TABLE users(mail VARCHAR(200))");
		} catch (SQLException e) {
			throw new IllegalStateException("Fatal Error: Cannot create table. ", e);
		}

	}

}
