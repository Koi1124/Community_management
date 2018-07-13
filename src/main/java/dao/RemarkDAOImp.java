package dao;

import model.Remark;
import model.User;

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
            ps=conn.prepareStatement(sql);
            ps.setString(1,rNum);
            rs=ps.executeQuery();
            while (rs.next()){
                String stuNum=rs.getString("stuNum");
                String stuName=rs.getString("stuName");
                String stuSchool=rs.getString("stuSchool");
                String stuBirth=rs.getString("stuBirth");
                String stuSex=rs.getString("stuSex");
                String stuProfess=rs.getString("stuProfess");
                String uName=rs.getString("uName");
                String uPassword=rs.getString("uPassword");
                String stuSrc=rs.getString("stuSrc");
                String stuNumber=rs.getString("stuNumber");
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
        String sql="SELECT * FROM remark WHERE cNum=? ORDER BY rDate DESC";
        List<Remark> remarks=new ArrayList<>();
        try {
            getConnection(); // need revising
            ps=conn.prepareStatement(sql);
            ps.setString(1,cNum);
            rs=ps.executeQuery();
            while (rs.next()){
                Remark remark=new Remark();
                remark.setrNum(rs.getString("rNum"));
                remark.setrContent(rs.getString("rContent"));
                remark.setStuNum(rs.getString("stuNum"));
                remark.setrDate(rs.getString("rDate"));
                remark.setcNum(rs.getString("cNum"));
                remarks.add(remark);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return remarks;
    }
}
