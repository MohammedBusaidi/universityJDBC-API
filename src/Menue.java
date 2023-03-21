import java.util.*;

public class Menue {
	Scanner menueSc = new Scanner(System.in);
	static APIConsumer newAPI = new APIConsumer();
	static JDBC jdbc = new JDBC();

	
	public void showMenue() {
		boolean menueLoop = true;
		while (menueLoop) {
			HashMap<Integer, String> menuOptions = new HashMap<Integer, String>();
			 menuOptions.put(1, "Search the name of the country to fetch universites");
			 menuOptions.put(2, "Need suggestions of Countries? choose me!");
			 menuOptions.put(3, "Manage Shop Settings");
			 menuOptions.put(4, "Backup Database");
			 menuOptions.put(5, "Remove Table");
			 menuOptions.put(6, "Show all universites");
			 menuOptions.put(7, "fetch Data");
			 menuOptions.put(8, "Search by: ");
			 menuOptions.put(9, "Dump data into file");
			 menuOptions.put(10, "Exit");
			 
			 int choice = 0;
		        
		        while (choice != 11) {
		            System.out.println("==================UNIVERSITIES DATABASE==================");
		            for (int i = 1; i <= 11; i++) {
		                System.out.println(i + ". " + menuOptions.get(i));
		            }
		            System.out.println("=========================================================");
		            System.out.print("Enter your choice: ");
		            choice = menueSc.nextInt();
		            
		            switch (choice) {
		            case 1:
		            	newAPI.searchByCountry();
		            	break;
		            case 2:
		            	newAPI.showListOfCountries();
		            	break;
		            case 3: 
		            	System.out.println("hi");
		            	break;
		            }
		}
	}
		
	}

}
