package com.ufolep.polytech.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ufolep.polytech.model.*;

import com.ufolep.polytech.Repositories.*;


import java.util.List;
import java.util.Optional;

@Service
public class CiblesService {

    @Autowired
    private CiblesRepository ciblesRepository;
    @Autowired
    private ClubUtilisateurRepository clubUtilisateurRepository;
    
    

    @Autowired
    private JointureCalendrierRepository jointureCalendrierRepository;

    // Récupérer les participants en fonction du code de la cible, de l'événement et du département
    public List<JointureCalendrier> getParticipantsBasedOnCibleCode(Long id_cible, Long id_evenement, Integer depart) {
        return jointureCalendrierRepository.findByIdEvenementAndCibleAndNumeroTir(id_evenement, id_cible.toString(), depart);
    }

    // Ajouter un participant à la cible
    public String addParticipantToTarget(Long id_cible, JointureCalendrier jointureCalendrier) {
        jointureCalendrierRepository.save(jointureCalendrier);
        return "Participant ajouté avec succès";
    }

    // Définir un marqueur pour la cible
    public String defineMarker(Long id_cible, JointureCalendrier jointureCalendrier) {
        // Logique pour définir un marqueur
        return "Marqueur défini avec succès";
    }

    // Supprimer un participant de la cible
    public String deleteParticipantFromTarget(Long id_cible, JointureCalendrier jointureCalendrier) {
        jointureCalendrierRepository.delete(jointureCalendrier);
        return "Participant supprimé avec succès";
    }
    public Cibles saveCible(Cibles cible) {
        return ciblesRepository.save(cible);
    }

    // Mettre à jour une cible
    public Cibles updateCible(Long id, Cibles cible) {
        cible.setId(id);
        return ciblesRepository.save(cible);
    }

    // Supprimer une cible
    public void deleteCible(Long id) {
        ciblesRepository.deleteById(id);
    }

    // Récupérer une cible par ID
    public Cibles getCibleById(Long id) {
        return ciblesRepository.findById(id).orElse(null);
    }

    // Récupérer tous les cibles
    public List<Cibles> getAllCibles() {
        return ciblesRepository.findAll();
    }
    /**
     * Récupérer la liste de toutes les affectations (jointures) pour un événement et un numéro de cible.
     * 
     * @param idEvenement l'identifiant de l'événement
     * @param numeroCible le numéro de la cible (en tant que String)
     * @return La liste des affectations pour cette cible
     */
    public List<JointureCalendrier> getAffectations(int idEvenement, String numeroCible) {
        return jointureCalendrierRepository.findByIdEvenementAndCible(idEvenement, numeroCible);
    }

    /**
     * Ajouter une affectation (importer un archer) sur une cible.
     * 
     * @param idEvenement l'identifiant de l'événement
     * @param numeroCible  le numéro de la cible (ex. "5")
     * @param licence    la licence de l'utilisateur à affecter
     * @return L'objet JointureCalendrier créé ou null si l'utilisateur n'est pas trouvé
     */
    public JointureCalendrier ajouterAffectation(int idEvenement, String numeroCible, String licence) {
        Optional<ClubsUtilisateur> userOpt = clubUtilisateurRepository.findByLicence(licence);
        if (!userOpt.isPresent()) {
            // L'utilisateur n'est pas inscrit ou la licence est incorrecte
            return null;
        }
        ClubsUtilisateur user = userOpt.get();
        JointureCalendrier affectation = new JointureCalendrier();
        affectation.setIdEvenement(idEvenement);
        affectation.setCible(numeroCible);
        affectation.setUtilisateur(user);
        // Vous pouvez initialiser d'autres champs (comme depart, numeroTir, etc.) selon vos besoins
        return jointureCalendrierRepository.save(affectation);
    }

    /**
     * Récupérer les détails d'une affectation donnée.
     * 
     * @param idEvenement  l'identifiant de l'événement
     * @param numeroCible  le numéro de la cible
     * @param idAffectation l'identifiant de la jointure (affectation)
     * @return L'objet JointureCalendrier correspondant ou null s'il n'est pas trouvé
     */
    public JointureCalendrier getAffectationDetail(int idEvenement, String numeroCible, Long idAffectation) {
        // Optionnel : vous pouvez valider que l'affectation correspond bien à l'événement et la cible
        Optional<JointureCalendrier> opt = jointureCalendrierRepository.findById(idAffectation);
        return opt.orElse(null);
    }

    /**
     * Supprimer une affectation (retirer un compétiteur d'une cible).
     * 
     * @param idEvenement  l'identifiant de l'événement
     * @param numeroCible  le numéro de la cible
     * @param idAffectation l'identifiant de l'affectation à supprimer
     * @return true si la suppression est effectuée, false en cas d'erreur
     */
    public boolean supprimerAffectation(int idEvenement, String numeroCible, Long idAffectation) {
        try {
            // Vous pouvez ajouter ici des vérifications sur l'événement et le numéro de cible avant de supprimer
            jointureCalendrierRepository.deleteById(idAffectation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

