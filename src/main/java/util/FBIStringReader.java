package util;

import com.google.gson.Gson;
import dao.CriminalDao;
import http.HttpClient;
import model.Criminal;
import model.CriminalHolder;

public class FBIStringReader {

    public void getCriminalDetails() {
        Gson gson = new Gson();

//        CriminalHolder buffer = new CriminalHolder();

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
                criminalHolder = gson.fromJson(data, CriminalHolder.class);
                insertCriminalsToDb(criminalHolder);
//                criminalHolder.connectCriminalLists(criminalHolder);
                criminalsCounter += 20;
                n++;
            } else {
                break;
            }
        }
    }

    private void insertCriminalsToDb(CriminalHolder criminalHolder){
        CriminalDao criminalDao = new CriminalDao();
        for(Criminal criminal : criminalHolder.getItems()){
            if(!criminalDao.isCriminalInDataBase(criminal)){
                criminalDao.insert(criminal);
            }
        }
    }

    private String dataHtmlReplacer(String data) {
        data = data.replace("'age_min': None", "'age_min': -1");

        data = data.replace("<p>", "");
        data = data.replace("</p>", "");

        data = data.replace("<html><body>", "");
        data = data.replace("</body></html>", "");

        return data;
    }



}
