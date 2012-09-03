package moneytrackerconsoleclient;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceRef;
import moneytrackerconsoleclient.methods.*;

//TODO: отстуттвует property id у всех кроме User !
//TODO: убрать хард-код
public class MoneyTrackerClient {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/MoneyTracker/MoneyTrackerService?wsdl")
    MoneyTrackerService service;
    WebServiceContext context;
    @Resource
    MoneyTracker port;

    public MoneyTrackerClient() {
        this.Init();
    }
    
    public void Init() {
        this.service = new MoneyTrackerService();
        this.port = this.service.getMoneyTrackerPort();
    }

    public MoneyTracker getPort() {
        return this.port;
    }
}
