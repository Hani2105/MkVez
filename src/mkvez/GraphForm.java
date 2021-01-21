/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.joda.time.DateTime;

/**
 *
 * @author krisztian_csekme1
 */
public class GraphForm extends javax.swing.JPanel {

    /**
     * Creates new form GraphForm
     */
    double[] terv = new double[14];
    double[] teny = new double[14];
    double[] kumm = new double[14];
    double[] diff = new double[14];

    double[] csuszas = new double[14];
    double[] karbi = new double[14];

    String[] mszak = new String[]{"Hétfő reggel", "Hétfő délután", "Kedd reggel", "Kedd délután", "Szerda reggel", "Szerda délután", "Csütörtök reggel", "Csütörtök délután", "Péntek reggel", "Péntek délután", "Szombat reggel", "Szombat délután", "Vasárnap reggel", "Vasárnap délután"};

    double getTerv(int c) {
        double value = 0;
        for (int r = 0; r < TAB_PLAN.getRowCount(); r++) {

            try {
                if (TAB_PLAN.getValueAt(r, 5).toString().equals("Terv")) {
                    value += Double.parseDouble(TAB_PLAN.getValueAt(r, c).toString());
                }
            } catch (Exception err) {

            }
        }
        return value;
    }

    double getTeny(int c) {
        double value = 0;
        for (int r = 0; r < TAB_PLAN.getRowCount(); r++) {

            try {
                if (TAB_PLAN.getValueAt(r, 5).toString().equals("Tény")) {
                    value += Double.parseDouble(TAB_PLAN.getValueAt(r, c).toString());
                }
            } catch (Exception err) {

            }
        }
        return value;
    }

    public void precalc() {
        Arrays.fill(csuszas, 0.0);
        Arrays.fill(karbi, 0.0);

        for (int r = 0; r < TAB_PLAN_DOWN.getRowCount(); r++) {
            if (TAB_PLAN_DOWN.getValueAt(r, 0).toString().equals("Csúszás") || TAB_PLAN_DOWN.getValueAt(r, 0).toString().equals("Csúszás - létszám miatt")) {
                for (int c = 1; c < TAB_PLAN_DOWN.getColumnCount(); c++) {
                    try {
                        csuszas[c - 1] += Double.parseDouble(TAB_PLAN_DOWN.getValueAt(r, c).toString());
                    } catch (Exception e) {

                    }
                }
            }
            
            if (TAB_PLAN_DOWN.getValueAt(r, 0).toString().equals("Karbantartás")) {
                for (int c = 1; c < TAB_PLAN_DOWN.getColumnCount(); c++) {
                    try {
                        karbi[c - 1] += Double.parseDouble(TAB_PLAN_DOWN.getValueAt(r, c).toString());
                    } catch (Exception e) {

                    }
                }
            }
            
        }

    }

    public void calc() {
        precalc();
        Arrays.fill(terv, 0.0);
        Arrays.fill(teny, 0.0);

        for (int c = 0; c < 14; c++) {
            terv[c] = getTerv(c + 6);
            terv[c] += csuszas[c];
            terv[c] += karbi[c];
            teny[c] = getTeny(c + 6);
            teny[c] += karbi[c];
            diff[c] = teny[c] - terv[c];
            if (c > 0) {
                kumm[c] = (teny[c] - terv[c]) + kumm[c - 1];
            } else {
                kumm[c] = teny[c] - terv[c];
            }

        }

        DefaultTableModel MODEL = new DefaultTableModel(0, 15);

        Object[] sor = new Object[15];

        sor[0] = "Terv";
        for (int i = 0; i < terv.length; i++) {
            sor[i + 1] = round(terv[i], 2);
        }
        MODEL.addRow(sor);
        sor = new Object[15];

        sor[0] = "Tény";
        for (int i = 0; i < teny.length; i++) {
            sor[i + 1] = round(teny[i], 2);
        }

        MODEL.addRow(sor);
        sor = new Object[15];

        sor[0] = "Diff";
        for (int i = 0; i < diff.length; i++) {
            sor[i + 1] = round(diff[i], 2);
        }

        MODEL.addRow(sor);
        sor = new Object[15];
        sor[0] = "Kummulatív";
        for (int i = 0; i < kumm.length; i++) {
            sor[i + 1] = round(kumm[i], 2);
        }
        MODEL.addRow(sor);

        TAB_EFF.setModel(MODEL);

    }

