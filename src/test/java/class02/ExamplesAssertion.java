package class02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class ExamplesAssertion {

    //    goto syntax hrms
    //    verify that login btn is dislayed
    //    verify that login btn is Enabled
    //    close the browser

    WebDriver driver;
    @BeforeMethod
    public void launchTheWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void LoginBtnIsDisplayed() {
        WebElement logIn = driver.findElement(By.xpath("//*[@id='btnLogin']"));
        boolean isdisplayed = logIn.isDisplayed();

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(isdisplayed);

        boolean isenabled = logIn.isEnabled();
        soft.assertTrue(isenabled);

        soft.assertAll();
    }

    @Test
    public void testing() {
        System.out.println("I am a test2");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

}
