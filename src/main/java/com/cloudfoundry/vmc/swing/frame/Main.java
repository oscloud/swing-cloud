package com.cloudfoundry.vmc.swing.frame;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.cloudfoundry.vmc.CloudConstants;
import com.cloudfoundry.vmc.common.Icon;
import com.cloudfoundry.vmc.swing.action.Function;
import com.cloudfoundry.vmc.swing.component.Body;
import com.cloudfoundry.vmc.swing.component.MenuBar;

public class Main extends BaseFrame{
	private static final long serialVersionUID = -8607149290281591829L;
	
	private TrayIcon trayIcon;
    private SystemTray tray;
    
    private PopupMenu pop;
    private MenuItem miOpen;
    private MenuItem miQuit;
	
    public void initPopMenu() {
        pop = new PopupMenu();
        miOpen = new MenuItem("Open");
        miQuit = new MenuItem("Quit");
        pop.add(miOpen);
        pop.addSeparator();
        pop.add(miQuit);
    }
    
    public void initAccelerator() {
    	this.initPopMenu();
    	
    }
    
    public void initTray() {
    	this.initAccelerator();
    	
        trayIcon = new TrayIcon(Icon.IM(CloudConstants.icon.DESKTOP), null, pop);
        trayIcon.setImageAutoSize(true);
    }
    
	public void initGUI(){
	    this.initTray();
	    
		this.add(Body.getInstance().toBody());
		this.setJMenuBar(MenuBar.getInstance().toMenuBar());
		
		this.setTitle("Desktop for cloudfoundry vmc");
		this.setIconImage(Icon.IM(CloudConstants.icon.DESKTOP));
		this.setSize(800, 520);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	public void initAction() {
	    miOpen.addActionListener(this);
	    miQuit.addActionListener(this);
	    
	    trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showFrame();
                }
            }
        });
	}
	
	
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == miOpen) {
            showFrame();
        } else if (obj == miQuit) {
            Function.quit(this);
        }
    }
    
    public String initTips() {
    	return "Email: " + loginFrame.getEmail();
    }
    
    public void invoke() {
        this.setVisible(true);
        try {
        	tray = SystemTray.getSystemTray();
        	trayIcon.setToolTip(this.initTips());
            tray.add(trayIcon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
    }
    
    public void hivoke() {
        tray.remove(trayIcon);
        this.hidden();
    }
    
    public void hidden() {
    	this.setVisible(false);
        this.dispose();
    }

    private void showFrame() {
    	this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Main(){
		this.initGUI();
		this.initAction();
	}

	public static void main(String[] args) {
		try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login.getInstance();
			}
		});
	}
}
