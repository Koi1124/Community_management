package dao;

import com.sun.corba.se.spi.ior.ObjectKey;
import model.Community;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp extends DBconnImp implements UserDAO {


    @Override
    public boolean checkUser(User user) {
        Object[] obj = new Object[2];
        obj[0] = user.getuName();
        obj[1] = user.getuPassword();
        String sql = "select * from userforcomm where uName=? and uPassword=?";
        return checkisHave(sql,obj,user,1);
    }

    @Override
    public boolean isHave(User user) {
        String sql = "select count(*)mycount from userforcomm where uName=?";
        Object[] obj = new Object[1];
        obj[0]=user.getuName();
        return checkisHave(sql,obj,user,0);
    }

    @Override
    public int addUser(User user) {
        String sql="insert into userforcomm values(?,?,?,?,?,?,?,?,?,?)";
        Object[] objects=new Object[10];
        objects[0]=user.getStuNum();
        objects[1]=user.getStuName();
        objects[2]=user.getStuSchool();
        objects[3]=user.getStuBirth();
        objects[4]=user.getStuSex();
        objects[5]=user.getStuProfess();
        objects[6]=user.getuName();
        objects[7]=user.getuPassword();
        objects[8]=user.getStuSrc();
        objects[9]=user.getStuNumber();

        return executeUpdata(sql,objects);
    }

    @Override
    public int updateUser(User user) {
        String sql="update userforcomm set uName=?,stuProfess=?,stuNumber=?,stuBirth=?,uPassword=?,stuSrc=? where stuNum=?";
        Object[] objects=new Object[7];
        objects[0]=user.getuName();
        objects[1]=user.getStuProfess();
        objects[2]=user.getStuNumber();
        objects[3]=user.getStuBirth();
        objects[4]=user.getuPassword();
        objects[5]=user.getStuSrc();
        objects[6]=user.getStuNum();



        return executeUpdata(sql,objects);
    }

    @Override
    public int deleteUser() {
        return 0;
    }

    @Override
    public List<User> userInformation(String keyword) {
        String sql="Select * from userforcomm where stuName like ? OR uName like ?";
        List<User> userlist=new ArrayList<>();
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            ps.setString(2,"%"+keyword+"%");
            rs =ps.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setStuNum(rs.getString(1));
                user.setStuName(rs.getString(2));
                user.setStuSchool(rs.getString(3));
                user.setStuBirth(rs.getString(4));
                user.setStuSex(rs.getString(5));
                user.setStuProfess(rs.getString(6));
                user.setuName(rs.getString(7));
                user.setuPassword(rs.getString(8));
                user.setStuSrc(rs.getString(9));
                user.setStuNumber(rs.getString(10));
                userlist.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }


    @Override
    public List<Community> getCommunityInfo(String stuNum) {
        return null;
    }


    @Override
    public User getUserInfoByID(String stuNum) {
        String sql="select * from userforcomm where stuNum=?";
        User user=new User();
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,stuNum);
            rs=ps.executeQuery();
            while (rs.next()){
                user.setStuNum(rs.getString("stuNum"));
                user.setStuName(rs.getString("stuName"));
                user.setStuSchool(rs.getString("stuSchool"));
                user.setStuBirth(rs.getString("stuBirth"));
                user.setStuSex(rs.getString("stuSex"));
                user.setStuProfess(rs.getString("stuProfess"));
                user.setuName(rs.getString("uName"));
                user.setuPassword(rs.getString("uPassword"));
                user.setStuSrc(rs.getString("stuSrc"));
                user.setStuNumber(rs.getString("stuNumber"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return user;
    }


    @Override
    public void initUserList(List<User> ulist)
    {
        String sql = "select * from userforcomm order by uName DESC ";
        getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                User tUser = new User();
                tUser.setStuNum(rs.getString("stuNum"));
                tUser.setuName(rs.getString("uName"));
                tUser.setStuName(rs.getString("stuName"));
                ulist.add(tUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int deleteUser(String deleteNum) {
        String sql="delete from userforcomm where stuNum=?";
        Object[] obj=new Object[1];
        obj[0]=deleteNum;

        String sql2="delete from remark where stuNum=?";
        executeUpdata(sql2,obj);

        return executeUpdata(sql,obj);
    }

    @Override
    public void searchUser(List<User>searchList,String uName)
    {
        String sql = "select * from userforcomm WHERE uName LIKE '%"+uName+"%'"+"or stuName LIKE '%"+uName+"%'";
        getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                User tUser = new User();
                tUser.setStuNum(rs.getString("stuNum"));
                tUser.setuName(rs.getString("uName"));
                tUser.setStuName(rs.getString("stuName"));
                searchList.add(tUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String getStuName(String stuNum) {
        String sql="select stuName from userforcomm where stuNum='"+stuNum+"'";
        String stuName=null;
        try {
            getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next())stuName=rs.getString("stuName");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return stuName;
    }
}
