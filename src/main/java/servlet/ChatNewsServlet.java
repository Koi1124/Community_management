package servlet;

import model.ChatNews;
import model.Message;
import model.User;
import service.ChatNewsService;
import service.MessageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ChatNewsServlet")
public class ChatNewsServlet extends HttpServlet {
    ChatNewsService chatNewsService = new ChatNewsService();
    MessageService messageService = new MessageService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String op = request.getParameter("action");
        String action=op.split(",")[0];
        String clickNum=null;
        if(op.contains(","))clickNum = op.split(",")[1];

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("curUser");
        if(action.equals("进入私信"))
        {
            List<ChatNews> newsList = chatNewsService.initNews(user.getStuNum());
            List<String> chatList = chatNewsService.initUser(newsList,user.getStuNum());
            session.setAttribute("newsList",newsList);
            session.setAttribute("chatList",chatList);
            List<ChatNews> curNewsList = new ArrayList<>();
            session.setAttribute("curNewsList",curNewsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
        if(action.equals("私信"))
        {
            List<ChatNews> newsList = chatNewsService.initNews(user.getStuNum());
            List<String> chatList = chatNewsService.clickGetUsers(newsList,user.getStuNum(),clickNum);
            session.setAttribute("newsList",newsList);
            session.setAttribute("chatList",chatList);
            String clickUser = clickNum;
            //int icount=chatNewsService.getUnreadCount(user.getStuNum(),clickNum);
            chatNewsService.changeState(user.getStuNum(),clickUser);
            session.setAttribute("clickUser",clickUser);
            List<ChatNews> curNewsList = chatNewsService.getNewsWithUser(newsList,clickNum);
            session.setAttribute("curNewsList",curNewsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }

        if(action.equals("更改私信用户")){
            String clickUser = clickNum;
            chatNewsService.changeState(user.getStuNum(),clickUser);
            List<ChatNews> newsList = chatNewsService.initNews(user.getStuNum());
            session.setAttribute("newsList",newsList);

            session.setAttribute("clickUser",clickUser);
            List<ChatNews> curNewsList = chatNewsService.getNewsWithUser(newsList,clickNum);
            session.setAttribute("curNewsList",curNewsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }

        if(action.equals("sendMessage")){
            String endNum = request.getParameter("usernum");
            String news = request.getParameter("content");
            chatNewsService.addNews(user.getStuNum(),endNum,news);
            List<ChatNews> newsList = chatNewsService.initNews(user.getStuNum());
            session.setAttribute("newsList",newsList);
            List<ChatNews> curNewsList = chatNewsService.getNewsWithUser(newsList,endNum);
            session.setAttribute("curNewsList",curNewsList);


            Message message = new Message();
            message.setIsRead(0);
            message.setStuNum(endNum);
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String publishTime=simpleDateFormat.format(date);
            message.setmTime(publishTime);
            String content=user.getuName()+"给您发送了一条私信";
            message.setmContent(content);
            String mNum= UUID.randomUUID().toString().replaceAll("-","");
            message.setmNum(mNum);
            message.setmSrc("#");
            messageService.addMessage(message);



            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }
        if(action.equals("delete")){
            String nNum = op.split(",")[1];
            chatNewsService.deleteNews(nNum);
            List<ChatNews> newsList = chatNewsService.initNews(user.getStuNum());
            session.setAttribute("newsList",newsList);
            String curNum = (String)session.getAttribute("clickUser");
            List<ChatNews> curNewsList = chatNewsService.getNewsWithUser(newsList,curNum);
            session.setAttribute("curNewsList",curNewsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
