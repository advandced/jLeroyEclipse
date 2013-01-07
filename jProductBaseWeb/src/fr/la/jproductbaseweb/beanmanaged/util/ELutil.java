package fr.la.jproductbaseweb.beanmanaged.util;

import java.awt.event.ActionEvent;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;

/**
 * 
 * @author Thiago Luiz Rodrigues
 * 
 */
public class ELutil {

	
        /**
         * Mata uma sess�o aberta
         * 
         * @param beanName
         */
        public static void killSessionBean(String beanName) {
                try {
                        FacesContext.getCurrentInstance().getExternalContext()
                                        .getSessionMap().remove(beanName);
                } catch (FacesException e) {
                        e.printStackTrace();
                }
        }

        /**
         * Cria um m�thodo para o atributo action da taglib
         * 
         * @param valueExpression
         * @param valueType
         * @param expectedParamTypes
         * @return
         */
        public static MethodExpression createMethodExpression(
                        String valueExpression, Class<?> valueType,
                        Class<?>[] expectedParamTypes) {

                MethodExpression methodExpression = null;
                try {
                        ExpressionFactory factory = FacesContext.getCurrentInstance()
                                        .getApplication().getExpressionFactory();
                        methodExpression = factory.createMethodExpression(FacesContext
                                        .getCurrentInstance().getELContext(), valueExpression,
                                        valueType, expectedParamTypes);
                } catch (Exception e) {
                        e.printStackTrace();
                }

                return methodExpression;
        }

        /**
         * Cria um m�thodo para o atributo actionListener da taglib
         * 
         * @param valueExpression
         * @param valueType
         * @param expectedParamTypes
         * @return
         */
        public static MethodExpressionActionListener createMethodActionListener(String valueExpression) {
        	Class<?> valueType= ActionEvent.class;
            Class<?>[] expectedParamTypes = new Class[]{ActionEvent.class};
        	
        	 
            MethodExpressionActionListener actionListener = null;
                try {
                	ExpressionFactory _factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
                	 MethodExpression _methodExpression = _factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(),valueExpression,valueType,expectedParamTypes);   
                	
                	actionListener = new MethodExpressionActionListener(
                                        _methodExpression);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return actionListener;
        }

}
