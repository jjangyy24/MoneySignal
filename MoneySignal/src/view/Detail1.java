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
		super.setTitle("����");
		super.setSize(840,630);
	    //super.setVisible(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void init(long account) {
		//���̾ƿ��� ���������Ŵϱ� layout���� ������ x
		super.setLayout(null);
		 
		//���� �г� ����
		picture=new JLabel(new ImageIcon("new1.png"));
		picture.setBounds(0, 0, 840, 630);
		
		//���̺� ��������
		
		//Vector<PayDTO> data=biz.detail(Loginsu.udto.getU_account());
		table=new DetailTable().table(account);    
		pane=new JScrollPane(table);
		pane.setBounds(73,60,660, 400);
		
		//picture�߰��ϱ�
		picture.add(pane);
		
		super.add(picture);
	}
	
}

class DetailTable{
	public JTable table(long account) {
		//�÷� ����
		MoneyBiz biz=new MoneyBiz();
		Vector<String> column=new Vector<String>();
		column.add("���¹�ȣ");
		column.add("�ݾ�");
		column.add("�ŷ�����");
		column.add("�޸�");
		column.add("ī�װ�");
		    	
		DefaultTableModel model=new DefaultTableModel();
		model.setColumnIdentifiers(column);
				
		//������ ��������
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
		    	
		//�÷��� ���� ���� -> �ݾװ� �ŷ������� 
		    	
		table.getColumn("���¹�ȣ").setPreferredWidth(132);
		table.getColumn("�ݾ�").setPreferredWidth(82);
		table.getColumn("�ŷ�����").setPreferredWidth(62);
		table.getColumn("�޸�").setPreferredWidth(252);
		table.getColumn("ī�װ�").setPreferredWidth(132);
		    	
		return table;
	}
}
