package com.yum.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.AccountInfo;
import com.yum.card.service.ChangeAccountService;
import com.yum.card.util.Validate;

/**
 * Servlet implementation class ChangeAccountServlet
 */
@WebServlet("/ChangeAccountServlet")
public class ChangeAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account_old = request.getParameter("account_old").trim();
		String account_new = request.getParameter("account_new").trim();
		String brand = request.getParameter("brand");
		String password = request.getParameter("password").trim();
		
		ChangeAccountService cas = new ChangeAccountService();
		AccountInfo accountInfo = null;
		String errorMessage = null;
		//if (!password.equals("3666404")) {
		if (!new Validate().valiCode(password)) {
			errorMessage = "1";
			System.out.println(errorMessage);
		} else {
			if (!(cas.isOldAccountExist(account_old, brand))) {
				errorMessage = "2";
				System.out.println(errorMessage);				
			} else {
				//�ж����˻�����Ƿ����0
				if (cas.isNewAccountHasBalance(account_new, brand)) {
					//������˻�������0
					errorMessage = "3";
					System.out.println(errorMessage);		
				} else{
					//������˻����Ϊ0�����˻�������
					//ִ�и�Ĳ���
					accountInfo = cas.getExcuteResult(account_old, account_new, brand);
					System.out.println(accountInfo.getMobile());
					System.out.println(accountInfo.getAccountBalance());
					System.out.println(accountInfo.getIssuerBrandId());
					
				}				
			}
		}
		
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("accountInfo", accountInfo);
		
		request.getRequestDispatcher("/change_account.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
