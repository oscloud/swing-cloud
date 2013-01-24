package com.cloudfoundry.vmc.swing.component;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ZTable extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 5954132957942911724L;

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        int maxPreferredWidth = 0;
        for (int i = 0, n = table.getColumnCount(); i < n; i++) {
            setText(table.getValueAt(row, i).toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
            maxPreferredWidth = Math.max(maxPreferredWidth,
                    getPreferredSize().width);
        }

        if (table.getColumnModel().getColumn(row).getWidth() != maxPreferredWidth) // 少了这行则处理器瞎忙
            table.getColumnModel().getColumn(row).setWidth(maxPreferredWidth);

        setText(value == null ? "" : value.toString());

        return this;
    }

}
