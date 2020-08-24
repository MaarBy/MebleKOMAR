package com.marko.MebleOnLine.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Koszyk {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uzytkownik_id")
	private Uzytkownik uzytkownik;

	@OneToMany(mappedBy = "koszyk", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produkt> produkty;

	public Koszyk() {
		super();
	}

	public Koszyk(Uzytkownik uzytkownik, List<Produkt> produkty) {
		super();
		this.uzytkownik = uzytkownik;
		this.produkty = produkty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public List<Produkt> getProdukty() {
		return produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}

}
