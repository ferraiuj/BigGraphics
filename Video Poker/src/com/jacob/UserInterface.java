package com.jacob;

import java.awt.Dimension;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JButton cardOne;
	public JPanel contentPane;
	private int cardSpacing = 0;

	public UserInterface() {

		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(1000, 400));
		// cardOne = new JButton();
		// cardOne.setBounds(0, 0, 126, 176);
		contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(1000, 400));
		// contentPane.add(addButtons());

		frame.setContentPane(contentPane);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	public void showHand(Card hand) {
		
		JButton card = new JButton(hand.getCard() + " " +  hand.getSuitName());
		card.setBounds(cardSpacing, 0, 126, 176);
		contentPane.add(card);
	
		cardSpacing += 126;

	}
}
