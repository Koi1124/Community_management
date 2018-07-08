package dao;

import model.Remark;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RemarkDAOImp extends DBconnImp implements RemarkDAO {


    @Override
    public int addRemark(Remark remark) {
        String sql="insert into Remark values(?,?,?,?,?)";
        Object[] objects=new Object[5];
        objects[0]=remark.getrNum();
        objects[1]=remark.getrContent();
        objects[2]=remark.getStuNum();
        objects[3]=remark.getrDate();
        objects[4]=remark.getcNum();


        return executeUpdata(sql,objects);
    }

    @Override
    public int updateRemark(Remark remark) {
        String sql="update Remark set rContent=?,stuNum=?,rDate=?,cNum=? where rNum=?";
        Object[] objects=new Object[5];

        return executeUpdata(sql,objects);
    }

    @Override
    public int deleteRemark(String rNum) {
        String sql="delete from Remark where rNum=?";
        Object[] objects={rNum};


        return executeUpdata(sql,objects);
    }

    @Override
    public User getCommentUserInfo(String rNum) {
        String sql="select u.* from userforcomm u,Remark r where u.stuNum=r.stuNum and rNum=?";
        User user=new User();
        try {
            getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,rNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String stuNum=resultSet.getString(1);
                String stuName=resultSet.getString(2);
                String stuSchool=resultSet.getString(3);
                String stuBirth=resultSet.getString(4);
                String stuSex=resultSet.getString(5);
                String stuProfess=resultSet.getString(6);
                String uName=resultSet.getString(7);
                String uPassword=resultSet.getString(8);
                String stuSrc=resultSet.getString(9);
                String stuNumber=resultSet.getString(10);
                user.setStuNum(stuNum);
                user.setStuName(stuName);
                user.setStuSchool(stuSchool);
                user.setStuBirth(stuBirth);
                user.setStuSex(stuSex);
                user.setStuProfess(stuProfess);
                user.setuName(uName);
                user.setuPassword(uPassword);
                user.setStuSrc(stuSrc);
                user.setStuNumber(stuNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return user;
    }

    @Override
    public List<Remark> getRemarkInfo(String cNum) {
        String sql="SELECT * FROM remark WHERE cNum=?";
        List<Remark> remarks=new ArrayList<>();
        try {
            getConnection(); // need revising
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,cNum);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Remark remark=new Remark();
                remark.setrNum(resultSet.getString(1));
                remark.setrContent(resultSet.getString(2));
                remark.setStuNum(resultSet.getString(3));
                remark.setrDate(resultSet.getString(4));
                remark.setcNum(resultSet.getString(5));
                remarks.add(remark);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return remarks;
    }
}
