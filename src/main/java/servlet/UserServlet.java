package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {


    UserService userService=new UserService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stuNum= req.getParameter("stuNum");
        if (stuNum!=null){
            passUserInfo(stuNum,req,resp);
        }


    }


    /**
     *@Discription: 根据学号传递到用户信息页面
     *@Param: [stuNum, req, resp]
     *@Return: void
     *@Author:
     */
    public void passUserInfo(String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

        User user=userService.getUserByID(stuNum);
        req.setAttribute("UserInfo",user);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("User.jsp");
        requestDispatcher.forward(req,resp);

    }
}
