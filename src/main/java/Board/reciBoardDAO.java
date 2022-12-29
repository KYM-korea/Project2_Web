package Board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class reciBoardDAO extends JDBConnect{
	
	//레시피 등록
	public int insertRECI(reciBoardDTO dto) {
		int result = 0;
		
		String query = "INSERT INTO reciboard "
				+ " (title, ingre, src, kind, id, content, img)"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ? ) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getIngre());
			psmt.setString(3, dto.getSrc());
			psmt.setString(4, dto.getKind());
			psmt.setString(5, dto.getId());
			psmt.setString(6, dto.getContent());
			psmt.setString(7, dto.getImg());
			result=psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("레시피 등록 중 에러");
		}
		return result;
	}
	
	//레시피 목록 출력
	public List<reciBoardDTO> selectListRECI(Map<String, Object> map){
		List<reciBoardDTO> rBoardList = new Vector<reciBoardDTO>();
		
		String query = "SELECT * FROM reciboard " +
			" WHERE kind = '"+map.get("kind")+"' ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				reciBoardDTO dto = new reciBoardDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setIngre(rs.getString("ingre"));
				dto.setSrc(rs.getString("src"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setKind(rs.getString("kind"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setId(rs.getString("Id"));
				dto.setContent(rs.getString("content"));
				dto.setGc(rs.getInt("gc"));
				dto.setImg(rs.getString("img"));
				
				rBoardList.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 조회 중 에러");
		}
		
		return rBoardList;
	}
	
	//레시피 상세보기
	public reciBoardDTO selectViewRE(String idx) {
		reciBoardDTO dto = new reciBoardDTO();
		
		String query = "SELECT * FROM reciboard WHERE idx = ?";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setIngre(rs.getString("ingre"));
				dto.setSrc(rs.getString("src"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setKind(rs.getString("kind"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setId(rs.getString("Id"));
				dto.setContent(rs.getString("content"));
				dto.setGc(rs.getInt("gc"));
				dto.setImg(rs.getString("img"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("상세보기 호출 중 에러 발생");
		}
		
		return dto;
	}

	//좋아요 증가
	public void updatePlusGc(String idx) {
		String query = "UPDATE reciboard SET gc = gc + 1 WHERE idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.execute();
		}catch (Exception e) {
		}
	}
	
	//좋아요 감소
	public void updateMinuGc(String idx) {
		String query = "UPDATE reciboard SET gc = gc - 1 WHERE idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.execute();
		}catch (Exception e) {
		}
	}
	
	//조회수 증가
	public void updatePlusVc(String idx) {
		String query = "UPDATE reciboard SET visitcount = visitcount + 1 WHERE idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.execute();
		}catch (Exception e) {
		}
	}
}
