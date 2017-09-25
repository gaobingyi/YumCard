package com.yum.card.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yum.card.model.OrderInfo;
import com.yum.card.model.Pager;
import com.yum.card.service.OrderInfoService;

/**
 * Servlet implementation class QueryOrderInfoServlet
 */
@WebServlet("/QueryOrderInfoServlet")
public class QueryOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOrderInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//4.��ȡ�����Ӳ�ѯ����
		String queryParameter_new = request.getParameter("queryParameter_new");
		System.out.println("�������ύ��ѯ������"+queryParameter_new);
		
		//1.��ȡ����ѯ����
		String queryParameter = request.getParameter("query_parameter");
		if (queryParameter != null) {
			queryParameter = queryParameter.trim();
		}
		System.out.println("���ύ��ѯ������"+queryParameter);
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
			
			System.out.println("��ʽ����ѯ������"+queryParameter_new);
		}
		//2.��ȡ�������Ӳ�������
		String parameterType = request.getParameter("parameter_type");
		System.out.println("���������ύ�������ͣ�"+parameterType);
		
		OrderInfoService ois = new OrderInfoService();
		
		//��service��ȡ��ҳ��Ϣ����pageNow��
		Pager pager = ois.getPager(queryParameter_new, parameterType);
		//3.��ȡ��������pageNow
		String pageNow = request.getParameter("pageNow");
		System.out.println("���������ύpageNow��"+pageNow);
		//��jsp��ȡ��ҳ��Ϣ��pageNow��
		pager.setPageNow(Integer.parseInt(pageNow));
		System.out.println("pageSize:" + pager.getPageSize());
		System.out.println("pageNow:" + pager.getPageNow());
		System.out.println("rowCount:" + pager.getRowCount());
		System.out.println("pageCount:" + pager.getPageCount());
		
		ArrayList<OrderInfo> orderInfoList = ois.getOrderInfo(queryParameter_new, parameterType, pager);
		
		request.setAttribute("orderInfoList", orderInfoList);
		request.setAttribute("pager", pager);
		request.setAttribute("queryParameter_new", queryParameter_new);
		request.setAttribute("parameterType", parameterType);

		request.getRequestDispatcher("query_order_info.jsp")
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
