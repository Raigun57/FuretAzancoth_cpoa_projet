package test.listememoire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import dao.Persistance;
import dao.factory.DAOFactory;
import dao.modele.ClientDAO;
import metier.Client;
	
	class ListeMemoireClientDAOTest {
	
		ClientDAO clientdao = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO();

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
			try {
				Client client = new Client("Val", "AZ");
				clientdao.create(client);
				Client clientModif = new Client(client.getId(), "Modif", "Modif");
				assertTrue(clientdao.update(clientModif));
			} catch (SQLException e) {
				fail("Erreur modification : l'id n'existe pas !");
			}
		}
		
		
		@Test
		void testDeleteTrue() {
			try {
				Client client = new Client("Pedro", "Pascal");
				clientdao.create(client);
				assertTrue(clientdao.delete(client));
			} catch (SQLException e) {
				fail("Erreur suppresion : l'id n'existe pas !");
			}
		}
		
		@Test
		void testGetByIdNotNull() {
			try {
				Client client = new Client("Pedro", "Pascal");
				clientdao.create(client);
				int id = client.getId();
				assertNotNull(clientdao.getById(id));			
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
