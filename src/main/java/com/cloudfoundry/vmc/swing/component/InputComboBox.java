package com.cloudfoundry.vmc.swing.component;

import javax.swing.ComboBoxModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class InputComboBox extends PlainDocument {

    private static final long serialVersionUID = 3443570534975914710L;

    private ComboBoxModel model;
    private boolean selecting;
    
    public InputComboBox(ComboBoxModel model) {
        this.model = model;
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        if (!selecting) {
            super.remove(offs, len);
        }
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (!selecting) {
            super.insertString(offs, str, a);
            String content = getText(0, getLength());
            selecting = true;
            model.setSelectedItem(lookupItem(content));
            selecting = false;
        }
    }

    private Object lookupItem(String pattern) {
        for (int i = 0, n = model.getSize(); i < n; i++) {
            Object currentItem = model.getElementAt(i);
            if (currentItem.toString().startsWith(pattern)) {
                return currentItem;
            }
        }
        return null;
    }

}
