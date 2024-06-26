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
@Table(name = "tb_son_child_subitem_nota_tecnica")
public class SonChildSubItemNotaTecnica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "son_child_sub_item")
	private String sonChildSubItem;
	@Column(name = "descricao_son_child_sub_item",columnDefinition = "LONGTEXT")
	private String descricaoSonChildSubItem;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "child_sub_item_nota_tecnica_id", referencedColumnName = "id")
	private ChildSubItemNotaTecnica childSubItemNotaTecnica;
	

}
