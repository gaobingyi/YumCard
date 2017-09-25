package com.yum.card.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.CMBCardInfo;
import com.yum.card.model.CMBOrderInfo;
import com.yum.card.model.CMBTransactionInfo;
import com.yum.card.service.CMBTransactionInfoService;

/**
 * Servlet implementation class QueryCMBTransactionInfoServlet
 */
@WebServlet("/QueryCMBTransactionInfoServlet")
public class QueryCMBTransactionInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCMBTransactionInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input").trim();
		String query_type = request.getParameter("query_type");
		String parameter_type = request.getParameter("parameter_type");
		
		CMBTransactionInfoService ctis = new CMBTransactionInfoService();
		
		if (query_type.equals("order")) {//查询订单信息
			CMBOrderInfo cmbOrderInfo = ctis.getOrderInfo(input);
			request.setAttribute("cmbOrderInfo", cmbOrderInfo);
			request.setAttribute("flag", "order");
		} else {//查询交易信息
			
			List<CMBTransactionInfo> cmbTransactionInfoList = null;
			CMBCardInfo cmbCardInfo = ctis.getCardInfo(input, parameter_type);
			String message = null;
			if (cmbCardInfo != null) {
				cmbTransactionInfoList = ctis.getTransactionInfoList(input);
			} else {
				message = "1";
			}
			request.setAttribute("flag", "transaction");		
			request.setAttribute("cmbCardInfo", cmbCardInfo);
			request.setAttribute("message", message);
			request.setAttribute("cmbTransactionInfoList", cmbTransactionInfoList);		
			
		}
		request.setAttribute("input", input);
		request.getRequestDispatcher("/query_cmb_transaction_info.jsp")
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
