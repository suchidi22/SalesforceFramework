package com.jan23.Salesforce;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jan23Salesforce.base.SalesForceBaseTest;
import com.jan23salesforceUtility.PropertiesUtility;



@Listeners(com.jan23salesforceUtility.TestEventListnersUtility.class)
	public class SalesforceAutomationTest extends SalesForceBaseTest {
	//@Test
		//public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
			//loginToSalesforce();
			//Tc02();
			//Tc03();
			 //forgotPassword();
			 //errorMessage();
			//clickOnaboutTab();
			//postfilelinkClick ();
			//Addonphotolink ();
			 //Testcase08();
			//OpenLeadsPage();
			// loginToSalesForce();
				//OpenAccountsPage();
				//CreateNewAccount();
			//LeadsDropDownShowAllValues();
		//}
		@Test
		public static void loginToSalesForce() throws InterruptedException {
			//logger.info("inside loginToSalesForce method");
			//extentreport.logTestInfo("inside loginToSalesforse method");
			PropertiesUtility propertiesUtility =new PropertiesUtility();
			Properties propertyFile=  propertiesUtility.loadFile("testDataProperties");
			String url=propertiesUtility.getPropertyValue("url");
			String username=propertiesUtility.getPropertyValue("login.valid.userid");
			String password=propertiesUtility.getPropertyValue("login.valid.password");
			
			
			String expected ="Salesforce Home Page";
			GetDriverInstance("chrome");
			goToUrl(url);
			
			By idLoc = By.id("username");
			
			WebElement userName = driver.findElement(idLoc);
			WaitUntilElementIsVisible(userName,"username element");
			enterText(userName,username, "usernameEle");
			
			WebElement passwrd = driver.findElement(By.id("password"));
			enterText(passwrd, password, "passwordEle");
			driver.findElement(By.id("Login")).click();
			driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);	
		}
		@Test
		public static void loginToSalesforce() throws InterruptedException, IOException{
			logger.info("salesforceLogin method"); 
			 String expected ="Please enter your password.";
			 GetDriverInstance("chrome");
				goToUrl("https://login.salesforce.com/");
			Thread.sleep(5000);
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"suchi@tekarch.com","username");
			WebElement password =driver.findElement(By.id("password"));
			enterText (password,"","password");
			Thread.sleep(5000);
			driver.findElement(By.id("Login")).click();
			WebElement textElement = driver.findElement(By.id("error"));
			 if (textElement.isDisplayed()) {
				 //String actual = textElement.getText();
				 String actual = GetTextFromWebElement(textElement);
				 if(actual.equalsIgnoreCase(expected)) {
	        	 logger.info("SFDC login error - test case is passed");
				 }
	         }
	         else {
	        	logger.error("SFDC login error - test case is failed");
	         }
	
			
		}
		
		@Test
		public static void Tc02 ()throws InterruptedException {
			
			GetDriverInstance("chrome");
			goToUrl("https://login.salesforce.com/");
			Thread.sleep(4000);
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"suchi@tekarch.com","username");
			WebElement password =driver.findElement(By.id("password"));
			enterText (password,"allow@123","password");
			driver.findElement(By.id("Login")).click();
			System.out.println("User able to login to Salesforce home page");
			
		}
		@Test
		public static void forgotPassword() throws InterruptedException, IOException {
			
			GetDriverInstance("chrome");
			goToUrl("https://login.salesforce.com/");
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"suchi@tekarch.com","username");
			WebElement password =driver.findElement(By.id("password"));
			 enterText (password,"allow@123","password");
			WebElement forgotpassword =driver.findElement(By.xpath("//*[@id=\"forgot_password_link\"]"));
			Thread.sleep(2000);
			forgotpassword.click();
		

		}
		@Test
		public static void Tc03() throws InterruptedException, IOException {
			PropertiesUtility propertiesUtility =new PropertiesUtility();
				
			Properties propertyFile=  propertiesUtility.loadFile("testDataProperties");
			String url=propertiesUtility.getPropertyValue("url");
			String username=propertiesUtility.getPropertyValue("login.valid.userid");
			String password=propertiesUtility.getPropertyValue("login.valid.password");
			
			
			String expected ="rememberme checkbox should be checked";
			GetDriverInstance("chrome");
			goToUrl(url);
			
			By idLoc = By.id("username");
			
			WebElement userName = driver.findElement(idLoc);
			WaitUntilElementIsVisible(userName,"username element");
			enterText(userName,username, "usernameEle");
			
			WebElement passwrd = driver.findElement(By.id("password"));
			enterText(passwrd, password, "passwordEle");
			 
			Thread.sleep(2000);
			WebElement rememberme =driver.findElement(By.xpath("//*[@id=\"rememberUn\"]"));
			rememberme.click();
			System.out.println("checkbox checked");
			driver.findElement(By.id("Login")).click();
			WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
	        usermenue.click();
	      //move mouse pointer on dropbox menu
	        Actions actions =new Actions(driver);
	        actions.moveToElement(usermenue).perform();
	        driver.findElement(By.linkText("Logout")).click();
	  
	
			
		}

		@Test
		public static void errorMessage() throws InterruptedException, IOException {
			String expected ="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
			GetDriverInstance("chrome");
			goToUrl("https://login.salesforce.com/");
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"123","username");
			WebElement password =driver.findElement(By.id("password"));
			 enterText (password,"22131","password");
			Thread.sleep(5000);
			driver.findElement(By.id("Login")).click();
			
			WebElement textElement = driver.findElement(By.id("error"));
			 if (textElement.isDisplayed()) {
				//String actual = driver.findElement(By.id("error")).getText();
				 String actual = GetTextFromWebElement(textElement);
				 if(actual.equalsIgnoreCase(expected)) {
		        	 System.out.println("Your login attempt has failed - test case is passed");
					 }
				 else {
					 System.out.println("SFDC logged in - test case is failed");
				 }
			 }
			 
			
			 
		}
		public static void clickOnaboutTab() throws InterruptedException, IOException{
			GetDriverInstance("chrome");
			goToUrl("https://login.salesforce.com/");
			Thread.sleep(2000);
			
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"suchi@tekarch.com","username");
			WebElement password =driver.findElement(By.id("password"));
			enterText (password,"allow@123","password");
			
			driver.findElement(By.id("Login")).click();
		
	         WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
	         usermenue.click();
	     Thread.sleep(4000);
	         WebElement myprofile = driver.findElement(By.xpath("//a[contains(text(), 'My Profile')]"));
	         myprofile.click();
	         Thread.sleep(4000);
	          WebElement editbutton =driver.findElement(By.xpath(" //a[@id='moderatorMutton']"));
	       editbutton.click();
	         Thread.sleep(3000);
	         WebElement goToEditprofile =driver.findElement(By.xpath("//a[contains(text(),'Edit Profile')]"));
	         goToEditprofile.click();
	        // driver.close();
		}
		@Test
		public static void postfilelinkClick () throws InterruptedException, AWTException, IOException{
       	 GetDriverInstance("chrome");
    		goToUrl("https://login.salesforce.com/");
    		Thread.sleep(2000);

    		WebElement username = driver.findElement(By.id("username"));
    		enterText(username,"suchi@tekarch.com","username");
    		WebElement password =driver.findElement(By.id("password"));
    		enterText (password,"allow@123","password");
    		
    		driver.findElement(By.id("Login")).click();
    		 WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
            usermenue.click();
        Thread.sleep(4000);
            WebElement myprofile = driver.findElement(By.xpath("//a[contains(text(), 'My Profile')]"));
            myprofile.click();
            Thread.sleep(4000);
        
        WebElement post = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]")); //click on the post
        post.click();
        WebElement postbox =driver.findElement(By.xpath("//iframe[contains(@title,'Rich Text Editor, publisherRichTextEditor')]"));
      	driver.switchTo().frame(postbox);
      	WebElement frameBody = driver.findElement(By.xpath("/html[1]/body[1]"));
      	frameBody.click();
      	frameBody.sendKeys("hi there!!");
      	driver.switchTo().parentFrame();
      	
      	//WebElement shareButton  = driver.findElement(By.xpath("//input[@id=\"publishersharebutton\"]"));
      	//shareButton.click();
      Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"publisherAttachContentPost\"]")).click();//click on the file 
      //a[@id="chatterUploadFileAction"]
        WebElement uploadFile = driver.findElement(By.xpath("//a[@id=\"chatterUploadFileAction\"]"));
      uploadFile.click();
    //input[@id="chatterFile"]
      WebElement choosefile = driver.findElement(By.xpath("//input[@id=\"chatterFile\"]"));
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("document.getElementById('chatterFile').click();");
    
      StringSelection stringSelection = new StringSelection("C:\\Users\\debsa\\OneDrive\\Pictures\\sp.jpg");
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);//outside selenium 
		Robot robot=new Robot();
		 robot.keyPress(KeyEvent.VK_CONTROL);//pressing the control v button ..1st 4 step
       robot.keyPress(KeyEvent.VK_V);
       robot.keyRelease(KeyEvent.VK_V);
       robot.keyRelease(KeyEvent.VK_CONTROL);
       robot.keyPress(KeyEvent.VK_ENTER);//press enter key
       robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(4000);
      
       // driver.close();
        //driver.findElement(By.xpath("//*[@id=\"publisherAttachLinkPost\"]/span[1]")).click(); //click on the file link
		}
		@Test
		public static void Addonphotolink () throws InterruptedException, IOException{
       	 GetDriverInstance("chrome");
    		goToUrl("https://login.salesforce.com/");
    		Thread.sleep(2000);

    		WebElement username = driver.findElement(By.id("username"));
    		enterText(username,"suchi@tekarch.com","username");
    		WebElement password =driver.findElement(By.id("password"));
    		enterText (password,"allow@123","password");
    		
    		driver.findElement(By.id("Login")).click();
    		 WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
            usermenue.click();
        Thread.sleep(4000);
            WebElement myprofile = driver.findElement(By.xpath("//a[contains(text(), 'My Profile')]"));
            myprofile.click();
            Thread.sleep(4000);
            WebElement moderator = driver.findElement(By.xpath("//span[@id='displayBadge']"));
            Actions action = new Actions (driver);
            action.moveToElement(moderator).build().perform();
           
            WebElement addPhoto = driver.findElement(By.xpath("//*[@id=\"uploadLink\"]"));
            addPhoto.click();
            Thread.sleep(4000);
            WebElement childwindow = driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));
            driver.switchTo().frame(childwindow);
            
            WebElement ChooseFile = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
            ChooseFile.sendKeys("C:\\Users\\debsa\\OneDrive\\Pictures");
            getScreenshotOfThePage();
            //driver.close();
        }   
		public static void Testcase08() throws InterruptedException, IOException {
			GetDriverInstance("chrome");
			goToUrl("https://login.salesforce.com/");
			Thread.sleep(2000);
			
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"suchi@tekarch.com","username");
			WebElement password =driver.findElement(By.id("password"));
			enterText (password,"allow@123","password");
			driver.findElement(By.id("Login")).click();
			Thread.sleep(2000);
	         WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
	         usermenue.click();
	         Thread.sleep(3000);
	         //move mouse pointer on dropbox menu
	         Actions actions =new Actions(driver);
	         actions.moveToElement(usermenue).build().perform();
	         driver.findElement(By.linkText("Developer Console")).click();
	         System.out.println("Developer consolde opened..  ");
	         
	         Set<String> getAllWindows = driver.getWindowHandles();
	         Iterator <String> itr = getAllWindows.iterator();
	         
	  
	         String parentId = itr.next();
	         String childId = itr.next();
	         
	         Thread.sleep(8000);
	         
	         //traverse the method
	         driver.switchTo().window(childId);
	         System.out.println("Title of the child page:  "+ driver.getTitle());
	         driver.switchTo().window(parentId);
	  
	         Actions a = new Actions(driver);
	         a.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();
	         getScreenshotOfThePage();
	         //driver.close();
		}
		@Test
		public static void Tc09()  throws InterruptedException, IOException {
			GetDriverInstance("chrome");
			goToUrl("https://login.salesforce.com/");
			Thread.sleep(4000);
			WebElement username = driver.findElement(By.id("username"));
			enterText(username,"suchi@tekarch.com","username");
			WebElement password =driver.findElement(By.id("password"));
			enterText (password,"allow@123","password");
			driver.findElement(By.id("Login")).click();
			WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
	        usermenue.click();
	      //move mouse pointer on dropbox menu
	        Actions actions =new Actions(driver);
	        actions.moveToElement(usermenue).build().perform();
	        driver.findElement(By.linkText("Logout")).click();
	        getScreenshotOfThePage();
	       // driver.close();
		}

		@Test
		public static void OpenAccountsPage() throws IOException {
			WebElement usermenue = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
	        Actions actions =new Actions(driver);
	        actions.moveToElement(usermenue).perform();
	        driver.findElement(By.linkText("Accounts")).click();
	        getScreenshotOfThePage();
	        //driver.close();
		}
		@Test
		public static void CreateNewAccount() throws IOException {
			driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")).click();
			ClosePopupWindow();
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			By accountName =  By.name("acc2");
			WebElement accountNameTextbox = driver.findElement(accountName);
			js.executeScript("arguments[0].value='Matangini'", accountNameTextbox);
			
			By billingCity = By.name("acc17city");
			WebElement billingCityTextbox =  driver.findElement(billingCity);
			js.executeScript("arguments[0].value='Poway'", billingCityTextbox);
			getScreenshotOfThePage();
			//driver.close();
		}
		@Test
		
		public static void ClosePopupWindow() throws IOException {
			driver.findElement(By.xpath("//*[@id=\"tryLexDialogX\"]")).click();
			System.out.println(driver.getWindowHandle());
			String popupWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			for(String x: windows ) {
				if(x.equals(popupWindow) ==false) {
					driver.switchTo().window(x);
					break;
				}
			}
			logger.info(driver.getCurrentUrl());
			logger.info(driver.getTitle());
			getScreenshotOfThePage();
			//driver.close();
		}
		
	}

		