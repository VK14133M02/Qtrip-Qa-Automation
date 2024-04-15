package org.qtriptest.tests;

import org.qtriptest.DP;
import org.qtriptest.DriverSingleton;
import org.qtriptest.ReportSingleton;
import org.qtriptest.pages.AdventurePage;
import org.qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

public class testCase_02 {
    static ChromeDriver driver;

    static ExtentReports report;
    static ExtentTest test;
    

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException{
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstance();

        ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonReport();
        report = reportSingleton.getReport();
        test = report.startTest("TestCase02");
    }

    // @Test(description = "TestCase02 find the city in searchbar", dataProvider = "data-provider", dataProviderClass = DP.class, groups = {"Search and Filter flow"}, priority = 2)
    @Parameters({"CityName","Category_Filter","DurationFilter","ExpectedFilteredResults","ExpectedUnFilteredResults"})
    public void TestCase02(String CityName,String Category_Filter,String DurationFilter,String ExpectedFilteredResults,String ExpectedUnFilteredResults) throws InterruptedException{
        boolean status = false;
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase02", "Start")));
        HomePage home = new HomePage(driver);
        home.navigateToHome();
        Thread.sleep(4000);            
        status = home.searchForCity(CityName);
        Assert.assertTrue(status,"Unable to get the city");        

        status = home.getSearchResult();
        Assert.assertTrue(status,"Unable to get the result");

        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.filterByCategory(Category_Filter);
        adventurePage.filterByHour(DurationFilter);
        int initialData = adventurePage.verifyAppropriateData();

        status = initialData == Integer.parseInt(ExpectedFilteredResults);
        Assert.assertTrue(status,"Not getting the appropriate data after filter");      

        // clear the filter for hour
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/div")).click();
        Thread.sleep(2000);

        //clear the filter for category
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div[1]/div[3]/div")).click();
        Thread.sleep(2000);

        int finalData = adventurePage.verifyAppropriateData();      
        status = finalData == Integer.parseInt(ExpectedUnFilteredResults);
        Assert.assertTrue(status,"Not Getting the appropriate data after clear filter");        
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase02", "PASS")));
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        report.flush();
    }
}
