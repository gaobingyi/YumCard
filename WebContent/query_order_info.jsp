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
#content textarea{
		height:60px; 
		width:300px; 
		margin-right:20px;		
	}
fieldset{
	padding-right:20px;
}
label{
	font-size:15px;
}
#pager{
	margin-top:5px;
	margin-bottom:5px;
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
		if ($selectedvalue == "orderseqid") {
			$('#entry').attr("placeholder", "请输入订单号，格式如下：\nP5989599773 \nP5402458489 \n……");
		} else if ($selectedvalue == "cardbin") {
			$('#entry').attr("placeholder", "请输入卡号，格式如下：\n2366233610000000826 \n2366232900200104366 \n……");
		} else {
			$('#entry').attr("placeholder", "请输入购卡人手机号，格式如下：\n18652757317 \n18652757318 \n……");
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
        <dd><a href="query_user_info.jsp">查询用户信息</a></dd>
        <dd><a href="query_card_info_users.jsp">查询卡信息</a></dd>
        <dd><a href="query_card_conversion_info.jsp">查询实转虚信息</a></dd> 
        <dd class="current"><a href="query_order_info.jsp">查询订单信息</a></dd>
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
        <dt>查询订单信息</dt>
        <dd>	
		<!-- 表单  -->
		<form action="/YumCard/QueryOrderInfoServlet" method="post">
		  <!-- 查询参数  -->
		  <textarea id="entry" name="query_parameter" placeholder="请输入订单号，格式如下：
P5989599773
P5402458489
……"></textarea>	
	      <br>	  
		  <!-- 参数类型  -->
          <input class="radio" type="radio" name="parameter_type" value="orderseqid" id="1" checked="checked"><label for="1">订单号</label>
		  <input class="radio" type="radio" name="parameter_type" value="cardbin" id="2"><label for="2">卡号</label>
	      <input class="radio" type="radio" name="parameter_type" value="mobile" id="3"><label for="3">购卡人手机号</label>
		  <br/>
		  <!-- 提交按钮  -->
		  <input id="query" type="submit" value="查询">
		  <input id="reset" type="reset"  value="重置">
		  <!-- 表单提交的分页信息（pageNow） -->
		  <input type="hidden" name="pageNow" value="1">
		</form>  
		</dd>
      </dl> 
	  <dl>
        <dt>查询结果</dt>
        <dd >
		<!-- 存放查询结果（表格）-->
		<%				
	    ArrayList<OrderInfo> orderInfoList = (ArrayList<OrderInfo>) request.getAttribute("orderInfoList");			
		if(orderInfoList != null) {			
			boolean isStyle = true;//控制表格相邻行变色
			int lenth = orderInfoList.size();
			if(lenth > 0) {
				%>
				<table id="customers">
				<tr>
					<th>订单号</th><th>品牌</th><th>金额</th><th>购买时间</th><th>订单状态</th>
					<th>卡号</th><th>卡状态</th><th>购卡人</th><th>收卡人</th><th>账户是否有卡</th>
				</tr>
				<%
				for(int i=0; i<lenth; i++) {
					OrderInfo orderInfo = (OrderInfo)orderInfoList.get(i);
				
					if(isStyle) {
						out.println("<tr>");
					} else {
						out.println("<tr class=\"alt\">");
					}
					isStyle = !isStyle;
				%>
				<td><%=orderInfo.getOrderid()%></td><td><%=orderInfo.getIssuerbrandid()%></td>
				<td><%=orderInfo.getAmount()/100.0%></td><td><%=orderInfo.getPurchasetime().substring(0, 19)%></td>
				<td><%=orderInfo.getOrderstatus()%></td><td><%=orderInfo.getCardbin()==null?"":orderInfo.getCardbin()%></td>
				<td><%=orderInfo.getCardstatus()==-1?"":orderInfo.getCardstatus()%></td><td><%=orderInfo.getPersonaccountinfo()==null?"":orderInfo.getPersonaccountinfo()%></td>
				<td><%=orderInfo.getReceiver()==null?"":orderInfo.getReceiver()%></td><td><%=orderInfo.getPaymentcard()%></td>
				</tr>
				<%
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
		<%
		String queryParameter = (String) request.getAttribute("queryParameter_new");
		String parameterType = (String) request.getAttribute("parameterType");
		Pager pager = (Pager) request.getAttribute("pager");
		int rowCount = 0;
		int pageCount = 0;
		int pageNow = 0;
		if (pager != null) {
			rowCount = pager.getRowCount();
			pageCount = pager.getPageCount();
			pageNow = pager.getPageNow();
		}
		
		if(pager != null) {
			%>
			<div id="pager">
			<%
			//显示首页
			out.println("<a href=/YumCard/QueryOrderInfoServlet?pageNow=1&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">首页</a>");
			//显示上一页
			//if (pageNow != 1) {
				out.println("<a href=/YumCard/QueryOrderInfoServlet?pageNow="+((pageNow==1)?pageNow:(pageNow-1))+"&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">上一页</a>");
			//}       	
			//显示下一页		
			//if (pageNow != pageCount) {
				out.println("<a href=/YumCard/QueryOrderInfoServlet?pageNow="+((pageNow==pageCount)?pageNow:(pageNow+1))+"&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">下一页</a>");
			//}
			//显示尾页			
			out.println("<a href=/YumCard/QueryOrderInfoServlet?pageNow="+pageCount+"&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">尾页</a>");
			
			//显示第几页			
			%>			
        	&nbsp;第<%=pageNow%>页
        	<%
        	//显示共几页
        	%>
        	共<%=pageCount%>页
        	<%
        	//显示共几条记录        	
        	%>
        	总共<%=rowCount%>条
        	<%	
        	%>
			</div>
			<%
		}
		%>
		</dd>
      </dl>
    </div>
  </div>
  <!-- <div id="footer">百胜&copy;版权所有</div>尾  -->
</div>
</body>
</html>
