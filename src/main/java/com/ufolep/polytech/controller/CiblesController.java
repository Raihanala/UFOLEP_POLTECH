package com.ufolep.polytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ufolep.polytech.service.*;
import com.ufolep.polytech.model.*;

import java.util.List;

@RestController
@RequestMapping("/api/cibles")
public class CiblesController {

    @Autowired
    private CiblesService ciblesService;

    @PostMapping("/addCible")
    public Cibles addCible(@RequestBody Cibles cible) {
        return ciblesService.saveCible(cible);
    }

    @PutMapping("/{id}")
    public Cibles updateCible(@PathVariable Long id, @RequestBody Cibles cible) {
        return ciblesService.updateCible(id, cible);
    }

    @DeleteMapping("/{id}")
    public void deleteCible(@PathVariable Long id) {
        ciblesService.deleteCible(id);
    }

    @GetMapping("/{id}")
    public Cibles getCibleById(@PathVariable Long id) {
        return ciblesService.getCibleById(id);
    }

    @GetMapping("/getAllCibles")
    public List<Cibles> getAllCibles() {
        return ciblesService.getAllCibles();
    }
  @GetMapping("/{idEvenement}/{numeroCible}")
    public ResponseEntity<List<JointureCalendrier>> getAffectations(
            @PathVariable("idEvenement") int idEvenement,
            @PathVariable("numeroCible") String numeroCible) {
        List<JointureCalendrier> list = ciblesService.getAffectations(idEvenement, numeroCible);
        return ResponseEntity.ok(list);
    }

    // 2.2. Sélectionner un archer sur une cible pour saisir des points
    // Ex. GET /api/cibles/123/5/archer/10  (idEvenement=123, numeroCible=5, idAffectation=10)
    @GetMapping("/{idEvenement}/{numeroCible}/archer/{idAffectation}")
    public ResponseEntity<JointureCalendrier> getAffectationDetail(
            @PathVariable("idEvenement") int idEvenement,
            @PathVariable("numeroCible") String numeroCible,
            @PathVariable("idAffectation") Long idAffectation) {
        JointureCalendrier detail = ciblesService.getAffectationDetail(idEvenement, numeroCible, idAffectation);
        return ResponseEntity.ok(detail);
    }

    // 2.3. Ajouter (importer) un compétiteur sur une cible
    // Ex. POST /api/cibles/123/5/ajouter avec body JSON { "licence": "ABC123" }
    @PostMapping("/{idEvenement}/{numeroCible}/ajouter")
    public ResponseEntity<?> ajouterAffectation(
            @PathVariable("idEvenement") int idEvenement,
            @PathVariable("numeroCible") String numeroCible,
            @RequestBody AffectationRequest request) {
        JointureCalendrier affectation = ciblesService.ajouterAffectation(idEvenement, numeroCible, request.getLicence());
        if (affectation == null) {
            return ResponseEntity.badRequest().body("Ajout impossible, vérifiez la licence ou l'inscription.");
        }
        return ResponseEntity.ok(affectation);
    }

    // 2.4. Supprimer une affectation (retirer un compétiteur de la cible)
    // Ex. DELETE /api/cibles/123/5/archer/10
    @DeleteMapping("/{idEvenement}/{numeroCible}/archer/{idAffectation}")
    public ResponseEntity<?> supprimerAffectation(
            @PathVariable("idEvenement") int idEvenement,
            @PathVariable("numeroCible") String numeroCible,
            @PathVariable("idAffectation") Long idAffectation) {
        boolean success = ciblesService.supprimerAffectation(idEvenement, numeroCible, idAffectation);
        if (success) {
            return ResponseEntity.ok("Affectation supprimée");
        }
        return ResponseEntity.badRequest().body("Erreur lors de la suppression");
    }
}

// DTO pour l'ajout d'affectation
class AffectationRequest {
    private String licence;
    public String getLicence() {
        return licence;
    }
    public void setLicence(String licence) {
        this.licence = licence;
    }
}

   

