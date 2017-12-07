package projetumlbd;

public class Produit implements I_Produit {
    
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private static double tauxTVA = 0.2d;
    
    public Produit(String nom, double prixUnitaireHT, int qte){
        this.nom = nom;
        this.prixUnitaireHT = prixUnitaireHT;
        this.quantiteStock += qte;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        this.quantiteStock += qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if((this.quantiteStock -= qteVendue) < 0){
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
        return this.prixUnitaireHT * tauxTVA;
    }

    @Override
    public double getPrixStockTTC() {
        return this.quantiteStock * this.getPrixUnitaireTTC();
    }
    
    @Override
    public String toString(){
        String infosProduit = "";
        infosProduit.concat(this.nom + "  prix HT : " + this.prixUnitaireHT + " prix TTC : " + this.getPrixUnitaireTTC() + "   quantité en stock : " + this.quantiteStock);
        return infosProduit;
    }
}