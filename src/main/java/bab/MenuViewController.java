package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.menuBoardDAO;
import Board.menuBoardDTO;

@WebServlet("/zibbab/menuview.do")
public class MenuViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		menuBoardDAO dao = new menuBoardDAO();
		
		String idx = req.getParameter("idx");
		menuBoardDTO dto = dao.selectViewME(idx);
		dao.close();
		
		dto.setExpr(dto.getExpr().replace("\r\n", "<br>"));
		dto.setOrigin(dto.getOrigin().replace("\r\n", "<br>"));
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/menu/MenuView.jsp").forward(req, resp);
	}
}
