package com.cenop4011.padroniza.dtos;

import java.io.Serializable;

import com.cenop4011.padroniza.models.Linha;

import lombok.Data;

@Data
public class LinhaDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeLinha;
	private Integer codigoLinha;
	
	
	
	public LinhaDTO(Linha linha) {
		
		this.nomeLinha = linha.getNomeLinha();
		this.codigoLinha = linha.getCodigoLinha();
	}



	public LinhaDTO() {
		super();
	}
	
	
	
	
	
	

}
