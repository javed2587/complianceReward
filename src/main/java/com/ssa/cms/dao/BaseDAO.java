/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dao;

import com.ssa.cms.bean.BusinessObject;
import com.ssa.cms.common.Constants;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.PatientGlucoseResults;
import com.ssa.cms.util.AppUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adeel.usmani
 */
@Repository
public class BaseDAO implements Serializable {

//    @Autowired
//    private SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager entityManager;

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);//sessionFactory.getCurrentSession();
    }

    public void persist(Object bean) throws Exception {
        entityManager.persist(bean);
    }

    public void save(Object bean) throws Exception {

        this.getCurrentSession().save(bean);
        //entityManager.persist(bean);
    }

    public void save(Object bean, boolean isdbSessionClear) throws Exception {
        if (isdbSessionClear) {
            this.getCurrentSession().clear();
        }
        this.getCurrentSession().save(bean);
    }

    public void merge(Object bean) throws Exception {
        try {
            this.getCurrentSession().merge(bean);
        } catch (org.hibernate.ObjectNotFoundException e) {
            save(bean);
        }
    }

    public void update(Object bean) throws Exception {
        // this.entityManager.merge(bean);
        this.getCurrentSession().update(bean);
    }

    public void delete(Object bean) throws Exception {
        this.getCurrentSession().delete(bean);
    }

    public void deleteProfile(Object bean) throws Exception {
        //this.getCurrentSession().delete(bean);
        entityManager.remove(entityManager.contains(bean) ? bean : entityManager.merge(bean));
    }

    public List<?> getList(Object bean) throws Exception {
        return this.getCurrentSession().createCriteria(bean.getClass()).list();
    }

    public Object saveOrUpdate(Object transientInstance) {

        // log.debug("Saving Instance
        // Class="+transientInstance.getClass().getName());
        Object result = null;
        //result=entityManager.merge(transientInstance);
        Session session = null;
        FlushMode flushModule = null;
        try {
            // log.debug("going to call saveOrUpdate to save the instance.");

            try {
                session = this.getCurrentSession();
                flushModule = session.getFlushMode();
                session.setFlushMode(FlushMode.AUTO);
            } catch (RuntimeException e) {
                return e;
            }

            session.saveOrUpdate(transientInstance);
            
        } catch (Exception e) {
            try {

                // logger.debug("Exception occure while saving through
                // saveorUpdate.Going to call merge to save the instance.");
                //e.printStackTrace();
                result = session.merge(transientInstance);
                // logger.debug("merge successful");
                return result;
            } catch (RuntimeException re) {
                //e.printStackTrace();
                // logger.error("merge failed", re);

                throw re;
            }
        } finally {
            try {
                if (session != null) {
                    session.flush();
                    session.setFlushMode(flushModule);
                }
            } catch (HibernateException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
                System.out.println("---->EXCEPTION OCCURED:::::" + e.getMessage());
                return e;
            }
        }
        return result;
    }

    public Object findRecordById(Object clz, int id) {
        return this.getCurrentSession().get(clz.getClass(), id);
    }

    public Object findRecordById(Object clz, Long id) {
        return this.getCurrentSession().get(clz.getClass(), id);
    }

    public Object findRecordById(Object clz, String id) {
        return this.getCurrentSession().get(clz.getClass(), id);
    }
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * Specifically applicable in queries where nested properties are being
     * compared in where clause
     *
     * @param object Object of any hibernate class
     * @param buisnessObjectLst Containing list of BussinessObject containing
     * name/value pair & comparison operator(Constants.HIBERNATE_EQ_OPERATOR or
     * 0 for equality Constants.HIBERNATE_NE_OPERATOR for not equal
     * Constants.HIBERNATE_LIKE_OPERATOR for wildcard like
     * Constants.HIBERNATE_LIKE_START_OPERATOR for pattern matching at start of
     * word ) used in criteria query
     * @param value Value of the specific property
     *
     * @param orderByProp Property for which you apply sorting, leave blank if
     * not any Constants.NULL_STRING for blank
     * @param seq Sorting order Constants.HIBERNATE_ASC_ORDER for ascending
     * Constants.HIBERNATE_DESC_ORDER for descending
     * @return return List of Objects of specific type
     */
    public List findByNestedProperty(Object object, List<BusinessObject> buisnessObjectLst, String orderByProp, int seq) {
        String sql = " from " + object.getClass().getName();
        String parameter = "obj";

        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {
            BusinessObject buisObj = buisnessObjectLst.get(i);
            if (i == 0) {
                sql += " where ";
            } else {
                sql += " and ";
            }
            String operator = buisObj.getComparisonOperator() == Constants.HIBERNATE_EQ_OPERATOR ? "=" : buisObj.getComparisonOperator() == Constants.HIBERNATE_NE_OPERATOR ? "!=" : " like ";
            if (buisnessObjectLst.get(i).getValue() != null) {
                sql += buisnessObjectLst.get(i).getName() + operator + " :" + parameter + i;
            } else if (buisObj.getComparisonOperator() == Constants.HIBERNATE_EQ_OPERATOR) {
                sql += buisnessObjectLst.get(i).getName() + " is null ";
            } else {
                sql += " buisnessObjectLst.get(i).getName() is not null ";
            }

        }
        if (AppUtil.getSafeStr(orderByProp, "").length() > 0) {
            sql += " order by " + orderByProp + (seq == Constants.HIBERNATE_ASC_ORDER ? " asc " : " desc ");
        }
        Query query = getCurrentSession().createQuery(sql);
        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {
            BusinessObject buisObj = buisnessObjectLst.get(i);
            switch (buisObj.getComparisonOperator()) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                case Constants.HIBERNATE_NE_OPERATOR:
                    if (buisnessObjectLst.get(i).getValue() != null) {
                        query.setParameter(parameter + i, buisObj.getValue());
                    }
                    break;
                case Constants.HIBERNATE_LIKE_START_OPERATOR:
                    query.setParameter(parameter + i, buisObj.getValue() + "%");
                    break;
                default:
                    query.setParameter(parameter + i, "%" + buisObj.getValue() + "%");
                    break;
            }
        }

        return query.list();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * Specifically applicable in queries where nested properties are being
     * compared in where clause
     *
     * @param object Object of any hibernate class
     * @param propertyAttr Property for which you have to make comparison
     * @param value Value of the specific property
     * @param operand Operator you want to Apply. Use Operators from
     * Constants.java: Constants.HIBERNATE_EQ_OPERATOR or 0 for equality
     * Constants.HIBERNATE_NE_OPERATOR for not equal
     * Constants.HIBERNATE_LIKE_OPERATOR for like
     * @param orderByProp Property for which you apply sorting, leave blank if
     * not anygetTotalRecords
     * @param seq Sorting order Constants.HIBERNATE_ASC_ORDER for ascending
     * @return Object of specific type
     */
    public List findByNestedProperty(Object object, String propertyAttr, Object value, int operand, String orderByProp, int seq) {
        String sql = " from " + object.getClass().getName();
        String parameter = "obj";
        String operator = operand == Constants.HIBERNATE_EQ_OPERATOR ? "=" : operand == Constants.HIBERNATE_NE_OPERATOR ? "!=" : " like ";
        sql += " where " + propertyAttr + operator + " :" + parameter;

        if (AppUtil.getSafeStr(orderByProp, "").length() > 0) {
            sql += " order by " + orderByProp + (seq == Constants.HIBERNATE_ASC_ORDER ? " asc " : " desc ");
        }
        Query query = getCurrentSession().createQuery(sql);

        switch (operand) {
            case Constants.HIBERNATE_EQ_OPERATOR:
            case Constants.HIBERNATE_NE_OPERATOR:
                query.setParameter(parameter, value);
                break;
            case Constants.HIBERNATE_LIKE_START_OPERATOR:
                query.setParameter(parameter, value + "%");
                break;
            default:
                query.setParameter(parameter, "%" + value + "%");
                break;

        }

        return query.list();
    }
    /////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param object Object of any hibernate class
     * @param buisnessObjectLst Containing list of BussinessObject containing
     * name/value pair & comparison operator(Constants.HIBERNATE_EQ_OPERATOR or
     * 0 for equality Constants.HIBERNATE_NE_OPERATOR for not equal
     * Constants.HIBERNATE_LIKE_OPERATOR for wildcard like
     * Constants.HIBERNATE_LIKE_START_OPERATOR for pattern matching at start of
     * word ) used in criteria query
     *
     * @param orderByProp Property for which you apply sorting, leave blank if
     * not any Constants.NULL_STRING for blank
     * @param seq Sorting order Constants.HIBERNATE_ASC_ORDER for ascending
     * Constants.HIBERNATE_DESC_ORDER for descending
     * @return return List of Objects of specific type
     */
    public List findByProperty(Object object, List<BusinessObject> buisnessObjectLst, String orderByProp, int seq) {

        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {
            if (AppUtil.getSafeStr(buisnessObjectLst.get(i).getName(), "").contains(".")) {
                return findByNestedProperty(object, buisnessObjectLst, orderByProp, seq);
            }
        }
        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {

            BusinessObject obj = buisnessObjectLst.get(i);
            int operand = obj.getComparisonOperator();
            switch (operand) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                    if (obj.getValue() != null) {
                        criteria.add(Restrictions.eq(obj.getName(), obj.getValue()));
                    } else {
                        criteria.add(Restrictions.isNull(obj.getName()));
                    }
                    break;
                case Constants.HIBERNATE_NE_OPERATOR:
                    criteria.add(Restrictions.ne(obj.getName(), obj.getValue()));
                    break;
                case Constants.HIBERNATE_LIKE_OPERATOR:
                    criteria.add(Restrictions.like(obj.getName(), "%" + obj.getValue() + "%"));
                    break;
                case Constants.HIBERNATE_LIKE_START_OPERATOR:
                    criteria.add(Restrictions.like(obj.getName(), obj.getValue() + "%"));
                    break;
                default:
                    criteria.add(Restrictions.eq(obj.getName(), obj.getValue()));
                    break;
            }

        }

        if (AppUtil.getSafeStr(orderByProp, "").length() > 0) {
            if (seq == Constants.HIBERNATE_ASC_ORDER) {
                criteria.addOrder(Order.asc(orderByProp));
            } else {
                criteria.addOrder(Order.desc(orderByProp));
            }
        }

        return criteria.list();
    }
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param object Object of any hibernate class
     * @param propertyAttr Property for which you have to make comparison
     * @param value Value of the specific property
     * @param operand Operator you want to Apply. Use Operators from
     * Constants.java: Constants.HIBERNATE_EQ_OPERATOR or 0 for equality
     * Constants.HIBERNATE_NE_OPERATOR for not equal
     * Constants.HIBERNATE_LIKE_OPERATOR for like
     * @param orderByProp Property for which you apply sorting, leave blank if
     * not any
     * @param seq Sorting order Constants.HIBERNATE_ASC_ORDER for ascending
     * Constants.HIBERNATE_DESC_ORDER for descending
     * @return return List of Objects of specific type
     */
    public List findByProperty(Object object, String propertyAttr, String value, int operand, String orderByProp, int seq) {
        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
        if (!"".equals(AppUtil.getSafeStr(value, ""))) {
            switch (operand) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                    criteria.add(Restrictions.eq(propertyAttr, AppUtil.getSafeStr(value, "")).ignoreCase());
                    break;
                case Constants.HIBERNATE_NE_OPERATOR:
                    criteria.add(Restrictions.ne(propertyAttr, AppUtil.getSafeStr(value, "")).ignoreCase());
                    break;
                case Constants.HIBERNATE_LIKE_OPERATOR:
                    criteria.add(Restrictions.like(propertyAttr, "%" + value + "%"));
                    break;
                case Constants.HIBERNATE_LIKE_START_OPERATOR:
                    criteria.add(Restrictions.like(propertyAttr, value + "%"));
                    break;
                default:
                    criteria.add(Restrictions.eq(propertyAttr, AppUtil.getSafeStr(value, "")).ignoreCase());
                    break;
            }

            if (AppUtil.getSafeStr(orderByProp, "").length() > 0) {
                if (seq == Constants.HIBERNATE_ASC_ORDER) {
                    criteria.addOrder(Order.asc(orderByProp));
                } else {
                    criteria.addOrder(Order.desc(orderByProp));
                }
            }
        }

        return criteria.list();
    }

    /**
     *
     * @param object Object of any hibernate class
     * @param propertyAttr Property for which you have to make comparison
     * @param value Value of the specific property
     * @param operand Operator you want to Apply. Use Operators from
     * Constants.java: Constants.HIBERNATE_EQ_OPERATOR or 0 for equality
     * Constants.HIBERNATE_NE_OPERATOR for not equal
     * Constants.HIBERNATE_LIKE_OPERATOR for like
     * @return return Object of specific type
     */
    public Object findByPropertyUnique(Object object, String propertyAttr, String value, int operand) {
        getCurrentSession().clear();
        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
        if (!"".equals(AppUtil.getSafeStr(value, ""))) {
            switch (operand) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                    criteria.add(Restrictions.eq(propertyAttr, AppUtil.getSafeStr(value, "")).ignoreCase());
                    break;
                case Constants.HIBERNATE_NE_OPERATOR:
                    criteria.add(Restrictions.ne(propertyAttr, AppUtil.getSafeStr(value, "")).ignoreCase());
                    break;
                case Constants.HIBERNATE_LIKE_OPERATOR:
                    criteria.add(Restrictions.like(propertyAttr, value));
                    break;
                default:
                    criteria.add(Restrictions.eq(propertyAttr, AppUtil.getSafeStr(value, "")).ignoreCase());
                    break;
            }

        }

        return criteria.list() != null && criteria.list().size() > 0 ? criteria.list().get(0) : null;
    }

    /**
     *
     * @param object
     * @param propertyAttr
     * @param value
     * @param operand
     * @return Object of specific type
     */
    public Object findByPropertyUnique(Object object, String propertyAttr, Long value, int operand) {
        if (AppUtil.getSafeStr(propertyAttr, "").contains(".")) {
            List lst = findByNestedProperty(object, propertyAttr, value, operand, "", 0);
            return lst != null && lst.size() > 0 ? lst.get(0) : null;
        }
        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
        if (value != null) {
            switch (operand) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                    criteria.add(Restrictions.eq(propertyAttr, value));
                    break;
                case Constants.HIBERNATE_NE_OPERATOR:
                    criteria.add(Restrictions.ne(propertyAttr, value));
                    break;
                case Constants.HIBERNATE_LIKE_OPERATOR:
                    criteria.add(Restrictions.like(propertyAttr, value));
                    break;
                default:
                    criteria.add(Restrictions.eq(propertyAttr, value));
                    break;
            }

        }

        return criteria.list() != null && criteria.list().size() > 0 ? criteria.list().get(0) : null;
    }

    /**
     *
     * @param object
     * @param propertyAttr
     * @param value
     * @param operand
     * @param orderByProp
     * @param seq
     * @return Object of specific type
     */
    public Object findByPropertyUnique(Object object, String propertyAttr, Object value, int operand, String orderByProp, int seq) {
        if (AppUtil.getSafeStr(propertyAttr, "").contains(".")) {
            List lst = findByNestedProperty(object, propertyAttr, value, operand, orderByProp, seq);
            return lst != null && lst.size() > 0 ? lst.get(0) : null;
        }
        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
        if (value != null) {
            switch (operand) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                    criteria.add(Restrictions.eq(propertyAttr, value));
                    break;
                case Constants.HIBERNATE_NE_OPERATOR:
                    criteria.add(Restrictions.ne(propertyAttr, value));
                    break;
                case Constants.HIBERNATE_LIKE_OPERATOR:
                    criteria.add(Restrictions.like(propertyAttr, value));
                    break;
                default:
                    criteria.add(Restrictions.eq(propertyAttr, value));
                    break;
            }
            if (AppUtil.getSafeStr(orderByProp, "").length() > 0) {
                if (seq == Constants.HIBERNATE_ASC_ORDER) {
                    criteria.addOrder(Order.asc(orderByProp));
                } else {
                    criteria.addOrder(Order.desc(orderByProp));
                }
            }
        }

        return criteria.list() != null && criteria.list().size() > 0 ? criteria.list().get(0) : null;
    }

    public Object getTotalRecords(Object object, String propertyAttr, Object value, int operand, String funPropertyAttr) throws Exception {
        String sql = "Select ";
        if (operand == Constants.HIBERNATE_SUM_FUNCTION) {
            sql += "SUM(" + funPropertyAttr + ")";
        } else if (operand == Constants.HIBERNATE_COUNT_FUNCTION) {
            sql += "COUNT(" + funPropertyAttr + ")";
        }
        String parameter = "obj";
        sql += " from " + object.getClass().getName() + " where " + propertyAttr + " =:" + parameter;
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter(parameter, value);
        return query.uniqueResult();
    }

    public Object getTotalRecordsByNestedProperty(Object object, List<BusinessObject> buisnessObjectLst, String sqlFunc) {
        String sql = "Select " + sqlFunc + " from " + object.getClass().getSimpleName();
        String parameter = "obj";

        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {
            BusinessObject buisObj = buisnessObjectLst.get(i);
            if (i == 0) {
                sql += " where ";
            } else {
                sql += " and ";
            }
            String operator = buisObj.getComparisonOperator() == Constants.HIBERNATE_EQ_OPERATOR ? "=" : buisObj.getComparisonOperator() == Constants.HIBERNATE_NE_OPERATOR ? "!=" : " like ";
            sql += buisnessObjectLst.get(i).getName() + operator + " :" + parameter + i;
        }

        Query query = getCurrentSession().createQuery(sql);
        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {
            BusinessObject buisObj = buisnessObjectLst.get(i);
            switch (buisObj.getComparisonOperator()) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                case Constants.HIBERNATE_NE_OPERATOR:
                    query.setParameter(parameter + i, buisObj.getValue());
                    break;
                case Constants.HIBERNATE_LIKE_START_OPERATOR:
                    query.setParameter(parameter + i, buisObj.getValue() + "%");
                    break;
                default:
                    query.setParameter(parameter + i, "%" + buisObj.getValue() + "%");
                    break;
            }
        }
        return query.uniqueResult();
    }

    void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    public int delete(String tblName, String propertyAttr, Object value) throws Exception {
        //entityManager.persist(foo);
        flushAndClear();
        Query query = getCurrentSession().createSQLQuery("Delete From " + tblName + " Where " + propertyAttr + " In (:value)");
        query.setParameter("value", value);
        return query.executeUpdate();
    }

    public int deleteByMobileNo(String tblName, String propertyAttr, Object value) throws Exception {

        flushAndClear();
        Query query = getCurrentSession().createSQLQuery("Delete From " + tblName + " Where " + propertyAttr + " In (:value)");
        query.setParameter("value", value);
        return query.executeUpdate();
    }

    public int deleteSMSByMobileNo(String customerRequestTable, String campaigneMessageRequestTable, String campaigneMessageReqResTable, String propertyAttr, Object value) throws Exception {
        flushAndClear();
        Query query = getCurrentSession().createSQLQuery("DELETE " + customerRequestTable + "," + campaigneMessageRequestTable + "," + campaigneMessageReqResTable + " FROM " + customerRequestTable + " "
                + "INNER JOIN " + campaigneMessageRequestTable + " ON " + customerRequestTable + ".`CR_Seq_No`=" + campaigneMessageRequestTable + ".`CR_Seq_No` "
                + "INNER JOIN " + campaigneMessageReqResTable + " ON " + campaigneMessageRequestTable + ".`CMR_Seq_No`=" + campaigneMessageReqResTable + ".`CMR_Seq_No` "
                + "WHERE " + customerRequestTable + "." + propertyAttr + " =:value");
        query.setParameter("value", value);
        return query.executeUpdate();
    }

    public int deleteUserbyPracticeId(String users, String model_has_roles, String propertyAttr, Object value) throws Exception {
        flushAndClear();
        Query query = getCurrentSession().createSQLQuery("DELETE " + users + "," + model_has_roles + " FROM " + users + " "
                + "INNER JOIN " + model_has_roles + " ON " + users + ".`id`=" + model_has_roles + ".`model_id` "
                + "WHERE " + users + "." + propertyAttr + " =:value");
        query.setParameter("value", value);
        return query.executeUpdate();
    }

    public void deleteOrderHistory(Integer patientProfileId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("DELETE FROM OrderHistory WHERE OrderHistory.OrderId IN (SELECT Id FROM Orders where Orders.PatientId IN (:patientProfileId))");
        query.setParameter("patientProfileId", patientProfileId);
        query.executeUpdate();
    }

    public CMSEmailContent getCMSEmailContent(String emailType) throws Exception {
        Query query = getCurrentSession().createQuery("FROM CMSEmailContent emailContent left join fetch emailContent.cMSEmailType cMSEmailType where cMSEmailType.title=:emailType");
        query.setParameter("emailType", emailType);
        return (CMSEmailContent) query.uniqueResult();
    }

    public void deleteRewardHistoryByPatientId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Delete From RewardHistory Where OrderId is not null And PatientId=:patientId");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    public void deleteNotificationMessagesByPatientId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Delete From NotificationMessages Where OrderID is not null And ProfileId=:patientId");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    public void deleteMultirxByPatientId(Integer patientId) throws Exception {
        Query query = getCurrentSession().createSQLQuery("DELETE m FROM MultiRx AS m INNER JOIN Orders AS o ON o.Id=m.OrderID WHERE o.PatientId=:patientId");
        query.setParameter("patientId", patientId);
        query.executeUpdate();
    }

    public CMSEmailContent getCMSEmailByPageId(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("from CMSEmailContent emailContent left join fetch emailContent.cMSEmailType cMSEmailType where cMSEmailType.id=:id");
        query.setParameter("id", id);
        return (CMSEmailContent) query.uniqueResult();
    }

    public List<com.ssa.cms.model.Order> getOrderListByIds(List<String> ids) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(com.ssa.cms.model.Order.class, "ord");
        criteria.createCriteria("deliveryPreference", "deliveryPreference", JoinType.LEFT_OUTER_JOIN);
        criteria.createCriteria("drugDetail", "drugDetail", JoinType.LEFT_OUTER_JOIN);

        criteria.add(Restrictions.in("ord.id", ids));
        return criteria.list();
    }

    public List<com.ssa.cms.model.Order> getRxProgramOrdersByPatientId(Integer patientId, List statusIds) throws Exception {
        List<com.ssa.cms.model.Order> orders = null;

        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfile pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("where pf.id=:patientId and os.id in(:orderStatus) and (ord.patientProfileMembers is null or ord.patientProfileMembers.id is not null) ");

        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setParameterList("orderStatus", statusIds);

        orders = query.list();

        return orders;
    }

    public Object findUniqueObjectByProperty(Object object, List<BusinessObject> buisnessObjectLst, String orderByProp, int seq) {

//        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {
//            if (AppUtil.getSafeStr(buisnessObjectLst.get(i).getName(), "").contains(".")) {
//                return findByNestedProperty(object, buisnessObjectLst, orderByProp, seq);
//            }
//        }
        Criteria criteria = getCurrentSession().createCriteria(object.getClass());
        for (int i = 0; buisnessObjectLst != null && i < buisnessObjectLst.size(); i++) {

            BusinessObject obj = buisnessObjectLst.get(i);
            int operand = obj.getComparisonOperator();
            switch (operand) {
                case Constants.HIBERNATE_EQ_OPERATOR:
                    if (obj.getValue() != null) {
                        criteria.add(Restrictions.eq(obj.getName(), obj.getValue()));
                    } else {
                        criteria.add(Restrictions.isNull(obj.getName()));
                    }
                    break;
                case Constants.HIBERNATE_NE_OPERATOR:
                    criteria.add(Restrictions.ne(obj.getName(), obj.getValue()));
                    break;
                case Constants.HIBERNATE_LIKE_OPERATOR:
                    criteria.add(Restrictions.like(obj.getName(), "%" + obj.getValue() + "%"));
                    break;
                case Constants.HIBERNATE_LIKE_START_OPERATOR:
                    criteria.add(Restrictions.like(obj.getName(), obj.getValue() + "%"));
                    break;
                default:
                    criteria.add(Restrictions.eq(obj.getName(), obj.getValue()));
                    break;
            }

        }

        if (AppUtil.getSafeStr(orderByProp, "").length() > 0) {
            if (seq == Constants.HIBERNATE_ASC_ORDER) {
                criteria.addOrder(Order.asc(orderByProp));
            } else {
                criteria.addOrder(Order.desc(orderByProp));
            }
        }

        return criteria.list() != null && criteria.list().size() > 0 ? criteria.list().get(0) : null;
    }

    public List<com.ssa.cms.model.Order> getFilledStatusOrdersByPatientId(Integer patientId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfile pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.patientProfileMembers patientProfileMembers ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id=:orderStatus and deliveryPreference.id!=:deliveryPreferenceId ");

        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setInteger("orderStatus", Constants.ORDER_STATUS.FILLED_ID);
        query.setInteger("deliveryPreferenceId", Constants.DELIVERY_PREfFERENCES.PICK_UP_AT_PHARMACY_ID);
        return query.list();
    }
}
