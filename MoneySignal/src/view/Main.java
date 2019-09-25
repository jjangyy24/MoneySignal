package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Biz.MoneyBiz;
import dto.CommDTO;
import dto.UserDTO;

public class Main extends JFrame implements ActionListener,ItemListener,Runnable{

	private JPanel contentPane;
	JPanel cardPane;
	ImageIcon icon;
	JButton mytransButton, mydetailButton, commtransbutton, commdetailbutton;  //���� ��ü ,���λ�, ������ü, �����󼼹�ư
	JButton searchButton,commjoinButton;
	JButton exitbutton,chatbutton;
	
	//static JLabel lblNewLabel_1;
	
	//��񿡼� ������ ���� ����
	static String id;
	String pw;
	static int total;
	static int ctotal;
	long myaccount;
	long commaccount;
	
	//�����ͺ��̽����� ó���Ұ�
	MoneyBiz biz=new MoneyBiz();
	UserDTO udto;
	CommDTO cdto;
	
	//������ ��ü ����
	NewMember newMember=new NewMember();
	Login login=new Login();
	Transfer transfer=new Transfer();
	Detail1 detail1;
	Detail2 detail2;
	Search search=new Search();
	CommAco commaco=new CommAco();
	Transfer2 transfer2=new Transfer2();
	Real real=new Real();
	
	//�ݾ׺�����
	JLabel lblNewLabel;//���ݾ�
	JLabel lblNewLabel_1;//���ԵǾ������� �������� ��
	JLabel lblNewLabel_2;//x
	
	//��Ʈ��ũ
	Socket s;
	BufferedReader in; 
	BufferedWriter out;
	

