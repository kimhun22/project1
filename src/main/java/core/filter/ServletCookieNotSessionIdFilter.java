package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletCookieNotSessionIdFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    HttpSession session = req.getSession();
		if(req.getRequestURI().indexOf("/common/fileDownload")==-1&&req.getRequestURI().indexOf("/search")==-1)
    	{
			if (session.isNew()) {
		    	res.sendRedirect(res.encodeRedirectURL(req.getRequestURI()));
		    	return;
		    } else if (session.getAttribute("verified") == null) {
		        // Session has not been verified yet? OK, mark it verified so that we don't need to repeat this.
		    	session.setAttribute("verified", true);
			    if (req.isRequestedSessionIdFromCookie()) {
			    	// Supports cookies? OK, redirect to unencoded URL to get rid of jsessionid in URL.
			    	res.sendRedirect(req.getRequestURI().split(";")[0]);
			    	return;
		    	}
		    }
    	}

	    chain.doFilter(request, response);

	}

	public void destroy() {
		// TODO
	}

}
