package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.qBoardDAO;
import Board.qBoardDTO;

@WebServlet("/zibbab/qview.do")
public class QuestViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		qBoardDAO dao = new qBoardDAO();
		
		String idx = req.getParameter("idx");
		
		qBoardDTO dto = dao.selectViewQT(idx);
		dao.close();
		dto.setQtext(dto.getQtext().replace("\r\n", "<br>"));
		if(dto.getAtext() != null) {
			dto.setAtext(dto.getAtext().replace("\r\n", "<br>"));
		}
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/account/Q&AView.jsp").forward(req, resp);
	}
}
