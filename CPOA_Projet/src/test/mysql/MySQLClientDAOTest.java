package test.mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.ClientDAO;
import metier.Client;

class MySQLClientDAOTest {
	
	ClientDAO clientdao = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO();

	@Test
	void testCreateTrue() {
		Client client = new Client("Val", "AZ");
		try {
			assertTrue(clientdao.create(client));
		} catch (SQLException e) {
			fail("Erreur creation !");
		}
	}
	
	@Test
	void testUpdateTrue() {
		Client client = new Client(1, "Val", "AZ");
		try {
			assertTrue(clientdao.update(client));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testUpdateFalse() {
		Client client = new Client(2, "Modif", "Modif");
		try {
			assertFalse(clientdao.update(client));
		} catch (SQLException e) {
			fail("Erreur modification : l'id n'existe pas !");
		}
	}
	
	@Test
	void testDeleteFalse() {
		Client client = new Client("Pedro", "Pascal");
		try {
			assertFalse(clientdao.delete(client));
		} catch (SQLException e) {
			fail("Erreur suppresion : l'id n'existe pas !");
		}
	}
	
	@Test
	void testGetByIdNull() {
		try {
			assertNull(clientdao.getById(-1));			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}
	
	@Test
	void testFindAllNotNull() {
		try {
			assertNotNull(clientdao.findAll());			
		} catch (SQLException e) {
			fail("Cette id n'existe pas");
		}
	}

}
