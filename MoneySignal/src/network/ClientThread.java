package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ClientThread implements Runnable{

	//��Ʈ��ũ
	Socket s;
	BufferedReader in;
	OutputStream out;
		
	//������� ����
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
				
				System.out.println("�������� �޴� ���� :"+msg);
				
				StringTokenizer st=new StringTokenizer(msg, "|");
				int protocol=Integer.parseInt(st.nextToken());
				
				if(protocol==1) {
					System.out.println("������ id :"+st.nextToken());
				}else if(protocol==2) {
					String youid=st.nextToken();
					String money=st.nextToken();
					
					JOptionPane.showMessageDialog(null, youid+"���� "+money+"�� �Ա��ϼ̽��ϴ�.");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//��Ʈ��ũ�� �α����� ��� id ������ ���� ����
	public void connectProcess(String id) {
		try {
			serverid=id;//id�� ���ؿ�
			s=new Socket();
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=s.getOutputStream();
				
			//������� id�� ���� ����
			out.write((1+"|"+serverid).getBytes());//1|id
			System.out.println("������ ���� : "+1+serverid);
				
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
