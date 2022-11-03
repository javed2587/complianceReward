package com.ssa.cms.dao;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.BasicStatisticsReportDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.model.*;
import com.ssa.cms.util.CommonUtil;
import com.ssa.cms.util.DateUtil;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDAO extends BaseDAO implements Serializable {

    public DailyRedemption getTransactionDetail(DailyRedemptionId id) {
        DailyRedemption obj = (DailyRedemption) this.getCurrentSession().get(DailyRedemption.class, id);
        return obj;
    }

    public List<Order> getOrdersList(Integer orderStatusId, Integer pharmacyId, String fromDate, String toDate) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        if (orderStatusId != null && orderStatusId != 0 && orderStatusId != 7) {
            filterCriteria.add(Restrictions.eq("orderStatus.id", orderStatusId));
        }
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        if (pharmacyId != null && pharmacyId > 0 && orderStatusId > 1) {
            filterCriteria.add(Restrictions.eq("pharmacy.id", pharmacyId));
        }
        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
            filterCriteria.add(Restrictions.between("createdOn", DateUtil.stringToDateTime(fromDate, "MM/dd/yyyy"), DateUtil.stringToDateTime1(toDate, "MM/dd/yyyy")));
        } else if (fromDate != null && !fromDate.isEmpty()) {
            filterCriteria.add(Restrictions.eq("createdOn", DateUtil.stringToDateTime(fromDate, "MM/dd/yyyy")));
        } else if (toDate != null && !toDate.isEmpty()) {
            filterCriteria.add(Restrictions.eq("createdOn", DateUtil.stringToDateTime(toDate, "MM/dd/yyyy")));
        }
        filterCriteria.addOrder(org.hibernate.criterion.Order.desc("createdOn"));
        List<Order> list = filterCriteria.list();
        return list;
    }

    public boolean checkTrackingNo(String trackingNo, String id) throws Exception {
        Query query = getCurrentSession().createQuery("from Order ord where ord.orderTrackingNo=:trackingNo and ord.id=:id");
        query.setParameter("trackingNo", trackingNo);
        query.setParameter("id", id);
        if (query.uniqueResult() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public List<OrderHistory> getOrderHistorysById(Integer orderId, Integer orderStatusId) throws Exception {
        Query query = getCurrentSession().createQuery("from OrderHistory orderHistory where orderHistory.order.id=:orderId and orderHistory.orderStatus.id=:orderStatusId");
        query.setParameter("orderId", orderId);
        query.setParameter("orderStatusId", orderStatusId);
        return query.list();
    }

    public void deleteOrderHistory(Integer orderId, Integer orderStatusId) throws Exception {
        Query query = getCurrentSession().createQuery("Delete from OrderHistory orderHistory where orderHistory.order.id=:orderId and orderHistory.orderStatus.id=:orderStatusId");
        query.setParameter("orderId", orderId);
        query.setParameter("orderStatusId", orderStatusId);
        query.executeUpdate();
    }

    public DailyRedemption getDailyRedemptionByTransactionNo(String transactionNo) throws Exception {
        DailyRedemption dailyRedemption = new DailyRedemption();
        Query query = getCurrentSession().createQuery("From DailyRedemption dailyRedemption where dailyRedemption.id.transactionNumber=:transactionNo");
        query.setParameter("transactionNo", transactionNo);
        Object result = query.uniqueResult();
        if (result != null) {
            dailyRedemption = (DailyRedemption) result;
        }
        return dailyRedemption;
    }

    public List<RedemptionIngredient> getRedemptionIngredients(String transactionNo) throws Exception {
        Query query = getCurrentSession().createQuery("From RedemptionIngredient redemptionIngredient where redemptionIngredient.transactionNumber=:transactionNo");
        query.setParameter("transactionNo", transactionNo);
        return query.list();
    }

    public List<Pharmacy> getPharmacys(Integer currentUserId) throws Exception {
        Query query = getCurrentSession().createQuery("From Pharmacy pharmacy where pharmacy.id=:currentUserId order by pharmacy.title asc");
        query.setParameter("currentUserId", currentUserId);
        return query.list();
    }

    public List<DailyRedemption> getDailyRedemptionByNpiNo(String pharmacyNpi) throws Exception {
        Query query = getCurrentSession().createQuery("From DailyRedemption dailyRedemption where dailyRedemption.pharmacyNpi=:pharmacyNpi");
        query.setParameter("pharmacyNpi", pharmacyNpi);
        return query.list();
    }

    public OrderStatus getOrderStatusByName(String name) throws Exception {
        OrderStatus orderStatus = new OrderStatus();
        Query query = getCurrentSession().createQuery("from OrderStatus orderStatus where orderStatus.name=:name");
        query.setParameter("name", name);
        Object result = query.uniqueResult();
        if (result != null) {
            orderStatus = (OrderStatus) result;
        }
        return orderStatus;
    }

    public List<Order> getALLPharmacyOrder() throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        return filterCriteria.list();
    }

    public List<Pharmacy> getPharmacysList() throws Exception {
        return getCurrentSession().createQuery("From Pharmacy pharmacy order by pharmacy.title asc").list();
    }

    public boolean getDrugByNdcNo(String ndcNO) throws Exception {
        Query query = getCurrentSession().createQuery("From Drug drug where drug.ndcnumber=:ndcNO");
        query.setParameter("ndcNO", ndcNO);
        Object result = query.uniqueResult();
        if (result != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Order getOrdersById(String id) throws Exception {
        Order order = new Order();
       Query query = getCurrentSession().createQuery("From Order ord "
//               + "left join fetch ord.orderHistory orderhistory "
               + "left join fetch ord.orderStatus orderStatus "
               + " where ord.id=:id ");
        query.setParameter("id", id);
        Object o = query.uniqueResult();
        if (o != null) {
            order = (Order) o;
            return order;
        }
        return null;

    }

    public Order getOrdersById(String id, Date olversion) throws Exception {
        Order order = null;
        Query query = getCurrentSession().createQuery("From Order ord left join fetch ord.pharmacy pharmacy left join fetch ord.orderCarriers orderCarriers left join fetch ord.orderHistory orderhistory left join fetch orderhistory.orderStatus orderStatus where ord.id=:id and ord.olversion=:olversion");
        query.setParameter("id", id);
        query.setParameter("olversion", olversion);
        Object o = query.uniqueResult();
        if (o != null) {
            order = (Order) o;
        }
        return order;
    }

    public List<Order> getOrderByTransactionNumber(String transactionNumber) throws Exception {
        Query query = getCurrentSession().createQuery("From Order ord where ord.transactionNo=:transactionNumber");
        query.setParameter("transactionNumber", transactionNumber);
        return query.list();
    }
//    SELECT COUNT(*) AS COUNT FROM PharmacyUser pu INNER JOIN Pharmacy p ON pu.`PharmacyId` = p.`Id` WHERE p.id=:id and pu.role=:role"

    public long getCountAllOrders(int orderStatus, Integer pharmacyId) throws Exception {
        Query query = getCurrentSession().createQuery("SELECT COUNT(*) AS COUNT FROM Order o WHERE o.orderStatus.id =:orderStatus and o.pharmacy.id=:pharmacyId ");
        query.setParameter("orderStatus", orderStatus);
        query.setParameter("pharmacyId", pharmacyId);
        long count = (Long) query.uniqueResult();

        return count;//Integer.parseInt(count + "");
    }

    public List<OptInOut> getTextOptInOut(Integer campaignId, String phoneNumber) throws Exception {
        Query query = getCurrentSession().createQuery("Select oIo From OptInOut oIo where oIo.campaignId=:campaignId and oIo.phoneNumber=:phoneNumber order by oIo.effectiveDate desc");
        query.setParameter("campaignId", campaignId);
        query.setParameter("phoneNumber", phoneNumber);
        return query.list();
    }

    public List<DrugBrand> getCompoundList(int index) throws Exception {
        Query query = getCurrentSession().createQuery("select distinct drugBrand From DrugBrand drugBrand left join fetch drugBrand.drugs drugs left join fetch drugs.drugUnits drugUnits");
        return query.setMaxResults(index).list();
    }

    public List<DrugBrand> getDrugBrands(String key) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(DrugBrand.class);
        filterCriteria.createCriteria("drugs", "drugs", JoinType.LEFT_OUTER_JOIN);
        if (!key.isEmpty()) {
            filterCriteria.add(Restrictions.disjunction().add(Restrictions.like("genericName", "%" + key + "%")).add(Restrictions.eq("drugs.ndcnumber", key)));
        }
        filterCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return filterCriteria.list();
    }

    public Drug getDrugBrandByNdc(String ndc) throws Exception {
        Query query = getCurrentSession().createQuery("from Drug drug where drug.ndcnumber=:ndc");
        query.setParameter("ndc", ndc);
        return (Drug) query.uniqueResult();
    }

    public List<Order> getFilterOrders(String fromDate, String toDate, String orderNo, Integer orderStatusId, Integer pharmacyId) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("orderCarriers", "orderCarriers", JoinType.LEFT_OUTER_JOIN);
        if (orderStatusId != null && orderStatusId != 0 && orderStatusId != 7) {
            filterCriteria.add(Restrictions.eq("orderStatus.id", orderStatusId));
        }
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        if (pharmacyId != null) {
            filterCriteria.add(Restrictions.eq("pharmacy.id", pharmacyId));
        }
        if (orderNo != null && !"".equals(orderNo)) {
            filterCriteria.add(Restrictions.eq("id", orderNo));
        }
        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
            filterCriteria.add(Restrictions.between("createdOn", DateUtil.stringToDateTime(fromDate, "MM/dd/yyyy"), DateUtil.stringToDateTime1(toDate, "MM/dd/yyyy")));
        } else if (fromDate != null && !fromDate.isEmpty()) {
            filterCriteria.add(Restrictions.eq("createdOn", DateUtil.stringToDateTime(fromDate, "MM/dd/yyyy")));
        } else if (toDate != null && !toDate.isEmpty()) {
            filterCriteria.add(Restrictions.eq("createdOn", DateUtil.stringToDateTime(toDate, "MM/dd/yyyy")));
        }
        filterCriteria.addOrder(org.hibernate.criterion.Order.desc("createdOn"));
        List<Order> list = filterCriteria.list();
        return list;
    }

    public List<Order> getAllOrderByPharmacyId(Integer currentUserId) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("orderCarriers", "orderCarriers", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.add(Restrictions.or(Restrictions.eq("pharmacy.id", currentUserId), Restrictions.isNull("pharmacy.id")));
        filterCriteria.addOrder(org.hibernate.criterion.Order.desc("updatedOn"));
        return filterCriteria.list();
    }

    public List<Order> searchOrders(String orderNo, String daysBack, String fromDate, String toDate, Integer pharmacyId) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("orderCarriers", "orderCarriers", JoinType.LEFT_OUTER_JOIN);

        if (orderNo != null && !orderNo.isEmpty()) {
            DetachedCriteria dc = DetachedCriteria.forClass(DailyRedemption.class).setProjection(Property.forName("id.transactionNumber"));
            dc.add(Restrictions.eq("prescriptionNumber", orderNo));
            filterCriteria.add(Subqueries.propertyIn("transactionNo", dc));
        }

        if (daysBack != null && !daysBack.isEmpty()) {
            if (daysBack.equalsIgnoreCase("7")) {
                filterCriteria.add(Restrictions.between("updatedOn", DateUtil.getOneWeekBeforeDate(), DateUtil.endDateFormat(new Date())));
            } else if (daysBack.equalsIgnoreCase("30")) {
                filterCriteria.add(Restrictions.between("updatedOn", DateUtil.getOneMonthBefore(), DateUtil.endDateFormat(new Date())));
            }
        }

        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
            filterCriteria.add(Restrictions.between("updatedOn", DateUtil.stringToDateTime(fromDate, "MM/dd/yyyy"), DateUtil.stringToDateTime1(toDate, "MM/dd/yyyy")));
        }

        filterCriteria.add(Restrictions.eq("pharmacy.id", pharmacyId));

        filterCriteria.addOrder(org.hibernate.criterion.Order.desc("updatedOn"));
        return filterCriteria.list();
    }

    public List<Order> getRecentOrderByPharmacyId() throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("orderCarriers", "orderCarriers", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.add(Restrictions.isNull("pharmacy.id"));
        filterCriteria.add(Restrictions.eq("orderStatus.id", 1));
        filterCriteria.addOrder(org.hibernate.criterion.Order.desc("createdOn"));
        return filterCriteria.list();
    }

    public List<Order> getPrescriptionTrackingByPharmacyId(Integer currentUserId) throws Exception {
        Criteria filterCriteria = getCurrentSession().createCriteria(Order.class);
        filterCriteria.createCriteria("orderStatus", "orderStatus", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("pharmacy", "pharmacy", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.createCriteria("orderCarriers", "orderCarriers", JoinType.LEFT_OUTER_JOIN);
        filterCriteria.add(Restrictions.eq("pharmacy.id", currentUserId));
        filterCriteria.add(Restrictions.ne("orderStatus.id", 1));
        filterCriteria.addOrder(org.hibernate.criterion.Order.desc("createdOn"));
        filterCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return filterCriteria.list();
    }

    public List<OrderCarriers> getOrderCarriersList() throws Exception {
        return getCurrentSession().createCriteria(OrderCarriers.class).list();
    }

    public PharmacyUser getPharmacyUserById(Integer id) throws Exception {
        return (PharmacyUser) getCurrentSession().createQuery("from PharmacyUser pharmacyUser where pharmacyUser.id=:id").setParameter("id", id).uniqueResult();
    }

    public List<Order> getDeliveredOrderList(Long orderchainId) throws Exception {
        Query query = getCurrentSession().createQuery("from Order ord where  ord.orderChain.id=:orderId and ord.orderStatus.name in('DELIVERY','Shipped') order by ord.createdOn desc");
        query.setParameter("orderId", orderchainId);
        return query.list();
    }

    public List<OrderHistory> getOrderHistoryList(String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("from OrderHistory orderHistory left join fetch orderHistory.order ord left join fetch ord.orderCarriers oc left join fetch orderHistory.orderStatus orderStatus where ord.id=:orderId order by orderHistory.createdOn desc");
        query.setParameter("orderId", orderId);
        return query.list();
    }

    public OrderCarriers getOrderCarriersByOrderId(String orderId) throws Exception {
        Query query = getCurrentSession().createQuery("from OrderCarriers orderCarrier where orderCarrier.orderList.id=:orderId");
        query.setParameter("orderId", orderId);
        return (OrderCarriers) query.uniqueResult();
    }

    public Integer getOrderQuality(Integer campaignId, String pharmacyNpi) throws Exception {
        Integer result = null;
        Query query = getCurrentSession().createSQLQuery("SELECT count(*) as avg From `DailyRedemption` drf INNER JOIN `RedemptionIngredient` ri ON(drf.`NDC_Number`=ri.`Ndc`) where drf.`CampaignId`='" + campaignId + "' AND drf.`Pharmacy_NPI`='" + pharmacyNpi + "' and ROUND(((drf.`Plan_Pharmacy_Amount`/drf.`Pharmacy_Gross_Amount`)*100))");
        List<Object[]> list = query.list();
        if (list != null && !list.isEmpty()) {
            Object value = list.get(0);
            result = (Integer.parseInt(value.toString()));
        }
        return result;
    }

    public Order getPatientProfileDetailByTransactionNo(String transactionNumber) throws Exception {
        Order order = new Order();
        SQLQuery sQLQuery = getCurrentSession().createSQLQuery("SELECT "
                + "  PtProfile_Info.`FirstName`,PtProfile_Info.`LastName`,"
                + "  PtProfile_Address.`Address`,PtProfile_Address.`City`,State.`Abbr`,PtProfile_Address.`zip`,"
                + "  PtProfile_PaymentInfo.`CardNumber`,PtProfile_PaymentInfo.`CardHolderName`,PtProfile_PaymentInfo.`ExpiryDate`,"
                + "  PtProfile_PaymentInfo.`CCVNumber`,PtProfile_PaymentInfo.`CardType` "
                + "FROM "
                + "  `PtProfile_Info` "
                + "  INNER JOIN PtProfile_PaymentInfo ON PtProfile_PaymentInfo.`ID` =`PtProfile_Info`.`PaymentInfoID`"
                + "  INNER JOIN `PtProfile_Address` ON PtProfile_Address.`ID`=PtProfile_Info.`ShippingAddressID`"
                + "  INNER JOIN `State` ON State.`Id`=PtProfile_Address.`StateID`"
                + "  INNER JOIN `DailyRedemption` ON DailyRedemption.`Communication_Id`=`PtProfile_Info`.`CommunicationID` "
                + "  WHERE DailyRedemption.`Transaction_Number`= ? And PtProfile_Info.`Status`='Approved'");
        sQLQuery.setParameter(0, transactionNumber);
        List<Object[]> list = sQLQuery.list();
        if (list != null && !list.isEmpty()) {
            for (Object[] object : list) {
                order.setFirstName(object[0].toString());
                order.setLastName(object[1].toString());
                order.setStreetAddress(object[2].toString());
                order.setCity(object[3].toString());
                order.setState(object[4].toString());
                order.setZip(object[5].toString());
                order.setCardNumber(object[6].toString());
                order.setCardHolderName(object[7].toString());
                order.setCardExpiry(object[8].toString());
                order.setCardCvv(object[9].toString());
                order.setCardType(object[10].toString());
                break;
            }
        }
        return order;
    }

    public List populateOrderListForFinancialReport(Date frmDate, Date toDate, String orderStatusStr, int pharmacyId) {
        String sql = " From Order o left join fetch o.drugDetail as detail "
                + " left join fetch detail.drugBasic as drugBasic "
                + " left join fetch detail.drugForm "
                + " left join fetch drugBasic.drugGeneric "
                + " where o.orderStatus.id in(5,6,15) "
                + " and o.filledDate between :frmDate and :toDate ";
        if (pharmacyId > 0) {
            sql += " and o.pharmacy.id=:pharmacyId";
        }
        Query query = getCurrentSession().createQuery(sql);

        // query.setParameter("orderStatus", orderStatusStr);
        query.setDate("frmDate", frmDate);
        query.setDate("toDate", toDate);
        if (pharmacyId > 0) {
            query.setParameter("pharmacyId", pharmacyId);
        }

        return query.list();

    }

    public List<Order> getMultiRxOrdersByPatientId(Integer orderId, Integer patientId, Integer pharmacyId) throws Exception {
        List<Order> orders = null;
        Query query = getCurrentSession().createQuery("From Order ord inner join fetch ord.patientProfile pf "
                + "left join fetch ord.orderStatus os "
                + "left join fetch ord.pharmacy pharmacy "
                + "left join fetch ord.deliveryPreference deliveryPreference "
                + "left join fetch ord.drugDetail drugDetail "
                + "left join fetch drugDetail.drugBasic drugBasic "
                + "left join fetch drugBasic.drugGeneric drugGeneric "
                + "where pf.id=:patientId and os.id=:orderStatus and ord.patientProfileMembers is null and pharmacy.id=:pharmacyId");
//                + "where ord.id<>:orderId and pf.id=:patientId and os.id=:orderStatus and ord.patientProfileMembers is null");
//        query.setInteger("orderId", orderId);
        query.setInteger("patientId", patientId);
        query.setInteger("orderStatus", Constants.ORDER_STATUS.FILLED_ID);
        query.setInteger("pharmacyId", pharmacyId);
        orders = query.list();

        return orders;
    }

    public List<Order> getMultiRxOrdersByDependentId(Integer orderId, Integer patientId, Integer pharmacyId) throws Exception {
        List<Order> orders = null;
        Query query = getCurrentSession().createQuery("From Order ord inner join fetch ord.patientProfileMembers pf "
                + "left join fetch ord.orderStatus os "
                + "left join fetch ord.pharmacy pharmacy "
                + "left join fetch ord.deliveryPreference deliveryPreference "
                + "left join fetch ord.drugDetail drugDetail "
                + "left join fetch drugDetail.drugBasic drugBasic "
                + "left join fetch drugBasic.drugGeneric drugGeneric "
                + "where pf.id=:patientId and os.id=:orderStatus "
                + "and pharmacy.id=:pharmacyId ");
//                + "where ord.id<>:orderId and pf.id=:patientId and os.id=:orderStatus");
//        query.setInteger("orderId", orderId);
        query.setInteger("patientId", patientId);
        query.setInteger("orderStatus", Constants.ORDER_STATUS.FILLED_ID);
        query.setInteger("pharmacyId", pharmacyId);
        orders = query.list();

        return orders;
    }

    //////////////////////////////////////////////////////////////
    public List<Order> getMultiRxOrdersStatusWiseByPatientId(Integer patientId, int status, Integer pharmacyId) throws Exception {
        List<Order> orders = null;
        Query query1 = getCurrentSession().createQuery("From Order ord inner join fetch ord.patientProfile pf "
                + "left join fetch ord.orderStatus os "
                + " left join fetch ord.pharmacy pharmacy "
                + "where pf.id=:patientId and os.id=:orderStatus and ord.patientProfileMembers is null and (pharmacy.id is null or pharmacy.id=:pharmacyId)");
        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfile pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id=:orderStatus and ord.patientProfileMembers is null ");
//        if (status == Constants.ORDER_STATUS.PENDING_ID) {
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
//        } else {
//            sb.append("and pharmacy.id=:pharmacyId ");
//        }
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setInteger("orderStatus", status);
//        if (status != Constants.ORDER_STATUS.PENDING_ID) {
        query.setInteger("pharmacyId", pharmacyId);
//        }
        orders = query.list();

        return orders;
    }

    public List<Order> getMultiRxOrdersStatusWiseByDependentId(Integer patientId, int status, Integer pharmacyId) throws Exception {
        List<Order> orders = null;
        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfileMembers pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id=:orderStatus ");
//        if (status == Constants.ORDER_STATUS.PENDING_ID) {
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
//        } else {
//            sb.append("and pharmacy.id=:pharmacyId ");
//        }
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setInteger("orderStatus", status);
//        if (status != Constants.ORDER_STATUS.PENDING_ID) {
        query.setInteger("pharmacyId", pharmacyId);
//        }
        orders = query.list();

        return orders;
    }

    //////////////////////////////////////////////////////////////
    public List<Order> getOrdersByDependentId(Integer patientId, List statusIds, Integer pharmacyId) throws Exception {
        List<Order> orders = null;
        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfileMembers pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("where pf.id=:patientId and os.id in(:orderStatus) ");
//        if (status == Constants.ORDER_STATUS.PENDING_ID) {
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
//        } else {
//            sb.append("and pharmacy.id=:pharmacyId ");
//        }
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setParameterList("orderStatus", statusIds);
//        if (status != Constants.ORDER_STATUS.PENDING_ID) {
        query.setInteger("pharmacyId", pharmacyId);
//        }
        orders = query.list();

        return orders;
    }

    //////////////////////////////////////////////////////////////
    public List<Order> getOrdersByPatientId(Integer patientId, List statusIds, Integer pharmacyId) throws Exception {
        List<Order> orders = null;

        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfile pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id in(:orderStatus) and ord.patientProfileMembers is null ");
//        if (status == Constants.ORDER_STATUS.PENDING_ID) {
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
//        } else {
//            sb.append("and pharmacy.id=:pharmacyId ");
//        }
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setParameterList("orderStatus", statusIds);
//        if (status != Constants.ORDER_STATUS.PENDING_ID) {
        query.setInteger("pharmacyId", pharmacyId);
//        }
        orders = query.list();

        return orders;
    }

    ////////////////////////////////////////////////////////////
    public PatientProfile getPatientProfile(String mobileNumber) throws Exception {
        Query query = getCurrentSession().createQuery("From PatientProfile pf "
                + "where pf.mobileNumber = :mobileNumber ");
        query.setString("mobileNumber", mobileNumber);
        return (PatientProfile) query.uniqueResult();
    }

    public List<Order> getpopulateAutoDeleteOrder(Integer autoDeletionFlag, Date autoDeletionDate, Integer orderStatus) throws Exception {
        List<Order> orders = null;
        Query query = getCurrentSession().createQuery("From Order where autoDeletionFlag=:autoDeletionFlag and autoDeletionDate<=:autoDeletionDate and orderStatus.id=:orderStatus");
        query.setParameter("autoDeletionFlag", autoDeletionFlag);
        query.setParameter("autoDeletionDate", autoDeletionDate);
        query.setParameter("orderStatus", orderStatus);
        orders = query.list();
        return orders;

    }

    public Long getBasicStatisticsReport(BaseDTO baseDTO, Boolean isAccountHolder, Boolean isAdult, String gender) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Select Count(*) from Order o ");
        sb.append("left join o.patientProfileMembers patientProfileMembers ");
        sb.append("left join o.orderStatus orderStatus ");
        sb.append("where o.createdOn>=:fromDate ");
        sb.append("and o.createdOn<=:toDate ");
        sb.append("and o.orderStatus.name not in ('Cancelled','Hidden') ");
        if (isAccountHolder != null && isAccountHolder) {
            sb.append(" and o.patientProfileMembers.id is null ");
        }
        if (isAdult != null) {
            sb.append(" and o.patientProfileMembers.id is not null ");
            sb.append(" and o.patientProfileMembers.isAdult=:isAdult");
        }
        if (CommonUtil.isNotEmpty(gender)) {
            sb.append(" and o.patientProfileMembers.id is not null ");
            sb.append(" and o.patientProfileMembers.gender=:gender");
        }
        Query query = getCurrentSession().createQuery(sb.toString());
        if (baseDTO.getFromDate() != null) {
            query.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            query.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        if (isAdult != null) {
            query.setParameter("isAdult", isAdult);
        }
        if (CommonUtil.isNotEmpty(gender)) {
            query.setParameter("gender", gender);
        }
        return (Long) query.uniqueResult();
    }

    public Long getTotalAccountHolderGCount(BaseDTO baseDTO, String gender) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Select Count(*) from Order o ");
        sb.append("left join o.patientProfile patientProfile ");
        sb.append("left join o.orderStatus orderStatus ");
        sb.append("where o.patientProfileMembers.id is null and o.createdOn>=:fromDate ");
        sb.append("and o.createdOn<=:toDate ");
        sb.append("and o.orderStatus.name not in ('Cancelled','Hidden') ");

        if (CommonUtil.isNotEmpty(gender)) {
            sb.append(" and o.patientProfile.gender=:gender");
        }
        Query query = getCurrentSession().createQuery(sb.toString());
        if (baseDTO.getFromDate() != null) {
            query.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            query.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        if (CommonUtil.isNotEmpty(gender)) {
            query.setParameter("gender", gender);
        }
        return (Long) query.uniqueResult();
    }

    public BasicStatisticsReportDTO getRxPateintMembersAgeCount(BaseDTO baseDTO) throws Exception {
        BasicStatisticsReportDTO bsrdto = new BasicStatisticsReportDTO();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE()) AS ageInYear,");
        sb.append(" CASE ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=0 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=17 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=18 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=34 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=35 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=50 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=51 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=64 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=65 ");
        sb.append("THEN COUNT(*) ");
        sb.append(" END AS TotalAgeCount ");
        sb.append(" FROM(SELECT CAST( CONCAT( SUBSTRING(BirthDate,7,4),'-',SUBSTRING(BirthDate,1,2),'-', SUBSTRING(BirthDate,4,2))AS DATE)AS BirthDate ");
        sb.append("FROM ( ");
        sb.append("SELECT AES_DECRYPT(FROM_BASE64(BirthDate), :aesKey)AS BirthDate FROM ");
        sb.append(" Orders AS o ");
        sb.append(" INNER JOIN PatientProfileMembers ON PatientProfileMembers.Id = o.DependentId ");
        sb.append(" INNER JOIN OrderStatus ON OrderStatus.Id = o.OrderStatus ");
        sb.append(" WHERE o.`DependentId` IS NOT NULL ");
        sb.append(" AND OrderStatus.Name NOT IN ('Cancelled','Hidden') ");
        sb.append(" AND o.CreatedOn>=:fromDate ");
        sb.append(" AND o.CreatedOn<=:toDate ");
        sb.append(" )AS BirthDecryption  )AS BirthDateFormatting");
        sb.append(" GROUP BY TIMESTAMPDIFF(YEAR,BirthDateFormatting.BirthDate,CURDATE())");

        SQLQuery lQuery = getCurrentSession().createSQLQuery(sb.toString());
        lQuery.setParameter("fromDate", baseDTO.getFromDate());
        lQuery.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        lQuery.setParameter("aesKey", Constants.AES_KEY);
        populateBithCountData(lQuery, bsrdto);
        return bsrdto;
    }

    public BasicStatisticsReportDTO getPateintBirthAgeCount(BaseDTO baseDTO) throws Exception {
        BasicStatisticsReportDTO bsrdto = new BasicStatisticsReportDTO();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE()) AS ageInYear,");
        sb.append(" CASE ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=0 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=17 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=18 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=34 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=35 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=50 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=51 ");
        sb.append("AND TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())<=64 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When TIMESTAMPDIFF(YEAR,BirthDateFormatting.`BirthDate`,CURDATE())>=65 ");
        sb.append("THEN COUNT(*) ");
        sb.append(" END AS TotalAgeCount ");
        sb.append(" FROM(SELECT CAST( CONCAT( SUBSTRING(BirthDate,7,4),'-',SUBSTRING(BirthDate,1,2),'-', SUBSTRING(BirthDate,4,2))AS DATE)AS BirthDate ");
        sb.append("FROM ( ");
        sb.append("SELECT AES_DECRYPT(FROM_BASE64(BirthDate), :aesKey)AS BirthDate FROM ");
        sb.append(" Orders AS o ");
        sb.append(" INNER JOIN PatientProfileInfo ON PatientProfileInfo.Id = o.PatientId ");
        sb.append(" INNER JOIN OrderStatus ON OrderStatus.Id = o.OrderStatus ");
        sb.append(" WHERE o.`DependentId` IS NULL ");
        sb.append(" AND OrderStatus.Name NOT IN ('Cancelled','Hidden') ");
        sb.append(" AND o.CreatedOn>=:fromDate ");
        sb.append(" AND o.CreatedOn<=:toDate ");
        sb.append(" )AS BirthDecryption  )AS BirthDateFormatting");
        sb.append(" GROUP BY TIMESTAMPDIFF(YEAR,BirthDateFormatting.BirthDate,CURDATE())");

        SQLQuery lQuery = getCurrentSession().createSQLQuery(sb.toString());
        lQuery.setParameter("fromDate", baseDTO.getFromDate());
        lQuery.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        lQuery.setParameter("aesKey", Constants.AES_KEY);
        populateBithCountData(lQuery, bsrdto);
        return bsrdto;
    }

    private void populateBithCountData(SQLQuery lQuery, BasicStatisticsReportDTO bsrdto) throws HibernateException {
        Long underAge17Count = 0L;
        Long underAge18Count = 0L;
        Long underAge35Count = 0L;
        Long underAge51Count = 0L;
        Long underAge65Count = 0L;

        List<Object[]> queryResult = lQuery.list();
        if (!CommonUtil.isNullOrEmpty(queryResult)) {
            for (Object[] record : queryResult) {
                Object value = record[0];
                Object value1 = record[1];
                if (value != null) {
                    BigInteger ageInYear = (BigInteger) value;
                    BigInteger totalAgeCount = (BigInteger) value1;
                    if (ageInYear.intValue() >= 0 && ageInYear.intValue() <= 17) {
                        underAge17Count = underAge17Count + totalAgeCount.longValue();
                    } else if (ageInYear.intValue() >= 18 && ageInYear.intValue() <= 34) {
                        underAge18Count = underAge18Count + totalAgeCount.longValue();
                    } else if (ageInYear.intValue() >= 35 && ageInYear.intValue() <= 50) {
                        underAge35Count = underAge35Count + totalAgeCount.longValue();
                    } else if (ageInYear.intValue() >= 51 && ageInYear.intValue() <= 64) {
                        underAge51Count = underAge51Count + totalAgeCount.longValue();
                    } else if (ageInYear.intValue() >= 65) {
                        underAge65Count = underAge65Count + totalAgeCount.longValue();
                    }
                }
            }
            bsrdto.setUnderAge17Count(underAge17Count);
            bsrdto.setUnderAge18Count(underAge18Count);
            bsrdto.setUnderAge35Count(underAge35Count);
            bsrdto.setUnderAge51Count(underAge51Count);
            bsrdto.setUnderAge65Count(underAge65Count);
        }
    }

    public Long getInsuranceCount(BaseDTO baseDTO, String finalPaymentMode) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Select Count(*) from Order o ");
        sb.append("left join o.orderStatus orderStatus ");
        sb.append("where o.orderStatus.name in ('Filled','Shipped','DELIVERY','Pickup At Pharmacy') ");
        sb.append("and o.createdOn>=:fromDate ");
        sb.append("and o.createdOn<=:toDate ");
        if (CommonUtil.isNotEmpty(finalPaymentMode)) {
            sb.append("and o.finalPaymentMode=:finalPaymentMode ");
        }
        Query query = getCurrentSession().createQuery(sb.toString());
        if (baseDTO.getFromDate() != null) {
            query.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            query.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        if (!CommonUtil.isNullOrEmpty(finalPaymentMode)) {
            query.setParameter("finalPaymentMode", finalPaymentMode);
        }
        return (Long) query.uniqueResult();
    }

    public BigInteger getRxMixCount(BaseDTO baseDTO, String brandIndicator) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" COUNT(*) ");
        sb.append(" FROM ");
        sb.append(" Orders ");
        sb.append(" INNER JOIN `OrderStatus` ON OrderStatus.`Id`=Orders.`OrderStatus` ");
        sb.append(" INNER JOIN `drugdetail` ON drugdetail.`drugdetailid`=Orders.`DrugNdc` ");
        sb.append(" INNER JOIN drugbasic ON drugbasic.`DrugBasicId`=drugdetail.`DrugBasicId` ");
        sb.append(" WHERE drugbasic.`BrandIndicator`=:brandIndicator ");
        sb.append(" AND Orders.CreatedOn>=:fromDate ");
        sb.append(" AND Orders.CreatedOn<=:toDate ");
        sb.append(" AND OrderStatus.`Name` IN ('Filled','Shipped','DELIVERY','Pickup At Pharmacy') ");
        SQLQuery lQuery = getCurrentSession().createSQLQuery(sb.toString());
        lQuery.setParameter("brandIndicator", brandIndicator);
        if (baseDTO.getFromDate() != null) {
            lQuery.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            lQuery.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        return (BigInteger) lQuery.uniqueResult();
    }

    public BigInteger getRxFulfillmentCount(BaseDTO baseDTO, Integer deleiveryPreferenceId) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" COUNT(*) ");
        sb.append(" FROM ");
        sb.append(" Orders ");
        sb.append(" INNER JOIN `OrderStatus` ON OrderStatus.`Id`=Orders.`OrderStatus` ");
        sb.append(" WHERE OrderStatus.`Name` IN ('Filled','Shipped','DELIVERY','Pickup At Pharmacy') ");
        sb.append(" AND Orders.CreatedOn>=:fromDate ");
        sb.append(" AND Orders.CreatedOn<=:toDate ");
        sb.append(" AND Orders.DeleiveryPreferenceId=:deleiveryPreferenceId");
        SQLQuery lQuery = getCurrentSession().createSQLQuery(sb.toString());
        lQuery.setParameter("deleiveryPreferenceId", deleiveryPreferenceId);
        if (baseDTO.getFromDate() != null) {
            lQuery.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            lQuery.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }
        return (BigInteger) lQuery.uniqueResult();
    }

    public BasicStatisticsReportDTO getDeliveryDistanceCount(BaseDTO baseDTO, List<Integer> deleiveryPreferenceId, Long totalOrderCount) throws Exception {
        BasicStatisticsReportDTO basicStatisticsReportDTO = new BasicStatisticsReportDTO();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" o.Miles,");
        sb.append(" CASE ");
        sb.append("When o.Miles=0 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.Miles >=1 AND o.Miles <=5 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.Miles >= 6 AND o.Miles <= 10 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.Miles >= 11 AND o.Miles <= 20 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.Miles > 20");
        sb.append("THEN COUNT(*) ");
        sb.append(" END AS TotalMilesCount ");
        sb.append(" FROM ");
        sb.append(" Orders AS o ");
        sb.append(" INNER JOIN `OrderStatus` ON OrderStatus.`Id`=o.`OrderStatus` ");
        sb.append(" WHERE OrderStatus.`Name` IN ('Filled','Shipped','DELIVERY','Pickup At Pharmacy') ");
        sb.append(" AND o.Miles IS NOT NULL ");
        sb.append(" AND o.DeleiveryPreferenceId IN (:deleiveryPreferenceId) ");
        sb.append(" AND Orders.CreatedOn>=:fromDate ");
        sb.append(" AND Orders.CreatedOn<=:toDate ");
        sb.append(" GROUP BY o.Miles");

        SQLQuery lQuery = getCurrentSession().createSQLQuery(sb.toString());
        lQuery.setParameter("deleiveryPreferenceId", deleiveryPreferenceId);
        if (baseDTO.getFromDate() != null) {
            lQuery.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            lQuery.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }

        Long zeroDistanceCount = 0L;
        Long distance1To5Count = 0L;
        Long distance6To10Count = 0L;
        Long distance11To20Count = 0L;
        Long distance20GreaterCount = 0L;
        List<Object[]> queryResult = lQuery.list();
        if (!CommonUtil.isNullOrEmpty(queryResult)) {
            for (Object[] record : queryResult) {
                Object value = record[0];
                Object value1 = record[1];
                if (value != null) {
                    Long miles = (Long) value;
                    Long totalMilesCount = (Long) value1;
                    if (miles == 0) {
                        zeroDistanceCount = zeroDistanceCount + totalMilesCount;
                    } else if (miles >= 1 && miles <= 5) {
                        distance1To5Count = distance1To5Count + totalMilesCount;
                    } else if (miles >= 6 && miles <= 10) {
                        distance6To10Count = distance6To10Count + totalMilesCount;
                    } else if (miles >= 11 && miles <= 20) {
                        distance11To20Count = distance11To20Count + totalMilesCount;
                    } else if (miles > 20) {
                        distance20GreaterCount = distance20GreaterCount + totalMilesCount;
                    }
                }
            }
            zeroDistanceCount = (100 * zeroDistanceCount / totalOrderCount);
            basicStatisticsReportDTO.setZeroDistanceCount(zeroDistanceCount);
            distance1To5Count = (100 * distance1To5Count / totalOrderCount);
            basicStatisticsReportDTO.setDistance1To5Count(distance1To5Count);
            distance6To10Count = (100 * distance6To10Count / totalOrderCount);
            basicStatisticsReportDTO.setDistance6To10Count(distance6To10Count);
            distance11To20Count = (100 * distance11To20Count / totalOrderCount);
            basicStatisticsReportDTO.setDistance11To20Count(distance11To20Count);
            distance20GreaterCount = (100 * distance20GreaterCount / totalOrderCount);
            basicStatisticsReportDTO.setDistance20GreaterCount(distance20GreaterCount);
        }
        return basicStatisticsReportDTO;
    }

    public BasicStatisticsReportDTO getPatientOutOfPocketCount(BaseDTO baseDTO, Long totalOrderCount) throws Exception {
        BasicStatisticsReportDTO basicStatisticsReportDTO = new BasicStatisticsReportDTO();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" o.OriginalPtCopay,");
        sb.append(" CASE ");
        sb.append("When o.OriginalPtCopay=0 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.OriginalPtCopay >=0.01 AND o.OriginalPtCopay <=25 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.OriginalPtCopay >=26 AND o.OriginalPtCopay <= 75 ");
        sb.append("THEN COUNT(*) ");
        sb.append("When o.OriginalPtCopay >= 76 ");
        sb.append("THEN COUNT(*) ");
        sb.append(" END AS TotalPtCopayCount ");
        sb.append(" FROM ");
        sb.append(" Orders AS o ");
        sb.append(" INNER JOIN `OrderStatus` ON OrderStatus.`Id`=o.`OrderStatus` ");
        sb.append(" WHERE OrderStatus.`Name` IN ('Filled','Shipped','DELIVERY','Pickup At Pharmacy') ");
        sb.append(" AND o.OriginalPtCopay IS NOT NULL ");
        sb.append(" AND o.CreatedOn>=:fromDate ");
        sb.append(" AND o.CreatedOn<=:toDate ");
        sb.append(" GROUP BY o.OriginalPtCopay");

        System.out.println(sb.toString());
        SQLQuery lQuery = getCurrentSession().createSQLQuery(sb.toString());
        if (baseDTO.getFromDate() != null) {
            lQuery.setParameter("fromDate", baseDTO.getFromDate());
        }
        if (baseDTO.getToDate() != null) {
            lQuery.setParameter("toDate", DateUtil.addDays(baseDTO.getToDate(), 1));
        }

        Long ptOutofPocketCount = 0L;
        Long ptOutofPocket1To25Count = 0L;
        Long ptOutofPocket26To75Count = 0L;
        Long ptOutofPocket76GreaterCount = 0L;
        List<Object[]> queryResult = lQuery.list();
        if (!CommonUtil.isNullOrEmpty(queryResult)) {
            for (Object[] record : queryResult) {
                Object value = record[0];
                Object value1 = record[1];
                if (value != null) {
                    Float originalPtCopay = (Float) value;
                    BigInteger totalPtCopayCount = (BigInteger) value1;
                    if (originalPtCopay == 0) {
                        ptOutofPocketCount = ptOutofPocketCount + totalPtCopayCount.longValue();
                    } else if (originalPtCopay >= 0.01 && originalPtCopay <= 25) {
                        ptOutofPocket1To25Count = ptOutofPocket1To25Count + totalPtCopayCount.longValue();
                    } else if (originalPtCopay >= 26 && originalPtCopay <= 75) {
                        ptOutofPocket26To75Count = ptOutofPocket26To75Count + totalPtCopayCount.longValue();
                    } else if (originalPtCopay >= 76) {
                        ptOutofPocket76GreaterCount = ptOutofPocket76GreaterCount + totalPtCopayCount.longValue();
                    }
                }
            }

            basicStatisticsReportDTO.setPtOutofPocketNumber(ptOutofPocketCount);
            Double ptOutof_Pocket_Count = (100D * ptOutofPocketCount / totalOrderCount);
            basicStatisticsReportDTO.setPtOutofPocketCount(CommonUtil.getDecimalFormat(ptOutof_Pocket_Count));

            basicStatisticsReportDTO.setPtOutofPocket1To25Number(ptOutofPocket1To25Count);
            Double ptOutof_Pocket_1To25_Count = (100D * ptOutofPocket1To25Count / totalOrderCount);
            basicStatisticsReportDTO.setPtOutofPocket1To25Count(CommonUtil.getDecimalFormat(ptOutof_Pocket_1To25_Count));

            basicStatisticsReportDTO.setPtOutofPocket26To75Number(ptOutofPocket26To75Count);
            Double ptOutof_Pocket_26To75_Count = (100D * ptOutofPocket26To75Count / totalOrderCount);
            basicStatisticsReportDTO.setPtOutofPocket26To75Count(CommonUtil.getDecimalFormat(ptOutof_Pocket_26To75_Count));

            basicStatisticsReportDTO.setPtOutofPocket76GreaterNumber(ptOutofPocket76GreaterCount);
            Double ptOutof_Pocket_76Greater_Count = (100D * ptOutofPocket76GreaterCount / totalOrderCount);
            basicStatisticsReportDTO.setPtOutofPocket76GreaterCount(CommonUtil.getDecimalFormat(ptOutof_Pocket_76Greater_Count));

            Double totalPtOutOfPocketCount = ptOutof_Pocket_Count + ptOutof_Pocket_1To25_Count + ptOutof_Pocket_26To75_Count + ptOutof_Pocket_76Greater_Count;
            basicStatisticsReportDTO.setTotalPtOutOfPocketCount(CommonUtil.getDecimalFormat(totalPtOutOfPocketCount));
        }
        return basicStatisticsReportDTO;
    }

    public List<OrderDetailDTO> getOrderBatchsList(Integer patientId, String orderId) throws Exception {
        List<OrderDetailDTO> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("Select OrderBatch.PatientId,Orders.Id ");
        query.append("FROM OrderBatch ");
        query.append("INNER JOIN Orders ON Orders.PatientId=OrderBatch.PatientId ");
        query.append("AND Orders.OrderBatchId=OrderBatch.Id ");
        query.append("WHERE OrderBatch.PatientId=:patientId ");
        query.append("AND Orders.Id!=:orderId");

        SQLQuery lQuery = getCurrentSession().createSQLQuery(query.toString());
        lQuery.setParameter("patientId", patientId);
        lQuery.setParameter("orderId", orderId);
        List<Object[]> queryResult = lQuery.list();
        if (!CommonUtil.isNullOrEmpty(queryResult)) {
            queryResult.stream().map((record) -> {
                OrderDetailDTO detailDTO = new OrderDetailDTO();
                Object ptId = record[0];
                Object ordId = record[1];
                if (ptId != null) {
                    Integer patId = (Integer) ptId;
                    detailDTO.setPatientId(patId.toString());
                }
                if (ordId != null) {
                    detailDTO.setId(ordId.toString());
                }
                return detailDTO;
            }).forEach((detailDTO) -> {
                list.add(detailDTO);
            });
        }
        return list;
    }

    //////////////////////////////////////////////////////////////
    public List<Order> getOrdersByDependentId(Integer patientId, List statusIds, Integer pharmacyId,
            int prefId) throws Exception {
        List<Order> orders = null;
        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfileMembers pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id in(:orderStatus) ");
//        if (status == Constants.ORDER_STATUS.PENDING_ID) {
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
        sb.append(" and deliveryPreference.id =:prefId ");
//        } else {
//            sb.append("and pharmacy.id=:pharmacyId ");
//        }
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setParameterList("orderStatus", statusIds);
//        if (status != Constants.ORDER_STATUS.PENDING_ID) {
        query.setInteger("pharmacyId", pharmacyId);
        query.setInteger("prefId", prefId);
//        }
        orders = query.list();

        return orders;
    }

    //////////////////////////////////////////////////////////////
    public List<Order> getOrdersByPatientId(Integer patientId, List statusIds, Integer pharmacyId,
            int prefId) throws Exception {
        List<Order> orders = null;

        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord inner join fetch ord.patientProfile pf ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id in(:orderStatus) and ord.patientProfileMembers is null ");
//        if (status == Constants.ORDER_STATUS.PENDING_ID) {
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
        sb.append(" and ord.deliveryPreference.id =:prefId ");
//        } else {
//            sb.append("and pharmacy.id=:pharmacyId ");
//        }
        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setParameterList("orderStatus", statusIds);
//        if (status != Constants.ORDER_STATUS.PENDING_ID) {
        query.setInteger("pharmacyId", pharmacyId);
        query.setInteger("prefId", prefId);
//        }
        orders = query.list();

        return orders;
    }

    public List<Order> getMultiRxOrders(Integer patientId, Integer status, Integer readyToDeliverRxId, Integer prefId, Integer pharmacyId, String orderId) throws Exception {
        List<Order> orders = null;
        StringBuilder sb = new StringBuilder();
        sb.append("From Order ord ");
        sb.append("inner join fetch ord.patientProfile pf ");
        sb.append("left join fetch ord.patientProfileMembers patientProfileMembers ");
        sb.append("left join fetch ord.orderStatus os ");
        sb.append("left join fetch ord.pharmacy pharmacy ");
        sb.append("left join fetch ord.deliveryPreference deliveryPreference ");
        sb.append("left join fetch ord.readyToDeliverRxOrders readyToDeliverRxOrders ");
        sb.append("left join fetch ord.drugDetail drugDetail ");
        sb.append("left join fetch drugDetail.drugBasic drugBasic ");
        sb.append("left join fetch drugBasic.drugGeneric drugGeneric ");
        sb.append("where pf.id=:patientId and os.id=:orderStatus ");
        sb.append("and (pharmacy.id is null or pharmacy.id=:pharmacyId) ");
        sb.append("and readyToDeliverRxOrders.id=:readyToDeliverRxId ");
        sb.append("and deliveryPreference.id=:prefId ");
        sb.append("and ord.id!=:orderId ");

        Query query = getCurrentSession().createQuery(sb.toString());
        query.setInteger("patientId", patientId);
        query.setInteger("orderStatus", status);
        query.setInteger("pharmacyId", pharmacyId);
        query.setInteger("readyToDeliverRxId", readyToDeliverRxId);
        query.setInteger("prefId", prefId);
        query.setString("orderId", orderId);

        orders = query.list();

        return orders;
    }
}
