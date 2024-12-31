package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;

	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
		
//		Or
//		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
//		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(findBy));
	}

    public OrderPage goToOrderPage()
    {
    	ordersButton.click();
    	OrderPage orderPage = new OrderPage(driver);
    	return orderPage;
    }
}
