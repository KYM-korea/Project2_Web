package bab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/zibbab/idchk.do")
public class PopupIdChk extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		int result = 0;
		String UserId=req.getParameter("UserId");
		if(UserId.equals("admin")) {
			result = 1;
		}
		else {
			result = dao.selectUser(UserId);
		}
		dao.close();
	
		req.setAttribute("result", result);
		req.getRequestDispatcher("/login/Idfind.jsp").forward(req, resp);
	}
}