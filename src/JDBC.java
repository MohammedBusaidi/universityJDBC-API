import java.beans.Statement;
import java.sql.*;
import java.util.Scanner;

public class JDBC {
	static String user;
	static String pass;
	static String databaseName = "uniDatabase";

	Menue menue = new Menue();

	public JDBC giveUserAccess(JDBC giveUserAccess) {
		String expectedUser = "sa";
		String expectedPass = "root";

		Scanner accessSc = new Scanner(System.in);
		System.out.println("==================LOGIN TO THE DATABASE==================");
		boolean accessGranted = false;
		while (!accessGranted) {
			System.out.print("Enter your user name: ");
			String userInput = accessSc.next();
			System.out.print("Enter your password: ");
			String passInput = accessSc.next();
			System.out.println("=========================================================");

			// Check if the entered credentials match the expected values
			if (userInput.equals(expectedUser) && passInput.equals(expectedPass)) {
				giveUserAccess.user = userInput;
				giveUserAccess.pass = passInput;
				System.out.println("Access granted");
				menue.showMenue();
			} else {
				System.out.println("Access denied. Incorrect username or password.");
			}
		}
		return giveUserAccess;

	}

	public void initializeDatabase() {
		Scanner dataSc = new Scanner(System.in);
		System.out.println("Enter the name of database");
		String databaseInput = dataSc.next();

		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) con.createStatement();

			// Create database
			String sql1 = "IF NOT EXISTS(SELECT * FROM sys.databases WHERE name='" + databaseName + "')\r\n"
					+ "CREATE DATABASE " + databaseName;
			((java.sql.Statement) st).executeUpdate(sql1);
			System.out.println("DATABASE CREATED");
			// Update url with the database name
			url += ";databaseName=" + databaseName;
			con = DriverManager.getConnection(url, user, pass);
			Statement st2 = (Statement) con.createStatement();

			// Create table
			String sql2 = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Universty')"
					+ "CREATE TABLE Universty (" + "  id INTEGER IDENTITY PRIMARY KEY,"
					+ "  name VARCHAR(255)," + "  country VARCHAR(255)," + "  state_province VARCHAR(255),"
					+ "  domains VARCHAR(MAX)," + "  web_pages VARCHAR(MAX),"
					+ "  alpha_two_code VARCHAR(2)" + ");";
			((java.sql.Statement) st2).executeUpdate(sql2);
			System.out.println(" TABLE CREATED");

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}
//	public static void countryTable() {
//	String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=University;" + "encrypt=true;"
//			+ "trustServerCertificate=true";
//
//	Connection con = null;
//	try {
//
//		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//		DriverManager.registerDriver(driver);
//
//		con = DriverManager.getConnection(url, user, pass);
//		String sql = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Countries')" + "BEGIN"
//				+ "CREATE TABLE Countries(" + "Country_Name VARCHAR(50)" + ");" + "END";
//	} catch (Exception ex) {
//		System.err.println(ex);
//	}
}
