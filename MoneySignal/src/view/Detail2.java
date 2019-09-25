package view;
import java.awt.Component;
import java.awt.Container;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import Biz.MoneyBiz;

public class Detail2 extends JFrame {
 
	JTabbedPane tp;
	JLabel lb;
	JTable ta1;//테이블
	JTextArea ta2;//그래프
	JLabel picture;
 
 	public Detail2(long account){
 		init(account);
 
 		setSize(840,630);
 		//setVisible(true);
 		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 }
 
 	public void init(long account) {
 		//이미지 설정
 		super.setLayout(null);
 		picture=new JLabel(new ImageIcon("new1.png"));
 		picture.setBounds(0,0,840,630);
 		
 		//탭 설정
 		tp=new JTabbedPane(JTabbedPane.TOP);
 
 		//탭1에 넣을것 
 		ta1=new DetailTable().table(account);//공동계좌로 넣기
 		JScrollPane pane1=new JScrollPane(ta1);
 		pane1.setBounds(0,0,660,470);
  
 		//탭2에 넣을거--그래프
 		Browser browser=new DetailGraph().Graph(account);
 		
 		//탭에 데이터 추가해주기
 		tp.addTab("상세내역", pane1);
 		tp.addTab("그래프", new BrowserView(browser));
 		tp.setBounds(73,60,660,470);
 		
 		//사진에 tab 넣기
 		picture.add(tp);
 		super.add(picture);
 	 		
 	}

 
}
