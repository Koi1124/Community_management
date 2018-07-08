package servlet;

import model.Activity;
import model.Remark;
import model.User;
import service.RemarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RemarkServlet extends HttpServlet {
    RemarkService remarkService=new RemarkService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");


        String rNum=req.getParameter("rNum");
        if (rNum!=null) {
            passRemarkerInfo(rNum,req,resp);
            return;
        }
        String op=req.getParameter("type");
        if (op.equals("提交留言")) {
            addRemark(req,resp);
        }

    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    
    
    /**
     *@Discription: 传递留言人的信息至用户页面（跳转至留言用户页面）
     *@Param: [rNum, req, resp]
     *@Return: void
     *@Author: 
     */
    public void passRemarkerInfo(String rNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        User user=remarkService.getRemarkerInfo(rNum);
        req.setAttribute("UserInfo",user);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("User.jsp");
        requestDispatcher.forward(req,resp);
    }



    /**
     *@Discription: 添加留言
     *@Param: [req, resp]
     *@Return: void
     *@Author:
     */
    public void addRemark(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        String rNum= UUID.randomUUID().toString().replaceAll("-","");
        String rContent=req.getParameter("rContent");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);

        HttpSession session=req.getSession();
        String cNum=(String) session.getAttribute("cNum");
        User user=(User)session.getAttribute("curUser");
        String stuNum=user.getStuNum();

        Remark remark=new Remark();
        remark.setcNum(cNum);
        remark.setrNum(rNum);
        remark.setrContent(rContent);
        remark.setrDate(publishTime);
        remark.setStuNum(stuNum);

        remarkService.addRemark(remark);
        resp.sendRedirect("remark.jsp");
    }
}
