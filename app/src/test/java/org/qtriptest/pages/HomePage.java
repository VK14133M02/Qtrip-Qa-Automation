package org.qtriptest.pages;

import org.qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;
    String homePageUrl = "https://qtripdynamic-qa-frontend.vercel.app/";

    // Find Logout button
    @FindBy(xpath = "//div[text()='Logout']") WebElement logoutButton;

    // Find search for ciry textbox
    @FindBy(id = "autocomplete") WebElement searchCity_TextBox;

    
    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToHome() {
        // if (!this.driver.getCurrentUrl().equals(this.homePageUrl)) {
        //     this.driver.get(this.homePageUrl);
        // }
        SeleniumWrapper.navigate(driver, homePageUrl);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public Boolean PerformLogout() throws InterruptedException {
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
        try {
            // Find and click on the Logout Button
            if(logoutButton.isDisplayed()){
                seleniumWrapper.click(logoutButton,driver);
                // logoutButton.click();
                // SLEEP_STMT_10: Wait for Logout to complete
                Thread.sleep(3000);
                return true;
            }           

            return false;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    public boolean searchForCity(String city) throws InterruptedException{
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
        try{
            seleniumWrapper.sendKey(searchCity_TextBox,city);
            // searchCity_TextBox.sendKeys(city);
            Thread.sleep(4000);            
            return true;
        }catch(Exception e){
            System.out.println("Unable to find the city");
            return false;
        }        
    }

    public boolean getSearchResult(){
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();        
        try{
            WebElement cityResult = driver.findElement(By.xpath("//ul/a/li"));            
            System.out.println(cityResult.getText());
            if(cityResult.isDisplayed()){
                seleniumWrapper.click(cityResult, driver);
                // cityResult.click();
                Thread.sleep(3000);
                return true;
            }
            return false;
        }catch(Exception e){
            WebElement noResultFound = driver.findElement(By.xpath("//ul/h5"));
            System.out.println(noResultFound.getText());
            return true;
        }
    }  
}
