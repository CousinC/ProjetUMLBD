package Models.DAO.DAOProduit;

import Models.I_Produit;
import java.util.List;

public class AdaptateurDAOProduitXML implements I_DAOProduit {

    private ProduitDAO_XML produitDAO_xml;

    public AdaptateurDAOProduitXML(){
        produitDAO_xml = new ProduitDAO_XML();
    }

    @Override
    public boolean create(I_Produit produit) {
        return produitDAO_xml.creer(produit);
    }

    @Override
    public List<I_Produit> findAll() {
        return produitDAO_xml.lireTous();
    }

    @Override
    public I_Produit findByName(String nomProduit) {
        return produitDAO_xml.lire(nomProduit);
    }

    @Override
    public boolean update(I_Produit produit) {
        return produitDAO_xml.maj(produit);
    }

    @Override
    public boolean delete(I_Produit produit) {
        return produitDAO_xml.supprimer(produit);
    }
}
