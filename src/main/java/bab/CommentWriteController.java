package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.commentDAO;
import Board.commentDTO;
import util.JSFunction;

@WebServlet("/zibbab/comment.do")
public class CommentWriteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		commentDTO dto = new commentDTO();
		dto.setId(req.getParameter("id"));
		dto.setRcomment(req.getParameter("rcomment"));
		dto.setReciIndex(req.getParameter("reciIndex"));
		
		commentDAO dao = new commentDAO();
		int r = dao.insertComment(dto);
		dao.close();
		
		if(r==1) {
			resp.sendRedirect("../zibbab/reciview.do?idx="+req.getParameter("reciIndex"));
		}else {
			JSFunction.alertBack(resp, "댓글 작성 실패");
		}
	}
}
