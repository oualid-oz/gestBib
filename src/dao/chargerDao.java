
package dao;

import java.io.*;
import java.sql.*;
import java.nio.file.*;
import java.util.logging.*;
import java.util.stream.Stream;
import java.util.function.Function;
import javax.swing.JOptionPane;
import Connection.connect;

public class chargerDao {
    public static void charger(String path,String tab){
        Function<String, String []> fl = (s)->s.split(",");
        try{
            Stream<String> strm=Files.lines(Paths.get(path));
            strm.map(fl).forEach(e->{
                String s="";
                switch(tab){
                case "etudiant":
                    s = "insert into etudiant values('"+e[0]+"','"+e[1]+"','"+e[2]+"','"+e[3]+"')";
                    break;
                case "livre":
                    s = "insert into livre values("+e[0]+",'"+e[1]+"',"+e[2]+",'"+e[3]+"',"+e[4]+")";
                    break;
                }
                try{
                    Statement state=connect.getInstance().prepareStatement(s);
                    state.executeUpdate(s);
                }catch (SQLException ex) {
                    Logger.getLogger(chargerDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            JOptionPane.showMessageDialog(null, "Votre insecrtion des information bien effectuer :)", "SUCCES !", JOptionPane.YES_NO_CANCEL_OPTION);
        }catch (IOException ex) {
            Logger.getLogger(chargerDao.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "~Tu n'a pas preciser le chemin de votre fichier :(" , "ATTENTION!", JOptionPane.WARNING_MESSAGE);
        }
    }
}