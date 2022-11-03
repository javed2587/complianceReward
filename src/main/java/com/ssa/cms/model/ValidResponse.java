package com.ssa.cms.model;
// Generated Apr 10, 2013 1:18:32 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ValidResponse")
public class ValidResponse extends AuditFields implements java.io.Serializable {

    private Integer vresponseId;
    private Response response;
    @NotBlank(message = "Required")
    private String validWord;
    private String isActive;
    private String isDelete;
    int remove;

    public ValidResponse() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "VResponseId", unique = true, nullable = false)
    public Integer getVresponseId() {
        return this.vresponseId;
    }

    public void setVresponseId(Integer vresponseId) {
        this.vresponseId = vresponseId;
    }

    @Column(name = "ValidWord", nullable = false)
    public String getValidWord() {
        return this.validWord;
    }

    public void setValidWord(String validWord) {
        this.validWord = validWord;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ResponseId", nullable = false, insertable = true, updatable = true)
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Transient
    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

}
