package projetumlbd;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue{
    
    private ArrayList<I_Produit> lesProduits;

    public Catalogue(){
        lesProduits = new ArrayList<>();
    }  
    
    public ArrayList<I_Produit> getLesProduits() {
        return lesProduits;
    }
    
    private boolean nomProduitDejaExistant(String nomATester){
        boolean verif = false;
        int i = 0;
        String[] noms = this.getNomProduits();
        while(!verif && i < noms.length){
            if(noms[i].equals(nomATester))
                verif = true;
            i++;
        }
        
        return verif;
    }
    
    private I_Produit getProduitFromNom(String nomProduit){
        boolean trouve = false;
        I_Produit p = null;
        int i = 0;
        while(!trouve && i < lesProduits.size()){
            if(lesProduits.get(i).getNom().equals(nomProduit)){
                trouve = true;
                p = lesProduits.get(i);
            }
            i++;
        }
        return p;
    }
    
    @Override
    public boolean addProduit(I_Produit produit) {
        boolean verif = false; 

        if(produit != null && !nomProduitDejaExistant(produit.getNom()) && produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0){
            getLesProduits().add(produit);
            verif = true;
        }
        
        return verif;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        boolean verif = false;
        
       
        I_Produit p = new Produit(nom, prix, qte);
        if(addProduit(p)){
            verif = true;
        } 
        
        return verif;
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int c = 0;
        if(l != null){
            for(int i = 0 ; i < l.size() ; i++){
                if(this.addProduit(l.get(i))){
                    c += 1;
                }
            }
        }
        
        return c;
    }

    @Override
    public boolean removeProduit(String nom) {
        boolean verif = false;
        if(nomProduitDejaExistant(nom)){
            getLesProduits().remove(getProduitFromNom(nom));
            verif = true;
        }
        
        return verif;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        boolean verif = false;
        
        if(nomProduitDejaExistant(nomProduit) && qteAchetee > 0){
            if(getProduitFromNom(nomProduit).ajouter(qteAchetee));
                verif = true;
        }
        
        return verif;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        boolean verif = false;
        
        if(nomProduitDejaExistant(nomProduit) && qteVendue > 0){
            I_Produit p = getProduitFromNom(nomProduit);
            if(p.enlever(qteVendue))
                verif = true;
        }
        
        return verif;
    }

    @Override
    public String[] getNomProduits() {
        String[] noms = new String[getLesProduits().size()];
        for (int i = 0; i < getLesProduits().size(); i++) {
            noms[i] = getLesProduits().get(i).getNom();            
        }
        Arrays.sort(noms);
        
        return noms;
    }

    @Override
    public double getMontantTotalTTC() {
        double montant = 0;
        for(I_Produit produit : getLesProduits()){
            montant += produit.getPrixStockTTC();
        }
        montant = (double)Math.round(montant * 100) / 100;
        return montant;
    }

    @Override
    public void clear() {
        getLesProduits().clear();
    }
    
    @Override
    public String toString(){
        String afficher = new String();
        for(I_Produit produit : getLesProduits()){
            afficher += produit.toString() + "\n";
        }
        
        final NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(2);
        String montantTotalTTC =  instance.format(getMontantTotalTTC());    
        
        afficher += "\n" +
                    "Montant total TTC du stock : " + montantTotalTTC + " €";
        
        return afficher;
    }
    
}
