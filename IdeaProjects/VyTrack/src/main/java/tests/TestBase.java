package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigurationReader;
import utils.Driver;

public  abstract class TestBase {



    @BeforeMethod
    public  void  setup(){
        String url= ConfigurationReader.getProperty("url");
      //  String userName = ConfigurationReader.getProperty("userName");
      //  String passWord = ConfigurationReader.getProperty("passWord");
        Driver.get().get(url);
     //   Driver.get().get(userName);
     //   Driver.get().get(passWord);

    }
    @AfterMethod
    public void teardown(){
       Driver.close();
    }

}
