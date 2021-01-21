package mkvez;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;


/**
 *
 * @author krisztian_csekme Speciális cella formázására létrehozott cella render
 */
public class own_plan_tablecell_renderer_align extends DefaultTableCellRenderer {

    public static enum align {LEFT, CENTER, RIGHT};
    protected align A;

    public own_plan_tablecell_renderer_align(align _align){
        switch (_align){
            case LEFT:
                A=align.LEFT;
                break;
            case CENTER:
                A=align.CENTER;
                break;
            case RIGHT:
                A=align.RIGHT;
                break;
                
        }
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//A tábla eredeti cell render komponense létrehozva és boxolva egy JLabel componensé
        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
        lbl.setOpaque(true);
         
        
         

        switch (A){
            case LEFT:
                lbl.setHorizontalAlignment(JLabel.LEFT);
                break;
            case CENTER:
                lbl.setHorizontalAlignment(JLabel.CENTER);
                break;
            case RIGHT:
                lbl.setHorizontalAlignment(JLabel.RIGHT);
                break;
                
        }

        return lbl;
    }

}
