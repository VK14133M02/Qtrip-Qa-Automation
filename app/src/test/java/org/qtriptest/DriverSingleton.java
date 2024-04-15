package org.qtriptest;

import java.net.MalformedURLException;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSingleton {
    static ChromeDriver driver;
    public static void createDriver() throws MalformedURLException {
         // This line creates a new instance of ChromeDriver in each test class        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    public ChromeDriver getInstance() throws MalformedURLException{
        if(driver==null){
            createDriver();
        }
        return driver;
    }
}