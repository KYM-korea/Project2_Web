package bab;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.commentDAO;
import Board.commentDTO;
import Board.gcBoardDAO;
import Board.reciBoardDAO;
import Board.reciBoardDTO;


@WebServlet("/zibbab/reciview.do")
public class ReciViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		reciBoardDAO dao = new reciBoardDAO();
		
		String idx = req.getParameter("idx");
		reciBoardDTO dto = dao.selectViewRE(idx);
		dao.updatePlusVc(idx);
		dao.close();
		
		dto.setContent(dto.getContent().replace("\r\n", "<br>"));
		
		commentDAO CDAO = new commentDAO();
		List<commentDTO> commentBoardLists = CDAO.selectComment(idx);
		dao.close();
		
		HttpSession session = req.getSession();
		gcBoardDAO GDAO= new gcBoardDAO();
		int likeChk = GDAO.gcCntUser(session.getAttribute("UserId").toString(), idx);
		GDAO.close();
		
		req.setAttribute("likeChk", likeChk);
		req.setAttribute("dto", dto);
		req.setAttribute("commentBoardLists", commentBoardLists);
		req.getRequestDispatcher("/reci/ReciView.jsp").forward(req, resp);
	}
}
