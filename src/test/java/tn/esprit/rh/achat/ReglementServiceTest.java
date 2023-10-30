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
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class ReglementServiceTest {
        @Mock
        ReglementRepository RegelementRepository;

        @InjectMocks
        ReglementServiceImpl ReglementService;

        Reglement reglement= new Reglement(1L,500.1f,400.1f,true,new Date(9), new Facture());

        List<Reglement> ListRegl = new ArrayList<Reglement>(){
            {
                add(new Reglement(2L,530.1f,410.1f,true,new Date(9), new Facture()));
            }
        };

        @Test
        public void testRetrieveReglement(){
            Mockito.when(RegelementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reglement));
            Reglement reglement1=ReglementService.retrieveReglement(2L);

            Assertions.assertNotNull(reglement1);
        }
    }


