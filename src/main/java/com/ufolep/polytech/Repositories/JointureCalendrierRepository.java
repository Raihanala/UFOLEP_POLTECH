package com.ufolep.polytech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ufolep.polytech.model.JointureCalendrier;

import java.util.List;
import java.util.Optional;

@Repository
public interface JointureCalendrierRepository extends JpaRepository<JointureCalendrier, Long> {

    // Trouver une jointure par événement et utilisateur
	Optional<JointureCalendrier> findByIdEvenementAndUtilisateur_Id(Long idEvenement, Long utilisateurId);

    // Trouver toutes les jointures pour un événement
    List<JointureCalendrier> findByIdEvenement(Long idEvenement);

    // Trouver les participants pour une cible spécifique dans un événement
    List<JointureCalendrier> findByIdEvenementAndCible(int idEvenement, String cible);
    List<JointureCalendrier> findByIdEvenementAndCibleAndNumeroTir(Long idEvenement, String cible, Integer numeroTir);

}
