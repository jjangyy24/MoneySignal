package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Biz.MoneyBiz;

public class NewMember extends JFrame{
	JTextField nameField;//ÀÌ¸§
	JTextField idField;//È¸¿ø°¡ÀÔ½Ã »ç¿ëÇÒ ¾ÆÀÌµð
	JTextField pwField;//ºñ¹Ð¹øÈ£
	JScrollPane scrollPane;
	ImageIcon icon;
	JButton joinButton;
   
	public NewMember() {
		 super.setLayout(new BorderLayout());
		
		icon = new ImageIcon("new1.png");
	      
	    JPanel picture = new JPanel() {
	        public void paintComponent(Graphics g) {
	             g.drawImage(icon.getImage(), 0, 0, null);
	             setOpaque(false); 
	             super.paintComponent(g);
	       }
	   };
	      
	   picture.setLayout(null);
	   
	   JLabel lblNewLabel = new JLabel("Name");
	      lblNewLabel.setForeground(Color.PINK);
	      lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	      lblNewLabel.setBounds(222, 283, 72, 27);
	      picture.add(lblNewLabel);
	      
	      JLabel lblNewLabel_1 = new JLabel("ID");
	      lblNewLabel_1.setForeground(Color.PINK);
	      lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	      lblNewLabel_1.setBounds(227, 334, 62, 18);
	      picture.add(lblNewLabel_1);
	      
	      JLabel lblPwd = new JLabel("PWD");
	      lblPwd.setForeground(Color.PINK);
	      lblPwd.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	      lblPwd.setBounds(226, 376, 62, 18);
	      picture.add(lblPwd);
	      
	      nameField = new JTextField();
	      nameField.setBounds(375, 285, 116, 24);
	      picture.add(nameField);
	      nameField.setColumns(10);
	      
	      idField = new JTextField();
	      idField.setBounds(375, 326, 116, 24);
	      picture.add(idField);
	      idField.setColumns(10);
	      
	      pwField = new JTextField();
	      pwField.setBounds(375, 370, 116, 24);
	      picture.add(pwField);
	      pwField.setColumns(10);
	      
	      joinButton = new JButton("JOIN");
	      joinButton.setForeground(Color.PINK);
	      joinButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	      joinButton.setBounds(555, 328, 105, 27);
	      picture.add(joinButton);
	      
	      scrollPane = new JScrollPane(picture);
	      super.add(picture,"Center");
	    
	   super.setBounds(10, 10, 820, 650);
	   //super.setVisible(true);
	   
	   super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}