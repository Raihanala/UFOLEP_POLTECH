package com.ufolep.polytech.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ufolep.polytech.model.*;

import com.ufolep.polytech.Repositories.*;
import java.util.Optional;

@Service
public class ClubsUtilisateurService {

    @Autowired
    private MarqueRepository marqueRepository;
    
    @Autowired
    private ClubUtilisateurRepository clubsUtilisateurRepository;
    

    // Récupérer le score d'un utilisateur pour un événement et une cible
    public List<Marque> getScore(String user_license, Long id_evenement, Long id_cible, Integer depart) {
        return marqueRepository.findByLicenceAndIdEvenementAndIdCible(user_license, id_evenement, id_cible);
    }

    // Récupérer les informations sur l'étape d'un utilisateur
    public String getStepInfo(String user_license, Long id_evenement, Long id_cible, Integer depart) {
        // Logique pour récupérer les informations sur l'étape
        return "Informations sur l'étape";
    }

    // Sauvegarder le score d'un utilisateur
    public String saveScore(String user_license, Marque marque) {
        marqueRepository.save(marque);
        return "Score sauvegardé avec succès";
    }

    // Mettre à jour le score d'un utilisateur
    public String updateScore(String user_license, Marque marque) {
        marqueRepository.save(marque);
        return "Score mis à jour avec succès";
    }
    
    // Sauvegarder un ClubsUtilisateur
    public ClubsUtilisateur saveClubsUtilisateur(ClubsUtilisateur club) {
        return clubsUtilisateurRepository.save(club);
    }

    // Mettre à jour un ClubsUtilisateur existant
    public ClubsUtilisateur updateClubsUtilisateur(Long id, ClubsUtilisateur clubDetails) {
        // Récupère le club existant ou lance une exception s'il n'existe pas
        ClubsUtilisateur club = clubsUtilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClubsUtilisateur not found with id: " + id));
        
        // Met à jour les attributs du club avec ceux fournis dans clubDetails
        club.setNom(clubDetails.getNom());
        club.setPrenom(clubDetails.getPrenom());
        club.setLicence(clubDetails.getLicence());
        club.setSexe(clubDetails.getSexe());
        club.setRole(clubDetails.getRole());
        club.setClub(clubDetails.getClub());
        
        // Sauvegarde et retourne le club mis à jour
        return clubsUtilisateurRepository.save(club);
    }

    
    // Supprimer un ClubsUtilisateur par son ID
    public void deleteClubsUtilisateur(Long id) {
        clubsUtilisateurRepository.deleteById(id);
    }
    

    // Récupérer un ClubsUtilisateur par son ID
    public ClubsUtilisateur getClubsUtilisateurById(Long id) {
        return clubsUtilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClubsUtilisateur not found with id: " + id));
    }

    // Récupérer la liste de tous les ClubsUtilisateurs
    public List<ClubsUtilisateur> getAllClubsUtilisateurs() {
        return clubsUtilisateurRepository.findAll();
    }
    public Optional<ClubsUtilisateur> findByLicence(String licence) {
    	   return clubsUtilisateurRepository.findByLicence(licence);
    	}

    }
