package com.tester;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Functionalities.mainMenuSwitch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
