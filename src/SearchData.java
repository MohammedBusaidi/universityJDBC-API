import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchData {

	public void searchFromDatabase() {
		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			url += ";databaseName=" + Access.databaseName;
			con = DriverManager.getConnection(url, Access.user, Access.pass);
			Statement st = (Statement) con.createStatement();

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter search criteria (name, country, or alpha_two_code):");
			String searchBy = scanner.next();
			System.out.println("Enter search query:");
			String searchQuery = scanner.next();

			String sql = "SELECT * FROM universities WHERE " + searchBy + " = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, searchQuery);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String name = rs.getString("Name");
				String country = rs.getString("Country");
				String stateProvince = rs.getString("State_Province");
				String domains = rs.getString("Domains");
				String webPages = rs.getString("Web_Pages");
				String alphaTwoCode = rs.getString("Alpha_Two_Code");
				System.out.println("---------------------------------------------------------");
				System.out.println("Name: " + name);
				System.out.println("Country: " + country);
				System.out.println("State/Province: " + stateProvince);
				System.out.println("Domains: " + domains);
				System.out.println("Web Pages: " + webPages);
				System.out.println("Alpha Two Code: " + alphaTwoCode);
				System.out.println(".........................................................");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
