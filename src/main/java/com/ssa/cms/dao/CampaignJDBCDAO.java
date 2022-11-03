
package com.ssa.cms.dao;

import com.ssa.cms.model.Keyword;
import com.ssa.cms.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class CampaignJDBCDAO {
    
    
    public int persist21200Keyword(Keyword keyword,Connection connection){
        
        int count = 0;
        
        PreparedStatement preparedStatement = null;
        
        String query = "INSERT INTO Text2ExtremeDB.Keyword(Keyword_Code, Program_Code, User_Id, Narrative, Keyword_FWD_URL, " +
                       "Effective_Date, End_Date, Session_ID, Send_WM, Send_TCM,Send_Enrollment, Yes_Response_Count, Send_Anonymous_response) " +
                       "VALUES(?,?,?,?,?,NOW(),NULL,0,?,?,?,?,?) ";
        
        try{
            
            preparedStatement = connection.prepareCall(query);
            
            int index = 1;
            
            preparedStatement.setString(index++, keyword.getKeyCode());
            preparedStatement.setString(index++, keyword.getProgramCode());
            preparedStatement.setInt(index++, keyword.getUserId());
            preparedStatement.setString(index++, keyword.getNarrative());
            preparedStatement.setString(index++, keyword.getForwardURL());
            preparedStatement.setString(index++, keyword.getSendWM());
            preparedStatement.setString(index++, keyword.getSendTCM());
            preparedStatement.setString(index++, keyword.getSendEnrollment());
            preparedStatement.setInt(index++, keyword.getYesResponseCount());
            preparedStatement.setString(index++, keyword.getSendAnonymousResponse());
            
            preparedStatement.executeUpdate();
            
            count = preparedStatement.getUpdateCount();
            
            System.out.println("Record Save Count : " + count);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeStatement(preparedStatement);
        }
        
        return count;
    }
    
    public void delete21200Keyword(String keyword,Connection connection){
        
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Text2ExtremeDB.Keyword WHERE Keyword_Code = '"+ keyword + "'";
        try{
            preparedStatement = connection.prepareCall(query);
            preparedStatement.executeUpdate();
            preparedStatement.getUpdateCount();            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeStatement(preparedStatement);
        }        
    }
}
