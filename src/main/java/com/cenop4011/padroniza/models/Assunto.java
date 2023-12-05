package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.AssuntoDTO;

import lombok.Data;

@Entity
@Table(name = "tb_assunto")
@Data
public class Assunto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome_assunto")
	private String nomeAssunto;
	
	public Assunto() {
		// TODO Auto-generated constructor stub
	}
	
	public Assunto(AssuntoDTO assuntoDTO) {
		this.nomeAssunto=assuntoDTO.getNomeAssunto();
	}

}
