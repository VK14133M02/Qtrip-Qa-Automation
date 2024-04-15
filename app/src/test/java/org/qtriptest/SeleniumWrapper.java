package org.qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


public class SeleniumWrapper {
    public boolean click(WebElement elementToClick, WebDriver driver){
        if(elementToClick.isDisplayed()){
            try{
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
                Thread.sleep(1000);
                elementToClick.click();
                Thread.sleep(1000);
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public boolean sendKey(WebElement inputBox, String keysToSend){
        if(inputBox.isDisplayed()){
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        }
        return false;
    }

    public static boolean navigate(WebDriver driver, String url){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
            return true;
        }
        return true;
    }

    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount) throws Exception{
        int count = 0;
        Exception ex = new Exception();
        while(count<= retryCount){
            try{
                return driver.findElement(by);
            }catch(Exception e){
                count++;
                ex = e;
            }
        }
        throw new Exception(ex.getCause());                
    }

}
