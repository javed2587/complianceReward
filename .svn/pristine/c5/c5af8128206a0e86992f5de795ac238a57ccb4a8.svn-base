
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abid
 */
public class TestIVR {

    public static void main(String[] ArSt) {
        String responseString = "";
        try {
            String strUrl = "https://secure.ifbyphone.com/click_to_xyz.php?app=cts&phone_to_call=_phone_to_call_&survo_id=_survoId_&key=55b16e47273b81318fa2efc367399434ba0e79c1&first_callerid=8555528832";
            strUrl = strUrl.replaceAll("_phone_to_call_", "8554797283");
            strUrl = strUrl.replaceAll("_survoId_", "649674");
            URL url = new URL(strUrl);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = rd.readLine()) != null) {
                responseString = responseString.concat(line);
            }
            rd.close();

        } catch (Exception e) {
            responseString = "";
            e.printStackTrace();
        }

    }
}
