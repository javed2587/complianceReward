package com.ssa.cms.filter;

import com.ssa.cms.bean.SessionBean;
import com.ssa.cms.util.AppUtil;
import com.ssa.cms.util.PermissionUtil;
import com.ssa.cms.util.PropertiesUtil;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestFilter implements Filter {
    
    private final Log logger = LogFactory.getLog(getClass());
    private FilterConfig filterConfig = null;
    
    public RequestFilter() {
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Begin: RequestFilter -> FilterConfig: Filter Created ###################  @@@@@@@@@@@@@@@@@@@@@@");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        logger.info("Begin: RequestFilter -> doFilter ###################  @@@@@@@@@@@@@@@@@@@@@@ " + PropertiesUtil.getProperty("APP_PATH"));
        logger.info("SERVER_MODE: "+System.getenv("SERVER_MODE"));
        try {
            //load server mode prop
            PropertiesUtil.loadServerModeProp();
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//            httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            httpResponse.setDateHeader("Expires", 0); // Proxies.

            String uri = httpRequest.getRequestURI();
            //String hostName=httpRequest.getRemoteHost();
            String servletName = httpRequest.getServletPath();
            String applicationUrl = PropertiesUtil.getProperty("APP_PATH");//Constants.APP_PATH;
            String notAuthorizedUrl = applicationUrl + "/notauthorized";
            String notFoundUrl = applicationUrl + "/notfound";
            String loginUrl = applicationUrl;
            if (uri.contains("PharmacyPortal")) {
                loginUrl += "/PharmacyPortal/login";
            } else {
//                loginUrl += "/pharmacyqueue/login";
                  loginUrl += "/login";
            }
            logger.info("RequestFilter -> doFilter -> Application URL:" + loginUrl + " IP: " + request.getRemoteAddr() + " Requested URL:" + uri);
            
            HttpSession session = httpRequest.getSession(false);
            request.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("text/html");

            //PMS and PMS for servlets
            if (session != null && session.getAttribute("sessionBeanPortal") == null && (uri.contains("ConsumerPortal") || uri.contains("Pharmacyqueue") || uri.contains("pharmacyqueue"))) {
                if (uri.contains("login")) {
                    filterChain.doFilter(request, response);
                    return;
                }
                else if (uri.contains("lookup") || uri.contains("pharmacyRegistration") || uri.contains("addConsumer") || uri.contains("forgotUserNamePassword")
                        || uri.contains("validateZip") || uri.contains("aboutUs") || uri.contains("contactUs") || uri.contains("faq")) 
                {
                    filterChain.doFilter(request, response);
                    return;
                }
                httpResponse.sendRedirect(PropertiesUtil.getProperty("INVALID_SESSION_URL"));
                
            } else if (uri.contains("InstantWS")
                    || uri.contains("ConsumerPortal")
                    || uri.contains("Pharmacyqueue")
                    || uri.contains("pharmacyqueue")
                    || uri.contains("placeorder")
                    || uri.contains("/order/status/")
                    || uri.contains("DailyRedemption")
                    || uri.contains("InstantRedemption")
                    || uri.contains("EMGS")
                    || uri.contains("PMS")
                    || uri.contains("widget")
                    || uri.contains("login")
                    || uri.contains("admin")
                    || uri.contains("Admin")
                    || uri.contains("logout")
                    || uri.contains("notfound")
                    || uri.contains("notauthorized")
                    || uri.contains("resources")
                    || uri.contains("support")
                    || uri.contains("dailyRedemption")
                    || uri.contains("/order/summary")
                    || uri.contains("redemptionIngredient")
                    || uri.contains("/QRCode")
                    || uri.contains("/survey/takeSurvey")
                    || uri.contains("/submitSurvey")
                    || uri.contains("/pages")
                    || uri.contains("Ws")
                    || uri.contains("/emailVerificationWs")
                    || uri.contains("/ageVerificationWs")
                    || uri.contains("/rxTransfer")
                    || uri.contains("/patient")) {
                if (uri.toLowerCase().endsWith("pharmacyqueue")) {
                    httpResponse.sendRedirect(uri + "/login");
                } else if (uri.toLowerCase().endsWith("pharmacyqueue/")) {
                    httpResponse.sendRedirect(uri + "login");
                } else {
                    
                    authorizedUser(session, uri, httpResponse, notAuthorizedUrl);
                    
                    filterChain.doFilter(request, response);
                }
                return;
            } else if (session == null) {
                httpResponse.sendRedirect(loginUrl);
                return;
                
            } else {
                Object mainObject = session.getAttribute("sessionBean");
                Object pharmacyObject = session.getAttribute("sessionBeanPortal");

                SessionBean mainSessionBean = (SessionBean) mainObject;
                SessionBean pharmacySessionBean = (SessionBean) pharmacyObject;
                
                if (mainSessionBean != null && pharmacySessionBean != null) {
                    authorizedUser(session, uri, httpResponse, notAuthorizedUrl);
                    filterChain.doFilter(request, response);
                    return;
                }
                
                if (pharmacySessionBean == null) {
                    if (uri.contains("PharmacyPortal")) {
                        httpResponse.sendRedirect(loginUrl);
                        return;
                    } else if (mainSessionBean == null) {
                        httpResponse.sendRedirect(loginUrl);
                        return;
                    } else {
                        String userName = mainSessionBean.getUserName();
                       // logger.info("RequestFilter -> doFilter -> User Name: " + userName);
                        if (userName == null || userName.trim().length() == 0) {
                            httpResponse.sendRedirect(loginUrl);
                            return;
                        }
                        
                        if (servletName.equals("/index") || servletName.equals("/") || uri.contains("order") || uri.contains("/dashboard")) {
                            filterChain.doFilter(request, response);
                            return;
                        }

                        //if its super user allow him every things
                        if (!mainSessionBean.getUserNameDB().equalsIgnoreCase("admin")) {
                            //get resource by uri
                            String resource = servletName.split("/")[1];
                            Integer resourceId = getResourceIdByservletName(resource, mainSessionBean);
                            if (servletName.contains("campaign")) {
                                resourceId = getResourceIdByservletName("production", mainSessionBean);
                                
                                if (!mainSessionBean.getViewPermission(resourceId)) {
                                    resourceId = getResourceIdByservletName("demo", mainSessionBean);
                                }
                            }
                            
                            if (resourceId == 0) {
                                httpResponse.sendRedirect(notFoundUrl);
                                return;
                            }

                            //all right page found validate its view permission
                            if (!mainSessionBean.getViewPermission(resourceId) && !mainSessionBean.getManagePermission(resourceId)) {
                                
                                httpResponse.sendRedirect(notAuthorizedUrl);
                                return;
                            } else if (!mainSessionBean.getManagePermission(resourceId)
                                    && (servletName.contains("add") || servletName.contains("delete")
                                    || servletName.contains("edit") || servletName.contains("SMS"))) {
                                httpResponse.sendRedirect(notAuthorizedUrl);
                                return;
                            }
                            filterChain.doFilter(request, response);
                            return;
                        }
                        filterChain.doFilter(request, response);
                    }
                } else {
                    String pharmacyAdminName = pharmacySessionBean.getUserName();
                   // logger.info("RequestFilter -> doFilter -> Admin Name: " + pharmacyAdminName + " URL: " + uri);
                    if (pharmacyAdminName == null || pharmacyAdminName.trim().length() == 0) {
                        httpResponse.sendRedirect(loginUrl);
                        return;
                    }
                    
                    if (uri.contains("PharmacyPortal") || uri.contains("/order/prescription")) {
                        //System.out.println("Autorizing user");
                        authorizedUser(session, uri, httpResponse, notAuthorizedUrl);
                       // System.out.println("GOING TO FILTER");
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        httpResponse.sendRedirect(loginUrl);
                        return;
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.error("IOException: RequestFilter -> doFilter", exception); 
        } catch (ServletException exception) {
            exception.printStackTrace();
            logger.error("ServletException: RequestFilter -> doFilter", exception);
        } catch (Exception exception) {
            logger.error("Exception: RequestFilter -> doFilter", exception);
        }
        
        logger.info("End: RequestFilter -> doFilter");
    }
    
    private Integer getResourceIdByservletName(String servletName, SessionBean sessionBean) {
        Integer resourceId = 0;
        if (sessionBean.getPmap() != null && sessionBean.getPmap().size() > 0) {
            for (Entry<Integer, PermissionUtil> entry : sessionBean.getPmap().entrySet()) {
                PermissionUtil permissionUtil = entry.getValue();
                if (permissionUtil.getServletName() != null
                        && permissionUtil.getServletName().contains(servletName)
                        && (permissionUtil.isManage() || permissionUtil.isView())) {
                    resourceId = entry.getKey();
                    break;
                }
            }
        }
        return resourceId;
    }
    
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
    
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
    @Override
    public void destroy() {
    }
    
    private void authorizedUser(HttpSession session, String uri, HttpServletResponse httpResponse, String notAuthorizedUrl) throws Exception {
        if (uri.contains("login")|| uri.contains("Ws") || uri.contains("logout") || uri.contains("resources") || uri.contains("notauthorized") || uri.contains("successSignin") || uri.contains("queuePatientDetailPage") || uri.contains("rxDetail")) {
            if(uri.contains("notauthorized"))
                httpResponse.sendRedirect(notAuthorizedUrl);
            return;
        }
        if (session != null && (session.getAttribute("sessionBeanPortal") != null || session.getAttribute("sessionBean") != null)) {
            
            SessionBean sb = session.getAttribute("sessionBeanPortal") != null ? (SessionBean) session.getAttribute("sessionBeanPortal") : session.getAttribute("sessionBean") != null ? (SessionBean) session.getAttribute("sessionBean") : null;
            if (sb != null) {
                boolean authorize = false;
                
                for (Map.Entry<Integer, PermissionUtil> entry : sb.getPmap().entrySet()) {
                   // System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                    if(entry.getValue() == null || AppUtil.getSafeStr(entry.getValue().getServletName(),"").length()==0)
                    {
                        authorize = true;
                    }
                    else if (entry.getValue() != null && entry.getValue().getServletName() != null && !entry.getValue().getServletName().equals("") && uri.contains(entry.getValue().getServletName())) {
                        authorize = true;
                    }
                }
                if (!authorize) {
                    httpResponse.sendRedirect(notAuthorizedUrl);
                    throw new Exception("You are not authorized to perform these actions");
                    
                }
                
            }
        }
        
    }
}
