package com.ssa.cms.dao;

import com.ssa.cms.model.DTable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class TableDAO extends BaseDAO{

    public List<Object[]> getTableList() throws Exception {
        return getCurrentSession().createSQLQuery("SELECT *  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'rxdev_ComplianceRewardQA'").list();
    }

}
