/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.joda.time.DateTime;

/**
 *
 * @author krisztian_csekme1
 */
public class PlanningWebsiteInterface {

    public static DefaultTableModel getSQLFile(String filename) {

        BufferedReader br = null;
        String query = null;

        try {
            br = new BufferedReader(new FileReader("sql\\" + filename));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            query = sb.toString();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlanForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlanForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(PlanForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return MkVez.MYDB_DB.getDataTableModel(query);

    }

    public static DefaultTableModel getPlaningDownTimes(String smtline, int year, int week) {

        DefaultTableModel res = null;
        String query = null;

        String sdate = MkVez.formatDate(MkVez.getFirstMondayDayOfWeek(year, week).plusHours(5));

        Object value = MkVez.MYDB_DB.getCellValue("SELECT query from storedquerys WHERE name='Allasok planning'");
        if (value != null) {

            query = value.toString();

            query = query.replace("@muszak_kezdes", "'" + sdate + "'");
            query = query.replace("@sor", "'" + smtline + "'");
            query = query.replace("@muszak_befejezes", "'" + MkVez.formatDate(MkVez.getFirstMondayDayOfWeek(year, week).plusHours(5).plusHours(12)) + "'");
            res = MkVez.MYDB_DB.getDataTableModel(query);
        }

        return res;

    }

    //
    public static DefaultTableModel getPlan(String smtline, int year, int week, Object type) {

        DefaultTableModel res = null;

        String query = null;

        String sdate = MkVez.formatDate(MkVez.getFirstMondayDayOfWeek(year, week).plusHours(5));

        Object value = null;
        if (MkVez.TESTMODE == 0) {
            if (type.equals("SMT")){
            value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Plan Query'");
            }else{
              value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Plan Query BE'");
            }
            
        } else {
            value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Plan Query test'");
        }
        if (value != null) {

            query = value.toString();
            query = query.replace("@report_date", "'" + sdate + "'");
            query = query.replace("@sor", "'" + smtline + "'");

            res = MkVez.MYDB_DB.getDataTableModel(query);
        }

        return res;

    }

    public static DefaultTableModel getDownTimeProd(String smtline, int year, int week) {

        DefaultTableModel res = null;
        String query = null;

        String sdate = MkVez.formatDate(MkVez.getFirstMondayDayOfWeek(year, week).plusHours(5));

        Object value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Allasok'");

        if (value != null) {
            query = value.toString();
            query = query.replace("@report_date", "'" + sdate + "'");
            query = query.replace("@sor", "'" + smtline + "'");
            res = MkVez.MYDB_DB.getDataTableModel(query);
        }

        return res;

    }

    public static DefaultTableModel getEfficiencyPlan(String smtline, int year, int week) {

        DefaultTableModel res = null;
        String query = null;

        String sdate = MkVez.formatDate(MkVez.getFirstMondayDayOfWeek(year, week).plusHours(5));

        Object value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Efficiency Plan'");
        if (value != null) {
            query = value.toString();
            query = query.replace("@report_date", "'" + sdate + "'");
            query = query.replace("@sor", "'" + smtline + "'");
            res = MkVez.MYDB_DB.getDataTableModel(query);
        }

        return res;

    }

    public static DefaultTableModel getDownTimes(String smtline, DateTime from, DateTime to) {

        DefaultTableModel res = null;
        String query = null;

        String from_d = MkVez.formatDate(from);
        String to_d = MkVez.formatDate(to);
        Object value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Downtime'");

        if (value != null) {
            query = value.toString();
            query = query.replace("@_from", "'" + from_d + "'");
            query = query.replace("@_to", "'" + to_d + "'");
            query = query.replace("@line", "'" + smtline + "'");
            res = MkVez.MYDB_DB.getDataTableModel(query);

        }
        return res;

    }

    public static DefaultTableModel getRiport(String smtline, DateTime date) {

        DefaultTableModel res = null;
        String query = null;

        String datestring = MkVez.formatDate(date);

        Object value = MkVez.MYDB_DB.getCellValue("SELECT query FROM storedquerys WHERE name='Riport'");

        if (value != null) {
            query = value.toString();
            query = query.replace("@datum", "'" + datestring + "'");
            query = query.replace("@line", "'" + smtline + "'");
            res = MkVez.MYDB_DB.getDataTableModel(query);
        }
        return res;

    }

}
