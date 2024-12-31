package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductsPage;
import testComponents.BaseTest;

public class SubmitOderTest extends BaseTest{
	
	String productName = "IPHONE 13 PRO";
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException
	{		
		
		String countryName = "india";
		
		ProductsPage productsPage = landingPage.loginApplication(input.get("email"), input.get("password"));

//		ProductsPage productsPage = new ProductsPage(driver);

		List<WebElement> products = productsPage.listOfProducts();
		productsPage.addProductToCart(input.get("productName"));
		CartPage cartPage = productsPage.goToCartPage();

//		CartPage cartPage = new CartPage(driver);

		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

//		CheckoutPage checkoutPage = new CheckoutPage(driver);

		checkoutPage.selectCountry(countryName);
		OrderConfirmationPage orderConfirmationPage = checkoutPage.submitOrder();

//		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);

		String confirmationMessage = orderConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistory()
	{
		ProductsPage productsPage = landingPage.loginApplication("anilsirimilla@gmail.com", "Anil@1997");
		OrderPage orderPage = productsPage.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\testData\\PurchaseOrder.json");
	    return	new Object[][] {{data.get(0)},{data.get(1)}};
}
}

//return new Object[][] {{"anilsirimilla@gmail.com","Anil@1997","IPHONE 13 PRO"}, {"anilsirimilla3@gmail.com","Anil@1997","ADIDAS ORIGINAL"}};

//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "anilsirimilla@gmail.com");
//map.put("password", "Anil@1997");
//map.put("productName", "IPHONE 13 PRO");
//
//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "anilsirimilla3@gmail.com");
//map1.put("password", "Anil@1997");
//map1.put("productName", "ADIDAS ORIGINAL");