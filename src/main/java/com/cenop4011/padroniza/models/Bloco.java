package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
@Table(name = "tb_bloco")
public class Bloco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nome_bloco")
	private String nomeBloco;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="checklist_id", referencedColumnName = "id")
	private Checklist checklist;
	
	
	@OneToMany( mappedBy = "bloco" ,cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Pergunta> perguntas = new ArrayList<>();
	
	
	
	

}
