package com.ssa.cms.delegate;

import com.ssa.cms.dao.WelcomeDAO;
import com.ssa.cms.model.WelcomePOJO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WelcomeService {

    @Autowired
    private WelcomeDAO welcomeDAO;
    private final Log logger = LogFactory.getLog(getClass());

    public List<WelcomePOJO> getWelcomeData(String userId) {
        List<WelcomePOJO> list = new ArrayList<>();
        try {
            list = welcomeDAO.getWelcomeData(userId);
        } catch (Exception e) {
            logger.error("Exception: WelcomeService -> getWelcomeData", e);
        }
        return list;
    }
}
