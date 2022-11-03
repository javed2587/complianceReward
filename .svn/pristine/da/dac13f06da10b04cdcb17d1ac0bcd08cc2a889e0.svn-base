package com.ssa.cms.dao;

import com.ssa.cms.model.CMSDocuments;
import com.ssa.cms.model.CMSPageContent;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.WidgetLog;
import com.ssa.cms.util.CommonUtil;
import java.io.Serializable;
import java.util.List;
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
public class ConsumerPortalDAO extends BaseDAO implements Serializable {

    public List<CMSDocuments> getDocumentsList(int index) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(CMSDocuments.class);
        return criteria.setMaxResults(index).list();
    }

    public List<CMSDocuments> getCMSDocumentsByTitle(String title) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(CMSDocuments.class);
        if (title != null && !"".equals(title)) {
            criteria.add(Restrictions.like("title", "%" + title + "%"));
        }
        return criteria.list();
    }

    public List<CMSPageContent> getCMSPageContents() throws Exception {
        return getCurrentSession().createQuery("Select distinct cpc From CMSPageContent cpc left join fetch cpc.cMSPages cMSPages ").list();
    }

    public List<SmtpServerInfo> getSmtpServerInfos() throws Exception {
        return getCurrentSession().createQuery("From SmtpServerInfo smtpInfo ").list();
    }

    public SmtpServerInfo getSmtpServerInfoById(Integer id) throws Exception {
        SmtpServerInfo info = new SmtpServerInfo();
        Query query = getCurrentSession().createQuery("From SmtpServerInfo smtpInfo where smtpInfo.smtpId=:id");
        query.setParameter("id", id);
        Object object = query.uniqueResult();
        if (object != null) {
            info = (SmtpServerInfo) object;
        }
        return info;
    }

    public List<CMSDocuments> getCMSDocumentsList() throws Exception {
        return getCurrentSession().createQuery("From CMSDocuments cmsDocuments ").list();
    }

    public List<WidgetLog> getWidgetLog(String communicationMethod) throws Exception {
        Query query = getCurrentSession().createQuery("From WidgetLog widgetlog where widgetlog.communicationMethod=:communicationMethod order by widgetlog.effectiveDate desc");
        query.setParameter("communicationMethod", communicationMethod);
        return query.list();
    }

    public List<DrugDetail> getDrugDetailsList(Long drugGCN, String dosageForm, String brandname, String drugStrength) throws Exception {
        String hql = "From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic left join fetch drugBasic.drugGeneric drugGeneric where ";

        hql = fetchByAnyRecord(dosageForm, brandname, drugStrength, hql, drugGCN);
        Query query = getCurrentSession().createQuery(hql);
        if (drugGCN != null && drugGCN > 0) {
            query.setParameter("drugGCN", drugGCN);
        }
        if (CommonUtil.isNotEmpty(dosageForm)) {
            query.setParameter("dosageForm", dosageForm);
        }
        if (CommonUtil.isNotEmpty(brandname)) {
            query.setParameter("brandName", brandname);
        }
        if (CommonUtil.isNotEmpty(drugStrength)) {
            query.setParameter("drugStrength", drugStrength);
        }

        return query.list();
    }
    
    public List<DrugDetail> getDrugDetailsListbyGCNs(List drugGCN) throws Exception {
        String hql = "From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic "
                    +" left join fetch drugBasic.drugGeneric drugGeneric where drugDetail.drugGCN in (:drugGCN)";

       
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("drugGCN", drugGCN);
        return query.list();
    }
    ///////////////////////////////////////////////////
    public List<DrugDetail> getDrugDetailsListbyId(Long id) throws Exception {
        String hql = "From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic "
                    +" left join fetch drugBasic.drugGeneric drugGeneric where drugDetail.drugDetailId =:id";

       
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }
    ///////////////////////////////////////////////////
     public List<DrugDetail> getDrugDetailsListbyBrandName(String drugName) throws Exception {
        String hql = "From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic "
                    +" left join fetch drugBasic.drugGeneric drugGeneric where drugBasic.brandName like :drugName)";

       
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("drugName", "%"+drugName+"%");
        return query.list();
    }
    ///////////////////////////////////////////////////
     public List<DrugDetail> getDrugDetailsListbyGenericName(String drugName) throws Exception {
        String hql = "From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic "
                    +" left join fetch drugBasic.drugGeneric drugGeneric where drugGeneric.genericName like :drugName)";

       
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("drugName", "%"+drugName+"%");
        return query.list();
    }
     
    ///////////////////////////////////////////////////

    public String fetchByAnyRecord(String dosageForm, String brandname, String drugStrength, String hql, Long drugGCN) {
        if (!CommonUtil.isNullOrEmpty(drugGCN) && CommonUtil.isNotEmpty(dosageForm) && CommonUtil.isNotEmpty(brandname) && CommonUtil.isNotEmpty(drugStrength)) {
            hql += "drugDetail.drugGCN=:drugGCN and drugDetail.drugForm.formDescr=:dosageForm and drugBasic.brandName=:brandName and drugDetail.strength=:drugStrength";
            return hql;
        } else if (CommonUtil.isNotEmpty(dosageForm) && CommonUtil.isNotEmpty(brandname) && CommonUtil.isNotEmpty(drugStrength)) {
            return hql += "drugDetail.drugForm.formDescr=:dosageForm and drugBasic.brandName=:brandName and drugDetail.strength=:drugStrength group by drugBasic.brandName";
        } else if (CommonUtil.isNotEmpty(brandname)) {
            return hql += "drugBasic.brandName=:brandName";
        } else if (!CommonUtil.isNullOrEmpty(drugGCN)) {
            return hql += "drugDetail.drugGCN=:drugGCN";
        }
        return hql;
    }
}
