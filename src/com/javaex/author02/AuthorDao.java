package com.javaex.author02;

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

	// 생성자 디폴트 생략(다른 생성자 없음)

	// 메소드 g/s

	// 일반메소드

	// DB접속 -- 내부에서만 쓰기 때문에 private
	private void getConnection() {
		// DB접속 기능

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
			if (rs != null) {
				rs.close();
			}
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

	// 작가 수정하기
	public int authorUpdate(AuthorVo authorVo) {

		int count = 0;

		// DB접속
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author "; // 따옴표 앞,뒤 한 칸씩 띄우기
			query += " set author_name = ? "; // query문이 붙어있다고 인식하기 때문
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);// 쿼리로 만들기
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao]" + count + "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 자원정리
		close();
		return count;
	}

	// 작가 삭제하기
	public int authorDelete(int authorId) {

		int count = 0;

		// DB접속
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			// 테스트
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 21);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao]" + count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 자원정리
		close();

		return count;
	}

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// DB접속
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  author_id, ";
			query += " 		   author_name, ";
			query += " 		   author_desc ";
			query += " from author ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			/*
			 * select author_id, author_name, author_desc from author;
			 */

			rs = pstmt.executeQuery();

			// 4.결과처리
			// rs에 있는 데이터를 List<authorVo> 로 구성해야 한다.

			while (rs.next()) {
				int authorId = rs.getInt("author_id"); // (1)
				String authorName = rs.getString("author_name"); // (2)
				String authorDesc = rs.getString("author_desc"); // (3) 으로 써도 값은 같다.

				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		// 자원정리
		close();
		return authorList;
	}

	// 작가 저장 기능
	public int authorInsert(AuthorVo authorVo) {

		// DBwjqthr
		getConnection();

		int count = 0;

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?) ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			/*
			 * insert into author values (seq_author_id.nextval, '최태현', '경기도 부천시');
			 */

			// 4.결과처리
			System.out.println("[dao] " + count + "건이 저장되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();

		return count;
	}

}
