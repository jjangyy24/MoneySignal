package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ClientThread implements Runnable{

	//네트워크
	Socket s;
	BufferedReader in;
	OutputStream out;
		
	//멤버변수 설정
	String serverid;
	String youid;
	int money;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				String msg = in.readLine();
				if(msg==null) return;
				
				System.out.println("서버에서 받는 정보 :"+msg);
				
				StringTokenizer st=new StringTokenizer(msg, "|");
				int protocol=Integer.parseInt(st.nextToken());
				
				if(protocol==1) {
					System.out.println("접속한 id :"+st.nextToken());
				}else if(protocol==2) {
					String youid=st.nextToken();
					String money=st.nextToken();
					
					JOptionPane.showMessageDialog(null, youid+"님이 "+money+"를 입금하셨습니다.");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//네트워크로 로그인한 사람 id 보내기 위해 만듦
	public void connectProcess(String id) {
		try {
			serverid=id;//id를 구해옴
			s=new Socket();
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=s.getOutputStream();
				
			//사용자의 id를 정보 전달
			out.write((1+"|"+serverid).getBytes());//1|id
			System.out.println("서버로 보냄 : "+1+serverid);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public void pushNotification(String youid,int money) {
		this.youid=youid;
		this.money=money;
		
		try {
			out.write((2+"|"+youid+"|"+money).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
