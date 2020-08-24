package com.marko.MebleOnLine.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.MebleOnLine.data.Authority;
import com.marko.MebleOnLine.data.AuthorityType;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	Set<Authority> findByName(AuthorityType name);
}
