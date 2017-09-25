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
	width:280px;
}
</style>
<style type="text/css">
	#content, #content a{
		font-size:20px;		
	}
	#content{
		padding-top:250px;
		text-align:center;
	}
	#content a{
		font-style:italic;
	}	
	#content a:link {color: #FF0000}	/* 未访问的链接 */
	#content a:visited {color: #00FF00}	/* 已访问的链接 */
	#content a:hover {color: #FF00FF}	/* 鼠标移动到链接上 */
	#content a:active {color: #0000FF}	/* 选定的链接 */
</style>
<script> 

</script>

</head>

<body>
<div id="wrap">
  <div class="logo">
		百胜卡IT专用
		<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<a href="admin.jsp" target="_self">管理员</a>
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
	     如有问题，请联系：<a href="Mailto:Bingyi.Gao@YumChina.com">点我发邮件</a>
	  <br><br><div>建议使用谷歌浏览器Chrome浏览</div>
    </div>
  </div>
  <!-- <div id="footer">百胜&copy;版权所有</div>尾  -->
</div>
</body>
</html>