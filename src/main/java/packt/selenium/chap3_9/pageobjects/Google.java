package packt.selenium.chap3_9.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Created by Ripon on 11/19/2015.
 */
public class Google {
    private WebDriver driver;
    private String baseURL;

    public Google(WebDriver driver){
        this.driver = driver;
        baseURL = "http://www.google.com/";
        driver.get(baseURL);
        //driver.get(baseURL + "?gws_rd=cr,ssl&ei=qZlNVpOUMNCauQS0iYmoCA&fg=1");
        System.out.println(driver.getTitle());
        if (!driver.getTitle().equals("Google")){
            throw new WrongPageException("Incorrect page for Google Home page");
        }
    }
    public GoogleSearchPage goToSearchPage(){
        driver.findElement(By.id("lst-ib")).sendKeys("Mastering Selenium Testing Tools");
        //WebDriverWait wait1 = new WebDriverWait(driver, 30);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", driver.findElement(By.name("btnK")));
        //driver.findElement(By.name("btnK")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        return new GoogleSearchPage(driver);
    }
}