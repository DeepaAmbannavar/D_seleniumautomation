package singlewindow_pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DPW_LoginPageObjects {

	
	    public DPW_LoginPageObjects(WebDriver driver) {
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(id = "username")
	    public WebElement username;

	    @FindBy(id = "password")
	    public WebElement password;

	    @FindBy(id = "kc-login")
	    public WebElement loginButton;

//	    @FindBy(xpath = "//div[@class='agent-card']//p[@title='AE-8123109 - Dubai Customs M2 - SWBRO01 M&M Private L.L.C']")
//	    public WebElement selectAgent;
	    
//	    @FindBy(xpath="(//div[@class='agent-card']//i[@class='v-icon notranslate v-theme--dtLightTheme v-icon--size-default text-white customerImage'])[2]")
//	    public WebElement selectAgent;
	    
	  //div[@class='v-overlay__content']
	    @FindBy(xpath="//div[@class='v-overlay__content']")
	    public WebElement overlayele;
	    
	    @FindBy(xpath="(//div[@class='v-responsive v-img vectorImage'])[2]")
	    public WebElement selectAgent;
	    
	    @FindBy(xpath="//span[text()='Start Your Journey']")
	    public WebElement startJourneyBtn;
	  
	    @FindBy(xpath="//button[@data-test-id='start-journey-btn']")
	    public WebElement startJourneyBtn2;
	  
	    @FindBy(xpath="//div[text()='SW Integrated Clearance']")
	    public WebElement favorite_IntegratedClearance;
	    
	    @FindBy(css = "iframe[title='description']")
	    public WebElement iframeDescription;

	    @FindBy(xpath = "//span[text()='Start Journey']")
	    public WebElement startJourneyBtn1;
	    
	    @FindBy(xpath = "//span[contains(@class,'v-btn__content') and text()='Proceed']")
	    public WebElement proceedBtn;
	    
	    @FindBy(xpath = "//input[@placeholder='Search']")
	    public WebElement exporterCode;
	    
	    @FindBy(xpath = "(//span[contains(@class,'v-btn__content') and text()='Save'])[1]")
	    public WebElement saveBtn;
	    
	    @FindBy(xpath = "//button[@type='button']//span[contains(text(),'Add Manually')]")
	    public WebElement addManually;
	    
	  
	    @FindBy(xpath = "//div[@class='v-field__field']/label[(text()='Invoice No')]/following-sibling::input")
	    public WebElement invoiceNo;
	   

	    
	}

