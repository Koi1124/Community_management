package servlet;

import model.Message;
import model.User;
import service.CommunityService;
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
import java.util.List;
import java.util.UUID;

public class CommunityServlet extends HttpServlet {

    CommunityService communityService=new CommunityService();
    MessageService messageService = new MessageService();
    UserService userService = new UserService();
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
            String promote=req.getParameter("promote");
            if (promote!=null) {
                String[] temp=promote.split("&");
                String comNum=temp[0];
                String stuNum=temp[1];
                String iden="2";
                memberUpdate(comNum,stuNum,iden,req,resp);
                return;
            }
            String takeover=req.getParameter("takeover");
            if (takeover!=null) {
                String[] temp=takeover.split("&");
                String comNum=temp[0];
                String stuNum=temp[1];
                String leadNum=temp[2];
                leadTakeover(comNum,stuNum,leadNum,req,resp);
                return;
            }
            String demote=req.getParameter("demote");
            if (demote!=null) {
                String[] temp=demote.split("&");
                String comNum=temp[0];
                String stuNum=temp[1];
                String iden="3";
                memberUpdate(comNum,stuNum,iden,req,resp);
                return;
            }
            String reviseNum=req.getParameter("reviseSyn");
            if (reviseNum!=null){
                String syn=req.getParameter("getSyn");
                PrintWriter out=resp.getWriter();
                if (syn.equals("")){
                    out.println("<script language = javascript>alert('SYN EMPTY NOT ALLOWED');");
                    out.println("location.href='CommunityManage.jsp'</script>");
                }else {
                    reviseSyn(syn,reviseNum,out,req,resp);
                }
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
            String cName = communityService.getCNameByCommID(cNum);
            String content = "您已被移出社团"+cName;
            Message message = new Message();
            message.setStuNum(stuNum);
            message.setIsRead(0);
            message.setmContent(content);
            message.setmSrc("#");
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            messageService.addMessage(message);
            out.println("<script language = javascript>");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('DELETE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }

    public void memberAgree(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        String cName = communityService.getCNameByCommID(cNum);
        String content = "社团"+cName+"加入申请";
        Message message = new Message();
        message.setStuNum(stuNum);
        message.setIsRead(0);
        message.setmSrc("#");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        message.setmTime(publishTime);
        if (communityService.doAgreeMum(cNum,stuNum)) {
            content+="已通过";
            message.setmContent(content);
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            messageService.addMessage(message);
            out.println("<script language = javascript>");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            content+="被拒绝";
            message.setmContent(content);
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            messageService.addMessage(message);
            out.println("<script language = javascript>alert('AGREE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }

    public void leadTakeover(String cNum,String adminNum,String leadNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        List<User> users = communityService.getUserByComm(cNum);
        String cName = communityService.getCNameByCommID(cNum);
        String content = "社团"+cName+"的团长变更为"+userService.getStuName(adminNum);
        Message message = new Message();
        message.setIsRead(0);
        message.setmContent(content);
        message.setmSrc("#");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        message.setmTime(publishTime);
        if (communityService.doTakeover(cNum,adminNum,leadNum,"3")){
            for(User u:users){
                message.setStuNum(u.getStuNum());
                String mNum= UUID.randomUUID().toString().replaceAll("-","");
                message.setmNum(mNum);
                messageService.addMessage(message);
            }
            out.println("<script language = javascript>");
            out.println("location.href='remark.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('TAKEOVER FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }

    public void memberUpdate(String cNum,String stuNum,String iden,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (communityService.doUpdateStuIden(cNum,stuNum,iden)){
            String cName = communityService.getCNameByCommID(cNum);
            String identity=communityService.getIdenByNum(stuNum, cNum);
            if (identity.equals("1")){
                identity="社长";
            }else if (identity.equals("2")){
                identity="管理员";
            }else if (identity.equals("3")){
                identity="成员";
            }
            String content = "您已成为社团"+cName+"的"+identity;
            Message message = new Message();
            message.setmContent(content);
            message.setIsRead(0);
            message.setStuNum(stuNum);
            message.setmSrc("#");
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String publishTime=simpleDateFormat.format(date);
            message.setmTime(publishTime);
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            messageService.addMessage(message);
            out.println("<script language = javascript>");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('UPDATE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }


    public void commApply(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        String cName = communityService.getCNameByCommID(cNum);
        String content = "有人申请加入社团"+cName;

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        String src = "CommunityManage.jsp?cNumByPara="+cNum;

        List<User> users = communityService.getAdministrators(cNum);
        if (communityService.doApply(cNum,stuNum)){
            for(User u:users){
                Message message = new Message();
                message.setmContent(content);
                message.setIsRead(0);
                message.setmSrc(src);
                message.setStuNum(u.getStuNum());
                String mNum= UUID.randomUUID().toString().replaceAll("-","");
                message.setmNum(mNum);
                message.setmTime(publishTime);
                messageService.addMessage(message);
            }
            out.println("<script language = javascript>");
            out.println("location.href='CommunityList.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('APPLY FAILED');");
            out.println("location.href='CommunityList.jsp'</script>");
        }
    }

    public void quitComm(String cNum,String stuNum,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out=resp.getWriter();
        if (communityService.doDeleteMum(cNum,stuNum)) {
            out.println("<script language = javascript>");
            out.println("location.href='Community.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('QUIT FAILED');");
            out.println("location.href='Community.jsp'</script>");
        }
    }

    public void reviseSyn(String syn,String cNum,PrintWriter out,HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        if (communityService.doReviseSyn(syn,cNum)){
            out.println("<script language = javascript>");
            out.println("location.href='CommunityManage.jsp'</script>");
        }else {
            out.println("<script language = javascript>alert('UPDATE FAILED');");
            out.println("location.href='CommunityManage.jsp'</script>");
        }
    }


}
