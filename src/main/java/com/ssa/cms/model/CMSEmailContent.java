package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "CmsEmailContent")
public class CMSEmailContent extends AuditFields implements Serializable {

    private Integer id;
    private String subject;
    private String emailBody;
    private CMSEmailType cMSEmailType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Subject", nullable = false, insertable = true, updatable = true)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "Body", insertable = true, updatable = true)
    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EmailTypeId", nullable = false, insertable = true, updatable = true)
    public CMSEmailType getcMSEmailType() {
        return cMSEmailType;
    }

    public void setcMSEmailType(CMSEmailType cMSEmailType) {
        this.cMSEmailType = cMSEmailType;
    }
}
