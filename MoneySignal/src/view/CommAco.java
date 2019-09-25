package view;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Biz.MoneyBiz;
import dto.CommDTO;

import javax.swing.SwingConstants;

public class CommAco extends JFrame {

	JPanel contentPane;
   	JTextField pwdField;   // 비밀번호 입력필드
   	JTextField limitField; //저축한도 필드
   	JButton createButton;
   	JButton cancelButton;// 개설버튼
   	Choice choice;
   	Float choiceitem;
   	ImageIcon icon;
   	
   	JLabel lblNewLabel;
   	
   	static int x;
   
   
   	public CommAco() {
     init();
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     super.setBounds(10, 10, 840, 630);
     //super.setVisible(true);
   	}
   	
   public void init() {
	   icon = new ImageIcon("new1.png");
       getContentPane().setLayout(new GridLayout(1,1));
       
       JPanel picture = new JPanel() {
          public void paintComponent(Graphics g) {
               g.drawImage(icon.getImage(), 0, 0, null);
               setOpaque(false); 
               super.paintComponent(g);
           }
       };
       
 
    picture.setLayout(null);
 
    JLabel label = new JLabel("비밀번호");  //비밀번호 설정
    label.setForeground(Color.PINK);
    label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    label.setBounds(261, 421, 113, 21);
    picture.add(label);
    
    pwdField = new JTextField();
    pwdField.setBounds(378, 423, 105, 24);
    picture.add(pwdField);
    pwdField.setColumns(10);
    
    createButton = new JButton("개설");  //공동계좌 개설하는 버튼
    createButton.setForeground(Color.PINK);
    createButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    createButton.setBounds(280, 487, 105, 27);
    picture.add(createButton);
    
//    picture.setBorder(BorderFactory.createTitledBorder("<공동계좌 개설>"));
    lblNewLabel = new JLabel("");
    //String.valueOf(Basic2.cdto.getC_account()) : 나중에 들어가기
    //생성된 계좌번호
    lblNewLabel.setBounds(215, 212, 390, 61);
    picture.add(lblNewLabel);
    lblNewLabel.setBorder(BorderFactory.createTitledBorder
      		(null, "계좌번호", TitledBorder.CENTER, TitledBorder.TOP, new Font("맑은 고딕", Font.BOLD,20), Color.PINK));
       
    JLabel lblNewLabel_1 = new JLabel("저축 %");   //저축%
    lblNewLabel_1.setForeground(Color.PINK); 
    lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    lblNewLabel_1.setBounds(276, 345, 72, 18);
    picture.add(lblNewLabel_1);
       
    choice = new Choice();  
    choice.setBounds(378, 345, 65, 24);
    picture.add(choice);
       
    choice.add("5");
    choice.add("10");
    choice.add("15");
    choice.add("20");
    choice.add("25");
    choice.add("30");
    choice.add("35");
    choice.add("40");
       
    JLabel lblNewLabel_2 = new JLabel("저축한도");   // 저축한도
    lblNewLabel_2.setForeground(Color.PINK);
    lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    lblNewLabel_2.setBounds(261, 382, 87, 27);
    picture.add(lblNewLabel_2);
       
    limitField = new JTextField();
    limitField.setBounds(378, 387, 105, 24);
    picture.add(limitField);
    limitField.setColumns(10);
       
    JLabel lblNewLabel_3 = new JLabel("만원");
    lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    lblNewLabel_3.setForeground(Color.PINK);
    lblNewLabel_3.setBounds(497, 385, 62, 24);
    picture.add(lblNewLabel_3);
       
    cancelButton = new JButton("취소");
    cancelButton.setForeground(Color.PINK);
    cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
    cancelButton.setBounds(427, 487, 105, 27);
    picture.add(cancelButton);
       
    super.add(picture);
       
   }
 }



   
