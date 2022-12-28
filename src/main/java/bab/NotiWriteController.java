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

import Board.noBoardDAO;
import Board.noBoardDTO;
import util.FileUtil;
import util.JSFunction;

@WebServlet("/zibbab/notiwrite.do")
public class NotiWriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("attr", req.getParameter("attr"));
		req.getRequestDispatcher("/account/NotiWrite.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sdir = req.getServletContext().getRealPath("Uploads");
		
		ServletContext application = getServletContext();
		int maxSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		MultipartRequest mr = FileUtil.uploadFile(req, sdir, maxSize);
		
		if(mr == null) {
			JSFunction.alertLocation(resp, "첨부파일 제한 용량 초과", "../zibbab/notiwrite.do");
			return;
		}
		
		noBoardDTO dto = new noBoardDTO();
	
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setAttr(mr.getParameter("attr"));
		
		String fn = mr.getFilesystemName("ofile");
		
		if(fn != null) {
		
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fn.substring(fn.lastIndexOf("."));
			String nfn = now + ext;
			
			File of = new File(sdir+File.separator+fn);
			File nf = new File(sdir+File.separator+nfn);
			of.renameTo(nf);
			
			dto.setOfile(fn);
			dto.setSfile(nfn);
		}
		noBoardDAO dao = new noBoardDAO();
		int r = dao.noWrite(dto);
		dao.close();
		
		if(r == 1) {
			if(dto.getAttr().equals("N")) {
				resp.sendRedirect("../zibbab/notice.do");
			}else {
				resp.sendRedirect("../zibbab/conlist.do");
			}
		}else {
			resp.sendRedirect("../zibbab/notiwrite.do");
		}
	}
}
