package dao;

import model.Activity;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DBconnImp implements DBconn {
    private String url="jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF-8&";
    private String user="root";
    private String password="123456";
    public Connection conn = null;
    public ResultSet rs = null;
    public PreparedStatement ps=null;

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载mysq驱动
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载错误");
            e.printStackTrace();//打印出错详细信息
        }
    }

    public void getConnection(){
        try{
            conn= DriverManager.getConnection(url,user,password);
            System.out.println("连接成功");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public int executeUpdata(String sql,Object[] obj){
        int count=0;
        getConnection();
        try{
            ps=conn.prepareStatement(sql);
            if(obj!=null&&obj.length>0){

                for (int i=0;i<obj.length;i++){
                    ps.setObject(i+1,obj[i]);
                }

            }
            count=ps.executeUpdate();



        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        return count;

    }

    @Override
    public Connection getConn() {
        return conn;
    }


    @Override
    //检查当前用户   0代表注册检查  1代表登陆检查
    public boolean checkisHave(String sql, Object[] obj, User user, int op) {
        boolean count=false;
        int rowCount = 0;
        ResultSet rs=null;
        getConnection();
        try{
            ps=conn.prepareStatement(sql);
            if(obj!=null&&obj.length>0){

                for (int i=0;i<obj.length;i++){
                    ps.setObject(i+1,obj[i]);
                }

            }
            rs=ps.executeQuery();
            if(op==0)
            {
                if(rs.next()){
                    rowCount=rs.getInt("mycount");
                }
            }
            if(op==1)
            {
                if (rs.next())
                {
                    user.setStuNum(rs.getString("stuNum"));
                    user.setStuName(rs.getString("stuName"));
                    user.setStuSex(rs.getString("stuSex"));
                    user.setStuProfess(rs.getString("stuProfess"));
                    user.setStuBirth(rs.getString("stuBirth"));
                    user.setStuNumber(rs.getString("stuNumber"));
                    user.setStuSrc(rs.getString("stuSrc"));
                    user.setStuSchool(rs.getString("stuSchool"));
                    rowCount = 1;
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }finally {


        }

        if(rowCount==0)count=false;
        else count=true;
        return count;
    }

    @Override
    public void initActivity(List<Activity> alist) {
        String sql = "select * from activity order by aDate DESC ";
        getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next())
            {
                Activity tacv = new Activity();
                tacv.setaNum(rs.getString("aNum"));
                tacv.setaContent(rs.getString("aContent"));
                tacv.setaDate(rs.getString("aDate"));
                tacv.setaTitle(rs.getString("aTitle"));
                tacv.setcNum(rs.getString("cNum"));
                tacv.setView(rs.getInt("view"));
                alist.add(tacv);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
