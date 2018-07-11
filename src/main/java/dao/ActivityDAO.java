package dao;

import model.Activity;
import model.Community;
import model.User;

import java.util.List;

public interface ActivityDAO {


    void initActivityList(List<Activity>alist);
    /**
     *@Discription: 选择数据库中活动信息
     *@Param: []
     *@Return: java.util.List<model.Activity>
     *@Author:
     */
    List<Activity> getActivities();
    
    /**
     *@Discription: 根据社团号获取社团活动集合（社团详情页用）
     *@Param: [cName]
     *@Return: java.util.List<model.Activity>
     *@Author: 
     */
    List<Activity> getActByComm(String cNum);

    /**
     *@Discription: 根据活动号获取社团活动（社团详情页用）
     *@Param: [aNum]
     *@Return: model.Activity
     *@Author:
     */
    Activity getActByNum(String aNum);


    /**
     *@Discription: 操作活动，进行增
     *@Param: []
     *@Return: int
     *@Author:
     */
    int addActivity(Activity activity);
    
    /**
     *@Discription: 操作活动，进行改
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int updateActivity(Activity activity);
    
    
    /**
     *@Discription: 操作活动，进行删
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int deleteActivity(String aNum);

    /**
     *@Discription: 根据活动获取发布人信息
     *@Param: []
     *@Return: model.User
     *@Author:
     */
    Community getCommInfo(String aNum);
}
