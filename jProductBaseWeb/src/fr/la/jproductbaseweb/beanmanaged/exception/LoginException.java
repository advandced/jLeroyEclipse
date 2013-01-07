/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.la.jproductbaseweb.beanmanaged.exception;

/**
 *
 * @author Joff
 */
public class LoginException extends Exception {

    private static final long serialVersionUID = 6495756969315173759L;

    public LoginException() {
        super();
    }

    public LoginException(String s) {
        super(s);
    }
}