package projetumlbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduit {
    
    private ConnexionBD co = new ConnexionBD();
    
    public boolean create(I_Produit p){
        try {
            String sql = "INSERT INTO Produits (idProduit, nomProduit, qteStock, prixUnitaireHT) VALUES (produits_Seq.NEXTVAL, ?,?,?)";
            Connection c = co.getCn();
            PreparedStatement pst = c.prepareStatement(sql);
            
            pst.setString(1, p.getNom());
            pst.setInt(2, p.getQuantite());
            pst.setDouble(3, p.getPrixUnitaireHT());
            
            return (pst.executeUpdate() != 0);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<I_Produit> findAll(){
        List<I_Produit> listeProduit = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Produits;";
            Connection c = co.getCn();
            Statement st = c.createStatement();
                  
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String nom = rs.getString(2);
                int qte = rs.getInt(3);
                double prix = rs.getDouble(4);
                
                Produit p = new Produit(nom, prix, qte);
                
                listeProduit.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeProduit;
    }
    
    public I_Produit findByName(String nomProduit){
        Produit p = null;
        try {
            String sql = "SELECT * FROM Produits "
                        + "WHERE nomProduit = ?;";
            Connection c = co.getCn();
            PreparedStatement pst = c.prepareStatement(sql);
            
            pst.setString(1, nomProduit);

            ResultSet rs = pst.executeQuery();
            
            String nom = rs.getString(2);
            int qte = rs.getInt(3);
            double prix = rs.getDouble(4);

            p = new Produit(nom, prix, qte);
                
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public boolean update(I_Produit p){
        try {
            String sql = "UPDATE Produits "
                        + "SET qteStock = ?, prixUnitaireHT = ? "
                        + "WHERE nomProduit = ?;";
            Connection c = co.getCn();
            PreparedStatement pst = c.prepareStatement(sql);
            
            pst.setInt(1, p.getQuantite());
            pst.setDouble(2, p.getPrixUnitaireHT());
            pst.setString(3, p.getNom());

            return (pst.executeUpdate() != 0);
                
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    public boolean delete(I_Produit p){
        try {
            String sql = "DELETE FROM Produits "
                        + "WHERE nomProduit = ?;";
            Connection c = co.getCn();
            PreparedStatement pst = c.prepareStatement(sql);
            
            pst.setString(1, p.getNom());

            return (pst.executeUpdate() != 0);
                
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduit.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
}