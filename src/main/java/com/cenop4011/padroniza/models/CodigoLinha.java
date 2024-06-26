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
@Table(name="tb_codigo_linha")
@Data
public class CodigoLinha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="codigo_linha")
	private Integer codigoLinha;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pergunta_id",referencedColumnName = "id")
	private Pergunta pergunta;

}
