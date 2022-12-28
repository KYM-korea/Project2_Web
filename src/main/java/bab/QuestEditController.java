package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.qBoardDAO;
import Board.qBoardDTO;
import util.JSFunction;

@WebServlet("/zibbab/qedit.do")
public class QuestEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		qBoardDAO dao = new qBoardDAO();
		qBoardDTO dto = dao.selectViewQT(idx);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/account/Q&AEdit.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("Title");
		String qtext = req.getParameter("qcontent");
		String uname = req.getParameter("Uname");
		String idx = req.getParameter("idx");
		
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		
		qBoardDTO dto = new qBoardDTO();
		dto.setTitle(title);
		dto.setUname(uname);
		dto.setPass(pass);
		dto.setQtext(qtext);
		dto.setIdx(idx);
		dto.setAtext(req.getParameter("atext"));
		
		qBoardDAO dao = new qBoardDAO();
		int r = dao.updateQT(dto);
		dao.close();
		
		if(r==1) {
			session.removeAttribute("pass");
			resp.sendRedirect("../zibbab/qview.do?idx="+idx);
		}else {
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요.", "../zibbab/qview.do?idx="+idx);
		}
	}
}
