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
    public boolean isHave(Community comm) {
        String sql = "select count(*)mycount from community where cName='"+comm.getcName()+"'";
        boolean isHave=false;
        try
        {
            getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next())
            {
                int rowCount=rs.getInt("mycount");
                if(rowCount!=0)isHave=true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return isHave;

    }

    public boolean createApply(Community comm)
    {
        boolean createResult=false;
        if(!isHave(comm))
        {
            String sql="insert into community(cNum,cName,cType,cSrc,Syn,cStartTime,stuNum,state)values(?,?,?,?,?,?,?,?)";
            Object[] obj=new Object[8];
            obj[0]=comm.getcNum();
            obj[1]=comm.getcName();
            obj[2]=comm.getcType();
            obj[3]=comm.getcSrc();
            obj[4]=comm.getSyn();
            obj[5]=comm.getcStartTime();
            obj[6]=comm.getcStuNum();
            obj[7]=comm.getState();
            if(executeUpdata(sql,obj)!=0)createResult=true;

        }
        return createResult;
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

    @Override
    public List<Community> getManCommByUID(String stuNum) {

        String sql="select c.* from community c,stu_comm sc where c.cNum=sc.cNum and stuIden='1' and c.stuNum=?";
        List<Community> commlist=new ArrayList<>();
        try{
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1, stuNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return commlist;
    }

    @Override
    public int deleteUserFromComm(String cNum, String stuNum) {

        String sql="delete from stu_comm where cNum=? and stuNum=?";
        Object[] objects=new Object[2];
        objects[0]=cNum;
        objects[1]=stuNum;

        return executeUpdata(sql,objects);
    }

    @Override
    public int getCountByUser(User user) {
        String sql="select count(*) commCount from stu_comm where stuNum=?";
        int count=0;
        try {
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getStuNum());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                count=resultSet.getInt("commCount");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public String getIdenByNum(String stuNum, String cNum) {

        String sql="select stuIden from stu_comm where stuNum=? and cNum=?";
        String identity="";
        try{
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,stuNum);
            preparedStatement.setString(2,cNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                identity=resultSet.getString("stuIden");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return identity;
    }
}
