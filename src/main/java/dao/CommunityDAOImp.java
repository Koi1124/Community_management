package dao;

import model.Community;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommunityDAOImp extends DBconnImp implements CommunityDAO {


    @Override
    public List<Community> getCommunities() {
        String sql="select * from community";
        List<Community> communities=new ArrayList<>();
        try {
            getConnection();
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Community community=new Community();
                community.setcNum(resultSet.getString(1));
                community.setcName(resultSet.getString(2));
                community.setcType(resultSet.getString(3));
                community.setcSrc(resultSet.getString(4));
                community.setSyn(resultSet.getString(5));
                community.setcStartTime(resultSet.getString(6));
                community.setcStuNum(resultSet.getString(7));
                communities.add(community);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return communities;
    }

    @Override
    public int addCommunity(Community community) {
        String sql="insert into Community values(?,?,?,?,?,?,?)";
        Object[] objects=new Object[7];
        objects[0]=community.getcNum();
        objects[1]=community.getcName();
        objects[2]=community.getcType();
        objects[3]=community.getcSrc();
        objects[4]=community.getSyn();
        objects[5]=community.getcStartTime();
        objects[6]=community.getcStuNum();

        return executeUpdata(sql,objects);
    }

    @Override
    public int updateCommunity(Community community) {
        return 0;
    }

    @Override
    public int deleteCommunity(String cNum) {
        return 0;
    }

    @Override
    public List<Community> communityInformation(String keyword) {
        return null;
    }

    @Override
    public List<Community> getCommByUID(String stuNum) {
        List<Community> commlist=new ArrayList<>();
        String sql="SELECT c.* from community c,stu_comm sc where c.cNum=sc.cNum and sc.stuNum=?";
        try {
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,stuNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Community comm =new Community();
                comm.setcName(resultSet.getString("cName"));
                comm.setcNum(resultSet.getString("cNum"));
                comm.setcSrc(resultSet.getString("cSrc"));
                comm.setcStuNum(resultSet.getString("stuNum"));
                comm.setcStartTime(resultSet.getString("cStartTime"));
                comm.setcType(resultSet.getString("cType"));
                comm.setSyn(resultSet.getString("Syn"));
                commlist.add(comm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return commlist;
    }


    @Override
    public Community getCommByID(String cNum) {
        String sql="select * from community where cNum=?";
        Community comm=new Community();
        try{
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,cNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                comm.setcName(resultSet.getString("cName"));
                comm.setcNum(resultSet.getString("cNum"));
                comm.setcSrc(resultSet.getString("cSrc"));
                comm.setcStuNum(resultSet.getString("stuNum"));
                comm.setcStartTime(resultSet.getString("cStartTime"));
                comm.setcType(resultSet.getString("cType"));
                comm.setSyn(resultSet.getString("Syn"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return comm;
    }

    @Override
    public List<User> getMumbers(String cNum) {
        String sql="select u.* from userforcomm u,stu_comm sc where u.stuNum=sc.stuNum and cNum=?";
        List<User> users=new ArrayList<>();
        try{
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,cNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                User user=new User();
                user.setStuNum(resultSet.getString(1));
                user.setStuName(resultSet.getString(2));
                user.setStuSchool(resultSet.getString(3));
                user.setStuBirth(resultSet.getString(4));
                user.setStuSex(resultSet.getString(5));
                user.setStuProfess(resultSet.getString(6));
                user.setuName(resultSet.getString(7));
                user.setuPassword(resultSet.getString(8));
                user.setStuSrc(resultSet.getString(9));
                user.setStuNumber(resultSet.getString(10));
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public String getCommIDByCName(String cName) {
        String sql="select cNum from community where cName=?";
        String cNum="";
        try{
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,cName);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                cNum=resultSet.getString("cNum");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cNum;
    }
}
