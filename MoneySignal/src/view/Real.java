package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Biz.MoneyBiz;

public class Real extends JFrame {
	 JButton yesButton, noButton; // ��, �ƴϿ� ��ư
	 JLabel reallb, real2lb; // "���� Ż���Ͻðڽ��ϱ�?"
	 MoneyBiz biz = null;

	 public Real() {

	    super.setLayout(null);
	    super.setSize(300, 170);
	    super.setLocationRelativeTo(null);
	    
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    init();
	    //setVisible(true);

	   }

	   private void init() {

	      yesButton = new JButton("Yes");
	      yesButton.setFont(new Font("���� ���", Font.BOLD, 15));
	      yesButton.setForeground(Color.PINK);
	      yesButton.setBounds(55, 65, 70, 30);
	      super.add(yesButton);

	      noButton = new JButton("No");
	      noButton.setFont(new Font("���� ���", Font.BOLD, 15));
	      noButton.setForeground(Color.PINK);
	      noButton.setBounds(155, 65, 70, 30);
	      super.add(noButton);

	      reallb = new JLabel("���� �����Ͻðڽ��ϱ�?");
	      reallb.setFont(new Font("���� ���", Font.BOLD, 12));
	      reallb.setForeground(Color.BLACK);
	      reallb.setBounds(55, -20, 170, 100);
	      super.add(reallb);

	   }
}
