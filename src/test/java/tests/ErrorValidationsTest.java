package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductsPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class) //, retryAnalyzer = Retry.class
	public void LoginError() throws InterruptedException
	{		
		landingPage.loginApplication("anilsirimilla@gmail.com", "Anil");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test()
	public void ProductErrorValidation() throws InterruptedException
	{
		String productName = "IPHONE 13 PRO";
		
		ProductsPage productsPage = landingPage.loginApplication("anilsirimilla2@gmail.com", "Anil@1997");
		List<WebElement> products = productsPage.listOfProducts();
		productsPage.addProductToCart(productName);
		CartPage cartPage = productsPage.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("IPHONE 13");
		Assert.assertFalse(match);
	}
}

