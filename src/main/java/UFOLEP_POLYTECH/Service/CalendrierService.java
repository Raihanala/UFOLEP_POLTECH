package UFOLEP_POLYTECH.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UFOLEP_POLYTECH.Model.Calendrier;
import UFOLEP_POLYTECH.Repositories.CalendrierRepository;

import java.util.List;

@Service
public class CalendrierService {

    @Autowired
    private CalendrierRepository calendrierRepository;

    // Récupérer tous les événements pour un utilisateur
    public List<Calendrier> getAllCalendriers() {
        return calendrierRepository.findAll();
    }

    // Récupérer un événement spécifique par ID
    public Calendrier getCalendrierById(Long id) {
        return calendrierRepository.findById(id).orElse(null);
    }

    // Créer un nouvel événement
    public Calendrier createCalendrier(Calendrier calendrier) {
        return calendrierRepository.save(calendrier);
    }

    // Supprimer un événement
    public void deleteCalendrier(Long id) {
        calendrierRepository.deleteById(id);
    }
}
