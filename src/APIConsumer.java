import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.Gson;


public class APIConsumer {
    
    public void serachUniversity() {
        Scanner apiSC = new Scanner(System.in);
        System.out.print("Enter the name of the country: ");
        String countryInput = apiSC.nextLine();
        
        String apiUrl = "http://universities.hipolabs.com/search?country=" + countryInput;
        //bring data from API and displays it as an object
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder json = new StringBuilder();
            
            while ((output = br.readLine()) != null) {
                json.append(output);
            }
            
            conn.disconnect();
            // switch from Json to object.
            //we use Gson to read Json.
            Gson gson = new Gson();
            University[] uni = gson.fromJson(json.toString(), University[].class);
            System.out.println("=============================================================================");
            for (int i = 0; i < uni.length; i++) {
            	University myUni = uni[i];
            	System.out.println((i + 1) + ":\t" + myUni.state_province + " - " + myUni.country + " - "
						+ myUni.name + " - " + myUni.alpha_two_code);
            	for (int j = 0; j < myUni.domains.length; j++) {
					System.out.println("\tDomain " + (j + 1) + ": " + myUni.domains[j]);
				}

				for (int m = 0; km < myUni.web_pages.length; m++) {
					System.out.println("\tWeb page " + (m) + ": " + myUni.web_pages[k]);

				}
				System.out.println("=============================================================================");
            }
     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

