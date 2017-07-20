<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello world</title>
    <link href="<%=path%>/static/editormd/css/editormd.css" rel="stylesheet" type="text/css" />
<style>
	h1{
		text-align: center;
	}
	input[type='submit']{
		width: 100px;
		height: 50px;
		color: #fff; 
		background-color:#90DBFF;
		position:fixed;
		bottom:0px;
		left:45% ;
	}
</style>
</head>

<body>
	<h1>EditorMd 测试</h1>
	<br><br><br><br>
	
	<form action="<%=path%>/saveArticle" method="post">
		<!-- editormd start -->
	    <div class="editormd" id="test-editormd">
	    	<textarea class="editormd-markdown-textarea" name="content" id="editormd"></textarea>
	   		<!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
	   		<!-- html textarea 需要开启配置项 saveHTMLToTextarea == true -->
	   		<textarea class="editormd-html-textarea" name="editorhtml" id="editorhtml"></textarea>       
	    </div>
	    <!-- editormd end --> 
	
		<br><br><br><br>
		<input type="submit" value="保存">
	
	</form>
	<!-- editormd start -->
    <script type="text/javascript" src="<%=path%>/static/script/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/static/editormd/editormd.min.js"></script>
    <script type="text/javascript">
  	  var testEditor;

	  testEditor=$(function() {
	      editormd("test-editormd", {
	           width: "90%",
               height: 740,
               path : "<%=request.getContextPath()%>/static/editormd/lib/",
               previewTheme : "dark",				//预览的主题
               //theme : "dark",					//工具栏的主题
               //editorTheme : "pastel-on-dark",	//编辑栏的主题
               //markdown : md,						//这不知道是什么东西，开启的时候报错
               //watch : true,               		// 关闭实时预览
               syncScrolling : true,				//这个看着意思是同步滑动。。。。false的时候，右边不显示了。。。。
               codeFold : true,						//代码折叠 ?   选中 然后按快捷键 Ctrl + Q,好像设置成false也可以用.....不知道，知道求告知
               saveHTMLToTextarea : true,   		// 保存 HTML 到 Textarea
               searchReplace : true,
               htmlDecode : true,   // 开启 HTML 标签解析，为了安全性，默认不开启    
               //toolbar  : false,             			 //关闭工具栏
               //previewCodeHighlight : false, 			 // 关闭预览 HTML 的代码块高亮，默认开启
               emoji : true,				 // 表情
               taskList : true,			 // 这个具体不知道,知道求告知
               tocm            : true,       // Using [TOCM]
               tex : true,                   // 开启科学公式TeX语言支持，默认关闭
               flowChart : true,             // 开启流程图支持，默认关闭
               sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
               //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
               //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
               //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
               //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
               //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
               imageUpload : true,
               imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
               imageUploadURL : "<%=request.getContextPath()%>/uploadImg",        	//上传地址
               onload : function() {							//加载完后执行
                   console.log('onload', this);
                   //this.fullscreen();
                   //this.unwatch();
                   //this.watch().fullscreen();
                   
                   //this.width("100%");
                   //this.height(480);
                   //this.resize("100%", 640);
               }

	      });
	
	    	
	  });

</script>
  <!-- editormd end -->  
	
</body>
</html>