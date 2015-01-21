package br.edu.ifpb.caju.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterLogin
 */
@WebFilter(urlPatterns = { "*.jsf" }, dispatcherTypes = {
		DispatcherType.FORWARD, DispatcherType.REQUEST })
public class FilterLogin implements Filter {

	/**
	 * Default constructor.
	 */
	public FilterLogin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest;
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getServletContext().getContextPath();
		try{
			if (!httpRequest.getRequestURI().endsWith("login.jsf")) {
				if (session != null) {
					if ((boolean) session.getAttribute("logado")) {
						chain.doFilter(request, response);
					} else {
						redirect(response, path + "/login.jsf");
					}
				} else {
					redirect(response, path + "/login.jsf");
				}
			} else {
				chain.doFilter(request, response);
			}
		}catch (NullPointerException e){
			redirect(response, path + "/login.jsf");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private void redirect(ServletResponse response, String url)
			throws IOException {
		((HttpServletResponse) response).sendRedirect(url);
	}

}
