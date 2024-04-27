package tn.esprit.rh.achat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
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
    ProduitRepository produitRepository;
    @MockBean
    SecteurActiviteRepository secteurActiviteRepository;

    

}
