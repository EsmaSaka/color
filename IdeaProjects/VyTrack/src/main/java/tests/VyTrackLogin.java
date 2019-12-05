package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class VyTrackLogin {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions act;
    @BeforeMethod
    public void setup(){
      driver= BrowserFactory.getDriver("chrome");
      driver.get("https://qa1.vytrack.com/user/login");
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      act= new Actions(driver);
      wait= new WebDriverWait(driver,10);
      driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
      driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);
      //driver.findElement(By.id("_submit")).click();
        WebElement mask = driver.findElement(By.xpath("//div[@class='loader-mask shown']//div[@class='loader-frame']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));
        WebElement ActivitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(ActivitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(ActivitiesElement));
        ActivitiesElement.click();
        WebElement CalenderEvent = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.elementToBeClickable(CalenderEvent));
        CalenderEvent.click();
        BrowserWait.wait(3);
      // to se whether we launched the new page or not i scripted following code
        String  ExpectedCalEventTitle= "All - Calendar Events - Activities";
       //  to make sure that we clicked on the link and the page is loaded
       String ActualCalEventTitle= driver.getTitle();
       // wait.until(ExpectedConditions.visibilityOf());
       Assert.assertEquals(ActualCalEventTitle,ExpectedCalEventTitle);



    }
    @Test(description = "verify that delete,edit, view buttons are available")
    public void test01(){
        // testers meeting xpath= //td[contains(text(),'Testers Meeting')]
       // WebElement Testers= driver.findElement(By.xpath("(//[1]"));
        WebElement ThreeDots= driver.findElement(By.xpath("//tr[15]//td[9]"));
        // //tr[15]//td[9]
        // (//a[contains(text(),'...')])[14]
        act.moveToElement(ThreeDots).build().perform();
        BrowserWait.wait(3);
        WebElement minTarget = driver.findElement(By.xpath("//tr[15]"));

        WebElement delete = minTarget.findElement(By.xpath("/html[1]/body[1]/ul[1]/li[1]/ul[1]/li[3]/a[1]/i[1]"));
        wait.until(ExpectedConditions.visibilityOf(delete));
        act.moveToElement(delete).build().perform();
        Assert.assertTrue(delete.isDisplayed(),"delete button is not displayed");

        WebElement editButton = minTarget.findElement(By.xpath("/html[1]/body[1]/ul[1]/li[1]/ul[1]/li[2]/a[1]/i[1]"));
        wait.until(ExpectedConditions.visibilityOf(editButton));
        act.moveToElement(editButton).build().perform();
        Assert.assertTrue(editButton.isDisplayed(),"Edit button is not displayed");

        WebElement  viewButton= minTarget.findElement(By.xpath("/html[1]/body[1]/ul[1]/li[1]/ul[1]/li[1]/a[1]/i[1]"));
        wait.until(ExpectedConditions.visibilityOf(viewButton));
        act.moveToElement(viewButton).build().perform();
        Assert.assertTrue(viewButton.isDisplayed(),"ViewButton is not displayed");


    }
    @Test(description = "Deselect all options and verify title ")
    public void test02(){

        driver.findElement(By.xpath("//i[@class='fa-cog hide-text']")).click();
        BrowserWait.wait(3);
        List <WebElement> ticks = driver.findElements(By.xpath("//div[@class='toolbar']//tr//td[1]"));

        for(int i=1;i<ticks.size();i++){
            BrowserWait.wait(2);
            if(!ticks.get(i).getText().equalsIgnoreCase("Title")){
                ticks.get(i).click();
            }
        }
        BrowserWait.wait(3);
        String title = driver.findElement(By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label'][contains(text(),'Title')]")).getText();
        Assert.assertEquals(title, "TITLE");
        BrowserWait.wait(2);
    }
    @Test(description = "Verify that save and close ; save and new ; and save opts are available")
    public void test03(){
        driver.findElement(By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]")).click();
        BrowserWait.wait(3);
        driver.findElement(By.xpath("//a[@class='btn-success btn dropdown-toggle']")).click();

        WebElement SaveClose =driver.findElement(By.xpath("//button[@class='action-button dropdown-item']"));
        wait.until(ExpectedConditions.visibilityOf(SaveClose));
        act.moveToElement(SaveClose).build().perform();
        Assert.assertTrue(SaveClose.isDisplayed(),"SaveClose option is not displayed");

        WebElement SaveNew = driver.findElement(By.xpath("//button[contains(text(),'Save and New')]"));
        wait.until(ExpectedConditions.visibilityOf(SaveNew));
        act.moveToElement(SaveNew).build().perform();
        Assert.assertTrue(SaveNew.isDisplayed(),"SaveNew is not displayed");

        WebElement Save = driver.findElement(By.xpath("//li[3]//button[1]"));
        wait.until(ExpectedConditions.visibilityOf(Save));
        act.moveToElement(Save).build().perform();
        Assert.assertTrue(Save.isDisplayed(),"Save button is not displayed");

    }
    @Test(description = "Verify that “All Calendar Events” page subtitle is displayed")
    public  void test04(){
        driver.findElement(By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]")).click();
        BrowserWait.wait(2);
        driver.findElement(By.xpath("//a[@title='Cancel']")).click();
        BrowserWait.wait(3);
        WebElement  Subtitle= driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        wait.until(ExpectedConditions.visibilityOf(Subtitle));
        String ActualSubText=driver.findElement(By.cssSelector("h1.oro-subtitle")).getText();
        System.out.println(ActualSubText);
        String expectedSubText= "All Calendar Events";
        Assert.assertEquals(ActualSubText,expectedSubText,"Subtitle does not match");

    }
    @Test(description = "Verify that difference between time is 1 hour")
    public  void test05(){
        driver.findElement(By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]")).click();
        BrowserWait.wait(2);
       String time= driver.findElement(By.xpath("//input[@class='input-small timepicker-input start ui-timepicker-input']")).getText();
       BrowserWait.wait(3);
        System.out.println(time);
    }
    @Test(description = "verify that end time is 10 pm")
    public void test06(){
        driver.findElement(By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]")).click();
        BrowserWait.wait(3);
        // find the locator using dynamic id of 9 pm
        driver.findElement(By.xpath("//input[starts-with(@id,'time_selector_oro_calendar_event_form_start-uid')]")).click();
        BrowserWait.wait(3);
        WebElement ninePM=driver.findElement(By.xpath("//li[contains(text(),'9:00 PM')]"));
        ninePM.click();
        //BrowserWait.wait(5);
       // Assert.assertTrue(ninePM.isDisplayed(),"9 pm is  not displayed");
        BrowserWait.wait(3);

        // now verify that end time is 10.00 pm
        WebElement endTimeBox= driver.findElement(By.xpath("//input[contains(@id,'time_selector_oro_calendar_event_form_end-uid')]"));
        endTimeBox.click();
        String actualEndTime = driver.findElement(By.xpath("(//li[@class='ui-timepicker-pm ui-timepicker-selected'])[2]")).getText();
        Assert.assertEquals(actualEndTime,"10:00 PM", "expected should be 10pm");
        System.out.println("Test 6 passed");
    }
    @Test(description = "Verify all day event")
    public void test07(){
        driver.findElement(By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]")).click();
        BrowserWait.wait(3);
       WebElement AllDayEvent= driver.findElement(By.xpath("//input[contains(@id,'oro_calendar_event_form_allDay-uid')]"));
       AllDayEvent.click();
        BrowserWait.wait(2);
        Assert.assertTrue(AllDayEvent.isSelected(),"AllDayEvent is not selected");
        // verify that start end time are displayed
        WebElement startTime =driver.findElement(By.xpath("//input[@class='input-small timepicker-input start ui-timepicker-input']"));
        Assert.assertFalse(startTime.isDisplayed());
        WebElement endTime= driver.findElement(By.xpath("//input[@class='input-small timepicker-input end ui-timepicker-input']"));
        Assert.assertFalse(endTime.isDisplayed(),"endTime is displayed");
        // Verify that start and end date input boxes are displayed
        WebElement startDate = driver.findElement(By.xpath("//input[contains(@id,'date_selector_oro_calendar_event_form_start-uid')]"));
        Assert.assertTrue(startDate.isDisplayed(),"startDate is not displayed");
        WebElement endDate= driver.findElement(By.xpath("//input[@class='input-small datepicker-input end hasDatepicker']"));
        Assert.assertTrue(endDate.isDisplayed(),"endDate is not displayed");

    }




    @AfterMethod
    public void teardown(){
        driver.close();
    }
}
