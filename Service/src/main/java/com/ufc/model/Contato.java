package com.ufc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Contato {
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String telefone;
	private String endereco;

}
