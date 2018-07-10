package dao;

import model.Community;
import model.User;

import java.util.List;

public interface UserDAO {
    /**
     *@Discription: 根据用户名和密码检查登录
     *@Param: [name, password]
     *@Return: model.User
     *@Author: 
     */
    boolean checkUser(User user);



    boolean isHave(User user);

   /**
    *@Discription: 操作用户，进行增
    *@Param: []
    *@Return: int
    *@Author:
    */
    int addUser(User user);

    /**
     *@Discription: 操作用户，进行改
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int updateUser(User user);
    
    
    /**
     *@Discription: 操作用户，进行删
     *@Param: []
     *@Return: int
     *@Author: 
     */
    int deleteUser();

    /**
     *@Discription: 根据关键词搜索用户信息
     *@Param: [keyword]
     *@Return: model.User
     *@Author:
     */
    User userInformation(String keyword);

    void initUserList(List<User> ulist);
    int deleteUser(String deleteNum);
    void searchUser(List<User>searchList,String uName);
    /**
     *@Discription: 根据用户得到加入社团信息
     *@Param: [stuNum]
     *@Return: java.util.List<model.Community>
     *@Author:
     */
    List<Community> getCommunityInfo(String stuNum);
    
    /**
     *@Discription: 根据用户号得到用户信息(社团详情页用)
     *@Param: [stuNum]
     *@Return: model.User
     *@Author: 
     */
    User getUserInfoByID(String stuNum);


}
