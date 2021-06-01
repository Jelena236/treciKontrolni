package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.Constants;

public class Home {
	
	public static void inputUsername(WebDriver driver, String username) {
		driver.findElement(By.id(Constants.USERNAME_ID)).sendKeys(username);
	}
	
	public static void inputPassword(WebDriver driver, String password) {
		driver.findElement(By.id(Constants.PASSWORD_ID)).sendKeys(password);
	}
	
	public static void clickLoginBtn(WebDriver driver) {
		driver.findElement(By.id(Constants.LOGIN_BTN_ID)).click();
	}

}
