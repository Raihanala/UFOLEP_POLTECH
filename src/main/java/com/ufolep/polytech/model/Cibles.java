package com.ufolep.polytech.model;

import jakarta.persistence.*;

@Entity
public class Cibles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int idEvenement;
    private String code;
    private String depart1;
    private String depart2;
    private String depart3;
    private String depart4;
    private int total;
    private boolean valide;
    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipes equipe;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIdEvenement() {
		return idEvenement;
	}
	public void setIdEvenement(int idEvenement) {
		this.idEvenement = idEvenement;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDepart1() {
		return depart1;
	}
	public void setDepart1(String depart1) {
		this.depart1 = depart1;
	}
	public String getDepart2() {
		return depart2;
	}
	public void setDepart2(String depart2) {
		this.depart2 = depart2;
	}
	public String getDepart3() {
		return depart3;
	}
	public void setDepart3(String depart3) {
		this.depart3 = depart3;
	}
	public String getDepart4() {
		return depart4;
	}
	public void setDepart4(String depart4) {
		this.depart4 = depart4;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}

    
}
