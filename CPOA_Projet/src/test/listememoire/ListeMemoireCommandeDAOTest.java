package test.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.CommandeDAO;
import metier.Commande;

class ListeMemoireCommandeDAOTest {

	CommandeDAO commandedao = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getCommandeDAO();

	@Test
	void testCreateTrue() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse("25/06/2001", formatage);
		Commande commande = new Commande(date, 3, null);
		try {
			assertTrue(commandedao.create(commande));
		} catch (SQLException e) {
			fail("Erreur creation !");
		}
	}

	@Test
	void testUpdateTrue() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse("23/02/2001", formatage);
		try {
			Commande commande = new Commande(date, 5, null);
			commandedao.create(commande);
			Commande commandeModif = new Commande(commande.getIdCommande(), date, 9, null);
			assertTrue(commandedao.update(commandeModif));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testDeleteTrue() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse("28/08/2008", formatage);
		try {
			Commande commande = new Commande(9, date, 9, null);
			commandedao.create(commande);
			assertTrue(commandedao.delete(commande));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNotNull() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse("28/08/2008", formatage);
		try {
			Commande commande = new Commande(9, date, 9, null);
			commandedao.create(commande);
			int id = commande.getIdCommande();
			assertNotNull(commandedao.getById(id));			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}
	
	@Test
	void testFindAllNotNull() {
		try {
			assertNotNull(commandedao.findAll());			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}

}
