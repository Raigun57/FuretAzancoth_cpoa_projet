package test.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.LigneCommandeDAO;
import metier.LigneCommande;

class ListeMemoireLigneCommandeDAOTest {

	LigneCommandeDAO<LigneCommande> lignecommandedao = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getLigneCommandeDAO();

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
		try {
			LigneCommande ligneCommande = new LigneCommande(67, 88, 14, 15);
			lignecommandedao.create(ligneCommande);
			LigneCommande ligneCommandeModif = new LigneCommande(ligneCommande.getIdCommande(), ligneCommande.getIdProduit(), 4, 10);
			assertTrue(lignecommandedao.update(ligneCommandeModif));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}


	@Test
	void testDeleteTrue() {
		try {
			LigneCommande ligneCommande = new LigneCommande(14, 12, 3, 7);
			lignecommandedao.create(ligneCommande);
			assertTrue(lignecommandedao.delete(ligneCommande));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}

	@Test
	void testGetByIdNotNull() {
		try {
			LigneCommande ligneCommande = new LigneCommande(45, 42, 3, 7);
			lignecommandedao.create(ligneCommande);
			int id1 = ligneCommande.getIdCommande();
			int id2 = ligneCommande.getIdProduit();
			assertNotNull(lignecommandedao.getById(id1, id2));			
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
