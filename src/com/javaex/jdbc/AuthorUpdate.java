package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			
			String query = "";
			query += " update author ";         //따옴표 앞,뒤 한 칸씩 띄우기
			query += " set author_desc = ? ";   //query문이 붙어있다고 인식하기 때문
			query += " where author_id = ? ";
			
			//테스트로 query문 확인
			//System.out.println(query);
			
			pstmt = conn.prepareStatement(query);//쿼리로 만들기
			pstmt.setString(1, "서울시 서초구 하이미디어 별관");
			pstmt.setInt(2, 21);
			
			/*
			update author
			set author_desc = '서울시 서초구 하이미디어 별관'
			where author_id = 21;
		    */
			int count = pstmt.executeUpdate();
			
			
		    // 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		         
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

		
		
		
	}

}
