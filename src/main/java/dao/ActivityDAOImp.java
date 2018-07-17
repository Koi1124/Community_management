package dao;

import model.ActComment;
import model.ActReply;
import model.Activity;
import model.Community;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAOImp extends DBconnImp implements ActivityDAO {


    @Override
    public void initActivityList(List<Activity> alist) {
        initActivity(alist);
    }

    @Override
    public List<Activity> getActivities() {
        String sql="select * from Activity";

        List<Activity> activities=new ArrayList<>();
        try {
            getConnection();
            Statement statement=conn.createStatement();
            rs=statement.executeQuery(sql);
            while (rs.next()){
                Activity activity=new Activity();
                activity.setaNum(rs.getString("aNum"));
                activity.setaContent(rs.getString("aContent"));
                activity.setaDate(rs.getString("aDate"));
                activity.setaTitle(rs.getString("aTitle"));
                activity.setcNum(rs.getString("cNum"));
                activity.setView(rs.getInt("view"));
                activities.add(activity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return activities;
    }


    @Override
    public List<Activity> getActByComm(String cNum) {
        String sql="select * from activity where cNum=? ORDER BY aDate DESC";
        List<Activity> activities=new ArrayList<>();
        try {
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,cNum);
            rs=ps.executeQuery();
            while (rs.next()){
                Activity activity=new Activity();
                activity.setaNum(rs.getString("aNum"));
                activity.setaContent(rs.getString("aContent"));
                activity.setaDate(rs.getString("aDate"));
                activity.setaTitle(rs.getString("aTitle"));
                activity.setcNum(rs.getString("cNum"));
                activity.setView(rs.getInt("view"));
                activities.add(activity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return activities;
    }

    @Override
    public Activity getActByNum(String aNum) {
        String sql="select * from activity where aNum=?";
        Activity activity=new Activity();
        try {
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,aNum);
            rs=ps.executeQuery();
            while (rs.next()) {
                activity.setaNum(rs.getString("aNum"));
                activity.setaContent(rs.getString("aContent"));
                activity.setaDate(rs.getString("aDate"));
                activity.setaTitle(rs.getString("aTitle"));
                activity.setcNum(rs.getString("cNum"));
                activity.setView(rs.getInt("view"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return activity;
    }

    @Override
    public int addActivity(Activity activity) {
        String sql="insert into Activity values(?,?,?,?,?,?)";
        Object[] objects=new Object[5];
        objects[0]=activity.getaNum();
        objects[1]=activity.getaContent();
        objects[2]=activity.getaDate();
        objects[3]=activity.getaTitle();
        objects[4]=activity.getcNum();
        objects[5]=activity.getView();

        return executeUpdata(sql,objects);
    }

    @Override
    public int updateView(Activity activity) {
        String sql="update Activity set view=? where aNum=?";
        Object[] objects={activity.getView(),activity.getaNum()};

        return executeUpdata(sql,objects);
    }

    @Override
    public int deleteActivity(String aNum) {
        String sql="delete from Activity where aNum=?";
        Object[] objects={aNum};


        return executeUpdata(sql,objects);
    }



    @Override
    public Community getCommInfo(String aNum) {
        String sql="select c.* from Community c,Activity a where c.cNum=a.cNum and aNum=?";
        Connection connection=getConn();
        Community community=new Community();
        try{
            ps=connection.prepareStatement(sql);
            ps.setString(1,aNum);
            rs=ps.executeQuery();
            while (rs.next()){
                String cNum=rs.getString("cNum");
                String cName=rs.getString("cName");
                String cType=rs.getString("cType");
                String cSrc=rs.getString("cSrc");
                String Syn=rs.getString("Syn");
                String cStartTime=rs.getString("cStartTime");
                String stuNum=rs.getString("stuNum");
                int state=rs.getInt("state");
                community.setcNum(cNum);
                community.setcName(cName);
                community.setcType(cType);
                community.setcSrc(cSrc);
                community.setSyn(Syn);
                community.setcStartTime(cStartTime);
                community.setcStuNum(stuNum);
                community.setState(state);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return community;
    }


    @Override
    public List<Activity> getActByKeyword(String keyword) {
        String sql = "select * from activity where aTitle like ? ";
        List<Activity> activities=new ArrayList<>();
        try {
            getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+keyword+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Activity activity=new Activity();
                activity.setaNum(rs.getString("aNum"));
                activity.setaContent(rs.getString("aContent"));
                activity.setaDate(rs.getString("aDate"));
                activity.setaTitle(rs.getString("aTitle"));
                activity.setcNum(rs.getString("cNum"));
                activity.setView(rs.getInt("view"));
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }


    @Override
    public int addComment(ActComment actComment) {
        String sql="insert into activity_comment(aNum,stuNum,content,acDate,acNum) values(?,?,?,?,?)";
        Object[] objects=new Object[5];
        objects[0]=actComment.getaNum();
        objects[1]=actComment.getStuNum();
        objects[2]=actComment.getContent();
        objects[3]=actComment.getAcDate();
        objects[4]=actComment.getAcNum();

        return executeUpdata(sql,objects);
    }

    @Override
    public List<ActComment> getCommentByANum(String aNum) {
        String sql="select * from activity_comment where aNum=? order by acDate desc";
        List<ActComment> actCommentList=new ArrayList<>();
        try {
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,aNum);
            rs=ps.executeQuery();
            while (rs.next()){
                ActComment actComment=new ActComment();
                actComment.setaNum(rs.getString("aNum"));
                actComment.setStuNum(rs.getString("stuNum"));
                actComment.setContent(rs.getString("content"));
                actComment.setAcDate(rs.getString("acDate"));
                actComment.setAcNum(rs.getString("acNum"));
                actCommentList.add(actComment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return actCommentList;
    }

    @Override
    public int addReply(ActReply actReply) {
        String sql="insert into activity_comment_reply(fromID,toID,content,acrDate,acNum,acrNum) values(?,?,?,?,?,?)";
        Object[] objects=new Object[6];
        objects[0]=actReply.getFromID();
        objects[1]=actReply.getToID();
        objects[2]=actReply.getContent();
        objects[3]=actReply.getAcrDate();
        objects[4]=actReply.getAcNum();
        objects[5]=actReply.getAcrNum();
        return executeUpdata(sql,objects);
    }

    @Override
    public List<ActReply> getReplyByUNum(String acNum) {
        String sql="select * from activity_comment_reply where acNum=? order by acrDate";
        List<ActReply> actReplyList=new ArrayList<>();
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,acNum);
            rs=ps.executeQuery();
            while (rs.next()){
                ActReply actReply=new ActReply();
                actReply.setFromID(rs.getString("fromID"));
                actReply.setToID(rs.getString("toID"));
                actReply.setContent(rs.getString("content"));
                actReply.setAcrDate(rs.getString("acrDate"));
                actReply.setAcNum(rs.getString("acNum"));
                actReply.setAcrNum(rs.getString("acrNum"));
                actReplyList.add(actReply);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return actReplyList;
    }

    @Override
    public int getCommentCount(String aNum) {
        String sql1="SELECT COUNT(*) FROM activity_comment WHERE aNum=?";
        String sql2="SELECT COUNT(acr.acNum) FROM activity_comment ac,activity_comment_reply acr WHERE ac.aNum=? AND ac.acNum=acr.acNum";
        int count1=0;
        int count2=0;
        try{
            getConnection();
            ps=conn.prepareStatement(sql1);
            ps.setString(1,aNum);
            rs=ps.executeQuery();
            if (rs.next())count1=rs.getInt(1);
            ps=conn.prepareStatement(sql2);
            ps.setString(1,aNum);
            rs=ps.executeQuery();
            if (rs.next())count2=rs.getInt(1);
            count1+=count2;
        }catch (Exception e){
            e.printStackTrace();
        }
        return count1;
    }
}
