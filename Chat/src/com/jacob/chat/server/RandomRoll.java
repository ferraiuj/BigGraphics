package com.jacob.chat.server;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jacob.chat.Client;
import com.jacob.chat.ClientWindow;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RandomRoll extends JFrame {

	private static final long serialVersionUID = 1L;

	private ClientWindow windoww;
	private Client client;
	private Random random = new Random();

	private List<String> names = new ArrayList<String>();

	private JPanel contentPane;
	private JTextField textField;

	public RandomRoll() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(158, 107, 113, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblTipeNaemsIn = new JLabel("TIPE NAEM IN FAGOT");
		lblTipeNaemsIn.setBounds(158, 23, 113, 14);
		contentPane.add(lblTipeNaemsIn);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (names.size() < 8) {
					names.add(textField.getText());
					String deleted = names.get(random.nextInt(names.size()));
					
					System.out.println(deleted);
					// String poop = textField.getText();
					// JLabel newName = new JLabel(poop);
					// newName.setBounds(158, 107, 113, 29);
					// contentPane.add(newName);
					
					//dispose();
				}

				
			
			}
		});
		btnEnter.setBounds(172, 195, 89, 23);
		contentPane.add(btnEnter);
		setVisible(true);
	}
}
