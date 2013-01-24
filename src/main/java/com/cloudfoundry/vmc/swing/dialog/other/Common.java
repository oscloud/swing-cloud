package com.cloudfoundry.vmc.swing.dialog.other;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cloudfoundry.vmc.common.Json;
import com.cloudfoundry.vmc.swing.frame.BaseInternalFrame;

public class Common extends BaseInternalFrame {
    private static final long serialVersionUID = -8607149290281591829L;

    private static Common common;

    private JPanel jPanel;
    private JButton btnRefresh;
    private static JTextArea txtInfo;
    private JScrollPane jscroll; 
    

    public Common() {
        this.initGUI();
        this.initAction();
    }
    
    private void initResource() {
        jPanel = new JPanel();
        btnRefresh = new JButton("Refresh");
        txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        jscroll = new JScrollPane(txtInfo);
    }
    
    private void initAction() {
        btnRefresh.addActionListener(this);
    }

    public void initGUI() {
        initResource();
        
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);

        this.add(jPanel,BorderLayout.NORTH);
        this.add(jscroll, BorderLayout.CENTER);
        
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel.add(btnRefresh);

        this.setSize(700, 400);
        this.setLocation(50, 8);
    }
    
    public static Common getInstance(Object object) {
        if (null == common) {
            common = new Common();
        }
        updateInformation(object == null ? "Nothing.." : Json.formatObjectAsString(object));
        return common;
    }
    
    public static void updateInformation(String s) {
    	txtInfo.setText(s);
    }
    
    public void actionPerformed(ActionEvent e) {
        Object obj = (Object) e.getSource();
        if (obj == btnRefresh) {
        }
    }
}
