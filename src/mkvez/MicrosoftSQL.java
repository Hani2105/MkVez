/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author krisztian_csekme
 */



    




public class MicrosoftSQL {

    private String user = "MESR_ReadOnly";
    private String pass = "MESR_ReadOnly";
    private String connectionString = "jdbc:sqlserver://BUD1EUMESW01";

   

    public static String default_query="SELECT  LOC.Workstation ,LOC.process_name ,PA.Part_Number ,SSA.[Pass] ,SO.Shop_Order_Number ,SUM(SSA.[Passed_qty]) SUMPassQty  ,SSA.[move_qty] ,SSA.[manual_move_qty]\n" +
"\n" +
"  FROM [MesR].[dbo].[Sum_Shift_Activity] SSA\n" +
"  INNER JOIN mesr.dbo.part PA ON PA.Pk_Id = SSA.part_pk_id\n" +
"  INNER JOIN mesr.dbo.Location LOC ON LOC.Pk_Id = SSA.Location_pk_id\n" +
"  LEFT JOIN mesr.dbo.Shop_Order SO ON SO.Pk_Id = SSA.Shop_Order_pk_id\n" +
"   where  SSA.dttmH_sql between 'DATE_FROM' and 'DATE_TO'\n" +
"    GROUP BY       LOC.Workstation\n" +
"      ,PA.Part_Number,\n" +
"	  LOC.process_name\n" +
"      ,SSA.[Pass]\n" +
"      ,SO.Shop_Order_Number\n" +
"      ,SSA.[move_qty]\n" +
"      ,SSA.[manual_move_qty]";

        

        public DefaultTableModel getMeshWebDataWithQuery(String query_string) {
          DefaultTableModel adatok = new DefaultTableModel(new String[]{"Workstation","Process","Partnumber","Pass","ShopOrderNumber","SumPassQTY","MoveQTY","ManualMoveQTY"},0);
          
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(connectionString, user, pass);
                System.out.println("Kapcsolat létrehozva");

                Statement statement = conn.createStatement();
                
                
                
                String queryString = query_string;
                ResultSet rs = statement.executeQuery(queryString);
                

                while (rs.next()) {
                    Object[] adatsor = new Object[7];
                    for (int i=0; i<adatsor.length; i++){
                        adatsor[i]=rs.getString(i+1);
                    }
                    adatok.addRow(adatsor);
                }
                
                
                statement.close();
                conn.close();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return adatok;
        }
        
        
           public DefaultTableModel getMeshWebDataWithQueryBE(String query_string) {
          DefaultTableModel adatok = new DefaultTableModel(new String[]{"Partnumber", "Qty"},0);
          
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(connectionString, user, pass);
                System.out.println("Kapcsolat létrehozva");

                Statement statement = conn.createStatement();
                
                
                
                String queryString = query_string;
                ResultSet rs = statement.executeQuery(queryString);
                

                while (rs.next()) {
                    Object[] adatsor = new Object[2];
                    for (int i=0; i<adatsor.length; i++){
                        adatsor[i]=rs.getString(i+1);
                    }
                    adatok.addRow(adatsor);
                }
                
                
                statement.close();
                conn.close();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return adatok;
        } 
    
        

    
}
