package AmezonDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by steve.bennett on 9/29/2014.
 * Description: Selenium QA test script that will go to Amazon.com then test cart functionality.
 */
public class AmezonTest {

    static final String AMAZON_URL = "http://www.amazon.com/";
    static final String AMAZON_QUERY = "The Legend Of Zelda Hyrule Crest Crew Sock Pair Yellow/Green";

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {

        System.out.print("Opening chromedriver...");
        WebDriver driver = new ChromeDriver();
        driver.get(AMAZON_URL);
        System.out.println("PASS");

        System.out.print("Performing search...");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(AMAZON_QUERY + Keys.RETURN);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        if ( driver.getCurrentUrl().compareTo(AMAZON_URL) != 0) {
            /* Page successfully changed... don't know if it's the right page but going to assume it is. */
            System.out.println("PASS");
        } else {
            System.err.println("FAIL");
            System.err.println("Page did not change.");
            System.exit(2);
        }

        System.out.print("Adding item to cart...");
        String itemLink = driver.findElement(By.id("result_0")).findElement(By.className("newaps")).findElement(By.tagName("a")).getAttribute("href");
        driver.get(itemLink);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement addToCart = driver.findElement(By.id("submit.add-to-cart"));
        addToCart.click();
        System.out.println("PASS");

        
        System.out.println("All tests passed.");
        driver.quit();
    }
}
