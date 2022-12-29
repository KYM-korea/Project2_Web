package bab;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.reciBoardDAO;
import Board.reciBoardDTO;
import util.JSFunction;


@WebServlet("/zibbab/reci.do")
public class ReciListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		reciBoardDAO dao = new reciBoardDAO();
		
		String kind = req.getParameter("kind");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		
		List<reciBoardDTO> rBoardLists = dao.selectListRECI(map);
		
		dao.close();
		
		req.setAttribute("rBoardLists", rBoardLists);
		HttpSession session = req.getSession();
		
		if(session.getAttribute("UserId")!=null) {
			if(kind.equals("all")) {
				req.getRequestDispatcher("/reci/ReciAll.jsp").forward(req, resp);
			}else {
				req.getRequestDispatcher("/reci/OwnReci.jsp").forward(req, resp);
			}
		}else {
			JSFunction.alertBack(resp, "로그인하고 이용이 가능합니다.");
		}
	}
}
