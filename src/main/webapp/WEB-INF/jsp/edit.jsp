<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="<%=path%>/static/editormd/css/editormd.css" rel="stylesheet" type="text/css" />
<title>编辑 ${data.title }</title>
</head>
<body>
	<h1>${data.title }</h1>
	<br>
	<br>
	<form action="<%=path%>/edit/${data.article_id}" method="post">
		<input type="hidden" name="id" value="${data.article_id }">
		<div id="wordsView" style="width: 95%" class="editormd-preview-theme-dark">
		     <textarea style="display:none;" class="editormd-markdown-textarea" name="content">${data.content }</textarea>
		</div>
	
		<br><br><br><br>
		<input type="submit" name="up" value="更新">
	</form>
	

	<!-- editormd start -->
    <script type="text/javascript" src="<%=path%>/static/script/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/static/editormd/editormd.min.js"></script>
    <script type="text/javascript">
  	  var testEditor;

	  testEditor=$(function() {
	      editormd("wordsView", {
	           width   : "90%",
	           height  : 640,
	           codeFold : true,
	           path    : "<%=request.getContextPath()%>/static/editormd/lib/",
	           imageUpload : true,
	           imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
	           imageUploadURL : "<%=path%>/uploadImg",
	           previewTheme: "dark",//预览主题
	           emoji: true,
	           taskList: true, 
	           tocm: true,         // Using [TOCM]
	           tex: true,                   // 开启科学公式TeX语言支持，默认关闭
	           flowChart: true,             // 开启流程图支持，默认关闭
	           sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
	           saveHTMLToTextarea : true            
	      });
	
	  });

</script>
  <!-- editormd end --> 
</body>
</html>