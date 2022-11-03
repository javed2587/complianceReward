package com.ssa.cms.dao;

import com.ssa.cms.model.CMSDocuments;
import com.ssa.cms.model.CMSPageContent;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zubair
 */
@Repository
@Transactional
public class CMSDAO extends BaseDAO implements Serializable {

    public Object get(Object type, Integer id) {
        Object obj = (Object) this.getCurrentSession().get(type.getClass(), id);
        return obj;
    }

    public boolean deleteRecord(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("Delete From CMSDocuments cmsDocuments where cmsDocuments.id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() != 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean isCMSDocumentTitleExist(String title, Integer id) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(CMSDocuments.class);
        if (!title.isEmpty()) {
            filterCriteria.add(Restrictions.eq("title", title));
        }
        if (id != null) {
            filterCriteria.add(Restrictions.ne("id", id));
        }
        return filterCriteria.uniqueResult() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    public CMSPageContent getCMSPageContentByPageId(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("from CMSPageContent pageContent left join fetch pageContent.cMSPages cmsPages where cmsPages.id=:id");
        query.setParameter("id", id);
        return (CMSPageContent) query.uniqueResult();
    }
}
