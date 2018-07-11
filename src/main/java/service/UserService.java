package service;

import dao.UserDAO;
import dao.UserDAOImp;
import model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO=new UserDAOImp();

    public User getUserByID(String stuNum) {
        User user=userDAO.getUserInfoByID(stuNum);
        return user;
    }
    public boolean isHave(User userI)  //用户不存在返回false  存在返回true
    {
        return userDAO.isHave(userI);
    }
    public boolean doLogin(User userI)
    {
        return userDAO.checkUser(userI);

    }
    public boolean doRegister(User userI){
        userDAO.addUser(userI);
        return true;
    }

    public boolean doRevise(User userI) {

        User user=userDAO.getUserInfoByID(userI.getStuNum());
        if (userI.getuName().equals(user.getuName())) {
            userDAO.updateUser(userI);
            return true;
        }else{
            if (isHave(userI)){
                return false;
            }else{
                userDAO.updateUser(userI);
                return true;
            }
        }
    }

    public void searchUser(List<User> searchList, String uName)
    {
        userDAO.searchUser(searchList,uName);

    }
    public void deleteUser(String deleteNum)
    {
        userDAO.deleteUser(deleteNum);
    }
    public void initUserList(List<User>ulist)
    {
        userDAO.initUserList(ulist);
    }
    public List<User> searchUser(String keyword){
        List<User> users=userDAO.userInformation(keyword);
        return users;
    }

     public String getStuName(String stuNum){
        return userDAO.getStuName(stuNum);
    }

}
