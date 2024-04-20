package demo;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHrm {
	public String baseurl ="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
public WebDriver driver;
	
    @BeforeTest
	public void setup() {
		System.out.println("Before test executed");
		driver= new ChromeDriver();
		
		//maximize window
		driver.manage().window().maximize();
		
		//open url
		driver.get(baseurl);
		
		//timer
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
	}
    
    public void login() {
    	driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("Admin");
    	//find and enter password
    	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
    	//find and click login button
    	//driver.findElement(By.xpath("//button[@type='submit']")).submit();
    	driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--main orangehrm-login-button\"]")).submit();
    	
    }
    
    @Test(priority=1,enabled=false)
	public void loginTestWithInValidCredential() throws InterruptedException

	{	driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("Admin");
	//find and enter password
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin221");
	//find and click login button
	//driver.findElement(By.xpath("//button[@type='submit']")).submit();
	driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--main orangehrm-login-button\"]")).submit();
	
	Thread.sleep(5000);
		
		String message_expected = "Invalid Credential";
		String message_actual = driver.findElement(By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-alert-content-text\"]")).getText();
		Thread.sleep(5000);
		Assert.assertEquals("Invalid Credential",message_expected);
		
	}	 
    
	@Test(priority=2,enabled=false)
	public void loginTestWithValidCredential()

