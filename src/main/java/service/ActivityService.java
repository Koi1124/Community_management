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

    public List<Activity> getAcByComm(String cName){
        List<Activity> activities=activityDAO.getActByComm(cName);
        return activities;
    }

    public  Activity getAcByID(String aNum){
        Activity activity=activityDAO.getActByNum(aNum);
        return activity;
    }

    public boolean isOperate() {
        return false;
    }

}
