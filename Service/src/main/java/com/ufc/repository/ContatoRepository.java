package com.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
