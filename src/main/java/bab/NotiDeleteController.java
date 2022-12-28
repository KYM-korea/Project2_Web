package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.noBoardDAO;
import Board.noBoardDTO;
import util.FileUtil;
import util.JSFunction;

@WebServlet("/zibbab/ndelete.do")
public class NotiDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		noBoardDAO dao = new noBoardDAO();
		
		String idx = req.getParameter("idx");
		
		noBoardDTO dto = dao.selectViewNO(idx);
		int r = dao.deleteNO(idx);
		dao.close();
		if(r == 1) {
			String sf = dto.getSfile();
			FileUtil.deleteFile(req, "/Uploads", sf);
			JSFunction.alertLocation(resp, "삭제완료되었습니다", "../zibbab/notice.do");
		}
	}
}