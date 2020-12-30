package com.javaex.book01;

import java.util.List;

public class BookAuthorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//작가 6명 등록
		//AuthorDao, AuthorVo 이용해서 등록
		AuthorDao aDao = new AuthorDao();
		
		List<AuthorVo> aList = aDao.getAuthorList();
		
		// 저장
		AuthorVo aInsertVo1 = new AuthorVo("이문열", "경북영양");
		AuthorVo aInsertVo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo aInsertVo3 = new AuthorVo("유시민", "제17대 국회의원");
		AuthorVo aInsertVo4 = new AuthorVo("기안84", "기안동 거주 84년생");
		AuthorVo aInsertVo5 = new AuthorVo("강풀", "1세대 온라인 만화가");
		AuthorVo aInsertVo6 = new AuthorVo("김영하", "알쓸신잡");
		
		aDao.authorInsert(aInsertVo1);
		aDao.authorInsert(aInsertVo2);
		aDao.authorInsert(aInsertVo3);
		aDao.authorInsert(aInsertVo4);
		aDao.authorInsert(aInsertVo5);
		aDao.authorInsert(aInsertVo6);
		
		//(수정,삭제,리스트 )
		
		//책 8권 등록
		//BookDao, BookVo 이용해서 등록
		BookDao bDao = new BookDao();
		
		List<BookVo> bList = bDao.getBookList();
		
		// 저장
		BookVo bInsertVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "98/02/22", 1);
		BookVo bInsertVo2 = new BookVo("삼국지", "민음사", "02/03/01", 1);
		BookVo bInsertVo3 = new BookVo("토지", "마로니에북스", "12/08/15", 2);
		BookVo bInsertVo4 = new BookVo("유시민의 글쓰기 특강", "생각의 길", "15/04/01", 3);
		BookVo bInsertVo5 = new BookVo("패션왕", "중앙북스(books)", "12/02/22", 4);
		BookVo bInsertVo6 = new BookVo("순정만화", "재미주의", "11/08/03", 5);
		BookVo bInsertVo7 = new BookVo("오직 두 사람", "문학동네", "17/05/04", 6);
		BookVo bInsertVo8 = new BookVo("26년", "재미주의", "12/02/04", 5);
		
		bDao.bookInsert(bInsertVo1);
		bDao.bookInsert(bInsertVo2);
		bDao.bookInsert(bInsertVo3);
		bDao.bookInsert(bInsertVo4);
		bDao.bookInsert(bInsertVo5);
		bDao.bookInsert(bInsertVo6);
		bDao.bookInsert(bInsertVo7);
		bDao.bookInsert(bInsertVo8);
		
		
		//(수정,삭제,리스트)
		
		//책을 전체로 출력 (책--> 책정보+작가정보)
		//BookVo --> 책정보 + 작가정보
		bList = bDao.getBookListAll();
		
		System.out.println("==========책정보리스트==========");
		for(int i = 0; i < bList.size(); i++) {
			
			BookVo vinfo = bList.get(i);
			
			System.out.println(vinfo.getBookId() + "," + vinfo.getTitle() + "," + vinfo.getPubs() + "," + vinfo.getPubDate() + "," + vinfo.getAuthorId() + "," + vinfo.getAuthorName() + "," + vinfo.getAuthorDesc());
			
		}
		
		
		
	}


}
