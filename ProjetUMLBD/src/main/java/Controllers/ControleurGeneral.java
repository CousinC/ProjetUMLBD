package Controllers;

import Models.Catalogue;
import Models.DAO.ConnexionBD;
import Models.I_Catalogue;

public class ControleurGeneral {
    
    private final I_Catalogue catalogue = new Catalogue();
    private static ControleurGeneral instance;
    private ControleurEtatStock controleurEtatStock;
    private ControleurCreerSupprimerProduit controleurCreerSupprimerProduit;
    private ControleurEnregistrerAchatVente controleurEnregistrerAchatVente;
    
    private ControleurGeneral(){
        this.controleurEtatStock = new ControleurEtatStock(catalogue);
        this.controleurCreerSupprimerProduit = new ControleurCreerSupprimerProduit(catalogue);
        this.controleurEnregistrerAchatVente = new ControleurEnregistrerAchatVente(catalogue);
    }

    public static ControleurGeneral getInstance() {
        if(instance == null){
            instance = new ControleurGeneral();
        }
        return instance;
    }
    
    public String[] genererListeProduits(){
        return catalogue.getNomProduits();
    }
    
    public ControleurEtatStock getControleurEtatStock() {
        return controleurEtatStock;
    }

    public ControleurCreerSupprimerProduit getControleurCreerSupprimerProduit() {
        return controleurCreerSupprimerProduit;
    }

    public ControleurEnregistrerAchatVente getControleurEnregistrerAchatVente() {
        return controleurEnregistrerAchatVente;
    }

    public void deconnexionBDSQL(){
        ConnexionBD connexionBD = ConnexionBD.getInstance();
        if(connexionBD.getCn() != null){
            connexionBD.deconnexion();
        }
    }
}
