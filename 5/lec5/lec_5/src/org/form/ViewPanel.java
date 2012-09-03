package org.form;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewPanel extends JPanel {

	private JButton buttonInFile;
	private JTextArea textAreaOut;
	private JButton buttonExecute;
	private JButton buttonInDic;
	private JTextArea textAreaInFile;
	private JTextArea textAreaDic;

	/**
	 * Create the panel.
	 */
	public ViewPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("Исходный файл");
		label.setBounds(12, 25, 119, 15);
		add(label);
		
		setButtonInFile(new JButton("Загрузить"));
		getButtonInFile().setBounds(133, 20, 117, 25);
		add(getButtonInFile());
		
		
		
		setTextAreaOut(new JTextArea());
		getTextAreaOut().setBounds(12, 196, 238, 76);
		add(getTextAreaOut());
		
		JLabel label_1 = new JLabel("Выходной файл");
		label_1.setBounds(12, 162, 119, 15);
		add(label_1);
		
		setButtonExecute(new JButton("Выполнить"));
		getButtonExecute().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getButtonExecute().setBounds(133, 157, 117, 25);
		add(getButtonExecute());
		
		setTextAreaDic(new JTextArea());
		getTextAreaDic().setBounds(288, 59, 138, 213);
		add(getTextAreaDic());
		
		JLabel label_2 = new JLabel("Словарь");
		label_2.setBounds(288, 25, 69, 15);
		add(label_2);
		
		setButtonInDic(new JButton("..."));
		getButtonInDic().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getButtonInDic().setBounds(373, 20, 34, 25);
		add(getButtonInDic());
		
		setTextAreaInFile(new JTextArea());
		getTextAreaInFile().setBounds(12, 57, 238, 76);
		add(getTextAreaInFile());

	}

	public JButton getButtonInFile() {
		return buttonInFile;
	}

	private void setButtonInFile(JButton buttonInFile) {
		this.buttonInFile = buttonInFile;
	}

	public JTextArea getTextAreaOut() {
		return textAreaOut;
	}

	private void setTextAreaOut(JTextArea textAreaOut) {
		this.textAreaOut = textAreaOut;
		textAreaOut.setLineWrap(true);
		textAreaOut.setWrapStyleWord(true);
	}

	public JButton getButtonExecute() {
		return buttonExecute;
	}

	private void setButtonExecute(JButton buttonExecute) {
		this.buttonExecute = buttonExecute;
	}

	public JButton getButtonInDic() {
		return buttonInDic;
	}

	private void setButtonInDic(JButton buttonInDic) {
		this.buttonInDic = buttonInDic;
	}

	public JTextArea getTextAreaInFile() {
		return textAreaInFile;
	}

	private void setTextAreaInFile(JTextArea textAreaInFile) {
		this.textAreaInFile = textAreaInFile;
		textAreaInFile.setLineWrap(true);
		textAreaInFile.setWrapStyleWord(true);
	}

	public JTextArea getTextAreaDic() {
		return textAreaDic;
	}

	private void setTextAreaDic(JTextArea textAreaDic) {
		this.textAreaDic = textAreaDic;
		textAreaDic.setLineWrap(true);
		textAreaDic.setWrapStyleWord(true);
	}
}
