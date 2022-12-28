package Board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class commentDAO extends JDBConnect{
	//게시글 등록
	public int insertComment(commentDTO dto) {
		int result = 0;
		
		String query = " INSERT INTO rcommentboard"
				+ " (reciIndex, id, rcomment ) "
				+ " VALUES (?, ?, ?) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getReciIndex());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getRcomment());
			result = psmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("댓글 입력 중 에러 발생");
		}
		
		return result;
	}
	
	//게시글 조회
	public List<commentDTO> selectComment(String idx){
		List<commentDTO> commentBoardList = new Vector<commentDTO>();
		
		String query = " SELECT * FROM rcommentboard"
				+ " WHERE reciIndex = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				commentDTO dto = new commentDTO();
				dto.setId(rs.getString("id"));
				dto.setPostdate(rs.getString("postdate"));
				dto.setRcomment(rs.getString("rcomment"));
				dto.setReciIndex(rs.getString("reciIndex"));
				
				commentBoardList.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("댓글 호출 중 에러 발생");
		}
		
		return commentBoardList;
	}
}
