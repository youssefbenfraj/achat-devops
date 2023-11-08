package tn.esprit.rh.achat.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

 public class TestProduitServiceImpl {
    @Mock
    ProduitRepository produitRepository= Mockito.mock(ProduitRepository.class);

    @InjectMocks
    ProduitServiceImpl produitService;

    Stock stock= new Stock();
    Set<DetailFacture> detailFactures = new HashSet<>();
    CategorieProduit categorieProduit = new CategorieProduit();

    Produit produit = new Produit(1l,"c1","l1",49.99f,new Date(),new Date(),stock,detailFactures,categorieProduit);
    List<Produit> listProduits = new ArrayList<Produit>(){
        {
            add(new Produit(2l,"c2","l2",49.99f,new Date(),new Date(),stock,detailFactures,categorieProduit));
            add(new Produit(3l,"c3","l3",49.99f,new Date(),new Date(),stock,detailFactures,categorieProduit));
        }
    };

    @Test
     void testretriveProduit(){
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        Produit produit1 = produitService.retrieveProduit(2l);
        Assertions.assertNotNull(produit1);
    }
    @Test
     void testCreateProduit() {
        // Create a new product object or use a builder
        Produit newProduit = new Produit();
        newProduit.setCodeProduit("c4");
        newProduit.setPrix(10.0f);

        Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(newProduit);

        Produit createdProduit = produitService.addProduit(newProduit);

        Assertions.assertNotNull(createdProduit);
        // You can add more assertions to verify that the product was created successfully.
    }
    @Test
     void testUpdateProduit() {
        // Assuming you have an existing product that you want to update
        Produit existingProduit = new Produit();
        existingProduit.setIdProduit(2L); // Use the ID of the existing product
        existingProduit.setCodeProduit("c5");
        existingProduit.setPrix(15.0f);

        Mockito.when(produitRepository.save(Mockito.any(Produit.class))).thenReturn(existingProduit);

        Produit updatedProduit = produitService.updateProduit(existingProduit);

        Assertions.assertNotNull(updatedProduit);
        // You can add more assertions to verify that the product was updated successfully.
    }

    @Test
     void testDeleteProduit() {
        // Assuming you have an existing product that you want to delete
        Produit existingProduit = new Produit();
        existingProduit.setIdProduit(2L); // Use the ID of the product you want to delete

        Mockito.doNothing().when(produitRepository).deleteById(existingProduit.getIdProduit());

        produitService.deleteProduit(existingProduit.getIdProduit());

        // Verify that the delete operation was called
        Mockito.verify(produitRepository, Mockito.times(1)).deleteById(existingProduit.getIdProduit());
    }





}
