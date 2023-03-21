import java.io.File;
import java.io.IOException;
import java.sql.*;

public class JDBC {

	public void initializeDatabase() {
		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, Access.user, Access.pass);
			Statement st = con.createStatement();

			// Check if the database exists
			String sql = "SELECT * FROM sys.databases WHERE name='" + Access.databaseName + "'";
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				// Update url with the existing database name
				url += ";databaseName=" + Access.databaseName;
				con = DriverManager.getConnection(url, Access.user, Access.pass);
				Statement st2 = con.createStatement();

				// Check if the universities table exists
				String sql1 = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'universities'";
				rs = st2.executeQuery(sql1);

				if (!rs.next()) {
					// Create table if it doesn't exist
					String sql2 = "CREATE TABLE universities (\r\n" + "  id INTEGER IDENTITY PRIMARY KEY,\r\n"
							+ "  name VARCHAR(255),\r\n" + "  country VARCHAR(255),\r\n"
							+ "  state_province VARCHAR(255),\r\n" + "  domains VARCHAR(MAX),\r\n"
							+ "  web_pages VARCHAR(MAX),\r\n" + "  alpha_two_code VARCHAR(2)\r\n" + ");";
					st2.executeUpdate(sql2);
					System.out.println("TABLE CREATED!");
				} else {
					System.out.println("Table already exists!");
				}
			}
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void insert_data_universities() {
		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection(url, Access.user, Access.pass);
			Statement st = con.createStatement();

			url += ";databaseName=" + Access.databaseName;
			con = DriverManager.getConnection(url, Access.user, Access.pass);

			String sql = "INSERT INTO universities(name, country, state_province, domains, web_pages, alpha_two_code) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			University[] uni = APIConsumer.uni;
			for (University myUni : uni) {
				ps.setString(1, myUni.name);
				ps.setString(2, myUni.country);
				ps.setString(3, myUni.state_province);
				ps.setString(4, String.join(",", myUni.domains));
				ps.setString(5, String.join(",", myUni.web_pages));
				ps.setString(6, myUni.alpha_two_code);
				ps.executeUpdate();
			}
			System.out.println("DATA INSERTED!");
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void backupDatabase() {
		//create a new file
		File backupFile = new File("backup_file.sql");
		try {
			backupFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String filePath = "backup_file.sql";

		try {
			Process process = Runtime.getRuntime().exec("mysqldump -u " + Access.user + " -p" + Access.pass
					+ " --add-drop-database -B " + Access.databaseName + " -r " + filePath);
			int processComplete = process.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
