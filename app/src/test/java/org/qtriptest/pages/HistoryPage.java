
package org.qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    RemoteWebDriver driver;

    @FindBy(xpath = "//table/tbody/tr") List<WebElement> tbody;

    @FindBy(id = "no-reservation-banner") WebElement noReservationPopup;

    public HistoryPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public String getTransactionNumber(){
        String transectionNumber = null;
        transectionNumber = tbody.get(tbody.size()-1).findElement(By.tagName("th")).getText();
        return transectionNumber;
    }

    public void cancelRegistration(){
        try{
            tbody.get(tbody.size()-1).findElement(By.className("cancel-button")).click();
            Thread.sleep(2000);
        }catch(Exception e){
            System.out.println("It is not able get the cancel button");
        }
    }

    public boolean verifyTransecrionIdRemoved(){       
        try{
            if(noReservationPopup.isDisplayed()){
                if(noReservationPopup.getText().contains("Oops! You have not made any reservations yet!")){
                    return true;
                }
            }                                  
            return false;
        }catch(Exception e){
            System.out.println("Unable to handle noReservationPopup");
            return false;
        }
    }

    public int verifyNumberOfBooking(){
        return tbody.size();
    }
}