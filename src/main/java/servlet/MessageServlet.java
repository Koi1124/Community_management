package servlet;

import model.Message;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MessageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageService messageService = new MessageService();
        String btn = req.getParameter("btn");

        System.out.println("btn  "+req.getParameter("btn"));

        if(btn.equals("0")){
            String mNum = req.getParameter("mNum");



            System.out.println("mNum  "+mNum);

            messageService.changeIsRead(mNum);
        }
        if(btn.equals("1")){
            List<Message> messages=messageService.getMessagesNotRead(req.getParameter("stuNum"));
            for(Message m:messages){
                messageService.changeIsRead(m.getmNum());
            }
        }

    }

}