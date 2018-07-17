package service;

import dao.ChatNewsDao;
import dao.ChatNewsDaoImp;
import model.ChatNews;

import java.util.ArrayList;
import java.util.List;

public class ChatNewsService {
    private ChatNewsDaoImp chatNewsDaoImp = new ChatNewsDaoImp();
    public List<String> initUser(List<ChatNews> newsList, String stuNum) {
        return chatNewsDaoImp.initUser(newsList,stuNum);
    }

    public List<ChatNews> initNews(String stuNum){return chatNewsDaoImp.initNews(stuNum);}

    public List<ChatNews> getNewsWithUser(List<ChatNews>newsList,String withUserNum)
    {
        List<ChatNews> newswithUserList = new ArrayList<>();
        for(ChatNews temnews : newsList)
        {
            if(temnews.getStartNum().equals(withUserNum)||temnews.getEndNum().equals(withUserNum))
            {
                newswithUserList.add(temnews);
            }
        }

        return newswithUserList;
    }

    public void changeState(String stuNum,String chatNum){
        chatNewsDaoImp.changeState(stuNum,chatNum);
    }

    public int getUnreadCount(String stuNum,String chatNum)
    {
        return chatNewsDaoImp.getUnreadCount(stuNum,chatNum);
    }

    public List<String> clickGetUsers(List<ChatNews> newsList,String stuNum, String clickNum){
        return chatNewsDaoImp.clickGetUsers(newsList,stuNum,clickNum);
    }

    public void addNews(String startNum,String endNum,String news)
    {
        chatNewsDaoImp.addNews(startNum,endNum,news);
    }

    public void deleteNews(String nNum)
    {
        chatNewsDaoImp.deleteNews(nNum);
    }
}
