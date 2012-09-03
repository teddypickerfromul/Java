package org.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.controller.Controller;
import org.panel.ConverterPanel;

public class MenuAddKursActionListener implements ActionListener {
	ConverterPanel panel;
	Controller  controller;
	private File file;
	public MenuAddKursActionListener(ConverterPanel panel, Controller controller) {
		this.panel = panel;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		openFileChooser();
	}

	private void openFileChooser() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = -1;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
		fc.setFileFilter(filter);
		returnVal = fc.showOpenDialog(panel.getParent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			controller.createUpdateModel(file);
		}		
		
	}

}
