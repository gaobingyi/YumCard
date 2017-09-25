package com.yum.card.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.CardConversionInfo;
import com.yum.card.service.CardConversionInfoService;

/**
 * Servlet implementation class QueryCardConversionInfoServlet
 */
@WebServlet("/QueryCardConversionInfoServlet")
public class QueryCardConversionInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCardConversionInfoServlet() {
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
		
		CardConversionInfoService ccis = new CardConversionInfoService();
		CardConversionInfo cardConversionInfo = ccis.getCardConversionInfo(
				queryParameter, parameterType);
		//System.out.println(cardConversionInfo.getPcardbin()+"------------");
		request.setAttribute("cardConversionInfo", cardConversionInfo);
		request.setAttribute("queryParameter", queryParameter);

		request.getRequestDispatcher("query_card_conversion_info.jsp")
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
