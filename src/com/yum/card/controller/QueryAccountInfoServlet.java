package com.yum.card.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.AccountInfo;
import com.yum.card.service.AccountInfoService;

/**
 * Servlet implementation class QueryAccountInfoServlet
 */
@WebServlet("/QueryAccountInfoServlet")
public class QueryAccountInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAccountInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取查询参数
		String queryParameter = request.getParameter("query_parameter").trim();
		System.out.println(queryParameter);
		//获取参数类型
		String parameterType = request.getParameter("parameter_type");
		System.out.println(parameterType);
		//获取品牌
		String brand[] = request.getParameterValues("brand");
		//处理brand
		String brand_new = null;
		if (brand != null) {
        	StringBuilder brand_b = new StringBuilder();
    		for(int i=0; i<brand.length; i++) {
    			if(brand[i].equals("Yum")) {
    				brand_b.append("\'001\',");
    			} else if (brand[i].equals("KFC")) {
    				brand_b.append("\'002\',");
    			} else {
    				brand_b.append("\'003\',");
    			}
    		}
    		brand_new = brand_b.toString().substring(0, brand_b.length()-1);
    		System.out.println(brand_new);	
	    }
		
		AccountInfoService ais = new AccountInfoService();
		ArrayList<AccountInfo> accountInfoList = ais.getAccountInfo(
				queryParameter, parameterType, brand_new);
		
		// 在jsp中用request.getAttribute("userInfoList")获取数据
		request.setAttribute("accountInfoList", accountInfoList);
		request.setAttribute("queryParameter", queryParameter);

		request.getRequestDispatcher("query_account_info.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
