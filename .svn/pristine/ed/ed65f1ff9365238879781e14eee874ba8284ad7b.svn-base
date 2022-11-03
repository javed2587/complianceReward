package com.ssa.cms.dao;

//import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.ssa.cms.common.Constants;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.SearchDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.ssa.cms.dto.TransferRxQueueDTO;
import com.ssa.cms.model.Drug;
import com.ssa.cms.model.MessageType;
import com.ssa.cms.model.Order;
import com.ssa.cms.model.OrderTransferImages;
import com.ssa.cms.model.PatientProfile;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.PharmacyLookup;
import com.ssa.cms.model.PharmacyType;
import com.ssa.cms.model.PharmacyUser;
import com.ssa.cms.model.RewardHistory;
import com.ssa.cms.model.SmtpServerInfo;
import com.ssa.cms.model.TransferDetail;
import com.ssa.cms.model.TransferRequest;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import com.ssa.cms.util.EncryptionHandlerUtil;
import com.sun.xml.ws.util.StringUtils;
import java.math.BigInteger;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Hibernate;

/**
 *
 * @author msheraz
 */
@Repository
public class ConsumerRegistrationDAO extends BaseDAO implements Serializable {

//    @Autowired
//    private SessionFactory sessionFactory;
    private final Log logger = LogFactory.getLog(getClass());
//
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

//    public void save(Object bean) throws Exception {
//
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
    public List<PharmacyType> getAllPharmacyTypes() {
        Query query = getCurrentSession().createQuery("from PharmacyType pharmacyType");
        return query.list();
    }

    public PharmacyLookup lookupPharmacy(String npi, String sitePassNumber, int typeId) {
        Query query = getCurrentSession().createQuery("from PharmacyLookup pharmacyLookup inner join fetch pharmacyLookup.pharmacyType inner join fetch pharmacyLookup.state where pharmacyLookup.npi=:npi and pharmacyLookup.passNumber=:sitePassNumber and pharmacyLookup.pharmacyType.id=:typeId");
        query.setParameter("npi", npi);
        query.setParameter("sitePassNumber", sitePassNumber);
        query.setParameter("typeId", typeId);
        return (PharmacyLookup) query.uniqueResult();
    }

    public PharmacyLookup lookupPharmacyObj(int pharmacyId) {
        Query query = getCurrentSession().createQuery("From PharmacyLookup pharmacyLookup"
                + "   where pharmacyLookup.id = ("
                + " From Pharmacy p where p.id =:pharmacyId");
        query.setParameter("pharmacyId", pharmacyId);
        return (PharmacyLookup) query.uniqueResult();
    }

    public PharmacyLookup PharmacylookupbyId(int id) {
        Query query = getCurrentSession().createQuery("From PharmacyLookup pharmacyLookup  where pharmacyLookup.id =:id");
        query.setParameter("id", id);
        return (PharmacyLookup) query.uniqueResult();
    }

    public boolean isEmailAddressUnique(String email) {
        Boolean emailExist = Boolean.TRUE;
        Query query = getCurrentSession().createQuery("from PharmacyUser pharmacyUser where pharmacyUser.email=:email");
        query.setParameter("email", email);
        Object result = query.uniqueResult();
        if (result == null) {
            emailExist = Boolean.FALSE;
        }

        return emailExist;
    }

    public PharmacyUser getPharmacyUserByEmail(String email) {
        Query query = getCurrentSession().createQuery("from PharmacyUser pharmacyUser inner join fetch pharmacyUser.pharmacy pharmacy inner join fetch pharmacy.state where pharmacyUser.email=:email");
        query.setParameter("email", email);

        return (PharmacyUser) query.uniqueResult();
    }

    public int getPharmacyUserByPharmacyId(int id, String role) {
        SQLQuery query = getCurrentSession().createSQLQuery("SELECT COUNT(*) AS COUNT FROM PharmacyUser pu INNER JOIN Pharmacy p ON pu.`PharmacyId` = p.`Id` WHERE p.id=:id and pu.role=:role").addScalar("COUNT", LongType.INSTANCE);
        query.setParameter("id", id);
        query.setParameter("role", role);
        List<Long> result = query.list();
        Long str = 0l;
        for (int i = 0; i < result.size(); i++) {
            str = (Long) result.get(i);
        }
        return Integer.parseInt(str + "");
    }

    public Set getPharmacyUserList(int pharmacyId) {
        Query query = getCurrentSession().createQuery("from PharmacyUser pharmacyUser where pharmacyUser.pharmacy.id=:id order by id asc");
        query.setParameter("id", pharmacyId);

        return new LinkedHashSet(query.list());
    }

    public Set getPharmacyFacilityOperations(int pharmacyId) {
        Query query = getCurrentSession().createQuery("from PharmacyFacilityOperation pharmacyFacilityOperation where pharmacyFacilityOperation.pharmacy.id=:id order by id asc");
        query.setParameter("id", pharmacyId);

        return new LinkedHashSet(query.list());
    }

    public SmtpServerInfo getSmtpServer(String fromEmail) {
        Query query = getCurrentSession().createQuery("from SmtpServerInfo smtpServerInfo where smtpServerInfo.fromEmail=:fromEmail");
        query.setParameter("fromEmail", fromEmail);

        return (SmtpServerInfo) query.uniqueResult();
    }

    public Pharmacy getPharmacyByEmail(String email) {
        Query query = getCurrentSession().createQuery("from Pharmacy pharmacy where pharmacy.email=:email");
        query.setParameter("email", email);

        return (Pharmacy) query.uniqueResult();
    }

    public List<Pharmacy> getPharmacyByNpi(long npi, Integer id) {
        Query query = getCurrentSession().createQuery("from Pharmacy pharmacy left join pharmacy.pharmacyLookup pharmacyLook where pharmacy.npi=:npi and pharmacyLook.id=:id");
        query.setParameter("npi", npi);
        query.setParameter("id", id);
        return query.list();
    }

    public Pharmacy getPharmacyById(int id) {
        Query query = getCurrentSession().createQuery("FROM Pharmacy pharmacy LEFT JOIN FETCH pharmacy.state s WHERE pharmacy.state.id=s.id AND  pharmacy.id=:id");
        query.setParameter("id", id);

        return (Pharmacy) query.uniqueResult();
    }

