/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author krisztian_csekme1
 */
public class PrintDialog extends javax.swing.JDialog {

    /**
     * Creates new form PrintDialog
     *
     * @param model
     */
    public void hideColumn(int col, JTable table) {
        if (table.getColumnCount() > col) {
            table.getColumnModel().getColumn(col).setWidth(0);
            table.getColumnModel().getColumn(col).setPreferredWidth(0);
            table.getColumnModel().getColumn(col).setMinWidth(0);
            table.getColumnModel().getColumn(col).setMaxWidth(0);
        }
    }

    public void showColumn(int col, int width, JTable table) {
        if (table.getColumnCount() > col) {
            table.getColumnModel().getColumn(col).setWidth(width);
            table.getColumnModel().getColumn(col).setPreferredWidth(width);
            table.getColumnModel().getColumn(col).setMinWidth(width);
            table.getColumnModel().getColumn(col).setMaxWidth(500);
        }
    }

    public void setHeaderName(int col, String name, JTable table) {
        if (table.getColumnCount() > col) {
            table.getColumnModel().getColumn(col).setHeaderValue(name);

        }
    }

    public void setTheme() {

        hideColumn(0, TAB);
        showColumn(1, 120, TAB);
        showColumn(2, 120, TAB);
        hideColumn(4, TAB);
        hideColumn(6, TAB);
        hideColumn(7, TAB);
        hideColumn(28, TAB);

        TAB.getTableHeader().setPreferredSize(new Dimension(jScrollPane1.getWidth(), 40));
        
        
        if (COMBO_FAMILY.isSelected()) {
            showColumn(8, 120, TAB);
        } else {
            hideColumn(8, TAB);
        }

        if (COMBO_DB_H.isSelected()) {
            showColumn(10, 50, TAB);
        } else {
            hideColumn(10, TAB);
        }

        if (COMBO_STARTUP.isSelected()) {
            showColumn(9, 50, TAB);
        } else {
            hideColumn(9, TAB);
        }

        if (COMBO_OUT.isSelected()) {
            showColumn(29, 60, TAB);
            TAB.getColumnModel().getColumn(29).setCellRenderer(new own_plan_tablecell_renderer_out());
        } else {
            hideColumn(29, TAB);
        }

        if (COMBO_SUM.isSelected()) {
            showColumn(30, 50, TAB);
            TAB.getColumnModel().getColumn(30).setCellRenderer(new own_plan_tablecell_rendererSUM());
        } else {
            hideColumn(30, TAB);
        }
        
        
        for (int i=0; i<TAB.getColumnCount(); i++){
            if (i == 5) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("Oldal");
            }

            if (i == 11) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Teljes<br>terv</html>");
            }

