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
    int updateCommunity(Community community);

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



    String getCommIDByCName(String cName);

}
