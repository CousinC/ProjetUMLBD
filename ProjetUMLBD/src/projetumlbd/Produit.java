package projetumlbd;

import java.text.NumberFormat;
import java.util.Locale;

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
        if((this.quantiteStock - qteVendue) < 0){
            return false;
        }
        else{
            this.quantiteStock -= qteVendue;
            return true;
        }
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
        double res = this.prixUnitaireHT + this.prixUnitaireHT * tauxTVA;
        return res;
    }

    @Override
    public double getPrixStockTTC() {
        double res = this.quantiteStock * this.getPrixUnitaireTTC();
        return res;
    }
    
    @Override
    public String toString(){
        String infosProduit = "";
        
        final NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        String prixUHT = instance.format(this.prixUnitaireHT);
        String prixUTTC = instance.format(this.getPrixUnitaireTTC());
        
        infosProduit = this.nom + " - prix HT : " + prixUHT + " € - prix TTC : " + prixUTTC + " € - quantité en stock : " + this.quantiteStock;
        
        return infosProduit;
    }
}