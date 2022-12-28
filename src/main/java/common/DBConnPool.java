package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public DBConnPool() {
		try {
			//초기 컨텍스트 선언
			Context init = new InitialContext();
			Context cxt = (Context)init.lookup("java:comp/env");
			//server.xml에 존재하는 dbcp_mysql변수를 읽어 src에 저장
			DataSource src = (DataSource) cxt.lookup("dbcp_mysql");
			con = src.getConnection();
			System.out.println("커넥션 완료");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("커넥션 풀 에러");
		}
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(psmt!=null)psmt.close();
			if(stmt!=null)stmt.close();
			if(rs!=null)rs.close();
			
			System.out.println("풀커넥션 자원 반환 완료");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("풀커넥션 자원반환 에러");
		}
	}
}