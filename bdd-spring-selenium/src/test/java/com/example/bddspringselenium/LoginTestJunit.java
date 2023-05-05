/*
 * bdd-spring-selenium
 * LoginTestJunit
 * @since 05/05/2023
 */
package com.example.bddspringselenium;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Slf4j
@Tag("E2ETest")
public class LoginTestJunit {


    static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        log.info("setUp");
        // load the driver
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/drives/linux_64/chromedriver").toString());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }



    @Test
    void testLogin(){

        // Launch website
        driver.navigate().to("http://localhost:8080");

        // Click on the search text box and send value
        driver.findElement(By.id("email")).sendKeys("teste@teste.imd.ufrn.br");

        driver.findElement(By.id("password")).sendKeys("123456");

        // Click on the search button
        driver.findElement(By.name("loginButton")).click();

        // should have <h1> element with "home page" text and a <p> with the logged user email
        Assertions.assertEquals("Home Page", driver.findElement(By.tagName("h1")).getText());
        Assertions.assertTrue(driver.findElement(By.tagName("p")).getText().contains("teste@teste.imd.ufrn.br"));
    }

    @AfterAll
    public static void tearDown(){
        log.info("tearDown");
        driver.close();
        driver.quit();
    }
}
