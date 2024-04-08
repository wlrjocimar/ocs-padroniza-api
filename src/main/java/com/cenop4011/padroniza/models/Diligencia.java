package com.cenop4011.padroniza.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cenop4011.padroniza.dtos.DiligenciaDTO;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_diligencia")
public class Diligencia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="cd_detalhe_ocr")
	private Integer codigoDetalheOcorrencia;
    @Column(name = "observacao",length = 1000)
	private String observacao;
	@Column(name="ativo")
	private Boolean ativo = true;
	@Column(name="nome_detalhe_ocorrencia")
	private String nomeDetalheOcorrencia;
	
	public Diligencia(Integer id) {
		
		this.id = id;
	}

	public Diligencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diligencia(DiligenciaDTO diligenciaDTO) {
		this.codigoDetalheOcorrencia=diligenciaDTO.getCodigoDetalheOcorrencia();
		this.observacao=diligenciaDTO.getObservacao();
		this.nomeDetalheOcorrencia=diligenciaDTO.getNomeDetalheOcorrencia();
	}


	
	
	

}
