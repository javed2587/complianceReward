/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import org.jdom.Document;

/**
 *
 * @author adeel.usmani
 */
public class RotatePdf extends PdfPageEventHelper {
    protected PdfNumber rotation = PdfPage.LANDSCAPE;//PORTRAIT;
   
    public void setRotation(PdfNumber rotation) {
        this.rotation = rotation;
    }
    public void onEndPage(PdfWriter writer, Document document) {
        writer.addPageDictEntry(PdfName.ROTATE, rotation);
    }
}