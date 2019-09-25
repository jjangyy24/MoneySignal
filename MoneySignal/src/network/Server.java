package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;


public class Server{
	ServerSocket ss;
	Socket s;
	HashMap<String, BufferedWriter> hm = new HashMap<String, BufferedWriter>();
	
	public Server() {
		try {
			ss=new ServerSocket(7000);
			
			while(true) {
				System.out.println("�Ͷ�");
				s = ss.accept();
				System.out.println("Ŭ���̾�Ʈ ���� : "+s);
				ServerThread service=new ServerThread(this);
				service.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public Socket getS() {
		return s;
	}

	public HashMap<String, BufferedWriter> getHm() {
		return hm;
	}

	public static void main(String[] args) {
		new Server();
	}
}


class ServerThread extends Thread{
	String myid;
	
	BufferedReader in;
	BufferedWriter out;
	
	Server server;
	Socket s;
	HashMap<String, BufferedWriter> hm;
	
	
	ServerThread(Server server){
		try {
			this.server=server;
			this.s = server.getS();
			this.hm = server.getHm();
			System.out.println("������ �� �����");
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			System.out.println("��Ʈ�� �� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Handler(String msg) {
		
		String[] spl=msg.split("#");
		System.out.println("����");
		int num = 0;
		for(String why : spl) {
			System.out.printf("spl[%d]: %s\n",num, why);
			num++;
		}
		Set<String> keys = hm.keySet();
		for(String user : keys) {
			System.out.println("���: " + user);
		}
		
		if(hm.containsKey(spl[1])) {
			try {
				System.out.println("����2");
				hm.get(spl[1]).write(spl[0]+"#"+spl[1]+"#"+spl[2]);
				hm.get(spl[1]).newLine();
				hm.get(spl[1]).flush();
				System.out.println(spl[0]+"#"+spl[1]+"#"+spl[2]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}
	@Override
	public void run() {
		try {
			System.out.println("id ������");
			String id=in.readLine();
			hm.put(id, out);
			System.out.println("����: "+ id);
			System.out.println("map ��� �Ϸ�");
			Set<String> keys = hm.keySet();
			for(String user : keys) {
				System.out.println("�������� ���: " + user);
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true) {
			try {
				String msg=in.readLine();
				System.out.println("�� �޾ƿ�");
				System.out.println(msg);
				Handler(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