            if (i == 12) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Terv<br>Tény</html>");
            }
            if (i == 13) {

                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Hétfő<br>Reggel</html>");

            }
            if (i == 14) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Hétfő<br>Délután</html>");
            }
            if (i == 15) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Kedd<br>Reggel</html>");
            }
            if (i == 16) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Kedd<br>Délután</html>");
            }
            if (i == 17) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Szerda<br>Reggel</html>");
            }
            if (i == 18) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Szerda<br>Délután</html>");
            }
            if (i == 19) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Csütörtök<br>Reggel</html>");
            }
            if (i == 20) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Csütörtök<br>Délután</html>");
            }
            if (i == 21) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Péntek<br>Reggel</html>");
            }
            if (i == 22) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Péntek<br>Délután</html>");
            }
            if (i == 23) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Szombat<br>Reggel</html>");
            }
            if (i == 24) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Szombat<br>Délután</html>");
            }
            if (i == 25) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Vasárnap<br>Reggel</html>");
            }
            if (i == 26) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Vasárnap<br>Délután</html>");
            }
            if (i == 27) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("<html>Hétfő<br>Reggel</html>");
            }

            if (i == 29) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("Kiszállítás");
            }
            if (i == 30) {
                TAB.getColumnModel().getColumn(i).setHeaderValue("Szumma");
            }
        }
        
        

        
        for (int c=13; c<28; c++){
            if (SELECTION_LIST.isSelectedIndex(c-13)){
                showColumn(c, 50, TAB);
                TAB.getColumnModel().getColumn(c).setCellRenderer(new own_plan_tablecell_renderer());
            }else{
                hideColumn(c, TAB);
            }
        }
        
        
    }

    public void setVisible(DefaultTableModel model) {
        TAB.setModel(model);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim);
        setTheme();
         if (SELECTION_LIST.getSelectedIndices().length==0){
                LBL_TOOLTIP.setVisible(true);
            }else{
                LBL_TOOLTIP.setVisible(false);
            }
        this.setVisible(true);
    }

    public PrintDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setIconImage(new ImageIcon(this.getClass().getResource("IMG/printer.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        COMBO_DB_H = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        SELECTION_LIST = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        COMBO_FAMILY = new javax.swing.JCheckBox();
        COMBO_STARTUP = new javax.swing.JCheckBox();
        COMBO_OUT = new javax.swing.JCheckBox();
        COMBO_SUM = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        LBL_TOOLTIP = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TAB = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Terv nyomtatása");
        setIconImage(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nyomtatandó oszlopok:"));

        COMBO_DB_H.setText("Darab / Óra");
        COMBO_DB_H.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                COMBO_DB_HMouseReleased(evt);
            }
        });
        COMBO_DB_H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_DB_HActionPerformed(evt);
            }
        });

        SELECTION_LIST.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Hétfő reggel", "Hétfő délután", "Kedd reggel", "Kedd délután", "Szerda reggel", "Szerda délután", "Csütörtök reggel", "Csütörtök délután", "Péntek reggel", "Péntek délután", "Szombat reggel", "Szombat délután", "Vasárnap reggel", "Vasárnap délután", "Hétfő reggel" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        SELECTION_LIST.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SELECTION_LISTMouseReleased(evt);
            }
        });
        SELECTION_LIST.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                SELECTION_LISTValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(SELECTION_LIST);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/printer16.png"))); // NOI18N
        jButton1.setText("Nyomtat");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        COMBO_FAMILY.setText("Termékcsalád");
        COMBO_FAMILY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                COMBO_FAMILYMouseReleased(evt);
            }
        });
        COMBO_FAMILY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_FAMILYActionPerformed(evt);
            }
        });

        COMBO_STARTUP.setText("Tárazás");
        COMBO_STARTUP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                COMBO_STARTUPMouseReleased(evt);
            }
        });
        COMBO_STARTUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_STARTUPActionPerformed(evt);
            }
        });

        COMBO_OUT.setText("Kiszállítási szint");
        COMBO_OUT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                COMBO_OUTMouseReleased(evt);
            }
        });
        COMBO_OUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_OUTActionPerformed(evt);
            }
        });

        COMBO_SUM.setText("Szumma");
        COMBO_SUM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                COMBO_SUMMouseReleased(evt);
            }
        });
        COMBO_SUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_SUMActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/refresh.png"))); // NOI18N
        jButton2.setToolTipText("Nyomtatási kép frissítése");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LBL_TOOLTIP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/hand_set_left.png"))); // NOI18N
        LBL_TOOLTIP.setText("Válassz ki műszak(ot/okat)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(COMBO_FAMILY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(COMBO_STARTUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(COMBO_DB_H, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(COMBO_OUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(COMBO_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LBL_TOOLTIP)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LBL_TOOLTIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(COMBO_FAMILY)
                            .addComponent(COMBO_OUT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(COMBO_DB_H)
                            .addComponent(COMBO_SUM))
                        .addGap(0, 0, 0)
                        .addComponent(COMBO_STARTUP))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nyomtatandó terv:"));

        TAB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TAB.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(TAB);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            TAB.print(JTable.PrintMode.FIT_WIDTH);

        } catch (PrinterException ex) {
            Logger.getLogger(PlanForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void COMBO_FAMILYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_FAMILYActionPerformed
        
    }//GEN-LAST:event_COMBO_FAMILYActionPerformed

    private void COMBO_DB_HActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_DB_HActionPerformed
        
    }//GEN-LAST:event_COMBO_DB_HActionPerformed

    private void COMBO_STARTUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_STARTUPActionPerformed
        
    }//GEN-LAST:event_COMBO_STARTUPActionPerformed

    private void COMBO_OUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_OUTActionPerformed
        
    }//GEN-LAST:event_COMBO_OUTActionPerformed

    private void COMBO_SUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_SUMActionPerformed
         
    }//GEN-LAST:event_COMBO_SUMActionPerformed

    private void COMBO_FAMILYMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_FAMILYMouseReleased

    }//GEN-LAST:event_COMBO_FAMILYMouseReleased

    private void COMBO_DB_HMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_DB_HMouseReleased
 
    }//GEN-LAST:event_COMBO_DB_HMouseReleased

    private void COMBO_STARTUPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_STARTUPMouseReleased

    }//GEN-LAST:event_COMBO_STARTUPMouseReleased

    private void COMBO_OUTMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_OUTMouseReleased

    }//GEN-LAST:event_COMBO_OUTMouseReleased

    private void COMBO_SUMMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_SUMMouseReleased

    }//GEN-LAST:event_COMBO_SUMMouseReleased

    private void SELECTION_LISTMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SELECTION_LISTMouseReleased

    }//GEN-LAST:event_SELECTION_LISTMouseReleased

    private void SELECTION_LISTValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_SELECTION_LISTValueChanged
            
        setTheme();
        
        
            
            if (SELECTION_LIST.getSelectedIndices().length==0){
                LBL_TOOLTIP.setVisible(true);
            }else{
                LBL_TOOLTIP.setVisible(false);
            }
            
    }//GEN-LAST:event_SELECTION_LISTValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                setTheme();
            }
        });
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
             SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                setTheme();
            }
        });
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrintDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrintDialog dialog = new PrintDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox COMBO_DB_H;
    private javax.swing.JCheckBox COMBO_FAMILY;
    private javax.swing.JCheckBox COMBO_OUT;
    private javax.swing.JCheckBox COMBO_STARTUP;
    private javax.swing.JCheckBox COMBO_SUM;
    private javax.swing.JLabel LBL_TOOLTIP;
    private javax.swing.JList SELECTION_LIST;
    private javax.swing.JTable TAB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
