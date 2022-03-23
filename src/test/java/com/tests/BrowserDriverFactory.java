package com.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	private Logger log;

	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}

	public WebDriver createDriver() {
		// Create driver
		log.info("Create driver: " + browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "/Users/muhamedsalah/Downloads/chromedriver");
			driver.set(new ChromeDriver());
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "/Users/muhamedsalah/Downloads/geckodriver");
			driver.set(new FirefoxDriver());
			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome.");
			System.setProperty("webdriver.chrome.driver", "/Users/muhamedsalah/Downloads/chromedriver");
			driver.set(new ChromeDriver());
			break;
		}

		return driver.get();
	}
}