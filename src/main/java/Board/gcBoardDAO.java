package Board;

import common.JDBConnect;

public class gcBoardDAO extends JDBConnect {
	//좋아요 로그인 유저가 클릭하였는지 여부 판단
	public int gcCntUser(String uid, String reciIdx) {
		int result = 0;

		String query = "SELECT COUNT(*) FROM gcBoard WHERE reciIndex = ? AND userId = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, reciIdx);
			psmt.setString(2, uid);
			rs = psmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 확인 중 에러 발생");
		}
		return result;
	}
	
	//좋아요 클릭시 insert
	public void insertGc(gcBoardDTO dto) {
		String query = "INSERT INTO gcBoard(reciIndex,userId) VALUES(?,?) ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getReciIndex());
			psmt.setString(2, dto.getUserId());
			psmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 입력 중 에러 발생");
		}
	}
	
	
	//좋아요취소시 delete
	public void deleteGc(gcBoardDTO dto) {
		String query = "DELETE FROM gcBoard WHERE reciIndex=? AND userId =?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getReciIndex());
			psmt.setString(2, dto.getUserId());
			psmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 입력 중 에러 발생");
		}
	}
}
