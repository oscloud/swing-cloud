package com.cloudfoundry.vmc.swing.component;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.cloudfoundry.client.lib.domain.CloudInfo;

import com.cloudfoundry.vmc.common.Session;
import com.cloudfoundry.vmc.swing.action.Function;
import com.cloudfoundry.vmc.swing.dialog.other.Common;
import com.cloudfoundry.vmc.swing.frame.BaseFrame;

public class MenuBar extends BaseFrame {
	private static final long serialVersionUID = -8607149290281591829L;

	/**
	 * autowried
	 */
	private static MenuBar menuBar;
	private JMenuBar mBar;

	/**
	 * menu
	 */
	private JMenu mApplications;
	private JMenu mCreation;
	private JMenu mOperations;
	private JMenu mUpdates;
	private JMenu mServices;
	private JMenu mInformation;
	private JMenu mAccount;
	private JMenu mSystem;
	private JMenu mHelp;
	private JMenu mQuit;

	/**
	 * menuItem
	 */
	private JMenuItem miApps;
	
	private JMenuItem miUser;
	private JMenuItem miPasswd;
	
	private JMenuItem miRuntimes;
	private JMenuItem miFrameworks;
	
	private JMenuItem miHelp;

	private JMenuItem miReboot;
	private JMenuItem miQuit;

	private void initResource() {
		mBar = new JMenuBar();
		mApplications = new JMenu("Applications(A)");
		mCreation = new JMenu("Creation(C)");
		mOperations = new JMenu("Operations(O)");
		mUpdates = new JMenu("Updates(U)");
		mServices = new JMenu("Services(S)");
		mInformation = new JMenu("Information(I)");
		mAccount = new JMenu("Account(T)");
		mSystem = new JMenu("System(M)");
		mHelp = new JMenu("Help(H)");
		mQuit = new JMenu("Quit(Q)");
		
		miApps = new JMenuItem("Apps list");
		
		miUser = new JMenuItem("Information");
		miPasswd = new JMenuItem("Update password");

		miRuntimes = new JMenuItem("Runtimes");
		miFrameworks = new JMenuItem("Frameworks");
		
		miHelp = new JMenuItem("Help");

		miReboot = new JMenuItem("Reboot");
		miQuit = new JMenuItem("Quit");
	}

	public void initAccelerator() {
		mApplications.setMnemonic('A');
		mCreation.setMnemonic('C');
		mOperations.setMnemonic('O');
		mUpdates.setMnemonic('U');
		mServices.setMnemonic('S');
		mInformation.setMnemonic('I');
		mAccount.setMnemonic('T');
		mSystem.setMnemonic('M');
		mHelp.setMnemonic('H');
		mQuit.setMnemonic('Q');
	}

	public void initAction() {
		miApps.addActionListener(this);
		miRuntimes.addActionListener(this);
		miFrameworks.addActionListener(this);
		miReboot.addActionListener(this);
		miQuit.addActionListener(this);
	}

	/**
	 * Build MenuBar
	 */
	public void buildMenuBar() {

		mBar.add(mApplications);
		mBar.add(mCreation);
		mBar.add(mOperations);
		mBar.add(mUpdates);
		mBar.add(mServices);
		mBar.add(mInformation);
		mBar.add(mAccount);
		mBar.add(mSystem);
		mBar.add(mHelp);
		mBar.add(mQuit);
		
		mApplications.add(miApps);
		
		mAccount.add(miUser);
		mAccount.add(miPasswd);
		
		mSystem.add(miRuntimes);
		mSystem.add(miFrameworks);
		
		mHelp.add(miHelp);
		
		mQuit.add(miReboot);
		mQuit.add(miQuit);
	}

	public void initGUI() {
		this.initResource();
		this.initAccelerator();
		this.initAction();
		this.buildMenuBar();
	}

	public MenuBar() {
		this.initGUI();
	}

	public static MenuBar getInstance() {
		if (null == menuBar) {
			menuBar = new MenuBar();
		}
		return menuBar;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		CloudFoundryClient client = Session.getInstance().getClient();
		if (obj == miApps) {
		    Function.open(Common.getInstance(client.getApplications()), CloudApplication.class);
		} else if (obj == miRuntimes) {
		    Function.open(Common.getInstance(client.getCloudInfo()), CloudInfo.class);
		} else if (obj == miFrameworks) {
		} else if (obj == miReboot) {
			Function.reboot(loginFrame, mainFrame);
		} else if (obj == miQuit) {
			Function.quit(mainFrame);
		}
	}

	public JMenuBar toMenuBar() {
		return mBar;
	}

}
