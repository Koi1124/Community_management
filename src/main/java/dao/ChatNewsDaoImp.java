package dao;

import model.ChatNews;
import model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ChatNewsDaoImp extends DBconnImp implements ChatNewsDao {

    UserDAOImp userDAOImp = new UserDAOImp();

    @Override
    public void addNews(String startNum, String endNum,String news) {
        String nNum = UUID.randomUUID().toString().replaceAll("-","");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String newTime = df.format(date).toString();
        int state = 0;

        String sql = "Insert into chatnews values(?,?,?,?,?,?)";
        Object[] obj = {startNum,endNum,news,nNum,newTime,state};
        getConnection();
        executeUpdata(sql,obj);
    }

    public List<ChatNews> initNews(String stuNum)  //将该用户的所有私信加入list中
    {
        List<ChatNews> newsList = new ArrayList<>();
        getConnection();
        try {
            String sql="select * from chatnews where startNum='"+stuNum+"' or endNum='"+stuNum+"'order by newTime desc";
            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                ChatNews tempnews = new ChatNews();
                tempnews.setStartNum(rs.getString("startNum"));
                tempnews.setEndNum(rs.getString("endNum"));
                tempnews.setNews(rs.getString("news"));
                tempnews.setnNum(rs.getString("nNum"));
                tempnews.setNewTime(rs.getString("newTime"));
                tempnews.setState(rs.getInt("state"));
                newsList.add(tempnews);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return newsList;

    }

    @Override
    public List<String> clickGetUsers(List<ChatNews> newsList,String stuNum, String clickNum) {
        List<String> clickUsers = new ArrayList<>();
        clickUsers.add(userDAOImp.getStuName(clickNum));
        for(ChatNews tempNews : newsList)
        {
            String tempuser=null;
            if(tempNews.getStartNum().equals(stuNum))
            {
                tempuser = userDAOImp.getStuName(tempNews.getEndNum());
                if(!clickUsers.contains(tempuser))clickUsers.add(tempuser);
            }
            if(tempNews.getEndNum().equals(stuNum))
            {
                tempuser=userDAOImp.getStuName(tempNews.getStartNum());
                if(!clickUsers.contains(tempuser))clickUsers.add(tempuser);
            }
        }

        return clickUsers;
    }

    @Override
    public List<String> initUser(List<ChatNews> newsList,String stuNum) {
        List<String> usersList =new ArrayList<>();

        for(ChatNews tempNews : newsList)
        {
            String tempuser=null;
            if(tempNews.getStartNum().equals(stuNum))
            {
                tempuser = userDAOImp.getStuName(tempNews.getEndNum());
                if(!usersList.contains(tempuser))usersList.add(tempuser);
            }
            if(tempNews.getEndNum().equals(stuNum))
            {
                tempuser=userDAOImp.getStuName(tempNews.getStartNum());
                if(!usersList.contains(tempuser))usersList.add(tempuser);
            }
        }

        return usersList;
    }


    public void changeState(String stuNum,String chatNum)
    {
        String sql="update chatnews set state='1' where startNum=? or endNum=? and state=0";
        Object[] obj2={chatNum,stuNum};
        executeUpdata(sql,obj2);
    }

    public int getUnreadCount(String stuNum,String chatNum)
    {
        int count=0;
        getConnection();
        try {
            int count1=0,count2=0;
            String sql2 = "select count(*) mycount from chatnews where startNum='"+chatNum+"' and endNum='"+stuNum+"' and state='0'";
            ps=conn.prepareStatement(sql2);
            rs=ps.executeQuery();
            if(rs.next())count2=rs.getInt("mycount");
            count = count1+count2;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return count;
    }


    @Override
    public void deleteNews(String nNum) {
        getConnection();
        try {
            String sql = "delete from chatnews where nNum='"+nNum+"'";
            ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
