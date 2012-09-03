package org.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.controller.Controller;
import org.file.OpenFile;

public class ButtonExecuteActionListener implements ActionListener{
    
	Controller controller;
	
	public ButtonExecuteActionListener(Controller controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		controller.execute();
		controller.viewResult();
		controller.saveFile();
		
			
	}
}
