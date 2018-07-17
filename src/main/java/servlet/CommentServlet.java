package servlet;

import dao.ActivityDAO;
import dao.ActivityDAOImp;
import model.ActComment;
import model.ActReply;
import model.Activity;
import model.Message;
import service.ActivityService;
import service.MessageService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommentServlet extends HttpServlet {
    ActivityService activityService=new ActivityService();
    MessageService messageService=new MessageService();
    UserService userService=new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Activity activity=(Activity) session.getAttribute("passAct");
        String comment=req.getParameter("comment");
        PrintWriter out=resp.getWriter();
        if (comment!=null){
            String[] commentInfo=req.getParameter("commentInfo").split("&");
            String stuNum=commentInfo[0];
            String aNum=commentInfo[1];
            insertComment(aNum,stuNum,comment,out,activity,req,resp);
            return;
        }
        String reply=req.getParameter("reply");
        if (reply!=null){
            String[] replyInfo=req.getParameter("replyInfo").split("&");
            String fromNum=replyInfo[0];
            String toNum=replyInfo[1];
            String acNum=replyInfo[2];
            insertReply(fromNum,toNum,reply,acNum,out,activity,req,resp);
            return;
        }


    }

    private void insertComment(String aNum,String stuNum,String content,PrintWriter out,Activity activity,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        String acNum= UUID.randomUUID().toString().replace("-","");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        ActComment actComment=new ActComment();
        actComment.setAcNum(acNum);
        actComment.setAcDate(publishTime);
        actComment.setContent(content);
        actComment.setaNum(aNum);
        actComment.setStuNum(stuNum);
        if (activityService.doAddComment(actComment)) {
            req.setAttribute("selectedActInfo",activity);
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("ActivityInfo.jsp");
            requestDispatcher.forward(req,resp);
        }
    }

    private void insertReply(String fromID,String toID,String content,String acNum,PrintWriter out,Activity activity,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        String acrNum=UUID.randomUUID().toString().replace("-","");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        ActReply actReply=new ActReply();
        actReply.setFromID(fromID);
        actReply.setToID(toID);
        actReply.setContent(content);
        actReply.setAcNum(acNum);
        actReply.setAcrDate(publishTime);
        actReply.setAcrNum(acrNum);


        String message="用户 "+userService.getUserByID(actReply.getFromID()).getuName()+" 在"+activity.getaTitle()+"活动中回复了你";

        if (activityService.doAddReply(actReply)){
            Message ms=new Message();
            ms.setmContent(message);
            ms.setmNum(UUID.randomUUID().toString().replace("-",""));
            ms.setIsRead(0);
            ms.setStuNum(actReply.getToID());
            ms.setmSrc("ActivityInfo.jsp?act="+activity.getaNum());
            ms.setmTime(publishTime);
            messageService.addMessage(ms);


            req.setAttribute("selectedActInfo",activity);
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("ActivityInfo.jsp");
            requestDispatcher.forward(req,resp);
        }
    }
}
