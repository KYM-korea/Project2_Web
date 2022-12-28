package util;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize,int blockPage, int pageNum, String req) {
		String pagingStr = "";
		
		int totalPages = (int)(Math.ceil(((double)totalCount/pageSize)));
		
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage)+1;
		
		if(pageTemp!=1) {
			pagingStr += "<a href='" + req + "?pageNum=1'>[첫페이지]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='" + req + "?pageNum="+(pageTemp-1)+"'>[이전 블록]</a>";
		}
		int blockcnt = 1;
		while(blockcnt <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				pagingStr += "&nbsp;<a href='"+req+"?pageNum="+pageTemp
						+ "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			blockcnt++;
		}
		
		if (pageTemp <= totalPages) {
			pagingStr += "<a href='" + req + "?pageNum="+pageTemp+"'>[다음 블록]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='" + req + "?pageNum="+totalPages+"'>[마지막 페이지]</a>";
		}
		return pagingStr;
	}
}