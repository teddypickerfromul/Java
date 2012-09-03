/*
 * Класс, реализующий диалоговое окно с абсолютным путём к файлу
 */

// TODO : переделать в соответсвии с MVC
// TODO : избавиться от hardcoded ширины и высоты окна
// TODO : реализовать поддержку вывода длинных путёй

package org.fstree;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PathMessage extends JFrame {

	private int defaultWidth;

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public void setDefaultWidth(int defaultWidth) {
		this.defaultWidth = defaultWidth;
	}

	public int getDefaultHeight() {
		return defaultHeight;
	}

	public void setDefaultHeight(int defaultHeight) {
		this.defaultHeight = defaultHeight;
	}

	private int defaultHeight;

	// второй аргумент для установки нужного заголовка окна
	
	PathMessage(String arg, String mode) {
		super();
		ResolutionProperties resprops = new ResolutionProperties();
		resprops.setappDimensionX(300); 
		resprops.setappDimensionY(100); 
		this.setLocation((int) resprops.getappDimensionX(),
				(int) resprops.getappDimensionY());
		this.setDefaultWidth(300);
		this.setDefaultHeight(100);
		this.getContentPane().setLayout(null);
		this.setSize(defaultWidth, defaultHeight);
		this.add(getLabel(arg), null);
		if (mode.equals("file")) {
			this.setTitle("File absolute path");
		}
		if (mode.equals("dir")) {
			this.setTitle("Directory absolute path");
		}
		this.setVisible(true);
		/* this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); */
	}

	private JLabel getLabel(String arg) {
		JLabel label = new JLabel();
		label.setBounds(10, 10, 600, 50);
		label.setText(arg);
		return label;
	}
}
