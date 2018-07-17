package service;

import dao.ActivityDAO;
import dao.ActivityDAOImp;
import model.ActComment;
import model.ActReply;
import model.Activity;

import java.util.List;

public class ActivityService {
    ActivityDAO activityDAO=new ActivityDAOImp();

    public void initActivityList(List<Activity> alist)
    {
        activityDAO.initActivityList(alist);
    }

    public void addActivity(Activity activity) {
        activityDAO.addActivity(activity);
    }

    public List<Activity> getAcFromDB(){
        List<Activity> activities=activityDAO.getActivities();
        return activities;
    }

    public List<Activity> getAcByComm(String cNum){
        List<Activity> activities=activityDAO.getActByComm(cNum);
        return activities;
    }

    public  Activity getAcByID(String aNum){
        Activity activity=activityDAO.getActByNum(aNum);
        return activity;
    }

    public boolean doDeleteActivity(String aNum) {
        if (activityDAO.deleteActivity(aNum)>0){
            return true;
        }else return false;
    }

    public boolean doAddActivity(Activity activity) {
        if (activityDAO.addActivity(activity)>0) {
            return true;
        }else return false;
    }

    public List<Activity> searchActivity(String keyword){
        List<Activity> activities=activityDAO.getActByKeyword(keyword);
        return activities;
    }

    public boolean doUpdateView(Activity activity){
        if (activityDAO.updateView(activity)>0){
            return true;
        }else return false;
    }

    public boolean doAddComment(ActComment actComment){
        if (activityDAO.addComment(actComment)>0){
            return true;
        }else return false;
    }

    public boolean doAddReply(ActReply actReply){
        if (activityDAO.addReply(actReply)>0){
            return true;
        }else return false;
    }

    public List<ActComment> getCommentByANum(String aNum){
        List<ActComment> actCommentList=activityDAO.getCommentByANum(aNum);
        return actCommentList;
    }

    public List<ActReply> getReplyByACNum(String acNum){
        List<ActReply> actReplyList=activityDAO.getReplyByUNum(acNum);
        return actReplyList;
    }

    public int getCommentCountByANum(String aNum){
        int count=activityDAO.getCommentCount(aNum);
        return count;
    }


}
