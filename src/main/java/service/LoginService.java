package service;

import dao.UserDAO;
import dao.UserDAOImp;
import model.User;

public class LoginService {


    UserDAO userDAO= new UserDAOImp();
    public boolean doLogin(User user){
        if (userDAO.addUser(user)>0){
            return true;
        }else {
            return false;
        }
    }
}
