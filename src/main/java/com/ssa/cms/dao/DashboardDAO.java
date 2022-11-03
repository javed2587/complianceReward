package com.ssa.cms.dao;

import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.Dashboard;
import com.ssa.cms.model.Pharmacy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msheraz
 */
@Repository
@Transactional
public class DashboardDAO  extends BaseDAO{

   

    public List<DailyRedemption> getTopTenPrescribers() {

        List<DailyRedemption> top10Prescribers = new ArrayList<>();

        String sql = "SELECT "
                + "COUNT(DISTINCT (o.`Id`)) AS Rx_Transactions,"
                + "(SELECT COUNT(*) FROM DailyRedemption WHERE Prescriber_NPI = drf.Prescriber_NPI) AS Offer_Sent,"
                + "SUM(dr.`AWP_Current_Pkg_Price`) AS AWP,"
                + "drf.`Prescriber_NPI` AS NPI,"
                + "drf.`Prescriber_Last_Name` AS `Last`,"
                + "drf.`Prescriber_First_Name` AS `First`,"
                + "drf.`Prescriber_Specialty` AS Specialty,"
                + "drf.`Prescriber_Zip_Code` AS Zip_Code "
                + "FROM "
                + "Orders o "
                + "INNER JOIN DailyRedemption drf "
                + "ON o.`TransactionNumber` = drf.`Transaction_Number` "
                + "INNER JOIN OrderStatus os "
                + "ON o.`OrderStatus` = os.`Id` "
                + "WHERE drf.`Claim_Status` = 0 "
                + "AND TIMESTAMPDIFF(DAY, o.`ModifiedOn`, NOW()) <= 30 "
                + "GROUP BY drf.`Prescriber_NPI` "
                + "ORDER BY COUNT(drf.`Prescriber_NPI`) DESC "
                + "LIMIT 10";

        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

        List<Object[]> queryResult = query.list();

        DailyRedemption dailyRedemption = null;
        Object value = null;

        for (Object[] record : queryResult) {
            dailyRedemption = new DailyRedemption();

            value = record[0];
            if (value != null) {
                dailyRedemption.setTotalRxTransactions((BigInteger) value);
            }

            value = record[1];
            if (value != null) {
                dailyRedemption.setTotalPatientOptins((BigInteger) value);
            }

            value = record[2];
            if (value != null) {
                dailyRedemption.setSumOfAWP(value.toString());
            }

            value = record[3];
            if (value != null) {
                dailyRedemption.setPrescriberNpi(value.toString());
            }

            value = record[4];
            if (value != null) {
                dailyRedemption.setPrescriberLastName(value.toString());
            }

            value = record[5];
            if (value != null) {
                dailyRedemption.setPrescriberFirstName(value.toString());
            }

            value = record[6];
            if (value != null) {
                dailyRedemption.setPrescriberSpecialty(value.toString());
            }

            value = record[7];
            if (value != null) {
                dailyRedemption.setPrescriberZipCode(value.toString());
            }

            top10Prescribers.add(dailyRedemption);
        }

        return top10Prescribers;
    }

