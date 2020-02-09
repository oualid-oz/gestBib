
package dao;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import Connection.connect;

public class empruntDao {
    public static DefaultTableModel remplirEm(JTable T1){
        DefaultTableModel model1=(DefaultTableModel) T1.getModel();
        try{                    
            String te ="select * from emprunt";
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
    public static ArrayList<String> listerEm(){
        ArrayList<String> s1=new ArrayList<String>();
        try{                    
            String te ="select idL,titre from livre";
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);
            ResultSet rs = state1.executeQuery();
            while(rs.next()){
                s1.add(rs.getString("idL")+":"+rs.getString("titre"));
            }
            return s1;
        }catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
        return s1;
    }
    public static ArrayList<String> listerEm1(){
        ArrayList<String> s1=new ArrayList<String>();
        try{    
            String te1 ="select cin from etudiant";
            PreparedStatement state2 = connect.getInstance().prepareStatement(te1);
            ResultSet rs1 = state2.executeQuery();
            while(rs1.next()){
                s1.add(rs1.getString("cin"));
            }
            return s1;
        }catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
        return s1;
    }
    public static void EffectuerEm(int idL, String titre, String cin){
        try{
            int c1=-1,c2=-1;
            String count="select count(*) as awt from emprunt where cin='"+cin+"'";
            PreparedStatement state5= connect.getInstance().prepareStatement(count);
            ResultSet rs3=state5.executeQuery();
            if(rs3.first())
            c1=rs3.getInt("awt");
            String count1="select count(*) as rt from emprunt where cin='"+cin+"' and idL="+idL+" and titre='"+titre+"'";
            PreparedStatement state4= connect.getInstance().prepareStatement(count1);
            ResultSet rs2=state4.executeQuery();
            if(rs2.first())
            c2=rs2.getInt("rt");
            if(c1<3 && idL!=0){
                if(c2==0){
                    String te ="INSERT INTO emprunt(idL, titre, cin) VALUES(?,?,?)";
                    PreparedStatement state1 = connect.getInstance().prepareStatement(te);
                    state1.setInt(1, idL);
                    state1.setString(2, titre);
                    state1.setString(3, cin);
                    state1.execute();
                    String te2="update livre set stock= stock-1 where idL ="+idL;
                    PreparedStatement state3 = connect.getInstance().prepareStatement(te2);
                    state3.execute();
                    JOptionPane.showMessageDialog(null, "'"+cin+"' a emprunter => "+idL+" : '"+titre+"' :)", "SUCCES DE CONNEXION ! ", JOptionPane.YES_NO_CANCEL_OPTION);
                }else
                    JOptionPane.showMessageDialog(null, cin+" est deja emprunter ce livre", "ERREUR DE CONNEXION ! ", JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, cin+" a ete emprunter trois livre", "ERREUR DE CONNEXION ! ", JOptionPane.WARNING_MESSAGE);
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
    }
}