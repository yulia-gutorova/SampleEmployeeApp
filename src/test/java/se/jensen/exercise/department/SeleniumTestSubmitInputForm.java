package se.jensen.exercise.department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

public class SeleniumTestSubmitInputForm extends SeleniumSetUppClass{

    final String SITE_URL = "https://www.seleniumeasy.com/test/input-form-demo.html";

    @Test
    public void a_openPage()
    {
        driver.get(SITE_URL);
        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
        sleep(1000);

        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    public void submitInputForm()
    {

        WebElement states = driver.findElement(By.name("state"));
        Select select = new Select(states);
        List <WebElement> nameOfStates = select.getOptions();

        List <WebElement> radioButton = driver.findElements(By.xpath("//div[@class='col-md-4']"));

        driver.findElement(By.name("first_name")).sendKeys("FirstName");
        sleep(1000);
        driver.findElement(By.name("last_name")).sendKeys("LastName");
        sleep(1000);
        driver.findElement(By.name("email")).sendKeys("mymail@gmail.com");
        sleep(1000);
        driver.findElement(By.name("phone")).sendKeys("76584653865");
        sleep(1000);
        driver.findElement(By.name("address")).sendKeys("myAddress");
        sleep(1000);
        driver.findElement(By.name("city")).sendKeys("New York");
        sleep(1000);

        select.selectByVisibleText("Alabama");
        sleep(1000);

        driver.findElement(By.name("zip")).sendKeys("123456");
        sleep(1000);
        driver.findElement(By.name("website")).sendKeys("myWebsite");
        sleep(1000);

        driver.findElement(By.xpath("//input[@type='radio']")).click();
        sleep(1000);

        driver.findElement(By.name("comment")).sendKeys("MyComment");
        sleep(1000);

        driver.findElement(By.xpath("//button[@type='submit']")).submit();
        sleep(1000);

    }
}
