package org.qtriptest.pages;

import org.qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {
    RemoteWebDriver driver;
    @FindBy(xpath = "//input[@name='name']") WebElement name_textBox;
    @FindBy(xpath = "//input[@name='date']") WebElement date_textBox;
    @FindBy(xpath = "//input[@name='person']") WebElement numberOfPerson_textBox;
    @FindBy(xpath = "//button[text()='Reserve']") WebElement reserverButton;
    @FindBy(id = "reserved-banner") WebElement successPopup;
    @FindBy(linkText = "Reservations") WebElement reservationsButton;

    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean BookAvbenture(String name,String date,int numberOfPeople){
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
        try{            
            seleniumWrapper.sendKey(name_textBox, name);
            // name_textBox.sendKeys(name);

            seleniumWrapper.sendKey(date_textBox, date);
            // date_textBox.sendKeys(date);

            seleniumWrapper.sendKey(numberOfPerson_textBox, String.valueOf(numberOfPeople));
            // numberOfPerson_textBox.sendKeys(String.valueOf(numberOfPeople));

            seleniumWrapper.click(reserverButton, driver);
            // reserverButton.click();
            Thread.sleep(2000);
            return true;
        }catch(Exception e){
            System.out.println("Unable to fill the correct data");
            return false;
        }
    }

    public boolean verifyResirvation(){        
        try{
            if(successPopup.isDisplayed()){
                if(successPopup.getText().contains("Greetings! Reservation for this adventure is successful.")){
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            System.out.println("Unable to print the success message");
            return false;
        }
    }

    public void clickOnReservationsLink(){
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
        try{
            seleniumWrapper.click(reservationsButton, driver);
            // reservationsButton.click();
            Thread.sleep(2000);            
        }catch(Exception e){
            System.out.println("Unable to find the Reservations button");            
        }
    }
}
