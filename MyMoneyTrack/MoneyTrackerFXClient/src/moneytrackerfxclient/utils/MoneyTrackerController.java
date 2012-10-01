package moneytrackerfxclient.utils;

import moneytrackerconsoleclient.*;
import moneytrackerconsoleclient.methods.MoneyTracker;

public class MoneyTrackerController {

    private static volatile MoneyTrackerController instance;
    
    private static MoneyTrackerClient client;
    
    public static MoneyTrackerController getInstance() {
        MoneyTrackerController localInstance = instance;
        if (localInstance == null) {
            synchronized (MoneyTrackerController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MoneyTrackerController();
                    client = new MoneyTrackerClient();
                }
            }
        }
        return localInstance;
    }
    
    public static MoneyTracker getClientPort(){
        return getInstance().getClient().getPort();
        /*return client.getPort();*/
    }
    
    public static MoneyTrackerClient getClient(){
        return getInstance().client;
    }
}
