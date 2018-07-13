package dao;

import model.Message;

import java.util.List;

public interface MessageDao {

    public List<Message> getMessagesNotRead(String stuNum);

    public int getCount(String stuNum);

    public int addMessage(Message message);

    public int changeIsRead(String mNum);

}
