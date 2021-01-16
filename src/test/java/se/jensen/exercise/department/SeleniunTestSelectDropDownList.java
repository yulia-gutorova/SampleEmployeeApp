package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
@TestMethodOrder(MethodOrderer.Alphanumeric.class)

public class SeleniunTestSelectDropDownList extends SeleniumSetUppClass{

    String  SITE_URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    @Test
    @DisplayName("Open a page")
    public void a_openPage()
    {
        driver.get(SITE_URL);
        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
        sleep(1000);

        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    @Disabled
    @DisplayName("Select value from drop down list")
    public void b_selectListDemo()
    {
        WebElement dropDownList = driver.findElement(By.id("select-demo"));
        Select select = new Select(dropDownList);
        List <WebElement> options = select.getOptions();
        System.out.println("Options");

        for (int i = 1; i < options.size(); i++ )
            {
                select.selectByIndex(i);
                sleep(1000);

                String option = options.get(i).getText();
                String expectedString = "Day selected :- " + option;
                String actualString = driver.findElement(By.xpath("//p[@class='selected-value']")).getText();
                Assert.assertEquals(expectedString, actualString);

            }
    }

    @Test
    @DisplayName("Multi Select List Demo")
    public void c_selectListDemo()
    {
        System.out.println("Multi Select List Demo");
        WebElement multiSelectList = driver.findElement(By.id("multi-select"));
        Select select = new Select(multiSelectList);
        List <WebElement> values = select.getOptions();

        for (int i = 0; i < values.size(); i++ )
        {
            select.selectByIndex(i);
            sleep(1000);

            String option = values.get(i).getText();

            driver.findElement(By.xpath("//button[@value='Print First']")).click();
            sleep(1000);

            String expectedStringFirstSelected = "First selected option is : " + option;
            String actualStringFirstSelected = driver.findElement(By.xpath("//p[@class='getall-selected']")).getText();
            sleep(1000);

            driver.findElement(By.xpath("//button[@value='Print All']")).click();
            sleep(1000);

            String expectedStringGetAllSelected = "Options selected are : " + option;
            String actualStringGetAllSelected  = driver.findElement(By.xpath("//p[@class='getall-selected']")).getText();
            sleep(1000);

            Assert.assertEquals(expectedStringFirstSelected, actualStringFirstSelected);
            Assert.assertEquals(expectedStringGetAllSelected, actualStringGetAllSelected);
        }


    }
}
