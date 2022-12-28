package bab;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/zibbab/regi.do")
public class RegistController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login/Regi.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = getServletContext();
		
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("UserId"));
		dto.setPass(req.getParameter("UserPass"));
		dto.setUname(req.getParameter("UserName"));
		String email = req.getParameter("Email")+"@"+req.getParameter("Domain");
		dto.setEmail(email);
		dto.setPhone(req.getParameter("Phone"));
		
		MemberDAO dao = new MemberDAO();
		int r = dao.insertUser(dto);
		dao.close();
		
		if(r==1) {
			resp.sendRedirect("../zibbab/login.do");
		}else {
			resp.sendRedirect("../zibbab/regi.do");
		}
	}
}