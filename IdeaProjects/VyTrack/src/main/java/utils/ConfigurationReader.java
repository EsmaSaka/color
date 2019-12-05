package utils;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private  static Properties configFile;



    static
    {
        try {
            FileInputStream fileInputStream = new FileInputStream("Configuration.properties");
            configFile = new Properties();
            configFile.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("file is not loaded");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return configFile.getProperty(key);
    }


}
