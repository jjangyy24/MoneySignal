package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Biz.MoneyBiz;
import dto.CommDTO;
import dto.UserDTO;

public class Login extends JFrame {

	//ȭ�鿡�� ������ ����
	JTextField IdField; // ���̵� �ʵ�
	JPasswordField PwdField; // ��й�ȣ �ʵ�
	JScrollPane scrollPane;
	ImageIcon icon;
	JButton NewMemberButton, LoginButton; // ȸ������ ��ư , �α��� ��ư

	//�����ͺ��̽�
	//UserDTO udto;
	//int total;
	//int ctotal;
	//CommDTO cdto = null;
	//MoneyBiz biz = null;
    //String id = null;
	//String pw = null;
	
	public Login() {
		init();
		super.setBounds(10, 10, 847, 660);
		super.setResizable(false);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//super.setVisible(true);
	}
	
	public void init() {
		//������ ó��
		//udto = new UserDTO();
		//biz = new MoneyBiz();
		
		icon = new ImageIcon("new2.png");
		
		JPanel picture = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		picture.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		lblNewLabel.setBounds(248, 358, 62, 18);
		picture.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PWD");
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 20));
		lblNewLabel_1.setBounds(235, 388, 62, 18);
		picture.add(lblNewLabel_1);

		IdField = new JTextField(); // ���̵� �ʵ�
		IdField.setBounds(315, 359, 116, 24);
		picture.add(IdField);
		IdField.setColumns(10);

		PwdField = new JPasswordField(); // ��й�ȣ �ʵ�
		PwdField.setBounds(315, 389, 116, 24);
		picture.add(PwdField);

		NewMemberButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		NewMemberButton.setForeground(Color.PINK);
		NewMemberButton.setFont(new Font("���� ���", Font.BOLD, 15));
		NewMemberButton.setBounds(489, 356, 105, 27);
		picture.add(NewMemberButton);

		LoginButton = new JButton("\uB85C\uADF8\uC778");
		LoginButton.setForeground(Color.PINK);
		LoginButton.setFont(new Font("���� ���", Font.BOLD, 15));
		LoginButton.setBounds(489, 386, 105, 27);
		picture.add(LoginButton);

		scrollPane = new JScrollPane(picture);
		// setContentPane(scrollPane);

		super.add(picture);
		super.setLayout(new GridLayout(1, 1));
		
		//�̺�Ʈ ó���� main����
	}
	
/*	public static void main(String[] args) {
		new Login();
	}*/
	
}
