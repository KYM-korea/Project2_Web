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

import Board.noBoardDAO;
import Board.noBoardDTO;
import util.BoardPage;

@WebServlet("/zibbab/main.do")
public class MainPageController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		noBoardDAO dao = new noBoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		
		int totalCount = dao.totalCount(map,"N");
		
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
		
		List<noBoardDTO> nBoardLists = dao.selectList(map, "N");
		dao.close();
		
		String pagingImg=BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../zibbab/notice.do");
		
		map.put("pagingImg", pagingImg);
		map.put("totalCnt", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		req.setAttribute("nBoardLists", nBoardLists);
		req.setAttribute("map", map);
		
		req.getRequestDispatcher("/home/Mainpage.jsp").forward(req, resp);
	}
}
