package test.mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.ProduitDAO;
import metier.Produit;

class MySQLProduitDAOTest {

	ProduitDAO produitdao = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO();

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
		Produit produit = new Produit(12, "rgr", "Chyl !", 32, "val.hthyr", 1);
		try {
			assertTrue(produitdao.update(produit));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testUpdateFalse() {
		Produit produit = new Produit(100, "Modif", "Modif", 32, "Modif", 1);
		try {
			assertFalse(produitdao.update(produit));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}

	@Test
	void testDeleteFalse() {
		Produit produit = new Produit("Supp", "Supp", 3, "Supp", 4);
		try {
			assertFalse(produitdao.delete(produit));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNull() {
		try {
			assertNull(produitdao.getById(-1));			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}

}
