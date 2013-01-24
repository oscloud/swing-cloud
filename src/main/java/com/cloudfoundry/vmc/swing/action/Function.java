package com.cloudfoundry.vmc.swing.action;

import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.cloudfoundry.vmc.CloudConstants;
import com.cloudfoundry.vmc.common.Session;
import com.cloudfoundry.vmc.swing.component.Body;
import com.cloudfoundry.vmc.swing.frame.Main;

public class Function {

    private Function() {

    }

    public static void quit(Main j) {
        int quit = JOptionPane.showConfirmDialog(j, "Are you sure quit?", "QUIT",
                JOptionPane.YES_NO_OPTION);
        if (quit == JOptionPane.YES_OPTION) {
			j.dispose();
			logout(j);
			System.exit(0);
        }
    }
    public static void reboot(JFrame show, Main hide) {
    	int relogin = JOptionPane.showConfirmDialog(hide, "Are you sure reLogin?", "RELOGIN",
                JOptionPane.YES_NO_OPTION);
        if (relogin == JOptionPane.YES_OPTION) {
        	logout(hide);
        	show.setVisible(true);
        	hide.hivoke();
        	Session.getInstance().put(CloudConstants.session.RE_LOGIN, true);
        }
    }
    private static void logout(Main j) {
        j.hivoke();
        Session.getInstance().getClient().logout();
        
    }
    // --------------------------------------------

    
    // --------------------------------------------

    public static void open(JInternalFrame f, Class<?> clazz) {
        if (!(f instanceof JInternalFrame)) {
            f = (JInternalFrame) f;
        }
        if (f.getDesktopPane() == null) {
            Body.getInstance().toDeskTop().add(f);
            f.setVisible(true);
        }
        try {
            if (!f.isVisible()) {
                f.setVisible(true);
            }
            f.setTitle(clazz.getSimpleName());
            f.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
    }
}
