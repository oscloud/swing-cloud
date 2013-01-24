package com.cloudfoundry.vmc.swing.dialog.applications;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.cloudfoundry.client.lib.domain.CloudApplication;

import com.cloudfoundry.vmc.swing.frame.BaseInternalFrame;

@Deprecated
public class AppList extends BaseInternalFrame {
    private static final long serialVersionUID = -8607149290281591829L;

    private static AppList apps;

    private JPanel jp;
    private JButton btn_refresh;
    private JTable table;
    private JScrollPane jsp;
    @SuppressWarnings("unused")
    private static DefaultTableModel dftm;

    public AppList() {
        this.initGUI();
        this.initAction();
    }
    
    private void initResource() {
        jp = new JPanel();
        btn_refresh = new JButton("Refresh");
        table = new JTable();
        dftm = (DefaultTableModel) table.getModel();
        jsp = new JScrollPane(table);
    }
    
    private void initAction() {
        btn_refresh.addActionListener(this);
    }

    public void initGUI() {
        initResource();
        
        this.setTitle("App List");

        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);

        this.add(jp,BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
        
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp.add(btn_refresh);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(25);
        table.setShowGrid(true);
        dftm = (DefaultTableModel) table.getModel();
        jsp.setViewportView(table);

        this.setSize(700, 400);
        this.setLocation(50, 8);
    }

    public static AppList getInstance() {
        if (null == apps) {
            apps = new AppList();
        }
        return apps;
    }
    
    public static void updateTable(List<CloudApplication> list, DefaultTableModel dftm) {
        int num = dftm.getRowCount();
        for (int i = num - 1; i > -1; i--) {
            dftm.removeRow(i);
        }
//        if (!list.isEmpty()) {
//	        for (App it : list) {
//	        	Vector<Object> rowData = new Vector<Object>();
//	            rowData.add(it.getApplication());
//	            rowData.add(it.getPlaceholder());
//	            rowData.add(it.getHealth());
//	            rowData.add(it.getUrl());
//	            rowData.add(it.getService());
//	            
//	            dftm.addRow(rowData);
//	        }
//        }
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = (Object) e.getSource();
        if (obj == btn_refresh) {
        }
    }
}
