package com.utils;

import com.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtils {
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
