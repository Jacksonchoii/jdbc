package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

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

	public int bookInsert(BookVo bVo) {


		int count = 0;
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// insert into book values(seq_book_id.nextval, '왜나는너를사랑하는가', '청미래', '07/07/30',
			// 7);
			String query = "insert into book values (seq_book_id.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getPubs());
			pstmt.setString(3, bVo.getPubDate());
			pstmt.setInt(4, bVo.getAuthorId());

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
	public int bookDelete(int bookId) {

		int count = 0;
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// delete from book where book_id = 5;
			String query = "delete from book where book_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

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
	public int bookUpdate(BookVo bVo) {

		
		int count = 0;
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// update book set title = '복학왕' where book_id = 5;
			String query = "";
			query += " update book ";
			query += " set title = ? ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setInt(2, bVo.getBookId());

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
	public List<BookVo> getBookList() {

		List<BookVo> bList = new ArrayList<BookVo>();

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " select book_id, ";
			query += " 		  title, ";
			query += " 		  pubs, ";
			query += " 		  pub_date, ";
			query += " 		  author_id, ";
			query += " from book ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");

				BookVo bvo01 = new BookVo(bookId, title, pubs, pubDate, authorId);

				bList.add(bvo01);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return bList;

	}

	// 리스트 전체
	public List<BookVo> getBookListAll() {

		List<BookVo> bAllList = new ArrayList<BookVo>();

		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " select b.book_id, ";
			query += " 		  b.title, ";
			query += " 		  b.pubs, ";
			query += " 		  to_char(b.pub_date, 'yyyy-mm-dd')";
			query += " 		  a.author_id, ";
			query += "        a.author_name, ";
			query += "        a.author_desc ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";
			query += " order by b.book_id asc ";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				BookVo bvo02 = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);

				bAllList.add(bvo02);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return bAllList;

	}

}
