$(function(){
	// 判断输入框内容是否为空（全空格视为空）
	$("#query").click(function(){
		var $val = $("#entry").val().trim();
		//alert($val);
	    if ($val == "") {
	    	alert("请输入查询参数！");
	    	return false;
	    }
	})
})