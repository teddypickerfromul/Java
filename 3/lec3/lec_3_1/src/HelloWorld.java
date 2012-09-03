import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HelloWorld extends JFrame {

	public HelloWorld() {
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		this.setSize(300, 200);
		this.getContentPane().setLayout(null);
		this.add(getJLabel(), null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);
		this.setTitle("HelloWorld");
		System.out.println("Разрешение сейчас = "+width+" * "+height+" пикселей.");
	}

	private javax.swing.JLabel getJLabel() {
		JLabel jLabel = new javax.swing.JLabel();
		jLabel.setBounds(34, 49, 53, 18);
		jLabel.setText("Name:");
		return jLabel;
	}

	private javax.swing.JTextField getJTextField() {
		JTextField jTextField = new javax.swing.JTextField();
		jTextField.setBounds(96, 49, 160, 20);
		return jTextField;
	}

	private javax.swing.JButton getJButton() {
		JButton jButton = new javax.swing.JButton();
		jButton.setBounds(103, 110, 71, 27);
		jButton.setText("OK");
		return jButton;
	}

	public static void main(String[] args) {
		HelloWorld w = new HelloWorld();
		w.setVisible(true);
	}
}
