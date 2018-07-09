package service;

import com.sun.org.apache.regexp.internal.RE;
import dao.RemarkDAO;
import dao.RemarkDAOImp;
import dao.UserDAO;
import dao.UserDAOImp;
import model.Remark;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class RemarkService {

    RemarkDAO remarkDAO=new RemarkDAOImp();



    public List<Remark> getRMByComm(String  cNum){
        List<Remark> remarks=remarkDAO.getRemarkInfo(cNum);
        return remarks;
    }


    public User getRemarkerInfo(String rNum){
        User user=remarkDAO.getCommentUserInfo(rNum);
        return user;
    }

    public boolean doDeleteRemark(String rNum) {
        if (remarkDAO.deleteRemark(rNum)>0) {
            return true;
        }else return false;
    }

    public void addRemark(Remark remark){
        remarkDAO.addRemark(remark);
    }
}
