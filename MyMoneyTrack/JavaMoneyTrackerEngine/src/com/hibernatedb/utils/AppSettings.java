package com.hibernatedb.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class AppSettings {
    private Wini inifile;
    private String filename;
    
    private String LoginRegexpString;
    private String PassRegexpString;
    
    private static final String default_login_pattern = "^[a-z0-9]{3,15}$";
    private static final String default_pass_pattern ="^[a-z0-9]{8,30}$";
    private static final String default_file_name ="appsettings.ini";
    private static final String default_regexp_section_name = "expressions";

    public static String getDefault_file_name() {
        return default_file_name;
    }

    public static String getDefault_login_pattern() {
        return default_login_pattern;
    }

    public static String getDefault_pass_pattern() {
        return default_pass_pattern;
    }

    public static String getDefault_regexp_section_name() {
        return default_regexp_section_name;
    }
            
    
    public AppSettings(){
        this.inifile = null;
        this.filename = null;
        this.LoginRegexpString = this.default_login_pattern;
        this.PassRegexpString = this.default_pass_pattern;
        this.filename = this.default_file_name;
    }
    
    public AppSettings loadFromFile(String filename) throws InvalidFileFormatException, IOException {
        this.inifile = new Wini(new File(filename));
        this.LoginRegexpString = this.getLoginRegexpString();
        this.PassRegexpString = this.getPassRegexpString();
        return this;
    };
    
    public void savetoFile(String filename) {
        try {
            File f=new File(filename);
            f.createNewFile();
            this.inifile = new Wini(f);
        } catch (IOException ex) {
            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.inifile.put(this.default_regexp_section_name, "pass_regexp", this.PassRegexpString);
            this.inifile.put(this.default_regexp_section_name, "login_regexp",this.LoginRegexpString);
        try {
            this.inifile.store();
        } catch (IOException ex) {
            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AppSettings(String filename) throws InvalidFileFormatException, IOException {
        this.inifile = new Wini(new File(filename));
        this.LoginRegexpString = this.getLoginRegexpString();
        this.PassRegexpString = this.getPassRegexpString();
    }
    
    public String getLoginRegexpString() {
        return this.inifile.get(this.default_regexp_section_name, "login_regexp");
    }
    
    public String getPassRegexpString() {
        return this.inifile.get(this.default_regexp_section_name, "pass_regexp");
    }
    
    public void setLoginRegexpString(String pattern) {
        this.LoginRegexpString = pattern;
        this.inifile.put(this.default_regexp_section_name, "login_regexp", pattern);
        try {
            this.inifile.store();
        } catch (IOException ex) {
            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void setPassRegexpString(String pattern) {
        this.PassRegexpString = pattern;
        this.inifile.put(this.default_regexp_section_name, "pass_regexp", pattern); 
        try {
            this.inifile.store();
        } catch (IOException ex) {
            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean SettingsFileExists(String filename){
        File f = new File(filename);
        return f.exists();                
    }
    
    public boolean DefaultSettingsFileExists() {
        File f = new File(this.default_file_name);
        return f.exists();
    } 
     
    public AppSettings makeDefaultSettings() throws InvalidFileFormatException, IOException{
        File f=new File(this.default_file_name);
        f.createNewFile();
        this.inifile = new Wini(f);
        this.inifile.put(this.default_regexp_section_name, "pass_regexp", this.default_pass_pattern);
        this.inifile.put(this.default_regexp_section_name, "login_regexp",this.default_login_pattern);
        this.inifile.store();
        return this;   
    }
    
   @Override
   public String toString() {
	StringBuilder result = new StringBuilder();
        result.append("path : "+this.inifile.getFile().getAbsolutePath()+" ; content + ");
        result.append(this.inifile.toString());
	return result.toString();
    }
}
