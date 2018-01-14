import Model.I_Catalogue;

public class ControleurCreerSupprimerProduit {
    
    private static I_Catalogue catalogue;
    
    public ControleurCreerSupprimerProduit(I_Catalogue cat){
        this.catalogue = cat;
    }
    
    public void creerProduit(String nom, String prix, String qte){
        catalogue.addProduit(nom, Double.valueOf(prix), Integer.valueOf(qte));
    }
    
    public void supprimer(String nom){
        catalogue.removeProduit(nom);
    }
}
