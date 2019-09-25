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
   	JTextField pwdField;   // ��й�ȣ �Է��ʵ�
   	JTextField limitField; //�����ѵ� �ʵ�
   	JButton createButton;
   	JButton cancelButton;// ������ư
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
 
    JLabel label = new JLabel("��й�ȣ");  //��й�ȣ ����
    label.setForeground(Color.PINK);
    label.setFont(new Font("���� ���", Font.BOLD, 20));
    label.setBounds(261, 421, 113, 21);
    picture.add(label);
    
    pwdField = new JTextField();
    pwdField.setBounds(378, 423, 105, 24);
    picture.add(pwdField);
    pwdField.setColumns(10);
    
    createButton = new JButton("����");  //�������� �����ϴ� ��ư
    createButton.setForeground(Color.PINK);
    createButton.setFont(new Font("���� ���", Font.BOLD, 20));
    createButton.setBounds(280, 487, 105, 27);
    picture.add(createButton);
    
//    picture.setBorder(BorderFactory.createTitledBorder("<�������� ����>"));
    lblNewLabel = new JLabel("");
    //String.valueOf(Basic2.cdto.getC_account()) : ���߿� ����
    //������ ���¹�ȣ
    lblNewLabel.setBounds(215, 212, 390, 61);
    picture.add(lblNewLabel);
    lblNewLabel.setBorder(BorderFactory.createTitledBorder
      		(null, "���¹�ȣ", TitledBorder.CENTER, TitledBorder.TOP, new Font("���� ���", Font.BOLD,20), Color.PINK));
       
    JLabel lblNewLabel_1 = new JLabel("���� %");   //����%
    lblNewLabel_1.setForeground(Color.PINK); 
    lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 20));
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
       
    JLabel lblNewLabel_2 = new JLabel("�����ѵ�");   // �����ѵ�
    lblNewLabel_2.setForeground(Color.PINK);
    lblNewLabel_2.setFont(new Font("���� ���", Font.BOLD, 20));
    lblNewLabel_2.setBounds(261, 382, 87, 27);
    picture.add(lblNewLabel_2);
       
    limitField = new JTextField();
    limitField.setBounds(378, 387, 105, 24);
    picture.add(limitField);
    limitField.setColumns(10);
       
    JLabel lblNewLabel_3 = new JLabel("����");
    lblNewLabel_3.setFont(new Font("���� ���", Font.BOLD, 20));
    lblNewLabel_3.setForeground(Color.PINK);
    lblNewLabel_3.setBounds(497, 385, 62, 24);
    picture.add(lblNewLabel_3);
       
    cancelButton = new JButton("���");
    cancelButton.setForeground(Color.PINK);
    cancelButton.setFont(new Font("���� ���", Font.BOLD, 20));
    cancelButton.setBounds(427, 487, 105, 27);
    picture.add(cancelButton);
       
    super.add(picture);
       
   }
 }



   
