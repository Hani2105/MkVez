package mkvez;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.ImageIcon;

/**
 *
 * @author krisztian_csekme Speciális cella formázására létrehozott cella render
 */
public class own_plan_tablecell_renderer_out extends DefaultTableCellRenderer {

    ImageIcon deliv = new ImageIcon(mkvez.MkVez.class.getResource("IMG/delivery.png"));
    ImageIcon unknown = new ImageIcon(mkvez.MkVez.class.getResource("IMG/unknown.png"));
    ImageIcon be = new ImageIcon(mkvez.MkVez.class.getResource("IMG/be.png"));
    

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//A tábla eredeti cell render komponense létrehozva és boxolva egy JLabel componensé
        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
        lbl.setIcon(null);
        lbl.setToolTipText("");
        lbl.setBackground(table.getBackground());

        if (isSelected) {
            lbl.setBackground(table.getSelectionBackground());
            if (table.getValueAt(row, 12).toString().equals("Terv")){
            if (!lbl.getText().equals("")) {
             if (lbl.getText().equals("SMT")){
                 lbl.setToolTipText("Kiszállítás SMT szinten");
                 lbl.setIcon(deliv);
             }else if (lbl.getText().equals("BE")){
                 lbl.setToolTipText("Kiszállítás BE szinten");
                 lbl.setIcon(be);
             }
            } else {
                lbl.setIcon(unknown);
                lbl.setToolTipText("Kiszállítási szint ismeretlen");
                lbl.setBackground(table.getBackground());
                
            }
            }
            
            

        } else {
            lbl.setBackground(table.getBackground());
            if (table.getValueAt(row, 12).toString().equals("Terv")){
            if (!lbl.getText().equals("")) {
             if (lbl.getText().equals("SMT")){
                 lbl.setToolTipText("Kiszállítás SMT szinten");
                 lbl.setIcon(deliv);
             }else if (lbl.getText().equals("BE")){
                 lbl.setToolTipText("Kiszállítás BE szinten");
                 lbl.setIcon(be);
             }
            } else {
                lbl.setIcon(unknown);
                lbl.setToolTipText("Kiszállítási szint ismeretlen");
                lbl.setBackground(table.getBackground());
                
            }
        }
        }
        lbl.setText("");
        lbl.setHorizontalAlignment(JLabel.CENTER);

        return lbl;
    }

}
