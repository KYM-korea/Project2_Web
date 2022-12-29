package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.gcBoardDAO;
import Board.gcBoardDTO;
import Board.reciBoardDAO;

@WebServlet("/zibbab/likeChk.do")
public class GoodCountController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mode=req.getParameter("mode");
		String idx=req.getParameter("idx");
		HttpSession session = req.getSession();
		String uid = session.getAttribute("UserId").toString();
		
		gcBoardDTO dto = new gcBoardDTO();
		dto.setReciIndex(idx);
		dto.setUserId(uid);
		
		gcBoardDAO dao = new gcBoardDAO();
		reciBoardDAO RDAO = new reciBoardDAO();
		if(mode.equals("good")) {
			dao.insertGc(dto);
			RDAO.updatePlusGc(idx);
		}else if(mode.equals("cancle")) {
			dao.deleteGc(dto);
			RDAO.updateMinuGc(idx);
		}
		dao.close();
		RDAO.close();
		
		resp.sendRedirect("../zibbab/reciview.do?idx="+idx);
	}
}
