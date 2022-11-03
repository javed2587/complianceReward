/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ssa.cms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author adeel.usmani
 */
@Entity
@Table(name = "DrugDetailDocuments")
public class DrugDetailDocuments extends AuditFields implements java.io.Serializable
{
    private Long drugDetailDocumentsId;
    private String contentType;
    private String contentTypeImage;
    private byte[] pdfDoc;
    private byte[] image;
    private DrugDetail drugDetail;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DrugDetailDocumentsID", unique = true, nullable = false)
    public Long getDrugDetailDocumentsId() {
        return drugDetailDocumentsId;
    }

    public void setDrugDetailDocumentsId(Long drugDetailDocumentsId) {
        this.drugDetailDocumentsId = drugDetailDocumentsId;
    }

    
    @Column(name = "PdfDoc")
    public byte[] getPdfDoc() {
        return pdfDoc;
    }

    public void setPdfDoc(byte[] pdfDoc) {
        this.pdfDoc = pdfDoc;
    }

    @Column(name = "ImgFile")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "ContentTypePdf")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Column(name="ContentTypeImg")
    public String getContentTypeImage() {
        return contentTypeImage;
    }

    public void setContentTypeImage(String contentTypeImage) {
        this.contentTypeImage = contentTypeImage;
    }
    
    

   
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "DrugNdc", nullable = true, insertable = true, updatable = true)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="DrugNdc")
    public DrugDetail getDrugDetail() {
        return drugDetail;
    }

    public void setDrugDetail(DrugDetail drugDetail) {
        this.drugDetail = drugDetail;
    }
    
    
}
