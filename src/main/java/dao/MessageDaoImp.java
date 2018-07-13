package dao;

import model.Message;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImp extends DBconnImp implements MessageDao{

    public List<Message> getMessagesNotRead(String stuNum){
        List<Message> messages = new ArrayList<>();
        String sql = "select * from message where stuNum = ? and isRead = '0'";
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,stuNum);
            rs=ps.executeQuery();
            while (rs.next()){
                Message message = new Message();
                message.setmContent(rs.getString("mContent"));
                message.setStuNum(rs.getString("stuNum"));
                message.setIsRead(rs.getInt("isRead"));
                message.setmTime(rs.getString("mTime"));
                message.setmSrc(rs.getString("mSrc"));
                message.setmNum(rs.getString("mNum"));
                messages.add(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public int getCount(String stuNum){
        String sql = "select stuNum,count(*) from message where stuNum = ? and isRead=0 group by stuNum";
        int count = 0;
        getConnection();
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,stuNum);
            rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getString("stuNum").equals(stuNum))
                    count = rs.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public int addMessage(Message message){
        String sql = "insert into message values (?, ?, ?, ?, ?, ?)";
        Object[] objects=new Object[6];
        objects[0]=message.getmNum();
        objects[1]=message.getmContent();
        objects[2]=message.getStuNum();
        objects[3]=message.getIsRead();
        objects[4]=message.getmSrc();
        objects[5]=message.getmTime();

        return executeUpdata(sql,objects);
    }

    public int changeIsRead(String mNum){
        String sql = "update message set isRead = '1' where mNum = ?";
        Object[] objects = {mNum};
        return executeUpdata(sql, objects);
    }

}
