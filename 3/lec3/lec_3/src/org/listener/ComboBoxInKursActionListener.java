package org.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.controller.Controller;
import org.model.ModelListKurs;
import org.panel.ConverterPanel;

public class ComboBoxInKursActionListener implements ActionListener {

	ConverterPanel panel;
	ModelListKurs model;
	Controller controller;
	public ComboBoxInKursActionListener(ConverterPanel panel, ModelListKurs model, Controller controller) {
		this.panel = panel;
		this.model = model;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		panel.getComboBoxOutKurs().removeAllItems();
		if (panel.getComboBoxInKurs().getSelectedItem() == null){
			return;
		};
		for(String item :model.valuta(panel.getComboBoxInKurs().getSelectedItem().toString())){
			panel.getComboBoxOutKurs().addItem(item);
		}	
		controller.updateListKurs();
	}

}
