package servlet;

import dao.DBconn;
import dao.DBconnImp;
import dao.UserDAOImp;
import model.Activity;
import model.User;
import service.ActivityService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "doLogin")
public class doLogin extends HttpServlet {
    //static Connection conn = null;
    UserService userService = new UserService();
    ActivityService activityService = new ActivityService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //UserDAOImp muImp = new UserDAOImp();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String op = request.getParameter("action");
        List<Activity> alist = new ArrayList<>();
        User user = new User();

        if(op.equals("注册"))
        {
            boolean istrue=signUp(request,response,user,userService);
            if(istrue)
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
                dispatcher.forward(request,response);
            }
            else{
                response.sendRedirect("signup.jsp?error=yes");
            }

        }
        if(op.equals("点击注册"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request,response);
        }
        if(op.equals("登录"))
        {
            boolean isCheck=login(request,response,user,userService);
            if(isCheck)
            {
                initAlist(request,response,alist,activityService);
                HttpSession session = request.getSession();
                session.setAttribute("curUser",user);
                session.setAttribute("alist",alist);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request,response);
            }
            else{
                response.sendRedirect("index.jsp?error=yes");
            }

        }
        if(op.equals("out"))
        {
            HttpSession session=request.getSession();
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private boolean signUp(HttpServletRequest request, HttpServletResponse response,User user,UserService userService) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String stuSchool = request.getParameter("school");
        String stuBirth = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String stuName = request.getParameter("stuname");
        //String password2 = request.getParameter("password2");
        UUID uuid = UUID.randomUUID();
        String stuNum=uuid.toString().replaceAll("-","");
        user.setStuNum(stuNum);
        user.setStuName(stuName);
        user.setStuSchool(stuSchool);
        user.setStuBirth(stuBirth);
        user.setuName(username);
        user.setStuSex(sex);
        user.setuPassword(password);
        user.setStuSrc("assets/img/user-medium.png");

        //添加到用户表
        if(!userService.isHave(user)){
            userService.doRegister(user);
            return true;
        }
        else return false;

    }
    public void initAlist(HttpServletRequest request, HttpServletResponse response,List<Activity> alist,ActivityService activityService)throws ServletException, IOException
    {
        activityService.initActivityList(alist);
    }

    private boolean login(HttpServletRequest request, HttpServletResponse response,User user,UserService userService) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setuName(username);
        user.setuPassword(password);
        return userService.doLogin(user);

    }
}
