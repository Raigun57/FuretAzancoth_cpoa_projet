package test.mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.CommandeDAO;
import metier.Commande;

class MySQLCommandeDAOTest {

	CommandeDAO commandedao = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO();

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
		Commande commande = new Commande(8, date, 5, null);
		try {
			assertTrue(commandedao.update(commande));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testUpdateFalse() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse("23/02/2001", formatage);
		Commande commande = new Commande(100, date, 5, null);
		try {
			assertFalse(commandedao.update(commande));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}

	@Test
	void testDeleteFalse() {
		DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate date = LocalDate.parse("28/08/2008", formatage);
		Commande commande = new Commande(9, date, 9, null);
		try {
			assertFalse(commandedao.delete(commande));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNotNull() {
		try {
			assertNotNull(commandedao.getById(1));			
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
