package com.cloudfoundry.vmc.swing.component;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import com.cloudfoundry.vmc.CloudConstants;

public class Body {
	private static final long serialVersionUID = -8607149290281591829L;

	private static Body mainBody;

	private JPanel jbody;
	private JDesktopPane desktop;

	private void initResource() {
		jbody = new JPanel(new BorderLayout());
		desktop = new DesktopPane(CloudConstants.icon.MAIN);
	}

	public void initGUI() {
		this.initResource();
		this.buildBody();
	}

	private void buildBody() {
		// jbody.add(ToolBar.getInstance().toToolBar(),BorderLayout.NORTH);
		jbody.add(desktop, BorderLayout.CENTER);
		jbody.add(Copyright.getInstance().toCopyright(), BorderLayout.SOUTH);
	}

	public void initAction() {
	}

	public Body() {
		this.initGUI();
		this.buildBody();
		this.initAction();
	}

	public static Body getInstance() {
		if (null == mainBody) {
			mainBody = new Body();
		}
		return mainBody;
	}

	public JPanel toBody() {
		return jbody;
	}

	public JDesktopPane toDeskTop() {
		return desktop;
	}
}
