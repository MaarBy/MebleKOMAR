package com.marko.MebleOnLine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.MebleOnLine.data.Uzytkownik;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Integer> {
	List<Uzytkownik> findByEmail(String email);
}
