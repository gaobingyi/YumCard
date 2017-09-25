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
#account,#balance,#operation_code{
	width:250px;
}
span{
	color:red;
}
</style>
<script type="text/javascript">
$(function(){
	$("#excute").click(function(){
		var $account = $("#account").val().trim();
		var $balance = $("#balance").val().trim();
		// 判断输入框内容是否为空（全空格视为空）
    if ($("#account").val().trim() == ""
    		|| $("#balance").val().trim() == ""
    		|| $("#operation_code").val().trim() == "") {
    	alert("输入为空！");
    	return false;
    }
 		// 判断手机号是否为11位且为数字
		if ($account.length != 11 || 
				!$.isNumeric($account)) {
			alert("账户输入错误！");
	    return false;
		}
 		//判断金额格式是否合法
 		if (!$.isNumeric($balance)) {
			alert("金额输入错误！");
			return false;
		}
 		var $arr = $balance.split(".");
		if ($arr.length == 1) {
			
		} else if ($arr.length == 2) {
			if ($arr[1].length > 2) {
				alert("金额输入错误！");
				return false;
			}
		} else {
			alert("金额输入错误！");
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
        <dd><a href="change_account.jsp">变更账户(慎用)</a></dd>       
		<dd class="current"><a href="adjust_account_balance.jsp">调整账户余额</a></dd>
		<dd><a href="adjust_card_balance.jsp">调整卡余额</a></dd>
		<dd><a href="#">******</a></dd>
        <dd><a href="#">******</a></dd>
      </dl>    
    </div>
    <div id="content"><!-- 内容  -->	
	  <dl>
      <dt>调整账户余额</dt>
      <dd><!-- 存放查询输入框 -->	
  		<form action="/YumCard/AdjustAccountBalanceServlet" method="post">
				<label>账&nbsp;&nbsp;&nbsp;户</label>
				<input id="account" type="text" name="account"
				placeholder="请输入账号（手机号）">
				<br>
				<label>品&nbsp;&nbsp;&nbsp;牌</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="radio" type="radio" name="brand" value="001" id="yum"><label for="yum">YUM</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="radio" type="radio" name="brand" value="002" id="kfc" checked="checked"><label for="kfc">KFC</label>
				&nbsp;&nbsp;&nbsp;&nbsp;	
				<input class="radio" type="radio" name="brand" value="003" id="phdi"><label for="phdi">PHDI</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<label>金&nbsp;&nbsp;&nbsp;额</label>
				<input id="balance" type="text" name="balance"
				placeholder="请输入调整金额">
				<br>
				<span>注：调整金额格式为：10、10.5、10.25</span>
				<br>
				<input class="radio" type="radio" name="adjust_orientation" value="up" id="up" checked="checked"><label for="up">上调</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="radio" type="radio" name="adjust_orientation" value="down" id="down"><label for="down">下调</label>
				<br>
				<label>操作码</label>
				<input id="operation_code" type="password" name="operation_code"
				placeholder="请输入操作码">	
				<br>
				<input id="excute" type="submit" value="确定">
				<input id="reset" type="reset"  value="重置">
			</form>
  		</dd>
    </dl> 
	  <dl>
      <dt>操作结果</dt>
      <dd ><!-- 存放查询结果（表格）-->
			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			AdjustBalanceResult adjustBalanceResult = (AdjustBalanceResult) request.getAttribute("adjustBalanceResult");
			if (errorMessage != null) {
				if (errorMessage.equals("1")) {
					out.println("操作码错误！");
				} else {
					out.println("账户不存在！");
				}
			} else {
				//返回修改结果
				if (adjustBalanceResult != null) {
				%>
				<table id="customers">
				<tr><th>账户</th><th>品牌</th><th>调整前余额</th><th>调整后余额</th></tr>
				<tr>
					<td><%=adjustBalanceResult.getAccount()%></td>
					<td><%=adjustBalanceResult.getBrand()%></td>
					<td><%=adjustBalanceResult.getAccount_balance_before()/100.0%></td>
					<td><%=adjustBalanceResult.getAccount_balance_after()/100.0%></td>
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