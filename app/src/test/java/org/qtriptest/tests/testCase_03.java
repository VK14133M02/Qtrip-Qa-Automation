package org.qtriptest.tests;

import org.qtriptest.DP;
import org.qtriptest.ReportSingleton;
import org.qtriptest.DriverSingleton;
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

public class testCase_03 {
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
        test = report.startTest("TestCase03");
    }

    @Test(description = "TestCase03 Book an Adventure",dataProvider = "data-provider", dataProviderClass = DP.class, groups = {"Booking and Cancellation Flow"}, priority = 3)   
    @Parameters({"NewUserName","Password","SearchCity","AdventureName","GuestName","Date","count"})
    public void TestCase03(String NewUserName,String Password,String SearchCity,String AdventureName,String GuestName,String Date, String count) throws InterruptedException{
        boolean status = false;
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase03", "Start")));

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
        
        HomePage home = new HomePage(driver);
        home.navigateToHome();
        Thread.sleep(4000);            
        status = home.searchForCity(SearchCity);
        Assert.assertTrue(status,"Unable to get the city");        

        status = home.getSearchResult();
        Assert.assertTrue(status,"Unable to get the result");

        AdventurePage adventurePage = new AdventurePage(driver);   
        adventurePage.searchAdventure(AdventureName);     
        
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        adventureDetailsPage.BookAvbenture(GuestName, Date, Integer.parseInt(count));
        status = adventureDetailsPage.verifyResirvation();
        Assert.assertTrue(status,"Unable to print the success message : Fail");
        adventureDetailsPage.clickOnReservationsLink();

        HistoryPage historyPage = new HistoryPage(driver);
        String transectionNo = historyPage.getTransactionNumber();
        System.out.println(transectionNo);
        historyPage.cancelRegistration();
        driver.navigate().refresh();
        Thread.sleep(4000);
        status = historyPage.verifyTransecrionIdRemoved();
        Assert.assertTrue(status,"Unable to handle noReservationPopup : Fail");
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase03", "PASS")));

    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        report.flush();
    }
}