    public int getOrderCount(int pharmacyId, int orderStatus) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        if (pharmacyId > 0) {
            filterCriteria.add(Restrictions.eq("pharmacy.id", pharmacyId));
            filterCriteria.add(Restrictions.disjunction().add(Restrictions.eq("orderStatus.id", orderStatus)));
        }
        return filterCriteria.list().size();
    }

    public int getTotalRxProcessedByPharmacy(int pharmacyId) throws Exception {
        String sql = "SELECT"
                + "  * "
                + "FROM"
                + "  DailyRedemption"
                + "  INNER JOIN Pharmacy"
                + "    ON Pharmacy.NPI = DailyRedemption.Pharmacy_NPI "
                + "WHERE Pharmacy.Id = '" + pharmacyId + "' ";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        return query.list().size();

    }

    public double getOrderRevenueByPharmacy(int pharmacyId) throws Exception {
        double totalAmount = 0.0;
        String sql = "SELECT"
                + "  SUM(Payment) AS TotalRevenue "
                + "FROM"
                + "  Orders"
                + "  INNER JOIN Pharmacy"
                + "    ON Pharmacy.Id = Orders.PharmacyId "
                + "WHERE Orders.PharmacyId = '" + pharmacyId + "' ";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> queryResult = query.list();
        if (queryResult != null && (!queryResult.isEmpty())) {
            Object object = queryResult.get(0);
            totalAmount = Double.parseDouble(object.toString());
        }
        return totalAmount;
    }

    public void deletePharmacyUsers(List<PharmacyUser> pharmacyUsersToBeDeleted) {
        Integer counter = 0;
        for (PharmacyUser pharmacyUser : pharmacyUsersToBeDeleted) {
            getCurrentSession().delete(pharmacyUser);
            if (counter % 10 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    public double getIngredientCostByPharmacy(int pharmacyId) throws Exception {
        double totalAmount = 0.0;
        String sql = "SELECT SUM(AWP_Current_Pkg_Price) AS payment "
                + "FROM Drug "
                + "  INNER JOIN RedemptionIngredient "
                + "    ON RedemptionIngredient.Ndc = Drug.NDCNumber "
                + "    INNER JOIN Orders ON Orders.TransactionNumber=RedemptionIngredient.TransactionNumber "
                + "    INNER JOIN Pharmacy "
                + "    ON Pharmacy.Id = Orders.PharmacyId "
                + "    INNER JOIN OrderStatus ON OrderStatus.Id=Orders.OrderStatus "
                + "    WHERE Orders.PharmacyId='" + pharmacyId + "' AND Orders.OrderStatus='" + 4 + "'";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> queryResult = query.list();
        if (queryResult != null && (!queryResult.isEmpty())) {
            Object object = queryResult.get(0);
            totalAmount = Double.parseDouble(object.toString());
        }
        return totalAmount;
    }

    public Drug getDrugByNdc(String ndc) throws Exception {
        Query query = getCurrentSession().createQuery("FROM Drug drug left join fetch drug.drugBrand drugBrand where drug.ndcnumber=:ndc");
        query.setParameter("ndc", ndc);
        return (Drug) query.uniqueResult();
    }

    public List<PharmacyUser> getPharmacyUsersList(Date date) throws Exception {
        Query query = getCurrentSession().createQuery("from PharmacyUser pharmacyUser where DATE(passwordUpdatedOn)=?");
        query.setDate(0, date);
        return query.list();
    }

    public void updatePharmacyUsersPasswordDate(String email, String date) throws Exception {
        Query query = getCurrentSession().createSQLQuery("UPDATE PharmacyUser SET PasswordUpdatedOn='" + date + "' where Email='" + email + "'");
        query.executeUpdate();
    }

    public List<PharmacyUser> getPharmacyUsersList(int hour, String day, int diffHour) throws Exception {
        List<PharmacyUser> list = new ArrayList<>();
        SQLQuery sQLQuery = getCurrentSession().createSQLQuery("SELECT PharmacyUser.PharmacyId,PharmacyUser.SmsNotify,PharmacyUser.EmailNotify,PharmacyUser.Email,PharmacyUser.Phone,PharmacyUser.Role FROM PharmacyUser "
                + " LEFT OUTER JOIN Pharmacy ON PharmacyUser.PharmacyId=Pharmacy.Id "
                + " LEFT OUTER JOIN PharmacyFacilityOperation ON Pharmacy.Id=PharmacyFacilityOperation.PharmacyId "
                + " WHERE PharmacyFacilityOperation.PhoneHoursFrom='" + hour + "' AND PharmacyFacilityOperation.Day= '" + day + "' OR PharmacyFacilityOperation.PhoneHoursTo= '" + diffHour + "'");

        logger.info("Pharmacy User List is: " + sQLQuery);
        List<Object[]> queryResult = sQLQuery.list();
        if (queryResult != null && (!queryResult.isEmpty())) {
            for (Object[] object : queryResult) {
                PharmacyUser pharmacyUser = new PharmacyUser();
                pharmacyUser.setSmsNotify(object[1].toString());
                pharmacyUser.setEmailNotify(object[2].toString());
                pharmacyUser.setEmail(object[3].toString());
                pharmacyUser.setPhone(object[4].toString());
                pharmacyUser.setRole(object[5].toString());
                list.add(pharmacyUser);
            }
        }
        return list;
    }

    public void setPharmacyUserLastLoggedInTime(Integer currentUserId) throws Exception {
        Query query = getCurrentSession().createQuery("Update PharmacyUser set LastLoggedOn=:currentDate where id=:currentUserId");
        query.setParameter("currentDate", DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        query.setParameter("currentUserId", currentUserId);
        query.executeUpdate();
    }

    public MessageType getMessageType(String title) throws Exception {
        Query query = getCurrentSession().createQuery("from MessageType messageType left join fetch messageType.campaignMessageses campaignMessage where messageType.messageTypeTitle=:title");
        query.setParameter("title", title);
        return (MessageType) query.uniqueResult();
    }

    public List<TransferRxQueueDTO> getTranferRxQueue() throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfile p, TransferRequest t where p.id=t.patientId order by t.requestedOn desc");
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        List<Object[]> list = query.list();
        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            TransferRequest transferRequest = (TransferRequest) arr[1];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setRxId(transferRequest.getId());
            transferRxQueueDTO.setReceivedDate(transferRequest.getRequestedOn());
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setRxSearched("test");
            transferRxQueueDTO.setQuantity(transferRequest.getQuantity());
            transferRxQueueDTO.setCashPriceQuated(0.0);
            transferRxQueueDTO.setStatus(patientProfile.getStatus());
            transferRxQueueList.add(transferRxQueueDTO);
        }
        return transferRxQueueList;
    }

    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileTransferRequestTransferDetails() throws Exception {
        Query queryOrder = getCurrentSession().createQuery(" FROM PatientProfile p , Order o "
                + " WHERE  o.patientProfile.id = p.id "
                + // " AND  (o.orderStatus.id =  8 OR o.orderStatus.id = 9 )"+    
                " AND  (lower(o.orderStatus.name)='pending' )"
                + " AND  o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o GROUP BY o.patientProfile.id)"
                + " ORDER BY o.createdOn DESC");

        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        List<Object[]> list = queryOrder.list();

        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            Order orders = (Order) arr[1];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
            if (orders.getQty() != null && !orders.getQty().trim().equalsIgnoreCase("")) {
                transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
            }
            transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
            transferRxQueueDTO.setOrderId(orders.getId());
            transferRxQueueDTO.setRxNumber(orders.getRxNo());
            transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
            transferRxQueueDTO.setStatus((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) ? "OPEN (LOCKED)" : "Pending");
            transferRxQueueDTO.setDisabled((orders.getViewStatus() != null && orders.getViewStatus().equalsIgnoreCase("Opened")) ? "1" : "0");
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueList.add(transferRxQueueDTO);

        }
        return transferRxQueueList;
    }

    //////////////////////////////////////////////////////////////////////////
    public List getTranferRxQueueByPatientProfileTransferRequestTransferDetails2() throws Exception {
        Query queryOrder = getCurrentSession().createQuery(" FROM PatientProfile p , Order o "
                + " WHERE  o.patientProfile.id = p.id "
                + " AND  (lower(o.orderStatus.name)='pending' )"
                + " AND  o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o GROUP BY o.patientProfile.id)"
                + " ORDER BY o.createdOn DESC");

        List<Object[]> list = queryOrder.list();
        return list;

    }

    /////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileOrder(int patientId, String orderId) throws Exception {

        Query queryOrder = getCurrentSession().createQuery(" FROM PatientProfile p , Order o left join fetch o.transferDetail td"
                + " WHERE p.id= o.patientProfile.id "//and o.orderStatus.id=1 "
                + " AND o.patientProfile.id =:patientId   ORDER BY o.createdOn DESC");
        queryOrder.setParameter("patientId", patientId);
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        List<Object[]> list = queryOrder.list();

        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            Order orders = (Order) arr[1];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
            if (orders.getQty() != null && !orders.getQty().trim().equals("")) {
                transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
            }
            transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
            transferRxQueueDTO.setQouted(orders.getDrugPrice() + "");
            transferRxQueueDTO.setOrderId(orders.getId());
            Hibernate.initialize(orders.getTransferDetail());
            if (orders.getTransferDetail() != null) {
                transferRxQueueDTO.setRxNumber(orders.getTransferDetail().getRxNumber());
                transferRxQueueDTO.setRxSearched(
                        orders.getTransferDetail().getDrugName() + " " + orders.getTransferDetail().getStrength());
                transferRxQueueDTO.setPharmacyName(orders.getTransferDetail().getPharmacyName());
                transferRxQueueDTO.setPharmacyPhone(orders.getTransferDetail().getPharmacyPhone());
            } else {
                transferRxQueueDTO.setRxNumber(orders.getRxNo());
                transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
            }

            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());

            transferRxQueueDTO.setStatus(orders.getOrderStatus() != null ? orders.getOrderStatus().getName() : "Pending");//("Pending");
            transferRxQueueDTO.setDisabled(transferRxQueueDTO.getStatus().equals("Pending") ? "0" : "1");
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setOrderType(orders.getOrderType() == null || orders.getOrderType().trim().equals("") ? "" : orders.getOrderType());
            if (orders.getTransferDetail() != null && orders.getTransferDetail().getId() != null) {
                queryOrder = null;
                int requestId = orders.getTransferDetail().getRequestId();
                queryOrder = getCurrentSession().createQuery(" FROM TransferRequest tr "
                        + " WHERE tr.id=:requestId");
                queryOrder.setParameter("requestId", requestId);
                List<TransferRequest> transferRequestList = queryOrder.list();
                for (int i = 0; i < transferRequestList.size(); i++) {
                    TransferRequest transferRequest = (TransferRequest) transferRequestList.get(0);
                    transferRxQueueDTO.setPtVideo((transferRequest.getVideo() == null || transferRequest.getVideo().trim().equals("")) ? "" : transferRequest.getVideo());
                }

            }
            transferRxQueueList.add(transferRxQueueDTO);

        }
        return transferRxQueueList;
    }

    ///////////////////////////////////////////////////////////////////////////
    public List<OrderTransferImages> getNextOrderImagesList(String orderId, Integer id) {
        String hql = " from OrderTransferImages o where id>:id and o.order.id=:orderId order by id ";
        Query queryOrder = getCurrentSession().createQuery(hql);
        queryOrder.setParameter("id", id);

        queryOrder.setParameter("orderId", orderId);

        List list = queryOrder.list();
        return list;
    }

    ///////////////////////////////////////////////////////////////////////////
    public List<OrderTransferImages> getPrevOrderImagesList(String orderId, Integer id) {
        String hql = " from OrderTransferImages o where id<:id and o.order.id=:orderId order by id ";
        Query queryOrder = getCurrentSession().createQuery(hql);
        queryOrder.setParameter("id", id);

        queryOrder.setParameter("orderId", orderId);

        List list = queryOrder.list();
        return list;
    }

    //////////////////////////////////////////////////////////////////////////
    public List getStatuswiseOrders(String orderStatus) throws Exception {

        String hql = " FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd "
                + " left join o.deliveryPreference dp "
                + " left join o.orderStatus os "
                + " left join  o.drugDetail dd "
                + " left join  dd.drugForm df "
                + " left join  dd.drugBasic db "
                + " left join  db.drugGeneric dg "
                + " left join o.patientProfileMembers pm "
                + " WHERE  o.patientProfile.id = p.id "
                + " AND  o.orderStatus.name in (:status) "
                + " order by o.createdOn desc,dp.id desc,o.updatedOn desc,o.careGiverRequest desc,o.isBottleAvailable desc";
        //+ " order by  o.createdOn desc,dp.id asc ";
        // + "  AND pd.defaultAddress='Yes'"
        // //           + " AND  o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o where o.orderStatus.name=:status GROUP BY o.patientProfile.id )"
        //+ " ORDER BY o.createdOn ASC";

        Query queryOrder = getCurrentSession().createQuery(hql);

        queryOrder.setParameter("status", orderStatus);

        List<Object[]> list = queryOrder.list();

//        for(Object[] row : list)
//        {
//             System.out.println("LENGTH "+row.length);
////            System.out.println("Count: " + row[0]+" order "+row[5]);
////            System.out.println(" first name " + row[2]+" last name "+row[3]);
////            System.out.println("drug: " + row[7]+" brand "+row[8]);
//        }        
        return list;

    }

    /////////////////////////////////////////////////////////////////////////
    public List getStatuswiseOrders(String orderStatus, int patientId) throws Exception {

        boolean orderStatusProvided = AppUtil.getSafeStr(orderStatus, "").length() > 0;
        String hql = " FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd "
                + " left join o.deliveryPreference dp "
                + " left join o.orderStatus os "
                + " left join  o.drugDetail dd "
                + " left join  dd.drugForm df "
                + " left join  dd.drugBasic db "
                + " left join  db.drugGeneric dg "
                + " WHERE  o.patientProfile.id = p.id ";
        if (orderStatusProvided) {
            hql += " AND  o.orderStatus.name in (:status) ";
        } else {
            hql += " AND  o.orderStatus.name <> 'Hidden' ";
        }
        hql += " AND o.patientProfile.id=:patientId"
                + " order by o.createdOn desc ";
        // + "  AND pd.defaultAddress='Yes'"
        // //           + " AND  o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o where o.orderStatus.name=:status GROUP BY o.patientProfile.id )"
        //+ " ORDER BY o.createdOn ASC";

        Query queryOrder = getCurrentSession().createQuery(hql);
        if (orderStatusProvided) {
            queryOrder.setParameter("status", orderStatus);
        }
        queryOrder.setParameter("patientId", patientId);

        List<Object[]> list = queryOrder.list();

//        for(Object[] row : list)
//        {
//             System.out.println("LENGTH "+row.length);
////            System.out.println("Count: " + row[0]+" order "+row[5]);
////            System.out.println(" first name " + row[2]+" last name "+row[3]);
////            System.out.println("drug: " + row[7]+" brand "+row[8]);
//        }        
        return list;

    }

    //////////////////////////////////////////////////////////////////////////
    public List getTranferRxQueueByPatientProfileTransferRequestTransferDetails2(String orderStatus, Integer pharmacyId) throws Exception {

        String hql = " FROM PatientProfile p , Order o,PatientDeliveryAddress pd "
                + " WHERE  o.patientProfile.id = p.id "
                + " AND  o.orderStatus.name=:status "
                + " AND pd.patientProfile.id=p.id "
                + "  AND pd.defaultAddress='Yes'";
//        if(orderStatus.equalsIgnoreCase("Pending"))
//        {
//            hql+=" and (o.patientProfileMembers is null or o.patientProfileMembers.isAdult=0 or o.patientProfileMembers.isApproved=1)";// or("
//               // +" ( o.patientProfileMembers.isAdult=1 and o.patientProfileMembers.isApproved=1)or o.patientProfileMembers.isAdult=0 ))";
//        }
        //           + " AND  o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o where o.orderStatus.name=:status GROUP BY o.patientProfile.id )"

        if (orderStatus.equalsIgnoreCase("other")) {
            hql = " FROM PatientProfile p , Order o,PatientDeliveryAddress pd "
                    + " WHERE  o.patientProfile.id = p.id "
                    + " AND  o.orderStatus.name not in('Pending','Shipped','On Hold','Delivery','FILLED') "
                    + " AND pd.patientProfile.id=p.id "
                    + "  AND pd.defaultAddress='Yes'";
            //           + " AND  o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o where o.orderStatus.name  not in('Pending','Shipped') GROUP BY o.patientProfile.id )"
        }
        hql += " AND o.pharmacy.id=:pharmacyId OR o.pharmacy is null ";
        hql += " ORDER BY o.createdOn ASC";
        Query queryOrder = getCurrentSession().createQuery(hql);
        if (!orderStatus.equalsIgnoreCase("other")) {
            queryOrder.setParameter("status", orderStatus);
        }
        queryOrder.setParameter("pharmacyId", pharmacyId);
        List<Object[]> list = queryOrder.list();
        return list;

    }

    /////////////////////////////////////////////////////////////////////////
    public List<TransferRxQueueDTO> getTranferRxQueueByPatientProfileOrder(int patientId, String orderId,
            String orderStatus) throws Exception {

        Query queryOrder = getCurrentSession().createQuery(" FROM PatientProfile p , Order o left join fetch o.transferDetail td"
                + " WHERE p.id= o.patientProfile.id "
                + " AND  (lower(o.orderStatus.name)=:status )"
                + " AND o.patientProfile.id =:patientId   ORDER BY o.createdOn DESC");
        queryOrder.setParameter("patientId", patientId);
        queryOrder.setParameter("status", orderStatus.toLowerCase());
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        List<Object[]> list = queryOrder.list();

        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            Order orders = (Order) arr[1];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setReceivedDate(orders.getCreatedOn());
            if (orders.getQty() != null && !orders.getQty().trim().equals("")) {
                transferRxQueueDTO.setQuantity(Integer.parseInt(orders.getQty()));
            }
            transferRxQueueDTO.setCashPriceQuated(orders.getPayment());//orders.getDrugPrice());
            transferRxQueueDTO.setQouted(orders.getDrugPrice() + "");
            transferRxQueueDTO.setOrderId(orders.getId());
            if (orders.getTransferDetail() != null) {
                transferRxQueueDTO.setRxNumber(orders.getTransferDetail().getRxNumber());
                transferRxQueueDTO.setRxSearched(
                        orders.getTransferDetail().getDrugName() + " " + orders.getTransferDetail().getStrength());
                transferRxQueueDTO.setPharmacyName(orders.getTransferDetail().getPharmacyName());
                transferRxQueueDTO.setPharmacyPhone(orders.getTransferDetail().getPharmacyPhone());
            } else {
                transferRxQueueDTO.setRxNumber(orders.getRxNo());
                transferRxQueueDTO.setRxSearched((CommonUtil.isNullOrEmpty(orders.getDrugName())) ? "" : orders.getDrugName() + " " + (CommonUtil.isNullOrEmpty(orders.getStrength()) ? "" : orders.getStrength()));
            }

            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());

            transferRxQueueDTO.setStatus("Pending");
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setOrderType(orders.getOrderType() == null || orders.getOrderType().trim().equals("") ? "" : orders.getOrderType());
            if (orders.getTransferDetail() != null && orders.getTransferDetail().getId() != null) {
                queryOrder = null;
                int requestId = orders.getTransferDetail().getRequestId();
                queryOrder = getCurrentSession().createQuery(" FROM TransferRequest tr "
                        + " WHERE tr.id=:requestId");
                queryOrder.setParameter("requestId", requestId);
                List<TransferRequest> transferRequestList = queryOrder.list();
                for (int i = 0; i < transferRequestList.size(); i++) {
                    TransferRequest transferRequest = (TransferRequest) transferRequestList.get(0);
                    transferRxQueueDTO.setPtVideo((transferRequest.getVideo() == null || transferRequest.getVideo().trim().equals("")) ? "" : transferRequest.getVideo());
                }

            }
            transferRxQueueList.add(transferRxQueueDTO);

        }
        return transferRxQueueList;
    }

    public List<TransferRxQueueDTO> getTranferDetailListByRequestId(int requestId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfile p, TransferRequest t, TransferDetail td"
                + " WHERE p.id=t.patientId AND t.id = td.requestId AND  td.status ='Transfered' and td.requestId =:requestId order by t.requestedOn desc");

