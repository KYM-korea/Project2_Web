package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.conBoardDAO;
import Board.conBoardDTO;
import util.JSFunction;

@WebServlet("/zibbab/cdelete.do")
public class ConDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		conBoardDAO dao = new conBoardDAO();
		
		String idx = req.getParameter("idx");
		
		int r = dao.deleteCON(idx);
		dao.close();
		if(r==1) {
			JSFunction.alertLocation(resp, "상담 삭제 완료되었습니다.", "../zibbab/consul.do");
		}else {
			JSFunction.alertBack(resp, "삭제 오류발생");
		}
	}
}
