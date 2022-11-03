package com.ssa.cms.model;

import com.ssa.cms.util.AppUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author msheraz
 */
public class OrderIdGenerator implements IdentifierGenerator {

    private static final Log logger = LogFactory.getLog(OrderIdGenerator.class.getName());

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        Order order = (Order) object;
        String communicationMethod = "";
        String result = null;
        try {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        Connection connection = session.connection();
//            PreparedStatement ps1 = connection.prepareStatement("SELECT Communication_Method from DailyRedemption where Transaction_Number = ?");
//            ps1.setString(1, order.getTransactionNo());
//            ResultSet rs1 = ps1.executeQuery();
//            if (rs1.next()) {
//                communicationMethod = rs1.getString(1);
//            }
//            
            int count = 0;
            String id="";
            String nextId=""; 
            String sql="SELECT MAX(id) AS id FROM(SELECT  (id * 1) AS id FROM Orders WHERE id LIKE ?) AS a ";
            PreparedStatement ps = connection.prepareStatement(sql);//"SELECT count(*) from Orders where Id like ?");
            ps.setString(1, date + "%");
            logger.info("Query generated: " + ps);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //count = rs.getInt(1);
                id = rs.getString(1);
                //logger.info("Count: "+ count);
                logger.info("max id: "+ id);
            }
            if(AppUtil.getSafeStr(id, "").length()>0)
            {
                String idPart=id.substring(8);
                int nextNum= AppUtil.getSafeInt(idPart, 0)+1;
                nextId=date+nextNum;
            }
            else
            {
                nextId=date+"1";
            }
            result =nextId; //date+nextId; //+ communicationMethod + (count + 1);
            logger.info("Generated Order Id: "+ result);
        } catch (SQLException e) {
            logger.error(e);
            throw new HibernateException("Unable to generate Order Id Sequence");
        }
        return result;
    }
}
