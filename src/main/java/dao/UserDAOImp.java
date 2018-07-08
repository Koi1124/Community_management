package dao;

import com.sun.corba.se.spi.ior.ObjectKey;
import model.Community;
import model.User;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String sql="update userforcomm set uName=?,stuProfess=?,stuNumber=?,stuBirth=?,uPassword=? where stuNum=?";
        Object[] objects=new Object[6];
        objects[0]=user.getuName();
        objects[1]=user.getStuProfess();
        objects[2]=user.getStuNumber();
        objects[3]=user.getStuBirth();
        objects[4]=user.getuPassword();
        objects[5]=user.getStuNum();



        return executeUpdata(sql,objects);
    }

    @Override
    public int deleteUser() {
        return 0;
    }

    @Override
    public User userInformation(String keyword) {
        return null;
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
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,stuNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return user;
    }
}
