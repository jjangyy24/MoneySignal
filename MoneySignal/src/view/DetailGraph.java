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
			public void onFinishLoadingFrame(FinishLoadingEvent event) {//프로그램 로딩이 끝났을때 호출되는 함수
				if(event.isMainFrame()) {
					System.out.println("HTML문서가 로드되었읍니다.");
				}
			} 
		});
		String title = "카테고리별 사용금액";
		ArrayList<PieElement> list=biz.commCateDetail(account);
		//ArrayList<PieElement> list=biz.commCateDetail(11111111111L);
		browser.loadHTML(new GoogleAPI().getPieChart(title, list));
		
		return browser;
	}
}
