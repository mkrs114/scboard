package com.msnks.scboard.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.msnks.scboard.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public void writeArticle(int memberid, String title, String content);
	public List<Article> getArticles();
	public Article getArticle(int id);
	public void deleteArticle(int id);
	public void modifyArticle(int id, String title, String content);
	public int getlastInsertId();
	
    
}
