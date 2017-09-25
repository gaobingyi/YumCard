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
	#account_old,#account_new,#password{
	width:220px;
	}
</style>
<script type="text/javascript">
$(function(){
	// 判断输入框内容是否为空（全空格视为空）
	$("#excute").click(function(){
		var $account_old = $("#account_old").val().trim();
		var $account_new = $("#account_new").val().trim();
		var $password = $("#password").val().trim();		
    if ($account_old=="" || $account_new=="" || $password=="") {
    	alert("输入为空！");
    	return false;
    }
	})	
})
</script>
</head>
<body>
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
        <dd><a href="login_account.jsp">登录账户</a></dd>
        <dd class="current"><a href="change_account.jsp">变更账户(慎用)</a></dd>       
		<dd><a href="adjust_account_balance.jsp">调整账户余额</a></dd>
		<dd><a href="adjust_card_balance.jsp">调整卡余额</a></dd>
		<dd><a href="#">******</a></dd>
        <dd><a href="#">******</a></dd>
      </dl>    
    </div>
    <div id="content"><!-- 内容  -->	
	  <dl>
      <dt>变更账户</dt>
      <dd><!-- 存放查询输入框 -->	
  		<form action="/YumCard/ChangeAccountServlet" method="post">
			<label>原账户</label>
			<input id="account_old" type="text" name="account_old"
			placeholder="请输入原账号（手机号）"><br>
			<label>新账户</label>
			<input id="account_new" type="text" name="account_new"
			placeholder="请输入新账号（手机号）"><br>
			<label>品&nbsp;&nbsp;&nbsp;牌</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="radio" type="radio" name="brand" value="001" id="yum"><label for="yum">YUM</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="radio" type="radio" name="brand" value="002" id="kfc" checked="checked"><label for="kfc">KFC</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="radio" type="radio" name="brand" value="003" id="phdi"><label for="phdi">PHDI</label>
			&nbsp;&nbsp;
			<br>
			<label>操作码</label>
			<input id="password" type="password" name="password"
			placeholder="请输入操作码">	
			<br>
			<input id="excute" type="submit" value="确定">	
			<input id="reset" type="reset" value="重置">	
			</form>
  		</dd>
    </dl> 
	  <dl>
      <dt>操作结果</dt>
      <dd ><!-- 存放查询结果（表格）-->
			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			AccountInfo accountInfo = (AccountInfo) request.getAttribute("accountInfo");
			if (errorMessage != null) {
				if (errorMessage.equals("1")) {
					out.println("操作码错误！");
				} else if (errorMessage.equals("2")) {
					out.println("原账户不存在！");
				} else {
					out.println("新账户已有余额，不能变更账户！");
				}
			} else {
				//返回修改结果
				if (accountInfo != null) {
				%>
				<table id="customers">
				<tr><th>账户</th><th>品牌</th><th>余额</th></tr>
				<tr>
					<td><%=accountInfo.getMobile()%></td>
					<td><%=accountInfo.getIssuerBrandId()%></td>
					<td><%=accountInfo.getAccountBalance()/100.0%></td>
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
</body>
</html>