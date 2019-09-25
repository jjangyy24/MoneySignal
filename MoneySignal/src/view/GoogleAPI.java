package view;


import java.util.ArrayList;

public class GoogleAPI {
	public String getPieChart(String title, ArrayList<PieElement> list) {
		String htmlString = "<html>\r\n" + 
				"  <head>\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"      google.charts.load(\"current\", {packages:[\"corechart\"]});\r\n" + 
				"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"      function drawChart() {\r\n" + 
				"        var data = google.visualization.arrayToDataTable([\r\n" + 
				"          ['항목', '수치'],\r\n"; 
				for(int i=0; i<list.size(); i++) {
					htmlString += "['" + list.get(i).getName() + "', " + list.get(i).getValue() + "],\r\n";
				}
				htmlString += "        ]);\r\n" + 
				"        var options = {\r\n" + 
				"          title: '제목',\r\n" + 
				"        };\r\n" + 
				"        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));\r\n" + 
				"        chart.draw(data, options);\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" + 
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"    <div id=\"piechart_3d\" style=\"width: 500px; height: 300px;\"></div>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		return htmlString;
	}
}

