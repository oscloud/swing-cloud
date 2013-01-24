package com.cloudfoundry.vmc.swing.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import com.cloudfoundry.vmc.common.Icon;

public class DesktopPane extends JDesktopPane {

    private static final long serialVersionUID = -5899319429856737324L;

    protected ImageIcon icon;

    public DesktopPane(String img) {
        setLayout(null);
        icon = Icon.I(img);
        setSize(icon.getIconWidth(), icon.getIconHeight());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = icon.getImage();
        g.drawImage(img, 0, 0, null);
    }

}
