package org.qtriptest.pages;

import org.qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;

    @FindBy(id = "search-adventures") WebElement searchAdventure_textBox;

    @FindBy(className = "mb-4") WebElement adventureDiv;

    

    public AdventurePage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean filterByHour(String hour){
        try{
            WebElement filterHour = driver.findElement(By.id("duration-select"));
            Select dropdown = new Select(filterHour);
            dropdown.selectByVisibleText(hour);
            Thread.sleep(3000);
            return true;           
        }catch(Exception e){
            System.out.println("Error while filter in hour");
            return false;
        }
    }

    public boolean filterByCategory(String category){
        try{
            WebElement filterCategory = driver.findElement(By.id("category-select"));
            Select dropdown = new Select(filterCategory);
            dropdown.selectByVisibleText(category);
            Thread.sleep(3000);
            return true;
        }catch(Exception e){
            System.out.println("Error while filter in hour");
            return false;
        }
    }

    public int verifyAppropriateData(){       
        List<WebElement> elements = driver.findElements(By.className("mb-4"));
        return elements.size();    
    }

    public boolean searchAdventure(String advanture){
        SeleniumWrapper seleniumWrapper = new SeleniumWrapper();
        try{
            seleniumWrapper.sendKey(searchAdventure_textBox, advanture);
            // searchAdventure_textBox.sendKeys(advanture);
            Thread.sleep(2000);
            if(adventureDiv.isDisplayed()){      
                seleniumWrapper.click(adventureDiv, driver);          
                // adventureDiv.click();
                Thread.sleep(2000);
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println("The advanture is not found");
            return false;
        }
    }
}