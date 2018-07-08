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


}
