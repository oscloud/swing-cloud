package com.cloudfoundry.vmc.swing.component;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.cloudfoundry.vmc.common.Icon;

public class Panel extends JPanel {
	private static final long serialVersionUID = -8607149290281591829L;

	protected ImageIcon icon;

	public Panel(String img) {
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
