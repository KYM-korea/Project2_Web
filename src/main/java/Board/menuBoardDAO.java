package Board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class menuBoardDAO extends JDBConnect{
	//메뉴 등록
	public int insertMenu(menuBoardDTO dto) {
		int r = 0;
		
		String query = "INSERT INTO menuboard "
				+ " (title, expr, img, cntk, allergy, origin, ftype) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getExpr());
			psmt.setString(3, dto.getImg());
			psmt.setString(4, dto.getCntk());
			psmt.setString(5, dto.getAllergy());
			psmt.setString(6, dto.getOrigin());
			psmt.setString(7, dto.getFtype());
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 등록 중 에러발생");
		}
		
		return r;
	}
	
	//메뉴 출력문
	public List<menuBoardDTO> selectListME(Map<String, Object> map){
		List<menuBoardDTO> mBoardList = new Vector<menuBoardDTO>();
		
		String query = "SELECT * FROM menuboard ";
		if(map.get("ftype")!=null) {
			query += " WHERE ftype = '"+map.get("ftype")+"' ";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				menuBoardDTO dto = new menuBoardDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setAllergy(rs.getString("allergy"));
				dto.setCntk(rs.getString("cntk"));
				dto.setExpr(rs.getString("expr"));
				dto.setFtype(rs.getString("ftype"));
				dto.setImg(rs.getString("img"));
				dto.setOrigin(rs.getString("origin"));
				dto.setTitle(rs.getString("title"));
				
				mBoardList.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 조회 중 에러");
		}
		return mBoardList;
	}
	
	//메뉴 삭제
	public int deleteME(String idx) {
		int r = 0;
		
		String query = "DELETE FROM menuboard WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 삭제 중 에러 발생");
		}
		
		return r;
	}
	
	//메뉴 수정
	public int updateME(menuBoardDTO dto) {
		int r = 0;
		
		String query = " UPDATE menuboard SET "
				+ " title = ?, expr = ?, img = ? , cntk = ? , allergy = ?, origin = ?, ftype = ? "
				+ " WHERE idx = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getExpr());
			psmt.setString(3, dto.getImg());
			psmt.setString(4, dto.getCntk());
			psmt.setString(5, dto.getAllergy());
			psmt.setString(6, dto.getOrigin());
			psmt.setString(7, dto.getFtype());
			psmt.setString(8, dto.getIdx());
			r = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 수정 중 에러 발생");
		}
		return r;
	}
	//메뉴 상세 보기
	public menuBoardDTO selectViewME(String idx) {
		menuBoardDTO dto = new menuBoardDTO();
		
		String query = "SELECT * FROM menuboard WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setAllergy(rs.getString("allergy"));
				dto.setCntk(rs.getString("cntk"));
				dto.setExpr(rs.getString("expr"));
				dto.setFtype(rs.getString("ftype"));
				dto.setIdx(rs.getString("idx"));
				dto.setImg(rs.getString("img"));
				dto.setOrigin(rs.getString("origin"));
				dto.setTitle(rs.getString("title"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 조회 중 에러 발생");
		}
		
		return dto;
	}
	
}
