package Models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

    private static final String URL = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private static final String LOGIN = "cousinc";
    private static final String PASSWORD = "joyeux";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static Connection cn = null;

    private ConnexionBD(){
    }

    public static Connection getConnexion(){
        if(cn == null){
            try {
                Class.forName(DRIVER);
                cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
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
        return cn;
    }

    public static void deconnexion(){
        try {
            ConnexionBD.cn.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally{
            if(ConnexionBD.cn != null){
                try{
                    cn.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
