package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.conBoardDAO;
import Board.conBoardDTO;

@WebServlet("/zibbab/conview.do")
public class ConViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conBoardDAO dao = new conBoardDAO();
		
		String idx = req.getParameter("idx");
		conBoardDTO dto = dao.selectViewCon(idx);
		dao.close();
		dto.setCtext(dto.getCtext().replace("\r\n", "<br>"));
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/fran/ConView.jsp").forward(req, resp);
	}
}