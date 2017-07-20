package top.lrshuai.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface Dao {
	
	public Map<String, Object> getArticleDetail(long id);
	
	public int saveArticle(Map<String, Object> parame);
	public int updateArticle(Map<String, Object> parame);
	
}
