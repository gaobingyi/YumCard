package com.yum.card.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.AccountTransactionInfo;
import com.yum.card.model.CardTransactionInfo;
import com.yum.card.model.Pager;
import com.yum.card.service.TransactionInfoService;

/**
 * Servlet implementation class QueryTransactionInfoServclet
 */
@WebServlet("/QueryTransactionInfoServlet")
public class QueryTransactionInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTransactionInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单数据
		String queryParameter = request.getParameter("queryParameter");
		if (queryParameter != null) {
			queryParameter = queryParameter.trim();
		}
		TransactionInfoService tis = new TransactionInfoService();
		//2.获取表单或超链接参数类型
		String parameterType = request.getParameter("parameterType");
		if (parameterType.equals("mobile")) {//查询账户交易信息
			String[] lastTransaction = request.getParameterValues("lastTransaction");
			String last = null;
			if (lastTransaction != null) {
				last = lastTransaction[0];
			}
			System.out.println(last);			
			//获取分页信息
			Pager pager = tis.getPage(queryParameter, last);
			String pageNow = request.getParameter("pageNow");
			pager.setPageNow(Integer.parseInt(pageNow));
			//获取交易信息
			ArrayList<AccountTransactionInfo> accountTransactionInfoList = 
					tis.getAccountTransactionInfo(queryParameter, pager, last);			
			request.setAttribute("accountTransactionInfoList", accountTransactionInfoList);			
			request.setAttribute("pager", pager);		
			request.setAttribute("lastTransaction", lastTransaction);		
						
		} else {// "cardbin"
			ArrayList<CardTransactionInfo> cardTransactionInfoList = 
					tis.getCardTransactionInfo(queryParameter);		
			request.setAttribute("cardTransactionInfoList", cardTransactionInfoList);
			
			
		}
		request.setAttribute("queryParameter", queryParameter);
		request.setAttribute("parameterType", parameterType);
		
		request.getRequestDispatcher("query_transaction_info.jsp")
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
