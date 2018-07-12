package dao;

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
                activities.add(activity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return activities;
    }


    @Override
    public List<Activity> getActByComm(String cNum) {
        String sql="select * from activity where cNum=?";
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return activity;
    }

    @Override
    public int addActivity(Activity activity) {
        String sql="insert into Activity values(?,?,?,?,?)";
        Object[] objects=new Object[5];
        objects[0]=activity.getaNum();
        objects[1]=activity.getaContent();
        objects[2]=activity.getaDate();
        objects[3]=activity.getaTitle();
        objects[4]=activity.getcNum();

        return executeUpdata(sql,objects);
    }

    @Override
    public int updateActivity(Activity activity) {
        String sql="update Activity set aContent=?,aDate=?,aTitle=?,cNum=? where aNum=?";
        Object[] objects=new Object[5];
        objects[0]=activity.getaContent();
        objects[1]=activity.getaDate();
        objects[2]=activity.getaTitle();
        objects[3]=activity.getcNum();
        objects[4]=activity.getaNum();


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
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
