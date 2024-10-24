import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MinecraftID {

    public String getHttpsRequest(String userName) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + userName);
            connection = (HttpURLConnection) url.openConnection();
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
            String playerData = response.toString();
            playerData = playerData.replace("\r", "");
            return playerData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public String getMinecraftID(String userName) {
        String HttpsRequest = getHttpsRequest(userName);
        StringBuilder iD = new StringBuilder(HttpsRequest);
        iD.delete(0, 11);
        iD.delete(32, 61);
        return iD.toString();

    }
}