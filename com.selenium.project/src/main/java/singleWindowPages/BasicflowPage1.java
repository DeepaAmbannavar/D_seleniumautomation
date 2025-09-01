package singleWindowPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageFiles.TestBase;
import singlewindow_pageobject.DPW_LoginPageObjects;

public class BasicflowPage1 extends TestBase {

    WebDriver driver;
    DPW_LoginPageObjects loginObjects;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  

    public BasicflowPage1(WebDriver driver) {
        this.driver = driver;
        loginObjects = new DPW_LoginPageObjects(driver);
    }

    public void launchApplication() {
        
		// Don't create a new ChromeDriver here, use the one passed in constructor!
        String url = prop.getProperty("url");
        if (url != null && !url.isEmpty()) {
            driver.get(url);
            System.out.println("Application Launched: " + url);
        } else {
            System.out.println("URL not found in config.properties");
        }
    }

    public void loginWithConfigCredentials() {
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        if (username != null && password != null) {
            loginObjects.username.sendKeys(username);
            loginObjects.password.sendKeys(password);
            loginObjects.loginButton.click();
           // logger.info("Logged in with credentials from config: " + username);
        } else {
           // logger.error("Username or password not found in config.properties");
            throw new RuntimeException("Missing credentials in config.properties");
        }
    }
    public void selectAgent() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Remove overlay elements if they are blocking the click
        js.executeScript("document.querySelectorAll('div.v-overlay__content').forEach(el => el.remove());");

        // Log overlay count after removal (optional debug)
        List<WebElement> overlays = driver.findElements(By.className("v-overlay__content"));
        System.out.println("Overlay count after removal: " + overlays.size());

        // Click the agent selection button
        js.executeScript("arguments[0].click();", loginObjects.selectAgent);
        System.out.println("user is now navigated to next page");
        System.out.println("Clicked. Current URL: " + driver.getCurrentUrl());
        
    }

		
		
		
	/*	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(loginObjects.selectAgent));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(loginObjects.selectAgent)).click();
		}catch (Exception e) {
		    // Take screenshot, log error, fail test with meaningful message
		    Assert.fail("Element was not clickable: " + e.getMessage());
		}
		System.out.println("Agent is selected and navigated to Start Journey Page");
	}
*/
    	
    	
    	/*working code ***************************
    	 * ************************************************
    	 
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	int attempts = 0;
	while (attempts < 3) {
	    try {
	    	//below code worked 2 times and later stopped
	       // WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginObjects.selectAgent));
	    	 //element.click();
	    	
	    	//next logic
	    	System.out.println("Trying to click on element");
	    	Actions actions = new Actions(driver);
	    	actions.moveToElement(loginObjects.selectAgent).click().perform();
	    	System.out.println("clicked on element");

	    	// Add a short sleep or wait for UI update
	    	Thread.sleep(1000); 

	       
	        break;
	    } catch (StaleElementReferenceException e) {
	        attempts++;
	    }
	}}
	
	
	
	
	****************************************
	*/
    
    public void switchToDescriptionIframe() {
	       driver.switchTo().frame(loginObjects.iframeDescription);
	    }

	    public void clickStartJourney() throws InterruptedException {
	    	Thread.sleep(10000);
//	       loginObjects.startJourneyBtn1.click();
//	       System.out.println("Clicked on Start Journey");
	       

WebElement startJourneyBtn = loginObjects.startJourneyBtn1;

    // JavaScript click
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", startJourneyBtn);

    System.out.println("Clicked on Start Journey using JavaScript");
    Thread.sleep(1000);

	    }

	    
	    public void clickProceedButton() throws InterruptedException {
	    	
	    	Thread.sleep(10000);
//	       loginObjects.startJourneyBtn1.click();
//	       System.out.println("Clicked on Start Journey");
	       

WebElement proceedBtn = loginObjects.proceedBtn;

    // JavaScript click
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", proceedBtn);

    System.out.println("Clicked on Proceed Button");

	    }

