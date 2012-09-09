package com.moneytracker.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.EmailValidator;
import org.ini4j.InvalidFileFormatException;

public class Validator {

    public static final Log logger = LogFactory.getLog(Validator.class);
    private String str;
    private AppSettings appcfg;

    public Validator() throws InvalidFileFormatException, IOException {
        this.appcfg = new AppSettings();
        if (!appcfg.DefaultSettingsFileExists()) {
            this.appcfg = this.appcfg.makeDefaultSettings();
        } else {
            this.appcfg = this.appcfg.loadFromFile(System.getProperty("user.dir") + System.getProperty("file.separator") + this.appcfg.getDefault_file_name());
        }
        this.str = null;
    }

    public Validator(String filename) throws InvalidFileFormatException, IOException {
        this.appcfg = new AppSettings();
        this.appcfg.loadFromFile(filename);
        this.str = null;
    }

    public Validator(AppSettings appcfg) throws InvalidFileFormatException, IOException {
        this.appcfg = appcfg;
        this.str = null;
    }

    public void setString(String string) {
        this.str = string;
    }

    public boolean checkLogin(String string) {
        this.str = string;
        Pattern pattern = Pattern.compile(this.appcfg.getLoginRegexpString());
        Matcher matcher = pattern.matcher(string);
        logger.info("login string: <" + string + "> matches : " + matcher.matches());
        return matcher.matches();
    }

    public boolean checkPassword(String string) {
        this.str = string;
        Pattern pattern = Pattern.compile(this.appcfg.getPassRegexpString());
        Matcher matcher = pattern.matcher(string);
        logger.info("password string: <" + string + "> matches : " + matcher.matches());
        return matcher.matches();
    }

    public boolean checkEmailAddress(String string) {
        logger.info("email string: <" + string + "> matches : " + EmailValidator.getInstance().isValid(string));
        return EmailValidator.getInstance().isValid(string);
    }

    public AppSettings getAppSettingsObject() {
        return this.appcfg;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.appcfg.getLoginRegexpString()).append(" ");
        result.append(this.appcfg.getPassRegexpString());
        return result.toString();
    }
}
