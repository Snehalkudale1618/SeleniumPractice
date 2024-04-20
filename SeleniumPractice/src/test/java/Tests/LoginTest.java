package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObject.AddEmployeePage;
import PageObject.ConfigrationPage;
import PageObject.DashbordPage;
import PageObject.EmployeeList;
import PageObject.LoginPage;

public class LoginTest {
	
	
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

	
	
	
	@Test(priority=1,enabled=false)
	public void loginTestWithInValidCredential() throws InterruptedException

	{	
		//pom page sources
		LoginPage pg = new LoginPage(driver);
		


		//enter user name
		pg.setUserNames("Admin");
		
		//enter password
		pg.setPassword("admin112");
		
		//click login btn
		pg.clickLoginbtn();
		
	
		String message_expected = "Invalid Credential";
		String message_actual = pg.getInvalidText();
		Thread.sleep(5000);
		Assert.assertEquals("Invalid Credential",message_expected);
		
	}	
	
	
	@Test(priority=2,enabled=true)
	public void loginTestWithValidCredential() throws InterruptedException{	
		
		//pom page sources
				LoginPage pg = new LoginPage(driver);
				DashbordPage dashboard=new DashbordPage(driver);

		
				//enter user name
				pg.setUserNames("Admin");
				
				//enter password
				pg.setPassword("admin123");
				
				//click login btn
				pg.clickLoginbtn();

	
	Thread.sleep(2000);
	//verify the login is successful by checking the page title or specific element
	
	String pagetitle=dashboard.getPageTitle();
	System.out.println("Dashbord Page Title"+pagetitle);
	Assert.assertEquals(pagetitle, "OrangeHRM");

}
	
	//Add New Employee
		@Test(priority=3,enabled=false)
		public void addNewEmployee() throws InterruptedException, IOException {
			LoginPage pg = new LoginPage(driver);
			DashbordPage dashboard = new DashbordPage(driver);
			AddEmployeePage addEmp = new AddEmployeePage(driver);

				
				dashboard.clickOnPIM();
				Thread.sleep(5000);
				dashboard.clickOnAddEmployee();
				Thread.sleep(5000);
				addEmp.setFirstName("Minal");
				addEmp.setMiddleName("Ram");
				addEmp.setLastName("Paranjape");
				
				
				addEmp.setEmpId("0101");
				addEmp.uploadEmpImage();
				Thread.sleep(3000);
				addEmp.clickOnSaveBtn();
				Thread.sleep(3000);
				
				//verify if employee is added successfully or not by checking employee list personal details
				String confermationmessage =addEmp.getConfermationMessage();
				if(confermationmessage.contains("Personal Details")) {
					System.out.println("Employee added succesfully!");
					}
					else {
						System.out.println("Failed to add Employee");
					}
				Thread.sleep(2000);
				
				}
		
	
		@Test(priority=6,enabled=false)
		public void fileUpload() throws InterruptedException, IOException {
			
			LoginPage pg = new LoginPage(driver);
			DashbordPage dashboard = new DashbordPage(driver);
			AddEmployeePage addEmp = new AddEmployeePage(driver);
			ConfigrationPage config=new ConfigrationPage(driver);
			//used autoIt tool and finder
			dashboard.clickOnPIM();
		
			Thread.sleep(2000);
			dashboard.clickOnConfigrationMenu();
			dashboard.clickOnDataImport();
			Thread.sleep(2000);
			
			config.clickOnBrouseBtn();
			Thread.sleep(5000);
			config.clickOnUpload();
				 
			
		}
		
		
		@Test(priority=8,enabled=false)
		public void deleteEmployee() throws InterruptedException {
		
			LoginPage pg = new LoginPage(driver);
			DashbordPage dashboard = new DashbordPage(driver);
			 EmployeeList emplist=new  EmployeeList(driver);
			
			dashboard.clickOnPIM();
			dashboard.clickOnEmployee_List();
			emplist.SetempName("minal");
			emplist.clickOnSearchBtn();
			emplist.clickOnDeleteIcon();
			emplist.areYouSureToDeleteEmployee();
			
			
		}
		
		
		@Test(priority=4,enabled=false)	
		public void searchEmployeeByName() throws InterruptedException {
			
			LoginPage pg = new LoginPage(driver);
			DashbordPage dashboard = new DashbordPage(driver);
			AddEmployeePage addEmp = new AddEmployeePage(driver);
			ConfigrationPage config=new ConfigrationPage(driver);
			EmployeeList emplist=new  EmployeeList(driver);

			//String messege="Records Found";
			
			dashboard.clickOnPIM();
			dashboard.clickOnEmployee_List();
			emplist.SetempName("minal");
			emplist.clickOnSearchBtn();
		
			Thread.sleep(2000);
		
			String actmessege=emplist.act_message();//recordsfound
			System.out.println(actmessege);
			
			}
		
		
		
		@Test(priority=5,enabled=true)
		public void searchEmployeeById() throws InterruptedException {
			
			//LoginPage pg = new LoginPage(driver);
			DashbordPage dashboard = new DashbordPage(driver);
			//AddEmployeePage addEmp = new AddEmployeePage(driver);
			//ConfigrationPage config=new ConfigrationPage(driver);
			EmployeeList emplist=new  EmployeeList(driver);
			
			String empid="101";
			String messegeActual="";
			//loginTestWithValidCredential();
			
			dashboard.clickOnPIM();
			dashboard.clickOnEmployee_List();
			emplist.setEmpId("101");
			emplist.clickOnSearchBtn();
			
				Thread.sleep(2000);
				
				//	scroll down by using java script executer
				JavascriptExecutor executor=(JavascriptExecutor)driver;
				executor.executeScript("window.scrollBy(0,"+ 800 +")");
			 List<WebElement>rows=driver.findElements(By.xpath("//div[@role=\"rowgroup\"]"));
			 if(rows.size()>1) {
				 messegeActual= driver.findElement(By.xpath("((//div[@role=\"rowgroup\"])[2]//div[@class='oxd-table-cell oxd-padding-cell'])[2]")).getText();
			
				 Assert.assertEquals(empid,messegeActual);
				 
			}
			 else {
				 System.out.println("Employee Id is not found");
			 }
		}
				
			

@AfterTest(enabled=true)
public void teardown() throws InterruptedException {
	//Thread.sleep(5000);
	//logout();
	driver.close();
	driver.quit();
}
}