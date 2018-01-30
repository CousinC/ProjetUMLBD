package Models;

import java.text.NumberFormat;

public class Produit implements I_Produit {
    
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private static double tauxTVA = 0.2d;
    
    public Produit(String nom, double prixUnitaireHT, int qte){
        this.nom = nom.trim();
        this.nom = this.nom.replaceAll("\t", " ");
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock = qte;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        this.quantiteStock += qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        boolean ventePossible = true;
        if((this.quantiteStock - qteVendue) < 0){
            ventePossible = false;
        }
        else{
            this.quantiteStock -= qteVendue;
        }
        return ventePossible;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public int getQuantite() {
        return this.quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return this.prixUnitaireHT;
    }

    @Override
    public double getPrixUnitaireTTC() {
        return this.prixUnitaireHT + this.prixUnitaireHT * tauxTVA;
    }

    @Override
    public double getPrixStockTTC() {
        return this.quantiteStock * this.getPrixUnitaireTTC();
    }
    
    @Override
    public String toString(){

        String infosProduit = "";

        // Permet de gérer les formats et arrondis des prix du produit
        final NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        String prixUHT = instance.format(this.prixUnitaireHT);
        String prixUTTC = instance.format(this.getPrixUnitaireTTC());
        
        infosProduit = this.nom + " - prix HT : " + prixUHT + " € - prix TTC : " + prixUTTC + " € - quantité en stock : " + this.quantiteStock;
        
        return infosProduit;
    }
}