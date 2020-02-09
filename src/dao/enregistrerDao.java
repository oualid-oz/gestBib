
package dao;

import java.io.FileWriter;
import javax.swing.*;
import java.sql.*;
import Connection.connect;

public class enregistrerDao {
    public static void enregistrer(String path,String table){
        try {
            FileWriter fw = new FileWriter(path + "/"+table+".csv");
            String query="SELECT * FROM "+table;
            Statement st = connect.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            int cols = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= cols; i ++){
               fw.append(rs.getMetaData().getColumnLabel(i));
               if(i < cols) fw.append(',');
               else fw.append('\n');
            }
            while (rs.next()) {
               for(int i = 1; i <= cols; i ++){
                   fw.append(rs.getString(i));
                   if(i < cols) fw.append(',');
               }
               fw.append('\n');
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "The file was saved in "+path + "/"+table+".csv :)", "SUCCES DE CONNEXION ! ", JOptionPane.YES_NO_CANCEL_OPTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "~Tu n'a pas preciser le chemin de votre fichier :(", "ATTENTION!", JOptionPane.WARNING_MESSAGE);
        }
    }
}