package com.services;

import com.doa.UserDao;
import com.enity.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServices {
    Scanner sc = new Scanner(System.in);
    UserDao userDao = new UserDao();

    User u = null;

    public UserServices() throws SQLException {
    }

    public void close() {
        userDao.close();
    }

    public void signUp() throws SQLException {
        boolean mobFlag = true;
        boolean emailFalg = true;
        System.out.print("Enter First Name : ");
        String fname = sc.next();
        System.out.print("Enter Last Name : ");
        String lname = sc.next();
        while (emailFalg) {
            System.out.print("Enter Email : ");
            String email = sc.next();
            if (userDao.checkUser(email)) {
                emailFalg = false;
                System.out.print("Enter Mobile No : ");
                String mob = sc.next();
                while (mobFlag) {
                    if ((mob.length()) == 10) {
                        mobFlag = false;
                        break;
                    }
                    System.out.print("Mobile no must be of 10 digit : ");
                    mob = sc.next();
                }
                System.out.print("Enter Password : ");
                String pass = sc.next();

                User u = new User(fname, lname, email, pass, mob);
                userDao.save(u);
                System.out.println(u.getFirstName() + " " + u.getLastName() + " Successfully Registered");
            } else {
                System.out.println("This email already registered. Please try with different Email id");
            }
        }
    }

    public User signIn() throws SQLException {
        System.out.print("Enter Email id : ");
        String email = sc.next();
        System.out.print("Enter password : ");
        String password = sc.next();
        if ((u = userDao.logIn(email, password)) != null) {
            System.out.println("/-------------------------Welcome " + u.getFirstName() + " " + u.getLastName() + "-------------------------/");
        } else {
            System.out.println("User not registered or Invalid userid or Password");
        }
        return u;
    }

    public int changePassword(int id) throws SQLException {
        System.out.print("Enter New Password : ");
        String npass = sc.next();
        System.out.print("Enter Confirm Password : ");
        String cpass = sc.next();
        int cnt = 0;
        if (npass.equals(cpass)) {
            cnt = userDao.passwordChange(id, npass);
            System.out.println("Password changed successfully");
        } else {
            System.out.println("PassWord does not match");
        }
        return cnt;
    }
}
