package test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import objects.InventoryPage;
import resources.Constants;

public class SorterTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testSortingOption() {
		driver.navigate().to(Constants.URL);
		Home.inputUsername(driver, "standard_user");
		Home.inputPassword(driver, "secret_sauce");
		Home.clickLoginBtn(driver);
		
		InventoryPage.sortPriceLowHi(driver);
		
		driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).click();
		
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory-item.html?id=2";
		
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();
		
		String currentUrl1=driver.getCurrentUrl();
		String expectedUrl1="https://www.saucedemo.com/inventory-item.html?id=5";
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(currentUrl, expectedUrl);
		sa.assertEquals(currentUrl1, expectedUrl1);
		
		//nisam znala kako da proverim da li su elementi sortirani, samo sam utvrdila da su prvi i poslednji na listi ocekivani :D
		
		sa.assertAll();
		
		
		

		}
	}

