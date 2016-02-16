package org.tuberlin.de.server.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.addHeader("Access-Control-Allow-Origin","http://localhost:9000"); //TODO could be an option
        resp.addHeader("Access-Control-Allow-Methods","GET,PUT,DEPLOY");
        resp.addHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type,Accept,Content-Disposition");
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ( request.getMethod().equals("OPTIONS") ) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {}
}
