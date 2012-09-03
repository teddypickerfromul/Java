package org.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Hashtable;

import org.form.ViewPanel;
import org.listener.ButtonExecuteActionListener;
import org.listener.ButtonOpenDicActionListener;
import org.listener.ButtonOpenFileActionListener;


public class Controller {

	private ViewPanel panel;
	private String sInText;
	private String sOutText;
	private Hashtable dictionary;
	private String titleHTML= "<TITLE>Работа со строками и с файлом</TITLE>";
	private String startPage = "<!DOCTYPE HTML PUBLIC" + '"' + "-//W3C//DTD HTML 3.2 Final//EN" + '"' + "> <HTML> <HEAD> <meta http-equiv=" + '"' + "Content-Type "+'"' +"content=" +'"' + "text/html; charset=utf-8"+'"'+ "><HEAD><BODY>";
	private String endPage = "</BODY></HTML>";	
	private String startTagHTML = "<b><i>";
	private String endTagHTML = "</i></b>";
	private String endStringHTML = "<br>";

	public Controller(ViewPanel panel) {
		this.panel = panel;
	}

	public void addListener() {
		panel.getButtonInFile().addActionListener(
				new ButtonOpenFileActionListener(this));
		panel.getButtonInDic().addActionListener(
				new ButtonOpenDicActionListener(this));
		panel.getButtonExecute().addActionListener(
				new ButtonExecuteActionListener(this));
	}

	public void openInFile(File file) {
		try {
			sInText = readFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.getTextAreaInFile().setText(sInText);

	}

	private String readFile(File file) throws IOException {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedInputStream bInSteam = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result = bInSteam.read();
		while (result != -1) {
			byte b = (byte) result;
			buf.write(b);
			result = bInSteam.read();
		}
		is.close();
		return buf.toString();
	}

	public void openDicFile(File f) {
		String sDicText = null;
		try {
			sDicText = readFile(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.getTextAreaDic().setText(sDicText);

		initDictonary(sDicText);
	}

	private void initDictonary(String sDicText) {
		dictionary = new Hashtable<String, String>();
		String[] result = sDicText.split("\n");
		for (int i = 0; i < result.length; i++) {			
			dictionary.put(result[i], startTagHTML + result[i] + endTagHTML);
		}
		dictionary.put("\n", endStringHTML);
	}

	public void execute() {
		String[] line = sInText.split("\n");
		sOutText = "";
		for (int i = 0; i < line.length; i++) {
			sOutText += replaseItem(line[i]) + dictionary.get("\n").toString();
		}

	}

	private String replaseItem(String string) {
		String[] line = string.split(" ");
		String result = "";
		Boolean flag = false;
		for (int i = 0; i < line.length; i++) {
			if (line[i].indexOf(".") > -1) {
				line[i].replace(".", "");
				flag = true;
			}
			if (dictionary.get(line[i]) != null) {
				line[i] = dictionary.get(line[i]).toString();
			}
			if (flag) {
				flag = false;
				line[i] += "";
			}
		}
		for (int i = 0; i < line.length; i++) {
			result += " " + line[i];
		}
		return result;
	}

	public void viewResult() {
		panel.getTextAreaOut().setText("");
		panel.getTextAreaOut().setText(sOutText);
		System.out.println(sOutText);
	}

	public void saveFile() {
		PrintWriter writer = null;
		String filename = "out.html";
	    try {
	     writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
	     writer.write(startPage);
	     writer.write(sOutText);
	     writer.write(endPage);
	     writer.close();
	    } catch (Exception ex) {} 
	    
	      
	    
	    
	    
	}

}
