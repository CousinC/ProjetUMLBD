package projetumlbd;

public class ControleurGeneral {
    
    private static final I_Catalogue catalogue = new Catalogue();
    private ControleurEtatStock controleurEtatStock;
    private ControleurCreerSupprimerProduit controleurCreerSupprimerProduit;
    private ControleurEnregistrerAchatVente controleurEnregistrerAchatVente;
    
    public ControleurGeneral(){
        this.controleurEtatStock = new ControleurEtatStock(catalogue);
        this.controleurCreerSupprimerProduit = new ControleurCreerSupprimerProduit(catalogue);
        this.controleurEnregistrerAchatVente = new ControleurEnregistrerAchatVente(catalogue);
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
    
}