//        Query query = getCurrentSession(). createQuery("FROM TransferDetail td"
//        		+ " WHERE td.requestId =:requestId");
        query.setParameter("requestId", requestId);
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        List<Object[]> list = query.list();
        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            TransferRequest transferRequest = (TransferRequest) arr[1];
            TransferDetail transferDetail = (TransferDetail) arr[2];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setRxId(transferRequest.getId());
            transferRxQueueDTO.setReceivedDate(transferRequest.getRequestedOn());
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setRxSearched(transferDetail.getDrugName() + " " + transferDetail.getStrength());
            transferRxQueueDTO.setQuantity(transferDetail.getQuantity());
            transferRxQueueDTO.setCashPriceQuated(0.0);
            transferRxQueueDTO.setStatus((transferDetail.getPharmacyStatus() != null && transferDetail.getPharmacyStatus().equalsIgnoreCase("Opened")) ? "Pending" : (transferDetail.getStatus().equalsIgnoreCase("Transfered") ? "Pending" : transferDetail.getStatus()));
            transferRxQueueDTO.setDisabled((transferDetail.getPharmacyStatus() != null && transferDetail.getPharmacyStatus().equalsIgnoreCase("Opened")) ? "1" : "0");
            transferRxQueueDTO.setDrug(transferDetail.getDrugName());
            transferRxQueueDTO.setPharmacyName(transferDetail.getPharmacyName());
            transferRxQueueDTO.setPharmacyPhone(transferDetail.getPharmacyPhone());
            transferRxQueueDTO.setRxNumber(transferDetail.getRxNumber());
            transferRxQueueDTO.setPtVideo(transferRequest.getVideo());
            transferRxQueueDTO.setTransferDetailId(transferDetail.getId());

            transferRxQueueList.add(transferRxQueueDTO);
        }
        return transferRxQueueList;
    }

    public List<TransferRxQueueDTO> getTranferDetailListById(int tId, int requestId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfile p, TransferRequest t, TransferDetail td "
                + " WHERE p.id=t.patientId AND t.id = td.requestId AND  td.status ='Transfered' and td.id =:tId and td.requestId =:requestId order by t.requestedOn desc");
        query.setParameter("tId", tId);
        query.setParameter("requestId", requestId);
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        List<Object[]> list = query.list();
        for (Object[] arr : list) {
            String hasRxCard = "";
            TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
            PatientProfile patientProfile = (PatientProfile) arr[0];
            TransferRequest transferRequest = (TransferRequest) arr[1];
            TransferDetail transferDetail = (TransferDetail) arr[2];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setRxId(transferRequest.getId());
            transferRxQueueDTO.setReceivedDate(transferRequest.getRequestedOn());
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setRxSearched(transferDetail.getDrugName() + " " + transferDetail.getStrength());
            transferRxQueueDTO.setQuantity(transferDetail.getQuantity());
            transferRxQueueDTO.setCashPriceQuated(0.0);
            transferRxQueueDTO.setStatus((transferDetail.getPharmacyStatus() != null && transferDetail.getPharmacyStatus().equalsIgnoreCase("Opened")) ? "Pending" : (transferDetail.getStatus().equalsIgnoreCase("Transfered") ? "Pending" : transferDetail.getStatus()));
            transferRxQueueDTO.setDisabled((transferDetail.getPharmacyStatus() != null && transferDetail.getPharmacyStatus().equalsIgnoreCase("Opened")) ? "1" : "0");
            transferRxQueueDTO.setDrug(transferDetail.getDrugName());
            transferRxQueueDTO.setPharmacyName(transferDetail.getPharmacyName());
            transferRxQueueDTO.setPharmacyPhone(transferDetail.getPharmacyPhone());
            transferRxQueueDTO.setRxNumber(transferDetail.getRxNumber());
            transferRxQueueDTO.setPtVideo(transferRequest.getVideo());
            transferRxQueueDTO.setTransferDetailId(transferDetail.getId());

            transferRxQueueList.add(transferRxQueueDTO);
        }
        return transferRxQueueList;
    }

    public TransferRxQueueDTO getTranferDetailListByTransferDetailId(int transferDetailId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfile p, TransferRequest t, TransferDetail td "
                + " WHERE p.id=t.patientId AND t.id = td.requestId AND  td.status ='Transfered' and td.id =:transferDetailId order by t.requestedOn desc");
        query.setParameter("transferDetailId", transferDetailId);
        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
        List<Object[]> list = query.list();
        for (Object[] arr : list) {
            String hasRxCard = "";

            PatientProfile patientProfile = (PatientProfile) arr[0];
            TransferRequest transferRequest = (TransferRequest) arr[1];
            TransferDetail transferDetail = (TransferDetail) arr[2];
            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setRxId(transferRequest.getId());
            transferRxQueueDTO.setReceivedDate(transferRequest.getRequestedOn());
            transferRxQueueDTO.setPatientName(patientProfile.getFirstName() + " " + patientProfile.getLastName());
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());
            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setRxSearched(transferDetail.getDrugName() + " " + transferDetail.getStrength());
            transferRxQueueDTO.setQuantity(transferDetail.getQuantity());
            transferRxQueueDTO.setCashPriceQuated(0.0);
            transferRxQueueDTO.setStatus((transferDetail.getPharmacyStatus() != null && transferDetail.getPharmacyStatus().equalsIgnoreCase("Opened")) ? "Pending" : (transferDetail.getStatus().equalsIgnoreCase("Transfered") ? "Pending" : transferDetail.getStatus()));
            transferRxQueueDTO.setDisabled((transferDetail.getPharmacyStatus() != null && transferDetail.getPharmacyStatus().equalsIgnoreCase("Opened")) ? "1" : "0");
            transferRxQueueDTO.setDrug(transferDetail.getDrugName());
            transferRxQueueDTO.setPharmacyName(transferRequest.getPharmacyName());
            transferRxQueueDTO.setPharmacyPhone(transferRequest.getPharmacyPhone());
            transferRxQueueDTO.setRxNumber(transferDetail.getRxNumber());
            transferRxQueueDTO.setPtVideo(transferRequest.getVideo());
            transferRxQueueDTO.setTransferDetailId(transferDetail.getId());

        }
        return transferRxQueueDTO;
    }

    public TransferRxQueueDTO getOrderDetailListById(String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("FROM PatientProfile p, Order o LEFT JOIN FETCH o.transferDetail td"
                + " WHERE p.id= o.patientProfile.id "
                // + " AND ( o.orderStatus.id = 8 OR o.orderStatus.id = 9) "
                + " AND o.id =:orderId ORDER BY o.createdOn DESC");
        query.setParameter("orderId", orderId);
//        List<TransferRxQueueDTO> transferRxQueueList = new ArrayList<>();
        TransferRxQueueDTO transferRxQueueDTO = new TransferRxQueueDTO();
        List<Object[]> list = query.list();
        for (Object[] arr : list) {
            String hasRxCard = "";

            PatientProfile patientProfile = (PatientProfile) arr[0];
            Order order = (Order) arr[1];

            if (patientProfile.getInsuranceFrontCardPath() != null) {
                hasRxCard = "yes";
            } else {
                hasRxCard = "no";
            }
            transferRxQueueDTO.setPatientId(patientProfile.getPatientProfileSeqNo());
            transferRxQueueDTO.setPatientEnrollmentDate(patientProfile.getCreatedOn());
            long days = DateUtil.dateDiffInDays(patientProfile.getCreatedOn(), new Date());
            transferRxQueueDTO.setIsOldPatient(days > 30);
            /*  transferRxQueueDTO.setRxId(transferRequest.getId());*/
            if (order.getFilledDate() != null) {
                transferRxQueueDTO.setFilledDate(DateUtil.dateToString(order.getFilledDate(), "MM/dd/yyyy"));
            }
            transferRxQueueDTO.setOrderId(order.getId());
            transferRxQueueDTO.setShippedOn(order.getShippedOn());
            // transferRxQueueDTO.setOlversion(order.getOlversion());
            transferRxQueueDTO.setReceivedDate(order.getCreatedOn());
            transferRxQueueDTO.setPatientName(AppUtil.getSafeStr(patientProfile.getFirstName(), "") + " " + AppUtil.getSafeStr(patientProfile.getLastName(), ""));
            transferRxQueueDTO.setPatientDOB(patientProfile.getDob());

            transferRxQueueDTO.setDependentFlag(false);
            transferRxQueueDTO.setAdultFlag(false);

            transferRxQueueDTO.setHasRxCard(hasRxCard);
            transferRxQueueDTO.setRxSearched(order.getDrugName() + " " + order.getStrength());
            transferRxQueueDTO.setStrength(order.getStrength());
            if (order.getQty() != null && !order.getQty().trim().equals("")) {
                transferRxQueueDTO.setQuantity(Integer.parseInt(order.getQty()));
            }
            transferRxQueueDTO.setCashPriceQuated(order.getPayment());//(order.getDrugPrice());
            transferRxQueueDTO.setStatus(order.getOrderStatus().getName());//(order.getViewStatus() != null && order.getViewStatus().equalsIgnoreCase("Opened")) ? "Pending" : (order.getViewStatus().equalsIgnoreCase("Transfered") ? "Pending" : order.getViewStatus()));
            transferRxQueueDTO.setStatusLabel(order.getOrderStatus().getId() != Constants.ORDER_STATUS.RESPONSE_RECEIVED_ID
                    ? order.getOrderStatus().getName() : AppUtil.getSafeStr(order.getPatientResponse(), order.getOrderStatus().getName()));

            if ((AppUtil.getSafeStr(order.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PLACE_QUESTION))
                    && (order.getOrderStatus().getId() == 19)) {
                transferRxQueueDTO.setQuestionAuthorization(true);
            } else {
                transferRxQueueDTO.setQuestionAuthorization(false);
            }

            if ((AppUtil.getSafeStr(order.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.PRE_AUTHORIZE_REFILL)
                    || AppUtil.getSafeStr(order.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.CONTINUE_WAIT))
                    && (order.getOrderStatus().getId() == 19)) {
                transferRxQueueDTO.setPreauthorized(true);
            } else {
                transferRxQueueDTO.setPreauthorized(false);
            }

            transferRxQueueDTO.setStatusId(order.getOrderStatus().getId() != null ? order.getOrderStatus().getId() : 0);
            transferRxQueueDTO.setDisabled((order.getViewStatus() != null && order.getViewStatus().equalsIgnoreCase("Opened")) ? "1" : "0");
            transferRxQueueDTO.setDrug(((CommonUtil.isNullOrEmpty(order.getDrugName())) ? "" : order.getDrugName()) + "  " + ((CommonUtil.isNullOrEmpty(order.getStrength())) ? "" : order.getStrength()));
            if (order.getDrugDetail() != null) {
//                if(order.getDrugDetail().getDrugBasic()!=null && order.get)
                String drugName = AppUtil.getSafeStr(order.getDrugDetail().getDrugBasic().getBrandName(), "")
                        + "{" + AppUtil.getSafeStr(order.getDrugDetail().getDrugBasic().getDrugGeneric().getGenericName(), "") + "}";
                if (order.getDrugDetail().getDrugBasic() != null
                        && AppUtil.getSafeStr(order.getDrugDetail().getDrugBasic().getBrandIndicator(), "").equalsIgnoreCase("BRAND")) {
                    drugName = AppUtil.getSafeStr(order.getDrugDetail().getDrugBasic().getBrandName(), "") + " " + AppUtil.getSafeStr(order.getDrugDetail().getDrugBasic().getDrugGeneric().getGenericName(), "");
                }
                transferRxQueueDTO.setDrugNameWithoutStrength(drugName);
                transferRxQueueDTO.setRxExpiry(order.getDrugDetail().getMonthRxExpiration());
            } else {
                transferRxQueueDTO.setDrugNameWithoutStrength(AppUtil.getSafeStr(order.getDrugName(), ""));
            }
            transferRxQueueDTO.setDrugType(order.getDrugType());
            transferRxQueueDTO.setPharmacyName(order.getPharmacy() != null ? order.getPharmacy().getTitle() : "N/A");
            transferRxQueueDTO.setPharmacyPhone(order.getPharmacy() != null ? order.getPharmacy().getPhone() : "N/A");
           //order.getRxNumber(), "").length() > 0 ? order.getRxNumber() : order.getId());
            String oldRxNumber = AppUtil.getSafeStr(order.getOldRxNumber(), ""); //AppUtil.getSafeStr(order.getRxNumber(), "").length() > 0 ? order.getRxNumber() : order.getOrderChain() != null ? order.getOrderChain().getId().toString() : order.getId();
            transferRxQueueDTO.setOldRxNo(oldRxNumber);//.setRxNumber(AppUtil.getSafeStr(rxNumber, ""));//order.getRxNumber(), "").length() > 0 ? order.getRxNumber() : order.getId());
            transferRxQueueDTO.setHandlingFee(order.getHandLingFee() != null ? order.getHandLingFee() : 0d);
            transferRxQueueDTO.setHandlingFeeStr(AppUtil.roundOffNumberToCurrencyFormat(
                    transferRxQueueDTO.getHandlingFee(), "en", "US"));
            if (order.getHandLingFee() != null && order.getHandLingFee() > 0d) {
                transferRxQueueDTO.setDeliveryFeeWaived("0");
            } else {
                transferRxQueueDTO.setDeliveryFeeWaived("1");
            }
            transferRxQueueDTO.setOrderType(order.getOrderType());

            ////////////////////////////////////////////////////////////////////////////////////////
            transferRxQueueDTO.setPharmacyName(order.getPharmacyName());
            transferRxQueueDTO.setPharmacyPhone(order.getPharmacyPhone());
            transferRxQueueDTO.setPrescriberName(order.getPrescriberName());
            transferRxQueueDTO.setPrescriberNPI(order.getPrescriberNPI());
            transferRxQueueDTO.setPrescriberPhone(order.getPrescriberPhone());
           
            transferRxQueueDTO.setPointsEarned(order.getRewardHistorySet() != null && order.getRewardHistorySet().size() > 0 ? order.getRewardHistorySet().get(0).getPoint() : 0);
            transferRxQueueDTO.setRxAcqCost(order.getRxAcqCost() != null ? order.getRxAcqCost() : 0d);
//            transferRxQueueDTO.setRxReimbCost(order.getRxReimbCost() != null ? order.getRxReimbCost() : 0d);
            transferRxQueueDTO.setOriginalPtCopay(order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d);
            transferRxQueueDTO.setOriginalPtCopayStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getOriginalPtCopay(), "en", "US"));
            transferRxQueueDTO.setAdditionalMargin(order.getAdditionalMargin() != null ? order.getAdditionalMargin() : 0d);
            transferRxQueueDTO.setProfitMargin(order.getProfitMargin() != null ? order.getProfitMargin() : 0d);