    public GraphForm() {
        initComponents();
        SPLIT.setOneTouchExpandable(true);
        SPLIT.setDividerLocation(9000);
        /*
         Gyártási Év Combobox inicializálása és előválasztása
         */
        int begening = 2015;

        for (int i = begening; i < new DateTime().getYear() + 2; i++) {
            COMBO_YEAR.addItem((Object) i);
        }

        COMBO_YEAR.setSelectedItem(new DateTime().getYear());

        /*
         Gyártási Hét Combobox inicializáslása és előválasztása
         */
        for (int i = 0; i < 53; i++) {
            COMBO_WEEK.addItem(i + 1);
        }

        COMBO_WEEK.setSelectedIndex(new DateTime().getWeekOfWeekyear() - 1);

        COMBO_LINE.setModel(new DefaultComboBoxModel(MkVez.LINE_NAMES));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SPLIT = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        COMBO_YEAR = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        COMBO_WEEK = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        COMBO_LINE = new javax.swing.JComboBox();
        BTN_QUERY = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        COMBO_TYPES = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        CHART_SCREEN = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TAB_EFF = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TAB_PLAN = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TAB_DOWNS = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TAB_PLAN_DOWN = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        SPLIT.setDividerLocation(200);
        SPLIT.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Év:");

        jLabel2.setText("Hét:");

        jLabel3.setText("Sor/Állomás:");

        BTN_QUERY.setText("Lekérdez");
        BTN_QUERY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_QUERYActionPerformed(evt);
            }
        });

        jLabel4.setText("Kimutatás típusa:");

        COMBO_TYPES.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terv / Tény", "%-os" }));
        COMBO_TYPES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_TYPESActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/table.png"))); // NOI18N
        jButton2.setToolTipText("Adatok megtekintése");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(COMBO_TYPES, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 467, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(COMBO_YEAR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(COMBO_LINE, 0, 86, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(COMBO_WEEK, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BTN_QUERY, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(COMBO_YEAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(COMBO_WEEK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(COMBO_LINE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_QUERY)
                    .addComponent(COMBO_TYPES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CHART_SCREEN.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout CHART_SCREENLayout = new javax.swing.GroupLayout(CHART_SCREEN);
        CHART_SCREEN.setLayout(CHART_SCREENLayout);
        CHART_SCREENLayout.setHorizontalGroup(
            CHART_SCREENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CHART_SCREENLayout.setVerticalGroup(
            CHART_SCREENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHART_SCREEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(CHART_SCREEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jScrollPane1.setViewportView(jPanel1);

        SPLIT.setTopComponent(jScrollPane1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TAB_EFF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(TAB_EFF);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/table.png"))); // NOI18N
        jButton6.setToolTipText("Adatok megtekintése");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kalkulált hatékonyság", jPanel10);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/table.png"))); // NOI18N
        jButton3.setToolTipText("Adatok megtekintése");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/bulb24.png"))); // NOI18N
        jLabel5.setText("<html>Tervezett, és megvalósult gyártások órára kalkulálva.</html>");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        TAB_PLAN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TAB_PLAN);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Gyártásterv", jPanel3);

        TAB_DOWNS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TAB_DOWNS);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/table.png"))); // NOI18N
        jButton4.setToolTipText("Adatok megtekintése");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/bulb24.png"))); // NOI18N
        jLabel6.setText("<html>Nem tervezett állások, percekbe kalkulálva.</html>");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Állások", jPanel4);

        TAB_PLAN_DOWN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(TAB_PLAN_DOWN);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/table.png"))); // NOI18N
        jButton5.setToolTipText("Adatok megtekintése");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mkvez/IMG/bulb24.png"))); // NOI18N
        jLabel7.setText("<html>Tervezett állások órába kalkulálva.</html>");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Tervezett állások", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jScrollPane2.setViewportView(jPanel2);

        SPLIT.setRightComponent(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SPLIT, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SPLIT, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setTheme()  {
        /*
         TAB_PLAN
         */
        try{
        TAB_PLAN.getTableHeader().setPreferredSize(new Dimension(jScrollPane3.getWidth(), 40));
        for (int c = 0; c < TAB_PLAN.getColumnCount(); c++) {

            if (c == 6) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Reggel</html>");
            }
            if (c == 7) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Délután</html>");
            }
            if (c == 8) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Reggel</html>");
            }
            if (c == 9) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Délután</html>");
            }
            if (c == 10) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Reggel</html>");
            }
            if (c == 11) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Délután</html>");
            }
            if (c == 12) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Reggel</html>");
            }
            if (c == 13) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Délután</html>");
            }
            if (c == 14) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Reggel</html>");
            }
            if (c == 15) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Délután</html>");
            }
            if (c == 16) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Reggel</html>");
            }
            if (c == 17) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Délután</html>");
            }
            if (c == 18) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Reggel</html>");
            }
            if (c == 19) {
                TAB_PLAN.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Délután</html>");
            }

            if (c > 5) {
                TAB_PLAN.getColumnModel().getColumn(c).setCellRenderer(new own_plan_tablecell_rendererNumeric());
            }
        }
        TAB_PLAN_DOWN.getTableHeader().setPreferredSize(new Dimension(jScrollPane4.getWidth(), 40));
        for (int c = 0; c < TAB_PLAN_DOWN.getColumnCount(); c++) {
            if (c == 0) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Állás</html>");
            }
            if (c == 1) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Reggel</html>");
            }
            if (c == 2) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Délután</html>");
            }
            if (c == 3) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Reggel</html>");
            }
            if (c == 4) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Délután</html>");
            }
            if (c == 5) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Reggel</html>");
            }
            if (c == 6) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Délután</html>");
            }
            if (c == 7) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Reggel</html>");
            }
            if (c == 8) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Délután</html>");
            }
            if (c == 9) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Reggel</html>");
            }
            if (c == 10) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Délután</html>");
            }
            if (c == 11) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Reggel</html>");
            }
            if (c == 12) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Délután</html>");
            }
            if (c == 13) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Reggel</html>");
            }
            if (c == 14) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Délután</html>");
            }
            if (c > 1) {
                TAB_PLAN_DOWN.getColumnModel().getColumn(c).setCellRenderer(new own_plan_tablecell_rendererNumeric());
            }

        }

        TAB_DOWNS.getTableHeader().setPreferredSize(new Dimension(jScrollPane4.getWidth(), 40));
        for (int c = 0; c < TAB_DOWNS.getColumnCount(); c++) {
            if (c == 1) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Állás</html>");
            }
            if (c == 2) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Javította</html>");
            }
            if (c == 3) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Reggel</html>");
            }
            if (c == 4) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Délután</html>");
            }
            if (c == 5) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Reggel</html>");
            }
            if (c == 6) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Délután</html>");
            }
            if (c == 7) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Reggel</html>");
            }
            if (c == 8) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Délután</html>");
            }
            if (c == 9) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Reggel</html>");
            }
            if (c == 10) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Délután</html>");
            }
            if (c == 11) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Reggel</html>");
            }
            if (c == 12) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Délután</html>");
            }
            if (c == 13) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Reggel</html>");
            }
            if (c == 14) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Délután</html>");
            }
            if (c == 15) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Reggel</html>");
            }
            if (c == 16) {
                TAB_DOWNS.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Délután</html>");
            }
            if (c > 2) {
                TAB_DOWNS.getColumnModel().getColumn(c).setCellRenderer(new own_plan_tablecell_rendererNumeric());
            }

        }

        TAB_EFF.getTableHeader().setPreferredSize(new Dimension(jScrollPane6.getWidth(), 40));
        for (int c = 0; c < TAB_EFF.getColumnCount(); c++) {
            if (c == 0) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Kalkulációk</html>");
            }
            if (c == 1) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Reggel</html>");
            }
            if (c == 2) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Hétfő<br>Délután</html>");
            }
            if (c == 3) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Reggel</html>");
            }
            if (c == 4) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Kedd<br>Délután</html>");
            }
            if (c == 5) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Reggel</html>");
            }
            if (c == 6) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Szerda<br>Délután</html>");
            }
            if (c == 7) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Reggel</html>");
            }
            if (c == 8) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Csütörtök<br>Délután</html>");
            }
            if (c == 9) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Reggel</html>");
            }
            if (c == 10) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Péntek<br>Délután</html>");
            }
            if (c == 11) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Reggel</html>");
            }
            if (c == 12) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Szombat<br>Délután</html>");
            }
            if (c == 13) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Reggel</html>");
            }
            if (c == 14) {
                TAB_EFF.getColumnModel().getColumn(c).setHeaderValue("<html>Vasárnap<br>Délután</html>");
            }
            if (c > 1) {
                TAB_EFF.getColumnModel().getColumn(c).setCellRenderer(new own_plan_tablecell_rendererNumeric());
            }

        }
        
        
        GraphForm.this.repaint();
        
        TAB_DOWNS.repaint();
        TAB_EFF.repaint();
        TAB_PLAN.repaint();
        TAB_PLAN_DOWN.repaint();
        }catch (ArrayIndexOutOfBoundsException e){
            
        }
        
        
    }

    private JFreeChart createChart(CategoryDataset dataset, String sor_nev) {
        JFreeChart chart = null;
        if (COMBO_TYPES.getSelectedIndex() == 0) {
            chart = ChartFactory.createBarChart(
                    "Műszakok",
                    sor_nev.toUpperCase(),
                    "óra",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );
        } else {
            chart = ChartFactory.createBarChart(
                    "Műszakok",
                    sor_nev.toUpperCase(),
                    "%",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );
        }

        CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        IntervalMarker target = new IntervalMarker(400.0, 700.0);
        target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));

        return chart;
    }

    CategoryDataset createDataset() {

        calc();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String series1 = "Terv (ó)";
        String series2 = "Tény (ó)";
        String series3 = "%";

        if (COMBO_TYPES.getSelectedIndex() == 0) {

            for (int i = 0; i < 14; i++) {
                dataset.addValue(terv[i], series1, mszak[i]);
                dataset.addValue(teny[i], series2, mszak[i]);
            }
        } else {
            for (int i = 0; i < 14; i++) {
                try {
                    dataset.addValue((teny[i] / terv[i]) * 100, series3, mszak[i]);
                } catch (Exception e) {
                    dataset.addValue(0, series1, mszak[i]);
                }
            }
        }

        return dataset;
    }

    public void drawChart() {

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, COMBO_LINE.getSelectedItem().toString());

        ChartPanel myChart = new ChartPanel(chart);
        CHART_SCREEN.removeAll();
        CHART_SCREEN.setLayout(new java.awt.BorderLayout());
        CHART_SCREEN.add(myChart, BorderLayout.CENTER);
        CHART_SCREEN.validate();

    }
    boolean BTN = false;

    private void BTN_QUERYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_QUERYActionPerformed
        if (!BTN) {
            Runnable RUN = new Runnable() {

                @Override
                public void run() {
                     SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            BTN_QUERY.setIcon(new ImageIcon(mkvez.MkVez.class.getResource("IMG/loader.gif")));

                        }
                    });
                    BTN = true;
                    TAB_PLAN.setModel(PlanningWebsiteInterface.getEfficiencyPlan(COMBO_LINE.getSelectedItem().toString(), Integer.parseInt(COMBO_YEAR.getSelectedItem().toString()), Integer.parseInt(COMBO_WEEK.getSelectedItem().toString())));
                    TAB_DOWNS.setModel(PlanningWebsiteInterface.getDownTimeProd(COMBO_LINE.getSelectedItem().toString(), Integer.parseInt(COMBO_YEAR.getSelectedItem().toString()), Integer.parseInt(COMBO_WEEK.getSelectedItem().toString())));
                    TAB_PLAN_DOWN.setModel(PlanningWebsiteInterface.getPlaningDownTimes(COMBO_LINE.getSelectedItem().toString(), Integer.parseInt(COMBO_YEAR.getSelectedItem().toString()), Integer.parseInt(COMBO_WEEK.getSelectedItem().toString())));
                    drawChart();
                    setTheme();
                    BTN = false;
                     SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            BTN_QUERY.setIcon(null);

                        }
                    });
                }
            };
            Thread T1 = new Thread(RUN);
            T1.start();
        }

    }//GEN-LAST:event_BTN_QUERYActionPerformed

    private void COMBO_TYPESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_TYPESActionPerformed
        drawChart();
    }//GEN-LAST:event_COMBO_TYPESActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (SPLIT.getDividerLocation() < 10) {
            SPLIT.setDividerLocation(9000);
        } else {
            SPLIT.setDividerLocation(0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (SPLIT.getDividerLocation() < 10) {
            SPLIT.setDividerLocation(9000);
        } else {
            SPLIT.setDividerLocation(0);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (SPLIT.getDividerLocation() < 10) {
            SPLIT.setDividerLocation(9000);
        } else {
            SPLIT.setDividerLocation(0);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (SPLIT.getDividerLocation() < 10) {
            SPLIT.setDividerLocation(9000);
        } else {
            SPLIT.setDividerLocation(0);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (SPLIT.getDividerLocation() < 10) {
            SPLIT.setDividerLocation(9000);
        } else {
            SPLIT.setDividerLocation(0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_QUERY;
    private javax.swing.JPanel CHART_SCREEN;
    private javax.swing.JComboBox COMBO_LINE;
    private javax.swing.JComboBox COMBO_TYPES;
    private javax.swing.JComboBox COMBO_WEEK;
    private javax.swing.JComboBox COMBO_YEAR;
    private javax.swing.JSplitPane SPLIT;
    private javax.swing.JTable TAB_DOWNS;
    private javax.swing.JTable TAB_EFF;
    private javax.swing.JTable TAB_PLAN;
    private javax.swing.JTable TAB_PLAN_DOWN;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
