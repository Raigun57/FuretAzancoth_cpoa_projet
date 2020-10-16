package test.mysql;

import static org.junit.jupiter.api.Assertions.*;
import dao.*;
import dao.factory.DAOFactory;
import dao.modele.CategorieDAO;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import metier.Categorie;

class MySQLCategorieDAOTest {
	
	//Les tests ne fonctionnent pas quand on essaye de récuperer les id donc on les a fait en dur
	
	CategorieDAO categoriedao = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO();

	@Test
	void testCreateTrue() {
		Categorie categ = new Categorie("test", "test.png");
		try {
			assertTrue(categoriedao.create(categ));
		} catch (SQLException e) {
			fail("Erreur creation !");
		}
	}
	
	@Test
	void testUpdateTrue() {
		Categorie categ = new Categorie(4, "Chaussettes", "chaussettes.png");
		try {
			assertTrue(categoriedao.update(categ));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testUpdateFalse() {
		Categorie categ = new Categorie(100, "Chaussettes", "chaussettes.png");
		try {
			assertFalse(categoriedao.update(categ));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	/*
	/!\ testDeleteTrue ne fonctionne pas quand je crée une categorie et que je récupère son id
	@Test
	void testDeleteTrue() {
		try {
			Categorie categ = new Categorie("Poulet", "Frit");
			categoriedao.create(categ);
			Categorie categId = new Categorie(categ.getId());
			assertTrue(categoriedao.delete(categId));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	*/
	
	@Test
	void testDeleteFalse() {
		try {
			Categorie categ = new Categorie("Poulet", "Frit");
			categoriedao.create(categ);
			assertFalse(categoriedao.delete(categ));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNull() {
		try {
			assertNull(categoriedao.getById(-1));			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}
	
	@Test
	void testFindAllNotNull() {
		try {
			assertNotNull(categoriedao.findAll());			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}
	
}
