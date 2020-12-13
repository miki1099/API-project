package util;

import dao.CriminalDao;
import model.Criminal;
import model.CriminalHolder;

import java.util.*;
import java.util.stream.Collectors;

public class ActionsImpl {

    private CriminalHolder criminalHolder;

    private static int compare(Criminal c1, Criminal c2) {
        return Integer.parseInt(c1.getAge_min()) - Integer.parseInt(c2.getAge_min());
    }

    private void createCriminalHolder() {
        FBIStringReader fbiStringReader = new FBIStringReader();
        criminalHolder = fbiStringReader.getCriminalDetails();
        CriminalDao criminalDao = new CriminalDao();
        for (int i = 0; i < criminalHolder.getItems().length; i++){
            if(!criminalDao.isCriminalInDataBase(criminalHolder.getItems()[i])){
                criminalDao.insert(criminalHolder.getItems()[i]);
            }

        }
    }

    public void getRandomCriminal() {
        if(criminalHolder == null) createCriminalHolder();
        Random randomNumber = new Random();
        int rand = randomNumber.nextInt(criminalHolder.getItems().length);
        System.out.println(criminalHolder.getItems()[rand]);
    }

    public void getYoungestCriminal() {
        if(criminalHolder == null) createCriminalHolder();
        List<Criminal> bufCriminalList = Arrays.asList(criminalHolder.getItems());
        bufCriminalList = bufCriminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None")).collect(Collectors.toList());
        bufCriminalList.sort(ActionsImpl::compare);
        System.out.println(bufCriminalList.get(0));
    }

    public void getOldestCriminal() {
        if(criminalHolder == null) createCriminalHolder();
        List<Criminal> criminalList = Arrays.asList(criminalHolder.getItems());
        criminalList = criminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None"))
                .collect(Collectors.toList());
        criminalList.sort(ActionsImpl::compare);
        int listLenght = criminalList.size() - 1;
        System.out.println(criminalList.get(listLenght));
    }
}
