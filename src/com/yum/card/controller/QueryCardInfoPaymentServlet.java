package com.yum.card.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.CardInfo;
import com.yum.card.model.Pager;
import com.yum.card.service.CardInfoPaymentService;

/**
 * Servlet implementation class QueryCardInfoPaymentServlet
 */
@WebServlet("/QueryCardInfoPaymentServlet")
public class QueryCardInfoPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCardInfoPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryParameter_new = request.getParameter("queryParameter_new");
		System.out.println(queryParameter_new);
		
		//获取查询参数
		String queryParameter = request.getParameter("query_parameter");
		if (queryParameter != null) {
			queryParameter = queryParameter.trim();
		}
		System.out.println(queryParameter);
		if(queryParameter != null) {
			String queryParameters[] = queryParameter.split("\r\n");
			StringBuilder queryParameter_b = new StringBuilder();		
			for(int i=0; i<queryParameters.length; i++) {
				queryParameters[i] = queryParameters[i].trim();			
				queryParameter_b.append("\'");
				queryParameter_b.append(queryParameters[i]);
				queryParameter_b.append("\',");
				//System.out.println(phones[i]);
			}
			queryParameter_new = queryParameter_b.toString().substring(0, queryParameter_b.length()-1);
			
			System.out.println(queryParameter_new);
		}
		//获取参数类型
		String parameterType = request.getParameter("parameter_type");
		System.out.println(parameterType);
		//获取"余额不为0"
		String[] hasAmount_Arr = request.getParameterValues("hasAmount");
		String hasAmount = null;
		if (hasAmount_Arr != null) {
			hasAmount = hasAmount_Arr[0];
		}
		System.out.println(hasAmount);
		
		CardInfoPaymentService cips = new CardInfoPaymentService();
		
		Pager pager = cips.getPager(queryParameter_new, parameterType, hasAmount);
		pager.setPageNow(Integer.parseInt(request.getParameter("pageNow")));
		System.out.println("pageSize:" + pager.getPageSize());
		System.out.println("pageNow:" + pager.getPageNow());
		System.out.println("rowCount:" + pager.getRowCount());
		System.out.println("pageCount:" + pager.getPageCount());
		
		ArrayList<CardInfo> cardInfoList = cips.getCardInfo(queryParameter_new, parameterType, pager, hasAmount);
		
		request.setAttribute("cardInfoList", cardInfoList);
		request.setAttribute("pager", pager);
		request.setAttribute("queryParameter_new", queryParameter_new);
		request.setAttribute("parameterType", parameterType);

		request.getRequestDispatcher("query_card_info_payment.jsp")
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
