package service;

import dao.MessageDao;
import dao.MessageDaoImp;
import model.Message;

import java.util.List;

public class MessageService {

    MessageDao messageDao = new MessageDaoImp();

    public List<Message> getMessagesNotRead(String stuNum){
        return messageDao.getMessagesNotRead(stuNum);
    }

    public int getCount(String stuNum){
        return messageDao.getCount(stuNum);
    }

    public int addMessage(Message message){
        return messageDao.addMessage(message);
    }

    public int changeIsRead(String mNum){
        return messageDao.changeIsRead(mNum);
    }
}
