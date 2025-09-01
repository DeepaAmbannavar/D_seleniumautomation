package stepDefinitions;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.dpw.utils.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFiles.Hooks;
import singleWindowPages.BasicflowPage1;

public class basicflowStep {

	BasicflowPage1 icPage = new BasicflowPage1(Hooks.driver);

	
	    WebDriver driver = Hooks.driver; // Assume Hooks class manages driver lifecycle
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    static int counter = 9; // Start from 9 or any number
	    
	    
	    
	    @Given("I am logged in to Dubai Trade portal")
	    public void i_am_logged_in_to_dubai_trade_portal() throws InterruptedException, IOException {
	    	 // driver = new ChromeDriver();
	        //  driver.manage().window().maximize();
	        driver.get("https://dtsit.dubaitrade.ae/");
	        driver.findElement(By.name("username")).sendKeys("SWBRO01");
	        driver.findElement(By.name("password")).sendKeys("Login@345");
	        driver.findElement(By.xpath("//input[@id='kc-login']")).click();
	        Thread.sleep(8000);
	        wait.until(ExpectedConditions.urlContains("customerSelection"));
	        
	        
//	        ExcelUtils.setExcelFile("src/test/resources/testdata.xlsx", "Sheet1");
//		    
//		    String username = ExcelUtils.getCellData(1, 0); // row 1, col 0
//		    String password = ExcelUtils.getCellData(1, 1); // row 1, col 1
//		    String regimeType = ExcelUtils.getCellData(1, 2); // row 1, col 2
//		    
//		    loginPage.login(username, password);
//		    integratedClearancePage.selectRegimeType(regimeType);

	    }

