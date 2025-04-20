package com.ufolep.polytech.service;

import com.ufolep.polytech.Repositories.ClubUtilisateurRepository;
import com.ufolep.polytech.model.ClubsUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ClubUtilisateurRepository clubsUtilisateurRepository;

    public ClubsUtilisateur loginByLicence(String licence) {
        Optional<ClubsUtilisateur> optUser = clubsUtilisateurRepository.findByLicence(licence);
        return optUser.orElse(null);
    }

    // Méthode pour changer de marqueur (exemple logique)
    public boolean changeMarqueur(String oldLicence, String newLicence) {
        Optional<ClubsUtilisateur> oldUserOpt = clubsUtilisateurRepository.findByLicence(oldLicence);
        Optional<ClubsUtilisateur> newUserOpt = clubsUtilisateurRepository.findByLicence(newLicence);
        if (oldUserOpt.isPresent() && newUserOpt.isPresent()) {
            ClubsUtilisateur oldUser = oldUserOpt.get();
            ClubsUtilisateur newUser = newUserOpt.get();
            // Par exemple, réinitialiser le statut du vieux marqueur et assigner le nouveau
            oldUser.setRole(null); // ou un rôle d'inactif
            newUser.setRole(oldUser.getRole()); // ou définir explicitement son rôle de marqueur
            clubsUtilisateurRepository.save(oldUser);
            clubsUtilisateurRepository.save(newUser);
            return true;
        }
        return false;
    }
}
