package PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage {
	
	@FindBy(xpath= "//input[@name='firstName']")private WebElement firstname;
	@FindBy(xpath= "//input[@name='middleName']")private WebElement middlename;
	@FindBy(xpath= "//input[@name='lastName']")private WebElement lastname;
	
	@FindBy(xpath= "(//input[@class=\"oxd-input oxd-input--active\"])[2]")private WebElement empid;
	
	@FindBy(xpath="//i[@class=\"oxd-icon bi-plus\"]")private WebElement empImage;
	
	@FindBy(xpath="//button[@type='submit']")private WebElement savebtn;
	
	@FindBy(xpath="//h6[text()='Personal Details']")private WebElement confermationMsg;
	
	WebDriver d;
	public AddEmployeePage(WebDriver driver){
		d=driver;
		PageFactory.initElements(d, this);	
	
	}	
	
	
	//methods 
	public void setFirstName(String fname) {
		firstname.sendKeys(fname);
	}
	
	public void setMiddleName(String mname) {
		middlename.sendKeys(mname);
	}
	
	public void setLastName(String lname) {
		lastname.sendKeys(lname);
	}
	
	public void setEmpId(String empId) {
		empid.sendKeys(empId);
	}
	
	public void uploadEmpImage() throws IOException {
		empImage.click();
		//to upload image using autoit tool
		Runtime.getRuntime().exec("/C://Users//Administrator//Desktop//Autoitfiles//PhotoUpload.exe/");
	}
	
	public void clickOnSaveBtn() {
		savebtn.submit();
	}
	
	public String getConfermationMessage() {
		return confermationMsg.getText();
		
	}
	

}
