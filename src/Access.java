import java.util.Scanner;

public class Access {
	static String user = "sa";
	static String pass = "root";
	static String databaseName = "uniDatabase";

	Menue menue = new Menue();

	public void giveUserAccess() {
//		String expectedUser = "sa";
//		String expectedPass = "root";
//		String expectedDatabase = "uniDatabase";

		Scanner accessSc = new Scanner(System.in);
		System.out.println("==================LOGIN TO THE DATABASE==================");
		boolean accessGranted = false;
		while (!accessGranted) {
			System.out.print("Enter your Database name: ");
			String databaseInput = accessSc.next();
			System.out.print("Enter your user name: ");
			String userInput = accessSc.next();
			System.out.print("Enter your password: ");
			String passInput = accessSc.next();
			System.out.println("=========================================================");

			// Check if the entered credentials match the expected values
			if (databaseInput.equals(databaseName) && userInput.equals(user)
					&& passInput.equals(pass)) {
//				Access.databaseName = databaseInput;
//				Access.user = userInput;
//				Access.pass = passInput;
				System.out.println("Access granted");
				menue.showMenue();
			} else {
				System.out.println("Access denied. Incorrect username or password.");
			}
		}
	}

}
