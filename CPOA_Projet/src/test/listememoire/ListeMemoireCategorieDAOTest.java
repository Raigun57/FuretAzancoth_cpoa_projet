package test.listememoire;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.CategorieDAO;
import metier.Categorie;

class ListeMemoireCategorieDAOTest {
	
	CategorieDAO categoriedao = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCategorieDAO();

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
		try {
			Categorie categ = new Categorie("Chaussettes", "chaussettes.png");
			categoriedao.create(categ);
			Categorie categModif = new Categorie(categ.getId(), "Modif", "Modif.png");
			assertTrue(categoriedao.update(categModif));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	

	@Test
	void testDeleteTrue() {
		try {
			Categorie categ = new Categorie("Poulet", "Frit");
			categoriedao.create(categ);
			assertTrue(categoriedao.delete(categ));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNotNull() {
		try {
			Categorie categ = new Categorie("Poulet", "Frit");
			categoriedao.create(categ);
			int id = categ.getId();
			assertNotNull(categoriedao.getById(id));			
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