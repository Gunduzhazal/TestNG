package class02;

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

public class HardAssertions {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void launchTheWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(groups = "regression")
    public void invalidCredentials(){

        WebElement userName = driver.findElement(By.xpath("//input[@name='txtUsername']"));
        userName.sendKeys("admin");

        WebElement password = driver.findElement(By.xpath("//input[@id = 'txtPassword']"));
        password.sendKeys("abracadabra");

        WebElement logIn = driver.findElement(By.xpath("//*[@id='btnLogin']"));
        logIn.click();

        WebElement errorMSG = driver.findElement(By.xpath("//span[text()='Invalid credentials']"));
        String ActualErrorMsg = errorMSG.getText();
        String ExpectedErrorMsg="Invalid credentials";

        Assert.assertEquals(ActualErrorMsg,ExpectedErrorMsg);

        System.out.println("i am here after 1st assertion");

        boolean isDisplayed = errorMSG.isDisplayed();
//      assertion will pass if the parameter boolean is "true" will fail if the parameter boolean is false
        Assert.assertTrue(isDisplayed);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
    }

    }
