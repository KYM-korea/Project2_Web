package Board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

//삭제가 필요 없는 테이블?
public class conBoardDAO extends JDBConnect{
	//상담 조회
	public List<conBoardDTO> selectCon(Map<String, Object> map) {
		List<conBoardDTO> cBoardList = new Vector<conBoardDTO>();
		
		String query = "SELECT * FROM consulBoard "; 
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
				conBoardDTO dto = new conBoardDTO();
				
				dto.setIdx(rs.getString("idx"));
				dto.setUname(rs.getString("uname"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setCtext(rs.getString("ctext"));
				dto.setAddr(rs.getString("addr"));
				dto.setPhone(rs.getString("phone"));
				
				cBoardList.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("상담 조회 중 에러 발생");
		}
		return cBoardList;
	}
	
	//상담 총 건수
	public int conCnt(Map<String , Object> map) {
		int t = 0;
		String query = "SELECT COUNT(*) FROM consulboard ";
		if(map.get("searchWord")!=null) {
			query += " WHERE "+map.get("searchField")+" LIKE '%"
					+ map.get("searchWord") +"%'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			t = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("상담 총 건수 조회 에러");
		}
		return t;
	}
	
	//상담 상세 보기
	public conBoardDTO selectViewCon(String idx) {
		conBoardDTO dto = new conBoardDTO();
		
		String query = "SELECT * FROM consulBoard "
				+ " WHERE idx=? ";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, idx);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setAddr(rs.getString("addr"));
				dto.setCtext(rs.getString("ctext"));
				dto.setIdx(rs.getString("idx"));
				dto.setPhone(rs.getString("phone"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setUname(rs.getString("uname"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("상담 상세보기 중 에러 발생");
		}
		return dto;
	}
	
	//상담 등록
	public int insertCon(conBoardDTO dto) {
		int r = 0;
		
		String query = "INSERT INTO consulboard "
				+ " (uname, ctext, addr, phone) "
				+ " VALUES ( ?, ?, ?, ? ) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getUname());
			psmt.setString(2, dto.getCtext());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getPhone());
			
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("상담 등록 에러");
		}
		return r;
	}
	
	//쓸모없는 게시물 혹은 완료된 게시글 삭제
	public int deleteCON(String idx) {
		int r = 0;
		
		String query = "DELETE FROM consulboard WHERE idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("상담 삭제 중 에러 발생");
		}
		return r;
	}
}