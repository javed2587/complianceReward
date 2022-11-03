/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssa.cms.util;

import com.ssa.cms.common.Constants;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

/**
 *
 * @author mzubair
 */
@Component
public class JsonDateTimeSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.USA_DATE_TIME_FORMATE);

    @Override
    public void serialize(Date date, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        String formattedDate = dateFormat.format(date);
        jg.writeString(formattedDate);
    }

}
