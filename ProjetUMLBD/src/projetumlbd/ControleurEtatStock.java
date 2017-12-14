package projetumlbd;

public class ControleurEtatStock {
    
    private static I_Catalogue catalogue;
    
    public ControleurEtatStock(I_Catalogue cat){
        this.catalogue = cat;
    }
    
    public String affichageEtatStock(){
        return catalogue.toString();
    }   
}
