package com.cloudfoundry.vmc.swing.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;

public class BaseInternalFrame extends JInternalFrame implements ActionListener {
    private static final long serialVersionUID = -8607149290281591829L;

    private static BaseInternalFrame baseInternal;

    public void actionPerformed(ActionEvent e) {
    }

    public BaseInternalFrame() {
    }

    public static BaseInternalFrame getInstance() {
        if (null == baseInternal) {
            baseInternal = new BaseInternalFrame();
        }
        return baseInternal;
    }
}
