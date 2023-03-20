import java.util.Scanner;

public class Access {
	static String user;
	static String pass;
	static String databaseName = "uniDatabase";

	Menue menue = new Menue();

	public Access giveUserAccess(Access giveUserAccess) {
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

}
