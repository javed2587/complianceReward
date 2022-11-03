/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.service;

import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saber Hussain
 */
@RestController
public class TestWebService {

    /**
     * This method will accept Request XML and response Result XML
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/InstantWS", method = RequestMethod.POST, headers = "content-type=application/xml")
    public @ResponseBody
    Result request(@RequestBody Request request) {
        Request rq = request;
        Result rs = new Result();
        if (rq == null) {
            rs.setStatus("Failed");
            rs.setMessage("Request is Null");
        } else {
            rs.setStatus("Success");
            rs.setMessage("Request Recived");
        }
        return rs;
    }

}

@XmlRootElement
class Result {

    private String status;

    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

@XmlRootElement
class Request {

    private String mainLine;
    private String compoundLine;

    public String getMainLine() {
        return mainLine;
    }

    public void setMainLine(String mainLine) {
        this.mainLine = mainLine;
    }

    public String getCompoundLine() {
        return compoundLine;
    }

    public void setCompoundLine(String compoundLine) {
        this.compoundLine = compoundLine;
    }

}
