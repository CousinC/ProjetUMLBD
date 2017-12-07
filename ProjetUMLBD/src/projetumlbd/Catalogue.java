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
        String[] noms = this.getNomProduits();
        while(verif && i < noms.length){
            if(noms[i].equals(produit.getNom()))
                verif = false;
            i++;
        }
        if(verif)
            lesProduits.add(produit);
        
        return verif;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        boolean verif = true;
        int i = 0;
        String[] noms = this.getNomProduits();
        while(verif && i < noms.length){
            if(noms[i].equals(nom))
                verif = false;
            i++;
        }
        
        if(verif){
            I_Produit p = new Produit(nom, prix, qte);
            lesProduits.add(p);
        }
        
        return verif;
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int c = 0;
        for(int i = 0 ; i < l.size() ; i++){
            if(this.addProduit(l.get(i))){
                c += 1;
            }
        }
        return c;
    }

    @Override
    public boolean removeProduit(String nom) {
        boolean verif = false;
        String[] noms = this.getNomProduits();
        int i = 0;
        while(verif && i < noms.length){
            if(noms[i].equals(nom))
                verif = true;
            i++;
        }
        
        if(verif)
            lesProduits.remove(lesProduits.get(i-1));
        
        return verif;
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
        String[] noms = new String[lesProduits.size()];
        for (int i = 0; i < lesProduits.size(); i++) {
            noms[i] = lesProduits.get(i).getNom();            
        }
        
        return noms;
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
