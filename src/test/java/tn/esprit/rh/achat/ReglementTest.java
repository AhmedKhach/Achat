package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.IReglementService;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReglementTest {

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    ReglementServiceImpl reglementService;



    @Test
    public void retrieveAllReglements() {
        // Mock behavior of the repository
        try {
            List<Reglement> reglements = new ArrayList<>();
            when(reglementRepository.findAll()).thenReturn(reglements);


        // Call the service method
        List<Reglement> result = (List<Reglement>) reglementService.retrieveAllReglements();

        // Assert the result
        assertEquals(reglements.size(), result.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addReglement() {
        // Create a mock Reglement object
        Reglement reglement = new Reglement();
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(100);

        // Mock behavior of the repository
        when(reglementRepository.save(reglement)).thenReturn(reglement);

        // Call the service method
        Reglement result = reglementService.addReglement(reglement);

        // Assert the result
        assertEquals(reglement.getIdReglement(), result.getIdReglement());
        assertEquals(reglement.getMontantPaye(), result.getMontantPaye());
    }

    @Test
    public void retrieveReglement() {
        // Create a mock Reglement object
        Long id = 1L;
        Reglement reglement = new Reglement();
        reglement.setIdReglement(id);
        reglement.setMontantPaye(100);

        // Mock behavior of the repository
        when(reglementRepository.findById(id)).thenReturn(Optional.of(reglement));

        // Call the service method
        Reglement result = reglementService.retrieveReglement(id);

        // Assert the result
        assertEquals(reglement.getIdReglement(), result.getIdReglement());
        assertEquals(reglement.getMontantPaye(), result.getMontantPaye());
    }

    @Test
    public void retrieveReglementByFacture() {
        // Mock behavior of the repository
        Long idFacture = 1L;
        List<Reglement> reglements = new ArrayList<>();
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(reglements);

        // Call the service method
        List<Reglement> result = reglementService.retrieveReglementByFacture(idFacture);

        // Assert the result
        assertEquals(reglements.size(), result.size());
    }

    @Test
    public void getChiffreAffaireEntreDeuxDate() {
        // Mock behavior of the repository
        Date startDate = new Date();
        Date endDate = new Date();
        float chiffreAffaire = 1000;
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(chiffreAffaire);


        // Call the service method
        float result =  reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        // Assert the result
        assertEquals(chiffreAffaire, result);
    }
}