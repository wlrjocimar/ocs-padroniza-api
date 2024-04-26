package com.cenop4011.padroniza.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cenop4011.padroniza.dtos.TagDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Entity
@Table(name = "tb_grupo_tags")
@Data
public class GrupoTag {
	
	


	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome_grupo_tag",unique = true)
	private String nomeGrupoTag;
	
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "grupoTag",cascade = CascadeType.ALL)
	private List<Tag> tags = new ArrayList<>();

	public GrupoTag() {
		// TODO Auto-generated constructor stub
	}
	
	public GrupoTag(int id) { // ao instanciar um GrupoTag com um id ja existente é o mesmo que buscar um grupo por id;
		this.id = id;
	}

	
}
