package org.controller;


import java.io.File;
import java.util.ArrayList;

import org.form.ConverterForm;
import org.listener.ButtonConvertActionListener;
import org.listener.ComboBoxInKursActionListener;
import org.listener.MenuAddKursActionListener;
import org.listener.MenuDeleteKursActionListener;
import org.model.ModelListKurs;
import org.panel.ConverterPanel;
import org.readXML.ReadXMLFile;

public class Controller {
	private ConverterForm frame;
	private ConverterPanel panel;
	private ModelListKurs model;

	public Controller(ConverterForm frame, ConverterPanel panel) {
		model = new ModelListKurs();
		this.frame = frame;
		this.panel = panel;

		this.frame.getContentPane().add(this.panel);
	}

	public void addListner() {
		panel.getbKonvert().addActionListener(new ButtonConvertActionListener(this));		
		frame.getMenuAddKurs().addActionListener(new MenuAddKursActionListener(panel,this));	
	    frame.getMenuDeleteKurs().addActionListener(new MenuDeleteKursActionListener(this));
	    panel.getComboBoxInKurs().addActionListener(new ComboBoxInKursActionListener(panel, model, this));
	}

	public void createUpdateModel(File file) {
		model.getList().removeAll(model.getList());
		ReadXMLFile fileXML = new ReadXMLFile(file);
		fileXML.readFile(model);
		initCombobox();
		panel.getbKonvert().setEnabled(true);
	}

	private void initCombobox() {
		for(String item :model.currentValuta()){
			panel.getComboBoxInKurs().addItem(item);
		}				
		panel.getComboBoxOutKurs().removeAllItems();
		for(String item :model.valuta(panel.getComboBoxInKurs().getSelectedItem().toString())){
			panel.getComboBoxOutKurs().addItem(item);
		}					
	}

	public void updateListKurs() {
		String text = new String();
		int i = 0;
		 String current = panel.getComboBoxInKurs().getSelectedItem().toString();
		 ArrayList<Double> list = model.valueAllKues(current);
		for(String item :model.valuta(panel.getComboBoxInKurs().getSelectedItem().toString())){
			text += item + " : " + list.get(i) + "\n";
			i++;
		}	
		panel.getListKursTextArea().setText(text);
		
	}

	public void setDefault() {		
		panel.getbKonvert().setEnabled(false);
		panel.getListKursTextArea().setText("");
		panel.getComboBoxInKurs().removeAllItems();
		panel.getComboBoxOutKurs().removeAllItems();
		model.removeAll();
	}

	public void konvert() {
		 double summa = Double.valueOf(panel.getInTextField().getText());
		 String current = panel.getComboBoxInKurs().getSelectedItem().toString();
		 String valuta = panel.getComboBoxOutKurs().getSelectedItem().toString();
		 double kurs = model.valueKues(current, valuta);
		 double result = summa * kurs;
		 panel.getOutTextField().setText(String.valueOf(result));
		
	}

}
