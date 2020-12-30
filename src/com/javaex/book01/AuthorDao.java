package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	// 메소드g/s
	// 메소드 일반

	// DB접속
	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			System.out.println("[접속성공]");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
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

	// 추가

	public int authorInsert(AuthorVo aVo) {

		int count = 0;

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// insert into author values (seq_author_id.nextval, '최태현', '경기도 부천시');
			String query = "insert into author values (seq_author_id.nextval, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aVo.getAuthorName());
			pstmt.setString(2, aVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[Dao]" + count + "건이 저장되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	close();
	return count;

	}

	// 삭제
	public int authorDelete(int authorId) {

		int count = 0;
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// delete from author where author_id = 7;
			String query = "delete from author where author_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[Dao]" + count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}

	// 수정
	public int authorUpdate(AuthorVo aVo) {

		int count = 0;
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// update author set author_desc = '나혼자산다 출연' where author_id = 4;
			String query = "";
			query += " update author ";
			query += " set author_desc = ? ";
			query += " where author_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, aVo.getAuthorDesc());
			pstmt.setInt(2, aVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[Dao]" + count + "건이 수정되었습니다.");


		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}

	// 리스트
	public List<AuthorVo> getAuthorList() {

		List<AuthorVo> aList = new ArrayList<AuthorVo>();

		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// select author_id, author_name, author_desc from author;
			String query = "";
			query += " select author_id, author_name, author_desc ";
			query += " from author ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo avo = new AuthorVo(authorId, authorName, authorDesc);

				aList.add(avo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		return aList;

	}

}
