package Models;

import Models.DAO.DAOProduit.DAOProduitFactory;
import Models.DAO.DAOProduit.I_DAOProduit;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue {
    
    private ArrayList<I_Produit> lesProduits;
    private I_DAOProduit daoProduit;

    public Catalogue(){
        // Création du lien avec la base de données
        DAOProduitFactory daoProduitFactory = DAOProduitFactory.getInstance();
        daoProduit = daoProduitFactory.creerDAOProduit("SQL");

        lesProduits = new ArrayList<>();
        lesProduits.addAll(daoProduit.findAll());
    }  
    
    public ArrayList<I_Produit> getLesProduits() {
        return lesProduits;
    }
    
    private boolean nomProduitDejaExistant(String nomATester){

        boolean produitExiste = false;
        int compteur = 0;
        String[] nomsDesProduitsExistants = this.getNomProduits();

        while(!produitExiste && compteur < nomsDesProduitsExistants.length){
            if(nomsDesProduitsExistants[compteur].equals(nomATester))
                produitExiste = true;
            compteur++;
        }
        
        return produitExiste;
    }
    
    private I_Produit getProduitFromNom(String nomProduit){

        boolean produitExiste = false;
        I_Produit produit = null;
        int compteur = 0;
        String nomProduitCourant;

        while(!produitExiste && compteur < lesProduits.size()){
            nomProduitCourant = lesProduits.get(compteur).getNom();
            if(nomProduitCourant.equals(nomProduit)){
                produitExiste = true;
                produit = lesProduits.get(compteur);
            }
            compteur++;
        }

        return produit;
    }
    
    @Override
    public boolean addProduit(I_Produit produit) {

        boolean reussiteAjoutProduit = false;

        if(produit != null){
            boolean prixUnitaireHTEstSuperieurAZero =  produit.getPrixUnitaireHT() > 0;
            boolean quantiteDuProduitEstPositive = produit.getQuantite() >= 0;
            boolean produitNExistePasDeja = !nomProduitDejaExistant(produit.getNom());
            boolean produitPeutEtreAjoute = prixUnitaireHTEstSuperieurAZero && quantiteDuProduitEstPositive
                    && produitNExistePasDeja;

            if(produitPeutEtreAjoute){
                lesProduits.add(produit);
                daoProduit.create(produit);
                reussiteAjoutProduit = true;
            }
        }
        
        return reussiteAjoutProduit;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {

        I_Produit produit = new Produit(nom, prix, qte);
        boolean reussiteAjoutProduit = addProduit(produit);

        return reussiteAjoutProduit;
    }

    @Override
    public int addProduits(List<I_Produit> produitList) {

        int nbProduitsAjoutes = 0;

        if(produitList != null){
            for(I_Produit produit : produitList) {
                if(this.addProduit(produit)) {
                    nbProduitsAjoutes += 1;
                }
            }
        }
        
        return nbProduitsAjoutes;
    }

    @Override
    public boolean removeProduit(String nom) {

        boolean reussiteSuppressionProduit = false;

        if(nomProduitDejaExistant(nom)){
            I_Produit produit = getProduitFromNom(nom);
            List<I_Produit> listeDesProduits = lesProduits;
            listeDesProduits.remove(produit);
            daoProduit.delete(produit);
            reussiteSuppressionProduit = true;
        }
        
        return reussiteSuppressionProduit;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {

        boolean reussiteAchatStockProduit = false;
        boolean quantiteQueLOnVeutAcheterEstPositive = qteAchetee > 0;

        if(nomProduitDejaExistant(nomProduit) && quantiteQueLOnVeutAcheterEstPositive){

            I_Produit produit = getProduitFromNom(nomProduit);
            boolean quantiteProduitEstBienAjoutee = produit.ajouter(qteAchetee);

            if(quantiteProduitEstBienAjoutee){
                daoProduit.update(produit);
                reussiteAchatStockProduit = true;
            }
        }
        
        return reussiteAchatStockProduit;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {

        boolean reussiteVenteStockProduit = false;
        boolean quantiteQueLOnVeutVendreEstPositive = qteVendue > 0;

        if(nomProduitDejaExistant(nomProduit) && quantiteQueLOnVeutVendreEstPositive){

            I_Produit produit = getProduitFromNom(nomProduit);
            boolean quantiteProduitEstBienEnlevee = produit.enlever(qteVendue);

            if(quantiteProduitEstBienEnlevee){
                daoProduit.update(produit);
                reussiteVenteStockProduit = true;
            }
        }
        
        return reussiteVenteStockProduit;
    }

    @Override
    public String[] getNomProduits() {

        int nbProduits = lesProduits.size();
        String[] ensembleDesNomsDesProduits = new String[nbProduits];
        String nomDuProduitCourant;

        for (int i = 0; i < nbProduits; i++) {
            nomDuProduitCourant = lesProduits.get(i).getNom();
            ensembleDesNomsDesProduits[i] = nomDuProduitCourant;
        }

        Arrays.sort(ensembleDesNomsDesProduits);
        
        return ensembleDesNomsDesProduits;
    }

    @Override
    public double getMontantTotalTTC() {

        double montantTotalTTC = 0;

        for(I_Produit produit : lesProduits){
            montantTotalTTC += produit.getPrixStockTTC();
        }

        montantTotalTTC = (double)Math.round(montantTotalTTC * 100) / 100;

        return montantTotalTTC;
    }

    @Override
    public void clear() {
        lesProduits.clear();
    }
    
    @Override
    public String toString(){

        StringBuilder infosDesProduitsDuCatalogue = new StringBuilder();

        for(I_Produit produit : lesProduits){
            infosDesProduitsDuCatalogue.append(produit.toString()).append("\n");
        }

        // Permet de gérer le format et l'arrondi du montantTotalTTC
        final NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(2);
        String montantTotalTTC = instance.format(getMontantTotalTTC());

        infosDesProduitsDuCatalogue.append("\n" + "Montant total TTC du stock : ").
                append(montantTotalTTC).append(" €");
        
        return infosDesProduitsDuCatalogue.toString();
    }
    
}
