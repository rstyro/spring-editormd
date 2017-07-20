package top.lrshuai.editormd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import top.lrshuai.editormd.service.IUploadService;
import top.lrshuai.util.DateUtil;
import top.lrshuai.util.Myutil;
import top.lrshuai.util.UploadUtil;


@Controller
public class UploadController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IUploadService uploadService;
	
	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	public void uploadImg(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
		log.info("....图片上传.....");
		try {
			String filePath = "/upload/"+DateUtil.getDays()+"/"+Myutil.random(5)+".png";
			String resultPath = UploadUtil.uploadImg(filePath, file.getInputStream());
			System.out.println("path="+resultPath);
			response.getWriter().write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + filePath + "\"}" );
		} catch (Exception e) {
			e.printStackTrace();
			log.error("upload failed ", e);
			try {
				response.getWriter().write( "{\"success\": 0, \"message\":\"上传失败\",\"url\":\""+ "\"}" );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 保存Article
	 * @return
	 */
	@RequestMapping(value="/saveArticle",method=RequestMethod.POST)
	public ModelAndView saveArticle(@RequestParam(value="content",required=false) String edmdDoc,
			@RequestParam(value="editorhtml",required=false) String edmdHtml){
		ModelAndView view  = new ModelAndView();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			System.out.println("edmdDoc.leng="+edmdDoc.length());
			System.out.println("edmdHtml.leng="+edmdHtml.length());
			System.out.println("0.0");
			System.out.println("edmdDoc="+edmdDoc);
			System.out.println("==========================================");
			System.out.println("edmdHtml="+edmdHtml);
			//保存数据库，返回 1 说明成功，0 失败，
			long id = uploadService.saveArticle(edmdDoc);
			System.out.println("id="+id);
			map.put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("data", map);
		view.setViewName("success");
		return view;
	}
	
	@RequestMapping(value="/a/{id}")
	public ModelAndView goDatail(@PathVariable("id") String articleId){
		ModelAndView view  = new ModelAndView();
		Map<String,Object> map = null;
		try {
			System.out.println("articleId="+articleId);
			int id = Integer.parseInt(articleId);
			System.out.println("id="+id);
			map = uploadService.getArticle(id);
			System.out.println("map="+map);
			view.addObject("data", map);
			view.setViewName("detail");
		} catch (Exception e) {
			e.printStackTrace();
			view.setViewName("404");
		}
		return view;
	}
	@RequestMapping(value="/a/{id}/edit")
	public ModelAndView goEdit(@PathVariable("id") String str_id){
		ModelAndView view  = new ModelAndView();
		Map<String,Object> map = null;
		try {
			long id = Integer.parseInt(str_id);
			map = uploadService.getArticle(id);
			System.out.println("map="+map);
			view.addObject("data", map);
			view.setViewName("edit");
		} catch (Exception e) {
			e.printStackTrace();
			view.setViewName("404");
		}
		return view;
	}
	
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") String str_id,
			@RequestParam(value="content") String content){
		ModelAndView view  = new ModelAndView();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			System.out.println("?");
			long id = Integer.parseInt(str_id);
			uploadService.uploadArticle(content, id);
			map.put("id", id);
			view.addObject("data", map);
			view.setViewName("success");
		} catch (Exception e) {
			e.printStackTrace();
			view.setViewName("404");
		}
		return view;
	}
	
}
