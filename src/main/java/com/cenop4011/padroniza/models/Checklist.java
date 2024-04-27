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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cenop4011.padroniza.dtos.ChecklistDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_checklist")
public class Checklist implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome_personalizado")
	private String nomePersonalizado; //exemplo; checklist do mario
	
	
//	@OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, orphanRemoval =true , fetch = FetchType.EAGER)
//	private List<Bloco> blocos = new ArrayList<Bloco>();
	
	
	
	//@JsonIgnore
	@ManyToMany
    @JoinTable(
        name = "tb_checklist_bloco",
        joinColumns = @JoinColumn(name = "checklist_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "bloco_id", referencedColumnName = "id"))
	private List<Bloco> blocos = new ArrayList<Bloco>();
	
	
	
	@LazyCollection(value = LazyCollectionOption.FALSE)
	@ManyToMany
    @JoinTable(
        name = "tb_checklist_tag",
        joinColumns = @JoinColumn(name = "checklist_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	 private List<Tag> tags = new ArrayList<>();
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="linha_id", referencedColumnName = "id" )
	private Linha linha;

	public Checklist(ChecklistDTO checklistDTO) {
		super();
		
		this.nomePersonalizado = checklistDTO.getNomePersonalizado();
		
		
	}
	public Checklist() {
		super();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
