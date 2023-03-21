import java.sql.*;

public class JDBC {
	
	
	public void contryTable() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + Access.databaseName + ";" + "encrypt=true;"
				+ "trustServerCertificate=true";
		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, Access.user, Access.pass);
			Statement st = (Statement) con.createStatement();

			// Create table
			String sql = "IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Countries') " 
		             + "CREATE TABLE Countries (Country_Name VARCHAR(50)); "
		             + "truncate table Countries; "
		             + "INSERT INTO Countries (Country_Name) ";
			((java.sql.Statement) st).executeUpdate(sql);
			System.out.println(" TABLE CREATED");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	} 
}
