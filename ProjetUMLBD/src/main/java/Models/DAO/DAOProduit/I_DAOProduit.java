package Models.DAO.DAOProduit;

import Models.I_Produit;
import java.util.List;

public interface I_DAOProduit {

    boolean create(I_Produit produit);
    List<I_Produit> findAll();
    I_Produit findByName(String nomProduit);
    boolean update(I_Produit p);
    boolean delete(I_Produit p);

}
