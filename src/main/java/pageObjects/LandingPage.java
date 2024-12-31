package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmailField;
	
	@FindBy(id = "userPassword") 
	WebElement userPasswordFiled;
	
	@FindBy(id = "login")
	WebElement submitButton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductsPage loginApplication(String email, String password)
	{
		userEmailField.sendKeys(email);
		userPasswordFiled.sendKeys(password);
		submitButton.click();
		ProductsPage productsPage = new ProductsPage(driver);
		return productsPage;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}


}
