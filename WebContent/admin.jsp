<%@ page language="java" import="java.util.*,com.yum.card.model.*"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="shortcut icon" href="favicon.ico">
<script src="js/jquery-3.1.1.js"></script>
<script src="js/script.js" type="text/javascript"></script>
<title>百胜卡IT专用</title>
<style type="text/css">
#string_before,#string_after,#admin_password,
#admin_password2,#operation_code {
	width:250px;
}
#encrypt,#add {
	font-size:20px;
	color:#fff;
	background:#3399cc;
	padding:3px 20px 3px 20px;
	border-radius:5px;
	margin:5px 10px 5px 10px;
}
</style>

<script type="text/javascript">
$(function(){   
	// 判断输入框内容是否为空（全空格视为空）
	$("#encrypt").click(function(){
		var $val1 = $("#string_before").val().trim();
		var $val2 = $("#admin_password").val().trim();
	    if ($val1 == "" || $val2 == "") {
	    	alert("请输入待加密字符串和管理员密码！");
	    	return false;
	    }
	})
	$("#add").click(function(){
		var $val3 = $("#operation_code").val().trim();
		var $val4 = $("#admin_password2").val().trim();
	    if ($val3 == "" || $val4 == "") {
	    	alert("请输入待加密字符串和管理员密码！");
	    	return false;
	    }
	})
	if ($("#errorMessage").val() == "no") {
		//禁用
		$("#string_after").attr("disabled", true);	
	}
})
</script>
</head>
<body>
<div id="wrap">
  <div class="logo">
		百胜卡IT专用
		<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
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
        <dd><a href="login_account.jsp">登录账户</a></dd>
        <dd><a href="change_account.jsp">变更账户(慎用)</a></dd>       
		<dd><a href="adjust_account_balance.jsp">调整账户余额</a></dd>
		<dd><a href="adjust_card_balance.jsp">调整卡余额</a></dd>
		<dd><a href="#">******</a></dd>
        <dd><a href="#">******</a></dd>
      </dl>    
    </div>
    <div id="content"><!-- 内容  -->	
	  <dl>
      <dt>加密字符串</dt>
      <dd>
      	<%
				String string_before = (String) request.getAttribute("string_before");
				String string_after = (String) request.getAttribute("string_after");
				String errorMessage = (String) request.getAttribute("errorMessage");				
				%>
  			<form action="/YumCard/AdminServlet" method="post">
  				<label>加密字符串</label>
				  <input id="string_before" type="text" name="string_before" 
					  value='<%=string_before==null ? "" : string_before %>' 
					  placeholder="请输入待加密的字符串">
				  <br>
				  <label>管理员密码</label>
				  <input id="admin_password" type="password" name="admin_password" 
				  	placeholder="请输入管理员密码">
				  <br>
				  <label>加密后结果</label>
				  <input id="string_after" type="text" name="string_after"
				  	value='<%=string_after==null ? "" : string_after %>'>
				  <br>
				  <input id="encrypt" type="submit" value="加密">
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <input id="reset" type="reset"  value="重置">
				  <input id="errorMessage" type="hidden" 
				  	value='<%=errorMessage==null ? "no" : "yes"%>'>
				</form>				 
		  </dd>
    </dl> 
	  <dl>
      <dt>分配操作码</dt>
      <dd>
  			<%
  			String errorMessage2 = (String) request.getAttribute("errorMessage2");
  			%>
  			<form action="/YumCard/AdminServlet" method="post">
  				<label>分配操作码</label>
				  <input id="operation_code" type="text" name="operation_code" 
					  placeholder="请输入操作码">
				  <br>
				  <label>管理员密码</label>
				  <input id="admin_password2" type="password" name="admin_password2" 
				  	placeholder="请输入管理员密码">
				  <br>
				  <input id="add" type="submit" value="增加">
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <input id="reset" type="reset"  value="重置">
				</form>
				<%
				if (errorMessage2 != null) {
					if (errorMessage2.equals("1")) {
						%>
						<div>管理员密码错误！</div>
						<%					
					}
					if (errorMessage2.equals("2")) {
						%>
						<div>分配成功！</div>
						<%
					}
				}
				%>
		  </dd>
    </dl>
		</div>
	</div>
</div>
</body>
</html>