public void enterExporterCode() throws InterruptedException {
	    	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement exporterCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']"))); // or By.xpath(...)
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", exporterCodeInput);
    wait.until(ExpectedConditions.elementToBeClickable(exporterCodeInput));
    exporterCodeInput.clear();
    exporterCodeInput.sendKeys("AE-8122862");
    exporterCodeInput.sendKeys(Keys.TAB);
    Thread.sleep(3000);
    
   // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(
//    		By.xpath("//div[contains(@class,'v-list-item-title') and text()='AE-8122862']")));
//    suggestion.click();
    System.out.println("Clicked on exporter suggestion");
    
    
    Thread.sleep(3000);
}

	    
	    
	    
	    public void switchToDefaultContent() {
	        driver.switchTo().defaultContent();
	    }
    	
	public void clickStartJourney1() throws InterruptedException {
		Thread.sleep(3000);	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		 System.out.println("Removing overlay");

	        Thread.sleep(1000);
	        System.out.println("Clicking start Journey");
	        js.executeScript("arguments[0].click();", loginObjects.startJourneyBtn);
	        System.out.println("user is now navigated to next page");
	        Thread.sleep(1000);
	        
	        String currentUrl = driver.getCurrentUrl();
	        System.out.println("Navigation successful. Current URL: " + currentUrl);

	        // Assert that the URL is as expected
	        //Assert.assertTrue("Navigation failed or incorrect page loaded", currentUrl.contains("https://dtsit.dubaitrade.ae/swip/"));
	       // Assert.assertTrue(currentUrl.contains("https://dtsit.dubaitrade.ae/swip/"), "Navigation failed or incorrect page loaded");
	        if (!currentUrl.contains("https://dtsit.dubaitrade.ae/swip/")) {
	            throw new RuntimeException("Navigation failed: Unexpected URL - " + currentUrl);
	        }

	    }

	public void clickOnSave() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[.//span[contains(@class,'v-btn__content') and text()='Save']]")
		));

		
		// Scroll and click
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);

		if (saveBtn.isEnabled()) {
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", saveBtn);
		    System.out.println("Created Draft Record");
		} else {
		    System.out.println("Save button is disabled.");
		}
	}
	
	public void clickOnYesOnPopUp() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[.//span[contains(@class,'v-btn__content') and text()='Yes']]")
		));

		
		// Scroll and click
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", yesBtn);

		if (yesBtn.isEnabled()) {
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", yesBtn);
		    System.out.println("Clicked on Yes");
		} else {
		    System.out.println("Yes button is disabled.");
		}
	}
	
	
	
	
	
	
	public boolean isDraftCreated() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    // Replace with actual locator for confirmation message or page transition
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Draft created')]"))) != null;
	}

	public void addManualInvoice() throws InterruptedException {
		
		Thread.sleep(3000);

		WebElement addManuallyBtn = loginObjects.addManually;

	    // JavaScript click
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", addManuallyBtn);

	    System.out.println("Clicked on Add Manually Button");
	    Thread.sleep(4000);

		    }

	public void clickButtonByText(WebDriver driver, String buttonText, int timeoutInSeconds) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            
            // XPath to locate button with specific text inside v-btn__content span
            By buttonLocator = By.xpath("//button[.//span[contains(@class,'v-btn__content') and text()='" + buttonText + "']]");

            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

            // Click using JS if enabled
            if (button.isEnabled()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                System.out.println("Clicked on button: " + buttonText);
            } else {
                System.out.println("Button '" + buttonText + "' is disabled.");
            }
        } catch (TimeoutException e) {
            System.out.println("Button with text '" + buttonText + "' not found or not clickable within " + timeoutInSeconds + " seconds.");
        } catch (Exception e) {

System.out.println("Error while clicking button '" + buttonText + "': " + e.getMessage());
Thread.sleep(20000);
}

    }	


}


