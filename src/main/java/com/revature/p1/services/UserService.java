package com.revature.p1.services;

import com.revature.p1.daos.ErsUserDao;
import com.revature.p1.dtos.requests.NewLoginRequest;
import com.revature.p1.dtos.requests.NewUserRequest;
import com.revature.p1.dtos.responses.Principal;
import com.revature.p1.models.ErsUser;
import com.revature.p1.models.Role;
import com.revature.p1.utils.custom_exeptions.InvalidUserException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserService {
    private final ErsUserDao ersUserDao;

    public UserService(ErsUserDao ersUserDao) {
        this.ersUserDao = ersUserDao;
    }

    public ErsUser signUp(NewUserRequest req) {
        ErsUser createdUser = new ErsUser(UUID.randomUUID().toString(), req.getUserName(), req.getEmail(), req.getPassword1(), req.getGivenName(), req.getSurName(), Role.DEFAULT);
        ersUserDao.save((createdUser));
        return createdUser;
    }

    public Principal login(NewLoginRequest req) {
        ErsUser validUser = ErsUserDao.getUserByUsernameAndPassword(req.getUserName(), req.getPassword());
        if (validUser == null) throw new com.revature.p1.utils.custom_exceptions.InvalidAuthException("Invalid username or password");
        return new Principal(validUser.getUser_id(), validUser.getUsername(), validUser.getRole(),"");
    }

    public List<ErsUser> getAllUsers(){
        return ersUserDao.findAll();
    }

    public List<ErsUser> getAllUsersByUserName(String userName){
        return ersUserDao.getAllUsersByUsername(userName);
    }

    public void saveUser(NewUserRequest req) throws SQLException {
        List<String> userNames = ersUserDao.findAllUserNames();
        if (!isValidUsername(req.getUserName())){
            throw new InvalidUserException("User name is not 8-20 characters long!");
        } if (userNames.contains(req.getUserName())){
            throw new InvalidUserException("That user name is take by some one else!");
        } if (!isValidPassword(req.getPassword1())) {
            throw new InvalidUserException("Password must be a minimum of eight characters, and at least one letter, and one number!");
        } if (!req.getPassword1().equals(req.getPassword2())) {
            throw new InvalidUserException("The password you provided do not match!");
        }
    }

    public boolean isValidUsername(String userName) {
        return userName.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }
    public boolean isDuplicateUsername(String userName) throws SQLException {
        List<String> userNames = ersUserDao.findAllUserNames();
        return userNames.contains(userName);
    }
    public boolean isSamePassword(String password1, String password2) {
        return password1.equals(password2);
    }

    // Minimum eight characters, at least one letter and one number:
    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
