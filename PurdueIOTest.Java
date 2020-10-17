import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.ProtocolException;
import java.net.HttpURLConnection;

public class PurdueIOTest {

    public static void main(String[] args) throws IOException {

        final String URL = "http://api.purdue.io/odata/";
        final String ENDPOINT = "Courses";
        final String PARAMS = "?$filter=contains(Title,%20%27Algebra%27)";
        
        // Create a URL object of our desired query
        System.out.println("Creating URL: " + URL + ENDPOINT + PARAMS);
        URL urlToGet = new URL(URL + ENDPOINT + PARAMS); 

        // Open an HTML connection with our URL, cast it as a HttpURLConnection.
        System.out.println("Creating Http connection...");
        HttpURLConnection connection = (HttpURLConnection) urlToGet.openConnection(); 

        // Specifying that we want a GET request.
        connection.setRequestMethod("GET");

        // Getting a response code from the connection.
        System.out.println("Getting connection response code...");
        int responseCode = connection.getResponseCode();

        // Checks if the connection is valid.
        System.out.println("Checking connection...");
        String readLine = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Connection made, reading response...");
            // Creates an input stream to read the API response.
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            } in.close();

            // We may want to be able to convert the StringBuffer response into a Hashmap for data accessibility.

            // Creates a response file and writes the API response within.
            CreateFile("response.txt");
            WriteToFile("response.txt", response.toString());
        } else {
            System.out.println("Connection request failed. Make sure the URL is valid.");
        }
    }
    
    public static void CreateFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("Created file: " + file.getName());
            } else {
                System.out.println("Error: File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    public static void WriteToFile(String fileName, String in) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(in);
            myWriter.close();
            System.out.println("Successfully wrote to file: " + fileName);
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}