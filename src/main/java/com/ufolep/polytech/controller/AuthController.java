package com.ufolep.polytech.controller;

import com.ufolep.polytech.model.ClubsUtilisateur;
import com.ufolep.polytech.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint de login par licence
    // Exemple d'appel : POST /api/auth/login?licence=123456
    @PostMapping("/login")
    public ResponseEntity<?> loginByLicence(@RequestParam("licence") String licence) {
        ClubsUtilisateur user = authService.loginByLicence(licence);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Licence invalide");
        }
        return ResponseEntity.ok(user);
    }

    // Endpoint pour changer le marqueur (si nécessaire)
    // Exemple d'appel : PUT /api/auth/changeMarqueur
    // Body JSON : { "oldLicence": "123", "newLicence": "456" }
    @PutMapping("/changeMarqueur")
    public ResponseEntity<?> changeMarqueur(@RequestBody ChangeMarqueurRequest request) {
        boolean success = authService.changeMarqueur(request.getOldLicence(), request.getNewLicence());
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Changement de marqueur échoué");
        }
        return ResponseEntity.ok("Marqueur modifié avec succès");
    }
}

// DTO pour le changement de marqueur
class ChangeMarqueurRequest {
    private String oldLicence;
    private String newLicence;
    
    public String getOldLicence() {
        return oldLicence;
    }
    public void setOldLicence(String oldLicence) {
        this.oldLicence = oldLicence;
    }
    public String getNewLicence() {
        return newLicence;
    }
    public void setNewLicence(String newLicence) {
        this.newLicence = newLicence;
    }
}
