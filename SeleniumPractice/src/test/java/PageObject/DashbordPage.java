package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashbordPage {
	//click on PIM menu
	@FindBy(xpath = "(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[2]") WebElement pim;
	//@findby(text="PIM")WebElement pim;
	
	
	@FindBy(xpath="//a[text()='Add Employee']")WebElement addemployee;
	@FindBy(xpath="//span[@class=\"oxd-topbar-body-nav-tab-item\"]")private WebElement configration;

	@FindBy(partialLinkText="Data ")WebElement data_import;
	@FindBy(xpath="//li[@class=\"oxd-topbar-body-nav-tab --visited\"]")private WebElement EmpList;
	
	
	WebDriver d;
	// pagefactroy constructor
	public DashbordPage(WebDriver driver) {
		
		d=driver;
		PageFactory.initElements(d, this);
	}
	//get page title
	public String getPageTitle() {
		return d.getTitle();
		
	}
	
	//clickon PIM Menu
	
	public void clickOnPIM() {
		pim.click();
		
	}
	
	
	public void clickOnAddEmployee() {
		addemployee.click();
	}
	
	public void clickOnConfigrationMenu() {
		configration.click();
	}
	
	public void clickOnDataImport() {
		data_import.click();
	}
	
	public void clickOnEmployee_List() {
		EmpList.click();
	}

}