{	//find and enterusername
	driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("Admin");
	//find and enter password
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
	//find and click login button
	//driver.findElement(By.xpath("//button[@type='submit']")).submit();
	driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--main orangehrm-login-button\"]")).submit();
	
	
	
	//verify the login is successful by checking the page title or specific element
	String pagetitle=driver.getTitle();
	
	Assert.assertEquals(pagetitle,"OrangeHRM");
	

	
	/* if(pagetitle.equals("OrangeHRM")) {
		System.out.println("Login Successful!");
	}else {
		System.out.println("Login failed!");
	}*/
}    
	
	/*//to handle alert pop up
	Alert a=driver.switchTo().alert();
	a.accept();*/
	
	
	//Add New Employee
	@Test(priority=3,enabled=false)
	public void addNewEmployee() throws InterruptedException, IOException {
		login();
		Thread.sleep(5000);
		List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
		for(int i=0;i<element.size();i++) {
			System.out.println(i +";"+ element.get(i).getText());
			String rowtext = element.get(1).getText();
			
		}
		element.get(1).click();
			
			/*List<WebElement> rowelement = driver.findElements(By.xpath("//a[@class=oxd-topbar-body-nav-tab-item]"));//click on PIM menu
			for(int j=0;j<rowelement.size();j++) {
			rowelement.get(2).click();
			System.out.println(j+";"+rowelement.get(j).getText());*/
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[text()='Add Employee']")).click(); 
			driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("minal");
			driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys("ram");
			driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("paranjape");
			driver.findElement(By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]"));
			
			//add employee image
			driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-plus\"]")).click();
			Thread.sleep(5000);
			////to upload image using autoit tool
			Runtime.getRuntime().exec("/C://Users//Administrator//Desktop//Autoitfiles//PhotoUpload.exe/");//path of exe file
			Thread.sleep(2000);
			
			//driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(123);	
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@type='submit']")).submit();	
			//verify if employee is added successfully or not by checking employee list personal details
			String confermationmessage =driver.findElement(By.xpath("//h6[text()='Personal Details']")).getText();
			if(confermationmessage.contains("Personal Details")) {
				System.out.println("Employee added succesfully!");
				}
				else {
					System.out.println("Failed to add Employee");
				}
			Thread.sleep(2000);
			logout();
			}
	
	
	@Test(priority=4,enabled=false)	
	public void searchEmployeeByName() throws InterruptedException {
		String messege="Records Found";
		
		List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
		for(int i=0;i<element.size();i++) {
			System.out.println(i +";"+ element.get(i).getText());
			String rowtext = element.get(1).getText();
		}
		element.get(1).click();
		Thread.sleep(2000);
	
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
		Thread.sleep(2000);
		
		//locate empname by using tagname
		driver.findElements(By.tagName("input")).get(1).sendKeys("minal");
		
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		Thread.sleep(2000);
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,"+ 300 +")");
		List<WebElement>elemen=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span\"]"));
		String actmessege=elemen.get(0).getText();//recordsfound
		System.out.println(actmessege);
		if(actmessege.equals(actmessege)) {
			System.out.println("Records found");
		}else {
			System.out.println("Records not found");
		}
		logout();
		}
	
	@Test(priority=5,enabled=false)
	public void searchEmployeeById() throws InterruptedException {
		String empId="888";
		String messegeActual="";
		//loginTestWithValidCredential();
		
		List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
		for(int i=0;i<element.size();i++) {
			System.out.println(i +";"+ element.get(i).getText());
			String rowtext = element.get(1).getText();
			
		}
		element.get(1).click();
		Thread.sleep(2000);
			driver.findElement(By.xpath("//a[text()='Employee List']")).click();
			driver.findElement(By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]")).sendKeys(empId);//empid
			driver.findElement(By.xpath("//button[text()=' Search ']")).click();//searchbox
			Thread.sleep(2000);
			
			//	scroll down by using java script executer
			JavascriptExecutor executor=(JavascriptExecutor)driver;
			executor.executeScript("window.scrollBy(0,"+ 500 +")");
		 List<WebElement>rows=driver.findElements(By.xpath("//div[@role=\"rowgroup\"]"));
		 if(rows.size()>1) {
			 messegeActual= driver.findElement(By.xpath("((//div[@role=\"rowgroup\"])[2]//div[@class='oxd-table-cell oxd-padding-cell'])[2]")).getText();
		
			 Assert.assertEquals(empId,messegeActual);
			 logout();
		}
		 
	}
			
	
	
	@Test(priority=6,enabled=false)
	public void fileUpload() throws InterruptedException, IOException {
		//used autoIt tool and finder
		login();
		//click  pim menu
		List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
		for(int i=0;i<element.size();i++) {
		String rowtext = element.get(1).getText();
		}
		element.get(1).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[@class=\"oxd-topbar-body-nav-tab-item\"]")).click();//click on configration
		//driver.findElement(By.xpath("//a[text()='Data Import']")).click();
		driver.findElement(By.partialLinkText("Data ")).click();
		
		driver.findElement(By.xpath("//div[@class=\"oxd-file-button\"]")).click();//brouse button
		Thread.sleep(5000);
		
		Runtime.getRuntime().exec("/C://Users//Administrator//Desktop//Autoitfiles//UploadFileAutoitScript.exe//");//you have to change direction of \\ slash to // from path
	
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).submit();//click on upload 
		logout();
	}
	
	
	@Test(priority=7,enabled=false)
	public void addEmpImage() throws InterruptedException, IOException {
		login();
		
		//click on pim
		List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
		for(int i=0;i<element.size();i++) {
		String rowtext = element.get(1).getText();
		}
		element.get(1).click();
		Thread.sleep(2000);
		
		//click on add emp
		driver.findElement(By.xpath("(//a[@class=\"oxd-topbar-body-nav-tab-item\"])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-plus\"]")).click();
		Thread.sleep(5000);
		////to upload image using autoit tool
		Runtime.getRuntime().exec("/C://Users//Administrator//Desktop//Autoitfiles//PhotoUpload.exe/");//path of exe file
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("minal");
		driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys("ram");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("paranjape");
		
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).submit();
		
	}
	
	@Test(priority=8,enabled=false)
	public void deleteEmployee() throws InterruptedException {
		login();
		
		//click on pim
				List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
				for(int i=0;i<element.size();i++) {
				String rowtext = element.get(1).getText();
				}
				element.get(1).click();
				Thread.sleep(2000);
		
		driver.findElement(By.xpath("//li[@class=\"oxd-topbar-body-nav-tab --visited\"]")).click();//emp list
		driver.findElement(By.xpath("(//input[@placeholder=\"Type for hints...\"])[1]")).sendKeys("aniket");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
//		scroll down by using java script executer
				JavascriptExecutor executor=(JavascriptExecutor)driver;
				executor.executeScript("window.scrollBy(0,"+ 500 +")");
		
	//click on delete Icon
				//driver.findElement(By.xpath("((//div[@role=\"rowgroup\"][2])//i[@class=\"oxd-icon bi-trash\"])[1]")).click();
				driver.findElement(By.xpath("(//i[@class=\"oxd-icon bi-trash\"])[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]")).click();
				
		
		
	}
		
	@Test(priority=9,enabled=false)
	public void printEmployeeList() throws InterruptedException {
		login();
		//click on PIM
		List<WebElement> element=driver.findElements(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"]"));
		for(int i=0;i<element.size();i++) {
		String rowtext = element.get(1).getText();
		}
		element.get(1).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();//click on employee list
		Thread.sleep(2000);
