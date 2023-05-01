package com.doa;

import com.dbutil.DbUtil;
import com.enity.User;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final PreparedStatement registerUserStmt;
    private final PreparedStatement checkUserStmt;
    private final PreparedStatement logInStmt;
    private final PreparedStatement changePasswordStmt;
    Connection con = null;

    public UserDao() throws SQLException {
        con = DbUtil.getConnection();
        String registerUserSql = "Insert into users(first_name, last_name, email, passwd, mobile) values(?,?,?,?,?)";
        registerUserStmt = con.prepareStatement(registerUserSql);
        String checkUserSql = "select email from users where email=?";
        checkUserStmt = con.prepareStatement(checkUserSql);
        String logInSql = "Select id,first_name,last_name,email,passwd,mobile from users where email= ? and passwd = ?";
        logInStmt = con.prepareStatement(logInSql);
        String changePasswordSql = "update users set passwd = ? where id = ?";
        changePasswordStmt = con.prepareStatement(changePasswordSql);
    }

    public void close() {
        try {
            con.close();
            registerUserStmt.close();
            checkUserStmt.close();
            logInStmt.close();
            checkUserStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(User u) throws SQLException {
        registerUserStmt.setString(1, u.getFirstName());
        registerUserStmt.setString(2, u.getLastName());
        registerUserStmt.setString(3, u.getEmail());
        registerUserStmt.setString(4, u.getPassword());
        registerUserStmt.setString(5, u.getMobile());
        registerUserStmt.executeUpdate();
    }

    public boolean checkUser(String email) throws SQLException {
        checkUserStmt.setString(1, email);
        try (ResultSet rs = checkUserStmt.executeQuery()) {
            if (rs.next()) {
                return false;
            }
        }
        return true;
    }

    public User logIn(String email, String password) throws SQLException {
        logInStmt.setString(1, email);
        logInStmt.setString(2, password);
        try (ResultSet rs = logInStmt.executeQuery()) {
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setMobile(rs.getString("mobile"));
                u.setPassword(rs.getString("passwd"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int passwordChange(int id, String password) throws SQLException {
        changePasswordStmt.setString(1, password);
        changePasswordStmt.setInt(2, id);
        int cnt = changePasswordStmt.executeUpdate();
        return cnt;
    }
}
