package com.yum.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yum.card.model.AdjustBalanceResult;
import com.yum.card.service.AdjustBalanceService;
import com.yum.card.util.Validate;

/**
 * Servlet implementation class AdjustAccountBalanceServlet
 */
@WebServlet("/AdjustAccountBalanceServlet")
public class AdjustAccountBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.
			getLogger(AdjustAccountBalanceServlet.class.getName());   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdjustAccountBalanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account").trim();
		String brand = request.getParameter("brand");
		String balance = request.getParameter("balance").trim();
		String adjust_orientation = request.getParameter("adjust_orientation");
		String operation_code = request.getParameter("operation_code").trim();
		
		AdjustBalanceService abs = new AdjustBalanceService();
		AdjustBalanceResult adjustBalanceResult = null;
		String errorMessage = null;
		
		//if (!operation_code.equals("3666404")) {
		if (!new Validate().valiCode(operation_code)) {
			errorMessage = "1";
			System.out.println(errorMessage);
		} else {
			if (!(abs.isAccountExist(account, brand))) {
				errorMessage = "2";
				System.out.println(errorMessage);				
			} else {
				//��balanceתΪint����
				//12 => 1200 | 12.5 => 1250 | 12.55 => 1255
				String balance_new = null;//��ʽ����Ľ���ַ�
				int balance_i = 0;
				String[] arr = balance.split("\\.");
				if (arr.length == 1) {
					balance_new = arr[0] + "00";
				} else {
					if (arr[1].length() == 1) {
						balance_new = arr[0] + arr[1] +"0";
					} else {
						balance_new = arr[0] + arr[1];
					}
				}
				balance_i = Integer.parseInt(balance_new);
				
				adjustBalanceResult = abs.getAccountExcuteResult(account, brand, balance_i, adjust_orientation);
				//写日志
				String log = "operation_code: "+operation_code+" | "+
					         "account: "+account+" | "+
					         "brand: "+brand+" | "+
					         "adjust_item: account_balance"+" | "+
					         "adjust_amount: "+balance_i+" | "+
					         "before: "+adjustBalanceResult.getAccount_balance_before()+" | "+
					         "after: "+adjustBalanceResult.getAccount_balance_after();
				logger.info(log);		
				System.out.println(adjustBalanceResult.getAccount());
				System.out.println(adjustBalanceResult.getBrand());
				System.out.println(adjustBalanceResult.getAccount_balance_before());
				System.out.println(adjustBalanceResult.getAccount_balance_after());
			}
		}
		
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("adjustBalanceResult", adjustBalanceResult);
		
		request.getRequestDispatcher("/adjust_account_balance.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
