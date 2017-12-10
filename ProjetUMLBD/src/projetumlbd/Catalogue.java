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
            if(getProduitFromNom(nomProduit).ajouter(qteAchetee));
                verif = true;
        }
        
        return verif;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        boolean verif = false;
        
        if(nomProduitDejaExistant(nomProduit)){
            if(getProduitFromNom(nomProduit).enlever(qteVendue));
                verif = true;
        }
        
        return verif;
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
        double montant = 0;
        for(I_Produit produit : lesProduits){
            montant += produit.getPrixStockTTC();
        }
        
        return montant;
    }

    @Override
    public void clear() {
        lesProduits = new ArrayList<>();
    }
    
    @Override
    public String toString(){
        String afficher = "";
        for(I_Produit produit : lesProduits){
            afficher += produit.toString() + System.getProperty("line.separator");
        }
        
        afficher += System.getProperty("line.separator") +
                    "Montant total TTC du stock " + getMontantTotalTTC() + "?";
        
        return afficher;
    }
    
}
