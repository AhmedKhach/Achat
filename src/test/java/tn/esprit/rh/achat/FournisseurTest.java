package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

class FournisseurTest {

    @Mock
    FournisseurRepository fournisseurRepository;

    @Mock
    DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    FournisseurServiceImpl fournisseurService;

    @Test
    void retrieveAllFournisseursTest() {
        // Given
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(new Fournisseur());
        fournisseurs.add(new Fournisseur());
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // When
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Then
        assertEquals(2, result.size());
        verify(fournisseurRepository, times(1)).findAll();
    }

    @Test
    void addFournisseurTest() {
        // Given
        Fournisseur fournisseur = new Fournisseur();

        // When
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Then
        assertNotNull(result);
        assertNotNull(result.getDetailFournisseur());
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    void saveDetailFournisseurTest() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);

        // When
        DetailFournisseur result = fournisseurService.saveDetailFournisseur(fournisseur);

        // Then
        assertNotNull(result);
        verify(detailFournisseurRepository, times(1)).save(detailFournisseur);
    }

    @Test
    void updateFournisseurTest() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);

        // When
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        // Then
        assertNotNull(result);
        assertNotNull(result.getDetailFournisseur());
        verify(detailFournisseurRepository, times(1)).save(detailFournisseur);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    void deleteFournisseurTest() {
        // Given
        Long fournisseurId = 1L;

        // When
        fournisseurService.deleteFournisseur(fournisseurId);

        // Then
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }

    @Test
    void retrieveFournisseurTest() {
        // Given
        Long fournisseurId = 1L;
        Fournisseur fournisseur = new Fournisseur();
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));

        // When
        Fournisseur result = fournisseurService.retrieveFournisseur(fournisseurId);

        // Then
        assertNotNull(result);
        verify(fournisseurRepository, times(1)).findById(fournisseurId);
    }

}
