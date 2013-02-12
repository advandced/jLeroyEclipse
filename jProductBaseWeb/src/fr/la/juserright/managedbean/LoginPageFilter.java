package fr.la.juserright.managedbean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.la.jproductbaseweb.beanmanaged.LoginBean;

@WebFilter(urlPatterns = { "/panel.jsf", "/param/*", "/entryPROD/*",
		"/entrySAV/*", "/admin/*" })
public class LoginPageFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		LoginBean auth = (LoginBean) req.getSession().getAttribute("loginBean");

		if (auth != null && auth.isUserconnected()) {
			// retourne l'addresse complète du fichier
			String fullURI = req.getRequestURI();

			if (fullURI.matches("/jProductBaseWeb/param/productConfModel.jsf")) {
				// System.out.println(fullURI);
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(req.getContextPath() + "/error403.jsf");
				chain.doFilter(request, response);
			} else {
				// L'utilisateur est connecte on le laisse poursuivre sa requete
				chain.doFilter(request, response);
			}
		} else {
			// L'utilisateur n'est pas connecte on le redirige a l'accueil
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/index.jsf");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}