//            transferRxQueueDTO.setFinalPayment(order.getFinalPayment() != null && order.getFinalPayment() > 0d
//                    ? order.getFinalPayment() : order.getPayment());//order.getPayment());
            double finalPayment = order.getFinalPayment() != null ? order.getFinalPayment() : 0d;//transferRxQueueDTO.getFinalPayment() != null ? transferRxQueueDTO.getFinalPayment() : 0d;
            double handlingFee = order.getHandLingFee() != null ? order.getHandLingFee() : 0d;
            double paymentExcludingDelivery = finalPayment;//order.getPaymentExcludingDelivery() != null ? order.getPaymentExcludingDelivery() : finalPayment - handlingFee;
            if (paymentExcludingDelivery < 0d) {
                paymentExcludingDelivery = 0d;
            }
            transferRxQueueDTO.setPaymentExcludingDelivery(paymentExcludingDelivery);
            transferRxQueueDTO.setPaymentExcludingDeliveryStr(AppUtil.roundOffNumberToCurrencyFormat(paymentExcludingDelivery, "en", "US"));
            transferRxQueueDTO.setFinalPayment(paymentExcludingDelivery + handlingFee);//order.getPayment());
            transferRxQueueDTO.setFinalPaymentStr(AppUtil.roundOffNumberToCurrencyFormat(
                    transferRxQueueDTO.getFinalPayment(), "en", "US"));
            transferRxQueueDTO.setRedeemPoints(order.getRedeemPoints());
            transferRxQueueDTO.setRedeemPointsCost(order.getRedeemPointsCost());
            transferRxQueueDTO.setCarrier(AppUtil.getSafeStr(order.getDeliverycarrier(), ""));
            transferRxQueueDTO.setShippedOn(order.getShippedOn());
            transferRxQueueDTO.setClerk(AppUtil.getSafeStr(order.getClerk(), ""));
            transferRxQueueDTO.setTracking(AppUtil.getSafeStr(order.getTraclingno(), ""));
            transferRxQueueDTO.setPaymentType(AppUtil.getSafeStr(order.getPaymentType(), ""));
            transferRxQueueDTO.setFinalPaymentMode(AppUtil.getSafeStr(order.getFinalPaymentMode(), ""));
            transferRxQueueDTO.setPaymentTypeDisabled("");//AppUtil.getSafeStr(order.getPaymentType(), "").equalsIgnoreCase("SELF PAY") ? "Disabled" : "");
            transferRxQueueDTO.setSelfPayCheck(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("SELF PAY") ? "checked" : "");
            transferRxQueueDTO.setInsuranceCheck(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("Public") ? "checked" : "");
            transferRxQueueDTO.setInsuranceCheckCommercial(AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("Commercial")
                    || AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equalsIgnoreCase("INSURANCE")
                    || AppUtil.getSafeStr(order.getFinalPaymentMode(), "").equals("") ? "checked" : "");
            Double drugPrice = order.getDrugPrice() != null ? order.getDrugPrice() : 0d;
            Double additionalMargin = order.getAdditionalMargin() != null ? order.getAdditionalMargin() : 0d;
            transferRxQueueDTO.setTotalDrugPrice(order.getPriceIncludingMargins() != null ? order.getPriceIncludingMargins() : drugPrice + additionalMargin);
            transferRxQueueDTO.setPrescriberExtension(AppUtil.getSafeStr(order.getPrescriberExtension(), ""));
            transferRxQueueDTO.setPharmacyExtension(AppUtil.getSafeStr(order.getPharmacyExtension(), ""));
            Double reimb = 0d;
            try {
                String videoStr = AppUtil.getSafeStr(order.getVideo(), "").replace("localhost", Constants.APP_PATH);//"rxdirectdev.ssasoft.com");
                if (videoStr.length() > 0 && !videoStr.contains("http://") && !videoStr.contains("https://")) {
                    videoStr = "http://" + videoStr;
                }
                transferRxQueueDTO.setPtVideo(videoStr);
                String imgStr = AppUtil.getSafeStr(order.getImagePath(), "").replace("localhost", Constants.APP_PATH);// "rxdirectdev.ssasoft.com");
                if (imgStr.length() > 0 && !imgStr.contains("http://") && !imgStr.contains("https://")) {
                    imgStr = "http://" + imgStr;
                }
                transferRxQueueDTO.setTransferImage(imgStr);
                transferRxQueueDTO.setComments(AppUtil.getSafeStr(order.getPatientComments(), ""));
                transferRxQueueDTO.setPaymentMode(AppUtil.getSafeStr(order.getPaymentMode(), "Cash"));
                transferRxQueueDTO.setMfrCost(order.getMfrCost() != null && order.getMfrCost() > 0d ? order.getMfrCost() : order.getMfrCost());
                transferRxQueueDTO.setProfitShareCost(order.getProfitShareCost() != null && order.getProfitShareCost() > 0d ? order.getProfitShareCost() : order.getProfitShareCost());
                transferRxQueueDTO.setProfitSharePoint(order.getProfitSharePoint() != null ? order.getProfitSharePoint() : 0);
                transferRxQueueDTO.setActualProfitShare(order.getActualProfitShare() != null ? order.getActualProfitShare() : 0f);
                transferRxQueueDTO.setActualrofitShareStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getActualProfitShare(), "en", "US"));
                if (order.getDeliveryPreference() != null && order.getDeliveryPreference().getId() != null) {
                    transferRxQueueDTO.setDeliveryService(order.getDeliveryPreference().getName());
                    String svc = AppUtil.getSafeStr(order.getDeliveryPreference().getName(), "").toLowerCase();
                    transferRxQueueDTO.setDeliveryUrgent(svc.indexOf("next day") >= 0 || svc.indexOf("same day") >= 0);
                    transferRxQueueDTO.setDeliveryId(order.getDeliveryPreference().getId());
                } else {
                    transferRxQueueDTO.setDeliveryService("");
                    transferRxQueueDTO.setDeliveryId(0);
                }
                transferRxQueueDTO.setInsuranceType(AppUtil.getSafeStr(order.getInsuranceType(), ""));
                
//                if (order.getOrderChain() != null) {
//
//                    transferRxQueueDTO.setNextRefillDateStr(
//                            DateUtil.dateToString(order.getOrderChain().getNextRefillDate(), "yyyy-MM-dd"));
////                    transferRxQueueDTO.setNextRefillDate(order.getOrderChain().getNextRefillDate());
//
//                }
                /////////////////////////////////////////////////////////
                //reimb = order.getRxReimbCost() != null && order.getRxReimbCost() > 0d ? order.getRxReimbCost() : transferRxQueueDTO.getTotalDrugPrice() - transferRxQueueDTO.getOriginalPtCopay();
                reimb = order.getRxReimbCost() != null && order.getRxReimbCost() > 0d ? order.getRxReimbCost() : 0d;
                reimb = AppUtil.getSafeDouble(AppUtil.roundOffNumberToTwoDecimalPlaces(reimb), 0d);
                double originalPtCopay = order.getOriginalPtCopay() != null ? order.getOriginalPtCopay() : 0d;
                double actualProfitShare = order.getActualProfitShare() != null ? order.getActualProfitShare() : 0d;
                String finalCopayStr = AppUtil.roundOffNumberToTwoDecimalPlaces(originalPtCopay - actualProfitShare);
                transferRxQueueDTO.setFinalCopay(AppUtil.getSafeDouble(finalCopayStr, 0d));
                transferRxQueueDTO.setFinalCopayStr(AppUtil.roundOffNumberToCurrencyFormat(transferRxQueueDTO.getFinalCopay(), "en", "US"));
                //transferRxQueueDTO.setRxReimbCost(reimb);
                if (transferRxQueueDTO.getRxExpiredDate() != null) {
                    transferRxQueueDTO.setRxExpiredDateStr(DateUtil.dateToString(
                            transferRxQueueDTO.getRxExpiredDate(), "MM-dd-yyyy"));
                } else {
                    transferRxQueueDTO.setRxExpiredDateStr("");
                }
                ////////////////////////////////////////////////////////    
            } catch (Exception e) {
                //e.printStackTrace();
            }
            transferRxQueueDTO.setRxReimbCost(reimb);
            transferRxQueueDTO.setOldRxNo(order.getOldRxNumber());
            //transferRxQueueDTO.setPtVideo(AppUtil.getSafeStr(order.getVideo(), ""));
            transferRxQueueDTO.setDrugIncluded(order.getDrugDetail() != null);
            List<RewardHistory> lstReward = order.getRewardHistorySet();
            if (lstReward != null && lstReward.size() > 0) {
                for (RewardHistory reward : lstReward) {
                    if (AppUtil.getSafeStr(reward.getType(), "").equalsIgnoreCase("PLUS")) {
                        transferRxQueueDTO.setAwardedPoints(reward.getPoint() != null ? reward.getPoint().toString() : "0");
                        break;
                    }
                }
            }
            try {
                transferRxQueueDTO.setNextRefillDate(order.getNextRefillDate());//DateUtil.addDaysToDate(new Date(),
                // transferRxQueueDTO.getDaysSupply()));
               
                String requestType = AppUtil.getSafeStr(order.getOrderType(), "");
                if (!requestType.equalsIgnoreCase("Refill")) {
                    if (order.getIsBottleAvailable() != null) {
                        if (order.getIsBottleAvailable() != null && order.getIsBottleAvailable()) {
                            requestType = "X-FER LABEL SCAN";
                            transferRxQueueDTO.setLabelScan(true);
                        } else {
                            requestType = "X-FER RX SCAN";
                            transferRxQueueDTO.setRxScan(true);
                        }
                    } else {
                        if (AppUtil.getSafeStr(order.getPharmacyName(), "").length() > 0
                                || AppUtil.getSafeStr(order.getPharmacyPhone(), "").length() > 0
                                || AppUtil.getSafeStr(order.getOldRxNumber(), "").length() > 0) {
                            requestType = "X-FER LABEL SCAN";
                            transferRxQueueDTO.setLabelScan(true);
                        } else {
                            requestType = "X-FER RX SCAN";
                            transferRxQueueDTO.setRxScan(true);
                        }
                    }
                } else {
                    if (order.getOrderStatus() != null && order.getOrderStatus().getId() == 1) {
                        transferRxQueueDTO.setStatus("Pending Refill");
                        transferRxQueueDTO.setAssignedOrderId(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), ""));
                    }
                }
                transferRxQueueDTO.setRequestType(requestType);
                String status = AppUtil.getSafeStr(transferRxQueueDTO.getStatus(), "");
                if (status.equalsIgnoreCase("DELIVERY") || status.equalsIgnoreCase("Shipped")
                        || status.equalsIgnoreCase("Pickup At Pharmacy") || status.equalsIgnoreCase("Filled")) {
                   
                } else {
                    transferRxQueueDTO.setProcessedRxNo("");
                }
                if (order.getOrderStatus() != null
                        && order.getOrderStatus().getId() == 1 && (AppUtil.getSafeStr(transferRxQueueDTO.getTransferImage(), "").length() > 0
                        || AppUtil.getSafeStr(transferRxQueueDTO.getPtVideo(), "").length() > 0)) {
                    transferRxQueueDTO.setStatus("Unverified Image");
                    transferRxQueueDTO.setStatusLabel("Unverified Image");
                    transferRxQueueDTO.setImageVerificationRequired(true);
                }
