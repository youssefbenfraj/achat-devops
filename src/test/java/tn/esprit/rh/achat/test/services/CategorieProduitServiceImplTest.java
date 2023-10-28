package tn.esprit.rh.achat.test.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategorieProduitServiceImplTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllCategorieProduits() {

        CategorieProduit categorieProduit1 = new CategorieProduit();
        categorieProduit1.setIdCategorieProduit(1L);
        categorieProduit1.setCodeCategorie("CAT123");
        categorieProduit1.setLibelleCategorie("Category 1");

        CategorieProduit categorieProduit2 = new CategorieProduit();
        categorieProduit2.setIdCategorieProduit(2L);
        categorieProduit2.setCodeCategorie("CAT456");
        categorieProduit2.setLibelleCategorie("Category 2");

        List<CategorieProduit> mockCategorieProduits = new ArrayList<>();
        mockCategorieProduits.add(categorieProduit1);
        mockCategorieProduits.add(categorieProduit2);


        Mockito.when(categorieProduitRepository.findAll()).thenReturn(mockCategorieProduits);


        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();


        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testAddCategorieProduit() {
        CategorieProduit mockCategorieProduit = new CategorieProduit();
        mockCategorieProduit.setIdCategorieProduit(1L);
        mockCategorieProduit.setLibelleCategorie("Category 1");

        Mockito.when(categorieProduitRepository.save(Mockito.any(CategorieProduit.class))).thenReturn(mockCategorieProduit);

        CategorieProduit result = categorieProduitService.addCategorieProduit(mockCategorieProduit);

        assertNotNull(result);
        assertEquals(1L, result.getIdCategorieProduit());
        assertEquals("Category 1", result.getLibelleCategorie());
    }



    @Test
    void testDeleteCategorieProduit() {
        Long idToDelete = 1L;

        categorieProduitService.deleteCategorieProduit(idToDelete);

        Mockito.verify(categorieProduitRepository).deleteById(idToDelete);
    }

    @Test
    void testUpdateCategorieProduit() {
        CategorieProduit mockCategorieProduit = new CategorieProduit();
        mockCategorieProduit.setIdCategorieProduit(1L);
        mockCategorieProduit.setLibelleCategorie("Updated Category");

        Mockito.when(categorieProduitRepository.save(Mockito.any(CategorieProduit.class))).thenReturn(mockCategorieProduit);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(mockCategorieProduit);

        assertNotNull(result);
        assertEquals(1L, result.getIdCategorieProduit());
        assertEquals("Updated Category", result.getLibelleCategorie());
    }



    @Test
    void testRetrieveNonExistentCategorieProduit() {
        Long idToRetrieve = 1L;

        Mockito.when(categorieProduitRepository.findById(idToRetrieve)).thenReturn(Optional.empty());

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(idToRetrieve);

        assertNull(result);
    }
}
