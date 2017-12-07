package projetumlbd;

import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue{
    
    private ArrayList<I_Produit> lesProduits;

    public Catalogue(){
        lesProduits = new ArrayList<>();
    }    
    
    @Override
    public boolean addProduit(I_Produit produit) {
        boolean verif = true;
        int i = 0;
        while(verif && i < this.lesProduits.size()){
            if(lesProduits.get(i).getNom().equals(produit.getNom()))
                verif = false;
        }
        if(verif)
            lesProduits.add(produit);
        
        return verif;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        boolean verif = true;
        int i = 0;
        while(verif && i < this.lesProduits.size()){
            if(lesProduits.get(i).getNom().equals(nom))
                verif = false;
        }
        
        if(verif){
            I_Produit p = new Produit(nom, prix, qte);
            lesProduits.add(p);
        }
        
        return verif;
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeProduit(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getNomProduits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getMontantTotalTTC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
