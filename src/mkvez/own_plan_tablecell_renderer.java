package mkvez;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author krisztian_csekme Speciális cella formázására létrehozott cella render
 */
public class own_plan_tablecell_renderer extends DefaultTableCellRenderer {

    ImageIcon en = new ImageIcon(mkvez.MkVez.class.getResource("IMG/engineering16.png"));

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//A tábla eredeti cell render komponense létrehozva és boxolva egy JLabel componensé
        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
        lbl.setToolTipText("");
        lbl.setIcon(null);
       // lbl.setOpaque(true);

        if (isSelected) {
            lbl.setBackground(table.getSelectionBackground());

        } else {
            if (!lbl.getText().equals("")) {
                if (table.getValueAt(row, 12).toString().equals("Terv")) {
                    lbl.setBackground(new Color(145, 255, 186)); //zöld
                    if (table.getValueAt(row, 28).toString().equals("1")) {
                        
                        lbl.setIcon(en);
                        lbl.setToolTipText("Mérnöki gyártás");
                    }

                } else {
                    lbl.setBackground(new Color(145, 164, 255)); //kék

                }

            } else {
                lbl.setBackground(table.getBackground());
                lbl.setText("");
            }
        }

        lbl.setHorizontalAlignment(JLabel.CENTER);

        return lbl;
    }

}