    public List<Pharmacy> getTopTenPharmacies() {

        List<Pharmacy> top10Pharmacies = new ArrayList<>();

        String sql = "SELECT " 
                + "SUM(CASE " 
                + "WHEN os.`Name` ='Filled' THEN 1 "
                + "ELSE 0 "
                + "END) AS FillCount,"
                + "SUM(CASE "
                + "WHEN os.`Name` ='Shipped' THEN 1 "
                + "ELSE 0 "
                + "END) AS Shipped,"
                + "SUM(CASE "
                + "WHEN os.`Name` ='Denied' THEN 1 "
                + "ELSE 0 "
                + "END) AS Denied,"
                + "SUM(awp.`AWP_Current_Pkg_Price`) AS AWP,"
                + "ph.`Title` AS Pharmacy,"
                + "ph.`Zip` AS Zip_Code,"
                + "awp.NonAPI AS NonAPI,"
                + "awp.APICount AS APICount,"
                + "ph.`SalesRep` AS SalesRep "
                + "FROM "
                + "Orders o "
                + "INNER JOIN `Pharmacy` ph "
                + "ON o.`PharmacyId` = ph.`Id` "
                + "INNER JOIN OrderStatus os "
                + "ON o.`OrderStatus` = os.`Id` "
                + "AND os.`Name` IN ('Filled', 'Shipped', 'Denied') "
                + "INNER JOIN (SELECT "
                + "SUM("
                + "CASE "
                + "WHEN dr.`NDCNumber` IS NULL "
                + "THEN 1 "
                + "ELSE 0 "
                + "END "
                + ") AS NonAPI, "
                + "SUM(" 
                + "CASE "
                + "WHEN dr.`NDCNumber` IS NOT NULL "
                + "THEN 1 "
                + "ELSE 0 "
                + "END "
                + ") AS APICount,"
                + "ri.`TransactionNumber`, "
                + "SUM(dr.AWP_Current_Pkg_Price) AS AWP_Current_Pkg_Price "
                + "FROM RedemptionIngredient ri "
                + "INNER JOIN Drug dr  ON ri.`Ndc` = dr.`NDCNumber` "
                + "GROUP BY ri.`TransactionNumber` "
                +") awp "
                + "ON awp.TransactionNumber = o.`TransactionNumber` "
                + "WHERE TIMESTAMPDIFF(DAY, o.`ModifiedOn`, NOW()) <= 30 "
                + "GROUP BY ph.`Id` "
                + "ORDER BY COUNT(ph.`Id`) DESC "
                + "LIMIT 10";

        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

        List<Object[]> queryResult = query.list();

        Pharmacy pharmacy = null;
        Object value = null;

        for (Object[] record : queryResult) {
            pharmacy = new Pharmacy();

            value = record[0];
            if (value != null) {
                pharmacy.setFillCount(((BigDecimal) value).intValue());
            }

            value = record[1];
            if (value != null) {
                pharmacy.setShipCount(((BigDecimal) value).intValue());
            }

            value = record[2];
            if (value != null) {
                pharmacy.setDeninedCount(((BigDecimal) value).intValue());
            }

            value = record[3];
            if (value != null) {
                pharmacy.setAwp((BigDecimal) value);
                
            }

            value = record[4];
            if (value != null) {
                pharmacy.setTitle(value.toString());
            }
            
            value = record[5];
            if (value != null) {
                pharmacy.setZip(((BigInteger) value).longValue());
            }
            
            Integer nonApiCount = 0;
            value = record[6];
            if (value != null) {
                nonApiCount = ((BigDecimal) value).intValue();
            }
            
            double apiCount = 0;
            value = record[7];
            if (value != null) {
                apiCount = ((BigDecimal) value).doubleValue();
            }
            
            pharmacy.setApiToNonApiRatio((apiCount/(apiCount + nonApiCount))*100);
            
            value = record[8];
            if (value != null) {
                pharmacy.setSalesRep(value.toString());
            }
            
            top10Pharmacies.add(pharmacy);
        }

    return top10Pharmacies ;
}

public Dashboard getAllProgramData() {

        String sql = "SELECT " 
                + "COUNT(DISTINCT (o.`Id`)) AS Response,"
                + "(SELECT COUNT(*) FROM DailyRedemption WHERE Prescriber_NPI = drf.Prescriber_NPI AND Claim_Status = 0) AS TotalOffersSent,"
                + "MAX(tab.Rx_INQueue) AS Rx_INQueue,"
                + "MAX(tab.AWP) AS AWP,"
                + "MAX(tab.APICount) AS APICount,"
                + "MAX(tab.NonAPI) AS NonAPI,"
                + "MAX(tab.RebatesPaid) AS RebatesPaid,"
                + "SUM(o.`Payment`) AS Payment_Collected "
                + "FROM "
                + "Orders o "
                + "INNER JOIN DailyRedemption drf "
                + "ON o.`TransactionNumber` = drf.`Transaction_Number` "
                + "INNER JOIN OrderStatus os "
                + "ON o.`OrderStatus` = os.`Id` "
                + "INNER JOIN RedemptionIngredient ri "
                + "ON o.`TransactionNumber` = ri.`TransactionNumber` "
                + "INNER JOIN Drug dr "
                + "ON ri.`Ndc` = dr.`NDCNumber` "
                + "LEFT OUTER JOIN "
                + "(SELECT "
                + "SUM("
                + "CASE "
                + "WHEN dr1.`NDCNumber` IS NULL "
                + "THEN 1 "
                + "ELSE 0 "
                + "END "
                + ") AS NonAPI,"
                + "SUM("
                + "CASE "
                + "WHEN dr1.`NDCNumber` IS NOT NULL "
                + "THEN 1 "
                + "ELSE 0 "
                + "END "
                + ") AS APICount,"
                + "COUNT(DISTINCT (o1.`TransactionNumber`)) AS Rx_INQueue,"
                + "SUM(dr1.`AWP_Current_Pkg_Price`) AS AWP,"
                + "SUM(dr1.`MaxOffer`) AS RebatesPaid,"
                + "o1.`TransactionNumber` AS TransactionNumber "
                + "FROM "
                + "Orders o1 "
                + "INNER JOIN RedemptionIngredient ri1 "
                + "ON o1.`TransactionNumber` = ri1.`TransactionNumber` "
                + "LEFT OUTER JOIN Drug dr1 " 
                + "ON ri1.`Ndc` = dr1.`NDCNumber` "
                + "WHERE OrderStatus IN (1, 2)) tab "
                + "ON tab.`TransactionNumber` = o.`TransactionNumber` "
                + "WHERE drf.`Claim_Status` = 0 "
                + "AND TIMESTAMPDIFF(DAY, o.`ModifiedOn`, NOW()) <= 30";

        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);

        List<Object[]> queryResult = query.list();

        Dashboard dashboard = null;
        Object value = null;

        for (Object[] record : queryResult) {
            dashboard = new Dashboard();

            value = record[0];
            if (value != null) {
                dashboard.setTotalPtResponses(((BigInteger) value).intValue());
            }

            value = record[1];
            if (value != null) {
                dashboard.setTotalRxMessages(((BigInteger) value).intValue());
            }

            value = record[2];
            if (value != null) {
                dashboard.setTotalRxInQueue(((BigInteger) value).intValue());
            }

            value = record[3];
            if (value != null) {
                dashboard.setAwpOfRxInQueue(((BigDecimal) value).doubleValue());
            }

            value = record[4];
            if (value != null) {
                dashboard.setTotalApiIngredients(((BigDecimal) value).intValue());
            }

            value = record[5];
            if (value != null) {
                dashboard.setTotalOtherIngredients(((BigDecimal) value).intValue());
            }
            
            value = record[6];
            if (value != null) {
                dashboard.setTotalRebatesPaid((Double) value);
            }
            
            value = record[7];
            if (value != null) {
                dashboard.setTotalPaymentCollected((Double) value);
            }
        }

        return dashboard;
    }
}
