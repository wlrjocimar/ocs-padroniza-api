package com.cenop4011.padroniza.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="tb_linha")
public class Linha implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nome_linha")
	private String nomeLinha;
	@Column(name = "codigo_linha", unique = true)
	private Integer codigoLinha;
	
	
	
	@OneToMany(mappedBy = "linha")
	private List<Checklist> checkLists = new ArrayList<>();
	

}
