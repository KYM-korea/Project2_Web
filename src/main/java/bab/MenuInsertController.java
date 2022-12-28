package bab;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import Board.menuBoardDAO;
import Board.menuBoardDTO;
import util.FileUtil;
import util.JSFunction;

@WebServlet("/zibbab/minsert.do")
public class MenuInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("ftype", req.getParameter("ftype"));
		req.getRequestDispatcher("/menu/MenuInsert.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sdir = req.getServletContext().getRealPath("Image");
		
		ServletContext app = getServletContext();
		int maxSize = Integer.parseInt(app.getInitParameter("maxPostSize"));
		
		MultipartRequest mr = FileUtil.uploadFile(req, sdir, maxSize);
		
		if(mr == null) {
			JSFunction.alertLocation(resp, "이미지 제한 용량 초과", "../zibbab/mwrite.do");
			return;
		}
		
		menuBoardDTO dto = new menuBoardDTO();
		
		dto.setAllergy(mr.getParameter("allergy"));
		dto.setCntk(mr.getParameter("cntk"));
		dto.setExpr(mr.getParameter("expr"));
		dto.setFtype(mr.getParameter("ftype"));
		dto.setOrigin(mr.getParameter("origin"));
		dto.setTitle(mr.getParameter("title"));
		
		String fn = mr.getFilesystemName("img");
		
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String ext = fn.substring(fn.lastIndexOf("."));
		String nfn = now + ext;
		
		File of = new File(sdir+File.separator+fn);
		File nf = new File(sdir+File.separator+nfn);
		of.renameTo(nf);
		
		dto.setImg(nfn);
		
		menuBoardDAO dao = new menuBoardDAO();
		int r = dao.insertMenu(dto);
		dao.close();
		
		if( r == 1 ) {
			resp.sendRedirect("../zibbab/menu.do");
		}else {
			resp.sendRedirect("../zibbab/minsert.do");
		}
	}
}