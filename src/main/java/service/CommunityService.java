package service;

import dao.CommunityDAO;
import dao.CommunityDAOImp;
import model.Community;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class CommunityService {

    CommunityDAO communityDAO=new CommunityDAOImp();


    public List<Community> getCommFromDB() {


        List<Community> communities=communityDAO.getCommunities();


        return communities;
    }

    public List<Community> getCommByUID(String stuNum) {

        List<Community> communities=communityDAO.getCommByUID(stuNum);
        return communities;
    }

    public boolean createApply(Community comm)
    {
        return communityDAO.createApply(comm);
    }


    public List<User> getUserByComm(String cNum){
        List<User> users=communityDAO.getMumbers(cNum);
        return users;
    }

    public Community getCommByID(String cNum) {
        Community community=communityDAO.getCommByID(cNum);
        return community;
    }

    public String getCommIDByCName(String cName) {
        String cNum=communityDAO.getCommIDByCName(cName);
        return cNum;
    }

    public List<Community> getManCommByID(String stuNum){

        List<Community> communities=communityDAO.getManCommByUID(stuNum);
        return communities;
    }


    public int getCountByUser(User user){
        return communityDAO.getCountByUser(user);
    }

    public String getIdenByNum(String stuNum,String cNum) {
        return communityDAO.getIdenByNum(stuNum,cNum);
    }

    public boolean doDeleteMum(String cNum,String stuNum) {
        if (communityDAO.deleteUserFromComm(cNum,stuNum)>0){
            return true;
        }
        else return false;
    }

    public boolean createApply(Community comm)
    {
        return communityDAO.createApply(comm);
    }

}
