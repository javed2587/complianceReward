package com.ssa.cms.delegate;

import com.ssa.cms.common.Constants;
import com.ssa.cms.dao.OrderDAO;
import com.ssa.cms.dto.BaseDTO;
import com.ssa.cms.dto.BasicStatisticsReportDTO;
import com.ssa.cms.dto.OrderDetailDTO;
import com.ssa.cms.model.*;
import com.ssa.cms.service.PatientProfileService;
import com.ssa.cms.util.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderDAO orderDao;
    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private PatientProfileService patientProfileService;

    public Order getOrderDetail(String orderId) {
        Order order = new Order();
        try {
            order = orderDao.getOrdersById(orderId);
            DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
            order.setDailyRedemption(dailyRedemption);
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
        return order;
    }

   
    /////////////////////////////////////////////////////////////

    public void acceptOrderList(String orderIds, Integer pharmacyId, Integer currentUserId) {
        if (orderIds != null && !orderIds.isEmpty()) {
            List<String> orderIdList = Arrays.asList(orderIds.split(","));
            for (String orderId : orderIdList) {
                updateOrderStatus(orderId, 2, pharmacyId, currentUserId);
            }
        }
    }

    public boolean updateOrderStatus(String orderId, Integer status, Integer pharmacyId, Integer currentUserId) {
        boolean update = false;
        try {
            Order order = new Order();
            order = (Order) orderDao.findRecordById(order, orderId);
            OrderStatus orderStatus = new OrderStatus();
            orderStatus = (OrderStatus) orderDao.findRecordById(orderStatus, status);
            order.setOrderStatus(orderStatus);
            OrderHistory orderHistory = new OrderHistory();
            List<OrderHistory> historys = new ArrayList<>();
            Pharmacy pharmacy = new Pharmacy();
            pharmacy = (Pharmacy) orderDao.findRecordById(pharmacy, pharmacyId);
            if (status == 2) {
                order.setPharmacy(pharmacy);
                orderHistory.setComments("Assigned By " + pharmacy.getTitle());
                setOrderHistory(orderHistory, order, orderStatus, currentUserId, historys);
            } else {
                order.setPharmacy(null);
                orderHistory.setComments("Rejected By " + pharmacy.getTitle());
                setOrderHistory(orderHistory, order, orderStatus, currentUserId, historys);

            }
            order.setUpdatedBy(currentUserId);
            order.setUpdatedOn(new Date());
            orderDao.update(order);
            update = true;
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
        return update;
    }

    private void setOrderHistory(OrderHistory orderHistory, Order order, OrderStatus orderStatus, Integer currentUserId, List<OrderHistory> historys) {
        orderHistory.setOrder(order);
        orderHistory.setOrderStatus(orderStatus);
        orderHistory.setCreatedBy(currentUserId);
        orderHistory.setCreatedOn(new Date());
        orderHistory.setUpdatedBy(currentUserId);
        orderHistory.setUpdatedOn(new Date());
        historys.add(orderHistory);
        order.setOrderHistory(historys);
    }

    public Drug getObject(Integer orderId) {
        Drug drug = new Drug();
        try {
            drug = (Drug) orderDao.findRecordById(drug, orderId);

            Hibernate.initialize(drug.getDrugBrand());

        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
        return drug;
    }

    public DailyRedemption getRedemptionDetail(DailyRedemptionId redemptionId) {
        DailyRedemption order = new DailyRedemption();
        try {
            order = orderDao.getTransactionDetail(redemptionId);
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
        return order;
    }

    public void save(Object ob) {
        try {
            orderDao.save(ob);
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
    }

    public void update(Object o) {
        try {
            orderDao.update(o);
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
    }

    public Order saveOrder(Order order, Integer currentUserId) {
        try {
            order.setCreatedBy(currentUserId);
            order.setCreatedOn(new Date());
            order.setUpdatedBy(currentUserId);
            order.setUpdatedOn(new Date());
            if (order.getOrderCarriers() == null) {
                order.setOrderCarriers(null);
            }
            if (order.getOrderHistory() != null) {
                for (OrderHistory orderHistory : order.getOrderHistory()) {
                    orderHistory.setOrder(order);
                    orderHistory.setCreatedBy(currentUserId);
                    orderHistory.setCreatedOn(new Date());
                    orderHistory.setUpdatedBy(currentUserId);
                    orderHistory.setUpdatedOn(new Date());
                    orderHistory.setOrderStatus(order.getOrderStatus());
                }
            }
            orderDao.save(order);
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
        return order;
    }

    public List<Order> getOrderList(Integer orderStatusId, Integer pharmacyId, String fromDate, String toDate) {
        List<Order> list = new ArrayList<>();
        try {
            if (orderStatusId != null || fromDate != null || toDate != null) {
                List<Order> newlist = orderDao.getOrdersList(orderStatusId, pharmacyId, fromDate, toDate);
                for (Order order : newlist) {
                    order.setPatientName(order.getFirstName() + " " + order.getLastName());
                    DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
                    if (dailyRedemption != null) {
                        order.setRxNo(dailyRedemption.getPrescriptionNumber());
                    }
                    list.add(order);
                }
            }
        } catch (Exception ex) {
            logger.error("Exception::", ex);
        }
        return list;
    }

    public List<OrderStatus> getOrderStatusList() {
        List<OrderStatus> list = new ArrayList<>();
        try {
            list = (List<OrderStatus>) orderDao.getList(new OrderStatus());
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public List<Pharmacy> getPharmacyList(String currentUserDbName, Integer currentUserId) {
        List<Pharmacy> list = new ArrayList<>();
        try {
            if (currentUserDbName.equalsIgnoreCase("viewOrder")) {
                list = orderDao.getPharmacys(currentUserId);
            } else {
                list = orderDao.getPharmacysList();
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public Boolean saveOrderDetail(Order order, Integer currentUserId) {
        boolean result = false;
        try {
            Order oldOrder = (Order) orderDao.findRecordById(new Order(), order.getId());
            oldOrder.setUpdatedBy(currentUserId);
            oldOrder.setUpdatedOn(new Date());
            if (order.getOrderTrackingNo() != null) {
                oldOrder.setOrderTrackingNo(order.getOrderTrackingNo());
            }
            if (order.getOrderStatus() != null) {
                oldOrder.setOrderStatus(order.getOrderStatus());
            }
            if (order.getOrderCarriers() != null) {
                oldOrder.setOrderCarriers(order.getOrderCarriers());
            } else {
                oldOrder.setOrderCarriers(null);
            }
            if (order.getOrderHistory().size() > 0) {
                for (OrderHistory orderHistory : order.getOrderHistory()) {
                    if (orderHistory.getComments() != null && !orderHistory.getComments().isEmpty()) {
                        oldOrder.setOrderHistory(order.getOrderHistory());
                        orderHistory.setOrder(oldOrder);
                        orderHistory.setCreatedBy(currentUserId);
                        orderHistory.setCreatedOn(new Date());
                        orderHistory.setUpdatedBy(currentUserId);
                        orderHistory.setUpdatedOn(new Date());
                        orderHistory.setOrderStatus(oldOrder.getOrderStatus());
                    }
                }
            } else {
                List<OrderHistory> historys = new ArrayList<>();
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrder(oldOrder);
                orderHistory.setCreatedBy(currentUserId);
                orderHistory.setCreatedOn(new Date());
                orderHistory.setUpdatedBy(currentUserId);
                orderHistory.setUpdatedOn(new Date());
                orderHistory.setOrderStatus(oldOrder.getOrderStatus());
                historys.add(orderHistory);
                oldOrder.setOrderHistory(historys);

            }
            orderDao.saveOrUpdate(oldOrder);
            result = true;
        } catch (Exception e) {
            logger.error("Exception:: saveOrderDetail", e);
        }
        return result;
    }

    public boolean isTrackingNoDuplicate(String trackingNo, String orderId) {
        boolean isDuplicate = false;
        try {
            isDuplicate = orderDao.checkTrackingNo(trackingNo, orderId);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return isDuplicate;
    }

    public String getOrderHistorysById(Integer orderId, Integer orderStatusId) {
        String json = "";
        List<OrderHistory> orderHistorys = new ArrayList<>();
        try {
            List<OrderHistory> orderHistoryList = orderDao.getOrderHistorysById(orderId, orderStatusId);
            for (OrderHistory history : orderHistoryList) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setComments(history.getComments());
                orderHistory.setCreatedOn(history.getCreatedOn());
                orderHistorys.add(orderHistory);
            }
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(orderHistorys);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return json;
    }

    public List<RedemptionIngredient> getRedemptionIngredients(String transactionNo) {
        List<RedemptionIngredient> list = new ArrayList<>();
        try {
            int count = 1;
            List<RedemptionIngredient> ingredients = orderDao.getRedemptionIngredients(transactionNo);
            for (RedemptionIngredient ingredient : ingredients) {
                RedemptionIngredient redemptionIngredient = new RedemptionIngredient();
                redemptionIngredient.setName(ingredient.getName());
                redemptionIngredient.setNdc(ingredient.getNdc());
                redemptionIngredient.setStrength(ingredient.getStrength());
                redemptionIngredient.setType(ingredient.getType());
                redemptionIngredient.setSrno(count);
                boolean isNDCMatch = orderDao.getDrugByNdcNo(ingredient.getNdc());
                if (isNDCMatch) {
                    redemptionIngredient.setNdcMatch(Boolean.TRUE);
                } else {
                    redemptionIngredient.setNdcMatch(Boolean.FALSE);
                }
                list.add(redemptionIngredient);
                count++;
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public List<Order> getAllPharmacyOrder() {
        List<Order> list = new ArrayList<>();
        try {
            List<Order> newlist = orderDao.getALLPharmacyOrder();
            for (Order order : newlist) {
                order.setPatientName(order.getFirstName() + " " + order.getLastName());
                DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
                order.setDailyRedemption(dailyRedemption);

                if (dailyRedemption != null) {
                    order.setRxNo(dailyRedemption.getPrescriptionNumber());
                }
                list.add(order);
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }

        return list;
    }

    public Order getOrderById(String id) {
        Order order = new Order();
        try {
            order = orderDao.getOrdersById(id);
            if (order != null) {
                order.setPrefName(order.getDeliveryPreference() != null ? AppUtil.getSafeStr(order.getDeliveryPreference().getName(), "") : "");
            }
        } catch (Exception e) {
            logger.error("Exception:: getOrderById", e);
        }
        return order;
    }

    public Order getOrderById(String id, Date olVersion) {
        Order order = null;
        try {
            order = orderDao.getOrdersById(id, olVersion);
            if (order != null) {
               
            } else {
                order = orderDao.getOrdersById(id);
                if (order != null) {
                    this.initializeOrderChildTables(order);
                }
            }
        } catch (Exception e) {
            logger.error("Exception:: getOrderById", e);
        }
        return order;
    }

    private void initializeOrderChildTables(Order order) throws HibernateException {
        Hibernate.initialize(order.getPatientProfile());
        order.setPrefName(order.getDeliveryPreference() != null ? AppUtil.getSafeStr(order.getDeliveryPreference().getName(), "") : "");
    }

    public DrugDetail getDrugDetailByOrder(String id) {

        Order order = new Order();
        try {
            order = orderDao.getOrdersById(id);
            DrugDetail tmp = order.getDrugDetail();
            Long drugDetailId = tmp.getDrugDetailId();
            DrugDetail detail = (DrugDetail) this.orderDao.findRecordById(new DrugDetail(), drugDetailId);
            return detail;

        } catch (Exception e) {
            logger.error("Exception:: getOrderById", e);
        }
        return null;
    }

    public List<Order> getOrderByTransactionNumber(String transactionNumber) {
        List<Order> order = new ArrayList<>();
        try {
            order = orderDao.getOrderByTransactionNumber(transactionNumber);
        } catch (Exception e) {
            logger.error("Exception:: getOrderByTransactionNumber", e);
        }
        return order;
    }

    public List<DrugBrand> getRedemptionIngredients(int index) {
        List<DrugBrand> list = new ArrayList<>();
        try {
            list = orderDao.getCompoundList(index);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public List<DrugBrand> getIngredientsList(String searchValue) {
        List<DrugBrand> list = new ArrayList<>();
        try {
            list = orderDao.getDrugBrands(searchValue);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public Order getOrderDetailById(String id) {
        Order order = new Order();
        try {
            order = orderDao.getOrdersById(id);
            //order.setItemsOrder(redemptionDAO.getRedemptionIngredientByTransactionNumber(order.getTransactionNo()).size());
            DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
            order.setDailyRedemption(dailyRedemption);
            if (dailyRedemption != null) {
                order.setRxNo(dailyRedemption.getPrescriptionNumber());
            }
            order.setCommunicationId(dailyRedemption.getCommunicationId());
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return order;
    }

    public List<DrugBrand> getRedemptionIngredients() {
        List<DrugBrand> list = new ArrayList<>();
        try {
            list = (List<DrugBrand>) orderDao.getList(new DrugBrand());
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public List<Order> getAllOrderByPharmacyId(Integer currentUserId) {
        List<Order> list = new ArrayList<>();
        try {
            List<Order> newlist = orderDao.getAllOrderByPharmacyId(currentUserId);
            for (Order order : newlist) {
                order.setPatientName(order.getFirstName() + " " + order.getLastName());
                DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
                order.setDailyRedemption(dailyRedemption);
                if (order.getUpdatedBy() != 0) {
                    PharmacyUser pharmacyUser = (PharmacyUser) orderDao.findRecordById(new PharmacyUser(), order.getUpdatedBy());
                    if (pharmacyUser != null && pharmacyUser.getId() != null) {
                        order.setRph(pharmacyUser.getFirstName().substring(0, 1) + "" + pharmacyUser.getLastName().substring(0, 1));
                    }
                }
                if (dailyRedemption != null) {
                    order.setRxNo(dailyRedemption.getPrescriptionNumber());
                }

                // Set order quality
                order.setQuality(getOrderQuality(order));

                if (order.getOrderStatus() != null && !order.getOrderHistory().isEmpty() && !"Un-Assigned".equalsIgnoreCase(order.getOrderStatus().getName())) {
                    OrderHistory orderHistory = order.getOrderHistory().get(order.getOrderHistory().size() - 1);
                    if (orderHistory.getCreatedBy() != 0) {
                        PharmacyUser pharmacyUser1 = orderDao.getPharmacyUserById(orderHistory.getCreatedBy());
                        if (pharmacyUser1 != null && pharmacyUser1.getId() > 0) {
                            order.setRph(pharmacyUser1.getFirstName().charAt(0) + "" + pharmacyUser1.getLastName().charAt(0));
                            order.setComments(orderHistory.getComments());
                        }
                    }
                }

                //Set AWP
                List<RedemptionIngredient> ingredientList = orderDao.getRedemptionIngredients(order.getTransactionNo());
                double awp = 0;
                for (RedemptionIngredient redemptionIngredient : ingredientList) {
                    Drug drug = orderDao.getDrugBrandByNdc(redemptionIngredient.getNdc());
                    if (drug != null) {
                        awp += drug.getDrugAwpPrice();
                    }
                }
                order.setGenRxAWP(awp);

                //order.setItemsOrder(redemptionDAO.getRedemptionIngredientByTransactionNumber(order.getTransactionNo()).size());
                list.add(order);
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }

        return list;
    }

    public List<Order> searchOrders(Order orderParam, Integer pharmacyId) {
        List<Order> list = new ArrayList<>();
        try {
            String orderNo = orderParam.getOrderNo();
            String daysBack = orderParam.getDaysBack();
            String fromDate = orderParam.getFromDate();
            String toDate = orderParam.getToDate();
            List<Order> newlist = orderDao.searchOrders(orderNo, daysBack, fromDate, toDate, pharmacyId);
            for (Order order : newlist) {
                order.setPatientName(order.getFirstName() + " " + order.getLastName());
                DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
                order.setDailyRedemption(dailyRedemption);

                if (dailyRedemption != null) {
                    order.setRxNo(dailyRedemption.getPrescriptionNumber());
                }

                // Set order quality
                order.setQuality(getOrderQuality(order));

                if (order.getOrderStatus() != null && !order.getOrderHistory().isEmpty() && !"Un-Assigned".equalsIgnoreCase(order.getOrderStatus().getName())) {
                    OrderHistory orderHistory = order.getOrderHistory().get(order.getOrderHistory().size() - 1);
                    PharmacyUser pharmacyUser = orderDao.getPharmacyUserById(orderHistory.getCreatedBy());
                    if (pharmacyUser != null && pharmacyUser.getId() > 0) {
                        order.setRph(pharmacyUser.getFirstName().charAt(0) + "" + pharmacyUser.getLastName().charAt(0));
                        order.setComments(orderHistory.getComments());
                    }
                }

                //Set AWP
                List<RedemptionIngredient> ingredientList = orderDao.getRedemptionIngredients(order.getTransactionNo());
                double awp = 0;
                for (RedemptionIngredient redemptionIngredient : ingredientList) {
                    Drug drug = orderDao.getDrugBrandByNdc(redemptionIngredient.getNdc());
                    if (drug != null) {
                        awp += drug.getDrugAwpPrice();
                    }
                }
                order.setGenRxAWP(awp);

                //order.setItemsOrder(redemptionDAO.getRedemptionIngredientByTransactionNumber(order.getTransactionNo()).size());
                list.add(order);
            }
        } catch (Exception e) {
            logger.error("OrderService --> searchOrders: ", e);
        }

        return list;
    }

    public List<Order> getRecentOrder(Integer currentUserId) {
        List<Order> list = new ArrayList<>();
        try {
            List<Order> newlist = orderDao.getRecentOrderByPharmacyId();
            for (Order order : newlist) {
                DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
                order.setDailyRedemption(dailyRedemption);
                if (dailyRedemption != null) {
                    order.setRxNo(dailyRedemption.getPrescriptionNumber());
                }
                // order.setItemsOrder(redemptionDAO.getRedemptionIngredientByTransactionNumber(order.getTransactionNo()).size());

                //Set AWP
                List<RedemptionIngredient> ingredientList = orderDao.getRedemptionIngredients(order.getTransactionNo());
                double awp = 0;
                for (RedemptionIngredient redemptionIngredient : ingredientList) {
                    Drug drug = orderDao.getDrugBrandByNdc(redemptionIngredient.getNdc());
                    if (drug != null) {
                        awp += drug.getDrugAwpPrice();
                    }
                }
                order.setGenRxAWP(awp);
                list.add(order);
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public List<Order> getPrescriptionTrackingOrder(Integer currentUserId) {
        List<Order> list = new ArrayList<>();
        try {
            List<Order> newlist = orderDao.getPrescriptionTrackingByPharmacyId(currentUserId);
            for (Order order : newlist) {
                order.setPatientName(order.getFirstName() + " " + order.getLastName());
                DailyRedemption dailyRedemption = orderDao.getDailyRedemptionByTransactionNo(order.getTransactionNo());
                order.setDailyRedemption(dailyRedemption);

                if (dailyRedemption != null) {
                    order.setRxNo(dailyRedemption.getPrescriptionNumber());
                }

                if (order.getOrderStatus() != null && !order.getOrderHistory().isEmpty() && !"Un-Assigned".equalsIgnoreCase(order.getOrderStatus().getName())) {
                    OrderHistory orderHistory = order.getOrderHistory().get(order.getOrderHistory().size() - 1);
                    if (orderHistory.getCreatedBy() != 0) {
                        PharmacyUser pharmacyUser = orderDao.getPharmacyUserById(orderHistory.getCreatedBy());
                        if (pharmacyUser != null && pharmacyUser.getId() > 0) {
                            order.setRph(pharmacyUser.getFirstName().charAt(0) + "" + pharmacyUser.getLastName().charAt(0));
                            order.setComments(orderHistory.getComments());
                        }
                    }
                }
                list.add(order);
            }
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public List<OrderCarriers> getOrderCarriersList() {
        List<OrderCarriers> list = new ArrayList<>();
        try {
            list = orderDao.getOrderCarriersList();
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return list;
    }

    public String getOrderCommentsHandler(String orderId) {
        String json = "";
        try {
            List<OrderHistory> newlist = new ArrayList<>();
            List<OrderHistory> dblist = orderDao.getOrderHistoryList(orderId);
            for (OrderHistory orderHistory : dblist) {
                OrderHistory history = new OrderHistory();
                if (orderHistory.getComments() == null) {
                    history.setComments("No comments");
                } else {
                    history.setComments(orderHistory.getComments());
                }
                if (orderHistory.getOrderStatus().getId() == 4) {
                    if (orderHistory.getOrder().getOrderCarriers() != null) {
                        history.setStatus(orderHistory.getOrderStatus().getName() + ", " + orderHistory.getOrder().getOrderCarriers().getName() + " (" + orderHistory.getOrder().getTransactionNo() + ")");
                    } else {
                        history.setStatus(orderHistory.getOrderStatus().getName());
                    }
                } else {
                    history.setStatus(orderHistory.getOrderStatus().getName());
                }
                history.setCreatedOn(orderHistory.getCreatedOn());
                newlist.add(history);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(newlist);
        } catch (Exception e) {
            logger.error("Exception::", e);
        }
        return json;
    }

    public String getOrderCareerByOrderId(String orderId) {
        OrderCarriers orderCarrier = null;
        String json = "";
        try {
            orderCarrier = orderDao.getOrderCarriersByOrderId(orderId);
            if (orderCarrier != null) {
                OrderCarriers dummyCarrier = new OrderCarriers();
                dummyCarrier.setName(orderCarrier.getName());
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writeValueAsString(dummyCarrier);
            }
        } catch (Exception e) {
            logger.error("Exception: OrderService --> getOrderCareerByOrderId: ", e);
        }
        return json;

    }

    public String getOrderQuality(Order order) throws Exception {
        String result = "";
        try {
            if (order.getDailyRedemption() != null && order.getDailyRedemption().getPlanPharmacyAmount() > 0) {
                Double planPharmacyAmount = order.getDailyRedemption().getPlanPharmacyAmount();
                Double grossPharmacyAmount = order.getDailyRedemption().getPharmacyGrossAmount();
                long quality = Math.round((planPharmacyAmount / grossPharmacyAmount) * 100);
                if (quality >= 51) {
                    result = "+++";
                } else if (quality >= 20) {
                    result = "++";
                } else if (quality >= 0) {
                    result = "+";
                } else {
                    result = "-";
                }
            }
        } catch (Exception e) {
            logger.error("Exception: OrderService --> getOrderQuality: ", e);
        }
        return result;
    }

    public Order getPatientProfileDetailByTransactionNo(String transactionNumber) {
        Order order = new Order();
        try {
            order = orderDao.getPatientProfileDetailByTransactionNo(transactionNumber);
        } catch (Exception e) {
            logger.error("Exception: OrderService --> getPatientProfileDetailByTransactionNo: ", e);
        }
        return order;
    }

    public boolean autoDeletOrder(Logger successed, Logger failed) throws Exception {
        boolean autoDeleteFlag = false;
        OrderStatus status = new OrderStatus();
        try {
//            DateUtil dateutil = new DateUtil();
            String autoDeletionDays = PropertiesUtil.getProperty("AUTO_DELETION_DAYS");
            if(CommonUtil.isNullOrEmpty(autoDeletionDays)){
                failed.info("AUTO_DELETION_DAYS not exist in the property file");
                return autoDeleteFlag;
            }
            Date date = DateUtil.addDaysToDate(new Date(), AppUtil.getSafeInt(autoDeletionDays, -2));
            List<Order> orders = orderDao.getpopulateAutoDeleteOrder(1, date, 16);
            int countOrders = orders.size();

            if (CommonUtil.isNullOrEmpty(orders)) {
                failed.info("There are no orders availabe which need to be deleted.");
                return autoDeleteFlag;
            }

            for (Order ord : orders) {

                status.setId(Constants.ORDER_STATUS.CANCEL_ORDER_ID);
                ord.setOrderStatus(status);
                orderDao.saveOrUpdate(ord);

                successed.info(ord.getId());

            }
            successed.info("These  (" + countOrders + ") orders has been updated with AutoDeletion stats as result of autodeletionjob");

        } catch (Exception e) {
            failed.error("Exception: OrderService --> AutoDeletOrder: ", e);
        }

        return autoDeleteFlag;

    }

    public int getAutoDeletionFlag(String orderId) {

        Order order = getOrderById(orderId);
        int val = 0;
        try {

            if (order != null) {
                val = order.getAutoDeletionFlag();
            }

        } catch (Exception e) {
            logger.error("Exception -> getOrderAutoDeltionFlag ", e);
        }
        return val;
    }

    public BasicStatisticsReportDTO getBasicStatisticsReport(BaseDTO baseDTO) {
        BasicStatisticsReportDTO bsrdto = new BasicStatisticsReportDTO();
        try {
            Long totalOrders = orderDao.getBasicStatisticsReport(baseDTO, false, null, null);
            //TotalPatientTypeCount
            getTotalPatientTypeCount(baseDTO, totalOrders, bsrdto);
            //TotalGenderCount
            getTotalGenderCount(baseDTO, totalOrders, bsrdto);

            //age by Rx recipient
            populateAgeByRxRecipient(baseDTO, bsrdto, totalOrders);

            Long totalFillShipOrders = orderDao.getInsuranceCount(baseDTO, null);
            //InsuranceCount
            getInsuranceCount(baseDTO, totalFillShipOrders, bsrdto);
            //Patient Out of Pocket
            BasicStatisticsReportDTO patientOutOfPocketCount = orderDao.getPatientOutOfPocketCount(baseDTO, totalFillShipOrders);
            bsrdto.setPtOutofPocketCount(patientOutOfPocketCount.getPtOutofPocketCount());
            bsrdto.setPtOutofPocketNumber(patientOutOfPocketCount.getPtOutofPocketNumber());
            bsrdto.setPtOutofPocket1To25Count(patientOutOfPocketCount.getPtOutofPocket1To25Count());
            bsrdto.setPtOutofPocket1To25Number(patientOutOfPocketCount.getPtOutofPocket1To25Number());
            bsrdto.setPtOutofPocket26To75Count(patientOutOfPocketCount.getPtOutofPocket26To75Count());
            bsrdto.setPtOutofPocket26To75Number(patientOutOfPocketCount.getPtOutofPocket26To75Number());
            bsrdto.setPtOutofPocket76GreaterCount(patientOutOfPocketCount.getPtOutofPocket76GreaterCount());
            bsrdto.setPtOutofPocket76GreaterNumber(patientOutOfPocketCount.getPtOutofPocket76GreaterNumber());
            bsrdto.setTotalPtOutOfPocketCount(patientOutOfPocketCount.getTotalPtOutOfPocketCount());
            bsrdto.setTotalPtOutofPocketNumber(patientOutOfPocketCount.getPtOutofPocketNumber() + patientOutOfPocketCount.getPtOutofPocket1To25Number() + patientOutOfPocketCount.getPtOutofPocket26To75Number()
                    + patientOutOfPocketCount.getPtOutofPocket76GreaterNumber());
            //Rx Mix
            getTotalRxMixCount(baseDTO, totalFillShipOrders, bsrdto);
            //Rx Fulfillment
            getTotalRxFulfillmentCount(baseDTO, totalFillShipOrders, bsrdto);

        } catch (Exception e) {
            logger.error("Exception:: getBasicStatisticsReport ", e);
        }
        return bsrdto;
    }

    private void getTotalRxMixCount(BaseDTO baseDTO, Long totalFillShipOrders, BasicStatisticsReportDTO bsrdto) throws Exception {
        //Rx Mix
        BigInteger brandCount = orderDao.getRxMixCount(baseDTO, "BRAND");
        bsrdto.setBrandNumber(brandCount.longValue());
        Double brand_Count = (100D * brandCount.longValue() / totalFillShipOrders);
        bsrdto.setBrandCount(CommonUtil.getDecimalFormat(brand_Count));

        BigInteger genericCount = orderDao.getRxMixCount(baseDTO, "GENERIC");
        bsrdto.setGenericNumber(genericCount.longValue());
        Double generic_Count = (100D * genericCount.longValue() / totalFillShipOrders);
        bsrdto.setGenericCount(CommonUtil.getDecimalFormat(generic_Count));

        Double totalRxMixCount = brand_Count + generic_Count;
        bsrdto.setTotalRxMixCount(CommonUtil.getDecimalFormat(totalRxMixCount));
        bsrdto.setTotalRxMixNumber(brandCount.longValue() + genericCount.longValue());
    }

    private void getTotalRxFulfillmentCount(BaseDTO baseDTO, Long totalFillShipOrders, BasicStatisticsReportDTO bsrdto) throws Exception {
        //Rx Fulfillment
        BigInteger sameDayCount = orderDao.getRxFulfillmentCount(baseDTO, 1);
        bsrdto.setSameDayNumber(sameDayCount.longValue());
        Double sameDay_Count = (100D * sameDayCount.longValue() / totalFillShipOrders);
        bsrdto.setSameDayCount(CommonUtil.getDecimalFormat(sameDay_Count));

        BigInteger nextDayCount = orderDao.getRxFulfillmentCount(baseDTO, 2);
        bsrdto.setNextDayNumber(nextDayCount.longValue());
        Double next_Day_Count = (100D * nextDayCount.longValue() / totalFillShipOrders);
        bsrdto.setNextDayCount(CommonUtil.getDecimalFormat(next_Day_Count));

        BigInteger day2ndCount = orderDao.getRxFulfillmentCount(baseDTO, 3);
        bsrdto.setDay2ndNumber(day2ndCount.longValue());
        Double day2nd_Count = (100D * day2ndCount.longValue() / totalFillShipOrders);
        bsrdto.setDay2ndCount(CommonUtil.getDecimalFormat(day2nd_Count));

        BigInteger pickUpCount = orderDao.getRxFulfillmentCount(baseDTO, 4);
        bsrdto.setPickUpNumber(pickUpCount.longValue());
        Double pick_Up_Count = (100D * pickUpCount.longValue() / totalFillShipOrders);
        bsrdto.setPickUpCount(CommonUtil.getDecimalFormat(pick_Up_Count));

        Double totalRxFullfillmentCount = sameDay_Count + next_Day_Count + day2nd_Count + pick_Up_Count;
        bsrdto.setTotalRxFullfillmentCount(CommonUtil.getDecimalFormat(totalRxFullfillmentCount));

        bsrdto.setTotalRxFulfillmentNumber(sameDayCount.longValue() + nextDayCount.longValue() + day2ndCount.longValue() + pickUpCount.longValue());
    }

    private void getInsuranceCount(BaseDTO baseDTO, Long totalFillShipOrders, BasicStatisticsReportDTO bsrdto) throws Exception {
        //insurance
        Long commercialInsuranceCount = orderDao.getInsuranceCount(baseDTO, "Commercial");
        bsrdto.setCommercialInusranceNumber(commercialInsuranceCount);
        Double commercial_Insurance_Count = (100D * commercialInsuranceCount / totalFillShipOrders);
        bsrdto.setCommercialInsuranceCount(CommonUtil.getDecimalFormat(commercial_Insurance_Count));

        Long selfPayCount = orderDao.getInsuranceCount(baseDTO, "SELF PAY");
        bsrdto.setSelfPayNumber(selfPayCount);
        Double self_Pay_Count = (100D * selfPayCount / totalFillShipOrders);
        bsrdto.setSelfPayCount(CommonUtil.getDecimalFormat(self_Pay_Count));

        Long publicMedicareCount = orderDao.getInsuranceCount(baseDTO, "Public");
        bsrdto.setPublicMedicareNumber(publicMedicareCount);
        Double public_Medicare_Count = (100D * publicMedicareCount / totalFillShipOrders);
        bsrdto.setPublicMedicareCount(CommonUtil.getDecimalFormat(public_Medicare_Count));

        Double totalInsuranceCount = commercial_Insurance_Count + self_Pay_Count + public_Medicare_Count;
        bsrdto.setTotalInsuranceCount(CommonUtil.getDecimalFormat(totalInsuranceCount));
        bsrdto.setTotalInsuranceNumber(commercialInsuranceCount + selfPayCount + publicMedicareCount);
    }

    private void getTotalGenderCount(BaseDTO baseDTO, Long totalOrders, BasicStatisticsReportDTO bsrdto) throws Exception {
        //totalMGenderCount
        Long totalMGenderCount = orderDao.getBasicStatisticsReport(baseDTO, null, null, "M");
        Long total_MGender_Count = orderDao.getTotalAccountHolderGCount(baseDTO, "M");
        Long totalMCount = totalMGenderCount + total_MGender_Count;
        bsrdto.setTotalMaleGenderNumber(totalMCount);
        Double totalMaleCount = (100D * totalMCount / totalOrders);
        bsrdto.setTotalMaleGender(CommonUtil.getDecimalFormat(totalMaleCount));
        //totalFGenderCount
        Long totalFGenderCount = orderDao.getBasicStatisticsReport(baseDTO, null, null, "F");
        Long total_FGender_Count = orderDao.getTotalAccountHolderGCount(baseDTO, "F");
        Long totalFCount = totalFGenderCount + total_FGender_Count;
        Double totalFemaleCount = (100D * totalFCount / totalOrders);
        bsrdto.setTotalFemaleGender(CommonUtil.getDecimalFormat(totalFemaleCount));
        bsrdto.setTotalFemaleGenderNumber(totalFCount);
        Double totalGenderCount = totalMaleCount + totalFemaleCount;
        bsrdto.setTotalGenderCount(CommonUtil.getDecimalFormat(totalGenderCount));
        bsrdto.setTotalGenderNumber(totalMCount + totalFCount);
    }

    private void getTotalPatientTypeCount(BaseDTO baseDTO, Long totalOrders, BasicStatisticsReportDTO bsrdto) throws Exception {
        //totalAccountHolder
        Long totalAccountHolder = orderDao.getBasicStatisticsReport(baseDTO, true, null, null);
        Double total_Account_Holder = totalOrders != null && totalOrders > 0 ? (100D * totalAccountHolder / totalOrders) : 0d;
        bsrdto.setAccountHolderCount(CommonUtil.getDecimalFormat(total_Account_Holder));
        bsrdto.setAccountHolderNumber(totalAccountHolder);
        //totalChildDependant
        Long totalChildDependant = orderDao.getBasicStatisticsReport(baseDTO, null, false, null);
        Double total_Child_Dependant = totalOrders != null && totalOrders > 0 ? (100D * totalChildDependant / totalOrders) : 0d;
        bsrdto.setChildDependantCount(CommonUtil.getDecimalFormat(total_Child_Dependant));
        bsrdto.setChildDependentNumber(totalChildDependant);
        //totalAdultDependant
        Long totalAdultDependant = orderDao.getBasicStatisticsReport(baseDTO, null, true, null);
        Double total_Adult_Dependant = totalOrders != null && totalOrders > 0 ? (100D * totalAdultDependant / totalOrders) : 0d;
        bsrdto.setAdultDependantCount(CommonUtil.getDecimalFormat(total_Adult_Dependant));
        bsrdto.setAdultDependentNumber(totalAdultDependant);

        Double totalPatientTypeCount = total_Account_Holder + total_Child_Dependant + total_Adult_Dependant;
        bsrdto.setTotalPatientTypeCount(CommonUtil.getDecimalFormat(totalPatientTypeCount));
        bsrdto.setTotalPatientTypeNumber(totalAccountHolder + totalChildDependant + totalAdultDependant);
    }

    private void populateAgeByRxRecipient(BaseDTO baseDTO, BasicStatisticsReportDTO bsrdto, Long totalOrders) throws Exception {
        BasicStatisticsReportDTO pateintBirthAgeCount = orderDao.getPateintBirthAgeCount(baseDTO);
        BasicStatisticsReportDTO rxPateintMembersAgeCount = orderDao.getRxPateintMembersAgeCount(baseDTO);
        bsrdto.setUnderAge17Count(pateintBirthAgeCount.getUnderAge17Count() + rxPateintMembersAgeCount.getUnderAge17Count());
        Double underAge17Count = totalOrders != null && totalOrders > 0 ? (100D * bsrdto.getUnderAge17Count() / totalOrders) : 0d;
        bsrdto.setUnderAge0To17Count(CommonUtil.getDecimalFormat(underAge17Count));

        Long underAge18Count = pateintBirthAgeCount.getUnderAge18Count() + rxPateintMembersAgeCount.getUnderAge18Count();
        Double under_Age18_Count = totalOrders != null && totalOrders > 0 ? (100D * underAge18Count / totalOrders) : 0d;
        bsrdto.setUnderAge18To34Count(CommonUtil.getDecimalFormat(under_Age18_Count));
        bsrdto.setUnderAge18Count(underAge18Count);

        Long underAge35Count = pateintBirthAgeCount.getUnderAge35Count() + rxPateintMembersAgeCount.getUnderAge35Count();
        Double under_Age35_Count = totalOrders != null && totalOrders > 0 ? (100D * underAge35Count / totalOrders) : 0d;
        bsrdto.setUnderAge35Count(underAge35Count);
        bsrdto.setUnderAge35To50Count(CommonUtil.getDecimalFormat(under_Age35_Count));

        Long underAge51Count = pateintBirthAgeCount.getUnderAge51Count() + rxPateintMembersAgeCount.getUnderAge51Count();
        Double under_Age51_Count = totalOrders != null && totalOrders > 0 ? (100D * underAge51Count / totalOrders) : 0d;
        bsrdto.setUnderAge51To64Count(CommonUtil.getDecimalFormat(under_Age51_Count));
        bsrdto.setUnderAge51Count(underAge51Count);

        Long underAge65Count = pateintBirthAgeCount.getUnderAge65Count() + rxPateintMembersAgeCount.getUnderAge65Count();
        Double under_Age65_Count = totalOrders != null && totalOrders > 0 ? (100D * underAge65Count / totalOrders) : 0d;
        bsrdto.setAge65MoreCount(CommonUtil.getDecimalFormat(under_Age65_Count));
        bsrdto.setUnderAge65Count(underAge65Count);
        Double totalAgeByRxRecipientCount = underAge17Count + under_Age18_Count + under_Age35_Count + under_Age51_Count + under_Age65_Count;
        bsrdto.setTotalAgeByRxRecipientCount(CommonUtil.getDecimalFormat(totalAgeByRxRecipientCount));
        bsrdto.setTotalAgeByRxRecipientNumber(bsrdto.getUnderAge17Count() + underAge18Count + underAge35Count + underAge51Count + underAge65Count);
    }

    public String nextOrderId(Integer patientId, String orderId) {
        String id = "0";
        try {
            List<OrderDetailDTO> orderBatchList = orderDao.getOrderBatchsList(patientId, orderId);
            if (!CommonUtil.isNullOrEmpty(orderBatchList)) {
                id = orderBatchList.stream().findFirst().get().getId();
            }
        } catch (Exception e) {
            logger.error("nextOrderBatchId# ", e);
        }
        return id;
    }

    public void reminderPOAExpiry(Logger successed, Logger failed) {
        try {
            Date date = new Date();
            Date aboutToExpiry = DateUtil.addDaysToDate(date, Constants.POA_EXPIRY_DAYS);
            
            CampaignMessages campaignMessages = patientProfileService.getNotificationMsgs("Update Caretaker POA", Constants.PHARMACY_NOTIFICATION);
            if (campaignMessages == null || CommonUtil.isNullOrEmpty(campaignMessages.getMessageId())) {
                failed.info("There are no caretaker msg exist.");
                return;
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            failed.error("Exception# reminderPOAExpiry# " + e);
        }

    }

}
