package moneytrackerfxclient.utils;

import java.io.File;
import java.io.IOException;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class AppSettings {

    private static volatile AppSettings instance;
    private Wini inifile;
    
    private static final String default_file_name = "appsettings.ini";
    private static final String default_credentials_section_name = "credentials";
  
    public static AppSettings getInstance() {
        AppSettings localInstance = instance;
        if (localInstance == null) {
            synchronized (MoneyTrackerController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AppSettings();
                }
            }
        }
        return localInstance;
    }

    public static String getDefault_file_name() {
        return default_file_name;
    }

    public static String getDefault_credentials_section_name() {
        return default_credentials_section_name;
    }
   
    public static void createEmptyConfigGile() throws IOException{
        File f = new File(default_file_name);
        f.createNewFile();
        
    }
}