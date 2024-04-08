package com.cenop4011.padroniza.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_nota_tecnica_modelo")
@Data
public class NotaTecnicaModelo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nm_modelo")
	private String nomeModelo;
	
	
	@OneToMany(mappedBy = "notaTecnicaModelo",cascade = CascadeType.ALL)
	private List<ItemNotaTecnica> itens = new ArrayList<>();
	
	
	

}
