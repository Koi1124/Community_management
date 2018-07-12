package dao;

import model.Community;
import model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommunityDAOImp extends DBconnImp implements CommunityDAO {


    @Override
    public List<Community> getCommunities() {
        String sql="select * from community";
        List<Community> communities=new ArrayList<>();
        try {
            getConnection();
            Statement statement=conn.createStatement();
            rs=statement.executeQuery(sql);
            while (rs.next()){
                Community community=new Community();
                community.setcNum(rs.getString("cNum"));
                community.setcName(rs.getString("cName"));
                community.setcType(rs.getString("cType"));
                community.setcSrc(rs.getString("cSrc"));
                community.setSyn(rs.getString("Syn"));
                community.setcStartTime(rs.getString("cStartTime"));
                community.setcStuNum(rs.getString("stuNum"));
                community.setState(rs.getInt("state"));
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
    public int updateCommunity(String syn,String cNum) {
        String sql="update community set Syn=? where cNum=?";
        Object[] objects=new Object[2];
        objects[0]=syn;
        objects[1]=cNum;
        return executeUpdata(sql,objects);
    }

    @Override
    public int deleteCommunity(String cNum) {
        String sql = "delete from community where cNum = ?";
        Object[] objects = {cNum};
        return executeUpdata(sql, objects);
    }

    @Override
    public List<Community> communityInformation(String keyword) {
        List<Community> commlist=new ArrayList<>();
        String sql="SELECT * from community where cName like ?";
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+keyword+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                Community comm =new Community();
                comm.setcName(rs.getString("cName"));
                comm.setcNum(rs.getString("cNum"));
                comm.setcSrc(rs.getString("cSrc"));
                comm.setcStuNum(rs.getString("stuNum"));
                comm.setcStartTime(rs.getString("cStartTime"));
                comm.setcType(rs.getString("cType"));
                comm.setSyn(rs.getString("Syn"));
                comm.setState(rs.getInt("state"));
                commlist.add(comm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commlist;

    }

    @Override
    public List<Community> getCommByUID(String stuNum) {
        List<Community> commlist=new ArrayList<>();
        String sql="SELECT c.* from community c,stu_comm sc where c.cNum=sc.cNum and sc.stuNum=? and sc.state=1 ORDER  BY sc.stuIden";
        try {
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,stuNum);
            rs=ps.executeQuery();
            while(rs.next())
            {
                Community comm =new Community();
                comm.setcName(rs.getString("cName"));
                comm.setcNum(rs.getString("cNum"));
                comm.setcSrc(rs.getString("cSrc"));
                comm.setcStuNum(rs.getString("stuNum"));
                comm.setcStartTime(rs.getString("cStartTime"));
                comm.setcType(rs.getString("cType"));
                comm.setSyn(rs.getString("Syn"));
                comm.setState(rs.getInt("state"));
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
            ps=conn.prepareStatement(sql);
            ps.setString(1,cNum);
            rs=ps.executeQuery();
            while (rs.next()) {
                comm.setcName(rs.getString("cName"));
                comm.setcNum(rs.getString("cNum"));
                comm.setcSrc(rs.getString("cSrc"));
                comm.setcStuNum(rs.getString("stuNum"));
                comm.setcStartTime(rs.getString("cStartTime"));
                comm.setcType(rs.getString("cType"));
                comm.setSyn(rs.getString("Syn"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return comm;
    }

    @Override
    public List<User> getMumbers(String cNum) {
        String sql="select u.* from userforcomm u,stu_comm sc where u.stuNum=sc.stuNum and cNum=? ORDER BY sc.stuIden,sc.state";
        List<User> users=new ArrayList<>();
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,cNum);
            rs=ps.executeQuery();
            while (rs.next()){
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
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public String getCNameByCommID(String cNum) {
        String sql="select cName from community where cNum=?";
        String cName="";
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,cNum);
            rs=ps.executeQuery();
            while (rs.next()) {
                cName=rs.getString("cName");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cName;
    }

    @Override
    public List<Community> getManCommByUID(String stuNum) {

        String sql="select c.* from community c,stu_comm sc where c.cNum=sc.cNum and stuIden IN ('1','2') and sc.stuNum=? ORDER BY sc.stuIden ";
        List<Community> commlist=new ArrayList<>();
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, stuNum);
            rs=ps.executeQuery();
            while (rs.next()){
                Community comm =new Community();
                comm.setcName(rs.getString("cName"));
                comm.setcNum(rs.getString("cNum"));
                comm.setcSrc(rs.getString("cSrc"));
                comm.setcStuNum(rs.getString("stuNum"));
                comm.setcStartTime(rs.getString("cStartTime"));
                comm.setcType(rs.getString("cType"));
                comm.setSyn(rs.getString("Syn"));
                comm.setState(rs.getInt("state"));
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
    public int updateUState(String cNum, String stuNum) {
        String sql="update stu_comm set state=1 where cNum=? and stuNum=?";
        Object[] objects=new Object[2];
        objects[0]=cNum;
        objects[1]=stuNum;

        return executeUpdata(sql,objects);
    }

    @Override
    public int updateUIden(String cNum, String stuNum, String iden) {
        String sql="update stu_comm set stuIden='"+iden+"' where cNum=? and stuNum=?";
        Object[] objects={cNum,stuNum};
        return executeUpdata(sql,objects);
    }

    @Override
    public int takeoverLead(String cNum, String adminNum,String leadNum) {
        String sql="";
        Object[] objects=new Object[2];
        if (updateUIden(cNum,adminNum,"1")>0) {// 首先将管理员身份升为1
            sql="update community set stuNum=? where cNum=?";
            objects[0]=adminNum;
            objects[1]=cNum;
        }
        return executeUpdata(sql,objects);
    }

    @Override
    public int getCountByUser(User user) {
        String sql="select count(*) commCount from stu_comm where stuNum=? and state=1";
        int count=0;
        try {
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getStuNum());
            rs=ps.executeQuery();
            while (rs.next()){
                count=rs.getInt("commCount");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
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

    @Override
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

            // 创建社团同时插入社长与社团信息
            String sql2="insert into stu_comm(stuNum,cNum,stuIden,state)values(?,?,?,?)";
            Object[] obj2=new Object[4];
            obj2[0]=comm.getcStuNum();
            obj2[1]=comm.getcNum();
            obj2[2]="1";// 默认创建者为社长
            obj2[3]=3;// 默认状态为3
            executeUpdata(sql2,obj2);

        }
        return createResult;
    }

    @Override
    public String getIdenByNum(String stuNum, String cNum) {

        String sql="select stuIden from stu_comm where stuNum=? and cNum=? and state=1";
        String identity="";
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,stuNum);
            ps.setString(2,cNum);
            rs=ps.executeQuery();
            while (rs.next()){
                identity=rs.getString("stuIden");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return identity;
    }

    @Override
    public int getUStateBy(String stuNum, String cNum) {
        String sql="select state from stu_comm where stuNum=? and cNum=?";
        int state=0;
        try{
            getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,stuNum);
            ps.setString(2,cNum);
            rs=ps.executeQuery();

            if (rs.next()){
                state=rs.getInt("state");
            }else{
                state=2;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public int addMum(String cNum, String stuNum) {
        String sql="insert into stu_comm values(?,?,?,?)";
        Object[] objects=new Object[4];
        objects[0]=stuNum;
        objects[1]=cNum;
        objects[2]="3";// 默认身份为3
        objects[3]="0";// 默认状态为0，即申请状态
        return executeUpdata(sql,objects);
    }

    @Override
    public int changeState(String cNum){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String publishTime=simpleDateFormat.format(date);

        String sql = "update community set state = 1,cStartTime=? where cNum = ?";
        Object[] objects = {publishTime,cNum};

        String sql2 = "update stu_comm set state=1 where cNum=?";
        Object[] objects2={cNum};
        executeUpdata(sql2,objects2);

        return executeUpdata(sql, objects);
    }
}
