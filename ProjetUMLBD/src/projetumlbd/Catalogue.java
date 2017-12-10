package projetumlbd;

import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue{
    
    private ArrayList<I_Produit> lesProduits;

    public Catalogue(){
        lesProduits = new ArrayList<>();
    }    
    
    private boolean nomProduitDejaExistant(String nomATester){
        boolean verif = false;
        int i = 0;
        String[] noms = this.getNomProduits();
        while(verif && i < noms.length){
            if(noms[i].equals(nomATester))
                verif = true;
            i++;
        }
        
        return verif;
    }
    
    private I_Produit getProduitFromNom(String nomProduit){
        boolean verif = true;
        String[] noms = this.getNomProduits();
        int i = 0;
        while(verif && i < noms.length){
            if(noms[i].equals(nomProduit))
                verif = false;
            i++;
        }
        return lesProduits.get(i-1);
    }
    
    @Override
    public boolean addProduit(I_Produit produit) {
        boolean verif = false;

        if(!nomProduitDejaExistant(produit.getNom())){
            lesProduits.add(produit);
            verif = true;
        }
        
        return verif;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        boolean verif = false;
        
        if(!nomProduitDejaExistant(nom)){
            I_Produit p = new Produit(nom, (float) prix, qte);
            lesProduits.add(p);
            verif = true;
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
        if(nomProduitDejaExistant(nom)){
            lesProduits.remove(getProduitFromNom(nom));
            verif = true;
        }
        
        return verif;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        boolean verif = false;
        
        if(nomProduitDejaExistant(nomProduit)){
            verif = true;
        }
        
        return verif;
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
