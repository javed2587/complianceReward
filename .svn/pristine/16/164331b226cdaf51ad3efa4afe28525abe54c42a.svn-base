package com.ssa.cms.delegate;

import com.ssa.cms.dao.CMSDAO;
import com.ssa.cms.model.CMSDocuments;
import com.ssa.cms.model.CMSEmailContent;
import com.ssa.cms.model.CMSEmailType;
import com.ssa.cms.model.CMSPageContent;
import com.ssa.cms.model.CMSPages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zubair
 */
@Service
@Transactional
public class CMSService {

    @Autowired
    private CMSDAO cmsdao;
    private final Log logger = LogFactory.getLog(getClass());

    public List<CMSDocuments> getCMSDocumentList() {
        List<CMSDocuments> list = new ArrayList<>();
        try {
            list = (List<CMSDocuments>) cmsdao.getList(new CMSDocuments());
        } catch (Exception e) {
            logger.error("Exception: CMSService -> getCMSDocumentList", e);
        }
        return list;
    }

    public boolean saveDocuments(CMSDocuments documents, Integer currentUserId) {
        boolean saved;
        try {
            if (documents.getId() == null) {
                documents.setCreatedBy(currentUserId);
                documents.setCreatedOn(new Date());
            }
            documents.setUpdatedBy(currentUserId);
            documents.setUpdatedOn(new Date());
            cmsdao.saveOrUpdate(documents);
            saved = true;
        } catch (Exception e) {
            saved = false;
            logger.error("Exception: CMSService -> saveDocuments", e);
        }
        return saved;
    }

    public CMSDocuments getCMSDocuments(Integer id) {
        CMSDocuments cMSDocuments = new CMSDocuments();
        try {
            cMSDocuments = (CMSDocuments) cmsdao.get(new CMSDocuments(), id);
        } catch (Exception e) {
            logger.error("Exception: CMSService -> getCMSDocuments", e);
        }
        return cMSDocuments;
    }

    public boolean deleteRecord(Integer id) {
        boolean delete = false;
        try {
            delete = cmsdao.deleteRecord(id);
        } catch (Exception e) {
            logger.error("Exception: CMSService -> deleteRecord", e);
        }
        return delete;
    }

    public boolean isCMSDocumentTitleExist(String title, Integer id) {
        boolean isExist = false;
        try {
            isExist = cmsdao.isCMSDocumentTitleExist(title, id);
        } catch (Exception e) {
            logger.error("Exception: CMSService -> isCMSDocumentTitleExist", e);
        }
        return isExist;
    }

    public boolean saveCMSContent(CMSPageContent cMSPageContent, Integer currentUserId) {
        boolean saved;
        try {
            if (cMSPageContent.getId() == null) {
                cMSPageContent.setCreatedBy(currentUserId);
                cMSPageContent.setCreatedOn(new Date());
            } else {
                CMSPageContent cmspc = (CMSPageContent) cmsdao.get(new CMSPageContent(), cMSPageContent.getId());
                cMSPageContent.setCreatedBy(cmspc.getCreatedBy());
                cMSPageContent.setCreatedOn(cmspc.getCreatedOn());
            }
            cMSPageContent.setUpdatedBy(currentUserId);
            cMSPageContent.setUpdatedOn(new Date());
            cmsdao.merge(cMSPageContent);
            saved = Boolean.TRUE;
        } catch (Exception e) {
            saved = Boolean.FALSE;
            logger.error("Exception: CMSService -> saveCMSContent", e);
        }
        return saved;
    }

    public List<CMSPages> getCMSPageses() {
        List<CMSPages> list = new ArrayList<>();
        try {
            list = (List<CMSPages>) cmsdao.getList(new CMSPages());
        } catch (Exception e) {
            logger.error("Exception: CMSService -> getCMSPageses", e);
        }
        return list;
    }

    public String getCMSContentById(Integer id) throws Exception {
        String json = "";
        try {
            CMSPageContent cMSPageContent = cmsdao.getCMSPageContentByPageId(id);
            CMSPageContent cmspc = new CMSPageContent();
            cmspc.setContent(cMSPageContent.getContent());
            cmspc.setId(cMSPageContent.getId());
            cmspc.setCreatedBy(cMSPageContent.getCreatedBy());
            cmspc.setCreatedOn(cMSPageContent.getCreatedOn());
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(cmspc);
        } catch (IOException e) {
            logger.error("Exception: CMSService -> getCMSContentById", e);
        }
        return json;
    }

    public boolean saveCMSEContent(CMSEmailContent cMSEmailContent, Integer currentUserId) {
        boolean saved;
        try {
            if (cMSEmailContent.getId() == null) {
                cMSEmailContent.setCreatedBy(currentUserId);
                cMSEmailContent.setCreatedOn(new Date());
            } else {
                CMSEmailContent cmspc = (CMSEmailContent) cmsdao.get(new CMSEmailContent(), cMSEmailContent.getId());
                cMSEmailContent.setCreatedBy(cmspc.getCreatedBy());
                cMSEmailContent.setCreatedOn(cmspc.getCreatedOn());
            }
            cMSEmailContent.setUpdatedBy(currentUserId);
            cMSEmailContent.setUpdatedOn(new Date());
            cmsdao.merge(cMSEmailContent);
            saved = Boolean.TRUE;
        } catch (Exception e) {
            saved = Boolean.FALSE;
            logger.error("Exception: CMSService -> saveCMSEContent", e);
        }
        return saved;
    }

    public String getCMSEContentById(Integer id) throws Exception {
        String json = "";
        try {
            CMSEmailContent cMSEmailContent = cmsdao.getCMSEmailByPageId(id);
            CMSEmailContent cmsec = new CMSEmailContent();
            cmsec.setId(cMSEmailContent.getId());
            cmsec.setSubject(cMSEmailContent.getSubject());
            cmsec.setEmailBody(cMSEmailContent.getEmailBody());
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(cmsec);
        } catch (IOException e) {
            logger.error("Exception: CMSService -> getCMSEContentById", e);
        }
        return json;
    }

    public List<CMSEmailType> getCMSEmailTypeList() {
        List<CMSEmailType> list = new ArrayList<>();
        try {
            list = (List<CMSEmailType>) cmsdao.getList(new CMSEmailType());
        } catch (Exception e) {
            logger.error("Exception: CMSService -> getCMSEmailTypeList", e);
        }
        return list;
    }

    public CMSPageContent getCMSPageContentById(Integer id) {
        CMSPageContent newPageContent = new CMSPageContent();
        try {
            CMSPageContent pageContent = cmsdao.getCMSPageContentByPageId(id);
            newPageContent.setContent(pageContent.getContent());
        } catch (Exception e) {
            logger.error("Exception: CMSService -> getCMSPageContentById", e);
        }
        return newPageContent;
    }

    public CMSEmailContent getCMSEmailByPageId(Integer id) {
        CMSEmailContent mSEmailContent = new CMSEmailContent();
        try {
            mSEmailContent = cmsdao.getCMSEmailByPageId(id);
        } catch (Exception e) {
            logger.error("Exception# CMSService# getCMSEmailByPageId", e);
        }
        return mSEmailContent;
    }
}
