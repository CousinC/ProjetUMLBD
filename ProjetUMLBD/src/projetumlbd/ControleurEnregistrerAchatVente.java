package projetumlbd;

public class ControleurEnregistrerAchatVente {
    
    private static I_Catalogue catalogue;
    
    public ControleurEnregistrerAchatVente(I_Catalogue cat){
        this.catalogue = cat;
    }
     
    public void ajouterProduitStock(String nom, String qte){
        catalogue.acheterStock(nom, Integer.parseInt(qte));
    }
    
    public void vendreProduit(String nom, String qte){
        catalogue.vendreStock(nom, Integer.parseInt(qte));
    }
}
