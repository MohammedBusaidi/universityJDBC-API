import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class APIConsumer {
    
    public static void main(String[] args) {
        Scanner apiSC = new Scanner(System.in);
        System.out.print("Enter the name of the country: ");
        String countryInput = apiSC.nextLine();
        
        String apiUrl = "http://universities.hipolabs.com/search?country=" + countryInput;
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

