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

@WebServlet("/zibbab/Qpass.do")
public class QuestPassController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		qBoardDAO dao = new qBoardDAO();
		qBoardDTO dto = dao.selectViewQT(idx);
		dao.close();

		String uid = "";
		String pass = "";
		HttpSession session = req.getSession();
		
		if(session.getAttribute("UserId")!=null) {
			uid = session.getAttribute("UserId").toString();
		}
		if(!(dto.getPass()==null)) {
			pass = dto.getPass();
		}
		
		if(pass.equals("")) {
			resp.sendRedirect("../zibbab/qview.do?idx="+idx);
		}else if(uid.equals("admin")){
			if(req.getParameter("mode").equals("delete")) {
				dao = new qBoardDAO();
				dao.deleteQT(idx);
				dao.close();
				
				JSFunction.alertLocation(resp, "삭제 되었습니다.", "../zibbab/questlist.do");
			}
		}else {
			req.setAttribute("mode", req.getParameter("mode"));
			req.getRequestDispatcher("/account/Pass.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		String pass = req.getParameter("pass");
		
		HttpSession session = req.getSession();
		
		qBoardDAO dao = new qBoardDAO();
		boolean chk = dao.confirmQT(idx, pass);
		
		dao.close();
		
		if(chk) {
			if(mode.equals("view")) {
				session.setAttribute("pass", pass);
				resp.sendRedirect("../zibbab/qview.do?idx="+idx);
			}else if(mode.equals("edit")) {
				session.setAttribute("pass", pass);
				resp.sendRedirect("../zibbab/qedit.do?idx="+idx);
			}else if(mode.equals("delete")) {
				dao = new qBoardDAO();
				dao.deleteQT(idx);
				dao.close();
				
				JSFunction.alertLocation(resp, "삭제 되었습니다.", "../zibbab/questlist.do");
			}
		}else {
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
	}
}