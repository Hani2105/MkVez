/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author krisztian_csekme1
 */
public class MySQL {

    private String Database_name;
    private String Host;
    private String Port;
    private String User;
    private String Pass;

    public MySQL(String host, String port, String user, String pass, String db) {
        Host = host;
        Port = port;
        User = user;
        Pass = pass;
        Database_name = db;
    }

    public void insertCommand(String command) {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");

            if (Port.equals("localhost")) {
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            }

            Statement st;

            st = con.createStatement();
            st.executeUpdate(command);

            st.close();
            con.close();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage().toString());
        }
    }

    

    public boolean isDBAlive() {
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
             
            if (Port.equals("localhost")) {
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");

            }

            return true;

        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        } catch (InstantiationException ex) {
           return false;
        } catch (IllegalAccessException ex) {
           return false;
        }

    }

     

    public Object getCellValue(String query) {
        Object res = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            
            if (Port.equals("localhost")) {
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            }
            Statement stmc = con.createStatement();
            stmc.executeQuery(query);
            ResultSet rs = stmc.getResultSet();
            if (rs!=null){
            rs.beforeFirst();
            rs.next();
            res = rs.getObject(1);
            }
            con.close();
       
        
        
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {

            return -1;
        }

        return res;
    }
    
    
    public Object[] getRecord(String query) {
        Object[] adatok = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            
            if (Port.equals("localhost")) {
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            }

            Statement stmc = con.createStatement();
            stmc.executeQuery(query);
            ResultSet rs = stmc.getResultSet();
            ResultSetMetaData rsMetaData = rs.getMetaData();
           

            adatok = new Object[rsMetaData.getColumnCount()];

                while (rs.next()) {
                    
                    for (int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
                        adatok[i - 1] = (rs.getString(i));

                    }
                    

                }
          

            con.close();
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            
        }

        return adatok;
    }
    
    
    
    

    public DefaultTableModel getDataTableModel(String query) {
        DefaultTableModel adatok = new DefaultTableModel();
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            
            if (Port.equals("localhost")) {
                con.close();
                con = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + Database_name + "?user=" + User + "&password=" + Pass + "&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
            }

            Statement stmc = con.createStatement();
            stmc.executeQuery(query);
            ResultSet rs = stmc.getResultSet();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            Vector<String> head = new Vector<String>();

            for (int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
                head.add(rsMetaData.getColumnName(i));
            }

            adatok = new DefaultTableModel(head, 0);

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            rs.beforeFirst();

            if (rows > 0) {
                while (rs.next()) {
                    Object[] adatsor = new Object[rsMetaData.getColumnCount()];

                    for (int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
                        adatsor[i - 1] = (rs.getString(i));

                    }
                    adatok.addRow(adatsor);

                }
            }

            rs.beforeFirst();
            con.close();
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            
        }

        return adatok;
    }

}
