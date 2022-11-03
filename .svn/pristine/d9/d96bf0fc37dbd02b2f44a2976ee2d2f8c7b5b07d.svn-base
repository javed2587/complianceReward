package com.ssa.cms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "CmsEmailType")
public class CMSEmailType implements Serializable {

    private Integer id;
    private String title;
    private List<CMSEmailContent> cmsEmailContent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cMSEmailType")
    @OrderBy(clause = "id asc")
    public List<CMSEmailContent> getCmsEmailContent() {
        return cmsEmailContent;
    }

    public void setCmsEmailContent(List<CMSEmailContent> cmsEmailContent) {
        this.cmsEmailContent = cmsEmailContent;
    }

}
