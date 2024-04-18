package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.InstrucaoNormativaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="tb_instrucao_normativa")
@Data
public class InstrucaoNormativa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nr_in")
	private Integer numeroIn;
	@Column(name="item")
	private Integer item;
	@Column(name="sub_item")
	private String subItem;

	
	@JsonIgnore
    @ManyToOne
	@JoinColumn(name = "pergunta_id",referencedColumnName = "id")
    private Pergunta pergunta;
	
	
	public InstrucaoNormativa() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public InstrucaoNormativa(InstrucaoNormativaDTO instrucaoNormativaDTO) {
		this.numeroIn=instrucaoNormativaDTO.getNumeroIn();
		this.item=instrucaoNormativaDTO.getItem();
		this.subItem=instrucaoNormativaDTO.getSubItem();
	}


	
}