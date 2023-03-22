import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchData {
	Scanner searchSc = new Scanner(System.in);

	public void searchAndInsertIntoDatabase() {

		String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);

			url += ";databaseName=" + Access.databaseName;
			con = DriverManager.getConnection(url, Access.user, Access.pass);
			Statement st = (Statement) con.createStatement();

			System.out.println("Search by Name, Country and Code.");
			String searchType = searchSc.next();
			System.out.println("Enter what you want to search by: ");
			String searchInput = searchSc.next();

			String sql = "";
			switch (searchType) {
			case "name":
				sql = "SELECT DISTINCT Name, Country, State_Province, Domains, Web_Pages, Alpha_Two_Code FROM universities WHERE Name LIKE '"
						+ searchInput + "%';";
			case "country":
				sql = "SELECT DISTINCT Name, Country, State_Province, Domains, Web_Pages, Alpha_Two_Code FROM universities WHERE Country LIKE '"
						+ searchInput + "%';";
				break;
			case "code":
				sql = "SELECT DISTINCT Name, Country, State_Province, Domains, Web_Pages, Alpha_Two_Code FROM universities WHERE Alpha_Two_Code LIKE '"
						+ searchInput + "';";
				break;
			default:
				System.err.println("Invalid search type.");
				break;
			}

			ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString("Name");
				String country = rs.getString("Country");
				String state_province = rs.getString("State_Province");
				String domains = rs.getString("Domains");
				String web_pages = rs.getString("Web_Pages");
				String alpha_two_code = rs.getString("Alpha_Two_Code");
				System.out.println("---------------------------------------------------------");
				System.out.println("Name: " + name);
				System.out.println("Country: " + country);
				System.out.println("State_Province: " + state_province);
				System.out.println("Domains: " + domains);
				System.out.println("Web_Pages: " + web_pages);
				System.out.println("Alpha_Two_Code: " + alpha_two_code);
				System.out.println(".........................................................");

				// Insert the result into the database
//				String insertSql = "INSERT INTO searched_universities (Name, Country, State_Province, Domains, Web_Pages, Alpha_Two_Code) VALUES (?, ?, ?, ?, ?, ?)";
//				PreparedStatement preparedStatement = con.prepareStatement(insertSql);
//				preparedStatement.setString(1, name);
//				preparedStatement.setString(2, country);
//				preparedStatement.setString(3, state_province);
//				preparedStatement.setString(4, domains);
//				preparedStatement.setString(5, web_pages);
//				preparedStatement.setString(6, alpha_two_code);
//				preparedStatement.executeUpdate();
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
