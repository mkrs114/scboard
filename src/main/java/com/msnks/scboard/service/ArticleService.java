package com.msnks.scboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msnks.scboard.Repository.ArticleRepository;
import com.msnks.scboard.util.Ut;
import com.msnks.scboard.vo.Article;
import com.msnks.scboard.vo.ResultData;

@Service
public class ArticleService {

	// 인스턴스 변수 시작
	ArticleRepository articleRepository;
	// 인스턴스 끝

	// 생성자
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	// 서비스 메서드 시작
	public ResultData<Integer> writeArticle(int memberid, String title, String content)
	{
		articleRepository.writeArticle(memberid, title, content);
		
		int id = articleRepository.getlastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String content) {
		articleRepository.modifyArticle(id, title, content);
	}
	
}
