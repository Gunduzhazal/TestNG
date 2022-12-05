package Review01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HardAssertion {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void launchTheWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void invalidCredentials() {
        WebElement userName = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        userName.sendKeys("admin");

        WebElement password = driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        password.sendKeys("abracadabra");

        WebElement logIn = driver.findElement(By.xpath("//*[@id='btnLogin']"));
        logIn.click();

        WebElement errorMSG = driver.findElement(By.xpath("//span[text()='Invalid credentials']"));

        String ActualErrorMsg = errorMSG.getText();
        String ExpectedErrorMsg = "Invalid credentials";

        Assert.assertEquals(ActualErrorMsg, ExpectedErrorMsg);
        System.out.println("I am here after 1st assertion");

        boolean isDisplayed = errorMSG.isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
