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
@Table(name = "tb_item_nota_tecnica")
@Data
public class ItemNotaTecnica {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "codigo_item")
	private Integer codigoItem;
	
	@Column(name = "descricao_item")
	private String descricaoItem;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nota_tecnica_id", referencedColumnName = "id")
	private NotaTecnicaModelo notaTecnicaModelo;
	
	@OneToMany(mappedBy = "itemNotaTecnica",cascade = CascadeType.ALL)
	List<SubItemNotaTecnica> subItens = new ArrayList<>();
	
}
