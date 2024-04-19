package org.qtriptest.tests;

import org.qtriptest.DP;
import org.qtriptest.DriverSingleton;
import org.qtriptest.ReportSingleton;
import org.qtriptest.pages.AdventureDetailsPage;
import org.qtriptest.pages.AdventurePage;
import org.qtriptest.pages.HistoryPage;
import org.qtriptest.pages.HomePage;
import org.qtriptest.pages.LoginPage;
import org.qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;
import org.testng.Assert;

public class testCase_04 {
    static ChromeDriver driver;
    public static String lastGeneratedUserName;    
    static ExtentReports report;
    static ExtentTest test;  

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException{
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstance();

        ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonReport();
        report = reportSingleton.getReport();
        test = report.startTest("TestCase04");
    }

    @Test(description = "TestCase04 Book multiple Adventure",dataProvider = "data-provider", dataProviderClass = DP.class, groups = {"Reliability Flow"}, priority = 4)   
    @Parameters({"NewUserName","Password","dataset1","dataset2","dataset3"})
    public void TestCase04(String NewUserName,String Password,String dataset1,String dataset2,String dataset3) throws InterruptedException{
        boolean status = false;
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase04", "Start")));

        String[] DS1 = dataset1.split(";"); String[] DS2 = dataset2.split(";"); String[] DS3 = dataset3.split(";");
        RegisterPage registration = new RegisterPage(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser(NewUserName, Password, true);
        Assert.assertTrue(status,"Not able to register the new user : Fail");

        lastGeneratedUserName = registration.lastGenerateUsername;

        // Visit the login page and login with the previuosly registered user
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        status = login.loginUser(lastGeneratedUserName, Password);
        Assert.assertTrue(status,"Not able to Login the register user : Fail");
        
        // 1
        HomePage home = new HomePage(driver);
        home.navigateToHome();
        Thread.sleep(4000);            
        status = home.searchForCity(DS1[0]);
        Assert.assertTrue(status,"Unable to get the city");        

        status = home.getSearchResult();
        Assert.assertTrue(status,"Unable to get the result");

        AdventurePage adventurePage = new AdventurePage(driver);   
        adventurePage.searchAdventure(DS1[1]);     
        
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        String people1 = DS1[4];
        adventureDetailsPage.BookAvbenture(DS1[2], DS1[3], Integer.parseInt(people1));
        status = adventureDetailsPage.verifyResirvation();
        Assert.assertTrue(status,"Unable to print the success message : Fail");

        //2
        home.navigateToHome();
        Thread.sleep(4000);            
        status = home.searchForCity(DS2[0]);
        Assert.assertTrue(status,"Unable to get the city");       
        status = home.getSearchResult();
        Assert.assertTrue(status,"Unable to get the result");
        adventurePage.searchAdventure(DS2[1]);            
        String people2 = DS2[4];
        adventureDetailsPage.BookAvbenture(DS2[2], DS2[3], Integer.parseInt(people2));
        status = adventureDetailsPage.verifyResirvation();
        Assert.assertTrue(status,"Unable to print the success message : Fail");

        //3
        home.navigateToHome();
        Thread.sleep(4000);            
        status = home.searchForCity(DS3[0]);
        Assert.assertTrue(status,"Unable to get the city");       
        status = home.getSearchResult();
        Assert.assertTrue(status,"Unable to get the result");
        adventurePage.searchAdventure(DS3[1]);            
        String people3 = DS3[4];
        adventureDetailsPage.BookAvbenture(DS3[2], DS3[3], Integer.parseInt(people3));
        status = adventureDetailsPage.verifyResirvation();
        Assert.assertTrue(status,"Unable to print the success message : Fail");

        adventureDetailsPage.clickOnReservationsLink();
        Thread.sleep(5000);

        HistoryPage historyPage = new HistoryPage(driver);  
        int numberOfBooking =  historyPage.verifyNumberOfBooking();
        Assert.assertEquals(numberOfBooking, 3,"The number of Booking is not sufficient");
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase04", "PASS")));

    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        report.flush();
    }
}
