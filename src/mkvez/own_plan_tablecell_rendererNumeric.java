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
public class own_plan_tablecell_rendererNumeric extends DefaultTableCellRenderer {

    ImageIcon en = new ImageIcon(mkvez.MkVez.class.getResource("IMG/engineering16.png"));

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//A tábla eredeti cell render komponense létrehozva és boxolva egy JLabel componensé
        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
        lbl.setIcon(null);
        // lbl.setOpaque(true);

        if (isSelected) {
            lbl.setBackground(table.getSelectionBackground());

        } else {
            lbl.setBackground(table.getBackground());
        }

        try{
            
            if (Double.parseDouble(lbl.getText())==0){
                lbl.setText("");
            }
            
        }catch (Exception err){
            
        }
        
        
        
        lbl.setHorizontalAlignment(JLabel.CENTER);

        return lbl;
    }

}
