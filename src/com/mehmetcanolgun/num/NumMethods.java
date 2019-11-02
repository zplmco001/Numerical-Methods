package com.mehmetcanolgun.num;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class NumMethods {

	private JFrame frame;

	public static void main(String[] args) {
		new NumMethods();
	}


	public NumMethods() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JCheckBox chckbxNewtonraphsonMethod = new JCheckBox("Newton-Raphson Method");
		chckbxNewtonraphsonMethod.setBounds(100, 66, 199, 50);
		frame.getContentPane().add(chckbxNewtonraphsonMethod);
		
		JCheckBox chckbxSecantMethod = new JCheckBox("Secant Method");
		chckbxSecantMethod.setBounds(100, 122, 128, 23);
		frame.getContentPane().add(chckbxSecantMethod);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBounds(6, 243, 438, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Please select the method you want to use.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 438, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(49, 116, 192, 82);
		frame.getContentPane().add(editorPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(59, 166, -47, 65);
		frame.getContentPane().add(textPane);
	}
}
