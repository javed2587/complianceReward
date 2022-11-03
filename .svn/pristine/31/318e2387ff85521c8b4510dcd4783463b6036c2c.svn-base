package com.ssa.cms.util;

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
 * @author Zubair
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Override
    public void serialize(Date date, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        String formattedDate = dateFormat.format(date);
        jg.writeString(formattedDate);
    }

}
