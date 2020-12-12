package util;

import model.Criminal;
import model.CriminalHolder;

import java.util.List;
import java.util.Random;

public class ActionsImpl {

    private CriminalHolder criminalHolder;

    private void createCriminalHoler() {
        FBIStringReader fbiStringReader = new FBIStringReader();
        criminalHolder = fbiStringReader.getCriminalDetails();
    }



    public void getRandomCriminal() {
        createCriminalHoler();
        Random randomNumber = new Random();
        int rand = randomNumber.nextInt(criminalHolder.getItems().length);
        System.out.println(criminalHolder.getItems()[rand]);
    }

    public void getYoungestCriminal() {
        createCriminalHoler();



    }

}
