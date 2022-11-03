package com.ssa.cms.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Response", uniqueConstraints = @UniqueConstraint(columnNames = "ResponseTitle")
)
public class Response extends AuditFields implements java.io.Serializable {

    private Integer responseId;
    @NotBlank(message = "Required")
    private String responseTitle;
    private String isActive;
    private String isDelete;
    private Set<CampaignMessagesResponse> campaignMessagesResponses = new HashSet<>(0);
    @Valid
    @NotNull
    @Size(min = 1)
    private List<ValidResponse> validResponses;

    public Response() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ResponseId", unique = true, nullable = false)
    public Integer getResponseId() {
        return this.responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    @Column(name = "ResponseTitle", unique = true, nullable = false)
    public String getResponseTitle() {
        return this.responseTitle;
    }

    public void setResponseTitle(String responseTitle) {
        this.responseTitle = responseTitle;
    }

    @Column(name = "IsActive", nullable = false, length = 4)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDelete", nullable = false, length = 4)
    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "response")
    public Set<CampaignMessagesResponse> getCampaignMessagesResponses() {
        return this.campaignMessagesResponses;
    }

    public void setCampaignMessagesResponses(Set<CampaignMessagesResponse> campaignMessagesResponses) {
        this.campaignMessagesResponses = campaignMessagesResponses;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "response")
    @OrderBy(clause = "validWord asc")
    public List<ValidResponse> getValidResponses() {
        return this.validResponses;
    }

    public void setValidResponses(List<ValidResponse> validResponses) {
        this.validResponses = validResponses;
    }

}
