
package dao;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Connection.connect;
import java.util.*;

public class etudiantDao {
    public static DefaultTableModel listerE(JTable T2){
        DefaultTableModel model1=(DefaultTableModel) T2.getModel();
        try{                    
            String te ="select * from etudiant";
            Statement stm= connect.getInstance().createStatement();
            ResultSet rs=stm.executeQuery(te);
            ResultSetMetaData rmsd = rs.getMetaData();
            int c=rmsd.getColumnCount();
            Vector row=new Vector();
            model1.setRowCount(0);
            while(rs.next()){
                row=new Vector(c);
                row.add(rs.getString(c));
                for(int i=1;i<c;i++){
                    row.add(rs.getString(i));
                }
                model1.addRow(row);
            }
            return model1;
        } catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
        return model1;
    }
    public static void ajouterE(bean.etudiant e){
        try{
            String te ="INSERT INTO etudiant(nom, prenom,filiere ,cin) VALUES(?,?,?,?)";
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);
            state1.setString(1, e.getNom());
            state1.setString(2, e.getPrenom());
            state1.setString(3, e.getFiliere());
            state1.setString(4, e.getCin());
            state1.execute();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, /*"cin='"+e.getCin()+"' existe deja dans notre base de donnee :("*/ex.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);                 
        }
        
    }
    public static void modifierE(bean.etudiant e){
        try{
            String sql="SELECT cin from etudiant";
            PreparedStatement state= connect.getInstance().prepareStatement(sql);
            ResultSet rs=state.executeQuery();
            boolean bool=true;
            while(rs.next()){
                if(rs.getString("cin")== e.getCin()){
                    String te ="update  etudiant set nom=?, prenom=?, filiere=?, cin=? where cin = ? ";
                    PreparedStatement state1 = connect.getInstance().prepareStatement(te);        
                    state1.setString(1, e.getNom());
                    state1.setString(2, e.getPrenom());
                    state1.setString(3, e.getFiliere());
                    state1.setString(4, e.getCin());
                    state1.setString(5, e.getCin());
                    state1.execute();
                    bool=false;
                }
            }
            if(bool){
                JOptionPane.showMessageDialog(null, "Desole cin=\""+e.getCin()+"\" n'exite pas dans notre base de donnee", "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void supprimerE(String cin){
        try{                    
            String te ="delete from etudiant where cin='"+cin+"'";
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);
            state1.execute();
        } catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        } 
    }
}