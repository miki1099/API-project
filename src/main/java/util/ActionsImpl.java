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

    private void fillCriminalHolderFromDatabase(){
        if(criminalHolder == null){
            CriminalDao criminalDao = new CriminalDao();
            criminalHolder = new CriminalHolder();
            List<Criminal> bufList = criminalDao.getAll();
            Criminal[] bufTable = new Criminal[bufList.size()];
            bufList.toArray(bufTable);
            criminalHolder.setItems(bufTable);
        }
    }

    public void getRandomCriminal() {
        fillCriminalHolderFromDatabase();
        Random randomNumber = new Random();
        int rand = randomNumber.nextInt(criminalHolder.getItems().length);
        System.out.println(criminalHolder.getItems()[rand]);
    }

    public void getYoungestCriminal() {
        fillCriminalHolderFromDatabase();
        List<Criminal> bufCriminalList = Arrays.asList(criminalHolder.getItems());
        bufCriminalList = bufCriminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None")).collect(Collectors.toList());
        bufCriminalList.sort(ActionsImpl::compare);
        System.out.println(bufCriminalList.get(0));
    }

    public void getOldestCriminal() {
        fillCriminalHolderFromDatabase();
        List<Criminal> criminalList = Arrays.asList(criminalHolder.getItems());
        criminalList = criminalList.stream().filter(criminal -> !criminal.getAge_min().equals("None"))
                .collect(Collectors.toList());
        criminalList.sort(ActionsImpl::compare);
        int listLenght = criminalList.size() - 1;
        System.out.println(criminalList.get(listLenght));
    }

    public void updateDataBase() {
        createCriminalHolder();
    }
}
