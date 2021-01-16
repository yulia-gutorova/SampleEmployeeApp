package se.jensen.exercise.department;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)

public class SeleniumTestRadioButton extends SeleniumSetUppClass{

    String SITE_URL = "https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
    Integer i,j;

    @Test
    public void a_openPage()
    {
        driver.get(SITE_URL);
        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
        sleep(1000);

        Assertions.assertEquals(SITE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Radio Button Demo - Click on button to get the selected value")
    public void b_clickOnRadioButton()
    {
        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@name='optradio']"));

            for (int i = 0; i < radioButtons.size(); i++)
            {
                radioButtons.get(i).click();
                driver.findElement(By.id("buttoncheck")).click();
                sleep(2000);
                switch (radioButtons.get(i).getAttribute("value"))
                    {
                        case "Male":
                            Assertions.assertTrue(driver.findElement(By.xpath("//p[@class='radiobutton']")).getText().contains("Radio button 'Male' is checked"));
                            break;
                        case "Female":
                            Assertions.assertTrue(driver.findElement(By.xpath("//p[@class='radiobutton']")).getText().contains("Radio button 'Female' is checked"));
                            break;
                    }
            }
    }

    @Test

    @DisplayName("Group Radio Buttons Demo - Click on button to get the selected value from group")
    public void c_clickOnGroupsRadioButton()
    {
        List <WebElement> sex = driver.findElements(By.xpath("//input[@name='gender']"));
        List <WebElement> ageGroup = driver.findElements(By.xpath("//input[@name='ageGroup']"));

        for ( i =0; i< sex.size(); i++)
        {
            sex.get(i).click();
            sleep(2000);

           for ( j=0; j<ageGroup.size(); j++)
           {
                ageGroup.get(j).click();
                sleep(2000);
                driver.findElement(By.xpath("//button[@onclick='getValues();']")).click();
                sleep(2000);
                WebElement groupradiobutton = driver.findElement(By.xpath("//p[@class='groupradiobutton']"));
                groupradiobutton.click();

               if (sex.get(i).getAttribute("value").equals("Male"))
                {
                    switch (ageGroup.get(j).getAttribute("value"))
                    {
                        case "0 - 5":
                            Assertions.assertTrue(groupradiobutton.getText().contains("Sex : Male\n" + "Age group: 0 - 5"));
                            break;
                        case "5 - 15":
                            Assertions.assertTrue(groupradiobutton.getText().contains("Sex : Male\n" + "Age group: 5 - 15"));
                            break;

                        case "15 - 50":
                            Assertions.assertTrue(groupradiobutton.getText().contains("Sex : Male\n" + "Age group: 15 - 50"));
                            break;
                    }
                }
               else
               {
                   switch (ageGroup.get(j).getAttribute("value"))
                   {
                       case "0 - 5":
                           Assertions.assertTrue(groupradiobutton.getText().contains("Sex : Female\n" + "Age group: 0 - 5"));
                           break;
                       case "5 - 15":
                           Assertions.assertTrue(groupradiobutton.getText().contains("Sex : Female\n" + "Age group: 5 - 15"));
                           break;

                       case "15 - 50":
                           Assertions.assertTrue(groupradiobutton.getText().contains("Sex : Female\n" + "Age group: 15 - 50"));
                           break;
                   }
               }
           }
        }
    }



}