//                if (order.getDrugDetail() == null && order.getOrderStatus() != null
//                        && order.getOrderStatus().getId() == 1 && (AppUtil.getSafeStr(transferRxQueueDTO.getTransferImage(), "").length() > 0
//                        || AppUtil.getSafeStr(transferRxQueueDTO.getPtVideo(), "").length() > 0)) {
//                    transferRxQueueDTO.setStatus("Unverified Image");
//                    transferRxQueueDTO.setStatusLabel("Unverified Image");
//                    transferRxQueueDTO.setImageVerificationRequired(true);
//                }
                transferRxQueueDTO.setDisabledFld(order.getDisabledFlds() != null ? order.getDisabledFlds() : 0);
                transferRxQueueDTO.setRequiresDeletion(order.getRequiresDeletion() != null && order.getRequiresDeletion() == 1);
                transferRxQueueDTO.setHandDelivery(order.getHandDelivery() != null ? order.getHandDelivery() : 0);
                transferRxQueueDTO.setHandDeliveryAccepted(order.getHandDeliveryAccepted() != null ? order.getHandDeliveryAccepted() : 0);
                transferRxQueueDTO.setOrderPharmacyId(order.getPharmacy() != null ? order.getPharmacy().getId() : 0);
                transferRxQueueDTO.setPtOverridePrice(order.getPtOverridePrice() != null && order.getPtOverridePrice() == 1);
                transferRxQueueDTO.setPrescriberLastName(AppUtil.getSafeStr(order.getPrescriberLastName(), ""));
                transferRxQueueDTO.setSystemGeneratedRxNumber(AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), ""));
                transferRxQueueDTO.setEstimatedPrice(order.getEstimatedPrice() != null
                        ? order.getEstimatedPrice() : 0d);
                transferRxQueueDTO.setPatientResponse(AppUtil.getSafeStr(order.getPatientResponse(), ""));
                boolean nextRefillDatePopulate = (AppUtil.getSafeStr(order.getPatientResponse(), "").equalsIgnoreCase(
                        Constants.PATIENT_RESPONSES.PRE_AUTHORIZE_REFILL)
                        || AppUtil.getSafeStr(order.getPatientResponse(), "").equalsIgnoreCase(Constants.PATIENT_RESPONSES.CONTINUE_WAIT))
                        && (order.getOrderStatus().getId() == 19);
                if (transferRxQueueDTO.getStatus().equalsIgnoreCase("Filled")
                        || transferRxQueueDTO.getStatus().equalsIgnoreCase("Shipped")
                        || transferRxQueueDTO.getStatus().equalsIgnoreCase("DELIVERY")
                        || transferRxQueueDTO.getStatus().equalsIgnoreCase("Pickup At Pharmacy")
                        || nextRefillDatePopulate) {
                    transferRxQueueDTO.setAssignedOrderId(AppUtil.getSafeStr(order.getRxPrefix(), "") + AppUtil.getSafeStr(order.getSystemGeneratedRxNumber(), ""));
                    ///////////////////////////////////////////////
                    

                    //////////////////////////////////////////////
                } else {
                    if (!AppUtil.getSafeStr(requestType, "").equalsIgnoreCase("Refill")) {
                        transferRxQueueDTO.setAssignedOrderId("NOT ASSIGNED");

                        //TODO transferRxQueueDTO.setNextRefillDateStr("");
                    }
                }
                if (CommonUtil.isNullOrEmpty(transferRxQueueDTO.getNextRefillDateStr()) && order.getNextRefillDate() != null) {
                    transferRxQueueDTO.setNextRefillDateStr(
                            DateUtil.dateToString(order.getNextRefillDate(), "MM-dd-yyyy"));
                }
                transferRxQueueDTO.setPaymentAuthorization(AppUtil.getSafeStr(order.getPaymentAuthorization(), "0"));
                transferRxQueueDTO.setPharmacyId(order.getPharmacy() != null ? order.getPharmacy().getId() : 0);
                transferRxQueueDTO.setReadyToDeliverId(order.getReadyToDeliverRxOrders() != null && order.getReadyToDeliverRxOrders().getId() != null ? order.getReadyToDeliverRxOrders().getId() : 0);
            } catch (Exception e) {

            }
            //transferRxQueueDTO.setAmountWithoutMargin(transferRxQueueDTO.getDrugPrice()-transferRxQueueDTO.getAdditionalMargin());
            //transferRxQueueDTO.setNextRefillDate(DateUtil.addDaysToDate(dto.getStatusCreatedOn(), dto.getDaysSupply()));

            ///////////////////////////////////////////////////////////////////////////////////////
        }
        return transferRxQueueDTO;
    }

    public int getCountTransferDetails() throws Exception {
        Query query = getCurrentSession().createQuery("SELECT COUNT(o.id) as count FROM PatientProfile p, Order o "
                + " WHERE p.id=o.patientProfile.id "
                + " AND ( o.orderStatus.id = 8 OR o.orderStatus.id = 9) "
                + " AND o.createdOn IN (SELECT  MAX(o.createdOn) FROM Order o GROUP BY o.patientProfile.id ) ORDER BY o.createdOn DESC");

        long count = (Long) query.uniqueResult();

        return Integer.parseInt(count + "");
    }

    public void setTransferDetailPharmacyStatus(String status, int id) throws Exception {
        //AND pharmacyStatusCreatedOn=:currentDate 
        Query query = getCurrentSession().createQuery("UPDATE TransferDetail SET pharmacyStatus=:status,pharmacyStatusCreatedOn=:currentDate  where id=:id");
        query.setParameter("status", status);
        query.setParameter("currentDate", new Date());
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void setOrderStatusWithId(String status, String id) throws Exception {

        Query query = getCurrentSession().createQuery("UPDATE Order SET ViewStatus=:status,statusCreatedOn=:currentDate  where id=:id");
        query.setParameter("status", status);
        query.setParameter("currentDate", new Date());
        query.setParameter("id", id);
        query.executeUpdate();
    }

    ///////////////////////////////////////////////////////
    public void updateOrderStatusWithIdAndUser(String status, String id, int userId) throws Exception {

        Query query = getCurrentSession().createQuery("UPDATE Order SET ViewStatus=:status,statusCreatedOn=:currentDate,updatedBy=:updatedBy  where id=:id");
        query.setParameter("status", status);
        query.setParameter("currentDate", new Date());
        query.setParameter("id", id);
        query.setParameter("updatedBy", userId);
        query.executeUpdate();
    }
    ///////////////////////////////////////////////////////

    public void updateOrderViewStatusWithIdAndUser(String viewStatus, String id, int userId, int status) throws Exception {

        Query query = getCurrentSession().createQuery("UPDATE Order SET ViewStatus=:viewStatus,statusCreatedOn=:currentDate,updatedBy=:updatedBy  where id=:id and orderStatus.id=:status");
        query.setParameter("viewStatus", viewStatus);
        query.setParameter("currentDate", new Date());
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.setParameter("updatedBy", userId);
        query.executeUpdate();
    }

    ////////////////////////////////////////////////////////
    public List<TransferRequest> getSelectTransferRx(int id) {
        Query query = getCurrentSession().createQuery("from TransferRequest t where t.patientId=:id");
        query.setParameter("id", id);

        return query.list();
    }

    public List<TransferDetail> getSelectTransferRxListByRequestId(int id) {
        Query query = getCurrentSession().createQuery("from TransferDetail t where t.requestId=:id");
        query.setParameter("id", id);

        return query.list();
    }

    public TransferRequest getSelectTransferRxById(int id) {
        Query query = getCurrentSession().createQuery("from TransferRequest t where t.id=:id");
        query.setParameter("id", id);
        List list = query.list();
        TransferRequest tr = null;
        if (!CommonUtil.isNullOrEmpty(list)) {
            tr = (TransferRequest) list.get(0);
        }

        return tr;
    }

//    public Object saveOrUpdateObject(Object transientInstance) {
//
//        // log.debug("Saving Instance
//        // Class="+transientInstance.getClass().getName());
//        Object result = null;
//        Session session = null;
//        FlushMode flushModule = null;
//        try {
//            // log.debug("going to call saveOrUpdate to save the instance.");
//
//            try {
//                session = this.getCurrentSession();
//                flushModule = session.getFlushMode();
//                session.setFlushMode(FlushMode.AUTO);
//            } catch (RuntimeException e) {
//            }
//
//            session.saveOrUpdate(transientInstance);
//        } catch (Exception e) {
//            try {
//                // logger.debug("Exception occure while saving through
//                // saveorUpdate.Going to call merge to save the instance.");
//                result = session.merge(transientInstance);
//                // logger.debug("merge successful");
//                return result;
//            } catch (RuntimeException re) {
//                e.printStackTrace();
//                // logger.error("merge failed", re);
//                throw re;
//            }
//        } finally {
//            try {
//                if (session != null) {
//                    session.flush();
//                    session.setFlushMode(flushModule);
//                }
//            } catch (HibernateException e) {
//                // TODO Auto-generated catch block
//                // e.printStackTrace();
//                System.out.println("---->EXCEPTION OCCURED:::::" + e.getMessage());
//            }
//        }
//        return result;
//    }
    public int getTransferRListCount(int patientId, String orderId) throws Exception {

        Query queryOrder = getCurrentSession().createQuery("Select Count(o.patientProfile.id) FROM PatientProfile p , Order o "
                + " WHERE p.id= o.patientProfile.id and o.orderStatus.id=1 "
                + " AND o.patientProfile.id =:patientId GROUP BY o.patientProfile.id  ORDER BY o.createdOn DESC");
        queryOrder.setParameter("patientId", patientId);
        int count = ((Long) queryOrder.uniqueResult()).intValue();
        return count;
    }

    /////////////////////////////////////////////////////////////////////////
    public List getTranferRxQueueByPatientProfileOrder2(int patientId, String orderId,
            String orderStatus) throws Exception {

        String hql = " FROM PatientProfile p , Order o left join fetch o.transferDetail td"
                + " WHERE p.id= o.patientProfile.id "
                + " AND  o.orderStatus.name =:status "
                + " AND o.patientProfile.id =:patientId   ORDER BY o.createdOn DESC";
        if (orderStatus.equalsIgnoreCase("other")) {
            hql = " FROM PatientProfile p , Order o left join fetch o.transferDetail td"
                    + " WHERE p.id= o.patientProfile.id "
                    + " AND  o.orderStatus.name not in('Pending','Shipped','On Hold') "
                    + " AND o.patientProfile.id =:patientId   ORDER BY o.createdOn DESC";
        }
        Query queryOrder = getCurrentSession().createQuery(hql);
        queryOrder.setParameter("patientId", patientId);
        if (!orderStatus.equalsIgnoreCase("other")) {
            queryOrder.setParameter("status", orderStatus.toLowerCase());
        }
        List<Object[]> list = queryOrder.list();
        return list;

    }

    /////////////////////////////////////////////////////////////////////////
    public List geNextOrderByPatientProfileOrder(int patientId, String orderId, Date createdDate,
            String orderStatus, int pharmacyId) throws Exception {

        String hql = " FROM  Order o "
                //  + " where o.orderStatus.name =:status "
                + " where o.patientProfile.id =:patientId   ";
//        if(!orderStatus.equalsIgnoreCase("DELIVERY") &&
//                   !orderStatus.equalsIgnoreCase("Shipped") &&
//                   !orderStatus.equalsIgnoreCase("Pickup At Pharmacy"))
//        {
//            hql+=" AND  o.orderStatus.name not in('DELIVERY','Shipped','Pickup At Pharmacy') ";
//                     
//        }
        if (orderStatus == null || orderStatus.equalsIgnoreCase("UNVERIFIED IMAGE")) {
            hql += " AND o.orderStatus.name='Pending' and (o.imagePath is not null OR o.video is not null) and o.drugDetail is null";
        } else {
            hql += " and o.orderStatus.name =:status ";
        }
//        hql+= " AND cast(o.id as integer)>:orderId "
        hql += " AND o.id !=:orderId "
                + " AND (o.pharmacy is null or o.pharmacy.id=:pharmacyId)"
                + " ORDER BY o.createdOn desc";//,o.id asc";
//        if (orderStatus.equalsIgnoreCase("other")) {
//            hql = " FROM  Order o "
//                    + " WHERE p.id= o.patientProfile.id "
//                    + " AND  o.orderStatus.name not in('Pending','Shipped','On Hold') "
//                    + " AND cast(o.id as integer)>:orderId "
//                    //                + " AND o.createdOn>=:createdDate  "
//                    //                + " AND o.id<>:orderId "
//                    + " AND o.patientProfile.id =:patientId   "
//                    + " ORDER BY o.createdOn DESC";
//        }
        Query queryOrder = getCurrentSession().createQuery(hql);
        queryOrder.setParameter("patientId", patientId);
        queryOrder.setParameter("pharmacyId", pharmacyId);
        //queryOrder.setTimestamp("createdDate",DateUtil.formatDate(createdDate,"yyyy-mm-dd"));
        queryOrder.setParameter("orderId", orderId);// AppUtil.getSafeInt(orderId, 0));
//       if(orderStatus.equalsIgnoreCase("DELIVERY") ||
//                   orderStatus.equalsIgnoreCase("Shipped") ||
//                   orderStatus.equalsIgnoreCase("Pickup At Pharmacy"))
//        {
//            queryOrder.setParameter("status", orderStatus.toLowerCase());
//        }
        if (!orderStatus.equalsIgnoreCase("UNVERIFIED IMAGE")) {
            queryOrder.setParameter("status", orderStatus.toLowerCase());
        }
        List<Order> list = queryOrder.list();
        return list;

    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    public List geNexPatientProfile(int patientId, String orderId, Date createdDate,
            String orderStatus, int pharmacyId) throws Exception {

        String hql = " FROM  Order o "
                //  + " where o.orderStatus.name =:status "
                + " where o.patientProfile.id !=:patientId   ";
//        if(!orderStatus.equalsIgnoreCase("DELIVERY") &&
//                   !orderStatus.equalsIgnoreCase("Shipped") &&
//                   !orderStatus.equalsIgnoreCase("Pickup At Pharmacy"))
//        {
//            hql+=" AND  o.orderStatus.name not in('DELIVERY','Shipped','Pickup At Pharmacy') ";
//                     
//        }
        if (orderStatus == null || orderStatus.equalsIgnoreCase("UNVERIFIED IMAGE")) {
            hql += " AND o.orderStatus.name='Pending' and (o.imagePath is not null OR o.video is not null) and o.drugDetail is null";
        } else {
            hql += " and o.orderStatus.name =:status ";
        }
//        hql+= " AND cast(o.id as integer)>:orderId "
        hql += " AND (o.pharmacy is null or o.pharmacy.id=:pharmacyId)"
                + " ORDER BY o.createdOn desc";
//        if (orderStatus.equalsIgnoreCase("other")) {
//            hql = " FROM  Order o "
//                    + " WHERE p.id= o.patientProfile.id "
//                    + " AND  o.orderStatus.name not in('Pending','Shipped','On Hold') "
//                    + " AND cast(o.id as integer)>:orderId "
//                    //                + " AND o.createdOn>=:createdDate  "
//                    //                + " AND o.id<>:orderId "
//                    + " AND o.patientProfile.id =:patientId   "
//                    + " ORDER BY o.createdOn DESC";
//        }
        Query queryOrder = getCurrentSession().createQuery(hql);
        queryOrder.setParameter("patientId", patientId);
        queryOrder.setParameter("pharmacyId", pharmacyId);
        //queryOrder.setTimestamp("createdDate",DateUtil.formatDate(createdDate,"yyyy-mm-dd"));
//        queryOrder.setParameter("orderId", orderId);// AppUtil.getSafeInt(orderId, 0));
//       if(orderStatus.equalsIgnoreCase("DELIVERY") ||
//                   orderStatus.equalsIgnoreCase("Shipped") ||
//                   orderStatus.equalsIgnoreCase("Pickup At Pharmacy"))
//        {
//            queryOrder.setParameter("status", orderStatus.toLowerCase());
//        }
        if (!orderStatus.equalsIgnoreCase("UNVERIFIED IMAGE")) {
            queryOrder.setParameter("status", orderStatus.toLowerCase());
        }
        List<Order> list = queryOrder.list();
        return list;

    }
    /////////////////////////////////////////////////////////////////////////

    public List gePrevOrderByPatientProfileOrder(int patientId, String orderId, Date createdDate,
            String orderStatus) throws Exception {

        String hql = " FROM  Order o "
                + " where o.orderStatus.name =:status "
                + " AND o.patientProfile.id =:patientId   "
                // + " AND o.createdOn<=:createdDate "
                // + " AND o.id<>:orderId"
                + " AND cast(o.id as integer)<:orderId "
                + " ORDER BY o.createdOn DESC";
        if (orderStatus.equalsIgnoreCase("other")) {
            hql = " FROM  Order o "
                    + " WHERE p.id= o.patientProfile.id "
                    + " AND  o.orderStatus.name not in('Pending','Shipped','On Hold') "
                    //                + " AND o.createdOn<=:createdDate  "
                    //                + " AND o.id<>:orderId "
                    + " AND cast(o.id as integer)<:orderId "
                    + " AND o.patientProfile.id =:patientId   "
                    + " ORDER BY o.createdOn DESC";
        }
        Query queryOrder = getCurrentSession().createQuery(hql);
        queryOrder.setParameter("patientId", patientId);
        //queryOrder.setTimestamp("createdDate",DateUtil.formatDate(createdDate,"yyyy-mm-dd"));
        queryOrder.setParameter("orderId", AppUtil.getSafeInt(orderId, 0));
        if (!orderStatus.equalsIgnoreCase("other")) {
            queryOrder.setParameter("status", orderStatus.toLowerCase());
        }
        List<Order> list = queryOrder.list();
        return list;

    }

/////////////////////////////////////////////////////////////////////////
    public TransferRequest getPTVideoFromTransferRequest(Order orders) {
        Query queryOrder = null;
        int requestId = orders.getTransferDetail().getRequestId();
        queryOrder = getCurrentSession().createQuery(" FROM TransferRequest tr "
                + " WHERE tr.id=:requestId");
        queryOrder.setParameter("requestId", requestId);
        List<TransferRequest> transferRequestList = queryOrder.list();
        if (transferRequestList == null || transferRequestList.size() == 0) {
            return null;
        }
        TransferRequest transferRequest = (TransferRequest) transferRequestList.get(0);
        return transferRequest;
        //transferRxQueueDTO.setPtVideo((transferRequest.getVideo() == null || transferRequest.getVideo().trim().equals("")) ? "" : transferRequest.getVideo());

    }

    public PharmacyUser getPharmacyUserById(Integer id) throws Exception {
        Query query = getCurrentSession().createQuery("from PharmacyUser pharmacyUser left join fetch pharmacyUser.pharmacy pharmacy left join fetch pharmacy.state left join fetch pharmacy.pharmacyLookup pharmacyLookup left join fetch pharmacyLookup.state where pharmacyUser.id=:id");
        query.setParameter("id", id);

        return (PharmacyUser) query.uniqueResult();
    }

    public List<Object[]> getStatuswiseOrders(List<String> orderStatusList, int patientId) {
        String hql = " FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd "
                + " left join o.deliveryPreference dp "
                + " left join o.orderStatus os "
                + " left join  o.drugDetail dd "
                + " left join  dd.drugForm df "
                + " left join  dd.drugBasic db "
                + " left join  db.drugGeneric dg "
                + " WHERE  o.patientProfile.id = p.id "
                + " AND  o.orderStatus.name in (:status) "
                + " AND o.patientProfile.id=:patientId"
                + " order by o.createdOn desc ";
        Query queryOrder = getCurrentSession().createQuery(hql);

        queryOrder.setParameterList("status", orderStatusList);
        queryOrder.setParameter("patientId", patientId);

        List<Object[]> list = queryOrder.list();
        return list;
    }

    public List getOrdersListByDate(BaseDTO baseDTO) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd ");
        sb.append(" left join o.deliveryPreference dp ");
        sb.append(" left join o.orderStatus os ");
        sb.append(" left join  o.drugDetail dd ");
        sb.append(" left join  dd.drugForm df ");
        sb.append(" left join  dd.drugBasic db ");
        sb.append(" left join  db.drugGeneric dg ");
        sb.append(" WHERE  o.patientProfile.id = p.id ");
        if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null && CommonUtil.isNotEmpty(baseDTO.getStatus())) {
            sb.append("AND o.createdOn >=:fromDate and o.createdOn <=:toDate and os.name=:status");
        } else if (baseDTO.getFromDate() != null && baseDTO.getToDate() != null) {
            sb.append("AND o.createdOn >=:fromDate and o.createdOn <=:toDate");
        } else if (CommonUtil.isNotEmpty(baseDTO.getStatus())) {

            if ((baseDTO.getStatus()).equalsIgnoreCase("Shipped")) {
                sb.append("AND os.id in(5,6,15)");
            } else if (baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.REFILLABLE_NOW)) {
                sb.append("AND os.id in(5,6,15) AND o.refillDone=0  AND o.orderChain.nextRefillDate<=:todayMorning AND o.orderChain.refillRemaing>0  ");
            } else if (baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.EXPIRING_SOON)) {
                sb.append("  AND o.orderChain.rxExpiredDate<=:expiringSoon AND o.orderChain.rxExpiredDate>:todayDate AND( os.id not in(5,6,15) OR o.orderChain.refillRemaing>0  )");
            } else if (baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.ACTIVE_ORDERS)) {
                sb.append("AND os.id in(1,2,8,17,19)");
            } else {
                sb.append("AND os.name=:status");
            }
        }
        if (CommonUtil.isNullOrEmpty(baseDTO.getStatus())) {
            sb.append("AND os.id in(1,2,5,6,7,8,9,10,15,16,17,19)");
        }

        sb.append(" AND o.patientProfile.id=:patientId");
        if (baseDTO != null && baseDTO.getDependentId() != null && baseDTO.getDependentId() > 0) {
            sb.append(" AND o.patientProfileMembers.id=:dependentId");
        } else {
            sb.append(" AND o.patientProfileMembers.id is null ");
        }
        sb.append(" order by o.createdOn asc ");

        Query queryOrder = getCurrentSession().createQuery(sb.toString());
        if (baseDTO.getFromDate() != null) {
            queryOrder.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            queryOrder.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        if (CommonUtil.isNotEmpty(baseDTO.getStatus())) {
            ////////////////////////////////////////////////////////////////////
            if (baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.REFILLABLE_NOW)) {
                Date today = new Date();
                Date todayMorning = DateUtils.truncate(today, Calendar.DATE);
                queryOrder.setParameter("todayMorning", todayMorning);
            } else if (baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.EXPIRING_SOON)) {
                queryOrder.setParameter("expiringSoon", baseDTO.getExpiringSoonDate());
                queryOrder.setParameter("todayDate", new Date());
            } ///////////////////////////////////////////////////////////////////
            else if (!"Shipped".equalsIgnoreCase(baseDTO.getStatus())
                    && !baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.ACTIVE_ORDERS)
                    && !baseDTO.getStatus().equalsIgnoreCase(Constants.ORDER_CATEGORIES.EXPIRING_SOON)) {
                queryOrder.setParameter("status", baseDTO.getStatus());
            }
