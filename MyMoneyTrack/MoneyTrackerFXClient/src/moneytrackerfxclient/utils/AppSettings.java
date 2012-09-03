package moneytrackerfxclient.utils;

public class AppSettings {

    private static volatile AppSettings instance;
  
    public static AppSettings getInstance() {
        AppSettings localInstance = instance;
        if (localInstance == null) {
            synchronized (MoneyTrackerController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MoneyTrackerController();
                }
            }
        }
        return localInstance;
    }
   
}