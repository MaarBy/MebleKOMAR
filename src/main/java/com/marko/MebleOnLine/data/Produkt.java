package com.marko.MebleOnLine.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produkt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String kategoria;
	private String nazwa;
	private String opis;
	private double cena;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "koszyk_id", nullable = true)
	private Koszyk koszyk;

	public Produkt() {
		super();
	}

	public Produkt(String kategoria, String nazwa, String opis, double cena) {
		super();
		this.kategoria = kategoria;
		this.nazwa = nazwa;
		this.opis = opis;
		this.cena = cena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKategoria() {
		return kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Koszyk getKoszyk() {
		return koszyk;
	}

	public void setKoszyk(Koszyk koszyk) {
		this.koszyk = koszyk;
	}

}
