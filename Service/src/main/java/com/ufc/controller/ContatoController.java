package com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.model.Contato;
import com.ufc.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoService service;
	
	@PostMapping
	public String save(@RequestBody Contato contato) {
		this.service.save(contato);
		return "salvo!";
	}
	
	@DeleteMapping("/{id}")
	public String remover(@PathVariable Integer id) {
		this.service.remove(id);
		return "removido!";
	}
	
	@GetMapping
	public List<Contato> getAll(){
		return this.service.getAll();
	}
}
