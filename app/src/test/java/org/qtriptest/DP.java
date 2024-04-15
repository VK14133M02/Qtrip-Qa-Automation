package org.qtriptest;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DP {
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod (Method m) throws IOException{
        int rowIndex=0;
        int cellIndex=0;
        List<List> outputList=new ArrayList<List>();

        FileInputStream excelFile = new FileInputStream(new File("src/test/resources/DatasetsforQTrip.xlsx"));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet selectedSheet = workbook.getSheet(m.getName());
        Iterator<Row> iterator = selectedSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             List<String> innerList=new ArrayList<String>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();                
                if(rowIndex>0 && cellIndex>0)
                {
                    if(cell.getCellType()==CellType.STRING) {
                        innerList.add(cell.getStringCellValue());
                    } else if (cell.getCellType()==CellType.NUMERIC) {
                        innerList.add( String.valueOf(cell.getNumericCellValue()));
                    }
                }
                cellIndex = cellIndex+1;
            }
            rowIndex=rowIndex+1;
            cellIndex=0;
            if(innerList.size()>0)
                outputList.add(innerList);

        }
         
        excelFile.close();

        String[][] stringArray = outputList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return stringArray;
        
    }

}



/*
package qtriptest;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod (Method m) throws IOException{
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        String resourceFolderPath = currentDir+"/src/test/resources/";
        String fileName = resourceFolderPath+"DatasetsforQTrip.xlsx";

        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workBook = new XSSFWorkbook(file);
        System.out.println(workBook.getSheetName(2));

        XSSFSheet sheet;

        switch(m.getName()){
            case "TestCase01":
            sheet = workBook.getSheet("TestCase01");
            return new Object [][]{
                {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString()},
                {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString()},
                {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString()}
            };
            case "TestCase02":
            sheet = workBook.getSheet("TestCase02");
            return new Object [][]{
                {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString(),sheet.getRow(1).getCell(4).toString(),sheet.getRow(1).getCell(5).toString()},
                {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(2).getCell(5).toString()},
                {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(3).getCell(5).toString()}
            };    
            case "TestCase03":
            sheet = workBook.getSheet("TestCase03");
            return new Object [][]{
                {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString(),sheet.getRow(1).getCell(4).toString(),sheet.getRow(1).getCell(5).toString(),sheet.getRow(1).getCell(6).toString(),sheet.getRow(1).getCell(7).toString()},
                {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(2).getCell(5).toString(),sheet.getRow(2).getCell(6).toString(),sheet.getRow(2).getCell(7).toString()},
                {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString(),sheet.getRow(3).getCell(4).toString(),sheet.getRow(3).getCell(5).toString(),sheet.getRow(3).getCell(6).toString(),sheet.getRow(3).getCell(7).toString()}
            };   
            case "TestCase04":
            sheet = workBook.getSheet("TestCase04");
            return new Object [][]{
                {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString(),sheet.getRow(1).getCell(4).toString(),sheet.getRow(1).getCell(5).toString()},
                {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString(),sheet.getRow(2).getCell(4).toString(),sheet.getRow(2).getCell(5).toString()},
                {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString(),sheet.getRow(3).getCell(4).toString(),sheet.getRow(3).getCell(5).toString()}
            };         
        }
        return null;
    }
}
*/