package fr.la.juserright.managedbean;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.la.jproductbaseweb.beanmanaged.LoginBean;

public class LoginPageFilter implements Filter
{
   public void init(FilterConfig filterConfig) throws ServletException
   {

   }

   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,   FilterChain filterChain) throws IOException, ServletException
   {
       //HttpServletRequest request = (HttpServletRequest) servletRequest;
       //HttpServletResponse response = (HttpServletResponse) servletResponse;
       
       LoginBean user = new LoginBean();

       if(user.getUserlogin() != null){ //If user is already authenticated
    	   FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/jProductBaseWeb/panel.jsf");
    	   System.out.println("StarPluc Vous n'êtes pas connecté ");
    	   System.out.println(user.getUserlogin());
    	   System.out.println(user.getLogin());
    	   System.out.println(user.getUserid());
       } else{
           filterChain.doFilter(servletRequest, servletResponse);
    
           System.out.println("StarPluc Vous n'êtes pas connecté "+ user.getUserlogin());
       }
       
       System.out.println("StarPluc !");
   }

   public void destroy()
   {

   }
}