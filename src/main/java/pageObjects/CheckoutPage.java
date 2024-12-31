package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	By countryDisplayed = By.cssSelector(".ta-results");
	
	@FindBy(xpath=("(//button[contains(@class, 'ta-item')])[2]"))
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitOrderButton;
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(countryDisplayed);
		selectCountry.click();
	}
	
	public OrderConfirmationPage submitOrder()
	{
		submitOrderButton.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}


}