	    @When("I navigate to the Integrated Clearance page")
	    public void i_navigate_to_integrated_clearance_page() throws InterruptedException {
	        // Add navigation steps as per your workflow, e.g.:
	        // (Replace with correct selectors for your app)
	    	Thread.sleep(10000);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'AE-8123109')]"))).click();
	        Thread.sleep(3000);
	        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test-id='start-journey-btn']"))).click();
	        Thread.sleep(10000);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'SW Integrated Clearance')]"))).click();
	        Thread.sleep(3000);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'AE-8123109')])[2]"))).click();
	        Thread.sleep(3000);
	        wait.until(ExpectedConditions.urlContains("/swip/sw/?module=ic"));
	    }

	    
	    @Then("the user clicks on Start Journey in the description iframe")
	    public void clickStartJourneyInIframe() {
	        try {
	            Thread.sleep(9000); // Not recommended for real waits! Use WebDriverWait in production.
	            //icPage.switchToDescriptionIframe();
	            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='description']")));
	            icPage.clickStartJourney();
	            icPage.switchToDefaultContent();
	            System.out.println("User is on IC first Page");
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @And("I select {string} from the Regime Type dropdown")
	    public void i_select_from_regime_type_dropdown(String option) throws InterruptedException {
	      
	    	Thread.sleep(4000);
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='description']")));
	        WebElement inputField = driver.findElement(By.xpath("(//input[@role='combobox'])[1]"));

	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", inputField);
    inputField.sendKeys(Keys.ARROW_DOWN);
    System.out.println("Triggered dropdown using keyboard");

	        System.out.println("Clicked on Regime Type input field");

	        Thread.sleep(4000); 

	         By optionLocator = By.xpath("//div[contains(@class,'v-list-item-title') and text()='" + option + "']");
	         WebElement dropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
	         wait.until(ExpectedConditions.visibilityOf(dropdownOption));

	         js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", dropdownOption);
	         System.out.println("Selected " + option + " from Regime Type dropdown");
	         Thread.sleep(3000);
	     }


	    @When("I select {string} from the {string} dropdown")
	    public void i_select_declarationType(String option, String option2) throws InterruptedException {
	    	Thread.sleep(2000);
	        WebElement inputField = driver.findElement(By.xpath("(//input[@role='combobox'])[2]"));

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", inputField);


	        inputField.sendKeys(Keys.ARROW_DOWN);
	        System.out.println("Clicked on Declaration Type input field");

	        Thread.sleep(2000); // Optional: allow animation

	      By optionLocator = By.xpath("//div[contains(@class,'v-list-item-title') and text()='" + option + "']");
	         WebElement dropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
	         wait.until(ExpectedConditions.visibilityOf(dropdownOption));

	         // 5. Click the option using JavaScript
	         js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", dropdownOption);
	         System.out.println("Selected " + option + " from Declaration Type dropdown");
	         Thread.sleep(1000);
	     }

	   
	    @And("I select {string} from {string} dropdown")
	    public void i_select_CargoChannel(String option, String option2) throws InterruptedException {
	    	Thread.sleep(3000);
	       // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='description']")));
	        WebElement inputField = driver.findElement(By.xpath("(//input[@role='combobox'])[3]"));
	        

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", inputField);


	        inputField.sendKeys(Keys.ARROW_DOWN);
	        System.out.println("Clicked on Cargo Channel input field");

	        Thread.sleep(1000); // Optional: allow animation

	      By optionLocator = By.xpath("//div[contains(@class,'v-list-item-title') and text()='" + option + "']");
	         WebElement dropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
	         wait.until(ExpectedConditions.visibilityOf(dropdownOption));

	         // 5. Click the option using JavaScript
	         js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", dropdownOption);
	         System.out.println("Selected " + option + " from Cargo Channel dropdown");
	         Thread.sleep(2000);
	     }

	   
	    @And("I enter DO Number")
	    public void i_Enter_DO_Number()throws InterruptedException {
	    	Thread.sleep(3000);
	       // wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='description']")));
	        WebElement inputField = driver.findElement(By.xpath("(//input[@class='v-field__input'])[1]"));
	        

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", inputField);

	        int randomNum = new Random().nextInt(1000); // 0 to 999
	        String uniqueValue = "DTest" + randomNum;
	        inputField.sendKeys(uniqueValue);


	       // inputField.sendKeys("Automation09");


//String uniqueValue = "Automation" + UUID.randomUUID().toString().substring(0, 8);
	        
//inputField.sendKeys(uniqueValue);



//	        counter++;
//	        String uniqueValue = "Automation" + counter;
//	        inputField.sendKeys(uniqueValue);

	        
	        //Entered a DO : 2500000438
	        System.out.println("Entered a CDR No : "+uniqueValue);

	        Thread.sleep(4000); // Optional: allow animation

	      
	     }
	    
	    @And("I click on Proceed")
	    public void i_Click_On_Proceed()throws InterruptedException {
	    	
	    try {
            Thread.sleep(6000); // Not recommended for real waits! Use WebDriverWait in production.
            //icPage.switchToDescriptionIframe();
          //  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='description']")));
            icPage.clickProceedButton();
           // icPage.switchToDefaultContent();
            System.out.println("User is on IC Second Page");
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	   
	    @When("I search Exporter Code")
	    public void i_enterExporterCode()throws InterruptedException {
	    	
		    try {
	            
	            //icPage.switchToDescriptionIframe();
	          //  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[title='description']")));
	            Thread.sleep(20000);
	           // icPage.enterExporterCode();
	            
	            System.out.println("User is clicking on Exporter Code field");
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search']")));
	            icPage.enterExporterCode();

	            System.out.println("User is clicking Save");
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'v-btn__content') and text()='Save']")));
	            icPage.clickOnSave();
	            
	           // icPage.clickOnSave();
	          //  icPage.switchToDefaultContent();
	            Thread.sleep(4000);
	            System.out.println("Check");
	            
	            
	            //assertTrue("Draft was not created", icPage.isDraftCreated());
	            Thread.sleep(20000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
	    @Then("The {string} option should be selected")
	    public void option_should_be_selected(String option) {
	        // Validate the selected value is displayed in the dropdown
	        WebElement selected = driver.findElement(
	            By.xpath("//label[text()='Regime Type']/following::div[contains(@class,'v-select')]//input"));
	       Assert.assertEquals(option, selected.getAttribute("value"));
	        driver.switchTo().defaultContent(); // Always switch back!
	    }
	
	    @And("I add invoice manually")
	    public void addInvoiceManually() throws InterruptedException {
	        Thread.sleep(3000);
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']//span[contains(text(),'Add Manually')]")));
            icPage.addManualInvoice();

            System.out.println("User is clicking Save");
            
	    }
	    
	    @When("I fill the form with the following data:")
	    public void fillFormWithData(DataTable dataTable) throws InterruptedException {
	    	Thread.sleep(3000);
	    	System.out.println("Collecting data");
	        List<Map<String, String>> fieldData = dataTable.asMaps(String.class, String.class);
	        for (Map<String, String> row : fieldData) {
	            String fieldLabel = row.get("Field Name");
	            System.out.println("Field Name: "+fieldLabel);
	            String value = row.get("Value");

if (value.equalsIgnoreCase("CURRENT_DATE")) {
    value = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
}

	            System.out.println("Field Value:"+value);

	            // Try input field
	            try {
	                String inputXpath = String.format("//div[@class='v-field__field']/label[text()='%s']/following-sibling::input", fieldLabel);
	                WebElement inputField = driver.findElement(By.xpath(inputXpath));
	                inputField.clear();
	                inputField.sendKeys(value);
	                
	                System.out.println("Entered Value Into"+fieldLabel);
	                continue;
	            } catch (NoSuchElementException ignored) {}

	            // Try dropdown
	            try {
	                String dropdownXpath = String.format("//div[@class='v-field__field']/label[text()='%s']/following-sibling::div", fieldLabel);
	                WebElement dropdown = driver.findElement(By.xpath(dropdownXpath));
	               // dropdown.click();
	                JavascriptExecutor js = (JavascriptExecutor) driver;
	    	        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].focus();", dropdown);


	    	        dropdown.sendKeys(Keys.ARROW_DOWN);
	    	        System.out.println("Triggered dropdown using keyboard");
	    	        System.out.println("Clicked on dropdown"+fieldLabel);
	                //String optionXpath = String.format("//div[contains(@class,'v-list-item-title') and text()='%s']", value);
	                String optionXpath = String.format("//div[contains(@class,'v-list-item-title') and normalize-space(text())='%s']", value);
	                By optionLocator = By.xpath(optionXpath);

				WebElement dropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
	    	         wait.until(ExpectedConditions.visibilityOf(dropdownOption));

	    	         // 5. Click the option using JavaScript
	    	         js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", dropdownOption);
	    	        Thread.sleep(3000);
	    	                     
	        
	                continue;
	            } catch (NoSuchElementException ignored) {}

	         // Try datefield
	            try {
	            	try {
		                String inputXpath = String.format("//div[@class='v-field__field']/label[text()='%s']/following-sibling::div", fieldLabel);
		              //div[@class='v-field__field']/label[text()='Invoice Date']/following-sibling::div
		                WebElement inputField = driver.findElement(By.xpath(inputXpath));
		               // inputField.clear();
		                inputField.sendKeys(value);
		                System.out.println("Entered Value Into"+fieldLabel);
		                continue;
		            } catch (NoSuchElementException ignored) {}


	            // Try checkbox
	            try {
	                String checkboxXpath = String.format("//label[text()='%s']/preceding-sibling::input[@type='checkbox']", fieldLabel);
	                WebElement checkbox = driver.findElement(By.xpath(checkboxXpath));
	                boolean shouldCheck = Boolean.parseBoolean(value);
	                if (checkbox.isSelected() != shouldCheck) {
	                    checkbox.click();
	                }
	            } catch (NoSuchElementException e) {
	                throw new RuntimeException("Unable to locate field: " + fieldLabel);
	            }}
	            finally {
	                System.out.println("Finished processing field: " + fieldLabel);
	            }
	        }
	    }

	    
	    
	   
	    @And("I click on Save")
	    public void click_On_Save() throws InterruptedException {
	    System.out.println("User is clicking Save");
	    Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'v-btn__content') and text()='Save']")));
        icPage.clickOnSave();
        
       // icPage.clickOnSave();
      //  icPage.switchToDefaultContent();
        Thread.sleep(4000);
        System.out.println("Clicked on save");
}
	    
	    
	    @And("I click on Yes")
	    public void click_On_Yes_on_Popup() throws InterruptedException {
	    System.out.println("User is clicking Yes on popup");
	    Thread.sleep(3000);
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'v-btn__content') and text()='Yes']")));
        icPage.clickOnYesOnPopUp();
        
       // icPage.clickOnSave();
      //  icPage.switchToDefaultContent();
        Thread.sleep(8000);
        System.out.println("Popup accepted");
        //driver.findElement(By.xpath("//button[@type='button']//span[text()='Proceed']"));
    //	Thread.sleep(2000);
    	// System.out.println("User now will be navigating to Document upload page");

}
	    
	    
	    
	    
	    @And("I upload a Inovice File")
	    public void uploadInvoice() throws InterruptedException {

	    	WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
	    	uploadInput.sendKeys("C:\\Users\\61079268\\OneDrive - LTIMindtree\\Deepa_OneDeriveFolder\\Desktop\\2024\\Dubai Trade(DP World) Travel_Trade_Logistic Domain\\TCs\\Integrated Clearance_101\\Automation\\Automation Inv File\\invoice.txt");
	    	
	    	Thread.sleep(2000);
	    	driver.findElement(By.xpath("//button[@type='button']//span[text()='Yes']"));
	    	Thread.sleep(2000);
	    	 System.out.println("Invoice Uploaded Successfully");
	    	 Thread.sleep(8000);
	    	
	    	
	    }
  
	    
	    @When("I click on the {string} button")
	    public void userClickOnButton(String buttonText) throws InterruptedException {
	    	Thread.sleep(6000);
	    	icPage.clickButtonByText(driver, buttonText, 10); 
	    }

		
	    
	    
	    
}