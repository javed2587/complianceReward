
package com.ssa.cms.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class PMSLogUtil {

    
    public static String getStackTrace(Exception e){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
