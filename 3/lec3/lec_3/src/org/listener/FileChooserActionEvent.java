package org.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import javax.swing.JFileChooser;

public class FileChooserActionEvent implements ActionListener {
	private JFileChooser fc;
	private int returnVal;
	private File file;

	public FileChooserActionEvent(JFileChooser fc, int returnVal) {
		this.fc = fc;
		this.returnVal = returnVal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Handle open button action.

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setFile(fc.getSelectedFile());
			

		}

	}

	public File getFile() {
		return file;
	}

	private void setFile(File file) {
		this.file = file;
	}

}
