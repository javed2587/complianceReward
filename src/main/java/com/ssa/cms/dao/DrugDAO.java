/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.DrugBasic;
import com.ssa.cms.model.DrugBrand;
import com.ssa.cms.model.DrugDetail;
import com.ssa.cms.model.DrugForm;
import com.ssa.cms.model.DrugGeneric;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Haider Ali
 */
@Repository
public class DrugDAO extends BaseDAO {

//    @Autowired
//    private SessionFactory sessionFactory;
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public void save(Object bean) throws Exception {
//        this.getCurrentSession().saveOrUpdate(bean);
//    }
//
//    public void merge(Object bean) throws Exception {
//        this.getCurrentSession().merge(bean);
//    }
//
//    public void update(Object bean) throws Exception {
//        this.getCurrentSession().update(bean);
//    }
//
//    public void delete(Object bean) throws Exception {
//        this.getCurrentSession().delete(bean);
//    }
//
//    public void saveOrUpdate(Object bean) throws Exception {
//        this.getCurrentSession().saveOrUpdate(bean);
//    }
//
//    public Object getObjectById(Object clz, int id) {
//        return this.getCurrentSession().get(clz.getClass(), id);
//    }
    public Object getObjectByIdForString(Object clz, String id) {
        return this.getCurrentSession().get(clz.getClass(), id);
    }

    public Object getObjectById(Class clz, int id) {
        return this.getCurrentSession().get(clz, id);
    }

    public List getAllRecords(Object type) throws Exception {
        return getCurrentSession().createQuery("from " + type.getClass().getName()).list();
    }

    public List getAllRecords(Class entity) throws Exception {
        return getCurrentSession().createQuery("from " + entity.getName()).list();
    }

    public List<Drug> getDrugsByBrandNameId(Integer brandNameId) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.id = :brandNameId");
        query.setParameter("brandNameId", brandNameId);
        return query.list();
    }

