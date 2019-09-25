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
	JTable ta1;//���̺�
	JTextArea ta2;//�׷���
	JLabel picture;
 
 	public Detail2(long account){
 		init(account);
 
 		setSize(840,630);
 		//setVisible(true);
 		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 }
 
 	public void init(long account) {
 		//�̹��� ����
 		super.setLayout(null);
 		picture=new JLabel(new ImageIcon("new1.png"));
 		picture.setBounds(0,0,840,630);
 		
 		//�� ����
 		tp=new JTabbedPane(JTabbedPane.TOP);
 
 		//��1�� ������ 
 		ta1=new DetailTable().table(account);//�������·� �ֱ�
 		JScrollPane pane1=new JScrollPane(ta1);
 		pane1.setBounds(0,0,660,470);
  
 		//��2�� ������--�׷���
 		Browser browser=new DetailGraph().Graph(account);
 		
 		//�ǿ� ������ �߰����ֱ�
 		tp.addTab("�󼼳���", pane1);
 		tp.addTab("�׷���", new BrowserView(browser));
 		tp.setBounds(73,60,660,470);
 		
 		//������ tab �ֱ�
 		picture.add(tp);
 		super.add(picture);
 	 		
 	}

 
}
