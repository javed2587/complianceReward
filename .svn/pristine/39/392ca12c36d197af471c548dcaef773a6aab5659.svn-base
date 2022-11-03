package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "CmsPages")
public class CMSPages implements Serializable {

    private Integer id;
    private String title;
    private List<CMSPageContent> cmsPageContent;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cMSPages")
    @OrderBy(clause = "id asc")
    public List<CMSPageContent> getCmsPageContent() {
        return cmsPageContent;
    }

    public void setCmsPageContent(List<CMSPageContent> cmsPageContent) {
        this.cmsPageContent = cmsPageContent;
    }

}
