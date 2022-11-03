package com.ssa.cms.service;

import com.ssa.cms.dao.SetupsDAO;
import com.ssa.cms.model.Pharmacy;
import com.ssa.cms.model.State;
import com.ssa.cms.util.EmailSenderUtil;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zubair
 */
@Service
@Transactional
public class PharmacyService {

    @Autowired
    private SetupsDAO setupsDAO;
    private static final Log logger = LogFactory.getLog(PharmacyService.class.getName());

    public List<Pharmacy> getPharmaciesList() {
        List<Pharmacy> list = new ArrayList<>();
        try {
            list = setupsDAO.getPharmaciesList();
        } catch (Exception e) {
            logger.error("Exception: PharmacyService -> pharmaciesList", e);
        }
        return list;
    }

    public boolean savePharmacy(Pharmacy pharmacy, Integer currentUserId) {
        boolean saved = false;
        try {
            if (pharmacy.getId() == null) {
                pharmacy.setCreatedBy(currentUserId);
                pharmacy.setCreatedOn(new Date());
            }
            pharmacy.setLastUpdatedBy(currentUserId);
            pharmacy.setLastUpdatedOn(new Date());
            if (pharmacy.getPhone() != null) {
                pharmacy.setPhone(pharmacy.getPhone().replaceAll("[\\s\\-()]", ""));
            }
            if (pharmacy.getStatus() != null && pharmacy.getStatus().contains("InActive")) {
                pharmacy.setStatus("InActive");
            }
            //this.sendPassword(pharmacy);
            //pharmacy.setPassword(this.md5(pharmacy.getPassword()));
            setupsDAO.saveOrUpdate(pharmacy);
            saved = true;
        } catch (Exception e) {
            logger.error("Exception: PharmacyService -> savePharmacy", e);
        }
        return saved;
    }

    private String md5(String password) throws NoSuchAlgorithmException {
        String result = password;
        if (password != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
            md.update(password.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) { //40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }

    public Pharmacy getPharmacyById(Integer id) {
        Pharmacy pharmacy = new Pharmacy();
        try {
            pharmacy = setupsDAO.getPharmacy(id);
        } catch (Exception e) {
            logger.error("Exception: PharmacyService -> getPharmacyById", e);
        }
        return pharmacy;
    }

    public boolean isNPINoDuplicate(Pharmacy pharmacy) {
        boolean isDuplicate = false;
        try {
            isDuplicate = setupsDAO.checkNPIDuplicate(pharmacy.getNpi(), pharmacy.getId());
        } catch (Exception e) {
            logger.error("Exception: PharmacyService -> isNPINoDuplicate", e);
        }
        return isDuplicate;
    }

    public boolean isPharmacyTitleDuplicate(Pharmacy pharmacy) {
        boolean isDuplicate = false;
        try {
            isDuplicate = setupsDAO.checkDuplicatePharmacyTitle(pharmacy.getTitle(), pharmacy.getId());
        } catch (Exception e) {
            logger.error("Exception: PharmacyService -> isPharmacyTitleDuplicate", e);
        }
        return isDuplicate;
    }

    public List<State> getStatesList() {
        List<State> list = new ArrayList<>();
        try {
            list = setupsDAO.getStatesList();
        } catch (Exception e) {
            logger.error("Exception: PharmacyService -> getStatesList", e);
        }
        return list;
    }

    private void sendPassword(Pharmacy pharmacy) {
        String emailBody = "<table width=\"75%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "<tbody>\n"
                + "<tr valign=\"top\">\n"
                + "<td width=\"100%\" style=\"padding-top:10px;color: #000;  font-family:Verdana, Geneva, sans-serif;font-size:12px;\">"
                + "<b>Dear User,</b><br>\n"
                + "<br>Welcome to API Rx Saving & Support Program!.\n"
                + "<br>\n"
                + "<br>Your account has been created on API Rx Saving & Support Program as on " + new Date() + ".<br>\n"
                + "<br>\n"
                + "<br>Your account will be effective from " + pharmacy.getCreatedOn() + ". <br>\n"
                + "<br>Your Password details are below.<br>"
                + "&nbsp; &nbsp;Password:&nbsp;<b>" + pharmacy.getPassword() + "</b><br>\n"
                + "<br>\n"
                + "<b>Regards,</b><br>\n"
                + "<br>\n"
                + "Team at API Rx Saving & Support Program<br>\n"
                + "<br><i>This email was created automatically. Please do not reply to this address.</i>\n"
                + "\n</td></tr></tbody></table>";
        EmailSenderUtil.send(pharmacy.getEmail(), "API Rx Saving & Support Program | Pharmacy Account Created", emailBody);

    }
}
