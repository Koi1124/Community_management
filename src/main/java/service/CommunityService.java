package service;

import dao.CommunityDAO;
import dao.CommunityDAOImp;
import model.Community;
import model.User;

import java.util.ArrayList;
import java.util.Iterator;
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

    public String getCNameByCommID(String cNum) {
        String cName=communityDAO.getCNameByCommID(cNum);
        return cName;
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

    public int getUStateByNum(String stuNum,String cNum) {
        return communityDAO.getUStateBy(stuNum,cNum);
    }

    public boolean doDeleteMum(String cNum,String stuNum) {
        if (communityDAO.deleteUserFromComm(cNum,stuNum)>0){
            return true;
        }
        else return false;
    }

    public boolean doAgreeMum(String cNum,String stuNum) {
        if (communityDAO.updateUState(cNum,stuNum)>0){
            return true;
        }else return false;
    }

    public boolean doUpdateStuIden(String cNum,String stuNum,String iden) {
        if (communityDAO.updateUIden(cNum,stuNum,iden)>0){
            return true;
        }else return false;
    }

    public boolean doTakeover(String cNum,String adminNum,String leadNum,String leadToMum) {
        if (communityDAO.updateUIden(cNum,leadNum,leadToMum)>0&&communityDAO.takeoverLead(cNum,adminNum,leadNum)>0){
            return true;
        }else return false;
    }


    public boolean doApply(String cNum,String stuNum) {
        if (communityDAO.addMum(cNum,stuNum)>0){
            return true;
        }else return false;

    }

    public boolean createApply(Community comm)
    {
        return communityDAO.createApply(comm);
    }

    public List<Community>  communityInformation(String keyword){
        List<Community> communities=communityDAO. communityInformation(keyword);
        return communities;
    }

    public boolean doReviseSyn(String syn,String cNum){
        if (communityDAO.updateCommunity(syn,cNum)>0){
            return true;
        }else return false;
    }

    public List<Community> commToAudit(){
        List<Community> communities = communityDAO.getCommunities();
        Iterator<Community> it = communities.iterator();
        while(it.hasNext()){
            Community x = it.next();
            if(x.getState()==1){
                it.remove();
            }
        }

        return communities;
    }

    public int deleteComm(String cNum){
        return communityDAO.deleteCommunity(cNum);
    }

    public int changeState(String cNum){
        return communityDAO.changeState(cNum);
    }

    public List<User> getAdministrators(String cNum){
        return communityDAO.getAdministrators(cNum);
    }

}
