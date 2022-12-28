package bab;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import Board.menuBoardDAO;
import Board.menuBoardDTO;
import util.FileUtil;
import util.JSFunction;

@WebServlet("/zibbab/mpass.do")
public class MenuEditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mode = req.getParameter("mode");
		String idx =req.getParameter("idx");
		menuBoardDAO dao = new menuBoardDAO();
		menuBoardDTO dto = dao.selectViewME(idx);
		if(mode.equals("edit")) {
			dao.close();
			req.setAttribute("dto", dto);
			req.getRequestDispatcher("/menu/MenuEdit.jsp").forward(req, resp);
		}else if(mode.equals("delete")) {
			int r=dao.deleteME(idx);
			if(r == 1) {
				String sf = dto.getImg();
				FileUtil.deleteFile(req, "/Uploads", sf);
			}
			dao.close();
			JSFunction.alertLocation(resp, "삭제 되었습니다.", "../zibbab/menu.do");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sdir = req.getServletContext().getRealPath("/Image");
		
		ServletContext app = this.getServletContext();
		int maxSize = Integer.parseInt(app.getInitParameter("maxPostSize"));
		
		MultipartRequest mr = FileUtil.uploadFile(req, sdir, maxSize);
		
		if(mr == null) {
			JSFunction.alertBack(resp, "첨부파일 제한 용량 초과");
			return;
		}
		
		String idx = mr.getParameter("idx");
		String prevImg = mr.getParameter("prevImg");
		
		String title = mr.getParameter("title");
		String ftype = mr.getParameter("ftype");
		String cntk = mr.getParameter("cntk");
		String allergy = mr.getParameter("allergy");
		String expr = mr.getParameter("expr");
		String origin = mr.getParameter("origin");
		
		menuBoardDTO dto = new menuBoardDTO();
		dto.setIdx(idx);
		dto.setTitle(title);
		dto.setFtype(ftype);
		dto.setCntk(cntk);
		dto.setAllergy(allergy);
		dto.setExpr(expr);
		dto.setOrigin(origin);
		
		String img = mr.getFilesystemName("img");
		
		if(img != null) {
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = img.substring(img.lastIndexOf("."));
			String nfn = now + ext;
			
			dto.setImg(img);
			FileUtil.deleteFile(req, "/Image", prevImg);
		}else {
			dto.setImg(prevImg);
		}
		
		menuBoardDAO dao = new menuBoardDAO();
		
		int r = dao.updateME(dto);
		dao.close();
		
		if(r==1) {
			resp.sendRedirect("../zibbab/menuview.do?idx="+idx);
		}else {
			JSFunction.alertLocation(resp, "수정을 실패하였습니다.", "../zibbab/menuview.do?idx="+idx);
		}
	}
}
