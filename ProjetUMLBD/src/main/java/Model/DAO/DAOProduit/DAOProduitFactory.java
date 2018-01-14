package Model.DAO.DAOProduit;

public class DAOProduitFactory {

    private static DAOProduitFactory daoProduitFactory = null;

    private DAOProduitFactory(){
    }

    public static DAOProduitFactory getDaoProduitFactory(){
        if(daoProduitFactory == null){
            daoProduitFactory = new DAOProduitFactory();
        }
        return daoProduitFactory;
    }

    public I_DAOProduit creerDAOProduit(String typeBaseDonnees){
        I_DAOProduit daoProduit = null;

        switch(typeBaseDonnees){
            case "SQL":
                daoProduit = new DAOProduitSQL();
                break;
            case "XML":
                daoProduit = new AdaptateurDAOProduitXML();
                break;
        }
        
        return daoProduit;
    }
}
