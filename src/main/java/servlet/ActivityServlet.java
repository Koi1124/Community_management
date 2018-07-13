package servlet;

import model.Activity;
import model.Message;
import model.User;
import service.ActivityService;
import service.CommunityService;
import service.MessageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ActivityServlet extends HttpServlet {
    ActivityService activityService=new ActivityService();
    List<Activity> activities=new ArrayList<>();
    CommunityService communityService=new CommunityService();
    MessageService messageService=new MessageService();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //addActivity(req,resp);
        String aNum=req.getParameter("aNum");
        if (aNum!=null) {
            String op=req.getParameter("type");
            if (op==null){
                passActivityInfo(aNum,req,resp);
            } else if (op.equals("manage")) {
                deleteActivity(aNum,req,resp);
            }
        }else {
            addActivity(req,resp);
        }






    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    // 根据活动号传递活动信息
    public void passActivityInfo(String aNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Activity activity=activityService.getAcByID(aNum);
        req.setAttribute("selectedActInfo",activity);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("ActivityInfo.jsp");
        requestDispatcher.forward(req,resp);
    }

    public void addActivity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        PrintWriter out=resp.getWriter();
        String aNum= UUID.randomUUID().toString().replaceAll("-","");
        String aTitle=req.getParameter("aTitle");
        String aContent=req.getParameter("aContent");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        try {
            Date aPublishTime=simpleDateFormat.parse(publishTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String cNum=req.getParameter("cNum");

        Activity activity=new Activity();
        activity.setaNum(aNum);
        activity.setaTitle(aTitle);
        activity.setaContent(aContent);
        activity.setaDate(publishTime);
        activity.setcNum(cNum);

        List<User> users = communityService.getUserByComm(cNum);
        String cName = communityService.getCNameByCommID(cNum);
        String content = "社团"+cName+"发布新活动";


        if (activityService.doAddActivity(activity)) {
            for(User u:users){
                Message message = new Message();
                message.setmContent(content);
                message.setIsRead(0);
                message.setmSrc("ActivityInfo.jsp?act="+activity.getaNum());
                message.setmTime(publishTime);
                String mNum= UUID.randomUUID().toString().replaceAll("-","");
                message.setmNum(mNum);
                message.setStuNum(u.getStuNum());
                messageService.addMessage(message);
            }
            activities=activityService.getAcFromDB();
            HttpSession session=req.getSession();
            session.setAttribute("alist",activities);
            out.println("<script language = javascript>alert('CREATE SUCCESS');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('CREATE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }

    }

    public void updateActivity(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

    }

    public void deleteActivity(String aNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (activityService.doDeleteActivity(aNum)) {
            out.println("<script language = javascript>alert('DELETE SUCCESS');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('DELETE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }
}
