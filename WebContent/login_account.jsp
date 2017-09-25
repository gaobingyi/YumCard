<%@ page language="java" import="java.util.*,com.yum.card.model.*"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css"><!-- 样式表   -->
<link rel="shortcut icon" href="favicon.ico"><!-- 浏览器图标   -->
<script src="js/jquery-3.1.1.js"></script><!-- jQuery   -->
<script src="js/script.js" type="text/javascript"></script>
<title>百胜卡IT专用</title>
<style type="text/css">
#excute {
	font-size:20px;
	color:#fff;
	background:#3399cc;
	padding:3px 20px 3px 20px;
	border-radius:5px;
	margin:5px 10px 5px 10px;
}
#account,#operation_code{
	width:220px;
}
label a{
	font-size:20px;
}
span{
	color:red;
}
</style>
<script type="text/javascript">
$(function(){
	// 判断输入框内容是否为空（全空格视为空）
	$("#excute").click(function(){
	    if ($("#account").val().trim() == ""
	    		|| $("#operation_code").val().trim() == "") {
	    	alert("输入为空！");
	    	return false;
	    }
	})
	
	if ($("#original_login_type").val() == "A") {
		$("#excute").attr("value","确定");
	} else {
		$("#excute").attr("value","恢复");
		//$('#reset').attr("disabled", true);
	}
})
</script>
</head>
<body>
<%
	String account = (String) request.getAttribute("account");
	String brand = (String) request.getAttribute("brand");
	String operation_code = (String) request.getAttribute("operation_code");
	String original_login_password = (String) request.getAttribute("original_login_password");
	String original_login_type = (String) request.getAttribute("original_login_type");
	System.out.println(original_login_password + " " + original_login_type);

