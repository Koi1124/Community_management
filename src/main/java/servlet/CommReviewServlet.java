package servlet;

import service.CommunityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommReviewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        //System.out.println("in");

        CommunityService communityService = new CommunityService();
        String cNum = req.getParameter("cNum");
        String value = req.getParameter("value");

        System.out.println(value);

        if("1".equals(value)){
            //System.out.println("====="+cNum);
            communityService.changeState(cNum);
        }
        else if("0".equals(value)){
            communityService.deleteComm(cNum);
        }


    }

    public static class DeleteCommServlet extends HttpServlet {
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req, resp);
        }


        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            String cNum = req.getParameter("cNum");
            deleteCommunity(cNum,req,resp);

        }

        public void deleteCommunity(String cNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

            PrintWriter out=resp.getWriter();
            CommunityService communityService = new CommunityService();
            if(communityService.deleteComm(cNum) > 0){
                out.println("<script language = javascript>alert('SUCCEED');");
                out.println("location.href='comList.jsp'</script>");
            }
            else{
                out.println("<script language = javascript>alert('FAIL');");
                out.println("location.href='comList.jsp'</script>");
            }

        }
    }
}
