package se.jensen.exercise.department;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;

import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.jensen.RestServiceApplication;

import java.util.Collections;
import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

public class SeleniumTestRadioButton {

    private static ConfigurableApplicationContext applicationContext;


    String SITE_URL = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
    public static WebDriver driver;
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
    public void a_openPage()
    {
        driver.get(SITE_URL);
        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
        sleep(1000);

        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("")
    public void b_clickOnRadioButton()
    {
        String str;
        List<WebElement> radioButtons1 = driver.findElements(By.xpath("//input[@name='optradio']"));

            for (int i = 0; i < radioButtons1.size(); i++)
            {
                radioButtons1.get(i).click();
                driver.findElement(By.id("buttoncheck")).click();
                sleep(2000);
                switch (radioButtons1.get(i).getAttribute("value"))
                    {
                        case "Male":
                            Assertions.assertTrue(driver.findElement(By.cssSelector(".radiobutton")).getText().contains("Radio button 'Male' is checked"));
                            break;
                        case "Female":
                            Assertions.assertTrue(driver.findElement(By.cssSelector(".radiobutton")).getText().contains("Radio button 'Female' is checked"));
                            break;
                    }
            }
    }

    @Test
    public void b_clickOnGroupsRadioButton()
    {

    }

}
