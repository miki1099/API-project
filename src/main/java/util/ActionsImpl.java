package util;

import dao.CriminalDao;
import model.Criminal;
import model.CriminalHolder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    public void getCriminalsByHairColor() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Hair color (brown/black/blond/gray): ");
        List criminalByHairColor = criminalDao.getCriminalByHairColor(scan.nextLine());

        if (!criminalByHairColor.isEmpty()) {
            System.out.println(criminalByHairColor);
        } else {
            System.out.println("There aren't any criminals with this hair color!");
        }
        System.out.println("---------------------");
    }


    public void exportAllToExcel() {
        List<Criminal> criminalList = criminalDao.getAll();
        exportToExcel(criminalList);
    }

    private void exportToExcel(List<Criminal> criminalList){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        String [] columnsNames = "ID;TITLE;AGE;SEX;UID;NATIONALITY;HAIR;EYES;RACE".split(";");

        Row topRow = sheet.createRow(0);
        for(int i = 0; i < columnsNames.length; i++){
            Cell cell = topRow.createCell(i);
            cell.setCellValue(columnsNames[i]);
        }

        int rowCounter = 1;
        for(Criminal criminal : criminalList){
            Row criminalDataRow = sheet.createRow(rowCounter);
            criminalDataRow.createCell(0).setCellValue(criminal.getId());
            criminalDataRow.createCell(1).setCellValue(criminal.getTitle());
            if(criminal.getAge_min() != -1){
                criminalDataRow.createCell(2).setCellValue(criminal.getAge_min());
            }else{
                criminalDataRow.createCell(2).setCellValue("Unknown");
            }

            criminalDataRow.createCell(3).setCellValue(criminal.getSex());
            criminalDataRow.createCell(4).setCellValue(criminal.getUid());
            criminalDataRow.createCell(5).setCellValue(criminal.getNationality());
            criminalDataRow.createCell(6).setCellValue(criminal.getHair_raw());
            criminalDataRow.createCell(7).setCellValue(criminal.getEyes());
            criminalDataRow.createCell(8).setCellValue(criminal.getRace());
            rowCounter++;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("Criminals.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
