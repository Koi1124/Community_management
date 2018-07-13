package servlet;

import model.Message;
import model.User;
import service.CommunityService;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        MessageService messageService=new MessageService();
        String cNum = req.getParameter("cNum");
        String value = req.getParameter("value");

        String stuNum = req.getParameter("stuNum");
        String cName = communityService.getCNameByCommID(cNum);
        String content = "您申请创建的社团"+cName;

        Message message = new Message();
        message.setIsRead(0);
        message.setStuNum(stuNum);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime=simpleDateFormat.format(date);
        message.setmTime(publishTime);

        if("1".equals(value)){
            //System.out.println("====="+cNum);
            communityService.changeState(cNum);
            content+="已通过";
            message.setmContent(content);
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            message.setmSrc("#");
            messageService.addMessage(message);
        }
        else if("0".equals(value)){
            communityService.deleteComm(cNum);
            content+="未通过";
            message.setmContent(content);
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            message.setmSrc("#");
            messageService.addMessage(message);
        }
    }

}
