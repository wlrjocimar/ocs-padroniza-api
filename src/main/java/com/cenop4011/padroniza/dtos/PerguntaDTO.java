package com.cenop4011.padroniza.dtos;



import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoPerguntaList;

import lombok.Data;

@Data
public class PerguntaDTO implements Serializable{
	
 	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private String descricaoFormatada;
	private Integer versao;
	private String ajuda;
	private String observacao;
	private Integer tempoAlerta;
	private Integer instrucaoIn;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPerguntaList tipo;
	

}
