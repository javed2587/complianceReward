package com.ssa.cms.dao;

import com.ssa.cms.model.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Repository
@Transactional
public class SetupsDAO extends BaseDAO implements Serializable {

//    @Autowired
//    private SessionFactory sessionFactory;
    private static final Log logger = LogFactory.getLog(SetupsDAO.class.getName());

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public void saveOrUpdate(Object bean) throws Exception {
//        this.getCurrentSession().saveOrUpdate(bean);
//    }
//
//    public void save(Object bean) throws Exception {
//        this.getCurrentSession().save(bean);
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

    public Object getRecordById(Object clz, int id) {
        return this.getCurrentSession().get(clz.getClass(), id);
    }

    
    public List<Industry> findAllIndustries() throws Exception {
        return getCurrentSession().createQuery("from Industry industry").list();
    }

    public TransferRequest findTransferRequestById(int requestId) throws Exception {
        return (TransferRequest) getCurrentSession().createQuery("FROM TransferRequest tr WHERE tr.id=:requestId").setParameter("requestId", requestId);
        
    }
    
    public PatientProfile findPatientProfileById(int id) throws Exception {
        return (PatientProfile) getCurrentSession().createQuery("FROM PatientProfile p WHERE p.id=:id").setParameter("id", id);
        
    }
    
    public PatientDeliveryAddress findPatientDeliveryAddressById(int id) throws Exception {
//        return (PatientDeliveryAddress) 
        Query query= getCurrentSession().createQuery("FROM PatientDeliveryAddress p WHERE p.patientProfile.id=:id AND p.defaultAddress = 'Yes'").setParameter("id", id);
        List lst=query.list();
        if(lst!=null && lst.size()>0)
        {
            return (PatientDeliveryAddress)lst.get(0);
        }
        return null;
    }
   
    public List getRecordList(Object object) throws Exception {
        return getCurrentSession().createCriteria(object.getClass()).list();
    }

    public Industry getIndustryById(Integer userId) throws Exception {
        Industry industry = null;
        Object o = this.getCurrentSession().get(Industry.class, userId);
        if (o != null) {
            industry = (Industry) o;
        }
        return industry;
    }

    public boolean findIndustryByTitle(String title, Integer id) throws Exception {
        boolean found = false;
        String hql = "From Industry ind where ind.industryTitle = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and ind.industryId != " + id;
        }
        try {

            Query query = getCurrentSession().createQuery(hql);

            if (!query.list().isEmpty()) {
                found = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findIndustryByTitle", e);
        }

        return found;
    }

    public List<Intervals> findIntervalValue(List<String> intervalValue, Integer id) throws Exception {
        if (intervalValue != null && !intervalValue.isEmpty()) {
            String inClause = StringUtils.collectionToDelimitedString(intervalValue, ",");
            String hq = "from Intervals intervals  where intervals.intervalValue in (" + inClause + ")";
            if (id != null && id != 0) {
                hq += " and intervals.intervalsType.intervalsTypeId != " + id;
            }
            return (List<Intervals>) getCurrentSession().createQuery(hq).list();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Folder> findAllFolders() throws Exception {
        return getCurrentSession().createQuery("from Folder folder order by folder.folderName ASC").list();
    }

    public Folder getFolderById(Integer userId) throws Exception {
        Folder folder = null;
        Object o = this.getCurrentSession().get(Folder.class, userId);
        if (o != null) {
            folder = (Folder) o;
        }
        return folder;
    }

    public List<MessageType> findAllMessageTypes() throws Exception {
        return getCurrentSession().createQuery("from MessageType messageType").list();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public MessageType getMessageTypeById(MessageTypeId userId) throws Exception {
        MessageType mt = null;
        Object o = this.getCurrentSession().get(MessageType.class, userId);
        if (o != null) {
            mt = (MessageType) o;
        }
        return mt;
    }

    public MessageType getMessageTypeById(Integer typeId) throws Exception {
        MessageType mt = null;
        Query query = this.getCurrentSession().createQuery("from MessageType msg left join fetch msg.campaignMessageses campaignMsg where msg.id.messageTypeId =" + typeId);
        Object o = query.uniqueResult();
        if (o != null) {
            mt = (MessageType) o;
        }
        return mt;
    }


    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<IntervalsType> findAllIntervalsType() {
        return getCurrentSession().createQuery("from IntervalsType intervalsType").list();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public IntervalsType getIntervalsTypeById(Integer id) {
        IntervalsType intervalsType = null;
        Object o = this.getCurrentSession().get(IntervalsType.class, id);
        if (o != null) {
            intervalsType = (IntervalsType) o;
        }
        return intervalsType;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<Intervals> findAllIntervals() throws Exception {
        return getCurrentSession().createQuery("select intervals from Intervals intervals order by intervals.intervalValue").list();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public boolean deleteIntervalsById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("DELETE FROM Intervals intervals where intervals.intervalId = :intervalId");
        query.setInteger("intervalId", id);
        return query.executeUpdate() != 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean findIntervalByTitle(String title, Integer id) throws Exception {
        boolean found = false;
        String hql = "From IntervalsType intervalsType where intervalsType.intervalsTypeTitle = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and intervalsType.intervalsTypeId != " + id;
        }
        try {

            Query query = getCurrentSession().createQuery(hql);

            if (!query.list().isEmpty()) {
                found = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findIntervalByTitle", e);
        }

        return found;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<String[]> pdrffieldName() throws Exception {
        String[] list = null;
        String[] colNames = null;
        List<String[]> colNamesList = new ArrayList<>();

        ClassMetadata meta = getCurrentSession().getSessionFactory().getClassMetadata(Drfprocessed.class);
        list = meta.getPropertyNames();

        for (int i = 0; i < list.length; i++) {
            AbstractEntityPersister persister = (AbstractEntityPersister) meta;
            colNames = persister.getPropertyColumnNames(list[i]);
            colNamesList.add(colNames);
        }

        return colNamesList;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public DrugBrand getDrugBrandById(Integer userId) throws Exception {
        DrugBrand db = null;
        Object o = this.getCurrentSession().get(DrugBrand.class, userId);
        if (o != null) {
            db = (DrugBrand) o;
        }
        return db;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public DrugBrand getDrugBrandByName(String name) throws Exception {
        DrugBrand db = null;
        String queryString = "from DrugBrand where BrandName = :BrandName";
        Query query = getCurrentSession().createQuery(queryString);
        query.setString("BrandName", name);
        Object queryResult = query.uniqueResult();
        db = (DrugBrand) queryResult;
        return db;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public Drug getDrugById(Integer userId) throws Exception {
        Drug drug = null;
        Object o = this.getCurrentSession().get(Drug.class, userId);
        if (o != null) {
            drug = (Drug) o;
        }
        return drug;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<Integer> getParentIdByUserId(Integer userId) {
        return getCurrentSession().createQuery("SELECT users.parentId FROM Users users WHERE users.userId ='" + userId + "'").list();
    }

    public List<Integer> getAllAdminIds(int excludeParent) {
        List<Integer> userIds = (List<Integer>) getCurrentSession().createQuery("SELECT users.userId FROM Users users WHERE users.parentId = 0 and users.userId <> " + excludeParent);
        return userIds;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<DrugBrand> findAllDrugSetup() throws Exception {
        return getCurrentSession().createQuery("select distinct drugBrand from DrugBrand drugBrand "
                + "left join fetch drugBrand.drugs drugs  "
                + "left join fetch drugs.drugUnits drugUnits  "
                + " order by drugBrand.id asc").list();
    }

    public List<String> findAllBrandId(String userId) throws Exception {
        List<String> list = null;

        String hql = "select brandId from UserBrand userBrand where userBrand.userId = :UserId";
        try {

            Query query = getCurrentSession().createQuery(hql);
            query.setInteger("UserId", Integer.parseInt(userId));

            list = (List<String>) query.list();
        } catch (NumberFormatException | HibernateException e) {
            logger.error("Exception: SetupsDAO -> findAllBrandId", e);
        }

        return list;

    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<Drug> findAllDrug(Integer drugBrandId) throws Exception {
        Query query = getCurrentSession().createQuery("from Drug drug where drug.drugBrand.id=:drugBrandId");
        query.setParameter("drugBrandId", drugBrandId);
        return query.list();
    }

    public void deleteDrugById(Integer id) throws Exception {
        String queryString = "DELETE FROM Drug drug where drug.drugBrand.id = :drugBrandId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("drugBrandId", id);
        query.executeUpdate();
    }

    public boolean deleteValidResponseById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("DELETE FROM ValidResponse valid where valid.vresponseId = :vresponseId");
        query.setInteger("vresponseId", id);
        return query.executeUpdate() != 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public List<Event> fetchAllEventSetup() throws Exception {
        return getCurrentSession().createQuery("select distinct event from Event event "
                + "left join fetch event.eventDetails eventDetails order by event.eventCriteria desc").list();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<Event> checkEventDuplication(String eventName, Integer eventId) throws Exception {

        String hql = "select event from Event event where 1=1 ";
        if (eventName != null && eventName.trim().length() > 0) {
            hql = hql + " and event.eventTitle = '" + eventName + "' ";
        }
        if (eventId != null && eventId > 0) {
            hql = hql + " and event.eventId != '" + eventId + "' ";
        }
        List<Event> list = (List<Event>) getCurrentSession().createQuery(hql);
        return list;
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public void deleteEvent(Integer id) throws Exception {
        String queryString = "DELETE FROM Event event where event.eventId = :eventId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("eventId", id);
        query.executeUpdate();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public void deleteEventDetail(Integer id) throws Exception {
        String queryString = "DELETE FROM EventDetail eventDetail where eventDetail.event.eventId = :eventId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("eventId", id);
        query.executeUpdate();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public void deleteBulkEventDetails(Integer eventId) throws Exception {
        String queryString = "DELETE FROM EventDetail eventDetail WHERE eventDetail.event.eventId=:eventId ";
        Query query = getCurrentSession().createQuery(queryString);
        query.setParameter("eventId", eventId);
        query.executeUpdate();
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public void saveBulkEventDetails(List<EventDetail> eventDetailList, Event event) throws Exception {
        Session session = getCurrentSession();
        for (Integer recordCounter = 0; recordCounter < eventDetailList.size(); recordCounter++) {
            EventDetail detail = eventDetailList.get(recordCounter);
            detail.setEvent(event);
            session.save(detail);
            if (recordCounter % 10 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    /*
     ********************************************************************************************
     ********************************************************************************************
     ********************************************************************************************
     */
    public List<Event> getEventByName(String eventName, Integer id) throws Exception {
        String queryString = "SELECT DISTINCT event FROM Event event WHERE event.eventTitle=:eventName ";
        if (id != null && id != 0) {
            queryString += " and event.eventId != " + id;
        }
        Query query = getCurrentSession().createQuery(queryString);
        query.setParameter("eventName", eventName);
        List<Event> eventList = query.list();
        return eventList;
    }

    public Object findById(Class clazz, Integer id) {
        Object o = getCurrentSession().get(clazz, id);
        return o;
    }

    public Response getResponseTitleById(Integer userId) throws Exception {
        Response db = null;
        Object o = this.getCurrentSession().get(Response.class, userId);
        if (o != null) {
            db = (Response) o;
        }
        return db;
    }

    public Response getResponseTitleByName(String name, Integer id) throws Exception {
        Response db = null;
        String queryString = "from Response response where response.responseTitle ='" + name + "'";
        if (id != null && id != 0) {
            queryString += " and response.responseId != " + id;
        }
        Query query = getCurrentSession().createQuery(queryString);
        Object queryResult = query.uniqueResult();
        if (queryResult != null) {
            db = (Response) queryResult;
        }
        return db;
    }

    public List<Response> findAllResponseSetup() throws Exception {
        return getCurrentSession().createQuery("select distinct response from Response response "
                + "left join fetch response.validResponses validResponses order by validResponses.validWord ASC").list();
    }

    public LinkedHashMap<String, String> irfFieldList() throws Exception {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'InstantRedemption' ORDER BY column_name";
        try {
            Query query = getCurrentSession().createSQLQuery(sql);
            List<String> queryList = query.list();

            for (String result : queryList) {
                list.put(result, result);
            }
            System.out.println("Query is : " + sql);
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findSMTPByEmail", e);
        }

        return list;
    }

    public LinkedHashMap<String, String> drfFieldList() throws Exception {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'DailyRedemption' ORDER BY column_name";
        try {
            Query query = getCurrentSession().createSQLQuery(sql);
            List<String> queryList = query.list();

            for (String result : queryList) {
                list.put(result, result);
            }
            System.out.println("Query is : " + sql);
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findSMTPByEmail", e);
        }
        return list;
    }

    public List<SmtpServerInfo> findAllSmtpSetup() throws Exception {
        return getCurrentSession().createQuery("from SmtpServerInfo smtpServerInfo").list();
    }

    public SmtpServerInfo getSmtpServerById(Integer userId) throws Exception {
        SmtpServerInfo smtpServerInfo = null;
        Object o = this.getCurrentSession().get(SmtpServerInfo.class, userId);
        if (o != null) {
            smtpServerInfo = (SmtpServerInfo) o;
        }
        return smtpServerInfo;
    }

    public boolean findSMTPByEmail(String email, Integer id) throws Exception {
        boolean found = false;
        String hql = "From SmtpServerInfo ind where ind.fromEmail = '" + email + "'";
        if (id != null && id != 0) {
            hql += " and ind.smtpId != " + id;
        }
        try {

            Query query = getCurrentSession().createQuery(hql);

            if (!query.list().isEmpty()) {
                found = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findSMTPByEmail", e);
        }

        return found;
    }

    public List<ShortCodes> findAllShortCodeSetup() throws Exception {
        return getCurrentSession().createQuery("select distinct shortCodes from ShortCodes shortCodes").list();
    }

    public ShortCodes getShortCodeByCode(Integer code) throws Exception {
        ShortCodes sc;
        String queryString = "from ShortCodes where ShortCode = :ShortCode";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("ShortCode", code);
        Object queryResult = query.uniqueResult();
        sc = (ShortCodes) queryResult;
        return sc;
    }

    public Event getEventById(Integer userId) throws Exception {
        Event event = null;
        String queryString = "select distinct event from Event event "
                + "left join fetch event.eventDetails eventDetails where event.eventId = :eventId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("eventId", userId);
        Object o = query.uniqueResult();
        if (o != null) {
            event = (Event) o;
        }
        return event;
    }

    public List<WidgetUser> findAllWidgetUser(String userId) throws Exception {

        String sql = "SELECT campaignId FROM CampaignsHasDrugBrand WHERE drugBrandId IN ("
                + " SELECT drugBrandId FROM DrugBrand WHERE brandId IN ("
                + " SELECT brandId FROM UserBrand WHERE userId = " + userId + "))";

        Query query = getCurrentSession().createSQLQuery(sql);
        List<String> campaings = query.list();

        String inClause = StringUtils.collectionToDelimitedString(campaings, ",");
        List<WidgetUser> list = null;

        if (inClause != null && inClause.length() != 0) {
            list = (List<WidgetUser>) getCurrentSession().createQuery("select distinct widgetUser from WidgetUser widgetUser left join widgetUser.widgetUserIpaddresseses widgetUserIPAddresses order by widgetUserIPAddresses.widgetUserIpaddressId ASC").list();
        }
        return list;
    }

    public WidgetUser getWidgetUserById(Integer userId) throws Exception {
        WidgetUser db = null;
        Object o = this.getCurrentSession().get(WidgetUser.class, userId);
        if (o != null) {
            db = (WidgetUser) o;
        }
        return db;
    }

    public List<WidgetUser> isUserNameValid(String usernName, Integer userId) throws Exception {
        String hql = "select userName from WidgetUser widgetUser where 1=1 ";
        if (usernName != null && usernName.trim().length() > 0) {
            hql = hql + " and widgetUser.userName = '" + usernName + "'";
        }

        if (userId != null && userId > 0) {
            hql = hql + " and widgetUser.widgetUserId != '" + userId + "' ";
        }

        Query query = getCurrentSession().createQuery(hql);
        List<WidgetUser> list = query.list();
        return list;
    }

    public List<Campaigns> findAllCampaigns(String userId) {
        List<Campaigns> campaigns = (List<Campaigns>) getCurrentSession().createQuery("from Campaigns campaigns order by campaigns.campaignName ASC").list();
        return campaigns;
    }

    public void deleteWidgetUserIpById(Integer id) throws Exception {
        String queryString = "DELETE FROM WidgetUserIPAddresses where widgetUserId = :widgetUserId";
        Query query = getCurrentSession().createSQLQuery(queryString);
        query.setInteger("widgetUserId", id);
        query.executeUpdate();
    }

    public List<UserBrand> getUserBrandByUserId(int userId) {

        List<UserBrand> userBrands = new ArrayList<>();
        try {

            String hql = "select usr from UserBrand usr "
                    + "where "
                    + "usr.userId = " + userId;

            userBrands = (List<UserBrand>) getCurrentSession().createQuery(hql).list();

        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> getUserBrandByUserId", e);
        }

        return userBrands;
    }

    public boolean findBrandByTitle(String title, Integer id) throws Exception {
        boolean found = false;
        String hql = "From Brand ind where ind.brandTitle = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and ind.brandId != " + id;
        }
        try {

            Query query = getCurrentSession().createQuery(hql);

            if (!query.list().isEmpty()) {
                found = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findBrandByTitle", e);
        }

        return found;
    }

    public boolean findCommunciationFolderByName(String title, Integer id) throws Exception {
        boolean found = false;
        String hql = "From Folder ind where ind.folderName = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and ind.folderId != " + id;
        }
        try {

            Query query = getCurrentSession().createQuery(hql);

            if (!query.list().isEmpty()) {
                found = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findCommunciationFolderByName", e);
        }

        return found;
    }

    public boolean deleteMessageType(Integer id) throws Exception {
        boolean deleted = false;

        String hql = "Delete from MessageType ind where ind.id.messageTypeId = '" + id + "'";
        try {
            getCurrentSession().createQuery(hql).executeUpdate();
            deleted = true;

        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> deleteMessageType", e);
        }

        return deleted;
    }

    public List<Drug> findNdcNumber(List<String> ndcNumber, Integer id) throws Exception {
        if (ndcNumber != null && !ndcNumber.isEmpty()) {
            String inClause = StringUtils.collectionToDelimitedString(ndcNumber, ",", "'", "'");
            String hq = "from Drug drug  where drug.ndcnumber in (" + inClause + ")";
            if (id != null && id != 0) {
                hq += " and drug.drugBrand.id != " + id;
            }
            return (List<Drug>) getCurrentSession().createQuery(hq).list();
        } else {
            return new ArrayList<Drug>();
        }
    }

    public boolean findGenericName(String name, Integer id) throws Exception {
        boolean found = false;
        String hql = "From DrugBrand drugBrand where drugBrand.genericName = '" + name + "'";
        if (id != null && id != 0) {
            hql += " and drugBrand.id != " + id;
        }
        try {

            Query query = getCurrentSession().createQuery(hql);

            if (!query.list().isEmpty()) {
                found = true;
            }
        } catch (HibernateException e) {
            logger.error("Exception: SetupsDAO -> findDrugBrandById", e);
        }

        return found;
    }

    public List<EventDetail> getAllEventDetail() throws Exception {
        return getCurrentSession().createQuery("select distinct eventdetail from EventDetail eventdetail").list();
    }

    public Campaigns getCampaignById(Integer userId) throws Exception {
        Campaigns campaigns = null;
        Object o = this.getCurrentSession().get(Campaigns.class, userId);
        if (o != null) {
            campaigns = (Campaigns) o;
        }
        return campaigns;
    }

    public boolean isMessTypeExit(int id, int folderId, String name, String type) throws Exception {
        boolean found = false;
        String hql = "SELECT messageTypeTitle,folderId FROM MessageType Where messageTypeId <>'" + id + "' and folderId = '" + folderId + "' and messageTypeTitle='" + name + "' and messageType='" + type + "'";
        Query query = getCurrentSession().createSQLQuery(hql);

        if (!query.list().isEmpty()) {
            found = true;
        }

        return found;
    }

    public List<DrugBrand> getDrugBrands() throws Exception {
        return getCurrentSession().createQuery("From DrugBrand drugBrand").list();
    }

    public void deleteDrugIngredeintByDrugId(Integer drugId) throws Exception {
        Query query = getCurrentSession().createQuery("delete from DrugIngredient drugIngredient where drugIngredient.drug.drugId=:drugId");
        query.setParameter("drugId", drugId);
        query.executeUpdate();
    }

    public List<Drug> getDrugIngredientList() throws Exception {
        return getCurrentSession().createQuery("select distinct drug from Drug drug inner join fetch drug.drugIngredients drgIngredients inner join fetch drug.drugBrand inner join fetch drgIngredients.ingredients").list();
    }

    public Drug getDrugCompoundById(Integer id) throws Exception {
        Drug drug = new Drug();
        Query query = getCurrentSession().createQuery("from Drug drug left join fetch drug.drugIngredients drugIngredients left join fetch drug.drugBrand drugBrand "
                + "where drug.drugId=:drugId");
        query.setParameter("drugId", id);
        Object object = query.uniqueResult();
        if (object != null) {
            drug = (Drug) object;
        }
        return drug;
    }

    public boolean checkDuplicateIngredients(String title, Integer id) throws Exception {
        String hql = "From Ingredients ingredients where ingredients.name = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and ingredients.id != " + id;
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean deleteDrugs(Integer drugId) throws Exception {
        Query query = getCurrentSession().createQuery("Delete From Drug drug where drug.drugId =:drugId");
        query.setParameter("drugId", drugId);
        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Pharmacy> getPharmaciesList() throws Exception {
        return getCurrentSession().createQuery("select distinct pharmacy From Pharmacy pharmacy left join fetch pharmacy.state state left join fetch pharmacy.pharmacyLookup pharmacyLookup").list();
    }

    public Pharmacy getPharmacy(Integer id) throws Exception {
        Pharmacy pharmacy = new Pharmacy();
        Query query = getCurrentSession().createQuery("From Pharmacy pharmacy left join fetch pharmacy.state state where pharmacy.id=:id");
        query.setParameter("id", id);
        Object result = query.uniqueResult();
        if (result != null) {
            pharmacy = (Pharmacy) result;
        }
        return pharmacy;
    }

    public boolean checkNPIDuplicate(Long npi, Integer id) throws Exception {
        String hql = "From Pharmacy pharmacy where pharmacy.npi = '" + npi + "'";
        if (id != null && id != 0) {
            hql += " and pharmacy.id != " + id;
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean checkDuplicatePharmacyTitle(String title, Integer id) throws Exception {
        String hql = "From Pharmacy pharmacy where pharmacy.title = '" + title + "'";
        if (id != null && id != 0) {
            hql += " and pharmacy.id != " + id;
        }
        Query query = getCurrentSession().createQuery(hql);
        if (query.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<State> getStatesList() throws Exception {
        return getCurrentSession().createQuery("From State state").list();
    }

    public List<Survey> getSurveyListByCampaignId(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("from Survey survy left join fetch survy.campaignses camp where camp.campaignId=:id");
        query.setParameter("id", id);
        return query.list();
    }
    
     public List getPatientWithoutOrder() throws Exception 
     {
        Query query = getCurrentSession().createQuery("select distinct pf ,pd from PatientProfile pf left join pf.orders o left join pf.patientDeliveryAddresses pd where o is null order by pf.firstName,pf.lastName ");
                                                       //+" where pf.orders is null");
       
        return query.list();
    }

    public boolean checkDuplicateSortOrder(Integer messageTypeId, Integer folderId, Integer sortOrder) throws Exception {
        boolean found = false;
        String hql = "SELECT sortOrder FROM MessageType Where messageTypeId <>'" + messageTypeId + "' and folderId = '" + folderId + "' and sortOrder='" + sortOrder + "'";
        Query query = getCurrentSession().createSQLQuery(hql);

        if (!query.list().isEmpty()) {
            found = true;
        }

        return found;

    }

    public boolean updateMessageType(MessageType messageType) throws Exception {
        Query query = getCurrentSession().createSQLQuery("Update MessageType set SortOrder=:sortOrder where MessageTypeId=:messageTypeId");
        query.setParameter("sortOrder", messageType.getSortOrder());
        query.setParameter("messageTypeId", messageType.getId().getMessageTypeId());
        int result = query.executeUpdate();
        if (result > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

//    public List<DrugUnits> getDrugUnitsList() throws Exception {
//        return getCurrentSession().createQuery("From DrugUnits drugUnits").list();
//    }

    public List<RewardPoints> getRewardPointsList() throws Exception {
        return getCurrentSession().createQuery("From RewardPoints rewardPoints ").list();
    }

    public List<FeeSettings> getFeeSettingsList() throws Exception {
        return getCurrentSession().createQuery("From FeeSettings feeSettings ").list();
    }

    public List<DeliveryPreferences> getDeliveryPreferencesList() throws Exception {
        return getCurrentSession().createQuery("From DeliveryPreferences deliveryPreferences").list();
    }

    public TransferDetail getTransferDetailByRequestId(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From TransferDetail transferDetail where transferDetail.requestId=:id");
        query.setParameter("id", id);
        return (TransferDetail) query.uniqueResult();
    }

    public TransferRequest getTransferRequestById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From TransferRequest transferRequest where transferRequest.id=:id");
        query.setParameter("id", id);
        return (TransferRequest) query.uniqueResult();
    }

    public PatientProfile getPatientProfileById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile patientProfile where patientProfile.id=:id");
        query.setParameter("id", id);
        return (PatientProfile) query.uniqueResult();
    }

    public TransferDetail getTransferDetailById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From TransferDetail transferDetail where transferDetail.id=:id");
        query.setParameter("id", id);
        return (TransferDetail) query.uniqueResult();
    }

    public boolean isRxNumberExist(String rxNumber, Integer id) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(TransferDetail.class);
        criteria.add(Restrictions.eq("rxNumber", rxNumber));
        if (id != null) {
            criteria.add(Restrictions.ne("id", id));
        }
        if (criteria.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<Drug> getDrugsByDrugBrandName(String drugName) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.drugBrand.genericName=:drugName");
        query.setParameter("drugName", drugName);
        return query.list();
    }

    public List<DrugAdditionalMargin> getDrugAdditionalMarginList() throws Exception {
        Query query = getCurrentSession().createQuery("select distinct drugAdditionalMargin From DrugAdditionalMargin drugAdditionalMargin left join fetch drugAdditionalMargin.drugAdditionalMarginPricesList left join fetch drugAdditionalMargin.drugCategory drugCategory ");
        return query.list();
    }

    public DrugAdditionalMargin getDrugAdditionalMarginById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("select distinct drugAdditionalMargin From DrugAdditionalMargin drugAdditionalMargin left join fetch drugAdditionalMargin.drugAdditionalMarginPricesList left join fetch drugAdditionalMargin.drugCategory drugCategory where drugAdditionalMargin.id=:id");
        query.setParameter("id", id);
        return (DrugAdditionalMargin) query.uniqueResult();
    }

    public List<DrugAdditionalMarginPrices> getDrugAdditionalMarginPriceses(Integer drugCategoryId) throws Exception {
        Query query = getCurrentSession().createQuery("select distinct drugAdditionalMarginPrices from DrugAdditionalMarginPrices drugAdditionalMarginPrices left join fetch drugAdditionalMarginPrices.drugAdditionalMargin drugAdditionalMargin left join fetch drugAdditionalMargin.drugCategory drugCategory where drugCategory.id=:drugCategoryId");
        query.setParameter("drugCategoryId", drugCategoryId);
        return query.list();
    }

//    public List<DrugCategory> getDrugCategoryList() throws Exception {
//        return getCurrentSession().createQuery("select distinct drugCategory From DrugCategory drugCategory "
//                + " order by drugCategory.drugCategoryName asc").list();
//    }

    public List<PharmacyZipCodes> getPharmacyZipCodesList() throws Exception {
        return getCurrentSession().createQuery("select distinct pharmacyZipCodes From PharmacyZipCodes pharmacyZipCodes left join fetch pharmacyZipCodes.deliveryDistanceFeesList deliveryDistanceFeesList left join fetch deliveryDistanceFeesList.deliveryDistances deliveryDistances left join fetch deliveryDistanceFeesList.deliveryPreferenceses deliveryPreferenceses ").list();
    }

    public PharmacyZipCodes getPharmacyZipCodesById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("select distinct pharmacyZipCodes From PharmacyZipCodes pharmacyZipCodes left join fetch pharmacyZipCodes.deliveryDistanceFeesList deliveryDistanceFeesList where pharmacyZipCodes.id=:id");
        query.setParameter("id", id);
        return (PharmacyZipCodes) query.uniqueResult();
    }

    public List<DeliveryPreferencesDistance> getDeliveryPreferencesDistanceById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("From DeliveryPreferencesDistance deliveryPreferencesDistance left join fetch deliveryPreferencesDistance.pharmacyZipCodes pharmacyZipCodes where pharmacyZipCodes.id=:id");
        query.setParameter("id", id);
        return query.list();
    }

    public boolean deleteDeliveryDistances(Integer id) throws Exception {
        SQLQuery sQLQuery = getCurrentSession().createSQLQuery("DELETE FROM `DeliveryDistances` WHERE Id=?");
        sQLQuery.setParameter(0, id);
        if (sQLQuery.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean updateDeliveryPreferences(Integer id, String description, Integer currentUserId) throws Exception {
        Query query = getCurrentSession().createQuery("Update DeliveryPreferences Set description=:description,updatedBy=:updatedBy,updatedOn=:updatedOn where id=:id");
        query.setParameter("description", description);
        query.setParameter("updatedBy", currentUserId);
        query.setParameter("updatedOn", new Date());
        query.setParameter("id", id);

        if (query.executeUpdate() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<DeliveryDistanceFee> getDeliveryDistanceFeesByDistanceId(Integer id, Integer dprefId) throws Exception {
        Query query = getCurrentSession().createQuery("From DeliveryDistanceFee deliveryDistanceFee left join fetch deliveryDistanceFee.deliveryDistances deliveryDistances left join fetch deliveryDistanceFee.deliveryPreferenceses deliveryPreferenceses where deliveryDistances.id=:id and deliveryPreferenceses.id=:dprefId");
        query.setParameter("id", id);
        query.setParameter("dprefId", dprefId);
        return query.list();
    }

    public void deleteDeliveryDistanceFeeByPharmacyId(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("Delete From DeliveryDistanceFee deliveryDistanceFee where deliveryDistanceFee.pharmacyZipCodes.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
