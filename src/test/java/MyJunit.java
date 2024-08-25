import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Get website title")
    @Test
    public void getTitle() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String title = driver.getTitle();
        String titleActual= driver.getTitle();
        System.out.println(titleActual);
        String titleExpected="Practice webform for learners | Digital Unite";
        Assertions.assertTrue(titleActual.contains(titleExpected));

    }

    @Test
    public void practiceWebform() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        WebElement cookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookies.click();

        //name
        WebElement name = driver.findElement(By.id("edit-name"));
        name.click();
        name.sendKeys("Subrina");

        //number
        WebElement number = driver.findElement(By.id("edit-number"));
        number.click();
        number.sendKeys("01688357697");

        //Today's Date
        WebElement date = driver.findElement(By.id("edit-date"));
        date.click();
        date.sendKeys("8/21/2024");

        //Email
        WebElement email = driver.findElement(By.id("edit-email"));
        email.click();
        email.sendKeys("subrina@gmail.com");

        //About yourself
        WebElement yourself = driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-"));
        yourself.sendKeys("This is Subrina. I love Dancing and Traveling");

        //upload
        WebElement upload = driver.findElement(By.id("edit-uploadocument-upload"));
        //upload.click();
        upload.sendKeys(System.getProperty("user.dir") + "/src/test/resources/flower.jpg");
        Thread.sleep(1200);

        //checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement termsCheckbox = driver.findElement(By.id("edit-age"));
        js.executeScript("arguments[0].click();", termsCheckbox);

        //submit
        WebElement submit = driver.findElement(By.id("edit-submit"));
        Actions actions = new Actions(driver);
        actions.click(submit).perform();

        WebElement resultElem = driver.findElement(By.tagName("h1"));
        String actualMessage = resultElem.getText();
        String messageExpected = "Thank you for your submission!";
        Assertions.assertTrue(actualMessage.contains(messageExpected));

    }

        }



