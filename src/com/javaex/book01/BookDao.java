package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	//추가
	
		public int bookInsert(BookVo aVo) {
		
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				//ResultSet rs = null;
			
				int count = 0;
				
				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);
			
				    // 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);
			
				    // 3. SQL문 준비 / 바인딩 / 실행
					//insert into book values(seq_book_id.nextval, '왜나는너를사랑하는가', '청미래', '07/07/30', 7);
					String query = "insert into book values (seq_book_id.nextval, ?, ?, ?, ?)";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bVo.getTitle());
					pstmt.setString(2, bVo.getPubs());
					pstmt.setString(3, bVo.getPub_date());
					pstmt.setInt(4, bVo.getAuthor_id());
					
					count = pstmt.executeUpdate();
					
					
					
				    // 4.결과처리
					System.out.println("[Dao]" + count + "건이 저장되었습니다.");
			
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
				
				return count;
			}
	
		//삭제
		public int bookDelete(int bookId) {
		
				
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				//ResultSet rs = null;
			
				int count = 0;
				
				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);

				    // 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);
					
			
				    // 3. SQL문 준비 / 바인딩 / 실행
					//delete from book where book_id = 5;
					String query = "delete from book where book_id = ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, bookId);
					
					count = pstmt.executeUpdate();
					
				    // 4.결과처리
					System.out.println("[Dao]" + count + "건이 삭제되었습니다.");
					
			
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
				return count;
		}
		
		
		//수정
		public int bookUpdate(BookVo bVo) {
		
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				//ResultSet rs = null;
			
				int count = 0;
				
				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName(driver);
			
				    // 2. Connection 얻어오기
					conn = DriverManager.getConnection(url, id, pw);
			
				    // 3. SQL문 준비 / 바인딩 / 실행
					//update book set title = '복학왕' where book_id = 5;
					String query = "";
					query += " update book ";
					query += " set title = ? ";
					query += " where book_id = ? ";
					
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, bVo.getTitle());
					pstmt.setInt(2, bVo.getBook_id());
					
					count = pstmt.executeUpdate();
					
					
				    // 4.결과처리
					System.out.println("[Dao]" + count + "건이 수정되었습니다.");
			
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
				return count;
		}
		
		
		// 리스트
		public List<BookVo> getBookList(){
			
			List<BookVo> bLlist = new ArrayList<BookVo>();
			
			// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
			    // 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

			    // 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);

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
				while(rs.next()) {
					
					int bookId = rs.getInt("book_id");
					String title = rs.getString("title");
					String pubs = rs.getString("pubs");
					String pub_date = rs.getString("pub_date");
					int author_id = rs.getInt("author_id");
					
					BookVo bvo01 = new BookVo(bookId, title, pubs, pub_date, author_id);
					
					bList.add(bvo01);
					
				}
				
				

			} catch (ClassNotFoundException e) {
			    System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			} finally {
			   
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
			
			return bList;
			
		}
		
	
}
