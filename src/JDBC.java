import java.sql.*;
import java.util.Scanner;

public class JDBC {
	String user;
	String pass;
	
	Menue menue = new Menue();
	
	public JDBC giveUserAccess(JDBC giveUserAccess) {
		String expectedUser = "sa";
		String expectedPass = "root";

		Scanner accessSc = new Scanner(System.in);
		System.out.println("==================LOGIN TO THE DATABASE==================");
		boolean accessGranted = false;
		while (!accessGranted) {
			System.out.print("Enter your user name: ");
			String enteredUser = accessSc.next();
			System.out.print("Enter your password: ");
			String enteredPass = accessSc.next();
			System.out.println("=========================================================");

			// Check if the entered credentials match the expected values
			if (enteredUser.equals(expectedUser) && enteredPass.equals(expectedPass)) {
				giveUserAccess.user = enteredUser;
				giveUserAccess.pass = enteredPass;
				System.out.println("Access granted");
				menue.showMenue();
			} else {
				System.out.println("Access denied. Incorrect username or password.");
			}
		}
		
		// If the user fails to enter the correct credentials after three attempts, return the original object
		return giveUserAccess;
	}

//	public static void countryTable() {
//		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=University;" + "encrypt=true;"
//				+ "trustServerCertificate=true";
//
//		Connection con = null;
//		try {
//
//			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//			DriverManager.registerDriver(driver);
//
//			con = DriverManager.getConnection(url, user, pass);
//			String sql = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Countries')" + "BEGIN"
//					+ "CREATE TABLE Countries(" + "Country_Name VARCHAR(50)" + ");" + "END";
//		} catch (Exception ex) {
//			System.err.println(ex);
//		}
//	}
}
