package Models.DAO.DAOProduit;

public class DAOProduitFactory {

    private static DAOProduitFactory instance;

    private DAOProduitFactory(){
    }

    public static DAOProduitFactory getInstance(){
        if(instance == null){
            instance = new DAOProduitFactory();
        }
        return instance;
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
            default:
                daoProduit = new DAOProduitSQL();
                break;
        }
        return daoProduit;
    }
}
