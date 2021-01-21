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
public class own_plan_tablecell_rendererSUM extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

//A tábla eredeti cell render komponense létrehozva és boxolva egy JLabel componensé
        JLabel lbl = ((JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column));
        lbl.setOpaque(true);
        int SUM=0;
        
        try{
            SUM = Integer.parseInt(value.toString());
            
        }catch(Exception e){
            
        }
        
        
        /*
         
        
        for (int i=5; i<19; i++){
           try{
            SUM+= Integer.parseInt(table.getValueAt(row, i).toString());
           }catch(Exception e){
               System.out.println(e.getMessage());
           }
        }
        
       */
        
       // lbl.setText(Integer.toString(SUM));
        
        if (isSelected){
        lbl.setBackground(table.getSelectionBackground());
        if (lbl.getText().equals("0")){
            lbl.setText("");
        }
            
        }else{
            if (!lbl.getText().equals("0")){
            if (table.getValueAt(row, 12).toString().equals("Terv")){
              lbl.setBackground(new Color(145,255,186));  
            }else{
               
                try{
                    if (table.getValueAt(row-1, column)!=null){
                    int TERV = Integer.parseInt(table.getValueAt(row-1, column).toString());
                     
                    if (SUM<TERV){
                        lbl.setBackground(new Color(255,193,145)); //piros
                    }else if (SUM==TERV){
                        lbl.setBackground(new Color(145,255,186)); //zöld
                      
                    }else{
                         lbl.setBackground(new Color(145,164,255)); //kék
                    }
                    }
                }catch(Exception e){
                     
                }
                
                
            }
        
        }else{
                lbl.setBackground(table.getBackground()); 
                lbl.setText("");
            }
        }

        lbl.setHorizontalAlignment(JLabel.CENTER);

        return lbl;
    }

}
