package com.ssa.cms.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Zubair
 */
@Entity
@Table(name = "CmsDocuments")
public class CMSDocuments extends AuditFields implements Serializable {

    private Integer id;
    private String title;
    private byte[] attachment;
    private String dType;
    private String fileSize;
    private String attachmentName;
    private String contentType;

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

    @Lob
    @Column(name = "Attachment")
    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Column(name = "DocumentType", nullable = false)
    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    @Transient
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Column(name = "AttachmentName", nullable = false)
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Column(name = "ContentType", nullable = false)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
