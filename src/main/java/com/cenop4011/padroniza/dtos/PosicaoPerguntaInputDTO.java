package com.cenop4011.padroniza.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class PosicaoPerguntaInputDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPergunta;
	private Integer posicao;

}
