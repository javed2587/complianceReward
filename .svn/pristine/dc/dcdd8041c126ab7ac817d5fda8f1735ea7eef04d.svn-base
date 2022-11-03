package com.ssa.cms.dao;

import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrescriberJdbcDAO {

    public static void populatePrescriberValues(InstantRedemption redemptionFile, Connection connection) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT "
                + "prescriberNPI.Provider_First_Name, "
                + "prescriberNPI.Provider_Last_Name__Legal_Name_,"
                + "prescriberNPI.Provider_Business_Mailing_Address_Telephone_Number, "
                + "prescriberNPI.Provider_Business_Mailing_Address_Fax_Number "
                + "FROM PrescriberNPI prescriberNPI "
                + "WHERE "
                + "prescriberNPI.NPI = ? ";

        try {

            String prescriberNPI = redemptionFile.getPrescriberNpi();

            if (prescriberNPI == null || prescriberNPI.trim().length() == 0) {
                System.out.println("Prescriber not found.");
                return;
            }

            prescriberNPI = prescriberNPI.trim();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, prescriberNPI);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                redemptionFile.setPrescriberFirstName(resultSet.getString("Provider_First_Name"));
                redemptionFile.setPrescriberLastName(resultSet.getString("Provider_Last_Name__Legal_Name_"));
                redemptionFile.setPrescriberFax(resultSet.getString("Provider_Business_Mailing_Address_Fax_Number"));
                redemptionFile.setPrescriberPhone(resultSet.getString("Provider_Business_Mailing_Address_Telephone_Number"));

            } else {
                System.out.println("No prescriber values found with NPI : " + prescriberNPI);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
    }

    public static void populateDRFPrescriberValues(DailyRedemption dailyRedemption, Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT "
                + "prescriberNPI.Provider_First_Name, "
                + "prescriberNPI.Provider_Last_Name__Legal_Name_,"
                + "prescriberNPI.Provider_Business_Mailing_Address_Telephone_Number, "
                + "prescriberNPI.Provider_Business_Mailing_Address_Fax_Number "
                + "FROM PrescriberNPI prescriberNPI "
                + "WHERE "
                + "prescriberNPI.NPI = ? ";

        try {

            String prescriberNPI = dailyRedemption.getPrescriberNpi();

            if (prescriberNPI == null || prescriberNPI.trim().length() == 0) {
                System.out.println("Prescriber not found.");
                return;
            }

            prescriberNPI = prescriberNPI.trim();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, prescriberNPI);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                dailyRedemption.setPrescriberFirstName(resultSet.getString("Provider_First_Name"));
                dailyRedemption.setPrescriberLastName(resultSet.getString("Provider_Last_Name__Legal_Name_"));
                dailyRedemption.setPrescriberPhone(resultSet.getString("Provider_Business_Mailing_Address_Telephone_Number"));

            } else {
                System.out.println("No prescriber values found with NPI : " + prescriberNPI);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
    }
}
