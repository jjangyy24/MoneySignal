package view;

import java.util.ArrayList;


import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;

import Biz.MoneyBiz;

public class DetailGraph{
	Browser browser;
	MoneyBiz biz;
	
	public Browser Graph(long account) {
		browser=new Browser();
		biz=new MoneyBiz();
		
		browser.addLoadListener(new LoadAdapter() {
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent event) {//���α׷� �ε��� �������� ȣ��Ǵ� �Լ�
				if(event.isMainFrame()) {
					System.out.println("HTML������ �ε�Ǿ����ϴ�.");
				}
			} 
		});
		String title = "ī�װ��� ���ݾ�";
		ArrayList<PieElement> list=biz.commCateDetail(account);
		//ArrayList<PieElement> list=biz.commCateDetail(11111111111L);
		browser.loadHTML(new GoogleAPI().getPieChart(title, list));
		
		return browser;
	}
}
