package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

@ContextConfiguration(classes = {SecteurActiviteServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class SecteurActiviteTest {

    @MockBean
    private SecteurActiviteRepository secteurActiviteRepository;

    @Autowired
    private SecteurActiviteServiceImpl secteurActiviteServiceImpl;

    @Test
    void testRetrieveAllSecteurActivite() {
        ArrayList<SecteurActivite> secteurActiviteList = new ArrayList<>();
        when(secteurActiviteRepository.findAll()).thenReturn(secteurActiviteList);
        List<SecteurActivite> actualRetrieveAllSecteurActiviteResult = secteurActiviteServiceImpl.retrieveAllSecteurActivite();
        assertSame(secteurActiviteList, actualRetrieveAllSecteurActiviteResult);
        assertTrue(actualRetrieveAllSecteurActiviteResult.isEmpty());
        verify(secteurActiviteRepository).findAll();
    }

    @Test
    void testDeleteSecteurActivite() {
        doNothing().when(secteurActiviteRepository).deleteById((Long) any());
        secteurActiviteServiceImpl.deleteSecteurActivite(123L);
        verify(secteurActiviteRepository).deleteById((Long) any());
    }
    @Test
    public void testUpdateSecteurActivite() {

        SecteurActivite sa = new SecteurActivite();
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(sa);
        SecteurActivite updatedSecteurActivite = secteurActiviteServiceImpl.updateSecteurActivite(sa);
        verify(secteurActiviteRepository).save(sa);
        assertEquals(sa, updatedSecteurActivite);
    }

    @Test
    public void testAddSecteurActivite() {
        SecteurActivite sa = new SecteurActivite();
        when(secteurActiviteRepository.save(any(SecteurActivite.class))).thenReturn(sa);
        SecteurActivite addedSecteurActivite = secteurActiviteServiceImpl.addSecteurActivite(sa);
        verify(secteurActiviteRepository).save(sa);
        assertEquals(sa, addedSecteurActivite);
    }
}
