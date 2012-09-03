package org.panel;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ConverterPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inTextField;
	private JTextField outTextField;
	private GroupLayout groupLayout;
	private JComboBox comboBoxInKurs;
	private JComboBox comboBoxOutKurs;
	private JTextArea listKursTextArea;
	private JButton bKonvert;
	/**
	 * Create the panel.
	 */
	public ConverterPanel() {
		
		setInTextField(new JTextField());
		getInTextField().setHorizontalAlignment(SwingConstants.RIGHT);
		getInTextField().setText("0");
		getInTextField().setColumns(10);
		
		setbKonvert(new JButton("Конвертировать"));
		
		setOutTextField(new JTextField());
		getOutTextField().setHorizontalAlignment(SwingConstants.RIGHT);
		getOutTextField().setEnabled(false);
		getOutTextField().setText("0");
		getOutTextField().setColumns(10);
		
		setComboBoxInKurs(new JComboBox());
		
		setComboBoxOutKurs(new JComboBox());
		
		JLabel label = new JLabel("Соотношение валют");
		
		setListKursTextArea(new JTextArea());
		
		
		setGroupLayout(new GroupLayout(this));
		getGroupLayout().setHorizontalGroup(
			getGroupLayout().createParallelGroup(Alignment.LEADING)
				.addGroup(getGroupLayout().createSequentialGroup()
					.addGap(22)
					.addGroup(getGroupLayout().createParallelGroup(Alignment.TRAILING)
						.addComponent(getListKursTextArea(), Alignment.LEADING)
						.addGroup(Alignment.LEADING, getGroupLayout().createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE))
							
						.addGroup(Alignment.LEADING, getGroupLayout().createSequentialGroup()
							.addGroup(getGroupLayout().createParallelGroup(Alignment.LEADING, false)
								.addComponent(getComboBoxInKurs(), Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(getInTextField(), Alignment.TRAILING))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(getbKonvert())
							.addGap(12)
							.addGroup(getGroupLayout().createParallelGroup(Alignment.TRAILING, false)
								.addComponent(getComboBoxOutKurs(), 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(getOutTextField()))))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		getGroupLayout().setVerticalGroup(
			getGroupLayout().createParallelGroup(Alignment.LEADING)
				.addGroup(getGroupLayout().createSequentialGroup()
					.addContainerGap()
					.addGroup(getGroupLayout().createParallelGroup(Alignment.BASELINE)
						.addComponent(getInTextField(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(getOutTextField(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(getbKonvert()))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(getGroupLayout().createParallelGroup(Alignment.BASELINE)
						.addComponent(getComboBoxInKurs(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(getComboBoxOutKurs(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(getGroupLayout().createParallelGroup(Alignment.BASELINE)
						.addComponent(label))
					.addGap(18)
					.addComponent(getListKursTextArea(), GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(getGroupLayout());
this.add(label);
	}

	public JTextField getInTextField() {
		return inTextField;
	}

	public void setInTextField(JTextField inTextField) {
		this.inTextField = inTextField;
	}

	public JTextField getOutTextField() {
		return outTextField;
	}

	public void setOutTextField(JTextField outTextField) {
		this.outTextField = outTextField;
	}

	public GroupLayout getGroupLayout() {
		return groupLayout;
	}

	public void setGroupLayout(GroupLayout groupLayout) {
		this.groupLayout = groupLayout;
	}

	public JComboBox getComboBoxInKurs() {
		return comboBoxInKurs;
	}

	private void setComboBoxInKurs(JComboBox comboBoxInKurs) {
		this.comboBoxInKurs = comboBoxInKurs;
	}

	public JComboBox getComboBoxOutKurs() {
		return comboBoxOutKurs;
	}

	private void setComboBoxOutKurs(JComboBox comboBoxOutKurs) {
		this.comboBoxOutKurs = comboBoxOutKurs;
	}

	public JTextArea getListKursTextArea() {
		return listKursTextArea;
	}

	private void setListKursTextArea(JTextArea listKursTextArea) {
		this.listKursTextArea = listKursTextArea;
	}

	public JButton getbKonvert() {
		return bKonvert;
	}

	private void setbKonvert(JButton bKonvert) {
		this.bKonvert = bKonvert;
		bKonvert.setEnabled(false);
	}
	
}
