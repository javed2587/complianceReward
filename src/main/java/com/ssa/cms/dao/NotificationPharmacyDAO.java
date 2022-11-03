/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.dao;

import com.ssa.cms.model.NotificationMessages;
import com.ssa.cms.model.PatientNotification;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Haider Ali
 */
@Repository
public class NotificationPharmacyDAO extends BaseDAO {

    private static final Log logger = LogFactory.getLog(NotificationPharmacyDAO.class.getName());

    public void save(Object bean) throws Exception {
        this.getCurrentSession().saveOrUpdate(bean);
    }

    public void merge(Object bean) throws Exception {
        this.getCurrentSession().merge(bean);
    }

    public void update(Object bean) throws Exception {
        this.getCurrentSession().update(bean);
    }

    public void delete(Object bean) throws Exception {
        this.getCurrentSession().delete(bean);
    }

    public Object getObjectById(Object clz, int id) {
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

    public List<NotificationMessages> getMedificationNotificationListByOrderId(int cPosition, int pSize, String orderId, String sort) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessage left join fetch notificationMessage.orders ord where ord.id=:orderId order by notificationMessage.createdOn " + sort);
        query.setParameter("orderId", orderId);
        return query.setFirstResult(cPosition).setMaxResults(pSize).list();
    }

    public Long getTotalPharmacyNotificationRecords(String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From OrdersPtMessage ordersPtMessage where ordersPtMessage.order.id=:orderId");
        query.setParameter("orderId", orderId);
        return (Long) query.uniqueResult();
    }

    public Long getTotalDependentRecords(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select count(*) From PatientProfileMembers profileMembers where profileMembers.patientId=:patientId");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public List<PatientNotification> getPatientNotificationListByPatientId(int cPosition, int pSize, Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientNotification patientNotification left join fetch patientNotification.patientProfile patientProfile where patientProfile.id=:patientId order by patientNotification.createdOn desc");
        query.setParameter("patientId", patientId);
        return query.setFirstResult(cPosition).setMaxResults(pSize).list();
    }

    public Long getTotalPatientProfileNotes(Integer patientId) throws Exception {
        Query query = getCurrentSession().createQuery("Select Count(*) From PatientNotification patientNotification left join fetch patientNotification.patientProfile patientProfile where patientProfile.id=:patientId");
        query.setParameter("patientId", patientId);
        return (Long) query.uniqueResult();
    }

    public List<NotificationMessages> getMedificationNotificationListByPatientId(int cPosition, int pSize, Integer patientId, String sort) throws Exception {
        Query query = getCurrentSession().createQuery("From NotificationMessages notificationMessage left join fetch notificationMessage.patientProfile patientProfile where patientProfile.id=:patientId order by notificationMessage.createdOn " + sort);
        query.setParameter("patientId", patientId);
        return query.setFirstResult(cPosition).setMaxResults(pSize).list();
    }

    public List<PatientNotification> getPatientNotificationListByPatientId(int cPosition, int pSize, Integer patientId, String sort) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientNotification patientNotification left join fetch patientNotification.patientProfile patientProfile where patientProfile.id=:patientId order by patientNotification.createdOn " + sort);
        query.setParameter("patientId", patientId);
        return query.setFirstResult(cPosition).setMaxResults(pSize).list();
    }
}
