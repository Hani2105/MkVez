package mkvez;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ImageIcon;

/**
 * @author krisztian_csekme Speciális cella formázására létrehozott cella render
 **/
public class own_plan_tablecell_rendererIMG extends DefaultTableCellRenderer {

    ImageIcon ico = null;

    public own_plan_tablecell_rendererIMG(ImageIcon icon) {
        this.ico = icon;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));

        lbl.setIcon(ico);

        if (isSelected) {
            lbl.setBackground(table.getSelectionBackground());
        } else {
            lbl.setBackground(table.getBackground());
        }

        return lbl;
    }

}
