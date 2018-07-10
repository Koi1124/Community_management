package servlet;

import service.CommunityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CommunityServlet extends HttpServlet {

    CommunityService communityService=new CommunityService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String op=req.getParameter("type");
        if (op.equals("normal")) {
            String cNum=req.getParameter("getCNum");
            String jsp="remark.jsp";
            if (cNum!=null) {
                passCommInfo(cNum,jsp,req,resp);
                return;
            }
            String join=req.getParameter("join");
            if (join!=null) {
                String[] temp=join.split("&");
                String stuNum=temp[0];
                String comNum=temp[1];
                commApply(comNum,stuNum,req,resp);
                return;
            }
            String quit=req.getParameter("quit");
            if (quit!=null) {
                String[] temp=quit.split("&");
                String comNum=temp[0];
                String stuNum=temp[1];
                quitComm(comNum,stuNum,req,resp);
                return;
            }
        }
        if (op.equals("manage")) {
            String cNum=req.getParameter("getCNum");
            String jsp="CommunityManage.jsp";
            if (cNum!=null) {
                passCommInfo(cNum,jsp,req,resp);
                return;
            }
            String memberDel=req.getParameter("memberDel");
            if (memberDel!=null) {
                String[] temp=memberDel.split("&");
                String comNum=temp[0];
                String stuNum=temp[1];
                memberDel(comNum,stuNum,req,resp);
                return;
            }
            String agree=req.getParameter("agree");
            if (agree!=null) {
                String[] temp=agree.split("&");
                String comNum=temp[0];
                String stuNum=temp[1];
                memberAgree(comNum,stuNum,req,resp);
                return;
            }
        }




    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    public void passCommInfo(String cNum,String jsp,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session=req.getSession();
        session.setAttribute("cNum",cNum);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher(jsp);
        requestDispatcher.forward(req,resp);
    }

    public void memberDel(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (communityService.doDeleteMum(cNum,stuNum)) {
            out.println("<script language = javascript>alert('DELETE SUCCESS');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('DELETE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }

    public void memberAgree(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (communityService.doAgreeMum(cNum,stuNum)){
            out.println("<script language = javascript>alert('AGREE SUCCESS');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('AGREE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }

    public void commApply(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (communityService.doApply(cNum,stuNum)){
            out.println("<script language = javascript>alert('APPLY SUCCESS');");
            out.println("location.href='CommunityList.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('APPLY FAILED');");
            out.println("location.href='CommunityList.jsp'</script>");
        }
    }

    public void quitComm(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (communityService.doDeleteMum(cNum,stuNum)) {
            out.println("<script language = javascript>alert('QUIT SUCCESS');");
            out.println("location.href='Community.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('QUIT FAILED');");
            out.println("location.href='Community.jsp'</script>");
        }
    }


}
