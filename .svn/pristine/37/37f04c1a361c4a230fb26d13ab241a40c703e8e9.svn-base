package com.ssa.cms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/PMSGenericEmailAction"})
public class PMSGenericEmailActionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String hostEmail = request.getParameter("host");

        System.out.println("CID : " + cid);

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> ");
        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        html.append("<head>");
        html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        html.append("<title>PMS</title>");
        html.append("</head>");

        html.append("<body alink=\"blue\" vlink=\"blue\" link=\"blue\">");
        html.append("<div id=\"content\" align=\"center\">");
        html.append("<div style=\"width: 894px; margin: 10px 0;\" align=\"left\">");
       // html.append("<img src=\"images/Top.png\" />");
        html.append("</div>");
        html.append("<div style=\"width: 894px; background-color: #EEEEEE; height: 2px; margin-top: 10px; margin-bottom: 20px;\"></div>");


        html.append("<div class=\"terms\" style=\"width: 894px; border: 2px solid #0a639b; -moz-box-shadow: inset 0 0 17px 8px #DDDDDD; -webkit-box-shadow: inset 0 0 17px 8px #DDDDDD; box-shadow: inset 0 0 17px 8px #DDDDDD;\">");
        html.append("<div style=\"height: 425px;overflow:scroll;\">");


        html.append("<br/>");
        html.append("<br/>");
        html.append("<br/>");
        html.append("<br/>");
        html.append("<br/>");
        html.append("<br/>");
        html.append("<h1>");
        html.append("Reply YES to complete enrollment");
        html.append("</h1>");
        html.append("<br/>");
        html.append("<br/>");
         

        html.append("<form action=\"PMSGenericEmailFlow\" name=\"form1\" id=\"form1\" method=\"post\">");
        html.append("<input type=\"hidden\" name = \"from\" value = \"" + cid + "\"/>");
        html.append("<input type=\"hidden\" name = \"host\" value = \"" + hostEmail + "\"/>");
        
        
       
        
        html.append("<table>");
        

        html.append("<tr>");
        
        html.append("<td>");
        html.append("<input name=\"message\" type=\"text\" id=\"message\" class=\"input_large\" maxlength=\"3\" style=\"width: 230px; height: 53px; font-size: 20px; text-align: center; margin-right: 10px; background-color: #f2f2f2; border: 1px solid #0a639b; box-shadow: 0 5px 5px #DDDDDD inset;\" />");
        html.append("</td>");
        
        
        html.append("<td>");
        html.append("<input type=\"submit\" width=\"50px\"  value=\" Complete Enrollment \"/>");
        html.append("</td>");
        
        html.append("</tr>");
        
        html.append("</table>");
        
        html.append("</form>");
        html.append("</div>");
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");



        PrintWriter out = response.getWriter();

        out.write(html.toString());
        out.close();

    }
}
