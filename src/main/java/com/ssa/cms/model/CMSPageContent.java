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
@Table(name = "CmsPageContent")
public class CMSPageContent extends AuditFields implements Serializable {

    private Integer id;
    private String content;
    private CMSPages cMSPages;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PageId", nullable = false, insertable = true, updatable = true)
    public CMSPages getcMSPages() {
        return cMSPages;
    }

    public void setcMSPages(CMSPages cMSPages) {
        this.cMSPages = cMSPages;
    }
}
