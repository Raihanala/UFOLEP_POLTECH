package com.ufolep.polytech.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Equipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dept;
    private String equipes;
    private String idEquipe;
    private String idEvenement;
    private String parcours;
    private String questions;
    private String bonus;
    private String malus;
    private String penalite;
    private int total;
    private String valide;
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<ClubsUtilisateur> archers;
    @OneToOne
    @JoinColumn(name = "marqueur_id")
    private ClubsUtilisateur marqueur;

    @ManyToOne
    @JoinColumn(name = "cible_id")
    private Cibles cible;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEquipes() {
		return equipes;
	}
	public void setEquipes(String equipes) {
		this.equipes = equipes;
	}
	public String getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}
	public String getIdEvenement() {
		return idEvenement;
	}
	public void setIdEvenement(String idEvenement) {
		this.idEvenement = idEvenement;
	}
	public String getParcours() {
		return parcours;
	}
	public void setParcours(String parcours) {
		this.parcours = parcours;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getMalus() {
		return malus;
	}
	public void setMalus(String malus) {
		this.malus = malus;
	}
	public String getPenalite() {
		return penalite;
	}
	public void setPenalite(String penalite) {
		this.penalite = penalite;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getValide() {
		return valide;
	}
	public void setValide(String valide) {
		this.valide = valide;
	}

    
}
