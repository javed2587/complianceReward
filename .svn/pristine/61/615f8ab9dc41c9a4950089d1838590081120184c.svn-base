package com.ssa.cms.dao;

import com.ssa.cms.model.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Repository
@Transactional
public class CampaignDAO extends BaseDAO  implements Serializable {

    private static final Logger logger = Logger.getLogger(CampaignDAO.class);

   

    public void save(Object bean) throws Exception {
        this.getCurrentSession().save(bean);
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

    public Object getIntervalById(Integer id) throws Exception {
        String queryString = "from Intervals interval where interval.intervalId = :id";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("id", id);
        return query.uniqueResult();
    }

    public List<UserBrand> getUserBrandByUserId(int userId) throws Exception {
        String hql = "select usr from UserBrand usr  where  usr.userId = :userId ";
        return (List<UserBrand>) getCurrentSession().createQuery(hql).setParameter("userId", userId).list();
    }

    public List<Campaigns> isCampaignExists(String campaignName, Integer campaignId) throws Exception {
        String hql = "select campaigns from Campaigns campaigns where campaigns.campaignName = :campaignName";
        if (campaignId != null && campaignId > 0) {
            hql = hql + " and campaigns.campaignId != '" + campaignId + "' ";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setString("campaignName", campaignName);
        List<Campaigns> list = query.list();
        return list;
    }

    public List<CampaignTrigger> isTriggerValid(String keyword, Integer campaignId) throws Exception {
        String hql = "select trigger from CampaignTrigger trigger where trigger.id.keyword = :Keyword";

        if (campaignId != null && campaignId > 0) {
            hql = hql + " and trigger.campaigns.campaignId != '" + campaignId + "' ";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setString("Keyword", keyword);
        List<CampaignTrigger> list = query.list();
        return list;
    }

    public List<DrugBrand> getAllDrugBrands(String userId, Integer campaignId) throws Exception {
        List<DrugBrand> list = new ArrayList<>();

        String where = "";
        if (campaignId != null && campaignId != 0) {
            where = "WHERE CampaignId  <> " + campaignId;
        }

        String sql = "Select drugBrand.DrugBrandId, BrandTitle, drugBrand.BrandId, "
                + "drugBrand.GenericName from DrugBrand drugBrand "
                + " Inner join Brand on Brand.BrandId = drugBrand.`BrandId` "
                + "where drugBrand.DrugBrandId not in "
                + "(select distinct chd.DrugBrandId from CampaignsHasDrugBrand chd " + where + ")"
                + "and drugBrand.brandId in (select brandId from UserBrand userBrand where userBrand.userId = " + userId + ") order by drugBrand.GenericName ASC";

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        List<Object[]> queryResult = query.list();

        Object value;
        for (Object[] record : queryResult) {
            DrugBrand db = new DrugBrand();

            value = record[0];
            if (value != null) {
                db.setId(Integer.parseInt(String.valueOf(value)));
            }

//            value = record[1];
//            if (value != null) {
//                db.setBrandName(String.valueOf(value));
//            }
//
//            value = record[2];
//            if (value != null) {
//                db.setBrandId(Integer.parseInt(String.valueOf(value)));
//            }
            value = record[3];
            if (value != null) {
                db.setDrugBrandName(String.valueOf(value));
                //db.setGenericName(String.valueOf(value));
            }

            list.add(db);
        }
        return list;
    }

    public boolean deleteCampaignBasicChildren(Integer id) {
        List<String> children = new ArrayList<>();
        children.add("CampaignTrigger");
        children.add("CampaignsHasDrugBrand");

        for (String tableName : children) {
            String queryString = "DELETE FROM " + tableName + " where CampaignId = :CampaignId";
            Query query;
            if ("CampaignsHasDrugBrand".equals(tableName)) {
                query = getCurrentSession().createSQLQuery(queryString);
            } else {
                query = getCurrentSession().createQuery(queryString);
            }
            query.setInteger("CampaignId", id);
            query.executeUpdate();
        }
        return true;
    }

    public boolean deleteCampaignAdvanceChildren(Integer id, String communicationPath) {
        List<String> children = new ArrayList<>();

        children.add("FolderHasCampaigns");
        children.add("EventHasFolderHasCampaigns");

        for (String tableName : children) {
            String queryString = "DELETE FROM " + tableName + " where CampaignId = :CampaignId and communicationPath = :communicationPath";
            Query query = getCurrentSession().createQuery(queryString);
            query.setInteger("CampaignId", id);
            query.setString("communicationPath", communicationPath);
            query.executeUpdate();
        }

        //delete messages via communication path SMS/MMS/IVR/Email
        children = new ArrayList<>();
        children.add("CampaignMessagesResponse");
        children.add("CampaignMessages");
        children.add("GroupHasFolderHasCampaign");
        for (String tableName : children) {
            String queryString = "DELETE FROM " + tableName + " where CampaignId = :CampaignId";
            if (tableName.equals("GroupHasFolderHasCampaign")) {
                queryString = "DELETE FROM " + tableName + " where CampaignId = :CampaignId and sourceType = :communicationPath";
            }
            Query query = getCurrentSession().createQuery(queryString);
            query.setInteger("CampaignId", id);
            if (tableName.equals("GroupHasFolderHasCampaign")) {
                query.setString("communicationPath", communicationPath);
            }
            query.executeUpdate();
        }
        return true;
    }

    public List<Campaigns> findAllCampaigns(String type) throws Exception {
        String queryString = "select distinct campaigns from Campaigns campaigns "
                + "left join fetch campaigns.shortCodes shortCodes "
                + "where campaigns.campaignType = '" + type + "' order by campaigns.campaignId desc";

        Query query = getCurrentSession().createQuery(queryString);

        List<Campaigns> list = query.list();

        return list;
    }

    public Campaigns getCampaignsById(Integer userId) throws Exception {
        Campaigns campaigns = null;
        String queryString = "select distinct campaigns from Campaigns campaigns "
                + "left join fetch campaigns.industry industry "
                + "left join fetch campaigns.smtpServerInfo smtpServerInfo "
                + "left join fetch campaigns.shortCodes shortCodes "
                + "left join fetch campaigns.survey survey "
                + "where campaigns.campaignId = " + userId;

        List<Campaigns> list = (List<Campaigns>) getCurrentSession().createQuery(queryString).list();
        if (list.size() > 0) {
            campaigns = list.get(0);
        }
        return campaigns;
    }

    public List<RedemptionChannel> getAllRedemptionChannels() throws Exception {
        List<RedemptionChannel> list;
        list = (List<RedemptionChannel>) getCurrentSession().createQuery("from RedemptionChannel redemptionChannel").list();
        return list;
    }

    public List<Industry> getIndustryList() throws Exception {
        List<Industry> list;
        list = (List<Industry>) getCurrentSession().createQuery("from Industry industry order by industry.industryTitle ASC").list();
        return list;
    }

    public List<Intervals> getIntervalList() throws Exception {
        List<Intervals> list;
        list = (List<Intervals>) getCurrentSession().createQuery("select distinct intervals from Intervals intervals "
                + "left join fetch intervals.intervalsType intervalsType order by intervalsType.intervalsTypeId asc, intervals.intervalValue asc").list();
        return list;
    }

    public List<SmtpServerInfo> getSmtpList() throws Exception {
        List<SmtpServerInfo> list;
        list = (List<SmtpServerInfo>) getCurrentSession().createQuery("from SmtpServerInfo smtpServerInfo order by smtpServerInfo.outGoingSmtp ASC").list();
        return list;
    }

    public List<Folder> getAllFolders(String communicationPath) throws Exception {
        Query query = getCurrentSession().createQuery("select distinct folder from Folder folder left join folder.messageTypes messageTypes where messageTypes.type =:communicationPath");
        query.setParameter("communicationPath", communicationPath);
        return query.list();
    }

    public List<Response> getResponseList() throws Exception {
        List<Response> list;
        list = (List<Response>) getCurrentSession().createQuery("from Response response order by response.responseTitle").list();
        return list;
    }

    public List<Event> getAllEvent(Integer id) throws Exception {
        List<Event> list = new ArrayList<>();

        if (id == null) {
            id = 0;
        }
        String sql = "Select event.EventId, event.EventTitle , event.brandIds from Event event "
                + "where event.EventTitle not in "
                + "(select distinct chd.Keyword from CampaignTrigger chd where chd.CampaignId != " + id + ") order by event.EventTitle ASC";
        logger.error("Reminder SQL : " + sql);

        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        List<Object[]> queryResult = query.list();
        logger.error("Result List Size : " + queryResult.size());
        Object value;
        for (Object[] record : queryResult) {
            Event event = new Event();

            value = record[0];
            if (value != null) {
                event.setEventId(Integer.parseInt(String.valueOf(value)));
            }

            value = record[1];
            if (value != null) {
                event.setEventTitle(String.valueOf(value));
            }

            value = record[2];
            if (value != null) {
                event.setBrandIds(String.valueOf(value));
            }

            list.add(event);

        }

        return list;
    }

    public List<ShortCodes> getShortCodeList() throws Exception {
        List<ShortCodes> list;
        list = (List<ShortCodes>) getCurrentSession().createQuery("from ShortCodes shortCodes order by shortCodes.shortCode").list();
        return list;
    }

    public List<String> getBrandShortCodes(List<String> seletedBrands) throws Exception {

        String inClause = StringUtils.collectionToDelimitedString(seletedBrands, ",");
        String hql = "SELECT ShortCodeIds FROM Brand WHERE brandId IN ( SELECT brandId FROM DrugBrand WHERE DrugBrandId IN (" + inClause + " )) AND shortCodeIds != '' AND shortCodeIds IS NOT NULL";

        Query query = getCurrentSession().createSQLQuery(hql);

        List<String> list = query.list();
        return list;
    }

    public SmtpServerInfo findEmailById(Integer emailId) throws Exception {
        String queryString = "from SmtpServerInfo where SmtpId = :SmtpId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setInteger("SmtpId", emailId);
        Object queryResult = query.uniqueResult();
        SmtpServerInfo email = (SmtpServerInfo) queryResult;
        return email;

    }

    public List<String[]> pdrffieldName() throws Exception {
        String[] list;
        String[] colNames;
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

    public List<EventHasFolderHasCampaigns> getFolderEvents(Integer campaignId, Integer folderId) {
        String hql = "select eventHasFolderHasCampaigns from EventHasFolderHasCampaigns eventHasFolderHasCampaigns where  ";

        if (campaignId != null && campaignId > 0) {
            hql = hql + "  eventHasFolderHasCampaigns.campaignId = '" + campaignId + "' ";
        }
        if (folderId != null && folderId > 0) {
            hql = hql + " and eventHasFolderHasCampaigns.folderId = '" + folderId + "' ";
        }
        List<EventHasFolderHasCampaigns> list = (List<EventHasFolderHasCampaigns>) getCurrentSession().createQuery(hql).list();
        return list;

    }

    public Event getEventById(Integer userId) throws Exception {
        Event event = null;
        Object o = this.getCurrentSession().get(Event.class, userId);
        if (o != null) {
            event = (Event) o;
        }
        return event;
    }

    public Response getResponseNameById(int responseId) throws Exception {

        Response validResponse = null;

        String hql = "select validResponse from Response validResponse "
                + "where "
                + "validResponse.responseId = :responseId "
                + "and validResponse.isActive = 'YES' "
                + "and validResponse.isDelete = 'NO' ";

        String[] names = {"responseId"};
        Object[] values = {responseId};

        List<Response> list = (List<Response>) getCurrentSession().createQuery(hql).setParameter("name", values);

        if (list != null && list.size() > 0) {
            validResponse = list.get(0);
        }

        return validResponse;

    }

    public Integer getBrandId(String brandId) throws Exception {

        String hql = "select brandId from DrugBrand drugBrand where drugBrand.drugBrandId = :brandId";

        Query query = getCurrentSession().createQuery(hql);
        query.setString("brandId", brandId);
        Integer bId = (Integer) query.uniqueResult();

        return bId;

    }

    public List<Integer> getAllAdminIds(int excludeParent) throws Exception {
        List<Integer> userIds = (List<Integer>) getCurrentSession().createQuery("SELECT users.userId FROM Users users WHERE users.parentId = 0 and users.userId <> " + excludeParent).list();
        return userIds;
    }

    public List<Survey> getSurveysList() throws Exception {
        return getCurrentSession().createQuery("SELECT distinct survey from Survey survey left join fetch survey.surveyQuestionList surveyQuestion left join fetch surveyQuestion.survey survy where survey.status='Active' and survy IS NOT NULL").list();
    }

    public List<Object[]> getSurveyReportList(Integer campaignId, Integer surveyId) throws Exception {
        String sqlQuery = "SELECT "
                + " SurveyQuestion.Id,"
                + "  SurveyQuestion.title AS Question,"
                + "  COUNT(`ResponseId`) AS Total,"
                + "  SurveyResponseDetail.title AS Response,"
                + "  counts AS grandTotal "
                + " FROM"
                + "  `SurveyUserResponse`"
                + "  INNER JOIN Survey ON Survey.Id=SurveyUserResponse.SurveyId"
                + " INNER JOIN Campaigns ON Campaigns.SurveyId=Survey.Id"
                + "  INNER JOIN `SurveyQuestion` ON SurveyQuestion.`Id` = SurveyUserResponse.QuestionId"
                + "  INNER JOIN `SurveyResponseDetail` "
                + "    ON SurveyResponseDetail.id = SurveyUserResponse.ResponseId "
                + "    LEFT OUTER JOIN "
                + " (SELECT COUNT(QuestionId) counts, QuestionId FROM SurveyUserResponse  GROUP BY QuestionId) AS test ON test.QuestionId = SurveyUserResponse.QuestionId"
                + " WHERE Campaigns.CampaignId=" + campaignId + " AND Survey.Id=" + surveyId + ""
                + " GROUP BY SurveyUserResponse.QuestionId,"
                + "  ResponseId";
        Query query = getCurrentSession().createSQLQuery(sqlQuery);
        return query.list();
    }
}
