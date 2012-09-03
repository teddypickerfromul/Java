package org.form;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.controller.Controller;
import org.panel.ConverterPanel;

public class ConverterForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6837812921296375229L;
	private JMenuItem menuAddKurs;
	private JMenuItem menuDeleteKurs;
	private Controller controller; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterForm frame = new ConverterForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConverterForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);		
		setTitle("Конвертор валют");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));			
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Курс");
		menuBar.add(mnNewMenu);
		
		setMenuAddKurs(new JMenuItem("Добавить курс"));
		mnNewMenu.add(getMenuAddKurs());
		
		setMenuDeleteKurs(new JMenuItem("Удалить курс"));
		mnNewMenu.add(getMenuDeleteKurs());		
		controller = new Controller(this, new ConverterPanel());		
		controller.addListner();
	}

	public JMenuItem getMenuDeleteKurs() {
		return menuDeleteKurs;
	}

	private void setMenuDeleteKurs(JMenuItem menuDeleteKurs) {
		this.menuDeleteKurs = menuDeleteKurs;
	}

	public JMenuItem getMenuAddKurs() {
		return menuAddKurs;
	}

	private void setMenuAddKurs(JMenuItem menuAddKurs) {
		this.menuAddKurs = menuAddKurs;
	}

}
