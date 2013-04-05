package fr.la.juserright.managedbean;

import java.io.IOException;
import javax.faces.bean.SessionScoped;
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

//Defini les pages � bloquer ainsi que les dossiers
@WebFilter(urlPatterns = { "/panel.jsf", "/param/*", "/entryPROD/*",
		"/entrySAV/*", "/admin/*" })
@SessionScoped
public class LoginPageFilter implements Filter {

	ServiceUserRight moduleGlobal = new ServiceUserRight();

	public LoginPageFilter() {

	}

	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		LoginBean auth = (LoginBean) req.getSession().getAttribute("loginBean");

		if (auth != null && auth.isUserconnected()) {
			// retourne l'addresse compl�te du fichier
			String fullURI = req.getRequestURI();
					//Charge la liste des pages autoris�s
					for (Autorisation r : auth.permList) {
						//Redirige vers error403 si le droit d'acc�s n'est pas valable
						if (r.getRessource() != null
								&& fullURI.equals("/jProductBaseWeb"
										+ r.getRessource().getPath())
								&& r.getPermission().getIdpermission() == 3) {
							HttpServletResponse res = (HttpServletResponse) response;
							res.sendRedirect(req.getContextPath()
									+ "/error403.jsf");
						}
					}

			// L'utilisateur est connecte on le laisse poursuivre sa requete
			chain.doFilter(request, response);
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