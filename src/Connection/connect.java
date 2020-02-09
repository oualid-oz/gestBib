package Connection;
import java.sql.*;
import javax.swing.JOptionPane;
public class connect {
    Connection con;
    private static String local="jdbc:mysql://localhost:3306/bibliotheque";
    private  static  Connection connec;
    public static Connection getInstance(){
        if(connec==null){
            try{
                connec = DriverManager.getConnection(local,"root","");
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connec;
        }
    Connection obtenirconnection(){
        return con;}
}