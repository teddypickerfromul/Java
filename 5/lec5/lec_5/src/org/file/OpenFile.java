package org.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	public OpenFile() {
	}
	public File open(){
		final JFileChooser fc = new JFileChooser();		
		int returnVal = -1;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		fc.setFileFilter(filter);
		returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();			
		}		
		return null;
	}
}
