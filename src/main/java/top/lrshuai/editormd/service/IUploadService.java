package top.lrshuai.editormd.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface IUploadService {

	public long saveArticle(String content);
	public long uploadArticle(String content,long id);
	
	public Map<String,Object> getArticle(long id);
}
