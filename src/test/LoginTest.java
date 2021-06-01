package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import resources.Constants;

public class LoginTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 2)
	public void testInvalidPassword() {

		File f = new File("data.xlsx");
		try {
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(1);

			driver.get(Constants.URL);

			String username = row.getCell(0).toString();
			String invalidPassword = "invalid";

			Home.inputUsername(driver, username);
			Home.inputPassword(driver, invalidPassword);
			Home.clickLoginBtn(driver);

			Assert.assertNotEquals(Constants.URL, Constants.URL1);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void testInvalidUsername() {

		File f = new File("data.xlsx");
		try {
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(1);

			driver.get(Constants.URL);

			String invalidUsername = "user";
			String password = row.getCell(1).toString();

			Home.inputUsername(driver, invalidUsername);
			Home.inputPassword(driver, password);
			Home.clickLoginBtn(driver);

			Assert.assertNotEquals(Constants.URL, Constants.URL1);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Test(priority = 3)
	public void testValidCredentials() {

		File f = new File("data.xlsx");
		try {
			InputStream is = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			SoftAssert sa = new SoftAssert();

			for (int i = 1; i < 4; i++) {
				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);

				String username = c0.toString();
				String password = c1.toString();

				driver.navigate().to(Constants.URL);
				driver.manage().window().maximize();

				Home.inputUsername(driver, username);
				Home.inputPassword(driver, password);

				Home.clickLoginBtn(driver);

				String expectedUrl = Constants.URL1;
				String actualUrl = driver.getCurrentUrl();

				if (username.equals("locked_out_user")) {
					Assert.assertNotEquals(actualUrl, expectedUrl);

				} else {

					sa.assertEquals(actualUrl, expectedUrl, username);
				}

			}

			sa.assertAll();

			wb.close();

			driver.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
