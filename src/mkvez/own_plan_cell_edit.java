/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author krisztian_csekme1
 */
public class own_plan_cell_edit extends DefaultCellEditor {

    public own_plan_cell_edit() {
        super(new JTextField());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JTextField editor = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        editor.setEditable(true);
        editor.setEnabled(true);
        if (table.getValueAt(row, 12).toString().equals("Tény")) {
            if (!MkVez.GEST) {
                editor.setEditable(true);
                editor.setEnabled(true);
            } else {
                editor.setEditable(false);
                editor.setEditable(false);
            }
        } else {
            editor.setEditable(false);
            editor.setEditable(false);
        }

        return editor;

    }

}
