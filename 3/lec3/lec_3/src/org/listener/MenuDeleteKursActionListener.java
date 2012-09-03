package org.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.controller.Controller;

public class MenuDeleteKursActionListener implements ActionListener {
	Controller controller;

	public MenuDeleteKursActionListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.setDefault();
	}

}
