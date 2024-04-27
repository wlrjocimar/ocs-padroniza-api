package com.cenop4011.padroniza.models;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.LinkDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_links")
@Data
public class Link {
	
	



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome_link")
	private String nomeLink;
	@Column(name = "url")
	private String url;
	@Column(name = "is_file")
	private Boolean isFile;
	@Column(name = "ajuda",columnDefinition = "LONGTEXT")
	private String ajuda;
	
	
	
	
	public Link() {
		// TODO Auto-generated constructor stub
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pergunta_id", referencedColumnName = "id")
	private Pergunta  pergunta; 
	
	
   public Link(LinkDTO linkDTO) {
		
		this.nomeLink=linkDTO.getNomeLink();
		this.url=linkDTO.getUrl();
		this.isFile=linkDTO.getIsFile();
		this.ajuda=linkDTO.getAjuda();
		
	}
	

}
