package Models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

    private final String URL = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private final String LOGIN = "cousinc";
    private final String PASSWORD = "joyeux";
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static ConnexionBD instance;
    private Connection cn = null;

    private ConnexionBD(){
    }

    public static ConnexionBD getInstance(){
        if(instance == null){
            instance = new ConnexionBD();
        }
        return instance;
    }

    public Connection getConnexion(){
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

    public void deconnexion(){
        try {
            cn.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally{
            if(cn != null){
                try{
                    cn.close();
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
