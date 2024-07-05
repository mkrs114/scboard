package com.msnks.scboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msnks.scboard.service.ArticleService;
import com.msnks.scboard.util.Ut;
import com.msnks.scboard.vo.Article;
import com.msnks.scboard.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {


	// 인스턴스 변수 시작
	@Autowired
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	// 액션 메서드 시작
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String content)
	{
		boolean isLogined = false;
		int loginMemberId = 0;
		
		if( httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false ) {
			return ResultData.from("F-A", "로그인 후 이용해 주세요.");
		}
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력하세요.");
		}
		
		if(Ut.empty(content)) {
			return ResultData.from("F-1", "title(을)를 입력하세요.");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(loginMemberId, title, content);
		int id = (int)writeArticleRd.getData1();
		
		Article article = articleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd, article);
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model)
	{
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}


	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles()
	{
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트입니다.", articles);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id)
	{
		// String 과 Article Class가 리턴 값이므로 
		// Object를 사용한다.
		Article article = articleService.getArticle(id);
		
		if( article == null ) {
			// return id + "번 게시물이 존재하지 않습니다.";
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id), article);
		}

		return ResultData.from("F-1", Ut.f("%d번 게시물입니다.", id), article);
		// return article;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id)
	{
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if( httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false ) {
			return ResultData.from("F-A", "로그인 후 이용해 주세요.");
		}

		Article article = articleService.getArticle(id);
		
		if( article.getMemberid() != loginedMemberId ) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}

		if( article == null ) {
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("F-1", Ut.f("%d번 게시물을(를) 삭제하였습니다.", id), id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String content)
	{
		Article article = articleService.getArticle(id);
		
		if( article == null ) {
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id));
		}
		
		articleService.modifyArticle(id, title, content);
		
		return ResultData.from("F-1", Ut.f("%d번 게시물을(를) 수정하였습니다.", id), id);
	}
	// 액션 메서드 끝
}
