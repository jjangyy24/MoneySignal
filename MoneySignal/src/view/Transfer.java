package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Transfer extends JFrame{
	JPanel contentPane;
	ImageIcon icon;
	JTextField transferField;//이체금액
	JTextField accountField;//계좌번호
	JTextField bankField;//은행
	JTextField aconameField;//예금주
	JButton sendButton, cancelButton;  //송금,취소 버튼
	
	public Transfer() {
		init();//화면구성
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		super.setBounds(100, 200, 830, 640);
		//super.setVisible(true);
	}
	
	public void init() {
		icon = new ImageIcon("new1.png");
		JPanel picture = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);

			}
		};
		
		picture.setLayout(null);

		JLabel lblNewLabel = new JLabel("이체금액 :");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setBounds(230, 244, 76, 18);
		picture.add(lblNewLabel);
		
		JLabel label = new JLabel("계좌번호 :");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setForeground(Color.PINK);
		label.setBounds(230, 274, 76, 18);
		picture.add(label);

		JLabel label_1 = new JLabel("은행 :");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setForeground(Color.PINK);
		label_1.setBounds(258, 304, 44, 18);
		picture.add(label_1);

		JLabel label_2 = new JLabel("예금주 :");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setForeground(Color.PINK);
		label_2.setBounds(244, 334, 62, 18);
		picture.add(label_2);

		transferField = new JTextField(); // 이체 금액 입력창
		transferField.setBounds(306, 241, 222, 24);
		picture.add(transferField);
		transferField.setColumns(10);

		/*setAccountField(new JTextField()); // 계좌번호 입력창
		getAccountField().setColumns(10);
		getAccountField().setBounds(306, 271, 222, 24);
		picture.add(getAccountField());*/
		
		accountField=new JTextField();
		accountField.setColumns(10);
		accountField.setBounds(306,271,222,24);
		picture.add(accountField);
		
		bankField = new JTextField(); // 은행 입력
		bankField.setColumns(10);
		bankField.setBounds(306, 301, 222, 24);
		picture.add(bankField);
		
		/*setAconameField(new JTextField()); // 예금주 입력
		getAconameField().setColumns(10);
		getAconameField().setBounds(306, 331, 222, 24);
		picture.add(getAconameField());*/
		
		aconameField=new JTextField();
		aconameField.setColumns(10);
		aconameField.setBounds(306,331,222,24);
		picture.add(aconameField);
		
		picture.setBorder(BorderFactory.createTitledBorder("<이체>"));

		sendButton = new JButton("송금");
		sendButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		sendButton.setForeground(Color.PINK);
		sendButton.setBounds(306, 379, 105, 27);
		picture.add(sendButton);

		cancelButton = new JButton("취소");
		cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		cancelButton.setForeground(Color.PINK);
		cancelButton.setBounds(423, 379, 105, 27);
		picture.add(cancelButton);
		
		setLayout(new BorderLayout());
		super.add(picture, "Center");
		
	}
	
	//이거왜만듦? --원래 다 static 함수였음
	/*public JTextField getAccountField() {
		return accountField;
	}

	public void setAccountField(JTextField accountField) {
		accountField = accountField;
	}

	public JTextField getAconameField() {
		return aconameField;
	}

	public void setAconameField(JTextField aconameField) {
		aconameField = aconameField;
	}*/
	
	/*public static void main(String[] args) {
		new Transfer();
	}*/
}
