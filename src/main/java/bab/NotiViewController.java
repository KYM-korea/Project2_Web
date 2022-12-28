package bab;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.noBoardDAO;
import Board.noBoardDTO;



@WebServlet("/zibbab/noview.do")
public class NotiViewController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		noBoardDAO dao = new noBoardDAO();
		
		String idx = req.getParameter("idx");
		dao.noCntPlus(idx);
		noBoardDTO dto = dao.selectViewNO(idx);
		dao.close();
		dto.setContent(dto.getContent().replace("\r\n", "<br>"));
		
		String ext = null;
		String fn = dto.getSfile();
		if(fn != null) {
			ext = fn.substring(fn.lastIndexOf(".")+1);
		}
		
		String[] ImgStr = {"png", "jpg", "gif", "jpeg", "PNG", "JPG", "GIF", "JPEG"};
		
		List<String> ImgList = Arrays.asList(ImgStr);
		boolean isImg = false;
		if(ImgList.contains(ext)) {
			isImg=true;
		}
		req.setAttribute("isImg", isImg);
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/account/NotiView.jsp").forward(req, resp);
		
	}
}