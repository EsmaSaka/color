package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private  static WebDriver driver;
    // RemoteWebDriver
    private Driver(){

    }

    public static WebDriver get(){
        if(driver== null){
            String browser= ConfigurationReader.getProperty("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver= new ChromeDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver= new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver();
                    break;
                case "firefox-headless" :
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
                case "ie" :
                    if(System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("your OS does not support IE");
                    WebDriverManager.iedriver().setup();
                    driver= new InternetExplorerDriver();
                    break;
                case "Edge" :
                    if(System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("your OS does not support Edge");
                    WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    break;
                case "Safari" :
                    if(System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("your OS does not support mac");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver= new SafariDriver();
                    break;
                default:
                    // if browser tpe is wrong, no browser will bw open
                    throw  new  RuntimeException("Wrong browser type");

            }
        }
        return driver;
    }
    public static  void  close(){
        // if driver still exist close all browsers
        // destroy browser
        // ready for garbage collector
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }


}


