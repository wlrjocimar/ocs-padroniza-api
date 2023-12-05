package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.OrigemDTO;

import lombok.Data;

@Entity
@Table(name="tb_origem")
@Data
public class Origem {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome_origem")
	private String nomeOrigem;
	
	public Origem() {
		// TODO Auto-generated constructor stub
	}
	
	public Origem(OrigemDTO origemDTO) {
		this.nomeOrigem= origemDTO.getNomeOrigem();
	}

}
