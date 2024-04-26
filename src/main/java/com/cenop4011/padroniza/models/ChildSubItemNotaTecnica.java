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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_child_sub_item_nota_tecnica")
public class ChildSubItemNotaTecnica {
	
	

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "child_sub_item")
	private String childSubItem;
	@Column(name = "descricao_child_sub_item",columnDefinition = "LONGTEXT")
	private String descricaoChildSubItem;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sub_item_nota_tecnica_id", referencedColumnName = "id")
	private SubItemNotaTecnica subItemNotaTecnica;

	
	
	@OneToMany(mappedBy = "childSubItemNotaTecnica",cascade = CascadeType.ALL)
	List<SonChildSubItemNotaTecnica> sonChildSubItemNotaTecnicas = new ArrayList<>();

}
