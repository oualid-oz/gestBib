
package dao;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Connection.connect;

public class livreDao {
    
    public static DefaultTableModel listerL(JTable T1){
        DefaultTableModel model1=(DefaultTableModel) T1.getModel();
        try{                    
            String te ="select * from livre";
            Statement stm= connect.getInstance().createStatement();
            ResultSet rs=stm.executeQuery(te);
            ResultSetMetaData rmsd = rs.getMetaData();
            int c=rmsd.getColumnCount();
            Vector row=new Vector();
            model1.setRowCount(0);
            while(rs.next()){
                row=new Vector(c);
                for(int i=1;i<=c;i++){
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
    public static void supplyL(int stock,int id){
        try{
            if(stock<0){
                JOptionPane.showMessageDialog(null, "Le nombre de stock doit etre positive !!", "ERREUR !", JOptionPane.ERROR_MESSAGE);
            }
            else{
                System.out.println("update livre set stock= stock+"+stock+" where idL ="+id);
                String te ="update livre set stock= stock+"+stock+" where idL ="+id;
                PreparedStatement state1 = connect.getInstance().prepareStatement(te);
                state1.execute();
            }
        } catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void ajouterL(bean.livre l){
        try{
            String rq="select idL,titre,edition,date from livre where titre='"+l.getTitre()+"' and date='"+l.getDate()+"' and edition="+l.getEdition();
            PreparedStatement state = connect.getInstance().prepareStatement(rq);
            ResultSet rs=state.executeQuery();
            boolean bool=true;
            if(rs.first()){
                dao.livreDao.supplyL(l.getStock(), rs.getInt("idL"));
                bool=false;
            }
            if(bool==true && l.getStock()>=0){
                String te ="INSERT INTO livre(titre, date, edition, stock) VALUES(?,?,?,?)";
                PreparedStatement state1 = connect.getInstance().prepareStatement(te);
                state1.setString(1, l.getTitre());
                state1.setString(2, l.getDate());
                state1.setInt(3, l.getEdition());
                state1.setInt(4, l.getStock());
                state1.execute();
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void modifierL(bean.livre l, int idL){
        try{
            String te ="update  livre set titre=?, date=?, edition=?, stock=? where idL ="+idL;
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);        
            state1.setString(1, l.getTitre());
            state1.setString(2, l.getDate());
            state1.setInt(3, l.getEdition());
            state1.setInt(4, l.getStock());
            state1.execute();
        } catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void supprimerL(int id){
        try{
            String te ="delete from livre where idL="+id;
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);
            state1.execute();
        } catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        } 
    }
}
