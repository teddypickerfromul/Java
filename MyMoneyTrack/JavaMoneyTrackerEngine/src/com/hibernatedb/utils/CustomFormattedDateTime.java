
//TODO: конвертирование Calendar обьекта в строку формата 

package com.hibernatedb.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomFormattedDateTime {
    
    private String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm";
    private Calendar cal;
    private SimpleDateFormat sdf;

    public CustomFormattedDateTime() {
        this.cal = Calendar.getInstance();
        this.sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    }
    
    // ?
    
    public CustomFormattedDateTime(Calendar cal) {
        this.cal = cal;
        this.sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    }
    
    public String now() {
        return this.sdf.format(cal.getTime());
    }
    
    public String getDATE_FORMAT_NOW() {
        return this.DATE_FORMAT_NOW;
    }
    
}
