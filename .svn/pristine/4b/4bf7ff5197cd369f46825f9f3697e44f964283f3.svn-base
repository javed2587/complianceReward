package com.ssa.cms.service;

import com.ssa.cms.dao.DashboardDAO;
import com.ssa.cms.model.DailyRedemption;
import com.ssa.cms.model.Dashboard;
import com.ssa.cms.model.Pharmacy;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author msheraz
 */
@Service
@Transactional
public class DashboardService {

    @Autowired
    private DashboardDAO dashboardDAO;
    private static final Log logger = LogFactory.getLog(DashboardService.class.getName());

    public List<DailyRedemption> getTopTenPrescribers() {
        
        List<DailyRedemption> top10Prescribers = new ArrayList<>();
        try {
            top10Prescribers = dashboardDAO.getTopTenPrescribers();
            for(DailyRedemption dailyRedemption : top10Prescribers) {
                dailyRedemption.setTotalPatientOptins(dailyRedemption.getTotalPatientOptins().subtract(dailyRedemption.getTotalRxTransactions()));
            }
        } catch (Exception e) {
            logger.error("Exception: DashboardService -> getTopTenPrescribers", e);
        }
        return top10Prescribers;
    }
    
    public List<Pharmacy> getTopTenPharmacies() {
        
        List<Pharmacy> top10Pharmacies = new ArrayList<>();
        try {
            top10Pharmacies = dashboardDAO.getTopTenPharmacies();     
        } catch (Exception e) {
            logger.error("Exception: DashboardService -> getTopTenPharmacies", e);
        }
        return top10Pharmacies;
    }
    
    public Dashboard getAllProgramData() {
        
        Dashboard dashboard = new Dashboard();
        try {
            dashboard = dashboardDAO.getAllProgramData();
        } catch (Exception e) {
            logger.error("Exception: DashboardService -> getAllProgramData", e);
        }
        return dashboard;
    }
}
