<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <script language="javascript">

        </script>
        
        <link type="text/css" rel="stylesheet" href="/rxcdi/javax.faces.resource/theme.css.em?ln=primefaces-rxcdi" />
        <link type="text/css" rel="stylesheet" href="/rxcdi/javax.faces.resource/style.css.em?ln=css" />
        <link type="text/css" rel="stylesheet" href="/rxcdi/javax.faces.resource/primefaces.css.em?ln=primefaces" />
        <script type="text/javascript" src="/rxcdi/javax.faces.resource/jquery/jquery.js.em?ln=primefaces"></script>
        <script type="text/javascript" src="/rxcdi/javax.faces.resource/primefaces.js.em?ln=primefaces"></script>
        
        <title>...................:::Rx PMS Campaign Test:::...................</title>
    </head>
    
    
    <body>
        
        
        <form method="post" action="PMSGenericEmailFlow">
            <input type="hidden" name="userLoginForm" value="userLoginForm" />

            <div class="maincontainer" style="height: 590px !important;">

                <div class="top-logo">
                    <img src="resources/images/UI-Portal.png" />
                </div>



                <div class="top-date">

                    <%

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEEE, MMMMM, dd, yyyy");
                        out.print(simpleDateFormat.format(new Date()));
                    %>


                </div>

                <div class="rightside"><img src="resources/images/paea121.gif" /></div>



                <div class="h-row"><img src="resources/images/h_line.gif" /></div>


                <div class="capital_login">
                    &nbsp;
                </div>

                <div class="h-row"><img src="resources/images/h_line.gif" /></div>  



                <div class="account_desc">
                    Email Flow Test Page
                </div>

                
                
                <table align="center" cellpadding="10" border="0">
                    
                    <tr>
                        <td align="right">
                            <label>Email : </label>
                        </td>
                        <td>
                            <input type="text" id="from" name="from" maxlength="50" style="margin-top: 0px;"/>
                        </td>
                        
                        
                        <td align="right">
                            <label>Message : </label>
                        </td>
                        <td>
                            <input type="text" id="message" name="message" maxlength="10" style="margin-top: 0px;" value="PMS"/>
                        </td>                        
                        
                    </tr>
                    
                    
                    
                    <tr>
                        <td align="right">
                            <label>Source : </label>
                        </td>
                        
                        <td>
                            <select name="source" id="source" style="width: 155px; background: none repeat scroll 0 0 #CBC9C9;    border: 1px solid #000000;    height: 23px;    margin-top: 13px;    padding-left: 3px;    position: relative;    right: 21px;    width: 157px; margin-top: 0px;">
                                <option value="0001">Widget</option>
                                <option value="0002" selected="selected">Phone</option>
                                <option value="0003">IVR</option>
                            </select>
                        </td>
                        
                        
                        <td align="right">
                            <label>Widget Name : </label>
                        </td>
                        
                        <td>
                            <input type="text" id="widgetName" name="widgetName" maxlength="25" style="margin-top: 0px;" value="widget1"/>
                        </td>
                    </tr>
                    
                    
                    <tr>
                        <td>
                            <label>Card Number :</label>
                        </td>
                        
                        <td>
                            <input type="text" id="cardNumber" name="cardNumber" maxlength="11" style="margin-top: 0px;" value="1023456789"/>
                        </td>
                        
                        <td align="right">
                            <label>Sender : </label>
                        </td>
                        <td>
                            <input type="text" id="sender" name="sender" maxlength="10" style="margin-top: 0px;" value="emailService"/>
                        </td>
                    </tr>
                    
                    
                    
                    <tr>
                        <td align="right">
                            <label>IVR Path : </label>
                        </td>
                        
                        <td>
                            <input type="text" id="ivrPath" name="ivrPath" maxlength="10" style="margin-top: 0px;" value="1"/>
                        </td>
                        
                        <td align="right">
                            <label>IVR Id :</label>
                        </td>
                        
                        <td>
                            <input type="text" id="ivrId" name="ivrId" maxlength="11" style="margin-top: 0px;" value="101"/>
                        </td>
                    </tr>
                    
                    
                    <tr>
                         <td align="right">
                            <label>Event Name :</label>
                        </td>
                        
                        <td>
                            <input type="text" id="eventName" name="eventName" style="margin-top: 0px;"/>
                        </td>
                        
                         <td align="right">
                            <label>Campaign SMTP Email : </label>
                        </td>
                        <td>
                            <input type="text" id="host" name="host" maxlength="50" style="margin-top: 0px;" value=""/>
                        </td>
                        
                    </tr>
                    
                    
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    
                    <tr>
                       
                        
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            &nbsp;
                        </td>
                        
                        
                        <td colspan="1" align="center">
                            <input type="submit" value="Send Message"/>
                        </td>
                    </tr>
                    
                    
                    
                    
                </table>


                
                <br/>
                
                <br/>
                <br/>
                



               

                <!-- Footer -->
                <div class="footrcontainr">

                    <div class="h-row" style="clear: both; " title="footer-line1">
                        <img src="resources/images/h_line.gif" />
                    </div>

                    <div style=" margin-left: 28px;clear: both; float: left; position:relative; bottom:14px; ">
                        <a><img src="resources/images/facebook.gif" style="vertical-align: middle;" /></a>
                    </div>


                    <div style="clear: right; float: left; margin-left: 5px; position:relative; bottom:14px;">
                        <a><img src="resources/images/twitter.gif" style="vertical-align: middle;" /></a>
                    </div>


                    <div style="width: 2px;height: 15px; background-color: #a2d283; clear: right; float: left; vertical-align: middle; margin-left: 8px;position:relative; bottom:10px; margin-right: 8px;">
                    </div>


                    <div style="float: left;  position:relative; bottom:14px;">
                        <a style="color:#3784c3; font-weight: bolder; text-decoration: none; font-size: 12px;" href="#">privacy policy</a>
                    </div>


                    <div style="width: 2px;height: 15px; background-color: #a2d283; clear: right; float: left; vertical-align: middle; margin-left: 8px; position:relative; bottom:10px; margin-right: 8px;">
                    </div>


                    <div style="float: left; position:relative; bottom:14px;">		
                        <a style="color:#3784c3; font-weight: bolder; text-decoration: none; font-size: 12px;" href="#">terms of use</a>
                    </div>

                    <div style="width: 275px; float: right;  padding-right: 34px; position:relative; bottom:14px;">
                        <label>
                            2013 EngagedMedia. All rights reserved. 
                        </label>
                    </div>
                    <div class="h-row" style="clear: both;"><img src="resources/images/h_line.gif" />
                    </div>
                </div>
            </div>
        </form></body>
</html>