//            SqueryOrder.setParameter("status", baseDTO.getStatus());
        }
        queryOrder.setParameter("patientId", baseDTO.getPatientId());
        if (!CommonUtil.isNullOrEmpty(baseDTO.getDependentId())) {
            queryOrder.setParameter("dependentId", baseDTO.getDependentId());
        }
        List<Object[]> list = queryOrder.list();
        return list;
    }

    public List getFilteredRecordByStatusWise(Integer cPosition, Integer pSize, String search, String colName, String sort, String orderStatus, Integer pharmacyId, SearchDTO searchDTO) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd ");
        sb.append(" left join o.deliveryPreference dp ");
        sb.append(" left join o.orderStatus os ");
        sb.append(" left join o.drugDetail dd ");
        sb.append(" left join dd.drugForm df ");
        sb.append(" left join dd.drugBasic db ");
        sb.append(" left join db.drugGeneric dg ");
        sb.append(" left join o.patientProfileMembers pm ");
        sb.append(" WHERE o.patientProfile is not NULL and o.patientProfile.id = p.id ");
        //Set search criteria
        if (CommonUtil.isNotEmpty(search)) {
            //sb.append(" AND  (o.firstName like :search or  o.lastName like :search)");// OR  o.patientProfileMembers.firstName like :search or o.patientProfileMembers.lastName like :search ");
            sb.append(" AND  (o.systemGeneratedRxNumber like :search or o.id like :search or o.oldRxNumber like :search ) ");
//            sb.append(" AND(  o.id like :search or o.systemGeneratedRxNumber like :search)");
        }
//        else {
        sb.append(" AND  o.orderStatus.name in (:status) ");
        if (CommonUtil.isNotEmpty(searchDTO.getMbrReqType())) {
            if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER LABEL SCAN") || searchDTO.getMbrReqType().equalsIgnoreCase("X-FER RX SCAN")) {
                //sb.append(" AND  o.isBottleAvailable=:isBottleAvailable ");
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("Refill")) {
                sb.append(" AND  o.orderType=:orderType ");
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())) {
            sb.append(" AND dp.name=:deliveryPref ");
        }
        if (CommonUtil.isNotEmpty(searchDTO.getSearchField()) && CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("dob")) {
                sb.append(" AND  (p.birthDate=:dob or pm.birthDate=:dob ) ");
            }
        }
//        if (CommonUtil.isNotEmpty(searchDTO.getOrderStatus())) {
//            if (searchDTO.getOrderStatus().equalsIgnoreCase("UNVERIFIED")) {
//                sb.append(" AND o.imagePath is not null or o.video is not null ");
//            }
//        }
//        }
        sb.append(" AND (o.pharmacy.id=:pharmacyId or o.pharmacy is null )");
//        if (!orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
//            sb.append(" AND o.pharmacy.id=:pharmacyId");
//        }
        //Set Sorting criteria
        this.populateSortingCriteria(colName, sort, sb, orderStatus);

        Query queryOrder = getCurrentSession().createQuery(sb.toString());
        if (CommonUtil.isNotEmpty(search)) {
            queryOrder.setParameter("search", "%" + search + "%");
        }
