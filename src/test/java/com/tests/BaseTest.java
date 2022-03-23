package com.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;
 
  
    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

		
		  String host = "localhost"; DesiredCapabilities dc;
		  
		  if(System.getProperty("BROWSER") != null &&
		  System.getProperty("BROWSER").equalsIgnoreCase("firefox")){ dc =
		  DesiredCapabilities.firefox(); }else{ dc = DesiredCapabilities.chrome(); }
		  
		  if(System.getProperty("HUB_HOST") != null){ host =
		  System.getProperty("HUB_HOST"); }
		  
		  String testName = ctx.getCurrentXmlTest().getName();
		  
		  String completeUrl = "http://" + host + ":4444/wd/hub";
		  dc.setCapability("name", testName); this.driver = new RemoteWebDriver(new
		  URL(completeUrl), dc);

		 
			/*
			 * String testName = ctx.getCurrentXmlTest().getName(); log =
			 * LogManager.getLogger(testName);
			 * 
			 * BrowserDriverFactory factory = new BrowserDriverFactory(browser, log); driver
			 * = factory.createDriver(); driver.manage().window().maximize();
			 */
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }



}
