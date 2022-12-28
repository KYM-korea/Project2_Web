package bab;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/zibbab/login.do")
public class LoginController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login/Login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = getServletContext();
		
		String admin_id = application.getInitParameter("Admin_id");
		String admin_pass = application.getInitParameter("Admin_pass");
		//아이디 및 패스워드 가져오기
		String id = req.getParameter("UserId");
		String pwd = req.getParameter("UserPass");
		
		//객체에 아이디 존재 여부 확인
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getUserDTO(id, pwd);
		dao.close();
		//세션에 아이디 로그인유지를 위해 세션 객체 선언
		HttpSession session = req.getSession();
		if(dto.getId() != null) {
			//세션에 아이디 저장
			session.setAttribute("UserId", dto.getId());
			
			resp.sendRedirect("../zibbab/main.do");
		}else {
			if(admin_id.equals(id)&&admin_pass.equals(pwd)) {
				session.setAttribute("UserId", admin_id);
				resp.sendRedirect("../zibbab/main.do");
			}else {
			//로그인 실패시 LoginErrMsg생성 및 포워딩
			req.setAttribute("LoginErrMsg", "오류 발생");
			req.getRequestDispatcher("/login/Login.jsp").forward(req, resp);
			}
		}
	}
}
