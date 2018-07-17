package servlet;

import model.Activity;
import model.Community;
import model.User;
import service.ActivityService;
import service.CommunityService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {
    CommunityService communityService=new CommunityService();
    UserService userService=new UserService();
    ActivityService activityService=new ActivityService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out=resp.getWriter();
        String keyword=req.getParameter("search");
        List<Community> commlist= searchInfoFromComm(keyword,req,resp);
        List<User> userList=searchUser(keyword,req,resp);
        List<Activity> activities=searchActivity(keyword,req,resp);
        int comcount =0;
        for(int i=0;i<commlist.size();i++){
            Community comm=commlist.get(i);
            req.setAttribute("cSrc"+i,comm.getcSrc());
            req.setAttribute("cNum"+i,comm.getcNum());
            req.setAttribute("Syn"+i,comm.getSyn());
            req.setAttribute("cStartTime"+i,comm.getcStartTime());
            req.setAttribute("cName"+i,comm.getcName());
            req.setAttribute("cType"+i,comm.getcType());
            comcount++;
        }
        req.setAttribute("comcount",comcount);
        int actcount=0;
        for(int k=0;k<activities.size();k++){
            Activity activity=activities.get(k);
            req.setAttribute("actNum"+k,activity.getaNum());
            req.setAttribute("actDate"+k,activity.getaDate());
            req.setAttribute("actTitle"+k,activity.getaTitle());
            req.setAttribute("coNum"+k,activity.getcNum());
            actcount++;
        }
        req.setAttribute("actcount",actcount);


        int usercount=0;
        for(int j=0;j<userList.size();j++){
           User user=userList.get(j);
           req.setAttribute("username"+j,user.getuName());
           req.setAttribute("name"+j,user.getStuName());
           req.setAttribute("school"+j ,user.getStuSchool());
           req.setAttribute("num"+j,user.getStuNum());
           usercount++;
        }
        req.setAttribute("usercount",usercount);
        if(usercount!=0||comcount!=0||actcount!=0){
            RequestDispatcher dispatcher = req.getRequestDispatcher("searchResult.jsp");
            dispatcher.forward(req,resp);
        }else{
            out.println("<script language = javascript>alert('NOT FOUND');");
            out.println("location.href='homepage.jsp'</script>");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    public List<User> searchUser(String keyword,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users=userService.searchUser(keyword);
        return users;
    }
    public List<Community> searchInfoFromComm(String keyword,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Community> communityList=communityService.communityInformation(keyword);
        return communityList;
    }
    public List<Activity> searchActivity(String keyword, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Activity> activities=activityService.searchActivity(keyword);
        return activities;
    }
}
