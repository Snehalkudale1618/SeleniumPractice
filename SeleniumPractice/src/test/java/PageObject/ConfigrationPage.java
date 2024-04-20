package PageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfigrationPage {
	
	@FindBy(xpath="//div[@class=\"oxd-file-button\"]")private WebElement brouse;
	@FindBy(xpath="//button[@type=\"submit\"]")private WebElement upload;
		
	WebDriver d;
	public ConfigrationPage(WebDriver driver) {
		
		d=driver;
	PageFactory.initElements(d,this );
		
	}


//Methods
	public void clickOnBrouseBtn() throws IOException {
		brouse.click();
		Runtime.getRuntime().exec("/C://Users//Administrator//Desktop//Autoitfiles//UploadFileAutoitScript.exe//");//you have to change direction of \\ slash to // from path
}
	public void clickOnUpload() {
		upload.submit();
	}

}