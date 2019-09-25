package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Transfer2 extends JFrame{
	JPanel contentPane;
	JPasswordField pwdField; //비밀번호 입력필드
	ImageIcon icon;
	JButton okButton; //확인

	public Transfer2() {
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
		
		JLabel lblNewLabel = new JLabel("비밀번호:");             
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setBounds(296, 285, 87, 18);
		picture.add(lblNewLabel);
	
		okButton = new JButton("확인");   //비밀번호 확인버튼
		okButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		okButton.setForeground(Color.PINK);
		okButton.setBounds(355, 360, 100, 25);
		picture.add(okButton);
		
		pwdField = new JPasswordField(); //비밀번호
		pwdField.setBounds(390, 283, 116, 24);
		picture.add(pwdField);
		pwdField.setColumns(10);
		
	
		setLayout(new GridLayout(1,1));
		super.add(picture);
	
	}
}
