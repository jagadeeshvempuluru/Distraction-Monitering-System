package com.distraction.test;

import java.sql.Connection;
import com.distraction.util.DBConnection;

public class TestDB {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAILED");
        }
    }
}