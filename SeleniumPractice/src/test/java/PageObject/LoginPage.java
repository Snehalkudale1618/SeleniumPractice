package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	// find webelements of login page
	@FindBy(xpath="//input[@name=\"username\"]")private WebElement username;
	@FindBy(xpath="//input[@name='password']")private WebElement password;
	@FindBy(xpath="//button[@class=\"oxd-button oxd-button--medium oxd-button--main orangehrm-login-button\"]")private WebElement loginBtn;
	@FindBy(xpath="//p[@class=\"oxd-text oxd-text--p oxd-alert-content-text\"]")private WebElement InvalidMessage;
	
	
	WebDriver d;
	// pagefactroy constructor
	public LoginPage(WebDriver driver) {
		
		d=driver;
		PageFactory.initElements(d, this);
	}
	
	//Methods for operations
	
	public void setUserNames(String name) {
		username.sendKeys(name);
	}
	
	public void setPassword (String pass) {
		password.sendKeys(pass);
	}
	
	public void clickLoginbtn() {
		loginBtn.submit();
	}
	
	public String getInvalidText() {
		return(InvalidMessage.getText());
	}
}
