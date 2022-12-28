package bab;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.commentDAO;
import Board.commentDTO;
import Board.qBoardDAO;
import Board.qBoardDTO;

@WebServlet("/zibbab/questwrite.do")
public class QuestWriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/account/Q&AWrite.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		qBoardDTO dto = new qBoardDTO();
		dto.setTitle(req.getParameter("Title"));
		dto.setUname(req.getParameter("Uname"));
		dto.setPass(req.getParameter("UserPass"));
		dto.setQtext(req.getParameter("qcontent"));
		
		qBoardDAO dao = new qBoardDAO();
		int r = dao.insertQT(dto);
		dao.close();
		
		if(r==1) {
			resp.sendRedirect("../zibbab/questlist.do");
		}else {
			resp.sendRedirect("../zibbab/questwrite.do");
		}
	}
}