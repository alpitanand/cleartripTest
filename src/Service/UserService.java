package Service;

import DAO.UserDatabase;
import Model.Center;
import Model.User;

import java.util.List;

public class UserService {
    UserDatabase userDatabase = new UserDatabase();
    List<User> userList = userDatabase.getUsers();

    public void addUser(String userName){
        userList.add(new User(userName));
    }

    public User getUser(String userName){
        for(User user: userList){
            if(user.getUserName().equals(userName))
                return user;
        }
        return null;
    }

}
