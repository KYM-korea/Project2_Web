package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.noBoardDAO;
import util.FileUtil;

@WebServlet("/zibbab/download.do")
public class DownloadController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		String idx = req.getParameter("idx");
		
		FileUtil.download(req, resp, "/Uploads", sfile, ofile);
		
		noBoardDAO dao = new noBoardDAO();
		dao.noDCntPlus(idx);
		dao.close();
	}
}