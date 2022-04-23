package com.janmokrackispringproject.helpers.dataaccess;

import com.janmokrackispringproject.beans.Book;
import com.janmokrackispringproject.beans.Role;
import com.janmokrackispringproject.beans.User;
import com.janmokrackispringproject.helpers.EncryptionMethods;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDbAccess extends DbAccess {

    public ArrayList<User> GetUsers() throws Exception {
        Statement statement = GetConnection().createStatement();
        String query = "SELECT * FROM Users";
        ResultSet result = statement.executeQuery(query);
        ArrayList<User> users = new ArrayList<User>();
        while (result.next()) {
            users.add(new User(result.getString("login"), result.getString("pass"), Role.values()[result.getInt("role")]));
        }
        return users;
    }
}