package com.yum.card.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.util.DesUtils;
import com.yum.card.util.PropertiesUtil;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String string_before = request.getParameter("string_before");
		if (string_before != null)
			string_before = string_before.trim();
		String admin_password = request.getParameter("admin_password");	
		if (admin_password != null)
			admin_password = admin_password.trim();
		String operation_code = request.getParameter("operation_code");
		if (operation_code != null)
			operation_code = operation_code.trim();
		String admin_password2 = request.getParameter("admin_password2");
		if (admin_password2 != null)
			admin_password2 = admin_password2.trim();
		if (operation_code == null) {//加密字符串
			String errorMessage = "0";
			String string_after = null;		
			if (!admin_password.equals("YUMKFCPHDI")) {
				errorMessage = "1";//管理员密码错误
				string_after = "admin_password error! ";
			} else {
				try {
					DesUtils des = new DesUtils();
					string_after = des.encrypt(string_before);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			request.setAttribute("string_before", string_before);
			request.setAttribute("string_after", string_after);
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		} else {//分配操作码
			String errorMessage2 = "0";
			if (!admin_password2.equals("YUMKFCPHDI")) {
				errorMessage2 = "1";//管理员密码错误
				System.out.println(errorMessage2);
			} else {
				PropertiesUtil propertiesUtil= new PropertiesUtil();
				String code = propertiesUtil.getProperties2("operation_code");
				System.out.println(code);
				operation_code = code + "&" + operation_code;
				System.out.println(operation_code);
				propertiesUtil.setProperties("operation_code", operation_code);
				errorMessage2 = "2";
			}
			request.setAttribute("errorMessage2", errorMessage2);
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
