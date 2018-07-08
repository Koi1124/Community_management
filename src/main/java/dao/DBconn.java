package dao;
import model.Activity;
import model.User;

import java.sql.Connection;
import java.util.List;

public interface DBconn {
    int executeUpdata(String sql,Object[] obj);
    void getConnection();
    Connection getConn();
    boolean checkisHave(String sql, Object[] obj, User user, int op);
    void initActivity(List<Activity>alist);
}
