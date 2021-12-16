package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait waitDriver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		waitDriver = new WebDriverWait(driver, 5);
	}

	public void fillText(By by, String str) {
		// wait until w.e is clickable
		waitDriver.until(ExpectedConditions.elementToBeClickable(by));
		WebElement we = driver.findElement(by);
		we.clear();
		we.sendKeys(str);
	}

	public void fillTextJS(By by, String str) {
		// wait until w.e is clickable
		waitDriver.until(ExpectedConditions.elementToBeClickable(by));
		WebElement we = driver.findElement(by);
		we.clear();
		// Javascript
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + str + "'", we);

	}

	public void click(By by) {
		// wait until w.e is clickable
		waitDriver.until(ExpectedConditions.elementToBeClickable(by));
		WebElement we = driver.findElement(by);
		we.click();

	}

	public void clickJS(By by) {
		// wait until w.e is clickable
		waitDriver.until(ExpectedConditions.elementToBeClickable(by));
		WebElement we = driver.findElement(by);
		// Javascript
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", we);

	}

	public boolean isWebElementExist(By by, WebDriver driver) {

		List<WebElement> list = driver.findElements(by);
		if (list.size() > 0)
			return true;
		else
			return false;
	}

}
