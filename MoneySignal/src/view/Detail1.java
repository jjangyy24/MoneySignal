package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Biz.MoneyBiz;
import dao.MoneyDAO;
import dto.PayDTO;

public class Detail1 extends JFrame{

	JLabel picture;
	JTable table;
	JScrollPane list;
	DefaultTableModel model;
	JScrollPane pane;
	
	
	public Detail1(long account) {
		
		init(account);
		super.setTitle("시험");
		super.setSize(840,630);
	    //super.setVisible(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void init(long account) {
		//레이아웃에 사진넣을거니까 layout따로 정하지 x
		super.setLayout(null);
		 
		//사진 패널 설정
		picture=new JLabel(new ImageIcon("new1.png"));
		picture.setBounds(0, 0, 840, 630);
		
		//테이블 가져오기
		
		//Vector<PayDTO> data=biz.detail(Loginsu.udto.getU_account());
		table=new DetailTable().table(account);    
		pane=new JScrollPane(table);
		pane.setBounds(73,60,660, 400);
		
		//picture추가하기
		picture.add(pane);
		
		super.add(picture);
	}
	
}

class DetailTable{
	public JTable table(long account) {
		//컬럼 설정
		MoneyBiz biz=new MoneyBiz();
		Vector<String> column=new Vector<String>();
		column.add("계좌번호");
		column.add("금액");
		column.add("거래유형");
		column.add("메모");
		column.add("카테고리");
		    	
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(column);
				
		//데이터 가져오기
		//Vector<PayDTO> data=biz.detail(Loginsu.udto.getU_account());
		Vector<PayDTO> data=biz.detail(account);
				
		Vector<Object> row;
		for(PayDTO p:data) {
		    row=new Vector<Object>();
		    row.addElement(p.getP_account());
		    row.addElement(p.getP_payment());
		    row.addElement(p.getP_detail());
		    row.addElement(p.getP_memo());
		    row.addElement(p.getP_category());
		    model.addRow(row);
		}

		JTable table=new JTable(model);
		    	
		//컬럼의 길이 조절 -> 금액과 거래유형만 
		    	
		table.getColumn("계좌번호").setPreferredWidth(132);
		table.getColumn("금액").setPreferredWidth(82);
		table.getColumn("거래유형").setPreferredWidth(62);
		table.getColumn("메모").setPreferredWidth(252);
		table.getColumn("카테고리").setPreferredWidth(132);
		    	
		return table;
	}
}
