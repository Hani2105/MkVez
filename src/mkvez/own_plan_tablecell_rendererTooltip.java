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
public class own_plan_tablecell_rendererTooltip extends DefaultTableCellRenderer {
ImageIcon ico = new ImageIcon(this.getClass().getResource("IMG/eye.png"));
     

@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//A tábla eredeti cell render komponense létrehozva és boxolva egy JLabel componensé
        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
        

     if (value!=null){
         if (value.toString().length()>0){
              if (table.getValueAt(row, 12).toString().equals("Terv")){
        lbl.setSize(16, 16);
        lbl.setIcon(ico);
        lbl.setToolTipText(lbl.getText());
        lbl.setHorizontalAlignment(JLabel.CENTER);
        
              }else{
                     lbl.setIcon(null);
                     lbl.setToolTipText(null); 
              }

     }else{
         lbl.setIcon(null);
         lbl.setToolTipText(null);
     }
     }
        lbl.setText("");
        return lbl;
    }

}
