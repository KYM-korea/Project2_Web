package bab;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;
import util.JSFunction;

@WebServlet("/zibbab/myPage.do")
public class MyPageController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uid = session.getAttribute("UserId").toString();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.choiceUser(uid);
		dao.close();
		
		String email = dto.getEmail().substring(0,dto.getEmail().lastIndexOf("@"));
		String domain = dto.getEmail().substring(dto.getEmail().lastIndexOf("@")+1);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", dto.getId());
		map.put("pass", dto.getPass());
		map.put("email", email);
		map.put("domain", domain);
		map.put("phone", dto.getPhone());
		map.put("uname", dto.getUname());
		
		req.setAttribute("map", map);
		req.getRequestDispatcher("/login/MyPage.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String email = req.getParameter("Email")+"@"+req.getParameter("Domain");
		
		if(req.getParameter("UserPass")!=null&&!(req.getParameter("UserPass").equals(""))) {
			//유저 비밀번호 변경
			map.put("pass",req.getParameter("UserPass"));
		}else {
			//유저 비밀번호 변경 안함
			map.put("pass",req.getParameter("PrevUserPass"));
		}
		
		map.put("uname", req.getParameter("UserName"));
		map.put("phone", req.getParameter("Phone"));
		map.put("email", email);
		map.put("id", req.getParameter("UserId"));
		map.put("prevPass", req.getParameter("PrevUserPass"));
		
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMEM(map);
		dao.close();
		
		if(result == 0) {
			//실행 실패
			JSFunction.alertBack(resp, "회원 정보 수정에 실패하였습니다.");
		}else {
			//실행 성공
			resp.sendRedirect("../zibbab/main.do");
		}
	}
}
