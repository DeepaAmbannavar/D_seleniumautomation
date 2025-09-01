package singleWindowPages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageFiles.TestBase;
import singlewindow_pageobject.DPW_LoginPageObjects;


public class SW1 {
	
	

	public class DPW_LoginPage extends TestBase {

	    WebDriver driver;
	    DPW_LoginPageObjects loginObjects;
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    public DPW_LoginPage(WebDriver driver) {
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
	    
	    
	    	
		public void clickStartJourney() throws InterruptedException {
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

	}
		/*
		
		public void clickStartJourney() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    JavascriptExecutor js = (JavascriptExecutor) driver;

		    try {
		        // Wait for the button to be visible and clickable
		        WebElement startJourneyBtn = wait.until(ExpectedConditions.elementToBeClickable(loginObjects.startJourneyBtn));
		        Assert.assertTrue("Start Journey button is not displayed", startJourneyBtn.isDisplayed());

		       
		        System.out.println("Clicking Start Journey button");
		        js.executeScript("arguments[0].click();", startJourneyBtn);

		        // Wait for navigation to complete (e.g., URL change or new element visible)
		        wait.until(ExpectedConditions.urlContains("https://dtsit.dubaitrade.ae/swip/")); // Replace with actual path or keyword

		        String currentUrl = driver.getCurrentUrl();
		        System.out.println("Navigation successful. Current URL: " + currentUrl);

		        // Assert that the URL is as expected
		        Assert.assertTrue("Navigation failed or incorrect page loaded", currentUrl.contains("https://dtsit.dubaitrade.ae/swip/"));

		    } catch (TimeoutException e) {
		        throw new AssertionError("Start Journey button not clickable or navigation failed within timeout", e);
		    } catch (Exception e) {
		        throw new RuntimeException("Unexpected error during Start Journey click", e);
		    }
		}


		}
		*/
			// not clickable sometimes using this
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.elementToBeClickable(loginObjects.startJourneyBtn)).click();
//			System.out.println("User is navigated to Landing page ");

			//2nd logic
		/*	boolean clicked = false;
			int attempts = 0;

			while (attempts < 3) {
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			        WebElement startJourneyBtn = wait.until(ExpectedConditions.elementToBeClickable(loginObjects.startJourneyBtn));
//			        startJourneyBtn.click();
			        //2nd logic
//			        Actions actions = new Actions(driver);
//			    	actions.moveToElement(loginObjects.startJourneyBtn).click().perform();
			    	//3rd logic
			        System.out.println("waiting for the page to load....");
			        wait.until(ExpectedConditions.urlContains("https://dtsit.dubaitrade.ae/swip/welcomeMessage"));
			        System.out.println("user is on correct page now");

			        JavascriptExecutor js = (JavascriptExecutor) driver;
			        js.executeScript("arguments[0].click();", loginObjects.startJourneyBtn);


			        System.out.println("Clicked on Start Journey");
			        clicked = true;
			        break;
			    } catch (Exception e) {
			        System.out.println("Retrying Start Journey click...");
			        Thread.sleep(1000);
			        attempts++;
			    }
			}

			if (!clicked) {
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//			    js.executeScript("arguments[0].click();", loginObjects.startJourneyBtn);
			    throw new RuntimeException("Failed to click on 'Start Journey' after 3 attempts.");
			    

			}
	*/
		//next logic
			/*
			 boolean clicked = false;
			int attempts = 0;

			while (attempts < 3) {
			    try {
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			        // Wait until the button is clickable
			        WebElement startJourneyBtn = wait.until(ExpectedConditions.elementToBeClickable(loginObjects.startJourneyBtn));

			        // Click using JavaScript (if normal click doesn't work)
			        JavascriptExecutor js = (JavascriptExecutor) driver;
			        js.executeScript("arguments[0].click();", startJourneyBtn);

			        System.out.println("Clicked on Start Journey");

			        // Now wait for the page to load or URL to change
			        wait.until(ExpectedConditions.urlContains("welcomeMessage"));

			        System.out.println("User is on correct page now");
			        clicked = true;
			        break;

			    } catch (Exception e) {
			        System.out.println("Retrying Start Journey click...");
			        attempts++;
			        Thread.sleep(1000);
			    }
			}

			if (!clicked) {
			    throw new RuntimeException("Failed to click on 'Start Journey' after 3 attempts.");
			}
	*/
			//next logic
			/*
			try {
				System.out.println("Clicked. Current URL: " + driver.getCurrentUrl());

			    Actions actions = new Actions(driver);
			    actions.moveToElement(loginObjects.startJourneyBtn).click().perform();
			} catch (Exception e) {
			    JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("arguments[0].click();", loginObjects.startJourneyBtn);
			}
	*/
			
			//next logic
			/*
			boolean clicked = false;
			int attempts = 0;

			while (attempts < 3) {
			    try {
			    	System.out.println("Current URL after click: " + driver.getCurrentUrl());
			    	System.out.println("Page source contains next button? " + driver.getPageSource().contains("data-test-id='start-journey-btn'"));

			    	JavascriptExecutor js = (JavascriptExecutor) driver;
			    	js.executeScript("document.querySelectorAll('div.v-overlay__content').forEach(el => el.remove());");

			    	
			    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay__content")));
			    	
			    	//JavascriptExecutor js = (JavascriptExecutor) driver;
			    	js.executeScript("arguments[0].click();", loginObjects.startJourneyBtn);


			    	
			        System.out.println("Navigation successful — next button is visible");
			        clicked = true;
			        break;

			    } catch (Exception e) {
			        System.out.println("Retrying Start Journey click...");
			        attempts++;
			        Thread.sleep(1000);
			    }
			}

			if (!clicked) {
			    throw new RuntimeException("Failed to click on 'Start Journey' after 3 attempts.");
			}

	           
			
			
		}
		*/
			    
			

}
