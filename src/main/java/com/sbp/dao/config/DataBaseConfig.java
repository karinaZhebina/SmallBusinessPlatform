package com.sbp.dao.config;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConfig {

  private final String URL = "jdbc:postgresql://localhost/";
  private final String USERNAME = "postgres";
  private final String DATABASE_NAME = "SmallBusinessPlatform";
  private final String DRIVER = "org.postgresql.Driver";

  private boolean driverIsLoaded = false;

  public DataBaseConfig() {
  }

  public Connection getConnection() throws SQLException {
    loadJdbcDriver();
    Connection connection;
    Properties properties = new Properties();
    properties.setProperty("user", USERNAME);
    String PASSWORD = "root";
    properties.setProperty("password", PASSWORD);
    connection = DriverManager.getConnection(URL + DATABASE_NAME, properties);
    return connection;
  }

  private void loadJdbcDriver() {
    if (!driverIsLoaded) {
      try {
        Class.forName(DRIVER);
        DriverManager.registerDriver(new Driver());
        driverIsLoaded = true;
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
