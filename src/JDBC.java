import java.sql.*;
import java.util.Scanner;
public class JDBC {

	
	public static void countryTable() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=University;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;
		
		try {

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, user, pass);
			String sql = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Countries')" + "BEGIN"
					+ "CREATE TABLE Countries(" + "Country_Name VARCHAR(50)" + ");" + "END";
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
