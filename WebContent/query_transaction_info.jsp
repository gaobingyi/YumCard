<%@ page language="java" import="java.util.*,com.yum.card.model.*"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css"><!-- 样式表   -->
<link rel="shortcut icon" href="favicon.ico"><!-- 浏览器图标   -->
<script src="js/jquery-3.1.1.js"></script><!-- jQuery   -->
<script src="js/script.js" type="text/javascript"></script>
<title>百胜卡IT专用</title>
<style type="text/css">
#entry{
	width:250px;
}
#pager{
	margin-top:5px;
	margin-bottom:5px;
}
</style>
<script type="text/javascript"> 
$(function(){
    //监听radio选择改变事件
    $('input:radio[name="parameterType"]').change(function() {
    	var $selectedvalue = $("input:radio[name='parameterType']:checked").val();	    	
		if ($selectedvalue == "mobile") {
			$('#entry').attr("placeholder", "请输入手机号");		
			$('#3').attr("disabled", false);
			$("input:checkbox").attr("checked", true);
		} else {
			$('#entry').attr("placeholder", "请输入卡号");
			//$("input[name='lastTransaction']").prop('checked', $(this).prop('checked'));			
			$('#3').attr("disabled", true);
			$("input:checkbox").attr("checked", false); 
		}
	})
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
        <dd class="current"><a href="query_transaction_info.jsp">查询交易信息</a></dd>
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
        <dt>查询交易信息</dt>
        <dd>
		<!-- 存放查询输入框 -->	
		 <%
			String queryParameter = (String) request.getAttribute("queryParameter");
		 	String parameterType = (String) request.getAttribute("parameterType");
		 	Pager pager = (Pager) request.getAttribute("pager");	
		 	System.out.println(parameterType);
		 %> 
		 <form action="/YumCard/QueryTransactionInfoServlet" method="post">
		  <input id="entry" type="text" name="queryParameter" placeholder="请输入手机号" 
		  value='<%= queryParameter==null ? "" : queryParameter %>'>
		  <input type="hidden" name="pageNow" value="1">
		  <input id="query" type="submit" value="查询">
		  <br/>		  
		  <fieldset>
			  <legend>选择查询条件</legend>
			  <input class="radio" type="radio" name="parameterType" value="mobile" id="1" checked="checked"><label for="1">账户交易</label>
			  <input class="radio" type="radio" name="parameterType" value="cardbin" id="2"><label for="2">卡交易</label>
			  <input class="checkbox" type="checkbox" name="lastTransaction" value="last" id="3" checked="checked"><label for="3">查询最近一笔</label>
          </fieldset>		  		  
		  <br/>
		</form> 
		</dd>
      </dl> 
	  <dl>
        <dt>查询结果</dt>
        <dd ><!-- 存放查询结果（表格）-->
		<div style="width:1096px; height:327px; overflow:auto; border:0px solid #000000;">
		<%
		System.out.println("----------------------");
		if (parameterType != null && parameterType.equals("mobile")) {
			System.out.println("----------------------");
			boolean isShowPager = false;
			ArrayList<AccountTransactionInfo> accountTransactionInfoList = (ArrayList<AccountTransactionInfo>) 
					 request.getAttribute("accountTransactionInfoList");				   	
			if(accountTransactionInfoList != null) {
				boolean isStyle = true;//控制表格相邻行变色
				int lenth = accountTransactionInfoList.size();
				if(lenth > 0) {
					isShowPager = true;
					%>
					<table id="customers">
					<tr>
						<th>订单号</th><!-- <th>交易号</th> --><th>门店号</th><th>品牌</th><th>订单状态</th>
						<th>交易类型</th><th>交易时间</th><th>支付账户</th><th>消费金额</th><th>使用卡及其金额</th>
					</tr>
					<%
					
					
					for(int i=0; i<lenth; i++) {
						AccountTransactionInfo accountTransactionInfo = 
								(AccountTransactionInfo)accountTransactionInfoList.get(i);		
						String originalTransId = accountTransactionInfo.getOriginalTransId();
						
						if (originalTransId == null) {
							originalTransId = "消费";
						} else if (originalTransId.equals("")) {
							originalTransId = "消费";
						} else {
							originalTransId = "取消消费（或撤销）";
						}
						if(isStyle) {
							out.println("<tr>");
						} else {
							out.println("<tr class=\"alt\">");
						}
						isStyle = !isStyle;
						String transactionType = accountTransactionInfo.getOriginalTransId();
						%>
						<td><%=accountTransactionInfo.getOrderId()%></td>
						<%-- <td><%=accountTransactionInfo.getTransactionId()%></td> --%>
						<td><%=accountTransactionInfo.getStoreId()%></td>
						<td><%=accountTransactionInfo.getIssuerBrandId()%></td>
						<td><%=accountTransactionInfo.getOrderStatus()%></td>
						<td><%=originalTransId%></td>
						<td><%=accountTransactionInfo.getAcqDateTime().substring(0, 19)%></td>
						<td><%=accountTransactionInfo.getPaymentAccount()%></td>
						<td><%=accountTransactionInfo.getAmount()/100.0%></td>
						<td><%=accountTransactionInfo.getCards()%></td>
						</tr>
						<%
					}
				} else {
					out.println("没有相关记录！");
				}		
			}
			out.println("</table>");
		
			int rowCount = 0;
			int pageCount = 0;
			int pageNow = 0;
			if (pager != null) {
				rowCount = pager.getRowCount();
				pageCount = pager.getPageCount();
				pageNow = pager.getPageNow();
			}	
			//显示分页信息
			if(pager != null && isShowPager) {
				//显示首页
				out.println("<a href=/YumCard/QueryTransactionInfoServlet?pageNow=1&queryParameter="+queryParameter+"&parameterType="+parameterType+">首页</a>");
				//显示上一页
				//if (pageNow != 1) {
					out.println("<a href=/YumCard/QueryTransactionInfoServlet?pageNow="+((pageNow==1)?pageNow:(pageNow-1))+"&queryParameter="+queryParameter+"&parameterType="+parameterType+">上一页</a>");
				//}       	
				//显示下一页		
				//if (pageNow != pageCount) {
					out.println("<a href=/YumCard/QueryTransactionInfoServlet?pageNow="+((pageNow==pageCount)?pageNow:(pageNow+1))+"&queryParameter="+queryParameter+"&parameterType="+parameterType+">下一页</a>");
				//}
				//显示尾页			
				out.println("<a href=/YumCard/QueryTransactionInfoServlet?pageNow="+pageCount+"&queryParameter="+queryParameter+"&parameterType="+parameterType+">尾页</a>");			
				//显示第几页			
				%>&nbsp;第<%=pageNow%>页<%
	        	//显示共几页
	        	%>共<%=pageCount%>页<%
	        	//显示共几条记录        	
	        	%>总共<%=rowCount%>条<%	
			}
		}
		if (parameterType != null && parameterType.equals("cardbin")) {
			ArrayList<CardTransactionInfo> cardTransactionInfoList = 
					(ArrayList<CardTransactionInfo>) request.getAttribute("cardTransactionInfoList");		
			
			if(cardTransactionInfoList != null) {
				int lenth = cardTransactionInfoList.size();
				if(lenth > 0) {
				%>
				<table id="customers">
				<tr>
					<th>卡号</th><th>订单号</th><th>交易号</th><th>消费前余额</th>
					<th>消费后余额</th><th>消费金额</th><th>消费时间</th>
				</tr>
				<%
				boolean isStyle = true;
				for(int i=0; i<lenth; i++) {
					CardTransactionInfo cardTransactionInfo = 
							(CardTransactionInfo) cardTransactionInfoList.get(i);
				
					if(isStyle) {
						out.println("<tr>");
					} else {
						out.println("<tr class=\"alt\">");
					}
					isStyle = !isStyle;
					%>
					<td><%=cardTransactionInfo.getCardBin()%></td><td><%=cardTransactionInfo.getOrderId()%></td>
					<td><%=cardTransactionInfo.getTransactionId()%></td><td><%=cardTransactionInfo.getBeforeBalance()/100.0%></td>
					<td><%=cardTransactionInfo.getAfterBalance()/100.0%></td><td><%=cardTransactionInfo.getAmount()/100.0%></td>
					<td><%=cardTransactionInfo.getLastUpdateTime().substring(0, 19)%></td>				
					</tr>
					<%
				}
			} else {
				out.println("没有相关记录！");
			}
			out.println("</table>");	
		}
		}
		
		%>	 
		</div>
		</dd>
      </dl>
    </div>
  </div>
</div>
</body>
</html>