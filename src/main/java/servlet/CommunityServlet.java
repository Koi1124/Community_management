package servlet;

import service.CommunityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommunityServlet extends HttpServlet {

    CommunityService communityService=new CommunityService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String cNum=req.getParameter("getCNum");
        if (cNum!=null) {
            passCommInfo(cNum,req,resp);
            return;
        }


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    public void passCommInfo(String cNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session=req.getSession();
        session.setAttribute("cNum",cNum);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("remark.jsp");
        requestDispatcher.forward(req,resp);
    }


}
