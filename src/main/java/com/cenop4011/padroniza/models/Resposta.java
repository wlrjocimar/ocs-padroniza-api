package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.models.Pergunta;

import lombok.Data;

@Entity
@Table(name = "tb_resposta")
@Data
public class Resposta implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotBlank
	private String valor;
	
	private String comportamento; // enuns de comportamentos possiveis
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pergunta", referencedColumnName = "id")
	private Pergunta pergunta;
	

}
