package org.qtriptest.tests;

import org.qtriptest.DP;
import org.qtriptest.DriverSingleton;
import org.qtriptest.ReportSingleton;
import org.qtriptest.pages.HomePage;
import org.qtriptest.pages.LoginPage;
import org.qtriptest.pages.RegisterPage;

import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;
import org.testng.Assert;

public class testCase_01 {
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
        test = report.startTest("TestCase01");
    }

    // @Test(description = "Verfy the authenticatio of user", dataProvider = "data-provider",dataProviderClass = DP.class, groups = {"Login Flow"}, priority = 1)
    @Parameters({"userName","password"})
    public void TestCase01(String userName, String password) throws InterruptedException{
        boolean status;
        System.out.println("Testcase 1 started, It will verify the authentication of user : Done");
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase01", "Start")));

        // Visit the Registration page and register a new user
        RegisterPage registration = new RegisterPage(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser(userName, password, true);
        Assert.assertTrue(status,"Not able to register the new user : Fail");

        lastGeneratedUserName = registration.lastGenerateUsername;

        // Visit the login page and login with the previuosly registered user
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        status = login.loginUser(lastGeneratedUserName, password);
        Assert.assertTrue(status,"Not able to Login the register user : Fail");

        // Visit the home page and log out the logged in user
        HomePage home = new HomePage(driver);
        status = home.PerformLogout();
        Assert.assertTrue(status,"Not able to Logout the user : Fail");
        test.log(LogStatus.INFO, test.addScreenCapture(ReportSingleton.takeScreenshot(driver, "TestCase01", "PASS")));

        System.out.println("Test case 1 passed successfully : Pass");
    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
        report.endTest(test);
        report.flush();
    }
}
