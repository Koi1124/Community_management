package dao;

import model.ChatNews;
import model.User;

import java.util.List;

public interface ChatNewsDao {

    void addNews(String startNum,String endNum,String news);

    List<ChatNews> initNews(String stuNum);

    List<String> initUser(List<ChatNews> newsList,String stuNum);

    void changeState(String stuNum,String chatNum);

    int getUnreadCount(String stuNum,String chatNum);

    List<String> clickGetUsers(List<ChatNews> newsList,String stuNum,String clickNum);

    void deleteNews(String nNum);
}
