package bab;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.qBoardDAO;
import Board.qBoardDTO;
import util.BoardPage;

@WebServlet("/zibbab/questlist.do")
public class QuestListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		qBoardDAO dao = new qBoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		//검색어로찾기
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		//검색어가 존재할 경우
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		//해당 검색어로 총 개수 확인
		int totalCount = dao.totalCount(map);
		
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}
		
		int start = (pageNum - 1) * pageSize;
		int end = pageSize;
		
		map.put("start", start);
		map.put("end", end);
		
		List<qBoardDTO> qBoardLists = dao.selectList(map);
		dao.close();
		
		String pagingImg=BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../zibbab/questlist.do");
		
		map.put("pagingImg", pagingImg);
		map.put("totalCnt", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		req.setAttribute("qBoardLists", qBoardLists);
		req.setAttribute("map", map);
		
		req.getRequestDispatcher("/account/Q&A.jsp").forward(req, resp);
	}
}
