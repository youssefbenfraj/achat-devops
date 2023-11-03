package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    StockRepository stockRepository = Mockito.mock(StockRepository.class);

    @InjectMocks
    StockServiceImpl stockService;

    Stock stock = new Stock();

    List<Stock> stockList =new ArrayList<Stock>(){
        {
            add(new Stock("Stock 1",5,2));
            add(new Stock("Stock 2",10,4));
        }
    };

    @Test
    public void testRetrieveStock(){
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Stock stock1 = stockService.retrieveStock(1L);
        Assertions.assertNotNull(stock1);
    }

    @Test
    public void testRestrieveAllStock(){
        Mockito.when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> stockList = stockService.retrieveAllStocks();
        Assertions.assertNotNull(stockList);
    }

    @Test
    public void testAddStock(){
        Stock stock2 =new Stock("Stock 3",5,5);
        Mockito.when(stockRepository.save(Mockito.any(stock2.getClass()))).thenAnswer(invocation ->{

            Stock savedStock = invocation.getArgument(0);
            // Check if the saved stock matches the stockToAdd.
            Assertions.assertEquals(stock2.getLibelleStock(), savedStock.getLibelleStock());
            Assertions.assertEquals(stock2.getQte(), savedStock.getQte());
            Assertions.assertEquals(stock2.getQteMin(), savedStock.getQteMin());
            return savedStock;
        });

        Stock addedStock = stockService.addStock(stock2);

        // Verify that stockRepository.save() was called with the expected stockToAdd.
        Mockito.verify(stockRepository).save(stock2);

        // Check if the addedStock returned by the service matches the stockToAdd.
        Assertions.assertNotNull(addedStock);
        Assertions.assertEquals(stock2.getLibelleStock(), addedStock.getLibelleStock());
        Assertions.assertEquals(stock2.getQte(), addedStock.getQte());
        Assertions.assertEquals(stock2.getQteMin(), addedStock.getQte());

    }

    @Test
    public void testUpdateStock() {
        Stock stockToUpdate = new Stock("StockToUpdate", 10, 2);
        Mockito.when(stockRepository.save(stockToUpdate)).thenReturn(stockToUpdate);
        Stock updatedStock = stockService.updateStock(stockToUpdate);

        Mockito.verify(stockRepository).save(stockToUpdate);

        Assertions.assertNotNull(updatedStock);
        Assertions.assertEquals(stockToUpdate.getLibelleStock(), updatedStock.getLibelleStock());
        Assertions.assertEquals(stockToUpdate.getQte(), updatedStock.getQte());
        Assertions.assertEquals(stockToUpdate.getQteMin(), updatedStock.getQteMin());
    }
}
