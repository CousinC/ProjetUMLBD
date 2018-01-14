import Model.I_Catalogue;

import javax.swing.JOptionPane;

public class ControleurEnregistrerAchatVente {
    
    private static I_Catalogue catalogue;
    private JOptionPane messageInformatif;
    
    public ControleurEnregistrerAchatVente(I_Catalogue cat){
        this.catalogue = cat;
    }
     
    public void ajouterProduitStock(String nom, String qte){
        boolean retour = catalogue.acheterStock(nom, Integer.parseInt(qte));
        if(!retour){
            messageInformatif = new JOptionPane();
            messageInformatif.showMessageDialog(null, "Erreur !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void vendreProduit(String nom, String qte){
        boolean retour = catalogue.vendreStock(nom, Integer.parseInt(qte));
        if(!retour){
            messageInformatif = new JOptionPane();
            messageInformatif.showMessageDialog(null, "Erreur !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
