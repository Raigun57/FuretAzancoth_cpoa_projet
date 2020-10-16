package test.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.ProduitDAO;
import metier.Produit;

class ListeMemoireProduitDAOTest {

	ProduitDAO produitdao = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getProduitDAO();

	@Test
	void testCreateTrue() {
		Produit produit = new Produit("Val", "C'est cool !", 40, "val.png", 3);
		try {
			assertTrue(produitdao.create(produit));
		} catch (SQLException e) {
			fail("Erreur creation !");
		}
	}

	@Test
	void testUpdateTrue() {
		try {
			Produit produit = new Produit("rgr", "Chyl !", 32, "val.hthyr", 1);
			produitdao.create(produit);
			Produit produitModif = new Produit(produit.getId(), "Modif", "Modif !", 47, "Modif.Modif", 8);
			assertTrue(produitdao.update(produitModif));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	
	@Test
	void testDeleteTrue() {
		try {
			Produit produit = new Produit("Supp", "Supp", 3, "Supp", 4);
			produitdao.create(produit);
			assertTrue(produitdao.delete(produit));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNotNull() {
		try {
			Produit produit = new Produit("Supp", "Supp", 3, "Supp", 4);
			produitdao.create(produit);
			int id = produit.getId();
			assertNotNull(produitdao.getById(id));			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}
	
	@Test
	void testFindAllNotNull() {
		try {
			assertNotNull(produitdao.findAll());			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}

}
