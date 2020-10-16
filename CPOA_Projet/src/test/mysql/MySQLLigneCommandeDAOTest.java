package test.mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.LigneCommandeDAO;
import metier.LigneCommande;

class MySQLLigneCommandeDAOTest {

	LigneCommandeDAO<LigneCommande> lignecommandedao = DAOFactory.getDAOFactory(Persistance.MYSQL).getLigneCommandeDAO();

	@Test
	void testCreateTrue() {
		LigneCommande ligneCommande = new LigneCommande(5, 6, 7, 8);
		try {
			assertTrue(lignecommandedao.create(ligneCommande));
		} catch (SQLException e) {
			fail("Erreur creation !");
		}
	}

	@Test
	void testUpdateTrue() {
		LigneCommande ligneCommande = new LigneCommande(1, 6, 14, 15);
		try {
			assertTrue(lignecommandedao.update(ligneCommande));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testUpdateFalse() {
		LigneCommande ligneCommande = new LigneCommande(87, 94, 14, 15);
		try {
			assertFalse(lignecommandedao.update(ligneCommande));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}

	@Test
	void testDeleteFalse() {
		LigneCommande ligneCommande = new LigneCommande(14, 12, 3, 7);
		try {
			assertFalse(lignecommandedao.delete(ligneCommande));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNull() {
		try {
			assertNull(lignecommandedao.getById(-1, -2));			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}
	
	@Test
	void testFindAllNotNull() {
		try {
			assertNotNull(lignecommandedao.findAll());			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}

}
