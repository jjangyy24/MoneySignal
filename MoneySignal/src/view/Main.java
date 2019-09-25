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
	JButton mytransButton, mydetailButton, commtransbutton, commdetailbutton;  //개인 이체 ,개인상세, 공동이체, 공동상세버튼
	JButton searchButton,commjoinButton;
	JButton exitbutton,chatbutton;
	
	//static JLabel lblNewLabel_1;
	
	//디비에서 가져올 변수 선언
	static String id;
	String pw;
	static int total;
	static int ctotal;
	long myaccount;
	long commaccount;
	
	//데이터베이스에서 처리할거
	MoneyBiz biz=new MoneyBiz();
	UserDTO udto;
	CommDTO cdto;
	
	//보여줄 객체 생성
	NewMember newMember=new NewMember();
	Login login=new Login();
	Transfer transfer=new Transfer();
	Detail1 detail1;
	Detail2 detail2;
	Search search=new Search();
	CommAco commaco=new CommAco();
	Transfer2 transfer2=new Transfer2();
	Real real=new Real();
	
	//금액보여줌
	JLabel lblNewLabel;//내금액
	JLabel lblNewLabel_1;//가입되어있을때 공동계좌 돈
	JLabel lblNewLabel_2;//x
	
	//네트워크
	Socket s;
	BufferedReader in; 
	BufferedWriter out;
	

	public Main() {
		System.out.println("메인 생성");
		login.setVisible(true);
		
		//Login 이벤트 리스너 등록
		login.NewMemberButton.addActionListener(this);//회원가입
		login.LoginButton.addActionListener(this);//로그인버튼
		
		init();//main 화면	
		//처음엔 모든객체가 안보여야됨
			
		//회원가입 이벤트 리스너 등록
		newMember.joinButton.addActionListener(this);
				
		//메인클래스 이벤트 리스너 등록
		mytransButton.addActionListener(this);
		mydetailButton.addActionListener(this);
		commtransbutton.addActionListener(this);
		commdetailbutton.addActionListener(this);
		
		//챗봇,탈퇴
		exitbutton.addActionListener(this);
        chatbutton.addActionListener(this);

		//공동계좌 없을때 이벤트 리스너
		searchButton.addActionListener(this);
		commjoinButton.addActionListener(this);
		
		//Transfer 이벤트 리스너 등록
		transfer.sendButton.addActionListener(this);
		transfer.cancelButton.addActionListener(this);
			
		//Search 이벤트 리스너 등록
		search.particiButton.addActionListener(this);
		search.searchButton.addActionListener(this);
		
		//CommAco 이벤트 리스너
		commaco.createButton.addActionListener(this);
		commaco.cancelButton.addActionListener(this);
		commaco.choice.addItemListener(this);
		
		//transfer 이벤트 리스너 등록
		transfer2.okButton.addActionListener(this);
		
		//탈퇴 클래스
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
		setTitle("기본창");
		
		super.setBounds(10, 10, 830, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//개인이체
		mytransButton = new JButton("이체"); 
		mytransButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mytransButton.setForeground(Color.PINK);
		mytransButton.setBounds(410, 243, 65, 27);
		picture.add(mytransButton);
		
		//개인상세
		mydetailButton = new JButton("상세");  // 개인 상세
		mydetailButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mydetailButton.setForeground(Color.PINK);
		mydetailButton.setBounds(494, 243, 65, 27);
		picture.add(mydetailButton);
		
		//내정보보여주는 라벨
		//JLabel lblNewLabel = new JLabel(String.valueOf(total)+"원");
		lblNewLabel = new JLabel(total + "원");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setBounds(183, 215, 390, 45);
		picture.add(lblNewLabel);
		//lblNewLabel.setBorder(BorderFactory.createTitledBorder("내계좌[" + myaccount+ "]"));
		
		//새로운라벨 보여주는창-->객체에서 받아온 값 있을때만 실행
		lblNewLabel_1 = new JLabel(ctotal+"원");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.PINK);
		lblNewLabel_1.setBounds(183, 385, 390, 45);
		picture.add(lblNewLabel_1);
		lblNewLabel_1.setBorder(BorderFactory.createTitledBorder("공동계좌[" + commaccount + "]"));
		
		//공동이체버튼
		
		commtransbutton = new JButton("이체");
		commtransbutton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		commtransbutton.setForeground(Color.PINK);
		commtransbutton.setBounds(410, 412, 65, 27);
		commtransbutton.setVisible(false);
		picture.add(commtransbutton);
		
		//상세버튼
		commdetailbutton = new JButton("상세");
		commdetailbutton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		commdetailbutton.setForeground(Color.PINK);
		commdetailbutton.setBounds(494, 412, 65, 27);
		commdetailbutton.setVisible(false);
		picture.add(commdetailbutton);
		
		
		//공동계좌 없을때 
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBounds(206, 359, 390, 84);
		lblNewLabel_2.setBorder(BorderFactory.createTitledBorder("공동계좌[]"));
		picture.add(lblNewLabel_2);
		//String comaco = null;
		
			
		searchButton = new JButton("참가/검색");       //공동계좌로 참가하는 검색버튼
		searchButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		searchButton.setForeground(Color.PINK);
		searchButton.setBounds(285, 390, 110, 27);
		picture.add(searchButton);
			
		commjoinButton = new JButton("개설");       //공동계좌 개설하는 버튼
		commjoinButton.addActionListener(this);
		commjoinButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		commjoinButton.setForeground(Color.PINK);
		commjoinButton.setBounds(455, 390, 65, 27);
		picture.add(commjoinButton);
			
		//
		exitbutton = new JButton("탈퇴");
        exitbutton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        exitbutton.setForeground(Color.PINK);
        exitbutton.setBounds(710, 530, 65, 27);
        picture.add(exitbutton);
      
        chatbutton = new JButton("챗봇");
        chatbutton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
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
				System.out.println("client로 받음 : "+msg);
				Handle(msg);
//				StringTokenizer st=new StringTokenizer(msg,"|");
//				int protocol=Integer.parseInt(st.nextToken());
//				
//				switch(protocol) {
//				case 1:
//					System.out.println(st.nextToken()+"가 로그인 하였습니다.");
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
			newMember.setVisible(true);//빈창으로 뜨는 오류 발생
		}else if(e.getSource()==newMember.joinButton) {
			System.out.println("회원가입버튼");
			newmemberJoin();
		}else if(e.getSource()==login.LoginButton){
			System.out.println("로그인버튼 누름");
			LoginloginButton();
		}else if(e.getSource()==this.mytransButton) {
			transfer.setVisible(true);//처음에 이체 자동으로 뜨는 현상 해결
		}else if(e.getSource()==this.mydetailButton) {
			detail1=new Detail1(udto.getU_account());
			detail1.setVisible(true);//다 실행해보고 보기
		}else if(e.getSource()==this.commtransbutton) {
			commtransButton();
		}else if(e.getSource()==this.commdetailbutton) {
			detail2=new Detail2(cdto.getC_account());
			detail2.setVisible(true);
		}else if(e.getSource()==transfer.sendButton) {
			TransferSendButton();
		}else if(e.getSource()==transfer.cancelButton) {
			//new Basic1();--다시 메인으로 돌아오기
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
	
	//핸들러
	public void Handle(String msg) {
		
		String[] sm = msg.split("#");
			
		if(id.equals(sm[1])) {
			JOptionPane.showMessageDialog(this, sm[0]+"님이 "+sm[2]+"원을 보냈습니다.");
		}
		
		
	}
	
	public void yesButton() {
		if(biz.Login_Commdto(id)!=null) {
			 int num = JOptionPane.showConfirmDialog(null, "<html>공동계좌가 있습니다.<br> 해체하시겠습니까?<html>", "확인",
	                  JOptionPane.YES_NO_OPTION);
			 if(num==JOptionPane.YES_NO_OPTION) {
				 int y=biz.delete_comm(id);
				 if(y>0) {
					 real.dispose();
					 int num1 = JOptionPane.showConfirmDialog(null, "<html>회원탈퇴 하시겠습니까?<html>", "확인",
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
				               JOptionPane.showMessageDialog(this, "잔액이 0원이어야 가능합니다.");
				               dispose();
				            }
				            if (biz.totalPayment(udto.getU_account()) == 0) {
				               int n = biz.delete_user(id);
				               if (n > 0) {
				                  JOptionPane.showMessageDialog(this, "탈퇴되었습니다.");
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
	           JOptionPane.showMessageDialog(this, "목표액수에 도달하여 이체가 가능합니다 ");
	           transfer.setVisible(true);
	           //dispose();
	    } else {
	         JOptionPane.showMessageDialog(this,
	                  "목표액수에 도달하지 못해 출금이 불가능합니다\n" + "목표액 :" + cdto.getC_limit() + "원");  
	    }
	   
	}
	
	public void okButton() {
		if(transfer2.pwdField.getText().equals(udto.getU_pwd())) {
			//네트워크
			System.out.println("돈 보내는 사람 :"+cdto.getCf_user());
			if(cdto.getCf_user().equals(id)) {//돈보낸 사람이 cf_user이면
				//소켓으로 보내기
				try {
					System.out.println("돈 받는 사람"+cdto.getCs_user());
					out.write(id+"#"+cdto.getCs_user()+"#"+transfer.transferField.getText());
					out.newLine();
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {//돈보낸사람이 cs_user이면
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
		 System.out.println(id+"의 "+n);
	        //개설 버튼 클릭시 개설자 아이디 계좌 번호 생성후 계좌 생성창에 계좌 표시
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
			
			//new Basic1();-->다시 메인 띄우기
			commaco.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "계좌 개설에 실패했습니다.");
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
			
			//다시 라벨의 값을 바꿔줌
			lblNewLabel.setText(String.valueOf(total+"원"));//라벨에 값을 너줌
			lblNewLabel.setBorder(BorderFactory.createTitledBorder("내계좌[" + myaccount + "]"));
			
			if (cdto != null) {
				//서버연결
				ctotal = biz.totalPayment(cdto.getC_account());
				System.out.println("공동계좌 가격 :"+ctotal);
				
				commaccount=cdto.getC_account();
				System.out.println("여기가 맞ㄴ냐"+commaccount);
				
				lblNewLabel_1.setVisible(true);
				lblNewLabel_2.setVisible(false);
				//참가, 개설 없애기
				searchButton.setVisible(false);
				commjoinButton.setVisible(false);
				commdetailbutton.setVisible(true);
				commtransbutton.setVisible(true);
				
				lblNewLabel_1.setText(String.valueOf(ctotal+"원"));
				lblNewLabel_1.setBorder(BorderFactory.createTitledBorder("공동계좌[" + commaccount + "]"));
				
				//네트워크
				try {
					s=new Socket("localhost",7000);
					in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					
					out.write(id);
					out.newLine();
					out.flush();
					System.out.println("서버로 보냄 : "+id);
					new Thread(this).start();
					
				} catch (Exception e) {}
				
				login.setVisible(false);
				this.setVisible(true);
			} else {//cdto 없을때
				System.out.println(1+""+myaccount);
				//this.revalidate();
				//this.repaint();
				//공동계좌가 있으면 com보여주기
				
				lblNewLabel_1.setVisible(false);
				lblNewLabel_2.setVisible(true);
				//참가, 개설 없애기
				searchButton.setVisible(true);
				commjoinButton.setVisible(true);
				commdetailbutton.setVisible(false);
				commtransbutton.setVisible(false);
				
				this.setVisible(true);
				login.dispose();
				//new Basic2(); // 공동계좌 없는 메인화면
				//dispose();
			}
			//변한 값 다시 넣어주기
			
			
		} else {
			JOptionPane.showMessageDialog(this, "아이디 비밀번호를 확인해주세요 ");
		}

	}
	
	public void TransferSendButton() {//나의 계좌 이체 눌렀을때
		int x;
		float y;
		if (		transfer.transferField.getText().equals("") 
				|| 	transfer.accountField.getText().equals("") 
				|| 	transfer.bankField.getText().equals("")
				|| 	transfer.aconameField.getText().equals("")) {
			JOptionPane.showMessageDialog(transfer, "공란을 확인해주세요");
		}else {
			try {
				x =Integer.parseInt(transfer.transferField.getText());
				System.out.println(cdto.getC_save());
				y =x+(x*cdto.getC_save());
                System.out.println(x+"  "+y);
				if (total >= y) {
					int ot = biz.bankchecked(transfer.transferField.getText(), transfer.bankField.getText(), transfer.aconameField.getText());
                     System.out.println("이체 가능?"+ot);
					if (ot == 1) {
						transfer2.setVisible(true);
						transfer.dispose();
					 }else {
						 JOptionPane.showMessageDialog(transfer, "이체정보(상대계좌,이름,은행)를 확인해주세요");
						 transfer.transferField.setText("");
						 transfer.bankField.setText("");
						 transfer.accountField.setText("");
					 }

				} else if (biz.totalPayment(udto.getU_account()) < x) {
					JOptionPane.showMessageDialog(this, "잔액을 확인해주세요");
				}

			} catch (NumberFormatException ed) {
				JOptionPane.showMessageDialog(this, "message");
			}
		}
	}
	
	public void newmemberJoin() {
		int x= biz.NewMember_checked(newMember.idField.getText());  // 아이디 중복 검사 
	    if(x==-1 && !newMember.nameField.getText().equals("")&& !newMember.idField.getText().equals("")
	    		 && !newMember.pwField.getText().equals("")){
	    	// 각 칸 입력 값 받아오기 
	    	int n=biz.signUp(newMember.nameField.getText(), newMember.idField.getText(), newMember.pwField.getText());
		                                   
	    	if(n>0) {
	    		System.out.println("회원가입이 완료되었습니다.");
	    		newMember.nameField.setText("");
	    		newMember.idField.setText("");
	    		newMember.pwField.setText("");
	    	}
		
	    	//new Loginsu();// 가입 완료후 로그인으로 복귀
	    	newMember.dispose();// 가입 창 닫
		
		//경고창
		}else if(x==1){
			JOptionPane.showMessageDialog(this, "아이디가 있습니다");   
		}else {
			JOptionPane.showMessageDialog(this, "아이디,이름,비밀번호를 확인해주세요");
		}
	}
	
	public void searchButton() {
		if (search.accountField.getText().equals("") || search.pwField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "계좌,비밀번호를 입력해주세요"); // 입력창이 공란일시 오류 출력
		} else {
			biz = new MoneyBiz();
			cdto = biz.searchcommdto(search.accountField.getText(), search.pwField.getText());
			if (cdto == null) {
				// 입력 값이 테이블에 없을경우 오류 출력
				JOptionPane.showMessageDialog(this, "계좌번호 또는 비밀번호를 확인해주세요"); 
			} else {
				//입력 값이 맞을시 그 계좌의 정보창 출력
				int x = JOptionPane.showConfirmDialog(this,
						"계좌 번호 :" + cdto.getC_account() + "\n" + "계좌주 아이디 :" + cdto.getCf_user() + "\n" + "잔액", "계좌확인",
						JOptionPane.YES_NO_OPTION);
				if (x != 0) {
				//청보창에서 아니오 클릭시 입력칸이 지워짐
					search.accountField.setText("");
					search.pwField.setText("");
				}
			}
		}
	}
	
	public void particiButton() {
		int x = biz.partici_commdto(search.accountField.getText(), search.pwField.getText(),id);
		if(x>0) {
			JOptionPane.showMessageDialog(this, "계좌를추가했습니다");
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
