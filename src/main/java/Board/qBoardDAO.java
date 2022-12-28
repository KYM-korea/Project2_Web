package Board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class qBoardDAO extends JDBConnect{
	//게시글 작성
	public int insertQT(qBoardDTO dto) {
		int r = 0;
		
		String query = " INSERT INTO questBoard ("
				+ " title, uname, pass, qtext )"
				+ " VALUES ( ?, ?, ?, ? ) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getUname());
			if(dto.getPass()!="") {
				psmt.setString(3, dto.getPass());
			}else {
				psmt.setString(3, null);
			}
			psmt.setString(4, dto.getQtext());
			
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Insert 에러");
		}
		
		return r;
	}
	
	//문의사항 총 게시글 반환
	public int totalCount(Map<String , Object> map) {
		int t = 0;
		
		String query = "SELECT COUNT(*) FROM questBoard ";
		if(map.get("searchWord")!=null) {
			query += " WHERE "+map.get("searchField")+" LIKE '%"
					+ map.get("searchWord") +"%'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			t = rs.getInt(1);					
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("카운팅 중 에러 발생");
		}
		return t;
	}
	
	//문의사항 총게시판 출력
	public List<qBoardDTO> selectList(Map<String, Object> map) {
		List<qBoardDTO> qBoardList = new Vector<qBoardDTO>();
		String query = " SELECT * FROM questboard ";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "		ORDER BY idx DESC limit ?, ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				qBoardDTO dto = new qBoardDTO();
				
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setUname(rs.getString("uname"));
				dto.setPass(rs.getString("pass"));
				dto.setQtext(rs.getString("qtext"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setAtext(rs.getString("atext"));
				
				qBoardList.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 중 에러");
		}
		return qBoardList;
	}
	
	//문의사항 상세보기
	public qBoardDTO selectViewQT(String idx) {
		qBoardDTO dto = new qBoardDTO();
		
		String query = "SELECT * FROM questboard "
				+ " WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setPass(rs.getString("pass"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setQtext(rs.getString("qtext"));
				dto.setUname(rs.getString("uname"));
				dto.setAtext(rs.getString("atext"));
				dto.setAnswerdate(rs.getDate("answerdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("문의 상세보기 중 에러 발생");
		}
		return dto;
	}
	
	//문의글 패스워드 검증
	public boolean confirmQT(String idx, String pass) {
		boolean r = true;
		
		String query = "SELECT COUNT(*) FROM questboard "
				+ " WHERE idx = ? AND pass = ?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0){
				r = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("비밀번호 검증 중 에러 발생");
		}
		return r;
	}
	
	//문의글 삭제하기
	public int deleteQT(String idx) { 
		int r = 0;
		
		String query = "DELETE FROM questboard WHERE IDX = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 삭제 오류");
		}
		
		return r;
	}

	//문의글 수정하기
	public int updateQT(qBoardDTO dto) {
		int r = 0;
		
		String query = "UPDATE questboard SET "
				+ " title = ?, qtext = ? ";
		if(!(dto.getAtext()==null||dto.getAtext().toString().equals(""))) {
				query += " ,atext = ?, answerdate = CURRENT_TIMESTAMP ";
		}
		query += " WHERE idx = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getQtext());
			if(!(dto.getAtext()==null||dto.getAtext().toString().equals(""))) {
				psmt.setString(3, dto.getAtext());
				psmt.setString(4, dto.getIdx());
			}else {
				psmt.setString(3, dto.getIdx());
			}
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("문의 수정 중 에러 발생");
		}
		return r;
	}
}
