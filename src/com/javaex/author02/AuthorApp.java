package com.javaex.author02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AuthorDao authorDao = new AuthorDao();

		List<AuthorVo> authorList;

		// int count = authorDao.authorInsert("최태현", "경기도"); //작가 테이블에 데이터 저장
		// System.out.println(count + "건 등록되었습니다");
		
		
		
		// 등록
		AuthorVo authorVo1 = new AuthorVo("이문열", "경북영양");
		// authorDao.authorInsert(authorVo1); //작가 테이블에 데이터 저장
		
		AuthorVo authorVo2 = new AuthorVo("박경리", "경남통영");
		// authorDao.authorInsert(authorVo2);
		// authorDao.authorInsert("유시민", "제17대 국회의원");

		// 리스트
		authorList = authorDao.getAuthorList();
		// System.out.println(authorList.toString());

		// 리스트 전체 출력
		System.out.println("=======작가 리스트======");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());

		}

		// 삭제
		authorDao.authorDelete(2);

		// 리스트 출력

		authorList = authorDao.getAuthorList();

		System.out.println("=======작가 리스트======");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}

		// 리스트 수정
		AuthorVo authorVo4 = new AuthorVo(2, "김경리", "제주도");
		authorDao.authorUpdate(authorVo4);

		// 리스트 출력

		authorList = authorDao.getAuthorList();

		System.out.println("=======작가 리스트======");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}

	}

}
