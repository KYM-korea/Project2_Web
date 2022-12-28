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

import Board.menuBoardDAO;
import Board.menuBoardDTO;

@WebServlet("/zibbab/menu.do")
public class MenuListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		menuBoardDAO dao = new menuBoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String ftype = req.getParameter("ftype");
		if(ftype!=null) {
			map.put("ftype", ftype);
		}
		
		List<menuBoardDTO> mBoardLists = dao.selectListME(map);
		dao.close();
		
		req.setAttribute("mBoardLists", mBoardLists);
		
		req.getRequestDispatcher("/menu/Menu.jsp").forward(req, resp);
	}
}
