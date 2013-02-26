package fr.la.juserright.managedbean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import fr.la.juserright.metier.Autorisation;
import fr.la.juserright.service.ServiceUserRight;

@WebFilter(urlPatterns = { "/panel.jsf", "/param/*", "/entryPROD/*",
		"/entrySAV/*", "/admin/*" })
public class LoginPageFilter implements Filter {
	
	ServiceUserRight moduleGlobal = new ServiceUserRight();
	
	List<Autorisation> Listpermissions = new ArrayList<Autorisation>();
	boolean urlFound = false;
	
	public LoginPageFilter(){

	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		LoginBean auth = (LoginBean) req.getSession().getAttribute("loginBean");
		
		if (auth != null && auth.isUserconnected()) {
			if (urlFound) {
				HttpServletResponse res = (HttpServletResponse) response;
				res.sendRedirect(req.getContextPath() + "/error403.jsf");
				chain.doFilter(request, response);
			}  else {
				// retourne l'addresse compl�te du fichier
				String fullURI = req.getRequestURI();
				
				List<Autorisation> permList = null;
				try {
					permList = moduleGlobal.getAutorisationByLogin(auth.getUserlogin());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Autorisation r : permList) {	
					if (r.getRessource() != null && fullURI.equals("/jProductBaseWeb"+r.getRessource().getPath()) && r.getPermission().getIdpermission() == 3) {
						HttpServletResponse res = (HttpServletResponse) response;
						res.sendRedirect(req.getContextPath() + "/error403.jsf");
						//System.out.println("True");
					}
					//System.out.println(fullURI);
					//System.out.println("/jProductBaseWeb"+r.getRessource().getPath());
					//System.out.println(fullURI.equals("/jProductBaseWeb"+r.getRessource().getPath()));
				}
				
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