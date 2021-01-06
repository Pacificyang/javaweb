package cn.pacificy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pacificy.domain.User;
import cn.pacificy.service.UserService;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//
		PrintWriter out = response.getWriter();
		
		//
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		User user = new User(null,username,password);
		
		UserService service = new UserService();
		boolean flag = service.checkUsername(username);
		
		if(flag) {
			//out.println("you can register now!");
			if (service.addUser(username, password)) {

				//out.println("user add success!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				//return;
			} else {
				//out.println("user add fail!");
			}

		}
		else {
			//response.setHeader("Refresh", "3;URL=/myAJAX/register.jsp");
			
			request.setAttribute("error", "username exist!");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		
	}
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
