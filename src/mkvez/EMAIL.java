package mkvez;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Copyright (C) <2016> <Krisztián Csekme>
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class EMAIL {

    private int process_count = 0;
    private String cc, from, to, sub, mess;
    private File file_subject;
    private File file_from;
    private File file_to;
    private File file_cc;
    private File file_message;
    private File file_sender;
    private OutputStream os = null;
    private OutputStreamWriter out = null;
    public static String ERRORMESSAGE = "";
    public static String ERRORTITLE = "";

    private final Random gen = new Random();
    public final static byte[] UTF8_BOM = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    
    public EMAIL(){
        ERRORMESSAGE = "<html>Az e-mail elküldése nem sikerült!<br> Folyamatban van egy másik e-mail kézbesítése, pár másodperc elteltével próbálja meg ismét!</html>";
        ERRORTITLE = "Figyelem";
        cc="";
        from="";
        to="";
        sub="";
        mess="";
    }
    
    
    public void setFrom(String _from) {
        from = _from;
    }

    public void setTo(String _to) {
        to = _to;
    }

    public void setSubject(String _sub) {
        sub = _sub;
    }

    public void setMessage(String _message) {
        mess = _message;
    }

    public void addCC(String value) {

        cc = cc + value + ";";

    }

    public int send() {

        process_count = 0;

        File processfile = new File(System.getProperty("user.dir") + "\\process.txt");

        if (!processfile.exists()) {

            FileOutputStream pro=null;
            OutputStreamWriter prow=null;
            try {
                pro = new FileOutputStream(processfile);
                prow = new OutputStreamWriter(pro, "8859_2");
                prow.append("1");
                prow.flush();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                 
                try {
                    
                    prow.close();
                    pro.close();
                } catch (IOException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            try {

                //* FROM *//
                try {
                    file_from = new File(System.getProperty("user.dir") + "\\from.txt");
                    os = new FileOutputStream(file_from);
                    out = new OutputStreamWriter(os, "8859_2");
                    out.append(from);
                    out.flush();
                    out.close();
                    os.close();
                    file_from.setReadable(true);
                    process_count++;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }
                }

                //* TO *//
                try {
                    file_to = new File(System.getProperty("user.dir") + "\\to.txt");
                    os = new FileOutputStream(file_to);
                    out = new OutputStreamWriter(os, "8859_2");
                    out.append(to);
                    out.flush();
                    out.close();
                    os.close();
                    file_to.setReadable(true);
                    process_count++;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }
                }

                // * CC * //
                try {
                    file_cc = new File(System.getProperty("user.dir") + "\\cc.txt");
                    os = new FileOutputStream(file_cc);
                    out = new OutputStreamWriter(os, "8859_2");
                    out.append(cc);
                    out.flush();
                    out.close();
                    os.close();
                    file_cc.setReadable(true);
                    process_count++;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }
                }

                // * SUBJECT * //
                try {
                    file_subject = new File(System.getProperty("user.dir") + "\\subject.txt");
                    os = new FileOutputStream(file_subject);
                    out = new OutputStreamWriter(os, "8859_2");
                    out.append(sub);
                    out.flush();
                    out.close();
                    os.close();
                    file_subject.setReadable(true);
                    process_count++;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }
                }

                // * MESSAGE *//
                try {
                    file_message = new File(System.getProperty("user.dir") + "\\message.txt");
                    os = new FileOutputStream(file_message);
                    out = new OutputStreamWriter(os, "8859_2");
                    out.append(mess);
                    out.flush();
                    out.close();
                    os.close();
                    file_message.setReadable(true);
                    process_count++;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }
                }

                // * WAIT * //
                while (process_count < 5) {

                }

                file_sender = new File(System.getProperty("user.dir") + "\\mail.vbs");
                String script = file_sender.getAbsolutePath();
                String executable = "wscript.exe";
                String cmdArr[] = {executable, script, new File(System.getProperty("user.dir")).getAbsolutePath()};

                Process process = Runtime.getRuntime().exec(cmdArr);
            } catch (IOException ex) {
                Logger.getLogger(EMAIL.class.getName()).log(Level.SEVERE, null, ex);
            }
            process_count = 0;
            return 1;

        } else {
            JOptionPane.showMessageDialog(null, ERRORMESSAGE, ERRORTITLE, JOptionPane.ERROR_MESSAGE);
            return 0;
        }

    }
}
