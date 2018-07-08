package dao;

import model.Activity;
import model.Community;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Activity activity=new Activity();
                activity.setaNum(resultSet.getString(1));
                activity.setaContent(resultSet.getString(2));
                activity.setaDate(resultSet.getString(3));
                activity.setaTitle(resultSet.getString(4));
                activity.setcName(resultSet.getString(5));
                activities.add(activity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return activities;
    }


    @Override
    public List<Activity> getActByComm(String cName) {
        String sql="select * from activity where cName=?";
        List<Activity> activities=new ArrayList<>();
        try {
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,cName);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Activity activity=new Activity();
                activity.setaNum(resultSet.getString(1));
                activity.setaContent(resultSet.getString(2));
                activity.setaDate(resultSet.getString(3));
                activity.setaTitle(resultSet.getString(4));
                activity.setcName(resultSet.getString(5));
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
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,aNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                activity.setaNum(resultSet.getString(1));
                activity.setaContent(resultSet.getString(2));
                activity.setaDate(resultSet.getString(3));
                activity.setaTitle(resultSet.getString(4));
                activity.setcName(resultSet.getString(5));
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
        objects[4]=activity.getcName();

        return executeUpdata(sql,objects);
    }

    @Override
    public int updateActivity(Activity activity) {
        String sql="update Activity set aContent=?,aDate=?,aTitle=?,cName=? where aNum=?";
        Object[] objects=new Object[5];
        objects[0]=activity.getaContent();
        objects[1]=activity.getaDate();
        objects[2]=activity.getaTitle();
        objects[3]=activity.getcName();
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
        String sql="select c.* from Community c,Activity a where c.cName=a.cName and aNum=?";
        Connection connection=getConn();
        Community community=new Community();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,aNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String cNum=resultSet.getString(1);
                String cName=resultSet.getString(2);
                String cType=resultSet.getString(3);
                String cSrc=resultSet.getString(4);
                String Syn=resultSet.getString(5);
                String cStartTime=resultSet.getString(6);
                String stuNum=resultSet.getString(7);
                community.setcNum(cNum);
                community.setcName(cName);
                community.setcType(cType);
                community.setcSrc(cSrc);
                community.setSyn(Syn);
                community.setcStartTime(cStartTime);
                community.setcStuNum(stuNum);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return community;
    }
}
