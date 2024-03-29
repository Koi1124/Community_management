package servlet;

import dao.DBconn;
import dao.DBconnImp;
import dao.UserDAOImp;
import model.Activity;
import model.Community;
import model.User;
import service.ActivityService;
import service.CommunityService;
import service.UploadService;
import service.UserService;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    CommunityService communityService=new CommunityService();
    UploadService uploadService=new UploadService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //UserDAOImp muImp = new UserDAOImp();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String op = request.getParameter("action");
        List<Activity> alist = new ArrayList<>();
        List<User> ulist = new ArrayList<>();
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

                HttpSession session = request.getSession();
                session.setAttribute("curUser",user);

                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request,response);
            }
            else{
                response.sendRedirect("Login.jsp?error=yes");
            }
        }
        if(op.equals("reflash"))
        {
            initUlist(request,response,ulist,userService);
            HttpSession session = request.getSession();
            session.setAttribute("curUser",user);
            session.setAttribute("ulist",ulist);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
            dispatcher.forward(request,response);
        }

        if(op.equals("管理员登录")) {
            boolean isAdmin = adminLogin(request, response, user);
            if (isAdmin) {
                initUlist(request, response, ulist, userService);
                HttpSession session = request.getSession();
                session.setAttribute("curUser", user);
                session.setAttribute("ulist", ulist);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("Login.jsp?error=yes");
            }
        }
        if(op.equals("out"))
        {
            HttpSession session=request.getSession();
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request,response);
        }
            if(op.equals("delete"))
            {
                String deleteNum = request.getParameter("deleteNum");
                userService.deleteUser(deleteNum);
                initUlist(request,response,ulist,userService);
                HttpSession session= request.getSession();
                session.setAttribute("ulist",ulist);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
                dispatcher.forward(request,response);

            }
            if(op.equals("search"))
            {
                List<User> searchList = new ArrayList<>();
                String uName = request.getParameter("searchUname");
                userService.searchUser(searchList,uName);
                HttpSession session= request.getSession();
                session.setAttribute("ulist",searchList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
                dispatcher.forward(request,response);
            }
        if(op.equals("createComm"))
        {
            boolean createResult = createApply(request,response,communityService);
            if(createResult)
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Community.jsp");
                dispatcher.forward(request,response);
            }
            else response.sendRedirect("createCom.jsp?error=yes");

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

    private boolean adminLogin(HttpServletRequest request, HttpServletResponse response,User user) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        if(userName.equals("Admin")&&passWord.equals("community")){
            user.setuName(userName);
            user.setuPassword(passWord);
            return true;
        }
        else return false;
    }




    public void initUlist(HttpServletRequest request, HttpServletResponse response,List<User> ulist,UserService userService)throws ServletException, IOException
    {
        userService.initUserList(ulist);
    }

    private boolean createApply(HttpServletRequest request, HttpServletResponse response, CommunityService communityService) throws ServletException, IOException{
        Community comm = new Community();
        comm.setcName(request.getParameter("cname"));
        UUID uuid = UUID.randomUUID();
        String cNum=uuid.toString().replaceAll("-","");
        comm.setcNum(cNum);
        comm.setcType(request.getParameter("ctype"));
        comm.setSyn(request.getParameter("syn"));
        comm.setState(0);
        HttpSession session = request.getSession();
        User cur = (User)session.getAttribute("curUser");
        comm.setcStuNum(cur.getStuNum());

        String imgUrl = request.getParameter("fileUrl");

        boolean createResult=false;
        if (!imgUrl.equals("")) {
            //String path ="D:\\tomcat\\apache-tomcat-7.0.73\\webapps\\ROOT\\assets\\img\\"+cNum+".png";
            //String workPath = "D:\\IDEA\\CommunityTest\\src\\main\\webapp\\assets\\img\\"+cNum+".png";
            //String relPath=path.substring(44);

            String relPath = "assets\\img\\" + cNum + ".png";
            String tomcatPath = request.getSession().getServletContext().getRealPath("/") + relPath;

            String base64img = imgUrl.substring(imgUrl.indexOf(",")+1);
            comm.setcSrc(relPath);
            createResult=communityService.createApply(comm);
            if(createResult) {
                uploadService.SaveCutImage(base64img,tomcatPath);
                //uploadService.SaveCutImage(base64img,path,workPath);
            }
        }

        return createResult;
    }

}
