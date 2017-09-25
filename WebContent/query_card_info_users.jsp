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
/* 	#entry{
		width:250px;
	} */
	#content textarea{
		height:60px; 
		width:300px; 
		margin-right:20px;		
	}
	fieldset{
		padding-bottom:0;
		text-align:left;
	}
	label{
	font-size:15px;
	}
	#pager{
	margin-top:5px;
	margin-bottom:5px;
}
</style>
<script type="text/javascript">
$(function(){
	//监听radio选择改变事件
	$('input:radio[name="parameter_type"]').change(function() {
    	var $selectedvalue = $("input:radio[name='parameter_type']:checked").val();	    	
		if ($selectedvalue == "cardbin") {
			//alert($selectedvalue);
			$('#entry').attr("placeholder", "请输入卡号，格式如下：\n2366232900200103251 \n2366232900200103269 \n……");
		} else {
			//alert($selectedvalue);
			$('#entry').attr("placeholder", "请输入手机号（用户拥有卡）");
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
        <dd class="current"><a href="query_card_info_users.jsp">查询卡信息</a></dd>
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
        <dt>查询卡信息</dt>
        <dd>
		<!-- 存放查询输入框 -->	
		
		<form action="/YumCard/QueryCardInfoServlet" method="post">
		   <textarea id="entry" name="query_parameter" placeholder="请输入卡号，格式如下：
2366232900200103251
2366232900200103269
……"></textarea>
		  <br><input class="radio" type="radio" name="parameter_type" value="cardbin" id="1" checked="checked"><label for="1">卡号</label>
		  &nbsp;&nbsp;&nbsp;&nbsp;
		  <input class="radio" type="radio" name="parameter_type" value="mobile" id="2"><label for="2">手机号</label>
		  <!-- <fieldset>
			  <legend>选择查询条件</legend>
			  <input class="radio" type="radio" name="parameter_type" value="cardbin" id="1" checked="checked"><label for="1">卡号</label>
			  <input class="radio" type="radio" name="parameter_type" value="mobile" id="2"><label for="2">手机号</label>
          </fieldset> --><br>
		  <input id="query" type="submit" value="查询">
		  <input id="reset" type="reset"  value="重置">
		  <input type="hidden" name="pageNow" value="1">
		  <br/> 
		</form> 
		</dd>
      </dl> 
	  <dl>
        <dt>查询结果</dt>
        <dd >
		<!-- 存放查询结果（表格）-->
		<%				
	    ArrayList<CardInfo> cardInfoList = (ArrayList<CardInfo>) request.getAttribute("cardInfoList");	
						  
		if(cardInfoList != null) {			
			boolean isStyle = true;//控制表格相邻行变色
			int lenth = cardInfoList.size();
			if(lenth > 0) {
				%>
				<table id="customers">
				<tr><th>卡号</th><th>购买时间</th><th>金额</th><th>余额</th>
				<th>当前状态</th><th>是否激活</th><th>品牌</th><th>有效期</th>
				<th>是否作废</th><th>是否绑定</th><th>是否冻结</th></tr>
				<%
				for(int i=0; i<lenth; i++) {
					CardInfo cardInfo = (CardInfo) cardInfoList.get(i);
					if(isStyle) {
						%>
						<tr>
						  <td><%=cardInfo.getCardBin()%></td><td><%=cardInfo.getPurchaseTime()==null?"":cardInfo.getPurchaseTime().substring(0, 19)%></td><td><%=cardInfo.getCardAmount()/100.0%></td>
						  <td><%=cardInfo.getBalance()/100.0%></td><td><%=cardInfo.getCurrentStatus()%></td><td><%=cardInfo.getIsactivate()%></td>
						  <td><%=cardInfo.getIssuerBrandId()%></td><td><%=cardInfo.getValidThru()==null?"":cardInfo.getValidThru().substring(0, 19)%></td><td><%=cardInfo.getIsRevoked()%></td>
						  <td><%=cardInfo.getIsBound()%></td><td><%=cardInfo.getIsFrozen()%></td>
						</tr>
						<%
					} else {
						%>
						<tr class="alt">
						  <td><%=cardInfo.getCardBin()%></td><td><%=cardInfo.getPurchaseTime()==null?"":cardInfo.getPurchaseTime().substring(0, 19)%></td><td><%=cardInfo.getCardAmount()/100.0%></td>
						  <td><%=cardInfo.getBalance()/100.0%></td><td><%=cardInfo.getCurrentStatus()%></td><td><%=cardInfo.getIsactivate()%></td>
						  <td><%=cardInfo.getIssuerBrandId()%></td><td><%=cardInfo.getValidThru()==null?"":cardInfo.getValidThru().substring(0, 19)%></td><td><%=cardInfo.getIsRevoked()%></td>
						  <td><%=cardInfo.getIsBound()%></td><td><%=cardInfo.getIsFrozen()%></td>
						</tr>
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
			out.println("<a href=/YumCard/QueryCardInfoServlet?pageNow=1&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">首页</a>");
			//显示上一页
			//if (pageNow != 1) {
				out.println("<a href=/YumCard/QueryCardInfoServlet?pageNow="+((pageNow==1)?pageNow:(pageNow-1))+"&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">上一页</a>");
			//}       	
			//显示下一页		
			//if (pageNow != pageCount) {
				out.println("<a href=/YumCard/QueryCardInfoServlet?pageNow="+((pageNow==pageCount)?pageNow:(pageNow+1))+"&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">下一页</a>");
			//}
			//显示尾页			
			out.println("<a href=/YumCard/QueryCardInfoServlet?pageNow="+pageCount+"&queryParameter_new="+queryParameter+"&parameter_type="+parameterType+">尾页</a>");
			
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