package com.ssa.cms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    /**
     *
     * @return @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getRxMobileConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://compliancerewards.ssasoft.com/rxmobil_db", "rxmobil_user", "844TqmpJjdCj");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw sqle;
        }
        return connection;
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public static void closeStatement(Statement preparedStatement) {

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    public static void closeConnection(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
