package com.cloudfoundry.vmc.swing.frame;

import java.awt.AWTEvent;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class BaseFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = -8607149290281591829L;

    public static Login loginFrame;
    protected static Main mainFrame;
    private static BaseFrame base;

    public void actionPerformed(ActionEvent e) {
    }

    public BaseFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
        	if (SystemTray.isSupported()) {
        		mainFrame.hidden();
        	} else {
        		System.exit(0);
        	}
        } else if (e.getID() == WindowEvent.WINDOW_ICONIFIED) {
        	if (SystemTray.isSupported()) {
        		mainFrame.hidden();
        	}
        }
    }

    public static BaseFrame getInstance() {
        if (null == base) {
            base = new BaseFrame();
        }
        return base;
    }
}
