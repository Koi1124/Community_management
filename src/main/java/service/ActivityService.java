package service;

import dao.ActivityDAO;
import dao.ActivityDAOImp;
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


}
