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
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.TagDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;



@Entity
@Table(name = "tb_tags")
@Data
public class Tag {
	
	



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome_tag")
	private String nomeTag;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "grupotag_id",referencedColumnName = "id")
	private GrupoTag grupoTag;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
    private List<Checklist> checklists = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")
    private List<Pergunta> perguntas = new ArrayList<>(); 
	
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(TagDTO tagDTO) {
		
		if(tagDTO.getCodigoTag()!=null) {
			this.id=tagDTO.getCodigoTag();
		}
		
		
		
		
	}
	
	

}
