package com.ufolep.polytech.model;
import jakarta.persistence.*;

@Entity
public class ClubsUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String licence;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipes equipe;

    @Enumerated(EnumType.STRING)  
    private Sexe sexe;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Clubs club;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Clubs getClub() {
		return club;
	}
	public void setClub(Clubs club) {
		this.club = club;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}
	public String getClubName() {
        return club != null ? club.getNom() : null;
    }
    
}
