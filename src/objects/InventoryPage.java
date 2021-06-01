package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import resources.Constants;

public class InventoryPage {
	
	public static void sortPriceLowHi(WebDriver driver) {
		
	Select s = new Select (driver.findElement(By.xpath(Constants.SORTER_XPATH)));
	s.selectByIndex(2);
	}
	
	

}