%>
<div id="wrap">
  <div class="logo">
		百胜卡IT专用
		<a href="index.jsp" target="_self">首页</a>
  </div>
  <div id="mainbody"><!-- 主体  -->
    <div id="sidebar"><!-- 侧边导航栏  -->
      <dl>
        <dt>用户系统</dt>       
        <dd><a href="query_user_info.jsp">查询用户信息</a></dd>
        <dd><a href="query_card_info_users.jsp">查询卡信息</a></dd>
        <dd><a href="query_card_conversion_info.jsp">查询实转虚信息</a></dd> 
        <dd><a href="query_order_info.jsp">查询订单信息</a></dd>
        <dd><a href="#">******</a></dd>
		<dd><a href="query_cmb_transaction_info.jsp">查询招行卡券交易信息</a></dd>
		<dd><a href="#">******</a></dd>
		<dd><a href="#">******</a></dd>
		<dd><a href="#">******</a></dd>
        <dd><a href="#">******</a></dd>
      </dl>
      <dl>
        <dt>账户系统</dt>        
        <dd><a href="query_account_info.jsp">查询账户信息</a></dd>
        <dd><a href="query_card_info_payment.jsp">查询卡信息</a></dd>
        <dd><a href="query_transaction_info.jsp">查询交易信息</a></dd>
        <dd><a href="#">******</a></dd>
        <dd class="current"><a href="login_account.jsp">登录账户</a></dd>
        <dd><a href="change_account.jsp">变更账户(慎用)</a></dd>       
		<dd><a href="adjust_account_balance.jsp">调整账户余额</a></dd>
		<dd><a href="adjust_card_balance.jsp">调整卡余额</a></dd>
		<dd><a href="#">******</a></dd>
        <dd><a href="#">******</a></dd>
      </dl>    
    </div>
    <div id="content"><!-- 内容  -->	
	  <dl>
      <dt>登录账户</dt>
      <dd><!-- 存放查询输入框 -->	
  		<form action="/YumCard/LoginAccountServlet" method="post">
				<label>账&nbsp;&nbsp;户</label>
				<input id="account" type="text" name="account" placeholder="请输入账号（手机号）" 
						value='<%=account==null?"":account%>'><br>
				<label>品&nbsp;&nbsp;牌</label>
				&nbsp;&nbsp;&nbsp;
				<input class="radio" type="radio" name="brand" value="210010001" id="yum"><label for="yum">YUM</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="radio" type="radio" name="brand" value="210010002" id="kfc" checked="checked"><label for="kfc">KFC</label>
				&nbsp;&nbsp;&nbsp;&nbsp;	
				<input class="radio" type="radio" name="brand" value="210010003" id="phdi"><label for="phdi">PHDI</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<label>操作码</label>
				<input id="operation_code" type="password" name="operation_code" placeholder="请输入操作码"
						value='<%=operation_code==null?"":operation_code%>'>	
				<!-- 登录密码 -->
				<input id="original_login_password" type="hidden" name="original_login_password"
						value='<%=original_login_password==null?"":original_login_password%>'>		
				<!-- 登录类型 -->
				<!-- 将登录类型作为判断标志 -->
				<input id="original_login_type" type="hidden" name="original_login_type"
						value='<%=original_login_type==null?"A":original_login_type%>'>
				<br>
				<input id="excute" type="submit" value="确定">
				<!-- <input id="reset" type="reset" value="重置"> -->	
				<br>
				<label><a href="https://cardapi.yum.com.cn/m/y/login.html" target='_blank'>YUM登录</a></label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label><a href="http://card.kfc.com.cn/m/kw/login.html" target='_blank'>KFC登录</a></label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label><a href="https://cardapi.yum.com.cn/m/pw/login.html" target='_blank'>PHDI登录</a></label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<span>注意：请在点击"确定"按钮替换登录密码后，不要关闭本页面和修改页面参数，并在登录成功后，务必点击"恢复"按钮！	</span>
			</form>
  		</dd>
    </dl> 
	  <dl>
      <dt>操作结果</dt>
      <dd ><!-- 存放查询结果（表格）-->
			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			String current_login_password = (String) request.getAttribute("current_login_password");
			String current_login_type = (String) request.getAttribute("current_login_type");	
			
			if (errorMessage != null) {
				if (errorMessage.equals("1")) {
					out.println("操作码错误！");
				} else {
					out.println("账户不存在！");
				}
			} else {
				//返回修改结果
				if (current_login_password != null && current_login_type != null) {
				%>
				<table id="customers">
				<%-- <tr><th>原始登录密码</th><td><%=original_login_password%></td></tr>
				<tr><th>原始登录类型</th><td><%=original_login_type%></td></tr>
				<tr><th>当前登录密码</th><td><%=current_login_password%></td></tr>
				<tr><th>当前登录类型</th><td><%=current_login_type%></td></tr> --%>
				<tr><th>原始登录密码</th><th>原始登录类型</th><th>当前登录密码</th><th>当前登录类型</th></tr>
				<tr>
					<td><%=original_login_password%></td>
					<td><%=original_login_type%></td>
					<td><%=current_login_password%></td>
					<td><%=current_login_type%></td>
				</tr>
				</table>
				<%
				}
			}
			%>
		</dd>
    </dl>
    </div>
  </div>
</div>
<script>

var brand = "<%=brand%>";
if (brand != null) {
	if (brand == "210010001") {
		$("input[name='brand'][value='210010001']").attr("checked",true); 
		$("input[name='brand'][value='210010002']").attr("checked",false); 
		$("input[name='brand'][value='210010003']").attr("checked",false); 
	} else if (brand == "210010002") {
		$("input[name='brand'][value='210010001']").attr("checked",false); 
		$("input[name='brand'][value='210010002']").attr("checked",true); 
		$("input[name='brand'][value='210010003']").attr("checked",false); 
	} else if (brand == "210010003"){
		$("input[name='brand'][value='210010001']").attr("checked",false); 
		$("input[name='brand'][value='210010002']").attr("checked",false); 
		$("input[name='brand'][value='210010003']").attr("checked",true); 
	} else {
  }
}
</script>
</body>
</html>