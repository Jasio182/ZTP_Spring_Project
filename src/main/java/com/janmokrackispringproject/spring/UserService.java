package com.janmokrackispringproject.spring;

import com.janmokrackispringproject.beans.User;
import com.janmokrackispringproject.helpers.dataaccess.UserDbAccess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService {

    private UserDbAccess userDbAccess;
    private HashMap<String, User> users;

    public UserService() {
        this.userDbAccess = new UserDbAccess();
        this.users = new HashMap<>();
        populateUsers();
    }

    private void populateUsers()
    {
        try {
            ArrayList<User> usersArr = userDbAccess.GetUsers();
            for(User user : usersArr) {
                this.users.put(user.getLogin(), user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public User getUser(String username) {
        return this.users.get(username);
    }
}
