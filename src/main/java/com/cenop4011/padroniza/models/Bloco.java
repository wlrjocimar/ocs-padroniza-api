package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cenop4011.padroniza.dtos.BlocoDTO;
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
	@Column(name="ativo")
	private Boolean ativo=true;

	
	
	public Bloco(BlocoDTO blocoDTO) {
		super();
		
		this.nomeBloco = blocoDTO.getNomeBloco();
		
	}
	
	

	
	
	public Bloco() {
		super();
		// TODO Auto-generated constructor stub
	}










	




	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "bloco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PosicaoBloco> posicaoBlocos = new ArrayList<>();





	@JsonIgnore
	@ManyToMany(mappedBy = "blocos", cascade = CascadeType.ALL)
    private List<Checklist> checklists = new ArrayList<>();
	
	
	
	//@JsonIgnore
	@ManyToMany
    @JoinTable(
        name = "tb_bloco_pergunta",
        joinColumns = @JoinColumn(name = "bloco_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "pergunta_id", referencedColumnName = "id"))
   private List<Pergunta> perguntas = new ArrayList<>();



	
	

	
	
	
	
	

}
