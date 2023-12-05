package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.ProprietarioDTO;

import lombok.Data;

@Entity
@Table(name="tb_proprietario")
@Data
public class Proprietario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome_proprietario")
	private String nomeProprietario;

	public Proprietario(ProprietarioDTO proprietarioDTO) {
		this.nomeProprietario = proprietarioDTO.getNomeProprietario();
	}

	
	public Proprietario() {
	  
	}
	
	
	
}
