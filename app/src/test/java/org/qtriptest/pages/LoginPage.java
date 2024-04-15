package org.qtriptest.pages;

import org.qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    RemoteWebDriver driver;
    String login_url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";

    // catch the username text
    @FindBy(id = "floatingInput")
    WebElement userEmail_textBox;

    // catch the password text
    @FindBy(id = "floatingPassword")
    WebElement userPassword_textBox;
    
    // catch the login now button
    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement loginButton;

    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;       
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToLoginPage(){
        // if(this.driver.getCurrentUrl().equals(this.login_url)){
        //     this.driver.get(this.login_url);
        // }
        SeleniumWrapper.navigate(driver, login_url);
    }

    public boolean loginUser(String userName, String password) throws InterruptedException{
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();

        seleniumWrapper.sendKey(userEmail_textBox, userName);
        // userEmail_textBox.sendKeys(userName);

        // seleniumWrapper.sendKey(userPassword_textBox, password);
        userPassword_textBox.sendKeys(password);

        seleniumWrapper.click(loginButton, driver);
        // loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"));
        // Thread.sleep(3000);

        return this.verifyUserLoggedIn();
    }

    public boolean verifyUserLoggedIn(){
        try{
            WebElement logoutButton = driver.findElement(By.xpath("//div[text()='Logout']"));
            if(logoutButton.isDisplayed()){
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println("Error in find logout button");
            return false;
        }
    }    
}
