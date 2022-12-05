package class04;

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

public class loginTest  {

    //    goto syntax HRMS
    //    verify that you get invalid credentials on entering wrong username

    WebDriver driver;
    @BeforeMethod
    public void launchTheWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void LoginWithInvalidCredentials() {
        WebElement username = driver.findElement(By.xpath("//input[@id='txtUsername']"));
        username.sendKeys("Admin");

        WebElement pswrd = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        pswrd.sendKeys("12346");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@id='btnLogin']"));
        loginBtn.click();

        WebElement errorMsg = driver.findElement(By.xpath("//span[@id='spanMessage']"));

        String actualError = errorMsg.getText();

        Assert.assertEquals(actualError, "Invalid credentials");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
