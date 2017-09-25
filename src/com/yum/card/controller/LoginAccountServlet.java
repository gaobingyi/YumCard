package com.yum.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yum.card.model.LoginAccount;
import com.yum.card.service.LoginAccountService;
import com.yum.card.util.Validate;

/**
 * Servlet implementation class LoginAccountServlet
 */
@WebServlet("/LoginAccountServlet")
public class LoginAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.
			getLogger(LoginAccountServlet.class.getName());      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account = request.getParameter("account").trim();
		String brand = request.getParameter("brand");
		String operation_code = request.getParameter("operation_code").trim();
		String original_login_password = request.getParameter("original_login_password");
		String original_login_type = request.getParameter("original_login_type");
		
		LoginAccountService las = new LoginAccountService();
		LoginAccount loginAccount = null;
		String errorMessage = null;
		//if (!operation_code.equals("3666404")) {
		if (!new Validate().valiCode(operation_code)) {
			errorMessage = "1";
			System.out.println(errorMessage);
		} else {
			if (!(las.isAccountExist(account, brand))) {
				errorMessage = "2";
				System.out.println(errorMessage);	
			} else {
				if (original_login_type.equals("A")) {
					//�滻����
					loginAccount = las.modify(account, brand, 
							"$SHA$e7lLQBWzeo9$BCraMMYAjvKkVkZsW3leKEUXopc", original_login_type);
					//写日志
					String log = "operation_code: "+operation_code+" | "+
						         "account: "+account+" | "+
						         "brand: "+brand+" | "+
						         "action_item: replace_password"+" | "+
						         "before_password: "+loginAccount.getOriginal_login_password()+" | "+
						         "before_type: "+loginAccount.getOriginal_login_type()+" | "+
						         "after_password: "+loginAccount.getCurrent_login_password()+" | "+
						         "after_type: "+loginAccount.getCurrent_login_type();
					logger.info(log);
					
					request.setAttribute("operation_code", operation_code);
					request.setAttribute("original_login_password", loginAccount.getOriginal_login_password());
					System.out.println(loginAccount.getOriginal_login_password());					
					request.setAttribute("original_login_type", loginAccount.getOriginal_login_type());
					System.out.println(loginAccount.getOriginal_login_type());	
				} else {// login_type == "D" || "F"
					//�ָ�����
					System.out.println(original_login_password + "-----" + original_login_type);
					loginAccount = las.modify(account, brand, 
							original_login_password, original_login_type);	
					//写日志
					String log = "operation_code: "+operation_code+" | "+
						         "account: "+account+" | "+
						         "brand: "+brand+" | "+
						         "action_item: restore_password"+" | "+
						         "before_password: "+loginAccount.getOriginal_login_password()+" | "+
						         "before_type: "+loginAccount.getOriginal_login_type()+" | "+
						         "after_password: "+loginAccount.getCurrent_login_password()+" | "+
						         "after_type: "+loginAccount.getCurrent_login_type();
					logger.info(log);
					
				}
				request.setAttribute("account", account);
				request.setAttribute("brand", brand);
				request.setAttribute("current_login_password", loginAccount.getCurrent_login_password());
				request.setAttribute("current_login_type", loginAccount.getCurrent_login_type());
			}
			
			
		}
		request.setAttribute("errorMessage", errorMessage);
		request.getRequestDispatcher("/login_account.jsp")
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
