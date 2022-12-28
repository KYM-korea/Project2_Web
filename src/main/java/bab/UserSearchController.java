package bab;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/zibbab/accountFind.do")
public class UserSearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login/IdPwdFind.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		//아이디 찾기에 사용되는 email과 phone을 전달해줄 map생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		String email = req.getParameter("email")+"@"+req.getParameter("domain");
		
		map.put("email", email);
		map.put("phone", req.getParameter("phone"));
		//비밀번호 찾기일 경우 id를 입력하므로 userid존재여부 판단
		String id =req.getParameter("UserId"); 
		if(id != null && !id.equals("")) {
			System.out.println(req.getParameter("UserId"));
		}
		// 조회하여 dto 객체 생성
		MemberDTO dto = dao.selectUser(map);
		dao.close();
		
		/*
		 map으로 select를 한 이후이기에 조회된 값이 있을 경우 id 및 pwd를
		 가져온 dto객체가 생성
		 만약 조회가 없으면 null
		 */
		if(dto.getId() != null) {
			req.setAttribute("UserId", dto.getId());
			req.setAttribute("UserPass", dto.getPass());
			req.getRequestDispatcher("/login/IdPwdFind.jsp").forward(req, resp);
		}else {
			req.setAttribute("SearchErrMsg", "오류 발생");
			req.getRequestDispatcher("/login/IdPwdFind.jsp").forward(req, resp);
		}
	}
}