package se.jensen.exercise.department;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;

import java.time.Duration;
import java.util.Collections;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

public class SeleniumTestInputForms {

    private static ConfigurableApplicationContext applicationContext;


    String SITE_URL = "https://www.seleniumeasy.com/test/basic-first-form-demo.html";
    public static  WebDriver driver;
    public WebDriverWait wait;

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @BeforeAll
    public static void setUpp()
    {
        String[] args = {};
        SpringApplication app = new SpringApplication(RestServiceApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.setLogStartupInfo(false);
        applicationContext = app.run(args);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        sleep(1000);
    }

    @AfterAll
    public static void tearDown()
    {
        driver.quit();
        SpringApplication.exit(applicationContext);
    }

    @Test
    @DisplayName("Open a website")
    public void a_openPage()
    {
        driver.get(SITE_URL);
        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
        sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement close = driver.findElement(By.id("at-cv-lightbox-close"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("at-cv-lightbox-close")));
        close.click();
        sleep(1000);

        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test that the logotype of the website and the sponsor logo  are present")
    public void b_isPresent()
    {
       WebElement seleniumEasyPicture =  driver.findElement(By.xpath("//img[@role='presentation']"));
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement sponsoredBy =  driver.findElement(By.xpath("//img[@class='cbt']"));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//img[@class='cbt']"))));

        Assertions.assertTrue(seleniumEasyPicture.isDisplayed());
        Assertions.assertTrue(sponsoredBy.isDisplayed());
    }

    @Test
    @DisplayName("Enter a single message")
    public void c_enterMessage()
    {
        WebElement enterMessage = driver.findElement(By.xpath("//input[@placeholder='Please enter your Message']"));
        enterMessage.sendKeys("abc");
        sleep(1000);

        WebElement showMessage = driver.findElement(By.xpath("//button[@onclick='showInput();']"));
        showMessage.click();
        sleep(1000);

        WebElement yourMessage = driver.findElement(By.id("display"));

        Assertions.assertEquals("abc", yourMessage.getText());

    }

    @Test
    @DisplayName("Enter two messages")
    public void d_enterValues()
    {
        WebElement enterValueA = driver.findElement(By.id(("sum1")));
        enterValueA.sendKeys("1");
        sleep(1000);

        WebElement enterValueB = driver.findElement(By.id(("sum2")));
        enterValueB.sendKeys("2");
        sleep(1000);

        WebElement getTotal = driver.findElement(By.xpath("//button[@onclick='return total()']"));
        getTotal.click();
        sleep(1000);

        WebElement total = driver.findElement(By.id("displayvalue"));
        Assertions.assertEquals("3", total.getText());
    }

}
