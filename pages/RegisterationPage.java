package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterationPage extends BasePage{
	
	//Class objects
	
	
	//Constructor
	public RegisterationPage (WebDriver driver) {
		super(driver);
	}
	
	
	public boolean fillRegistrationForm(String sName, String sEmail, String sPassword) {
		
		//Fill fields:
		fillText(By.name("fullname"),sName);
		fillText(By.name("email"),sEmail);
		fillText(By.name("password"),sPassword);
		fillText(By.name("password1"),sPassword);
		
		click(By.xpath("//*[@id=\"createform\"]/div[2]/button"));
		
		return true;
	}
	
	
}
