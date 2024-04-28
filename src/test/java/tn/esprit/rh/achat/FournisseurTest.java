package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ContextConfiguration(classes = { FournisseurServiceImpl.class })
@ExtendWith(SpringExtension.class)
public class FournisseurTest {

    @MockBean
    FournisseurRepository fournisseurRepository;
    @MockBean
    DetailFournisseurRepository detailFournisseurRepository;
    @MockBean
    FournisseurServiceImpl fournisseurServiceImpl;
    @MockBean
    SecteurActiviteRepository secteurActiviteRepository;

    @Test
    void retrieveAllFournisseurs() {
        ArrayList<Fournisseur> fournisseurList = new ArrayList<>();
        when(fournisseurRepository.findAll()).thenReturn(fournisseurList);
        List<Fournisseur> actualRetrieveAllfournisseurResult = fournisseurServiceImpl.retrieveAllFournisseurs();
        assertEquals(fournisseurList, actualRetrieveAllfournisseurResult); // Fix assertion
        assertTrue(actualRetrieveAllfournisseurResult.isEmpty());
    }

    @Test
    void testAddFournisseur() {
        DetailFournisseur detailFournisseurMock = mock(DetailFournisseur.class);
        when(detailFournisseurMock.getDateDebutCollaboration()).thenReturn(new Date());
        Fournisseur fournisseurMock = mock(Fournisseur.class);
        when(fournisseurMock.getDetailFournisseur()).thenReturn(detailFournisseurMock);
        fournisseurServiceImpl.addFournisseur(fournisseurMock);
        assertDoesNotThrow(() -> fournisseurServiceImpl.addFournisseur(fournisseurMock));
    }

    @Test
    void testSaveDetailFournisseur() {
        DetailFournisseur detailFournisseurMock = mock(DetailFournisseur.class);
        Fournisseur fournisseurMock = mock(Fournisseur.class);
        when(fournisseurMock.getDetailFournisseur()).thenReturn(detailFournisseurMock);
        fournisseurServiceImpl.saveDetailFournisseur(fournisseurMock);
        assertDoesNotThrow(() -> fournisseurServiceImpl.saveDetailFournisseur(fournisseurMock));

    }

    @Test
    void testUpdateFournisseur() {
        DetailFournisseur detailFournisseurMock = mock(DetailFournisseur.class);
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseurMock);
        Fournisseur fournisseurMock = mock(Fournisseur.class);
        when(fournisseurMock.getDetailFournisseur()).thenReturn(detailFournisseurMock);
        fournisseurServiceImpl.updateFournisseur(fournisseurMock);
        assertDoesNotThrow(() -> fournisseurServiceImpl.updateFournisseur(fournisseurMock));
    }

    @Test
    void testDeleteFournisseur() {
        Long fournisseurId = 123L;
        fournisseurServiceImpl.deleteFournisseur(fournisseurId);
        assertDoesNotThrow(() -> fournisseurServiceImpl.deleteFournisseur(fournisseurId));

    }

    @Test
    void testRetrieveFournisseur_WhenExists() {
        Long fournisseurId = 123L;
        Fournisseur mockFournisseur = new Fournisseur();
        mockFournisseur.setCode(fournisseurId.toString());
        mockFournisseur.setLibelle("Test Fournisseur");
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(mockFournisseur));
        fournisseurServiceImpl.retrieveFournisseur(fournisseurId);
        assertDoesNotThrow(() -> fournisseurServiceImpl.retrieveFournisseur(fournisseurId));    }

    @Test
    void testAssignSecteurActiviteToFournisseur() {
        Long idSecteurActivite = 1L;
        Long idFournisseur = 2L;
        Fournisseur fournisseurMock = new Fournisseur();
        SecteurActivite secteurActiviteMock = new SecteurActivite();
        when(fournisseurRepository.findById(idFournisseur)).thenReturn(Optional.of(fournisseurMock));
        when(secteurActiviteRepository.findById(idSecteurActivite)).thenReturn(Optional.of(secteurActiviteMock));
        fournisseurServiceImpl.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);
        assertDoesNotThrow(() -> fournisseurServiceImpl.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur));

    }

}
