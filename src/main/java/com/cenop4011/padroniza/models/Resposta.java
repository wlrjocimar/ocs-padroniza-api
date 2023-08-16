package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.RespostaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_resposta")
@Data
public class Resposta implements Serializable {
	
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String resposta;
	
	
	@JsonIgnore
	@ManyToOne
	private Pergunta pergunta;
	
	public Resposta() {
		
	}

	public Resposta(RespostaDTO respostaDTO) {
		this.resposta=respostaDTO.getResposta();
		
	}

	

	

}
