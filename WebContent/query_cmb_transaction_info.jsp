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
#entry{
	width:275px;
}
span{
	color:red;
}
</style>
<script type="text/javascript"> 
$(function(){
   //监听"查询类别"radio选择改变事件
   $('input:radio[name="query_type"]').change(function() {
   	var $selectedvalue = $("input:radio[name='query_type']:checked").val();	    	
		if ($selectedvalue == "order") {//选中订单信息
			//1.改变输入框提示信息
			$('#entry').attr("placeholder", "订单号为CMB开头的21位数字");
			//2.改变提示文字信息
			$('#tip').html("订单号为CMB开头的21位数字"); 
			//3.禁用"查询条件"及保持不选中状态
			$('input:radio[name="parameter_type"]').attr("disabled", true);	
			$("input[name=parameter_type]:eq(0)").prop('checked',false);
			$("input[name=parameter_type]:eq(1)").prop('checked',false);
		} else {//选中交易信息(默认选中"卡号")	
			//1.改变输入框提示信息
			$('#entry').attr("placeholder", "卡号为236开头的19位数字");
			//2.改变提示文字信息
			$('#tip').html("卡号为236开头的19位数字"); 
			//3.使能"查询条件"及选中"支付码"	
			$('input:radio[name="parameter_type"]').attr("disabled", false);
			$("input[name=parameter_type]:eq(0)").prop('checked',true);
			$("input[name=parameter_type]:eq(1)").prop('checked',false);
		}		
	})
	//监听"查询类别"radio选择改变事件
   $('input:radio[name="parameter_type"]').change(function() {
   	var $selectedvalue = $("input:radio[name='parameter_type']:checked").val();	    	
		if ($selectedvalue == "payment_code") {//选中"支付码"
			//1.改变输入框提示信息
			$('#entry').attr("placeholder", "支付码为210开头的19位数字");
			//2.改变提示文字信息
			$('#tip').html("支付码为210开头的19位数字"); 
		} else {//选中"卡号"
			//1.改变输入框提示信息
			$('#entry').attr("placeholder", "卡号为236开头的19位数字");
			//2.改变提示文字信息
			$('#tip').html("卡号为236开头的19位数字"); 
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
		<dd class="current"><a href="query_cmb_transaction_info.jsp">查询招行卡券交易信息</a></dd>
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
        <dt>查询招行卡券交易信息</dt>
        <dd>
		<!-- 存放查询输入框 -->	
		<%
		String input = (String) request.getAttribute("input");
		%>
		<form action="/YumCard/QueryCMBTransactionInfoServlet" method="post">
			<input id="entry" type="text" name="input" value='<%=input==null?"":input%>' placeholder="卡号为236开头的19位数字">	
			<input id="query" type="submit" value="查询">	
			<br>
			<span id="tip">卡号为236开头的19位数字</span>
			<br>
			<fieldset>
			  <legend>选择查询类别</legend>
			  <input class="radio" type="radio" name="query_type" value="order" id="1"><label for="1">订单信息</label>
			  <input class="radio" type="radio" name="query_type" value="transaction" id="2" checked="checked"><label for="2">交易信息</label>
			</fieldset>
			<fieldset id="parameter_type">
			  <legend>选择查询条件</legend>
			  <input class="radio" type="radio" name="parameter_type" value="cardbin" id="4" checked="checked"><label for="4">卡号</label>
			  <input class="radio" type="radio" name="parameter_type" value="payment_code" id="3"><label for="3">支付码</label>			  
			</fieldset>	
		</form> 
		</dd>
      </dl> 
	  <dl>
        <dt>查询结果</dt>
        <dd >
		<!-- 存放查询结果（表格）-->
		<%-- <%
			CMBCardInfo cmbCardInfo = (CMBCardInfo) request.getAttribute("cmbCardInfo");
			String message = (String) request.getAttribute("message");
			List<CMBTransactionInfo> cmbTransactionInfoList = 
					(ArrayList<CMBTransactionInfo>) request.getAttribute("cmbTransactionInfoList");
			if (cmbCardInfo == null) {
				if (message != null) {
					out.write("没有卡券信息！");
				}
			} else {
				String brand = cmbCardInfo.getBrand();
				System.out.println(brand);
				if (brand.equals("001")) {
					brand = "YUM";
				} else if (brand.equals("002")) {
					brand = "KFC";
				} else {
					brand = "PHDI";
				}
				%>
				<strong>招行卡券信息 </strong>
				<table id="customers">
					<tr><th>支付码</th><th>卡号</th><th>卡类别</th><th>品牌</th><th>面值</th><th>余额</th></tr>
					<tr>
					<td><%=cmbCardInfo.getPaymentCode() %></td>
					<td><%=cmbCardInfo.getCardbin() %></td>
					<td><%=cmbCardInfo.getCarddesc() %></td>
					<td><%=brand %></td>
					<td><%=cmbCardInfo.getCardamount()/100.0 %></td>
					<td><%=cmbCardInfo.getBalance()/100.0 %></td>		
					</tr>
				</table>
				<%
				if (cmbTransactionInfoList.isEmpty()) {
					out.write("没有交易信息！");
				} else {
					//显示卡交易信息
					boolean isStyle = true;//控制表格相邻行变色
					int lenth = cmbTransactionInfoList.size();
					%>
					<strong>卡交易信息 </strong>
					<table id="customers">
					<tr>
						<th>卡号</th><th>订单号</th><th>餐厅号</th><th>交易时间</th>
						<th>交易类型</th><th>交易前余额</th><th>交易后余额</th><th>交易金额</th>
					</tr>
					<%
					for(int i=0; i<lenth; i++) {
						CMBTransactionInfo cmbTransactionInfo = (CMBTransactionInfo) cmbTransactionInfoList.get(i);			
						if(isStyle) {
							out.write("<tr>");
						} else {
							out.write("<tr class=\"alt\">");
						}
						isStyle = !isStyle;
					%>
					<td><%= cmbTransactionInfo.getCardbin()%></td>
					<td><%= cmbTransactionInfo.getOrderid()%></td>
					<td><%= cmbTransactionInfo.getStoreid()%></td>
					<td><%= cmbTransactionInfo.getTranstime().substring(0, 19)%></td>
					<td><%= cmbTransactionInfo.getTransactionType().equals("0")?"消费":"退款"%></td>
					<td><%= cmbTransactionInfo.getBeforebalance()/100.0%></td>
					<td><%= cmbTransactionInfo.getAfterbalance()/100.0%></td>
					<td><%= cmbTransactionInfo.getTransamount()/100.0%></td>
					</tr>
					<%
					}
				}
			}	
		%> --%>
		<%
		CMBOrderInfo cmbOrderInfo = (CMBOrderInfo) request.getAttribute("cmbOrderInfo");
		CMBCardInfo cmbCardInfo = (CMBCardInfo) request.getAttribute("cmbCardInfo");
		String message = (String) request.getAttribute("message");
		String flag = (String) request.getAttribute("flag");
		List<CMBTransactionInfo> cmbTransactionInfoList = 
				(ArrayList<CMBTransactionInfo>) request.getAttribute("cmbTransactionInfoList");
		if (flag != null) {
			if (flag.equals("order")) {//订单
				if (cmbOrderInfo == null) {
						out.write("没有订单信息！");
				} else {
					/* 显示订单信息  */
					String brand = cmbOrderInfo.getBrand();
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
						<tr>
							<th>订单号</th><th>订单状态</th><th>品牌</th>
							<th>金额</th><th>卡片数量</th><th>购买时间</th><th>卡号</th>
						</tr>
						<tr>
							<td><%=cmbOrderInfo.getOrderid() %></td>
							<td><%=cmbOrderInfo.getStatus() %></td>
							<td><%=brand %></td>
							<td><%=cmbOrderInfo.getAmount()/100.0 %></td>
							<td><%=cmbOrderInfo.getCount() %></td>
							<td><%=cmbOrderInfo.getTime().substring(0, 19) %></td>		
							<td><%=cmbOrderInfo.getCardbin() %></td>		
						</tr>
					</table>
				<%
				}
			} 
			if (flag.equals("transaction")) {//交易
				if (cmbCardInfo == null) {
					if (message != null) {
						out.write("没有卡券信息！");
					}
				} else {
					String brand = cmbCardInfo.getBrand();
					System.out.println(brand);
					if (brand.equals("001")) {
						brand = "YUM";
					} else if (brand.equals("002")) {
						brand = "KFC";
					} else {
						brand = "PHDI";
					}
					%>
				  <strong>招行卡券信息 </strong>
					<table id="customers">
						<tr><th>卡号</th><th>支付码</th><th>卡类别</th><th>品牌</th><th>面值</th><th>余额</th></tr>
						<tr>
						<td><%=cmbCardInfo.getCardbin() %></td>
						<td><%=cmbCardInfo.getPaymentCode() %></td>					
						<td><%=cmbCardInfo.getCarddesc() %></td>
						<td><%=brand %></td>
						<td><%=cmbCardInfo.getCardamount()/100.0 %></td>
						<td><%=cmbCardInfo.getBalance()/100.0 %></td>		
						</tr>
					</table>
					<%
					if (cmbTransactionInfoList.isEmpty()) {
						out.write("没有交易信息！");
					} else {
						//显示卡交易信息
						boolean isStyle = true;//控制表格相邻行变色
						int lenth = cmbTransactionInfoList.size();
						%>
						<strong>卡交易信息 </strong>
						<table id="customers">
						<tr>
							<th>卡号</th><th>订单号</th><th>餐厅号</th><th>交易时间</th>
							<th>交易类型</th><th>交易前余额</th><th>交易后余额</th><th>交易金额</th>
						</tr>
						<%
						for(int i=0; i<lenth; i++) {
							CMBTransactionInfo cmbTransactionInfo = (CMBTransactionInfo) cmbTransactionInfoList.get(i);			
							if(isStyle) {
								out.write("<tr>");
							} else {
								out.write("<tr class=\"alt\">");
							}
							isStyle = !isStyle;
						%>
						<td><%= cmbTransactionInfo.getCardbin()%></td>
						<td><%= cmbTransactionInfo.getOrderid()%></td>
						<td><%= cmbTransactionInfo.getStoreid()%></td>
						<td><%= cmbTransactionInfo.getTranstime().substring(0, 19)%></td>
						<td><%= cmbTransactionInfo.getTransactionType().equals("0")?"消费":"退款"%></td>
						<td><%= cmbTransactionInfo.getBeforebalance()/100.0%></td>
						<td><%= cmbTransactionInfo.getAfterbalance()/100.0%></td>
						<td><%= cmbTransactionInfo.getTransamount()/100.0%></td>
						</tr>
						<%
						}
					}
				}
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