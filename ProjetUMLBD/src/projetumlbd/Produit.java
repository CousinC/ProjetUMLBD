package projetumlbd;

public class Produit implements I_Produit {
    
    private int quantiteStock;
    private String nom;
    private float prixUnitaireHT;
    private static float tauxTVA = 0.2f;
    
    public Produit(String nom, float prixUnitaireHT, int qte){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}