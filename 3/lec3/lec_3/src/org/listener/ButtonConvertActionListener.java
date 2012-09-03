package org.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.controller.Controller;

public class ButtonConvertActionListener implements ActionListener{
    
    Controller controller;
	
	public ButtonConvertActionListener(Controller controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		controller.konvert();	
	}
}
