package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	// public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
	// + "\\src\\main\\java\\com\\crm\\qa\\testData\\FreeCRMTestData.xlsx";
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "crm" + File.separator + "qa"
			+ File.separator + "testData" + File.separator + "FreeCRMTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream fis = null;
		// URI outputURI = new URI((TESTDATA_SHEET_PATH.replaceAll(" ", "%20")));
		// File outputFile = new File(outputURI);

		try {
			fis = new FileInputStream(new File(TESTDATA_SHEET_PATH));
			System.out.println("Sheet path " + TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		

		try {
			book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;

	}
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File srcFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png"));
	}

}
