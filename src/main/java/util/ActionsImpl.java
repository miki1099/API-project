package util;

import dao.CriminalDao;
import model.Criminal;
import model.CriminalHolder;

import java.util.*;
import java.util.stream.Collectors;

public class ActionsImpl {
        CriminalDao criminalDao = new CriminalDao();

    public void getRandomCriminal() {

        Criminal criminalRandom = criminalDao.getRandomCriminal();
        System.out.println(criminalRandom);
    }

    public void getYoungestCriminal() {
        Criminal criminalRandom = criminalDao.getYoungestCriminal();
        System.out.println(criminalRandom);
    }

    public void getOldestCriminal() {
        Criminal criminalRandom = criminalDao.getOldestCriminal();
        System.out.println(criminalRandom);
    }

    public void updateDataBase() {
        FBIStringReader fbiStringReader = new FBIStringReader();
        fbiStringReader.getCriminalDetails();
    }
}
