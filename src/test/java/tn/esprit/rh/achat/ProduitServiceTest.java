package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class ProduitServiceTest {
    @Mock
    ProduitRepository produitRepository;

    @InjectMocks
    ProduitServiceImpl produitService;

    Produit produit= new Produit(1L,"test","libelle",1,new Date(5),new Date(9),new Stock(),new HashSet<>(), new CategorieProduit());

    List<Produit> ListProd = new ArrayList<Produit>(){
        {
            add(new Produit(2L,"test2","libelle2",2,new Date(5),new Date(9),new Stock(),new HashSet<>(), new CategorieProduit()));
        }
    };


    @Test
    public void testRetrieveProduit(){
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        Produit produit1=produitService.retrieveProduit(1L);
        Assertions.assertNotNull(produit1);
    }
}
