package util;

import com.google.gson.Gson;
import http.HttpClient;
import model.Criminal;
import model.CriminalHolder;

public class FBIStringReader {

    public CriminalHolder getCriminalDetails() {
        Gson gson = new Gson();

        CriminalHolder buffer = new CriminalHolder();

        HttpClient httpClient = new HttpClient();
        String data = "";
        data = httpClient.fetch("https://api.fbi.gov/wanted/v1/list");
        data = dataHtmlReplacer(data);
        CriminalHolder criminalHolder = gson.fromJson(data, CriminalHolder.class);
        int n = 2;
        int criminalsCounter = 0;
        while (criminalsCounter <= Integer.parseInt(criminalHolder.getTotal())) {
            if (gson.fromJson(data, CriminalHolder.class).getItems() != null) {
                data = httpClient.fetch("https://api.fbi.gov/wanted/v1/list?page=" + n);
                data = dataHtmlReplacer(data);
                buffer = gson.fromJson(data, CriminalHolder.class);
                criminalHolder.connectCriminalLists(buffer);
                criminalsCounter += 20;
                n++;
            } else {
                break;
            }
        }

        return criminalHolder;
    }

    private String dataHtmlReplacer(String data) {

        data = data.replace("<p>", "");
        data = data.replace("</p>", "");

        data = data.replace("<html><body>", "");
        data = data.replace("</body></html>", "");

        return data;
    }



}
