package servlet;

import model.User;
import service.UploadService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


public class doRevise extends HttpServlet {


    UserService userService=new UserService();
    UploadService uploadService = new UploadService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");


        HttpSession session=req.getSession();
        User client=(User) session.getAttribute("curUser");


        String op=req.getParameter("type");
        if (op.equals("infoRevise")) {
            reviseFundInfo(client,req,resp,session);
        }
        if (op.equals("pwdRevise")) {
            revisePWD(client,req,resp,session);
        }



    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    public void reviseFundInfo(User client,HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        client = (User)session.getAttribute("curUser");
        String stuNum = client.getStuNum();
        String stuName=req.getParameter("stuName");
        String stuSchool=req.getParameter("stuSchool");
        String stuBirth=req.getParameter("stuBirth");
        String stuSex=req.getParameter("stuSex");
        String stuProfess=req.getParameter("stuProfess");
        String uName=req.getParameter("uName");
        String stuNumber=req.getParameter("stuNumber");
        String stuSrc=client.getStuSrc();
        String pwd=client.getuPassword();
        User updateUser=new User();
        updateUser.setStuName(stuName);
        updateUser.setStuNum(stuNum);
        updateUser.setStuSchool(stuSchool);
        updateUser.setStuSrc(stuSrc);
        updateUser.setuPassword(pwd);
        updateUser.setStuNumber(stuNumber);
        updateUser.setStuSex(stuSex);
        updateUser.setStuBirth(stuBirth);
        updateUser.setStuProfess(stuProfess);
        updateUser.setuName(uName);

        String imgUrl = req.getParameter("fileUrl");
        if (!imgUrl.equals("")) {
            String base64img = imgUrl.substring(imgUrl.indexOf(",")+1);
            String tomcatPath ="D:\\tomcat\\apache-tomcat-7.0.73\\webapps\\ROOT\\assets\\img\\"+stuNum+ UUID.randomUUID().toString()+".png";

            String relPath=tomcatPath.substring(44);
            updateUser.setStuSrc(relPath);
            String workPath = "D:\\IDEA\\CommunityTest\\src\\main\\webapp\\"+relPath;


            uploadService.SaveCutImage(base64img,tomcatPath,workPath);
        }
        if (userService.doRevise(updateUser)){
            session.setAttribute("curUser",updateUser);
            out.println("<script language = javascript>alert('SUCCEED');");
            out.println("location.href='UserInfo.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('FAIL');");
            out.println("location.href='UserInfo.jsp'</script>");
        }
    }

    public void revisePWD(User client,HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        String oldpwd=req.getParameter("password");
        String newpwd=req.getParameter("newPassword");
        String surepwd=req.getParameter("surePassword");
        if (!oldpwd.equals(client.getuPassword())) {
            out.println("<script language = javascript>alert('OLD PASSWORD ERROR');");
            out.println("location.href='password.jsp'</script>");
        }else if (oldpwd.equals(newpwd)) {
            out.println("<script language = javascript>alert('NEW PASSWORD SAME AS THE OLD');");
            out.println("location.href='password.jsp'</script>");
        }else if (!newpwd.equals(surepwd)) {
            out.println("<script language = javascript>alert('PASSWORD SURE PLZ');");
            out.println("location.href='password.jsp'</script>");
        }else{
            client.setuPassword(surepwd);
            userService.doRevise(client);
            session.setAttribute("curUser",client);
            out.println("<script language = javascript>alert('SUCCEED');");
            out.println("location.href='UserInfo.jsp'</script>");
        }

    }

}
