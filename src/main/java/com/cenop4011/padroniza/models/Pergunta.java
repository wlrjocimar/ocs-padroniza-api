package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_pergunta")
@Data
public class Pergunta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="descricao")
	private String descricao;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name="versao")
	private Integer versao;
	private String ajuda;
	private String observacao;
	private Integer tempoAlerta;
	private Integer instrucaoIn; /// relacionar com uma lista de instruções que vinculam à pergunta
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPerguntaList tipo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bloco_id", referencedColumnName = "id")
	private Bloco bloco;
	
	
	
	
	
	
	
	
	
	

}
