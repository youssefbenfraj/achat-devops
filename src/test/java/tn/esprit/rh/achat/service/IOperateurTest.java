package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IOperateurTest {
    @Autowired
    private IOperateurService operateurService;



    @Test
    @Order(1)
    public void testRetrieveAllOperateurs() {
        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();
        assertNotNull(operateurs);
        // You can add more specific assertions based on your use case.
    }

    @Test
    @Order(2)

    public void testAddOperateur() {
        Operateur newOperateur = new Operateur();
        // Set properties for the new Operateur
        Operateur addedOperateur = operateurService.addOperateur(newOperateur);
        assertNotNull(addedOperateur.getIdOperateur());
        // You can add more specific assertions based on your use case.
    }

    @Test
    @Order(3)

    public void testDeleteOperateur() {
        // You can delete an Operateur by ID and then check if it's no longer retrievable.
        Long operateurId = 1L; // Replace with a valid Operateur ID
        operateurService.deleteOperateur(operateurId);
        Operateur deletedOperateur = operateurService.retrieveOperateur(operateurId);
        Assertions.assertNull(deletedOperateur);
    }

    @Test
    @Order(4)

    public void testUpdateOperateur() {
        Operateur operateurToUpdate = new Operateur();
        // Set properties for the Operateur to update
        Operateur updatedOperateur = operateurService.updateOperateur(operateurToUpdate);
        assertNotNull(updatedOperateur);
        // You can add more specific assertions based on your use case.
    }


    /*public void testRetrieveOperateur() {
        Long operateurId = 1L; // Replace with a valid Operateur ID
        Operateur operateur = operateurService.retrieveOperateur(operateurId);
        assertNotNull(operateur);
        // You can add more specific assertions based on your use case.
    }*/

}
