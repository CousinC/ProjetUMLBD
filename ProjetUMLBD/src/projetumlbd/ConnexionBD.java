package projetumlbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {
    
    private Statement st;
    private ResultSet rs;
    private Connection cn;
    
    public ConnexionBD(){
        
        String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
        String login = "cousinc";
        String mdp = "joyeux";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.cn = DriverManager.getConnection(url, login, mdp);
            //this.st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } 
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } 
        catch (SQLException ex) {
            deconnexion();
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void deconnexion(){
        try {
            this.st.close();
            this.rs.close();
            this.getCn().close();
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally{
            if(getCn() != null){
                try{
                    getCn().close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public Connection getCn() {
        return cn;
    }
}
