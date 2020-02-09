package dao;
import Connection.connect;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class remettreDao {
    public static DefaultTableModel remplirR(JTable T1){
        DefaultTableModel model1=(DefaultTableModel) T1.getModel();
        try{                    
            String te ="select * from remettre";
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
    public static void EffectuerR(bean.remettre r){
        try{
            String idS="select idL from emprunt where id="+r.getId();
            PreparedStatement st=connect.getInstance().prepareStatement(idS);
            ResultSet rs=st.executeQuery();
            rs.first();
            int idL=rs.getInt("idL");
            String te ="INSERT INTO remettre(idL, titre, cin) VALUES(?,?,?)";
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);
            state1.setInt(1, idL);
            state1.setString(2, r.getTitre());
            state1.setString(3, r.getCin());
            state1.executeUpdate();
            String te3="delete from emprunt where id="+r.getId();
            PreparedStatement state2 = connect.getInstance().prepareStatement(te3);
            state2.executeUpdate();
            String te2="update livre set stock = stock +1 where idL="+idL;
            PreparedStatement state=connect.getInstance().prepareStatement(te2);
            state.executeUpdate();
            JOptionPane.showMessageDialog(null, "Your operation was done :)", "SUCCES", JOptionPane.YES_NO_CANCEL_OPTION);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);                 
        }
    }
    public static ArrayList<String> listerR(){
        ArrayList<String> s1=new ArrayList<String>();
        try{
            String te1 ="select distinct cin from emprunt";
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
    public static ArrayList<String> listerR1(String cin){
        ArrayList<String> s1=new ArrayList<String>();
        try{                    
            String te ="select id,titre from emprunt where cin ='"+cin+"'";
            PreparedStatement state1 = connect.getInstance().prepareStatement(te);
            ResultSet rs = state1.executeQuery();
            while(rs.next()){
                s1.add(rs.getString("id")+":"+rs.getString("titre"));
            }
            return s1;
        }catch(SQLException ef){
            JOptionPane.showMessageDialog(null, ef.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
        }
        return s1;
    }
}