	public Main() {
		System.out.println("���� ����");
		login.setVisible(true);
		
		//Login �̺�Ʈ ������ ���
		login.NewMemberButton.addActionListener(this);//ȸ������
		login.LoginButton.addActionListener(this);//�α��ι�ư
		
		init();//main ȭ��	
		//ó���� ��簴ü�� �Ⱥ����ߵ�
			
		//ȸ������ �̺�Ʈ ������ ���
		newMember.joinButton.addActionListener(this);
				
		//����Ŭ���� �̺�Ʈ ������ ���
		mytransButton.addActionListener(this);
		mydetailButton.addActionListener(this);
		commtransbutton.addActionListener(this);
		commdetailbutton.addActionListener(this);
		
		//ê��,Ż��
		exitbutton.addActionListener(this);
        chatbutton.addActionListener(this);

		//�������� ������ �̺�Ʈ ������
		searchButton.addActionListener(this);
		commjoinButton.addActionListener(this);
		
		//Transfer �̺�Ʈ ������ ���
		transfer.sendButton.addActionListener(this);
		transfer.cancelButton.addActionListener(this);
			
		//Search �̺�Ʈ ������ ���
		search.particiButton.addActionListener(this);
		search.searchButton.addActionListener(this);
		
		//CommAco �̺�Ʈ ������
		commaco.createButton.addActionListener(this);
		commaco.cancelButton.addActionListener(this);
		commaco.choice.addItemListener(this);
		
		//transfer �̺�Ʈ ������ ���
		transfer2.okButton.addActionListener(this);
		
		//Ż�� Ŭ����
		real.yesButton.addActionListener(this);
		real.noButton.addActionListener(this);
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
		setTitle("�⺻â");
		
		super.setBounds(10, 10, 830, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//������ü
		mytransButton = new JButton("��ü"); 
		mytransButton.setFont(new Font("���� ���", Font.BOLD, 15));
		mytransButton.setForeground(Color.PINK);
		mytransButton.setBounds(410, 243, 65, 27);
		picture.add(mytransButton);
		
		//���λ�
		mydetailButton = new JButton("��");  // ���� ��
		mydetailButton.setFont(new Font("���� ���", Font.BOLD, 15));
		mydetailButton.setForeground(Color.PINK);
		mydetailButton.setBounds(494, 243, 65, 27);
		picture.add(mydetailButton);
		
		//�����������ִ� ��
		//JLabel lblNewLabel = new JLabel(String.valueOf(total)+"��");
		lblNewLabel = new JLabel(total + "��");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setBounds(183, 215, 390, 45);
		picture.add(lblNewLabel);
		//lblNewLabel.setBorder(BorderFactory.createTitledBorder("������[" + myaccount+ "]"));
		
		//���ο�� �����ִ�â-->��ü���� �޾ƿ� �� �������� ����
		lblNewLabel_1 = new JLabel(ctotal+"��");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setBounds(183, 385, 390, 45);
		picture.add(lblNewLabel_1);
		lblNewLabel_1.setBorder(BorderFactory.createTitledBorder("��������[" + commaccount + "]"));
		
		//������ü��ư
		
		commtransbutton = new JButton("��ü");
		commtransbutton.setFont(new Font("���� ���", Font.BOLD, 15));
		commtransbutton.setForeground(Color.PINK);
		commtransbutton.setBounds(410, 412, 65, 27);
		commtransbutton.setVisible(false);
		picture.add(commtransbutton);
		
		//�󼼹�ư
		commdetailbutton = new JButton("��");
		commdetailbutton.setFont(new Font("���� ���", Font.BOLD, 15));
		commdetailbutton.setForeground(Color.PINK);
		commdetailbutton.setBounds(494, 412, 65, 27);
		commdetailbutton.setVisible(false);
		picture.add(commdetailbutton);
		
		
		//�������� ������ 
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBounds(206, 359, 390, 84);
		lblNewLabel_2.setBorder(BorderFactory.createTitledBorder("��������[]"));
		picture.add(lblNewLabel_2);
		//String comaco = null;
		
			
		searchButton = new JButton("����/�˻�");       //�������·� �����ϴ� �˻���ư
		searchButton.setFont(new Font("���� ���", Font.BOLD, 15));
		searchButton.setForeground(Color.PINK);
		searchButton.setBounds(285, 390, 110, 27);
		picture.add(searchButton);
			
		commjoinButton = new JButton("����");       //�������� �����ϴ� ��ư
		commjoinButton.addActionListener(this);
		commjoinButton.setFont(new Font("���� ���", Font.BOLD, 15));
		commjoinButton.setForeground(Color.PINK);
		commjoinButton.setBounds(455, 390, 65, 27);
		picture.add(commjoinButton);
			
		//
		exitbutton = new JButton("Ż��");
        exitbutton.setFont(new Font("���� ���", Font.BOLD, 15));
        exitbutton.setForeground(Color.PINK);
        exitbutton.setBounds(710, 530, 65, 27);
        picture.add(exitbutton);
      
        chatbutton = new JButton("ê��");
        chatbutton.setFont(new Font("���� ���", Font.BOLD, 15));
        chatbutton.setForeground(Color.PINK);
        chatbutton.setBounds(620, 530, 65, 27);
        picture.add(chatbutton);


		setLayout(new BorderLayout());
		super.add(picture,"Center");
		//super.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String msg=in.readLine();
				System.out.println("client�� ���� : "+msg);
				Handle(msg);
//				StringTokenizer st=new StringTokenizer(msg,"|");
//				int protocol=Integer.parseInt(st.nextToken());
//				
//				switch(protocol) {
//				case 1:
//					System.out.println(st.nextToken()+"�� �α��� �Ͽ����ϴ�.");
//					break;
//				case 2:
//					break;
//				}
			} catch (IOException e) {}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==login.NewMemberButton) {
			newMember.setVisible(true);//��â���� �ߴ� ���� �߻�
		}else if(e.getSource()==newMember.joinButton) {
			System.out.println("ȸ�����Թ�ư");
			newmemberJoin();
		}else if(e.getSource()==login.LoginButton){
			System.out.println("�α��ι�ư ����");
			LoginloginButton();
		}else if(e.getSource()==this.mytransButton) {
			transfer.setVisible(true);//ó���� ��ü �ڵ����� �ߴ� ���� �ذ�
		}else if(e.getSource()==this.mydetailButton) {
			detail1=new Detail1(udto.getU_account());
			detail1.setVisible(true);//�� �����غ��� ����
		}else if(e.getSource()==this.commtransbutton) {
			commtransButton();
		}else if(e.getSource()==this.commdetailbutton) {
			detail2=new Detail2(cdto.getC_account());
			detail2.setVisible(true);
		}else if(e.getSource()==transfer.sendButton) {
			TransferSendButton();
		}else if(e.getSource()==transfer.cancelButton) {
			//new Basic1();--�ٽ� �������� ���ƿ���
			transfer.dispose();
		}else if(e.getSource()==this.searchButton) {
			lblNewLabel.setText(String.valueOf(cdto.getC_account()));
			search.setVisible(true);
			//searchButton();
		}else if(e.getSource()==this.commjoinButton) {
		   commjoinButton();
		}else if(e.getSource()==search.particiButton) {
			particiButton();
		}else if(e.getSource()==search.searchButton) {
			searchButton();
		}else if(e.getSource()==commaco.createButton) {
			createButton();
		}else if(e.getSource()==commaco.cancelButton) {
			cancelButton();
		}else if(e.getSource()==transfer2.okButton) {
			okButton();
		}else if(e.getSource()==real.yesButton) {
			yesButton();
		}else if(e.getSource()==real.noButton) {
			real.dispose();
		}else if(e.getSource()==this.exitbutton) {
			real.setVisible(true);
		}
	}
	
	//�ڵ鷯
	public void Handle(String msg) {
		
		String[] sm = msg.split("#");
			
		if(id.equals(sm[1])) {
			JOptionPane.showMessageDialog(this, sm[0]+"���� "+sm[2]+"���� ���½��ϴ�.");
		}
		
		
	}
	
	public void yesButton() {
		if(biz.Login_Commdto(id)!=null) {
			 int num = JOptionPane.showConfirmDialog(null, "<html>�������°� �ֽ��ϴ�.<br> ��ü�Ͻðڽ��ϱ�?<html>", "Ȯ��",
	                  JOptionPane.YES_NO_OPTION);
			 if(num==JOptionPane.YES_NO_OPTION) {
				 int y=biz.delete_comm(id);
				 if(y>0) {
					 real.dispose();
					 int num1 = JOptionPane.showConfirmDialog(null, "<html>ȸ��Ż�� �Ͻðڽ��ϱ�?<html>", "Ȯ��",
		                        JOptionPane.YES_NO_OPTION);
		                  if(num1 == JOptionPane.YES_OPTION) {
		                     int x = biz.delete_user(id);
		                     if(x>0) {
		                        real.dispose();
		                        this.setVisible(true);
		                        
		                     }
		                  }
				 } else if (biz.Login_Commdto(id) == null) {
			            if (biz.totalPayment(udto.getU_account()) > 0) {
				               JOptionPane.showMessageDialog(this, "�ܾ��� 0���̾�� �����մϴ�.");
				               dispose();
				            }
				            if (biz.totalPayment(udto.getU_account()) == 0) {
				               int n = biz.delete_user(id);
				               if (n > 0) {
				                  JOptionPane.showMessageDialog(this, "Ż��Ǿ����ϴ�.");
				                  real.dispose();
				                  this.setVisible(true);
				               }

				            }

				         }
			 }
		                                               }
	}
	
	
	public void commtransButton() {
		System.out.println(ctotal+"  "+cdto.getC_limit());
		 if (ctotal >= cdto.getC_limit()) {
	           JOptionPane.showMessageDialog(this, "��ǥ�׼��� �����Ͽ� ��ü�� �����մϴ� ");
	           transfer.setVisible(true);
	           //dispose();
	    } else {
	         JOptionPane.showMessageDialog(this,
	                  "��ǥ�׼��� �������� ���� ����� �Ұ����մϴ�\n" + "��ǥ�� :" + cdto.getC_limit() + "��");  
	    }
	   
	}
	
	public void okButton() {
		if(transfer2.pwdField.getText().equals(udto.getU_pwd())) {
			//��Ʈ��ũ
			System.out.println("�� ������ ��� :"+cdto.getCf_user());
			if(cdto.getCf_user().equals(id)) {//������ ����� cf_user�̸�
				//�������� ������
				try {
					System.out.println("�� �޴� ���"+cdto.getCs_user());
					out.write(id+"#"+cdto.getCs_user()+"#"+transfer.transferField.getText());
					out.newLine();
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {//����������� cs_user�̸�
				try {
					out.write(id+"#"+cdto.getCf_user()+"#"+transfer.transferField.getText()+"#"+null);
					out.newLine();
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			transfer2.dispose();
		}
		
	}

	public void commjoinButton() {
		 int n=biz.C_insertaccount(id);       
		 System.out.println(id+"�� "+n);
	        //���� ��ư Ŭ���� ������ ���̵� ���� ��ȣ ������ ���� ����â�� ���� ǥ��
	     cdto=biz.Basic2_callCdata(id);
	     System.out.println();
	     commaco.setVisible(true);
	     setVisible(false);
	     
	}
	
	public void createButton() {
		int n=biz.createC_account((commaco.choiceitem/100), Integer.parseInt(commaco.limitField.getText()), commaco.pwdField.getText(), id);
		
		if(n>0) {
			int x=biz.Caccount_PayDTO(Integer.parseInt(commaco.limitField.getText()));
			cdto=biz.Login_Commdto(id);
			
			//new Basic1();-->�ٽ� ���� ����
			commaco.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "���� ������ �����߽��ϴ�.");
		}
	}
	
	public void cancelButton() {
		int n=biz.deleteC_account(id);
		if(n>0) {
			commaco.dispose();
			//new Basic2();
		}
	}
	
	public void LoginloginButton() {
		id=login.IdField.getText();
		pw=String.valueOf(login.PwdField.getPassword()).toString();
		
		udto=new UserDTO();
		udto = biz.loginUser(id, pw);
		System.out.println(udto.getU_account());
		cdto = biz.Login_Commdto(id);
		
		//System.out.println(udto.getU_account());
		//System.out.println(cdto.getC_account());
		
		if (udto != null && udto.getU_pwd().equals(pw)) {
			total = biz.totalPayment(udto.getU_account());
			System.out.println(total);
			myaccount=udto.getU_account();
			System.out.println(myaccount);
			
			//�ٽ� ���� ���� �ٲ���
			lblNewLabel.setText(String.valueOf(total+"��"));//�󺧿� ���� ����
			lblNewLabel.setBorder(BorderFactory.createTitledBorder("������[" + myaccount + "]"));
			
			if (cdto != null) {
				//��������
				ctotal = biz.totalPayment(cdto.getC_account());
				System.out.println("�������� ���� :"+ctotal);
				
				commaccount=cdto.getC_account();
				System.out.println("���Ⱑ �¤���"+commaccount);
				
				lblNewLabel_1.setVisible(true);
				lblNewLabel_2.setVisible(false);
				//����, ���� ���ֱ�
				searchButton.setVisible(false);
				commjoinButton.setVisible(false);
				commdetailbutton.setVisible(true);
				commtransbutton.setVisible(true);
				
				lblNewLabel_1.setText(String.valueOf(ctotal+"��"));
				lblNewLabel_1.setBorder(BorderFactory.createTitledBorder("��������[" + commaccount + "]"));
				
				//��Ʈ��ũ
				try {
					s=new Socket("localhost",7000);
					in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					
					out.write(id);
					out.newLine();
					out.flush();
					System.out.println("������ ���� : "+id);
					new Thread(this).start();
					
				} catch (Exception e) {}
				
				login.setVisible(false);
				this.setVisible(true);
			} else {//cdto ������
				System.out.println(1+""+myaccount);
				//this.revalidate();
				//this.repaint();
				//�������°� ������ com�����ֱ�
				
				lblNewLabel_1.setVisible(false);
				lblNewLabel_2.setVisible(true);
				//����, ���� ���ֱ�
				searchButton.setVisible(true);
				commjoinButton.setVisible(true);
				commdetailbutton.setVisible(false);
				commtransbutton.setVisible(false);
				
				this.setVisible(true);
				login.dispose();
				//new Basic2(); // �������� ���� ����ȭ��
				//dispose();
			}
			//���� �� �ٽ� �־��ֱ�
			
			
		} else {
			JOptionPane.showMessageDialog(this, "���̵� ��й�ȣ�� Ȯ�����ּ��� ");
		}

	}
	
	public void TransferSendButton() {//���� ���� ��ü ��������
		int x;
		float y;
		if (		transfer.transferField.getText().equals("") 
				|| 	transfer.accountField.getText().equals("") 
				|| 	transfer.bankField.getText().equals("")
				|| 	transfer.aconameField.getText().equals("")) {
			JOptionPane.showMessageDialog(transfer, "������ Ȯ�����ּ���");
		}else {
			try {
				x =Integer.parseInt(transfer.transferField.getText());
				System.out.println(cdto.getC_save());
				y =x+(x*cdto.getC_save());
                System.out.println(x+"  "+y);
				if (total >= y) {
					int ot = biz.bankchecked(transfer.transferField.getText(), transfer.bankField.getText(), transfer.aconameField.getText());
                     System.out.println("��ü ����?"+ot);
					if (ot == 1) {
						transfer2.setVisible(true);
						transfer.dispose();
					 }else {
						 JOptionPane.showMessageDialog(transfer, "��ü����(������,�̸�,����)�� Ȯ�����ּ���");
						 transfer.transferField.setText("");
						 transfer.bankField.setText("");
						 transfer.accountField.setText("");
					 }

				} else if (biz.totalPayment(udto.getU_account()) < x) {
					JOptionPane.showMessageDialog(this, "�ܾ��� Ȯ�����ּ���");
				}

			} catch (NumberFormatException ed) {
				JOptionPane.showMessageDialog(this, "message");
			}
		}
	}
	
	public void newmemberJoin() {
		int x= biz.NewMember_checked(newMember.idField.getText());  // ���̵� �ߺ� �˻� 
	    if(x==-1 && !newMember.nameField.getText().equals("")&& !newMember.idField.getText().equals("")
	    		 && !newMember.pwField.getText().equals("")){
	    	// �� ĭ �Է� �� �޾ƿ��� 
	    	int n=biz.signUp(newMember.nameField.getText(), newMember.idField.getText(), newMember.pwField.getText());
		                                   
	    	if(n>0) {
	    		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	    		newMember.nameField.setText("");
	    		newMember.idField.setText("");
	    		newMember.pwField.setText("");
	    	}
		
	    	//new Loginsu();// ���� �Ϸ��� �α������� ����
	    	newMember.dispose();// ���� â ��
		
		//���â
		}else if(x==1){
			JOptionPane.showMessageDialog(this, "���̵� �ֽ��ϴ�");   
		}else {
			JOptionPane.showMessageDialog(this, "���̵�,�̸�,��й�ȣ�� Ȯ�����ּ���");
		}
	}
	
	public void searchButton() {
		if (search.accountField.getText().equals("") || search.pwField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "����,��й�ȣ�� �Է����ּ���"); // �Է�â�� �����Ͻ� ���� ���
		} else {
			biz = new MoneyBiz();
			cdto = biz.searchcommdto(search.accountField.getText(), search.pwField.getText());
			if (cdto == null) {
				// �Է� ���� ���̺� ������� ���� ���
				JOptionPane.showMessageDialog(this, "���¹�ȣ �Ǵ� ��й�ȣ�� Ȯ�����ּ���"); 
			} else {
				//�Է� ���� ������ �� ������ ����â ���
				int x = JOptionPane.showConfirmDialog(this,
						"���� ��ȣ :" + cdto.getC_account() + "\n" + "������ ���̵� :" + cdto.getCf_user() + "\n" + "�ܾ�", "����Ȯ��",
						JOptionPane.YES_NO_OPTION);
				if (x != 0) {
				//û��â���� �ƴϿ� Ŭ���� �Է�ĭ�� ������
					search.accountField.setText("");
					search.pwField.setText("");
				}
			}
		}
	}
	
	public void particiButton() {
		int x = biz.partici_commdto(search.accountField.getText(), search.pwField.getText(),id);
		if(x>0) {
			JOptionPane.showMessageDialog(this, "���¸��߰��߽��ϴ�");
	    	 cdto = biz.Login_Commdto(id); 
	    	 
	    	 //this.dispose();
	         //new Basic1();
		}
	}
	

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		commaco.choiceitem = Float.parseFloat(commaco.choice.getSelectedItem());
	}

	
	public static void main(String[] args) {
		new Main();
	}



	
}