//        else {
        if (orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.SHIPPED)) {
            List<String> orderStatusList = new ArrayList<>();
            orderStatusList.add(orderStatus);
            orderStatusList.add(Constants.ORDER_STATUS.READY_TO_DELIVER_RX);
            queryOrder.setParameterList("status", orderStatusList);
        } else {
            queryOrder.setParameter("status", orderStatus);
        }
        if (CommonUtil.isNotEmpty(searchDTO.getMbrReqType())) {
            if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER LABEL SCAN")) {
                //queryOrder.setParameter("isBottleAvailable", Boolean.TRUE);
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER RX SCAN")) {
                //queryOrder.setParameter("isBottleAvailable", Boolean.FALSE);
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("Refill")) {
                queryOrder.setParameter("orderType", searchDTO.getMbrReqType());
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())) {
            queryOrder.setParameter("deliveryPref", searchDTO.getDeliveryPref());
        }
        if (CommonUtil.isNotEmpty(searchDTO.getSearchField()) && CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("dob")) {
                queryOrder.setParameter("dob", EncryptionHandlerUtil.getEncryptedString(searchDTO.getSearchValue()));
            }
        }
//        }
//        if (!orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
        queryOrder.setParameter("pharmacyId", pharmacyId);
//        }
        List<Object[]> list;
        if (cPosition == 0 && pSize == 0) {
            list = queryOrder.list();
        } else {
            list = queryOrder.setFirstResult(cPosition).setMaxResults(pSize).list();
        }
        return list;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getReadyToShipRecords(Integer cPosition, Integer pSize, String search, String colName, String sort, String orderStatus, Integer pharmacyId, SearchDTO searchDTO) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd ");
        sb.append(" left join o.deliveryPreference dp ");
        sb.append(" left join o.orderStatus os ");
        sb.append(" left join o.drugDetail dd ");
        sb.append(" left join dd.drugForm df ");
        sb.append(" left join dd.drugBasic db ");
        sb.append(" left join db.drugGeneric dg ");
        sb.append(" left join o.patientProfileMembers pm ");
        sb.append(" left join o.readyToDeliverRxOrders rtd ");
        sb.append(" WHERE o.patientProfile is not NULL and o.patientProfile.id = p.id ");
        //Set search criteria
        if (CommonUtil.isNotEmpty(search)) {
            //sb.append(" AND  (o.firstName like :search or  o.lastName like :search)");// OR  o.patientProfileMembers.firstName like :search or o.patientProfileMembers.lastName like :search ");
            sb.append(" AND  (o.systemGeneratedRxNumber like :search or o.id like :search or o.oldRxNumber like :search ) ");
//            sb.append(" AND(  o.id like :search or o.systemGeneratedRxNumber like :search)");
        }
//        else {
        sb.append(" AND  o.orderStatus.name in (:status) ");
//        }
        if (CommonUtil.isNotEmpty(searchDTO.getMbrReqType())) {
            if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER LABEL SCAN") || searchDTO.getMbrReqType().equalsIgnoreCase("X-FER RX SCAN")) {
                //sb.append(" AND  o.isBottleAvailable=:isBottleAvailable ");
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("Refill")) {
                sb.append(" AND  o.orderType=:orderType ");
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())) {
            sb.append(" AND dp.name=:deliveryPref ");
        }
        if (CommonUtil.isNotEmpty(searchDTO.getSearchField()) && CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("dob")) {
                sb.append(" AND  (p.birthDate=:dob or pm.birthDate=:dob ) ");
            }
        }
        sb.append(" AND (o.pharmacy.id=:pharmacyId or o.pharmacy is null )");
//        if (!orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
//            sb.append(" AND o.pharmacy.id=:pharmacyId");
//        }

        //Set Sorting criteria
        this.populateSortingCriteria(colName, sort, sb, orderStatus);

        Query queryOrder = getCurrentSession().createQuery(sb.toString());
        if (CommonUtil.isNotEmpty(search)) {
            // queryOrder.setParameter("search", search + "%");
            queryOrder.setParameter("search", "%" + search + "%");
        }
//        else {
        if (orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.SHIPPED)) {
            List<String> orderStatusList = new ArrayList<>();
            orderStatusList.add(orderStatus);
            orderStatusList.add(Constants.ORDER_STATUS.READY_TO_DELIVER_RX);
            queryOrder.setParameterList("status", orderStatusList);
        } else {
            queryOrder.setParameter("status", orderStatus);
        }
        if (CommonUtil.isNotEmpty(searchDTO.getMbrReqType())) {
            if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER LABEL SCAN")) {
                //queryOrder.setParameter("isBottleAvailable", Boolean.TRUE);
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER RX SCAN")) {
                //queryOrder.setParameter("isBottleAvailable", Boolean.FALSE);
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("Refill")) {
                queryOrder.setParameter("orderType", searchDTO.getMbrReqType());
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())) {
            queryOrder.setParameter("deliveryPref", searchDTO.getDeliveryPref());
        }
        if (CommonUtil.isNotEmpty(searchDTO.getSearchField()) && CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("dob")) {
                queryOrder.setParameter("dob", EncryptionHandlerUtil.getEncryptedString(searchDTO.getSearchValue()));
            }
        }
//        }
//        if (!orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
        queryOrder.setParameter("pharmacyId", pharmacyId);
//        }
        List<Object[]> list;
        if (cPosition == 0 && pSize == 0) {
            list = queryOrder.list();
        } else {
            list = queryOrder.setFirstResult(cPosition).setMaxResults(pSize).list();
        }
        return list;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    private void populateSortingCriteria(String colName, String sort, StringBuilder sb, String orderStatus) {
        if (CommonUtil.isNotEmpty(colName)) {
            if (colName.contains("requestControlNumber1")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.id asc");
                } else {
                    sb.append(" order by o.id desc");
                }
            } else if (colName.contains("reqPosted")) {
                if (orderStatus.equalsIgnoreCase("Pending Review") || orderStatus.equalsIgnoreCase("Waiting Pt Response")
                        || orderStatus.equalsIgnoreCase("Shipped") || orderStatus.equalsIgnoreCase("Filled") || orderStatus.equalsIgnoreCase("Cancelled")) {
                    if (sort.equalsIgnoreCase("asc")) {
                        sb.append(" order by o.updatedOn asc");
                    } else {
                        sb.append(" order by o.updatedOn desc");
                    }
                } else if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.createdOn asc");
                } else {
                    sb.append(" order by o.createdOn desc");
                }
            } else if (colName.contains("patientName")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by pm.fullName asc");
                } else {
                    sb.append(" order by pm.fullName desc");
                }
            } else if (colName.contains("status")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by os.id asc");
                } else {
                    sb.append(" order by os.id desc");
                }
            } else if (colName.contains("deliveryService")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by dp.id asc");
                } else {
                    sb.append(" order by dp.id desc");
                }
            } else if (colName.contains("drugNameWithoutStrength")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.drugName asc");
                } else {
                    sb.append(" order by o.drugName desc");
                }
            } else if (colName.contains("systemGeneratedRxNumber")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.systemGeneratedRxNumber asc");
                } else {
                    sb.append(" order by o.systemGeneratedRxNumber desc");
                }
            } else if (colName.contains("tracking")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.traclingno asc");
                } else {
                    sb.append(" order by o.traclingno desc");
                }
            } else if (colName.contains("handlingFeeStr")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.handLingFee asc");
                } else {
                    sb.append(" order by o.handLingFee desc");
                }
            } else if (colName.contains("zip")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by pd.zip asc");
                } else {
                    sb.append(" order by pd.zip desc");
                }
            } else if (colName.contains("strength")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by dd.strength asc");
                } else {
                    sb.append(" order by dd.strength desc");
                }
            } else if (colName.contains("miles")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.miles asc");
                } else {
                    sb.append(" order by o.miles desc");
                }
            } else if (colName.contains("originalPtCopayStr")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.originalPtCopay asc");
                } else {
                    sb.append(" order by o.originalPtCopay desc");
                }
            } else if (colName.contains("medicationSpecMsg")) {
                if (sort.equalsIgnoreCase("asc")) {
                    sb.append(" order by o.responseRequired asc");
                } else {
                    sb.append(" order by o.responseRequired desc");
                }
            } else if (colName.contains("requestType") || colName.contains("multiRxLabel")) {
                if (sort.equalsIgnoreCase("asc")) {
                    if (orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
                        sb.append(" order by o.isBottleAvailable asc,dp.id asc");
                    } else {
                        sb.append(" order by o.isBottleAvailable asc");
                    }
                } else {
                    if (orderStatus.equalsIgnoreCase(Constants.ORDER_STATUS.PENDING)) {
                        sb.append(" order by o.isBottleAvailable desc,dp.id desc");
                    } else {
                        sb.append(" order by o.isBottleAvailable desc");
                    }

                }
            }
        } else {
            sb.append(" order by o.createdOn asc,dp.id desc,o.updatedOn desc,o.careGiverRequest desc,o.isBottleAvailable desc ");
        }
    }

    public List<Object[]> getAllOrderTypeCount(Integer pharmacyId) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(o.`id`)AS patientcount,o.`OrderStatus` ");
        sql.append(" FROM Orders o ");
        if (CommonUtil.isNullOrEmpty(pharmacyId)) {
            sql.append(" where o.PharmacyId IS NULL and o.PatientId is not NULL ");
        } else {
            sql.append(" where (o.PharmacyId is null or o.PharmacyId=:pharmacyId) and o.PatientId is not NULL ");
        }
        sql.append(" GROUP BY o.`OrderStatus` ");

