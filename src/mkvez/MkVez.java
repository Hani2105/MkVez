/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkvez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.ini4j.Wini;
import org.joda.time.DateTime;

/**
 *
 * @author krisztian_csekme1
 */
public class MkVez {

    public static MainWindow MW;
    public static MicrosoftSQL MESR = new MicrosoftSQL();
    public static GraphForm GRAPHFORM;
    public static AddressForm MAILFORM;
    public static SFDC_ROUTE_WINDOW SRW;
    
    public static List<PlanForm> PLANS = new ArrayList<>();
    public static MySQL MYDB_DB = new MySQL("143.116.140.114", "3306", "plan", "plan500", "planningdb");
    public static String[] LINE_NAMES = null;
    public static String[] ALLAS_KATEGORIA = null;
    public static String[] NAP_NEVEK = null;
    public static String[] MUSZAK_NEVEK = null;

    /*
     USER INFO
     */
    public static String NAME;
    public static String EMAIL;
    public static String PASS;
    public static String ID;

    public static String VERSION = "1.0529";

    public static int DROP_DELAY = 1000;
    public static boolean ACCESS;
    public static boolean GEST;

    

    public static int TESTMODE=0;
    public static String PLANTABLENAME="terv";
    public static String PLANTABLENAME_TESTMODE="tervtest";
    public static WaitWindow ww;
    
    
    
    /**
     * @param args the command line arguments
     */
    private static final ActionListener DRUN = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            File DROP = new File("drop.out");

            if (DROP.exists()) {
                if (!MkVez.GEST) {
                    for (PlanForm elem : MkVez.PLANS) {
                        if (elem.RIPORT.getText().length() > 0) {
                            elem.saveComment();
                        }
                    }

                }
                System.out.println("Azonnali kilépés!");
                System.exit(0);
            }
        }
    };

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {

        if (TESTMODE==1){
            PLANTABLENAME=PLANTABLENAME_TESTMODE;
        }
        
        
        File DROP = new File("drop.out");

        if (DROP.exists()) {
            System.out.println("Azonnali kilépés!");
            System.exit(0);
        }

        ToolTipManager.sharedInstance().setInitialDelay(0);

        Wini ini = null;

        try {
            ini = new Wini(new File("setup.ini"));

            if (ini.get("SETUP", "tervezett_sorok") != null) {
                LINE_NAMES = ini.get("SETUP", "tervezett_sorok").split(",");
            }

            if (ini.get("SETUP", "allas_kategoriak") != null) {
                ALLAS_KATEGORIA = ini.get("SETUP", "allas_kategoriak").split(",");
            }

            if (ini.get("SETUP", "napok") != null) {
                NAP_NEVEK = ini.get("SETUP", "napok").split(",");
            }
            if (ini.get("SETUP", "muszakok") != null) {
                MUSZAK_NEVEK = ini.get("SETUP", "muszakok").split(",");
            }

            if (ini.get("SETUP", "drop_delay") != null) {
                DROP_DELAY = Integer.parseInt(ini.get("SETUP", "drop_delay"));
            }

        } catch (IOException ex) {
            Logger.getLogger(MkVez.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MkVez.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MkVez.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MkVez.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MkVez.class.getName()).log(Level.SEVERE, null, ex);
        }

        Timer timer = new Timer(DROP_DELAY, DRUN);
        timer.start();

        new LoginScreen().setVisible(true);

        while (!ACCESS) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(MkVez.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SRW = new SFDC_ROUTE_WINDOW();
        GRAPHFORM = new GraphForm();
        MW = new MainWindow();
        MW.setVisible(true);
        ww = new WaitWindow();
    }

    public static DateTime getDateTimeFromSQLDateString(String sqlstring) {

        DateTime DT = new DateTime();

        try {
            String[] spl = sqlstring.split(" ");
            String[] dt = spl[0].split("-");
            String[] tm = spl[1].split(":");

            int YEAR = Integer.parseInt(dt[0]);
            int MONTH = Integer.parseInt(dt[1]);
            int DAY = Integer.parseInt(dt[2]);

            int HOUR = Integer.parseInt(tm[0]);
            int MIN = Integer.parseInt(tm[1]);
            int SEC = 0;

            DT = new DateTime(YEAR, MONTH, DAY, HOUR, MIN, SEC);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return DT;
    }

    public static DateTime getFirstMondayDayOfWeek(int year, int week) {
        DateTime start = new DateTime(year, 1, 1, 1, 0);
        for (int el = 0; el < 7; el++) {
            if (start.plusDays(el).getDayOfWeek() == 1) {
                start = start.plusDays(el);
                break;
            }
        }

        if (start.getDayOfYear() > 4) {
            start = start.minusWeeks(1);
        }

        start = start.plusWeeks(week - 1);

        return start;
    }

    public static String formatDate(DateTime date) {
        return Integer.toString(date.getYear()) + "-" + String.format("%02d", date.getMonthOfYear()) + "-" + String.format("%02d", date.getDayOfMonth()) + " " + String.format("%02d", date.getHourOfDay()) + ":" + String.format("%02d", date.getMinuteOfHour()) + ":" + String.format("%02d", date.getSecondOfMinute());
    }

}
