package com.cloudfoundry.vmc.common;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.cloudfoundry.vmc.core.model.account.User;

public class Validation {
    
    private static final String INPUT = "INFORMATION";
    private static final String EMAIL_REG = "((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
    
//    private static final String ERROR = "Error!!";

    public static boolean isNull(Object o) {
        return null == o || "".equals(o.toString().trim());
    }
    
    public static boolean regMatch(String reg, String s) {
        return s.matches(reg);
    }
    
    public static void showMsg(Component c, String content, String title, int i) {
    	if (isNull(title)) title = INPUT;
    	if (i < 0) i = 2;
        JOptionPane.showMessageDialog(c, content, title, i);
    }
    
    public static int confirmMsg(Component c, String content, String title, int i) {
        if (isNull(title)) title = INPUT;
        if (i < 0) i = 2;
        return JOptionPane.showConfirmDialog(c, content, title, 0);
    }
    
    
    //--------- login ---------
    public static boolean validLogin(Component c, User u) {
        if (null == u || isNull(u.getTarget())) {
            showMsg(c, "Target should not be null, please input or select.", null, -1);
            return false;
        }
        if (isNull(u.getEmail()) || isNull(u.getPasswd())) {
            showMsg(c, "Email or Password should not be null.", null, -1);
            return false;
        }
        if (!regMatch(EMAIL_REG, u.getEmail())) {
            showMsg(c, "Please input a email address.", null, -1);
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
    	String s = "2011/11/31";
    	boolean b = s.matches("\\d{4}\\/([0][1-9]|[1][0-2])\\/[0-3][0-9]");
    	System.out.println(b);
    }
    
}
