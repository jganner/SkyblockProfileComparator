import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MinecraftID {

    public String getMinecraftID(String userName) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + userName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");  // Set method to GET

            //GetRequest
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.toString());;
            return (String) jsonObject.get("id");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}