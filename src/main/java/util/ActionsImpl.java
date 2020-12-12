package util;

import model.Criminal;
import model.CriminalHolder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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

}
