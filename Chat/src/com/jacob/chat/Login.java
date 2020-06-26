package com.jacob.chat;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField textAddress;
	private JLabel lblAddress;
	private JTextField textPort;
	private JLabel lblPort;
	private JLabel lblAddressDesc;

	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

			e.printStackTrace();
		}

		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(75, 35, 144, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(39, 38, 46, 14);
		contentPane.add(lblName);

		textAddress = new JTextField();
		textAddress.setBounds(75, 95, 144, 20);
		contentPane.add(textAddress);
		textAddress.setColumns(10);

		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(27, 98, 46, 14);
		contentPane.add(lblAddress);

		textPort = new JTextField();
		textPort.setColumns(10);
		textPort.setBounds(75, 153, 144, 20);
		contentPane.add(textPort);

		lblPort = new JLabel("Port:");
		lblPort.setBounds(39, 156, 46, 14);
		contentPane.add(lblPort);

		lblAddressDesc = new JLabel("(eg. 196.168.0.2)");
		lblAddressDesc.setBounds(96, 126, 93, 14);
		contentPane.add(lblAddressDesc);

		JLabel lblPortDesc = new JLabel("(eg. 8192)");
		lblPortDesc.setBounds(113, 184, 57, 14);
		contentPane.add(lblPortDesc);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String address = textAddress.getText();
				int port = Integer.parseInt(textPort.getText());
				login(name, address, port);
			}

		});
		btnLogin.setBounds(96, 317, 89, 23);
		contentPane.add(btnLogin);
	}

	private void login(String name, String address, int port) {
		dispose();
		new ClientWindow(name, address, port);
		// System.out.println(name + "  " + address + "  " + port);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
