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
#entry{
	width:280px;
}
</style>

<script>
/* window.onload = function() {	
	var query = document.getElementById("query");
	query.setAttribute("placeholder", "hehe");
	document.getElementById("hehe").onclick = function() {
		var query = document.getElementById("query");
		alert(query);
		query.setAttribute("placeholder", "hehe");
		alert(query.getAttribute("placeholder"));
	}  
}  */ 

$(function(){   
    //监听radio选择改变事件
    $('input:radio[name="parameter_type"]').change(function() {
    	var $selectedvalue = $("input:radio[name='parameter_type']:checked").val();	    	
		if ($selectedvalue == "mobile") {
			$('#entry').attr("placeholder", "请输入手机号");
			//alert($selectedvalue);
			//$('#brand').attr("disabled", false);
		} else if ($selectedvalue == "usercode") {
			$('#entry').attr("placeholder", "请输入用户码");
			//alert($selectedvalue);
			//$('#brand').attr("disabled", false);
		} else {
			$('#entry').attr("placeholder", "请输入卡号(查询卡所属用户)");
			//alert($selectedvalue);
			//$("input[name='brand']").attr("checked",true);
			$("input[name='brand']").prop('checked', $(this).prop('checked'));
			//$('#brand').attr("disabled", true);
		}
	})
})
</script>
<script>

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
        <dd class="current"><a href="query_user_info.jsp">查询用户信息</a></dd>
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
        <dt>查询用户信息</dt>
        <dd>
		<!-- 存放查询输入框 -->	
		<%
		String queryParameter = (String) request.getAttribute("queryParameter");
		%>
		<form action="/YumCard/QueryUserInfoServlet" method="post">
		  <input id="entry" type="text" name="query_parameter" placeholder="请输入手机号" 
		  value='<%= queryParameter==null ? "" : queryParameter %>'>
		  <input id="query" type="submit" value="查询">
		  <!-- <input type="button" id="hehe" > 
		  <input type="text" id="inp" placeholder="请输入用户名"> -->
		  <br/>
		  
		  <fieldset>
			  <legend>选择查询条件</legend>
			  <input class="radio" type="radio" name="parameter_type" value="mobile" id="1" checked="checked"><label for="1">手机号</label>
			  <input class="radio" type="radio" name="parameter_type" value="usercode" id="2"><label for="2">用户码</label>
		      <input class="radio" type="radio" name="parameter_type" value="cardbin" id="3"><label for="3">卡号</label>
          </fieldset>
		  
		  <fieldset id="brand">
			  <legend>选择品牌</legend>
			  <input class="checkbox" type="checkbox" name="brand" value="Yum" id="4" checked="checked"><label for="4">Yum</label>
	          <input class="checkbox" type="checkbox" name="brand" value="KFC" id="5" checked="checked"><label for="5">KFC</label>
			  <input class="checkbox" type="checkbox" name="brand" value="PHDI" id="6" checked="checked"><label for="6">PHDI</label>
		  </fieldset>
		  <br/>
		  
		  <!-- <input id="reset" type="reset"  value="重置"> -->
		</form>  
		</dd>
      </dl> 
	  <dl>
        <dt>查询结果</dt>
        <dd >
		<!-- 存放查询结果（表格）-->
		<%				
	    ArrayList<UserInfo> userInfoList = (ArrayList<UserInfo>) request.getAttribute("userInfoList");			
		if(userInfoList != null) {			
			boolean isStyle = true;//控制表格相邻行变色
			int lenth = userInfoList.size();
			if(lenth > 0) {
				%>
				<table id="customers">
				<tr><th>用户码</th><th>品牌</th><th>手机号</th></tr>
				<%
				for(int i=0; i<lenth; i++) {
					UserInfo userInfo = (UserInfo)userInfoList.get(i);
					if(isStyle) {
						%>
						<tr><td><%=userInfo.getUserCode()%></td><td><%=userInfo.getIssuerBrandId()%></td><td><%=userInfo.getMobile()%></td></tr>
						<%
					} else {
						%>
						<tr class="alt"><td><%=userInfo.getUserCode()%></td><td><%=userInfo.getIssuerBrandId()%></td><td><%=userInfo.getMobile()%></td></tr>
						<%
					}
						
					isStyle = !isStyle;
				}
			} else {
				out.println("没有相关记录！");
			}		
		}
		%>	 
		</table>
		</dd>
		<dd>
		<!-- 分页  -->
		
		</dd>
      </dl>
    </div>
  </div>
  <!-- <div id="footer">百胜&copy;版权所有</div>尾  -->
</div>
</body>
</html>
