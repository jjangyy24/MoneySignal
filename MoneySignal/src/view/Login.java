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

	//화면에서 보여줄 변수
	JTextField IdField; // 아이디 필드
	JPasswordField PwdField; // 비밀번호 필드
	JScrollPane scrollPane;
	ImageIcon icon;
	JButton NewMemberButton, LoginButton; // 회원가입 버튼 , 로그인 버튼

	//데이터베이스
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
		//데이터 처리
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
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(248, 358, 62, 18);
		picture.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PWD");
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(235, 388, 62, 18);
		picture.add(lblNewLabel_1);

		IdField = new JTextField(); // 아이디 필드
		IdField.setBounds(315, 359, 116, 24);
		picture.add(IdField);
		IdField.setColumns(10);

		PwdField = new JPasswordField(); // 비밀번호 필드
		PwdField.setBounds(315, 389, 116, 24);
		picture.add(PwdField);

		NewMemberButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		NewMemberButton.setForeground(Color.PINK);
		NewMemberButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		NewMemberButton.setBounds(489, 356, 105, 27);
		picture.add(NewMemberButton);

		LoginButton = new JButton("\uB85C\uADF8\uC778");
		LoginButton.setForeground(Color.PINK);
		LoginButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LoginButton.setBounds(489, 386, 105, 27);
		picture.add(LoginButton);

		scrollPane = new JScrollPane(picture);
		// setContentPane(scrollPane);

		super.add(picture);
		super.setLayout(new GridLayout(1, 1));
		
		//이벤트 처리는 main에서
	}
	
/*	public static void main(String[] args) {
		new Login();
	}*/
	
}
