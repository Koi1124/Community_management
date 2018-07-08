package servlet;

import model.Activity;
import service.ActivityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ActivityServlet extends HttpServlet {
    ActivityService activityService=new ActivityService();
    List<Activity> activities=new ArrayList<>();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //addActivity(req,resp);
        String aNum=req.getParameter("aNum");
        passActivityInfo(aNum,req,resp);




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
        HttpSession session=req.getSession();
        String cName=(String) session.getAttribute("cName");

        Activity activity=new Activity();
        activity.setaNum(aNum);
        activity.setaTitle(aTitle);
        activity.setaContent(aContent);
        activity.setaDate(publishTime);
        activity.setcName(cName);

        activityService.addActivity(activity);

        activities=activityService.getAcFromDB();
        session.setAttribute("activityList",activities);
        resp.sendRedirect("activity.jsp");

    }

    public void updateActivity(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

    }

    public void deleteActivity(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

    }
}
