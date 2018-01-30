package Controllers;

import Models.I_Catalogue;

public class ControleurEtatStock {
    
    private I_Catalogue catalogue;
    
    public ControleurEtatStock(I_Catalogue cat){
        this.catalogue = cat;
    }
    
    public String affichageEtatStock(){
        return catalogue.toString();
    }   
}
