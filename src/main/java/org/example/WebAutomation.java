package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
public class WebAutomation {
    WebDriver driver = null;

    @BeforeTest
    public void setup(){
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get("https://www.bundabergrum.com.au/");

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement element = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("onetrust-close-btn-container")));
        element.click();

        //close cookie popup
        if(element.isDisplayed()){
            driver.findElement(By.id("onetrust-close-btn-container")).click();
        }

    }

    @Test(priority = 1)
    public void ageTest() throws InterruptedException {

        //test for < 25
        driver.findElement(By.id("age_select_year_of_birth")).sendKeys("2006");
        if(driver.findElement(By.id("age_select_month_of_birth")).isDisplayed()) {
            driver.findElement(By.id("age_select_month_of_birth")).sendKeys("12");
        }
        if(driver.findElement(By.id("age_select_day_of_birth")).isDisplayed()) {
            driver.findElement(By.id("age_select_day_of_birth")).sendKeys("5");
        }
        driver.findElement(By.id("age_confirm")).click();
        if(driver.findElement(By.id("age_missing_message")).isDisplayed()){
            System.out.println("Tested age < 25: Error message displayed");
        }
        else{
            System.out.println("Tested age < 25: Error message not displayed");
        }
        driver.findElement(By.id("age_select_year_of_birth")).clear();

        if(driver.findElement(By.id("age_select_month_of_birth")).isDisplayed()) {
            driver.findElement(By.id("age_select_month_of_birth")).clear();
        }
        if(driver.findElement(By.id("age_select_day_of_birth")).isDisplayed()) {
            driver.findElement(By.id("age_select_day_of_birth")).clear();
        }

        //test for >= 25
        driver.findElement(By.id("age_select_year_of_birth")).sendKeys("1998");
        if(driver.findElement(By.id("age_select_month_of_birth")).isDisplayed()) {
            driver.findElement(By.id("age_select_month_of_birth")).sendKeys("12");
        }

        if(driver.findElement(By.id("age_select_day_of_birth")).isDisplayed()) {
            driver.findElement(By.id("age_select_day_of_birth")).sendKeys("5");
        }
        driver.findElement(By.id("age_confirm")).click();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        Thread.sleep(5000);

        if(!driver.findElement(By.className("age_header_title")).isDisplayed()){
            System.out.println("Tested age >= 25: Passed");
        }

        else{
            System.out.println("Tested age >= 25: Failed");
        }
    }

    @Test(priority = 2)
    public void myAccountTest(){

        System.out.println(driver.findElement(By.cssSelector("div.custom_image1:nth-child(2) > div:nth-child(2) > p:nth-child(2) > span:nth-child(1)")).getText());

        driver.findElement(By.linkText("My Account")).click();
        if(!(driver.findElement(By.id("send2")) ==null)){
            System.out.println("Login button available");
        }
        else{
            System.out.println("Login button not available");
        }
    }

    @Test(priority = 3)
    public void loginTest() throws InterruptedException {

        /*
        //try login without password
        driver.findElement(By.id("email")).sendKeys("williamjacob802@gmail.com");
        Thread.sleep(10000);


        driver.findElement(By.id("send2")).click();
        if(driver.findElement(By.id("pass-error")).isDisplayed()){
            System.out.println("Try login without password: error displayed for no password");
        }
        else{
            System.out.println("Try login without password: error NOT displayed for no password");

        }
        driver.findElement(By.id("email")).clear();

        //try login without email
        driver.findElement(By.id("pass")).sendKeys("12345678");
        Thread.sleep(10000);
        driver.findElement(By.id("send2")).click();
        if(driver.findElement(By.id("email-error")).isDisplayed()){
            System.out.println("Try login without email: error displayed for no email");
        }
        else{
            System.out.println("Try login without email: error NOT displayed for no email");
        }
        driver.findElement(By.id("pass")).clear();

        //try login with wrong email and password
        driver.findElement(By.id("email")).sendKeys("nilni@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("12345");
        Thread.sleep(10000);
        driver.findElement(By.id("send2")).click();

        if(driver.findElement(By.id("message-error")).isDisplayed()){
            System.out.println("Try login with wrong credentials: error displayed");
        }
        else{
            System.out.println("Try login with wrong credentials: error NOT displayed");
        }
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("email")).clear();



        //try login with correct email and password
        driver.findElement(By.id("email")).sendKeys("williamjacob802@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("12345678");

        Thread.sleep(5000);

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".recaptcha-checkbox-border")).click();

        driver.findElement(By.id("send2")).click();

        //Thread.sleep(50000);

         */





        //manual login
        Thread.sleep(60000);

    }

    @Test(priority = 4)
    public void accountTest(){
        String targetText = "william jacob";
        String divText = driver.findElement(By.cssSelector(".box-information > div:nth-child(2) > p:nth-child(1)")).getText();
        if(divText.contains(targetText)){
            System.out.println("Username correct");
        }
        else{
            System.out.println("Username wrong");
        }

    }

    @Test(priority = 5)
    public void clearCartTest(){
        String targetText = "0";
        String cartText = driver.findElement(By.cssSelector(".counter-number")).getText();
        if(cartText.equals(targetText)){
            System.out.println("Cart empty");
        }
        else{
            driver.findElement(By.className("counter-number")).click();
            while(driver.findElement(By.cssSelector(".delete")).isDisplayed()){
                driver.findElement(By.cssSelector(".delete")).click();

                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
                WebElement element1 = wait1.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".action-primary")));
                if(driver.findElement(By.cssSelector(".action-primary")).isDisplayed()){
                    driver.findElement(By.cssSelector(".action-primary")).click();
                }
                /*
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
                WebElement element2 = wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".action-primary")));
                if(driver.findElement(By.cssSelector(".action-primary")).isDisplayed()){
                    driver.findElement(By.cssSelector(".action-primary")).click();
                }*/
                driver.findElement(By.className("counter-number")).click();
            }
        }
    }

    @Test(priority = 6)
    public void addProductTest() throws InterruptedException {
        driver.findElement(By.linkText("Shop all Products")).click();

        driver.findElement(By.cssSelector("li.item:nth-child(6) > div:nth-child(1) > div:nth-child(3) > strong:nth-child(1) > a:nth-child(1)")).click();
        String productName = driver.findElement(By.cssSelector(".page-title")).getText();
        String price = driver.findElement(By.cssSelector("#product-price-50 > span:nth-child(1)")).getText();
        Thread.sleep(5000);
        driver.findElement(By.id("product-addtocart-button")).click();

        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.action:nth-child(2)")).click();
        String targetProduct = driver.findElement(By.cssSelector(".odd > div:nth-child(1) > div:nth-child(2) > strong:nth-child(1) > a:nth-child(1)")).getText();
        String targetPrice = driver.findElement(By.cssSelector(".minicart-price > span:nth-child(1)")).getText();

        System.out.println("Product: " +productName + " " +price);
        System.out.println("Cart: " +targetProduct + " " +targetPrice);
        if(productName.contains(targetProduct)&&price.contains(targetPrice)){
            System.out.println("Product name and Price verified");
        }
        else{
            System.out.println("Product name and Price mismatch");
        }

    }

    @Test(priority = 7)
    public void checkoutTest() throws InterruptedException {
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        /*
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("MYT7E9D")));
         */

        Thread.sleep(5000);
        /*String fname = driver.findElement(By.cssSelector("#shipping-new-address-form > div:nth-child(1) > div:nth-child(2)")).getAttribute("value");
        String lname = driver.findElement(By.cssSelector("div._required:nth-child(2) > div:nth-child(2)")).getAttribute("value");

         */
        String fname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lname = driver.findElement(By.name("lastname")).getAttribute("value");

        if(fname.contains("william")&&lname.contains("jacob")){
            System.out.println("First and last name verified");
        }
        else{
            System.out.println("First and last name NOT verified");
        }
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button.button:nth-child(1)")).click();
        if(driver.findElement(By.className("field-error")).isDisplayed()){
            System.out.println("Press Next without filling mandatory fields: Error messages displayed");
        }
        else{
            System.out.println("Press Next without filling mandatory fields: Error messages NOT displayed");
        }

        //fill fields
        driver.findElement(By.name("guest-dob")).sendKeys("03/11/1997");
        driver.findElement(By.name("street[0]")).sendKeys("Main street");
        driver.findElement(By.name("guest-dob")).sendKeys("03/11/1997");
        Select drpRegion = new Select(driver.findElement(By.name("region_id")));
        drpRegion.selectByVisibleText("Victoria");

        driver.findElement(By.name("city")).sendKeys("Kansas");
        driver.findElement(By.name("postcode_autocomplete")).sendKeys("2000");

        Thread.sleep(10000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

        driver.findElement(By.name("telephone")).sendKeys("0752783543");

        driver.findElement(By.cssSelector("button.button:nth-child(1)")).click();
    }

    @Test(priority = 8)
    public void ccTest() throws InterruptedException {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement element1 = wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("braintree")));
        //driver.findElement(By.id("braintree")).click();
        driver.findElement(By.cssSelector("div.payment-method:nth-child(3) > div:nth-child(1) > label:nth-child(2) > span:nth-child(1)")).click();

        Thread.sleep(25000);
        //cc number too long
        driver.switchTo().frame("braintree-hosted-field-number");
        driver.findElement(By.cssSelector("#credit-card-number")).sendKeys("4111 1111 1111 1111111");

        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0).click().perform();

        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        if(driver.findElement(By.cssSelector("div.hosted-error:nth-child(3) > span:nth-child(1)")).isDisplayed()){
            System.out.println("Enter invalid cc number (long): Error message displayed");
        }
        else{
            System.out.println("Enter invalid cc number (long): Error message NOT displayed");
        }


        //cc number too short
        driver.switchTo().frame("braintree-hosted-field-number");
        driver.findElement(By.id("credit-card-number")).clear();
        driver.findElement(By.id("credit-card-number")).sendKeys("4111 1111 1111");
        Actions actions1 = new Actions(driver);
        actions1.moveByOffset(0, 0).click().perform();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        if(driver.findElement(By.cssSelector("div.hosted-error:nth-child(3) > span:nth-child(1)")).isDisplayed()){
            System.out.println("Enter invalid cc number (short): Error message displayed");
        }
        else{
            System.out.println("Enter invalid cc number (short): Error message NOT displayed");
        }


        //valid cc number
        driver.switchTo().frame("braintree-hosted-field-number");
        driver.findElement(By.id("credit-card-number")).clear();
        driver.findElement(By.id("credit-card-number")).sendKeys("4111 1111 1111 1111");

        driver.switchTo().defaultContent();

        // invalid expiration date
        Thread.sleep(3000);
        driver.switchTo().frame("braintree-hosted-field-expirationDate");
        driver.findElement(By.cssSelector("#expiration")).sendKeys("0322");
        Actions actions2 = new Actions(driver);
        actions2.moveByOffset(0, 0).click().perform();

        driver.switchTo().defaultContent();
        if(driver.findElement(By.cssSelector("div.number:nth-child(4) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)")).isDisplayed()){
            System.out.println("Enter invalid expiration date: Error message displayed");
        }
        else{
            System.out.println("Enter invalid expiration date: Error message NOT displayed");
        }

        driver.switchTo().frame("braintree-hosted-field-expirationDate");
        driver.findElement(By.cssSelector("#expiration")).clear();
        driver.findElement(By.cssSelector("#expiration")).sendKeys("0327");

    }


}
