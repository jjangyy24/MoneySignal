package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Biz.MoneyBiz;
import dto.CommDTO;

public class Search extends JFrame{
	JPanel contentPane;
	JTextField pwField;// ºñ¹Ð¹øÈ£
	JTextField accountField;// °èÁÂ¹øÈ£
	ImageIcon icon;
	JButton particiButton, searchButton; // Âü°¡¹öÆ°
	
	public Search() {
		init();
		super.setBounds(100, 200, 830, 640);
		//super.setVisible(true);

	}
	
	public void init() {
		icon = new ImageIcon("new1.png");

		JPanel picture = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		picture.setLayout(null);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		pwField = new JTextField();
		pwField.setBounds(300, 293, 310, 30);
		picture.add(pwField);
		pwField.setColumns(10);

		accountField = new JTextField();
		accountField.setColumns(10);
		accountField.setBounds(300, 251, 310, 30);
		picture.add(accountField);

		JLabel label = new JLabel("ºñ¹Ð¹øÈ£ :");
		label.setBounds(193, 299, 100, 18);
		label.setForeground(Color.PINK);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		picture.add(label);

		JLabel label_1 = new JLabel("°èÁÂ¹øÈ£ :");
		label_1.setBounds(193, 257, 100, 18);
		label_1.setForeground(Color.PINK);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		picture.add(label_1);

		particiButton = new JButton("Âü°¡");
		particiButton.setBounds(336, 389, 105, 27);
		particiButton.setForeground(Color.PINK);
		particiButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		picture.add(particiButton);

		searchButton = new JButton("°Ë»ö");
		searchButton.setBounds(460, 389, 105, 27);
		searchButton.setForeground(Color.PINK);
		searchButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		picture.add(searchButton);

		picture.setBorder(BorderFactory.createTitledBorder("<°Ë»ö>"));
		super.add(picture);
		getContentPane().setLayout(new GridLayout(1, 1));

	}
	
}
