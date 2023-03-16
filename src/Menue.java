import java.util.*;

public class Menue {
	Scanner menueSc = new Scanner(System.in);
	
	public void showMenue() {
		boolean menueLoop = true;
		while (menueLoop) {
			HashMap<Integer, String> menuOptions = new HashMap<Integer, String>();
			 menuOptions.put(1, "Enter the name of the country to view all the universites: ");
			 menuOptions.put(2, "Enter the name of the database: ");
			 menuOptions.put(3, "Need suggestions? choose me!");
			 menuOptions.put(4, "Manage Shop Settings");
			 menuOptions.put(5, "Backup Database");
			 menuOptions.put(6, "Remove Table");
			 menuOptions.put(7, "Show all universites");
			 menuOptions.put(8, "fetch Data");
			 menuOptions.put(9, "Search by: ");
			 menuOptions.put(10, "Dump data into file");
			 menuOptions.put(11, "Exit");
			 
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
		            	break;
		            }
		}
	}
		
	}

}
