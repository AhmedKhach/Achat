package tn.esprit.rh.achat;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
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
    }

}
