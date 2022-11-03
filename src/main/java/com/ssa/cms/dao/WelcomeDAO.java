package com.ssa.cms.dao;

import com.ssa.cms.model.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WelcomeDAO extends BaseDAO implements Serializable {

    private final Log logging = LogFactory.getLog(getClass());

   

    public List<WelcomePOJO> getWelcomeData(String userId) {
        List<WelcomePOJO> pojoList = new ArrayList<WelcomePOJO>();
        List<Campaigns> list;
        try {
            String queryString = "select distinct campaigns from Campaigns campaigns "
                    + "left join campaigns.drugBrands drugBrands "
                    + "left join drugBrands.drugs drugs order by campaigns.campaignId desc";
            list = getCurrentSession().createQuery(queryString).list();

            String sqlQuery = "SELECT GROUP_CONCAT(DISTINCT campaignId) FROM CampaignsHasDrugBrand WHERE drugBrandId IN ("
                    + "  SELECT drugBrandId FROM DrugBrand WHERE brandId IN ("
                    + " SELECT brandId FROM UserBrand WHERE userId = " + userId + "))";

            SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
            List<String> result = query.list();
            List<String> campaignIds = null;
            if (result != null && result.size() > 0) {
                if (result.get(0) != null) {
                    campaignIds = Arrays.asList(result.get(0).split(","));
                }
            }

            WelcomePOJO welcomePOJO;
            for (Campaigns campaigns : list) {
                welcomePOJO = new WelcomePOJO();
                welcomePOJO.setCampaignName(campaigns.getCampaignName());
                String b_and_s = "(";
                int i = 0;
                for (DrugBrand brand : campaigns.getDrugBrands()) {
                    int j = 0;
                    b_and_s = b_and_s + ": "; //brandName
//                    for (Drug drug : brand.getDrugs()) {
//                        b_and_s = b_and_s + drug.getStrength();
//                        if (j == brand.getDrugs().size() - 1) {
//                            if (campaigns.getDrugBrands().size() > 0 && i < campaigns.getDrugBrands().size() - 1) {
//                                b_and_s = b_and_s + ", ";
//                            }
//                        } else {
//                            b_and_s = b_and_s + "/";
//                        }
//                        j++;
//                    }
                    if (i == campaigns.getDrugBrands().size() - 1) {
                        b_and_s = b_and_s + ")";
                    }
                    i++;
                }
                welcomePOJO.setBrandAndStrengths(b_and_s);
                if (campaignIds == null || campaignIds.contains(campaigns.getCampaignId().toString())) {
                    getResultsForCharts(campaigns.getCampaignId(), welcomePOJO);
                    pojoList.add(welcomePOJO);
                }

            }
        } catch (HibernateException e) {
            logging.error("Exception: WelcomeDAO -> getWelcomeData: ", e);
        }
        return pojoList;
    }

    public void getResultsForCharts(Integer campaignId, WelcomePOJO welcomePOJO) {

        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("SELECT COUNT(*) AS cnt FROM CustomerRequest  WHERE Effective_Date >= DATE_SUB(NOW(), INTERVAL 24 HOUR) AND Campaign_Id = ").append(campaignId);
        stringBuilder.append(" UNION ALL ");
        stringBuilder.append(" SELECT COUNT(*) AS cnt FROM InstantRedemption WHERE Effective_Date >= DATE_SUB(NOW(), INTERVAL 24 HOUR) AND CampaignId = ").append(campaignId);

        try {

            SQLQuery query = this.getCurrentSession().createSQLQuery(stringBuilder.toString());

            List result = query.list();
            if (result != null && result.size() > 0) {
                welcomePOJO.setTotalOptIn((BigInteger) result.get(0));
                welcomePOJO.setTotalRedemption((BigInteger) result.get(1));
            }

        } catch (NumberFormatException e) {
            logging.error("Exception: WelcomeDAO -> getResultsForCharts: ", e);
        } catch (HibernateException e) {
            logging.error("Exception: WelcomeDAO -> getResultsForCharts: ", e);
        }
    }
}
