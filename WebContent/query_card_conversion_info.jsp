<%@ page language="java" import="java.util.*,com.yum.card.model.*"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="shortcut icon" href="favicon.ico">
<script src="js/jquery-3.1.1.js"></script>
<script src="js/script.js" type="text/javascript"></script>
<title>百胜卡IT专用</title>
<style type="text/css">
	#entry{
	width:250px;
}

</style>
<script type="text/javascript">
$(function(){
	//监听radio选择改变事件
	$('input:radio[name="parameter_type"]').change(function() {
    	var $selectedvalue = $("input:radio[name='parameter_type']:checked").val();	    	
		if ($selectedvalue == "pcardbin") {
			$('#entry').attr("placeholder", "请输入实体卡号");
		} else if ($selectedvalue == "ecardbin") {
			$('#entry').attr("placeholder", "请输入虚拟卡号");
		} else {
			$('#entry').attr("placeholder", "请输入手机号(转入账户)");
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
        <dd class="current"><a href="query_card_conversion_info.jsp">查询实转虚信息</a></dd> 
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
        <dt>查询实转虚信息</dt>
        <dd>
		<!-- 存放查询输入框 -->	
		<%
		String queryParameter = (String) request.getAttribute("queryParameter");
		%>
		<form action="/YumCard/QueryCardConversionInfoServlet" method="post">
		  <input id="entry" type="text" name="query_parameter" placeholder="请输入实体卡号" 
		  value='<%= queryParameter==null ? "" : queryParameter %>'>
		  <input id="query" type="submit" value="查询">
		  <br/>
		  <fieldset>
			  <legend>选择查询条件</legend>
			  <input class="radio" type="radio" name="parameter_type" value="pcardbin" id="1" checked="checked"><label for="1">实体卡号</label>
			  <input class="radio" type="radio" name="parameter_type" value="ecardbin" id="2"><label for="2">虚拟卡号</label>
		      <input class="radio" type="radio" name="parameter_type" value="mobile" id="3"><label for="3">手机号</label>
          </fieldset>
		  <br/> 
		</form> 
		</dd>
      </dl> 
	  <dl>
        <dt>查询结果</dt>
        <dd >
		<!-- 存放查询结果（表格）-->
		<%				
		CardConversionInfo cardConversionInfo = (CardConversionInfo) request.getAttribute("cardConversionInfo");
		if (cardConversionInfo != null) {
			String brand = cardConversionInfo.getIssuerBrandId();
			System.out.println(brand);
			if (brand.equals("001")) {
				brand = "YUM";
			} else if (brand.equals("002")) {
				brand = "KFC";
			} else {
				brand = "PHDI";
			}
			%>
			<table id="customers">
			<tr><th>用户码</th><th>手机号</th><th>品牌</th><th>实体卡号</th><th>虚拟卡号</th><th>金额</th><th>转换时间</th></tr>
			<tr>
			<td><%=cardConversionInfo.getUserCode() %></td>
			<td><%=cardConversionInfo.getMobile() %></td>
			<td><%=brand %></td>
			<td><%=cardConversionInfo.getPcardbin() %></td>
			<td><%=cardConversionInfo.getEcardbin() %></td>
			<td><%=cardConversionInfo.getBalance()/100.0%></td>
			<td><%=cardConversionInfo.getTranstime().substring(0, 19) %></td>
			</tr>
			</table>
			<%
		} else {
			if (queryParameter != null) {
				out.println("虚拟卡系统没有实转虚记录！");
			}		
		}
		%>	
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