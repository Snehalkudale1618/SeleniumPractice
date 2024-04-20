package PageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class EmployeeList {
	
	@FindBy(xpath="(//input[@placeholder=\"Type for hints...\"])[1]")private WebElement empname;
	@FindBy(xpath="(//input[@class=\"oxd-input oxd-input--active\"])[2]")private WebElement empId;
	@FindBy(xpath="//button[@type=\"submit\"]")private WebElement searchBtn;
	@FindBy(xpath="(//i[@class=\"oxd-icon bi-trash\"])[1]")private WebElement delete;
	@FindBy(xpath="//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]")
	private WebElement warning;
	@FindBy(xpath= "//span[@class=\"oxd-text oxd-text--span\"][1]")private WebElement act_message;
	
	
	WebDriver d;
	public  EmployeeList(WebDriver driver) {
		d=driver;
		PageFactory.initElements(d, this);
		
	}

public void SetempName(String ename) {
	empname.sendKeys(ename);
}

public void setEmpId(String eid) {
	empId.sendKeys(eid);
}
public void clickOnSearchBtn() {
	searchBtn.click();
	
//	scroll down by using java script executer
			JavascriptExecutor executor=(JavascriptExecutor)d;
			executor.executeScript("window.scrollBy(0,"+ 700 +")");
}
public void clickOnDeleteIcon() {
	delete.click();
}
public void areYouSureToDeleteEmployee() {
	warning.click();
}
public String act_message() {
	return act_message.getText();
	
}
}