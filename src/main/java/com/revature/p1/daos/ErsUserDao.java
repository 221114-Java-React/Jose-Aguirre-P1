package com.revature.p1.daos;

import com.revature.p1.models.ErsUser;
import com.revature.p1.models.Role;
import com.revature.p1.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ErsUserDao implements CrudDAO<ErsUser>{
    @Override
    public void save(ErsUser obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("insert into ers_users (user_id, username, email, password, givenname, surname, role_id) values (?, ?, ?, ?, ?, ?, ?::role)");
            ps.setString(1, obj.getUser_id());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());
            ps.setString(5, obj.getGivenName());
            ps.setString(6, obj.getSurName());
            ps.setString(7, String.valueOf(obj.getRole()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ErsUser obj) {

    }
    @Override
    public void update(ErsUser obj) {

    }
    @Override
    public List<ErsUser> findAll(){
        List<ErsUser> ersUser = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ErsUser currentUser = new ErsUser(rs.getString("user_id"), rs.getString("username"),rs.getString("email"), rs.getString("password"),
                        rs.getString("givenName"), rs.getString("surName"), Role.valueOf(rs.getString("role_id")));// Make role roleId
                ersUser.add(currentUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ersUser;
    }

    public List<String> findAllUserNames() throws SQLException {
        List<String> userName = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select (userName) from ers_users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String currentUsername = rs.getString("userName");
                userName.add(currentUsername);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }

    @Override
    public ErsUser findById() {
        return null;
    }
    public static ErsUser getUserByUsernameAndPassword(String userName, String password) {
         ErsUser ersUser = null;
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM P1.ERS_USERS WHERE username = ? AND password = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ersUser = new ErsUser(rs.getString("user_id"), rs.getString("username"),rs.getString("email"), rs.getString("password"),
                        rs.getString("givenName"), rs.getString("surName"), Role.valueOf(rs.getString("role_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ersUser;
    }

    public List<ErsUser> getAllUsersByUsername(String userName) {
        List<ErsUser> ersUsers = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ERS_USERS WHERE userName LIKE ?");
            ps.setString(1, userName+ "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ErsUser currentUser = new ErsUser(rs.getString("user_id"), rs.getString("username"),rs.getString("email"), rs.getString("password"),
                        rs.getString("givenName"), rs.getString("surName"), Role.valueOf(rs.getString("role_id")));
                ersUsers.add(currentUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ersUsers;
    }
}