//        sql.append("(SELECT o.`PatientId`,o.`OrderStatus`, COUNT(o.`PatientId`)AS patientcount ");
//        sql.append(" FROM Orders o ");
//        sql.append(" WHERE o.`DependentId` IS NULL ");
//        if (CommonUtil.isNullOrEmpty(pharmacyId)) {
//            sql.append(" AND o.PharmacyId IS NULL ");
//        } else {
//            sql.append(" AND o.PharmacyId=:pharmacyId ");
//        }
//        sql.append(" GROUP BY o.`PatientId`,o.`OrderStatus` ");
//        sql.append(" ) ");
//        sql.append(" UNION ");
//        sql.append(" (SELECT o.`DependentId` AS PatientId ,o.`OrderStatus`, COUNT(o.`DependentId`)AS patientcount ");
//        sql.append(" FROM Orders o ");
//        sql.append(" WHERE o.`DependentId` IS NOT NULL ");
//        if (CommonUtil.isNullOrEmpty(pharmacyId)) {
//            sql.append(" AND o.PharmacyId IS NULL ");
//        } else {
//            sql.append(" AND o.PharmacyId=:pharmacyId ");
//        }
//        sql.append(" GROUP BY o.`DependentId`,o.`OrderStatus` ");
//        sql.append(" ) ");
//        sql.append(" ORDER BY orderstatus ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        if (!CommonUtil.isNullOrEmpty(pharmacyId)) {
            query.setParameter("pharmacyId", pharmacyId);
        }

        List<Object[]> queryResult = query.list();
        return queryResult;

    }

    public void updatePharmacyUserPsw(Integer id, String password) throws Exception {
        Query query = getCurrentSession().createQuery("Update PharmacyUser set password=:password where id=:id");
        query.setParameter("password", password);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void updatePharmacyPswById(Integer id, String password) throws Exception {
        Query query = getCurrentSession().createQuery("Update Pharmacy set password=:password where id=:id");
        query.setParameter("password", password);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List getStatuswiseOrders(String orderStatus, int patientId, int dependentId) throws Exception {
        StringBuilder hql = new StringBuilder();
        boolean orderStatusProvided = AppUtil.getSafeStr(orderStatus, "").length() > 0;
        hql.append(" FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd ");
        hql.append(" left join o.deliveryPreference dp ");
        hql.append(" left join o.orderStatus os ");
        hql.append(" left join  o.drugDetail dd ");
        hql.append(" left join  dd.drugForm df ");
        hql.append(" left join  dd.drugBasic db ");
        hql.append(" left join  db.drugGeneric dg ");
        hql.append(" WHERE  o.patientProfile.id = p.id ");

        if (orderStatusProvided) {
            hql.append(" AND  o.orderStatus.name in (:status) ");
        } else {
            hql.append(" AND  o.orderStatus.name <> 'Hidden' ");
        }
        hql.append(" AND o.patientProfile.id=:patientId ");
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            hql.append(" AND o.patientProfileMembers.id=:dependentId");
        } else {
            hql.append(" AND o.patientProfileMembers.id is null ");
        }
        hql.append(" order by o.createdOn desc ");
        Query queryOrder = getCurrentSession().createQuery(hql.toString());
        if (orderStatusProvided) {
            queryOrder.setParameter("status", orderStatus);
        }
        queryOrder.setParameter("patientId", patientId);
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            queryOrder.setParameter("dependentId", dependentId);
        }
        List<Object[]> list = queryOrder.list();
        return list;

    }

    public List getStatuswiseOrders(int orderStatus, int patientId, int dependentId) throws Exception {
        StringBuilder hql = new StringBuilder();
        boolean orderStatusProvided = orderStatus > 0;
        hql.append(" FROM Order o  ");
        hql.append(" WHERE  o.patientProfile.id=:patientId ");

        if (orderStatusProvided) {
            hql.append(" AND  o.orderStatus.id in (:status) ");
        } else {
            hql.append(" AND  o.orderStatus.id <> 18 ");//not equal to hidden
        }
//        hql.append(" AND o.patientProfile.id=:patientId ");
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            hql.append(" AND o.patientProfileMembers.id=:dependentId");
        } else {
            hql.append(" AND o.patientProfileMembers.id is null ");
        }
        hql.append(" order by o.createdOn desc ");
        Query queryOrder = getCurrentSession().createQuery(hql.toString());
        if (orderStatusProvided) {
            queryOrder.setParameter("status", orderStatus);
        }
        queryOrder.setParameter("patientId", patientId);
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            queryOrder.setParameter("dependentId", dependentId);
        }
        List<Object[]> list = queryOrder.list();
        return list;

    }

    //////////////////////////////////////////////////////////////////////
    public List getStatuswiseOrders(int orderStatus, int patientId, int dependentId,
            int pharmacyId) throws Exception {
        StringBuilder hql = new StringBuilder();
        boolean orderStatusProvided = orderStatus > 0;
        hql.append(" FROM Order o  ");
        hql.append(" WHERE  o.patientProfile.id=:patientId ");

        if (orderStatusProvided) {
            hql.append(" AND  o.orderStatus.id in (:status) ");
        } else {
            hql.append(" AND  o.orderStatus.id <> 18 ");//not equal to hidden
        }
//        hql.append(" AND o.patientProfile.id=:patientId ");
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            hql.append(" AND o.patientProfileMembers.id=:dependentId");
        } else {
            hql.append(" AND o.patientProfileMembers.id is null ");
        }
        if (pharmacyId > 0) {
            hql.append(" AND (o.pharmacy.id=:pharmacyId OR o.pharmacy is null) ");
        }
        hql.append(" order by o.createdOn desc ");
        Query queryOrder = getCurrentSession().createQuery(hql.toString());
        if (orderStatusProvided) {
            queryOrder.setParameter("status", orderStatus);
        }
        queryOrder.setParameter("patientId", patientId);
        if (!CommonUtil.isNullOrEmpty(dependentId)) {
            queryOrder.setParameter("dependentId", dependentId);
        }
        if (pharmacyId > 0) {
            queryOrder.setParameter("pharmacyId", pharmacyId);
        }
        List<Object[]> list = queryOrder.list();
        return list;

    }

    public List<PharmacyUser> getPharmacyUsersList() throws Exception {
        Query query = getCurrentSession().createQuery("from PharmacyUser pharmacyUser");
        return query.list();
    }
    //////////////////////////////////////////////////////////////////////

    public List<Order> getReadyToDeliverOrdersByPatientId(Integer patientId) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("Select ord FROM Order ord ");
        hql.append("left join ord.drugDetail drugDetail ");
        hql.append("left join  drugDetail.drugForm df ");
        hql.append("left join  drugDetail.drugBasic db ");
        hql.append("left join  db.drugGeneric dg ");
        hql.append("inner join fetch ord.readyToDeliverRxOrders readyToDeliverRxOrders ");
        hql.append("inner join fetch readyToDeliverRxOrders.patientDeliveryAddress patientDeliveryAddress ");
        hql.append("inner join fetch readyToDeliverRxOrders.deliveryPreferences deliveryPreferences ");
        hql.append("where ord.zip=patientDeliveryAddress.zip ");
        hql.append("and ord.city=patientDeliveryAddress.city ");
        hql.append("and ord.state=patientDeliveryAddress.state.name ");
        hql.append("and ord.streetAddress=patientDeliveryAddress.address ");
        hql.append("and ord.deliveryPreference.id=deliveryPreferences.id ");
        hql.append("and readyToDeliverRxOrders.isShipped=false ");
        hql.append("and ord.patientProfile.id=:patientId ");

        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("patientId", patientId);
        return query.list();
    }

    public List<Order> getReadyToDeliverOrdersByIdAndPatientIdAndPrefId(Integer patientId, Integer readyToDeliverRxOrdersId, Integer prefId, Boolean isShipped) throws Exception {
        StringBuilder hql = new StringBuilder();
        hql.append("Select ord FROM Order ord ");
        hql.append("left join ord.drugDetail drugDetail ");
        hql.append("left join  drugDetail.drugForm df ");
        hql.append("left join  drugDetail.drugBasic db ");
        hql.append("left join  db.drugGeneric dg ");
        hql.append("inner join fetch ord.readyToDeliverRxOrders readyToDeliverRxOrders ");
        hql.append("inner join fetch readyToDeliverRxOrders.deliveryPreferences deliveryPreferences ");
        hql.append("where ord.deliveryPreference.id=deliveryPreferences.id ");
        hql.append("and ord.patientProfile.id=:patientId ");
        hql.append("and readyToDeliverRxOrders.id=:readyToDeliverRxOrdersId ");
        hql.append("and ord.deliveryPreference.id=:prefId ");
        if (isShipped != null) {
            hql.append("and readyToDeliverRxOrders.isShipped=false ");
        }

        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("patientId", patientId);
        query.setParameter("readyToDeliverRxOrdersId", readyToDeliverRxOrdersId);
        query.setParameter("prefId", prefId);
        return query.list();
    }

    public BigInteger populateShippedAndReadyToDeliveryCountForPage1(Integer pharmacyId) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) ");
        sql.append(" FROM Orders o ");
        if (CommonUtil.isNullOrEmpty(pharmacyId)) {
            sql.append(" where o.PharmacyId IS NULL and o.PatientId is not NULL and o.ReadyToDeliverRxOrdersId is not NULL ");
        } else {
            sql.append(" where (o.PharmacyId is null or o.PharmacyId=:pharmacyId) and o.PatientId is not NULL and o.ReadyToDeliverRxOrdersId is not NULL ");
        }
        sql.append("and o.OrderStatus in (:orderStatus) ");
        SQLQuery query = getCurrentSession().createSQLQuery(sql.toString());
        if (!CommonUtil.isNullOrEmpty(pharmacyId)) {
            query.setParameter("pharmacyId", pharmacyId);
        }
        List<Integer> listOfStatus = new ArrayList<>();
        listOfStatus.add(Constants.ORDER_STATUS.SHIPPED_ID);
        listOfStatus.add(Constants.ORDER_STATUS.READY_TO_DELIVER_ID);
        query.setParameterList("orderStatus", listOfStatus);

        return (BigInteger) query.uniqueResult();
    }

    public List<Object[]> getAllFilteredRecords(int cPosition, int pSize, String search, String colName, String sdir, String filter, Integer pharmacyId, SearchDTO searchDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append(" FROM Order o left join o.patientProfile p left join p.patientDeliveryAddresses pd ");
        sb.append(" left join o.deliveryPreference dp ");
        sb.append(" left join o.orderStatus os ");
        sb.append(" left join o.drugDetail dd ");
        sb.append(" left join dd.drugForm df ");
        sb.append(" left join dd.drugBasic db ");
        sb.append(" left join db.drugGeneric dg ");
        sb.append(" left join o.patientProfileMembers pm ");
        sb.append(" WHERE o.patientProfile is not NULL and o.patientProfile.id = p.id ");
        //Set search criteria
        if (CommonUtil.isNotEmpty(search)) {
            sb.append(" AND  (o.systemGeneratedRxNumber like :rxNumber or o.oldRxNumber like :rxNumber) ");
            sb.append("or");
            sb.append(" ((p.firstName=:orgnPtName or p.lastName=:orgnPtName) or (p.firstName=:captilizePtName or p.lastName=:captilizePtName) or (p.firstName=:fullCaptilizePtName or p.lastName=:fullCaptilizePtName) ");
            sb.append(" or (pm.firstName=:orgnPtName or pm.lastName=:orgnPtName) or (pm.firstName=:captilizePtName or pm.lastName=:captilizePtName) or (pm.firstName=:fullCaptilizePtName or pm.lastName=:fullCaptilizePtName) ");
            sb.append(")");
        }
        if (CommonUtil.isNotEmpty(searchDTO.getMbrReqType())) {
            if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER LABEL SCAN") || searchDTO.getMbrReqType().equalsIgnoreCase("X-FER RX SCAN")) {
                //sb.append(" AND  o.isBottleAvailable=:isBottleAvailable ");
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("Refill")) {
                sb.append(" AND  o.orderType=:orderType ");
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())) {
            sb.append(" AND dp.name=:deliveryPref ");
        }
        if (CommonUtil.isNotEmpty(searchDTO.getSearchField()) && CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("dob")) {
                sb.append(" AND  (p.birthDate=:dob or pm.birthDate=:dob ) ");
            }
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("Rx")) {
                sb.append(" AND  (o.systemGeneratedRxNumber like :rxNumber or o.oldRxNumber like :rxNumber) ");
                sb.append("or");
                sb.append(" ((p.firstName=:orgnPtName or p.lastName=:orgnPtName) or (p.firstName=:captilizePtName or p.lastName=:captilizePtName) or (p.firstName=:fullCaptilizePtName or p.lastName=:fullCaptilizePtName) ");
                sb.append(" or (pm.firstName=:orgnPtName or pm.lastName=:orgnPtName) or (pm.firstName=:captilizePtName or pm.lastName=:captilizePtName) or (pm.firstName=:fullCaptilizePtName or pm.lastName=:fullCaptilizePtName)) ");
                sb.append("or");
                sb.append(" (p.mobileNumber=:mobileNumber) ");
            }
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("ptName")) {
                sb.append(" AND ((p.firstName=:orgnPtName or p.lastName=:orgnPtName) or (p.firstName=:captilizePtName or p.lastName=:captilizePtName) or (p.firstName=:fullCaptilizePtName or p.lastName=:fullCaptilizePtName) ");
                sb.append(" or (pm.firstName=:orgnPtName or pm.lastName=:orgnPtName) or (pm.firstName=:captilizePtName or pm.lastName=:captilizePtName) or (pm.firstName=:fullCaptilizePtName or pm.lastName=:fullCaptilizePtName)) ");
            }
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("mobileNo")) {
                sb.append(" AND  (p.mobileNumber=:mobileNumber) ");
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getOrderStatus()) && !AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED")) {
            sb.append(" AND  (os.name=:orderStatus) ");
        }
        sb.append(" AND (o.pharmacy.id=:pharmacyId or o.pharmacy is null )");
        //Set Sorting criteria
        this.populateSortingCriteria(colName, sdir, sb, filter);

        Query queryOrder = getCurrentSession().createQuery(sb.toString());
        if (CommonUtil.isNotEmpty(search)) {
            queryOrder.setParameter("rxNumber", "%" + search + "%");

            queryOrder.setParameter("orgnPtName", EncryptionHandlerUtil.getEncryptedString(search));
            queryOrder.setParameter("captilizePtName", EncryptionHandlerUtil.getEncryptedString(StringUtils.capitalize(search)));
            queryOrder.setParameter("fullCaptilizePtName", EncryptionHandlerUtil.getEncryptedString(search.toUpperCase()));
        }
        if (CommonUtil.isNotEmpty(searchDTO.getMbrReqType())) {
            if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER LABEL SCAN")) {
                //queryOrder.setParameter("isBottleAvailable", Boolean.TRUE);
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("X-FER RX SCAN")) {
                //queryOrder.setParameter("isBottleAvailable", Boolean.FALSE);
            } else if (searchDTO.getMbrReqType().equalsIgnoreCase("Refill")) {
                queryOrder.setParameter("orderType", searchDTO.getMbrReqType());
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getDeliveryPref())) {
            queryOrder.setParameter("deliveryPref", searchDTO.getDeliveryPref());
        }
        if (CommonUtil.isNotEmpty(searchDTO.getSearchField()) && CommonUtil.isNotEmpty(searchDTO.getSearchValue())) {
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("dob")) {
                queryOrder.setParameter("dob", EncryptionHandlerUtil.getEncryptedString(searchDTO.getSearchValue()));
            }
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("Rx")) {
                queryOrder.setParameter("rxNumber", "%" + searchDTO.getSearchValue() + "%");
            }
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("ptName") || AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("Rx")) {
                queryOrder.setParameter("orgnPtName", EncryptionHandlerUtil.getEncryptedString(searchDTO.getSearchValue()));
                queryOrder.setParameter("captilizePtName", EncryptionHandlerUtil.getEncryptedString(StringUtils.capitalize(searchDTO.getSearchValue())));
                queryOrder.setParameter("fullCaptilizePtName", EncryptionHandlerUtil.getEncryptedString(searchDTO.getSearchValue().toUpperCase()));
            }
            if (AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("mobileNo") || AppUtil.getSafeStr(searchDTO.getSearchField(), "").equalsIgnoreCase("Rx")) {
                queryOrder.setParameter("mobileNumber", EncryptionHandlerUtil.getEncryptedString(searchDTO.getSearchValue()));
            }
        }
        if (CommonUtil.isNotEmpty(searchDTO.getOrderStatus()) && !AppUtil.getSafeStr(searchDTO.getOrderStatus(), "").equalsIgnoreCase("UNVERIFIED")) {
            queryOrder.setParameter("orderStatus", searchDTO.getOrderStatus());
        }
        queryOrder.setParameter("pharmacyId", pharmacyId);
        List<Object[]> list;
        if (cPosition == 0 && pSize == 0) {
            list = queryOrder.list();
        } else {
            list = queryOrder.setFirstResult(cPosition).setMaxResults(pSize).list();
        }
        return list;
    }
}
