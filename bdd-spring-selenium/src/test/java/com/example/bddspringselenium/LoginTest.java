/*
 * bdd-spring-selenium
 * LoginTest
 * @since 04/05/2023
 */
package com.example.bddspringselenium;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;

/**
 * First test with selenium web driver
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class LoginTest {
    public static void main(String[] args) {

        // load the driver
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/drives/linux_64/chromedriver").toString());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver=new ChromeDriver(options);

        // Launch website
        driver.navigate().to("http://localhost:8080");

        // Click on the search text box and send value
        driver.findElement(By.id("email")).sendKeys("teste@teste.imd.ufrn.br");

        driver.findElement(By.id("password")).sendKeys("123456");

        // Click on the search button
        driver.findElement(By.name("loginButton")).click();

        // should have <h1> element with home page text
        Assertions.assertEquals("Home Page", driver.findElement(By.tagName("h1")).getText());
        Assertions.assertTrue(driver.findElement(By.tagName("p")).getText().contains("teste@teste.imd.ufrn.br"));

        driver.close();
        driver.quit();
    }
}
