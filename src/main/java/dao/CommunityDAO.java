package dao;

import model.Community;
import model.User;

import java.util.List;

public interface CommunityDAO {
    
    
    /**
     *@Discription: 操作社团，进行增
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int addCommunity(Community community);

    /**
     *@Discription: 操作社团，进行改
     *@Param: []
     *@Return: int
     *@Author:
     */
    int updateCommunity(String syn,String cNum);

    /**
     *@Discription: 操作社团，进行删
     *@Param: []
     *@Return: int
     *@Author:
     */
    int deleteCommunity(String cNum);
    
    /**
     *@Discription: 根据关键词搜索社团，模糊查询
     *@Param: [keyword]
     *@Return: model.Community
     *@Author: 
     */
    List<Community> communityInformation(String keyword);


    /**
     *@Discription: 根据用户ID获取用户加入的社团列表
     *@Param: [username]
     *@Return: model.Community
     *@Author:
     */
    List<Community> getCommByUID(String stuNum);


    /**
     *@Discription: 选择数据库中数据
     *@Param: []
     *@Return: java.util.List<model.Community>
     *@Author:
     */
    List<Community> getCommunities();

    boolean isHave(Community comm);    //检查该社团是否存在

    boolean createApply(Community comm);    //提交社团申请
    
    /**
     *@Discription: 根据社团ID获得此社团信息
     *@Param: [cNum]
     *@Return: model.Community
     *@Author: 
     */
    Community getCommByID(String cNum);


    /**
     *@Discription: 根据社团获取社团成员信息（社团详情页用）
     *@Param: [cNum]
     *@Return: java.util.List<model.User>
     *@Author:
     */
    List<User> getMumbers(String cNum);


    String getCNameByCommID(String cNum);

    /**
     *@Discription: 根据学号获取学生管理的社团列表（管理社团页用）
     *@Param: [stuNum]
     *@Return: java.util.List<model.Community>
     *@Author:
     */
    List<Community> getManCommByUID(String stuNum);
    
    
    /**
     *@Discription: 根据社团号与学号删除指定社团的用户（管理社团页用）
     *@Param: [cNum, stuNum]
     *@Return: int
     *@Author: 
     */
    int deleteUserFromComm(String cNum,String stuNum);
    
    /**
     *@Discription: 根据社团号与学号更改用户状态（管理社团页用）
     *@Param: [cNum, stuNum]
     *@Return: int
     *@Author: 
     */
    int updateUState(String cNum,String stuNum);
    
    /**
     *@Discription: 根据社团号与学号更改用户身份（管理社团页用）
     *@Param: [cNum, stuNum, iden]
     *@Return: int
     *@Author: 
     */
    int updateUIden(String cNum,String stuNum,String iden);

    /**
     *@Discription: 根据社团号与学号更改社团表中团长号（管理社团页用）
     *@Param: [cNum, stuNum]
     *@Return: int
     *@Author:
     */
    int takeoverLead(String cNum,String adminNum,String leadNum);

    /**
     *@Discription: 根据用户获取用户加入社团数量
     *@Param: [user]
     *@Return: int
     *@Author:
     */
    int getCountByUser(User user);
    
    /**
     *@Discription: 根据学号和社团号获取用户身份
     *@Param: [stuNum, cNum]
     *@Return: java.lang.String
     *@Author: 
     */
    String getIdenByNum(String stuNum,String cNum);

    /**
     *@Discription: 根据学号和社团号获取用户状态
     *@Param: [stuNum, cNum]
     *@Return: int
     *@Author:
     */
    int getUStateBy(String stuNum,String cNum);




    /**
     *@Discription: 根据社团号和学号添加用户（加入社团页用）
     *@Param: [cNum, stuNum]
     *@Return: int
     *@Author: 
     */
    int addMum(String cNum,String stuNum);


    int changeState(String cNum);
}
