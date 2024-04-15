# Qtrip-Qa-Automation

Testing the dummy e-travel website using selenium TestNG, POM, ExtentReports framework.

## About the Project

> **_The script is designed to evaluate the functionality of a dummy e-travle website. The script performs various tests, checking functionalities, and generate the report to store the results of Sanity and Regression testing in an emailable format._**

## Features

- Registration and login functionality testing
- Search box functionality testing
- Reservation functionality testing
- History functionality testing
- Filter functionality testing

# Setup

This project requires the following software and dependencies:

- **Java JDK 17.x.x:** Ensure you have Java Development Kit version 17 or above installed. You can download it [here](https://www.oracle.com/java/technologies/javase-downloads.html).

- **Maven 3.x.x:** Make sure you have Maven version 3 or above installed. You can download it [here](https://maven.apache.org/install.htmlhttps://gradle.org/install/).

- **RemoteWebDriver:** To manage WebDriver binaries using selenium server. Add the RemoteDriverManager dependency to your project. More information can be found [here](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-remote-driver/).

- - **Selenium server (Grid):** The project relies on Selenium standalone server to setup the hub and node. Ensure you have the latest version of Selenium server in your system. Details can be found [here](https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.15.0/selenium-server-4.15.0.jar).

- **Selenium:** The project relies on Selenium for automated testing. Ensure you have the latest version of Selenium WebDriver added to your dependencies. Details can be found [here](https://www.selenium.dev/downloads/).

- **ChromeDriver** Make sure you have the chromedriver downloaded in your system having the same version as Chrome browser. You can download it [here](https://googlechromelabs.github.io/chrome-for-testing/).

- **TestNG Framework:** This project uses TestNG for test execution and reporting. Add the TestNG dependency to your project. Information can be found [here](https://mvnrepository.com/artifact/org.testng/testng).

- **Apache POI:** Used for updating Excel files. Add the Apache POI dependency to your project. Details can be found [here](https://poi.apache.org/).

- **ExtentReport:** Used for generating the emailable reports. Add the extent reports dependency to your project. Details can be found [here](https://mvnrepository.com/artifact/com.relevantcodes/extentreports/).

- **Log4J:** Used to generate the log statements while execution. Add the log4j dependency to your proect. Details can be found [here](https://mvnrepository.com/artifact/log4j/log4j/).

- **Maven compiler plugin:** Used to compile the sources of the project. Add the maven-compiler-plugin to your project. Details can be found [here](https://maven.apache.org/plugins/maven-compiler-plugin/usage.html/).

- **Apache maven plugin** Used to include the TestNG suite XML files. Add the apache-maven-plugin to your project. Details can be found [here](https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html#using-suite-xml-files/).

### Example Gradle Dependency Configuration:

```maven
<plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
          <source>17</source>
          <target>17</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.2.2</version>
        <configuration>
          <suiteXmlFiles>
            <suiteXmlFile>testng.xml</suiteXmlFile>
          </suiteXmlFiles>
        </configuration>
      </plugin>
</plugins>
 <dependencies>
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>7.8.0</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>io.github.bonigarcia</groupId>
      <artifactId>webdrivermanager</artifactId>
      <version>5.6.2</version>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.15.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi-ooxml</artifactId>
      <version>5.2.5</version>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-remote-driver</artifactId>
      <version>4.15.0</version>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
    <dependency>
      <groupId>com.relevantcodes</groupId>
      <artifactId>extentreports</artifactId>
      <version>2.41.2</version>
    </dependency>
  </dependencies>
```

> [!NOTE]
> Some of the dependencies may not work in furure. Update to their latest version

# Instructions

Clone/Download the code to your local machine. Pull the code stubs/Unpack the file. Open your terminal/shell. Navigate to the project folder in your terminal.

> **_To start the hub_** Execute the command in the terminal.

```
java -jar <path_to_hub> hub
```

> **_To add the node to the hub_** Execute the command in the new terminal.

```
java -Dwebdriver.chrome.driver="<path_to_chromedriver>" -jar <path_to_hub> node --hub http://192.168.0.190:4444/grid/register
```

> **_For Windows:_** Execute the command in the terminal (command prompt or powershell).

```
mvn test-compile
```

> **_For Mac/Linux:_** Execute this command in the bash terminal.

```
mvn test-compile
```

Wait for some time and bingo! you will have the output in two different emailable files named `application.html` in the Log folder and `QTRIP_ExtentReport.html` in the Reports folder and the screenshots in the folder named `screenshots`.
