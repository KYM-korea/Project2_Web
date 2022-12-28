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

@WebServlet("/zibbab/nedit.do")
public class NotiEditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idx = req.getParameter("idx");
		noBoardDAO dao = new noBoardDAO();
		noBoardDTO dto = dao.selectViewNO(idx);
		dao.close();
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/account/NotiEdit.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sdir = req.getServletContext().getRealPath("/Uploads");
		
		ServletContext application = this.getServletContext();
		int maxSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		MultipartRequest mr = FileUtil.uploadFile(req, sdir, maxSize);
		
		if(mr == null) {
			JSFunction.alertBack(resp, "첨부파일 제한 용량 초과");
			return;
		}
		
		String idx = mr.getParameter("idx");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String attr = mr.getParameter("attr");
		
		noBoardDTO dto = new noBoardDTO();
		dto.setIdx(idx);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setAttr(attr);
		
		String fn = mr.getFilesystemName("ofile");
		
		if(fn!=null) {
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fn.substring(fn.lastIndexOf("."));
			String nfn = now + ext;
			
			File of = new File(sdir+File.separator+fn);
			File nf = new File(sdir+File.separator+nfn);
			of.renameTo(nf);
			
			dto.setOfile(fn);
			dto.setSfile(nfn);
			
			FileUtil.deleteFile(req, "/Uploads", prevSfile);
		}else {
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		noBoardDAO dao = new noBoardDAO();
		int r = dao.updateNO(dto);
		dao.close();
		
		if(r==1) {
			resp.sendRedirect("../zibbab/noview.do?idx="+idx);
		}else {
			JSFunction.alertLocation(resp, "수정을 실패하였습니다.", "../zibbab/noview.do?idx="+idx);
		}
	}
}