//		scroll down by using java script executer
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,"+ 800 +")");
		Thread.sleep(1000);
		
		//find links of pages
		List<WebElement> totallinkelements=driver.findElements(By.xpath("//ul[@class=\"oxd-pagination__ul\"]"));
		int totallinks=totallinkelements.size();
		System.out.println("total page links found="+ totallinks);
		if(totallinks==0) {
			
			List<WebElement> empList=driver.findElements(By.xpath("//div[@class=\"oxd-table-card\"]//div//div[4]"));
			//List<WebElement>empList=driver.findElements(By.xpath(""));
			for(int j=0;j<empList.size();j++)
			{
				//print last name
				String lastName=empList.get(j).getText();
				System.out.println("Last Name from list is : "+ lastName);
			}
		
			
		}
		else {
		for(int i=0;i<totallinks;i++)//0,1,2,3
		{
			
		try {
			String currentLinkText=totallinkelements.get(i).getText();
			
			int page=Integer.parseInt(currentLinkText);
			
			System.out.println("page:"+ page);
			totallinkelements.get(i).click();
			Thread.sleep(2000);
			
			List<WebElement> empList=driver.findElements(By.xpath("//div[@class=\"oxd-table-card\"]//div//div[4]"));
			//List<WebElement>empList=driver.findElements(By.xpath(""));
			for(int j=0;j<empList.size();j++)
			{
				//print last name
				String lastName=empList.get(j).getText();
				System.out.println("Last Name from list is : "+ lastName);
			}
			
			
		}
		catch(Exception e) {
			System.out.println("not a number");
			e.getMessage();
		}
		//Thread.sleep(5000);
		//logout();
	}
   }}
	@Test(priority=9,enabled=true)
	public void applyEmployeeLeave() throws InterruptedException {
		
		login();
		//click on leave menu
		driver.findElement(By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[3]")).click();
		driver.findElement(By.partialLinkText("Apply")).click();
		String pagetext= driver.findElement(By.xpath("//h6[@class=\"oxd-text oxd-text--h6 orangehrm-main-title\"]")).getText();
		System.out.println("pageText:  "+pagetext);
		
		// click on select 	
		driver.findElement(By.xpath("//div[@class=\"oxd-select-text-input\"]")).click();
		
		Thread.sleep(2000);
		//select from dropdown
		driver.findElement(By.xpath("//*[text()='CAN - FMLA']")).click();
		
		//send from date yyyy-dd-mm
		driver.findElement(By.xpath("(//input[@placeholder=\"yyyy-dd-mm\"])[1]")).sendKeys("2024-02-04");
		
		
		//send to date yyyy-dd-mm
		driver.findElement(By.xpath("(//input[@placeholder=\"yyyy-dd-mm\"])[2]")).sendKeys("2024-05-04");
		
		//enter comment
		driver.findElement(By.tagName("textarea")).sendKeys("Personal Leave");
		
		//click on apply
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).submit();
		Thread.sleep(5000);
		logout();
		
	}
	
	
	public void logout() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@class=\"oxd-userdropdown-tab\"]")).click();
		Thread.sleep(5000);
		List<WebElement> elements=driver.findElements(By.xpath("//a[@class=\"oxd-userdropdown-link\"]"));
		for(int i=0;i<elements.size();i++) {
			System.out.println(i+";"+ elements.get(i).getText());
		}
		elements.get(2).click();
					
			}
	
	@AfterTest(enabled=false)
	public void teardown() throws InterruptedException {
		//Thread.sleep(5000);
		//logout();
		driver.close();
		driver.quit();
	}
}
