package com.cenop4011.padroniza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_sub_item_nota_tecnica")
public class SubItemNotaTecnica {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sub_item")
	private String subItem;
	@Column(name = "descricao_sub_item")
	private String descricaoSubItem;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "item_nota_tecnica_id", referencedColumnName = "id")
	private ItemNotaTecnica itemNotaTecnica;
	
	
}
