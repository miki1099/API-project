package util;

import model.Criminal;
import model.CriminalHolder;

import java.util.*;
import java.util.stream.Collectors;

public class ActionsImpl {

    private CriminalHolder criminalHolder;

    private void createCriminalHolder() {
        FBIStringReader fbiStringReader = new FBIStringReader();
        criminalHolder = fbiStringReader.getCriminalDetails();
    }



    public void getRandomCriminal() {
        createCriminalHolder();
        Random randomNumber = new Random();
        int rand = randomNumber.nextInt(criminalHolder.getItems().length);
        System.out.println(criminalHolder.getItems()[rand]);
    }

    public void getYoungestCriminal() {
        createCriminalHolder();
        List<Criminal> bufCriminalList = Arrays.asList(criminalHolder.getItems());
        bufCriminalList = bufCriminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None")).collect(Collectors.toList());
        bufCriminalList.sort(Comparator.comparing(Criminal::getAge_min).reversed());
        System.out.println(bufCriminalList.get(0));
    }
    public void getOldestCriminal() {
        createCriminalHolder();
        List<Criminal> criminalList = Arrays.asList(criminalHolder.getItems());
        criminalList = criminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None"))
                .collect(Collectors.toList());
        criminalList.sort(Comparator.comparing(Criminal::getAge_min));
        System.out.println(criminalList.get(0));
    }


}
