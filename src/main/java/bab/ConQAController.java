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
import javax.servlet.http.HttpSession;

import Board.conBoardDAO;
import Board.conBoardDTO;
import util.BoardPage;
import util.JSFunction;

@WebServlet("/zibbab/consul.do")
public class ConQAController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userid="";
		if(session.getAttribute("UserId")!=null) {
			userid = session.getAttribute("UserId").toString();
		}
		
		if(userid.equals("admin")){
			conBoardDAO dao = new conBoardDAO();
			
			Map<String, Object> map = new HashMap<String, Object>();
			String searchField = req.getParameter("searchField");
			String searchWord = req.getParameter("searchWord");
			
			if(searchWord != null) {
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}
			
			int totalCount = dao.conCnt(map);
			
			ServletContext app = getServletContext();
			int pageSize = Integer.parseInt(app.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(app.getInitParameter("PAGES_PER_BLOCK"));
			
			int pageNum = 1;
			String pageTemp = req.getParameter("pageNum");
			if(pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp);
			}
			
			int start = (pageNum - 1) * pageSize;
			int end = pageSize;
			
			map.put("start", start);
			map.put("end", end);
			
			List<conBoardDTO> cBoardLists = dao.selectCon(map);
			dao.close();
			
			String pagingImg=BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../zibbab/questlist.do");
			
			map.put("pagingImg", pagingImg);
			map.put("totalCnt", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			req.setAttribute("cBoardLists", cBoardLists);
			req.setAttribute("map", map);
			
			req.getRequestDispatcher("/fran/ConsulA.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/fran/ConsulQ.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conBoardDTO dto = new conBoardDTO();
		dto.setUname(req.getParameter("uname"));
		dto.setCtext(req.getParameter("content"));
		dto.setAddr(req.getParameter("addr"));
		dto.setPhone(req.getParameter("phone"));
		 
		conBoardDAO dao = new conBoardDAO();
		int r = dao.insertCon(dto);
		dao.close();
		
		if(r==1) {
			JSFunction.alertLocation(resp, "문의 신청이 완료되었습니다.", "../zibbab/conlist.do");
		}else{
			resp.sendRedirect("../zibbab/consul.do");
		}
	}
}
