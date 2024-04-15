package org.qtriptest.pages;

import org.qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    RemoteWebDriver driver;
    String register_url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGenerateUsername = "";

    // catch the username text
    @FindBy(id = "floatingInput")
    WebElement userEmail_textBox;

    // catch the password text
    @FindBy(xpath = "//input[@name='password']")
    WebElement userPassword_textBox;

    // catch the confirm password text
    @FindBy(xpath = "//input[@name='confirmpassword']")
    WebElement userConfirmPassowrd_textBox;

    // catch the register now button
    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registerNowButton;

    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;    
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

    }

    public void navigateToRegisterPage(){
        // if(!this.driver.getCurrentUrl().equals(this.register_url)){
        //     driver.get(register_url);
        // }
        SeleniumWrapper.navigate(driver, register_url);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean registerUser(String userName, String password, boolean generateRandomUsername ) throws InterruptedException{
        
        String dynamciUserEamil;
        if(generateRandomUsername){
            dynamciUserEamil = userName+UUID.randomUUID().toString();
        }else{
            dynamciUserEamil = userName;
        }

        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();

        seleniumWrapper.sendKey(userEmail_textBox, dynamciUserEamil);

        seleniumWrapper.sendKey(userPassword_textBox, password);

        seleniumWrapper.sendKey(userConfirmPassowrd_textBox, password);

        seleniumWrapper.click(registerNowButton, driver);
        // Thread.sleep(2000);
        try{
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.or(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login")));
        }catch(TimeoutException e){
            return false;
        }

        this.lastGenerateUsername = dynamciUserEamil;

        return this.driver.getCurrentUrl().endsWith("/login");
    }    
}

