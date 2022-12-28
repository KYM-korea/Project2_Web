package member;

import java.util.Map;

import common.JDBConnect;

public class MemberDAO extends JDBConnect {

	//회원가입
	public int insertUser(MemberDTO dto) {
		int r = 0;
		
		String query = "INSERT INTO zibuser (id, pass, uname, phone, email) "
				+ " VALUES (?,?,?,?,?) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getUname());
			psmt.setString(4, dto.getPhone());
			psmt.setString(5, dto.getEmail());

			r = psmt.executeUpdate();
			
			System.out.println("회원가입이 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 에러 발생");
		}
		return r;
	}
	
	//로그인
	public MemberDTO getUserDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();

		String query = "SELECT * FROM zibuser "
				+ " WHERE id=? AND pass=? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setUname(rs.getString("uname"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 에러");
		}
		
		return dto;
	}
	//사용자 조회 ( 중복찾기 )
	public int selectUser(String id) {
		int r = 0;
		
		String query = "SELECT COUNT(*) FROM zibuser WHERE id = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			rs.next();
			r = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("중복 검사 중 에러");
		}
		
		return r;
	}
	//사용자 조회 ( 계정찾기 )
	public MemberDTO selectUser(Map<String, Object> map) {
		MemberDTO dto = new MemberDTO();
		
		String query = "SELECT * FROM zibuser"
				+ " WHERE email = ? AND phone = ? ";
		if(map.get("UserId")!=null&&map.get("UserId").toString().equals("")) {
			query += " AND id = ? ";
			System.out.println("아이디가 존재");
		}
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("email").toString());
			psmt.setString(2, map.get("phone").toString());
			if(map.get("id")!=null&&map.get("UserId").toString().equals("")) {
				psmt.setString(3, map.get("UserId").toString());
			}
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setUname(rs.getString("uname"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 에러");
		}
		return dto;
	}
	
	//회원정보 찾아오기 (수정시필요)
	public MemberDTO choiceUser(String id) {
		MemberDTO dto = new MemberDTO();
		
		String query = "SELECT * FROM zibuser WHERE id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setRegidate(rs.getString("regidate"));
				dto.setUname(rs.getString("uname"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("마이페이지 계정 호출 에러");
		}
		
		return dto;
	}
	
	//회원정보 수정
	public int updateMEM(Map<String, Object> map) {
		int r = 0;
		
		String query = "UPDATE zibuser SET "
				+ " pass = ?, uname = ?, phone = ? , email = ?"
				+ " WHERE id = ? AND pass = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("pass").toString());
			psmt.setString(2, map.get("uname").toString());
			psmt.setString(3, map.get("phone").toString());
			psmt.setString(4, map.get("email").toString());
			psmt.setString(5, map.get("id").toString());
			psmt.setString(6, map.get("prevPass").toString());
			
			System.out.println("=======pass======="+map.get("pass"));
			System.out.println("=======uname======="+map.get("uname"));
			System.out.println("=======phone======="+map.get("phone"));
			System.out.println("=======email======="+map.get("email"));
			System.out.println("=======id======="+map.get("id"));
			System.out.println("=======prevPass======="+map.get("prevPass"));
			
			r= psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 수정 중 에러 발생");
		}
		
		return r;
	}
}