    /**
     *
     * @param drugBrandName
     * @return
     * @throws Exception
     */
    //returns brand name by using like
    public List<DrugBasic> searchDrugBrandByName(String drugBrandName) throws Exception {
        //Query query = getCurrentSession().createQuery("From DrugBrand drugBrand where drugBrand.drugBrandName like :name");
        Query query = getCurrentSession().createQuery("From DrugBasic drugBrand where drugBrand.brandName like :name order by drugBrand.brandName ");
        query.setParameter("name", drugBrandName + "%");
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public List<Drug> searchDrugByExactName(String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.drugBrandName = :name group by drug.drugType");
        query.setParameter("name", drugBrandName);
        //query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    /**
     *
     * @param a_iDrugBrandId
     * @return
     * @throws Exception
     */
    public List<Drug> searchDrugTypesByDrugBrandId(Integer a_iDrugBrandId) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.id = :id group by drug.drugType");
        query.setParameter("id", a_iDrugBrandId);
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    /**
     *
     * @param a_iDrugBrandId
     * @return
     * @throws Exception
     */
    public List<Drug> searchDrugByDrugType(Integer a_iDrugBrandId, String a_drugType) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.id = :id and drug.drugType = :drugType");
        query.setParameter("id", a_iDrugBrandId);
        query.setParameter("drugType", a_drugType);
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public List<DrugDetail> searchDrugDetailByDrugType(String drugBrandName, String a_drugType) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugForm drugForm left join fetch drugDetail.drugBasic drugBasic where drugForm.formDescr = :drugType and drugBasic.brandName = :name");
        query.setParameter("name", drugBrandName);
        query.setParameter("drugType", a_drugType);
        //query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public DrugDetail searchDrugDetailByDrugNameTypeStrength(
            String drugBrandName, String a_drugType, String strength) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail where drugDetail.drugBasic.brandName = :name "
                + " and  drugDetail.drugForm.formDescr = :drugType "
                + " and drugDetail.strength = :strength and drugDetail.archived='N' ");
        query.setParameter("name", drugBrandName);
        query.setParameter("drugType", a_drugType);
        query.setParameter("strength", strength);
        //query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        List lst = query.list();
        if (lst != null && lst.size() > 0) {
            return (DrugDetail) lst.get(0);
        }
        return null;
    }

    public List<Drug> searchDrugByDrugTypeAndStrength(String drugBrandName, String a_drugType, String unit) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.drugBrandName = :name and "
                + " drug.drugType = :drugType and  drug.drugUnits.name= :unit");
        query.setParameter("name", drugBrandName);
        query.setParameter("drugType", a_drugType);
        query.setParameter("unit", unit);
        //query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    /**
     *
     * @param a_iDrugBrandId
     * @return
     * @throws Exception
     */
    public List<Drug> searchDrugsByDrugBrandId(Integer a_iDrugBrandId) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.id = :id");
        query.setParameter("id", a_iDrugBrandId);
        return query.list();
    }

    public DrugDetail searchDrugsByNameStrengthType(String brand, String strength, String type) throws Exception {
        String hql = "From DrugDetail where brandName = :name"
                + " and strength= :strength"
                + " and form= :form";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", brand);
        query.setParameter("strength", strength);
        query.setParameter("form", type);
        List<DrugDetail> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        }
        return null;
    }

    public DrugDetail searchDrugsByNDC(Long ndc) throws Exception {
        String hql = "From DrugDetail where drugNDC = :ndc";

        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("ndc", ndc);

        List<DrugDetail> lst = query.list();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        }
        return null;
    }

    public List<DrugBasic> searchDrugBasicByBrandName(String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugBasic drugBasic where drugBasic.brandName like :name");
        query.setParameter("name", drugBrandName + "%");
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public List<DrugForm> searchDrugFormByExactName(String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugForm drugForm left join fetch drugForm.drugDetailList drugDetailList left join fetch drugDetailList.drugBasic drugBasic where drugBasic.brandName=:name group by drugBasic.brandName");
        query.setParameter("name", drugBrandName);
        return query.list();
    }

    public List<DrugDetail> getDrugDetailListByDrugBasicId(Integer drugBasicId) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic where drugBasic.drugBasicId=:drugBasicId");
        query.setParameter("drugBasicId", drugBasicId);
        return query.list();
    }

    public List<DrugDetail> searchDrugDefaultQtyByStrength(Integer drugBasicId, String strength) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic where drugBasic.drugBasicId=:drugBasicId and drugDetail.strength=:strength");
        query.setParameter("drugBasicId", drugBasicId);
        query.setParameter("strength", strength);
        return query.list();
    }

    public List<DrugDetail> searchDrugBasicPrice(String drugBrandName, String a_drugType, String strength) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugForm drugForm left join fetch drugDetail.drugBasic drugBasic where drugForm.formDescr = :drugType and drugBasic.brandName = :name and drugDetail.strength=:strength");
        query.setParameter("name", drugBrandName);
        query.setParameter("drugType", a_drugType);
        query.setParameter("strength", strength);
        return query.list();
    }

    public List<DrugDetail> getDrugDetailsByNDC(long ndc) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugForm drugForm left join fetch drugDetail.drugBasic drugBasic where drugDetail.drugNDC=:drugNDC");
        query.setParameter("drugNDC", ndc);
        return query.list();
    }

    public List retrieveActiveDrugListData() {
//        return getCurrentSession().createQuery("From PatientProfile p,PatientDeliveryAddress addr "
//                + "  where p.id=addr.patientProfile.id and addr.defaultAddress='Yes' order by p.firstName  ").list();

        return getCurrentSession().createQuery("From DrugDetail d join fetch d.drugBasic drugbasic "
                + " join fetch drugbasic.drugGeneric  drugGeneric "
                + " join fetch d.drugForm drugForm "
                // + " join fetch d.drugPacking packing "
                + "  where d.archived='N' and (drugbasic.brandName like 'A%' or drugbasic.brandName like 'Z%') order by drugbasic.brandName  ").list();
    }

    public List retrieveActiveDrugListData(String drugName) {
//        return getCurrentSession().createQuery("From PatientProfile p,PatientDeliveryAddress addr "
//                + "  where p.id=addr.patientProfile.id and addr.defaultAddress='Yes' order by p.firstName  ").list();

        return getCurrentSession().createQuery("From DrugDetail d join fetch d.drugBasic drugbasic "
                + " join fetch drugbasic.drugGeneric  drugGeneric "
                + " join fetch d.drugForm drugForm "
                //  + " join fetch d.drugPacking packing "
                + "  where d.archived='N' and  drugbasic.brandName like :drugName "
                + " order by drugbasic.brandName  ").setParameter("drugName", drugName + "%").list();
    }

    public List<DrugGeneric> searchDrugGenericByGenericName(String drugGenericName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugGeneric drugGeneric where drugGeneric.genericName like :name");
        query.setParameter("name", drugGenericName + "%");
        query.setMaxResults(Constants.PAGING_CONSTANT.RECORDS_PER_AUTO_COMPLETE);
        return query.list();
    }

    public List<DrugDetail> getDrugDetailsListByDrugGCN(long drugGcn) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(DrugDetail.class);
        criteria.add(Restrictions.sqlRestriction(" drugGCN LIKE '%" + drugGcn + "%' group by drugGCN"));
        return criteria.list();
    }

    public List<DrugForm> searchDrugFormByDrugBrandName(String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugForm drugForm left join fetch drugForm.drugDetailList drugDetailList left join fetch drugDetailList.drugBasic drugBasic where drugBasic.brandName=:name group by drugForm.formDescr");
        query.setParameter("name", drugBrandName);
        return query.list();
    }
    
     public List<DrugForm> searchDrugFormByBrandedDrug(String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugForm drugForm left join fetch drugForm.drugDetailList drugDetailList left join fetch drugDetailList.drugBasic drugBasic where drugBasic.brandName=:name and drugBasic.brandIndicator='Brand' group by drugForm.formDescr");
        query.setParameter("name", drugBrandName);
        return query.list();
    }
    
    public List<DrugForm> searchDrugFormByBrandedDrugs(String drugBrandName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugForm drugForm left join fetch drugForm.drugDetailList "
                    +" drugDetailList left join fetch drugDetailList.drugBasic drugBasic where "
                    +" drugBasic.brandName=:name and drugBasic.brandIndicator='BRAND' group by drugForm.formDescr");
        query.setParameter("name", drugBrandName);
        return query.list();
    }
    
    public List<DrugForm> searchDrugFormByGenericDrugs(String drugBrandName,String genericName) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugForm drugForm left join fetch drugForm.drugDetailList "
                    +" drugDetailList left join fetch drugDetailList.drugBasic drugBasic where "
                    +" drugBasic.brandName=:name and drugBasic.drugGeneric.genericName=:genericName group by drugForm.formDescr");
        query.setParameter("name", drugBrandName);
        query.setParameter("genericName", genericName);
        return query.list();
    }

    public List getDrugDetailByGCN(Long drugGcn) throws Exception {
        Query query = getCurrentSession().createQuery("From DrugDetail drugDetail left join fetch drugDetail.drugBasic drugBasic left join fetch drugBasic.drugGeneric drugGeneric left join fetch drugDetail.drugForm drugForm where drugDetail.drugGCN=:drugGcn");
        query.setParameter("drugGcn", drugGcn);
        return query.list(); //(DrugDetail) query.uniqueResult();
    }
}
