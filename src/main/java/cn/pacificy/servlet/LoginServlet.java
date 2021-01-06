package cn.pacificy.servlet;

import cn.pacificy.domain.User;
import cn.pacificy.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
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
        boolean flag = service.login(username,password);

        if(flag) {

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            //response.setHeader("Refresh", "3;URL=/myAJAX/register.jsp");

            request.setAttribute("error", "login fail!");
            request.setAttribute("user", user);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            //return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //
        PrintWriter out = response.getWriter();

        //
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        User user = new User(null,username,password);

        UserService service = new UserService();
        boolean flag = service.login(username,password);

        if(flag) {

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            //response.setHeader("Refresh", "3;URL=/myAJAX/register.jsp");

            request.setAttribute("error", "login fail!");
            request.setAttribute("user", user);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            //return;
        }
    }
}
