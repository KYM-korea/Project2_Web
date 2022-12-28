package Board;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class noBoardDAO extends JDBConnect{
	//공지사항 작성
	public int noWrite(noBoardDTO dto) {
		int r = 0 ;
		
		String query = "INSERT INTO notiBoard "
				+ " (title, content, ofile, sfile, attr )"
				+ " VALUES ( ?, ?, ?, ?, ? ) ";
		
		try {
			psmt= con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			psmt.setString(5, dto.getAttr());
			
			r=psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("작성 중 에러 발생");
		}
		
		return r;
	}
	
	//공지사항 조회수 증가
	public void noCntPlus(String idx) {
		String query = " UPDATE notiBoard SET "
				+ " vcnt = vcnt + 1 WHERE idx = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회수 증가 중 에러");
		}
	}
	
	//공지사항 출력하기
	public List<noBoardDTO> selectList(Map<String, Object> map, String attr) {
		List<noBoardDTO> nBoardList = new Vector<noBoardDTO>();
		String query = " SELECT * FROM notiBoard WHERE attr = ? ";
		if(map.get("searchWord") != null) {
			query += " AND " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "		ORDER BY idx DESC limit ?, ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, attr);
			psmt.setInt(2, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(3, Integer.parseInt(map.get("end").toString()));
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				noBoardDTO dto = new noBoardDTO();
				
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setVcnt(rs.getInt("vcnt"));
				dto.setDcnt(rs.getInt("dcnt"));
				
				nBoardList.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 중 에러");
		}
		return nBoardList;
	}
		
	//공지사항 총 게시글 반환
	public int totalCount(Map<String , Object> map, String attr) {
		int t = 0;
		
		String query = "SELECT COUNT(*) FROM notiBoard "
				+ " WHERE attr = ? ";
		if(map.get("searchWord")!=null) {
			query += " AND "+map.get("searchField")+" LIKE '%"
					+ map.get("searchWord") +"%'";
		}
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, attr);
			rs=psmt.executeQuery();
			rs.next();
			t = rs.getInt(1);	
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("카운팅 중 에러 발생");
		}
		return t;
	}
	
	//공지사항 상세보기
	public noBoardDTO selectViewNO(String idx) {
		noBoardDTO dto = new noBoardDTO();
		
		String query = "SELECT * FROM notiboard "
				+ " WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setVcnt(rs.getInt("vcnt"));
				dto.setDcnt(rs.getInt("dcnt"));
				dto.setAttr(rs.getString("attr"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("공지 조회중 에러 발생");
		}
		
		return dto;
	}
	
	//다운로드 카운트 증가
	public void noDCntPlus(String idx) {
		String query = " UPDATE notiBoard SET "
				+ " dcnt = dcnt + 1 WHERE idx = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회수 증가 중 에러");
		}
	}
	
	//공지사항 삭제
	public int deleteNO(String idx) {
		int r = 0;
		
		String query = "DELETE FROM notiboard WHERE idx=?";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, idx);
			r = psmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("공지 삭제 완료");
			e.printStackTrace();
		}
		
		return r;
	}
	
	//공지사항 수정
	public int updateNO(noBoardDTO dto) {
		int r = 0 ;
		
		String query = "UPDATE notiboard SET "
				+ " title=?, content=?, attr=?, ofile=?, sfile=? "
				+ " WHERE idx = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getAttr());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			
			r=psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("공지 수정 중 에러");
		}
		return r;
	}
}
