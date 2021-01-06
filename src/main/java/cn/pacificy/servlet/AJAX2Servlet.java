package cn.pacificy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class AJAX2Servlet extends HttpServlet {
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		//String username = new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8"
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(username);
		
		//System.out.println("AJAX2Servlet???????????");
	}
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
