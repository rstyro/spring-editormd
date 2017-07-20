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
<title>详情--${data.title }</title>
</head>
<body>
	<h1>传过来的代码：</h1>
	<br>
	<br>
	
	<div id="markdownView" style="width: 95%" class="editormd-preview-theme-dark">
	     <textarea style="display:none;" name="content">${data.content }</textarea>
	</div>
	
	<br><br><br><br>
	<a href="<%=path%>/a/${data.article_id}/edit">去编辑</a><br>
	<a href="<%=path%>/">去新增</a>

	<!-- editormd start -->
    <script type="text/javascript" src="<%=path%>/static/script/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/static/editormd/editormd.min.js"></script>
    <script src="<%=path%>/static/editormd/lib/marked.min.js"></script>
	<script src="<%=path%>/static/editormd/lib/prettify.min.js"></script>
	<script src="<%=path%>/static/editormd/lib/raphael.min.js"></script>
	<script src="<%=path%>/static/editormd/lib/underscore.min.js"></script>
	<script src="<%=path%>/static/editormd/lib/sequence-diagram.min.js"></script>
	<script src="<%=path%>/static/editormd/lib/flowchart.min.js"></script>
	<script src="<%=path%>/static/editormd/lib/jquery.flowchart.min.js"></script>
    <script type="text/javascript">
	
	$(document).ready(function() {
	    var markdownView;
	    markdownView = editormd.markdownToHTML("markdownView", {
	    	//htmlDecode      : "style,script,iframe",  // you can filter tags decode
	        emoji           : true,
	        taskList        : true,
	        tex             : true,  // 默认不解析
	        flowChart       : true,  // 默认不解析
	        sequenceDiagram : true,  // 默认不解析
	        previewTheme: "dark",//预览主题
	    });
	    
	})
	
	
</script>
  <!-- editormd end --> 
</body>
</html>