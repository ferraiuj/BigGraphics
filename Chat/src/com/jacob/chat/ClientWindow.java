package com.jacob.chat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.jacob.chat.server.RandomRoll;
import com.jacob.chat.server.Server;

public class ClientWindow extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtMessage;
	private JTextArea txtHistory;
	private DefaultCaret caret;
	private boolean running = false;

	private Thread run, listen;
	private RandomRoll roll;
	private Client client;
	private Server server;

	public ClientWindow(String name, String address, int port) {
		setTitle("Chat Client");
		client = new Client(name, address, port);

		boolean connect = client.openConnection(address);

		if (!connect) {

			System.err.println("Connection Failed");
			console("Connection Failed");
		}
		createWindow();
		console("Attempting to connect to " + address + " : " + port + " user " + name + "...");
		String connection = "/c/" + name;
		client.send(connection.getBytes());
		run = new Thread(this, "Running");
		running = true;
		run.start();

	}

	private void createWindow() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 28, 815, 30, 7 }; // sum
		gbl_contentPane.rowHeights = new int[] { 50, 460, 40 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE, };
		contentPane.setLayout(gbl_contentPane);

		txtHistory = new JTextArea();
		txtHistory.setEditable(false);
		caret = (DefaultCaret) txtHistory.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scroll = new JScrollPane(txtHistory);

		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
		scrollConstraints.fill = GridBagConstraints.BOTH;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.insets = new Insets(0, 5, 0, 0);
		contentPane.add(scroll, scrollConstraints);

		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(txtMessage.getText());
					// RollRandom(txtMessage.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 5, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				send(txtMessage.getText());
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);

		setVisible(true);
		txtMessage.requestFocusInWindow();
	}

	public void run() {
		listen();
	}

	private void send(String message) {
		if (message.equals("")) return;

		if (message.equals("1")) {
			message = "IN THE NAME OF ALL THAT IS HOLY SOMEONE HAS CONNECTED YOU TO RYANS BUTTHOLE";
			message = "/a/" + message;

			//new RandomRoll();
		} else {
			message = client.getName() + ": " + message;
			message = "/m/" + message;
		}
		client.send(message.getBytes());
		txtMessage.setText("");

	}

	public void listen() {
		listen = new Thread("Listen") {
			public void run() {
				while (running) {
					String message = client.receive();
					if (message.startsWith("/c/")) {
						client.setID(Integer.parseInt(message.split("/c/|/e/")[1]));
						console("Successfully connected to server! " + client.getID());
					} else if (message.startsWith("/m/")) {
						String text = message.split("/m/|/e/")[1];
						
						console(text);

					} else if (message.startsWith("/a/")) {
						String text1 = message.split("/a/|/e/")[1];
						new RandomRoll();
						console(text1);

					}
				}
			}
		};
		listen.start();
	}

	public void console(String message) {
		txtHistory.append(message + "\n\r");
		txtHistory.setCaretPosition(txtHistory.getDocument().getLength());
	}

}
