package util;

import model.Criminal;
import model.CriminalHolder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void getOldestCriminal() {
        createCriminalHoler();
        List<Criminal> criminalList = Arrays.asList(criminalHolder.getItems());
        criminalList = criminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None"))
                .collect(Collectors.toList());
        criminalList.sort(Comparator.comparing(Criminal::getAge_min));
        System.out.println(criminalList.get(0));
    }


}
