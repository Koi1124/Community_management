package dao;

import model.*;

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
    int updateView(Activity activity);
    
    
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

    List<Activity> getActByKeyword(String keyword);

    /**
     *@Discription: 添加活动留言
     *@Param: [actComment]
     *@Return: int
     *@Author:
     */
    int addComment(ActComment actComment);



    /**
     *@Discription: 根据活动号获取留言列表
     *@Param: [aNum]
     *@Return: java.util.List<model.ActComment>
     *@Author:
     */
    List<ActComment> getCommentByANum(String aNum);


    /**
     *@Discription: 添加回复
     *@Param: [actReply]
     *@Return: int
     *@Author:
     */
    int addReply(ActReply actReply);


    /**
     *@Discription: 根据活动评论号获取回复列表
     *@Param: [acNum]
     *@Return: java.util.List<model.ActComment>
     *@Author:
     */
    List<ActReply> getReplyByUNum(String acNum);


    /**
     *@Discription: 根据活动号获取评论数
     *@Param: [aNum]
     *@Return: int
     *@Author:
     */
    int getCommentCount(String aNum);


}
