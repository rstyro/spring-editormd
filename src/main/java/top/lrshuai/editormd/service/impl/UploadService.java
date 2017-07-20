package top.lrshuai.editormd.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import top.lrshuai.dao.Dao;
import top.lrshuai.editormd.service.IUploadService;
import top.lrshuai.util.DateUtil;

@Service
public class UploadService implements IUploadService{

	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private Dao dao;
	
	
	public long saveArticle(String content) {
		Map<String,Object> parame = new HashMap<String, Object>();
		long articleId = 0;
		try {
			parame.put("content", content);
			parame.put("user_id", "1");
			parame.put("title", "随便写静态的了，赖的从前端传");
			parame.put("create_time", DateUtil.getTime());
			dao.saveArticle(parame);
			articleId = (Long) parame.get("id");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("save failed", e);
			//因为try catch 出异常 spring 是不会回滚的
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//			throw new RuntimeException("error");		//这样写也可以，这样写spring 再会捕获到
		}
		return articleId;
	}
	
	public long uploadArticle(String content,long id) {
		Map<String,Object> parame = new HashMap<String, Object>();
		try {
			parame.put("content", content);
			parame.put("article_id", id);
			dao.updateArticle(parame);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("save failed", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return id;
	}
	
	public Map<String, Object> getArticle(long id) {
		Map<String,Object> data = dao.getArticleDetail(id);
		if(data == null)
			data = new HashMap<String, Object>();
		return data;
	}

}
