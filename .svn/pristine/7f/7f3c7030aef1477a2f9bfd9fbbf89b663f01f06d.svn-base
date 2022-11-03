/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dao;

import com.ssa.cms.model.InstantRedemption;
import com.ssa.cms.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author SSASOFTDHA
 */
public class PharmacyJdbcDAO {
    
    public static boolean populatePharmacyValues(InstantRedemption redemptionFile,Connection connection){
		
		boolean flag = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "SELECT " +
					   "pharmacyNPI.Pharmacy_Name, " +
					   "pharmacyNPI.Pharmacy_Address_1, " +
					   "pharmacyNPI.Pharmacy_Address_2, " +
					   "pharmacyNPI.Pharmacy_City, " +
					   "pharmacyNPI.Pharmacy_State, " +
					   "pharmacyNPI.Pharmacy_Zip_Code, " +
					   "pharmacyNPI.Pharmacy_Phone " +
					   "FROM o2orxssa_Common.PharmacyNPI pharmacyNPI " +
					   "WHERE " +
					   "pharmacyNPI.Pharmacy_NPI = ? ";
		
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, redemptionFile.getPharmacyNpi());
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				redemptionFile.setPharmacyName(resultSet.getString("Pharmacy_Name"));
				redemptionFile.setPharmacyCity(resultSet.getString("Pharmacy_City"));
				redemptionFile.setPharmacyState(resultSet.getString("Pharmacy_State"));
				redemptionFile.setPharmacyZipCode(resultSet.getString("Pharmacy_Zip_Code"));
				redemptionFile.setPharmacyAddress1(resultSet.getString("Pharmacy_Address_1"));
				redemptionFile.setPharmacyPhone(resultSet.getString("Pharmacy_Phone"));				
			}
			
			
		}catch(Exception e){
			flag = false;
                        e.printStackTrace();
		}finally{
			DBUtil.closeResultSet(resultSet);
			DBUtil.closeStatement(preparedStatement);
		}
		
		return flag;
	}
}
