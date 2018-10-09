package com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.model.Contato;
import com.ufc.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;
	
	public void save(Contato contato) {
		this.repository.save(contato);
	}
	
	public void remove(Integer id) {
		this.repository.deleteById(id);
	}
	
	public List<Contato> getAll(){
		return this.repository.findAll();
	}
}
