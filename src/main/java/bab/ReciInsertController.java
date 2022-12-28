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

import Board.reciBoardDAO;
import Board.reciBoardDTO;
import util.FileUtil;
import util.JSFunction;

@WebServlet("/zibbab/rinsert.do")
public class ReciInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("kind", req.getParameter("kind"));
		req.getRequestDispatcher("/reci/ReciInsert.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sdir = req.getServletContext().getRealPath("Image");
		
		ServletContext app = getServletContext();
		int maxSize = Integer.parseInt(app.getInitParameter("maxPostSize"));
		
		MultipartRequest mr = FileUtil.uploadFile(req, sdir, maxSize);
		
		if(mr == null) {
			JSFunction.alertLocation(resp, "이미지 제한 용량 초과", "../zibbab/rinsert.do");
			return;
		}
		
		reciBoardDTO dto = new reciBoardDTO();
		
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setId(mr.getParameter("id"));
		dto.setIngre(mr.getParameter("ingre"));
		dto.setKind(mr.getParameter("kind"));
		dto.setSrc(mr.getParameter("src"));
		
		String fn = mr.getFilesystemName("img");
		
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String ext = fn.substring(fn.lastIndexOf("."));
		String nfn = now + ext;
		
		File of = new File(sdir+File.separator+fn);
		File nf = new File(sdir+File.separator+nfn);
		of.renameTo(nf);
		
		dto.setImg(nfn);
		
		reciBoardDAO dao = new reciBoardDAO();
		int r = dao.insertRECI(dto);
		dao.close();
		
		if( r == 1 ) {
			if(mr.getParameter("kind").equals("all")) {
				resp.sendRedirect("../zibbab/reci.do?kind=all");
			}else {
				resp.sendRedirect("../zibbab/reci.do?kind=own");
			}
		}else {
			resp.sendRedirect("../zibbab/rinsert.do");
		}
	}
}