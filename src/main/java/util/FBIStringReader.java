package util;

import com.google.gson.Gson;
import http.HttpClient;
import model.Criminal;
import model.CriminalHolder;

public class FBIStringReader {

    public CriminalHolder getCriminalDetails() {

        HttpClient httpClient = new HttpClient();

        String data = httpClient.fetch("https://api.fbi.gov/wanted/v1/list");

        data = data.replace("<p>", "");
        data = data.replace("</p>", "");

        data = data.replace("<html><body>", "");
        data = data.replace("</body></html>", "");

        Gson gson = new Gson();

        CriminalHolder criminalHolder = gson.fromJson(data, CriminalHolder.class);



        return criminalHolder;
    }


}
