package cn.mldn.eusplatform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.mldn.util.action.ActionResourceUtil;

@WebFilter("/pages/*")
public class EmpLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (request.getSession().getAttribute("eid") != null) {
			chain.doFilter(req, resp);
		} else {
			request.getRequestDispatcher(ActionResourceUtil.getPage("login.page")).forward(req, resp);;
		}
	}
}
