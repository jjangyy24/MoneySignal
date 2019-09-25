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
				System.out.println("와라");
				s = ss.accept();
				System.out.println("클라이언트 정보 : "+s);
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
			System.out.println("서버꺼 잘 등록함");
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			System.out.println("스트림 잘 만듦");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Handler(String msg) {
		
		String[] spl=msg.split("#");
		System.out.println("들어옴");
		int num = 0;
		for(String why : spl) {
			System.out.printf("spl[%d]: %s\n",num, why);
			num++;
		}
		Set<String> keys = hm.keySet();
		for(String user : keys) {
			System.out.println("등록: " + user);
		}
		
		if(hm.containsKey(spl[1])) {
			try {
				System.out.println("들어옴2");
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
			System.out.println("id 보내줘");
			String id=in.readLine();
			hm.put(id, out);
			System.out.println("접속: "+ id);
			System.out.println("map 등록 완료");
			Set<String> keys = hm.keySet();
			for(String user : keys) {
				System.out.println("접속중인 사람: " + user);
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true) {
			try {
				String msg=in.readLine();
				System.out.println("잘 받아옴");
				System.out.println(msg);
				Handler(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
