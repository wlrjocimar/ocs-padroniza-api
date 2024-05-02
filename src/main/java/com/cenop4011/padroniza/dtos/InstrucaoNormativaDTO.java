package com.cenop4011.padroniza.dtos;

import com.cenop4011.padroniza.models.InstrucaoNormativa;

import lombok.Data;




@Data
public class InstrucaoNormativaDTO {
	
	

	
	

	private Integer numeroIn;
	
	private Integer item;
	
	private String subItem;
	
	private String ajuda;

	public InstrucaoNormativaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public InstrucaoNormativaDTO(InstrucaoNormativa instrucaoNormativa) {
		
		this.numeroIn=instrucaoNormativa.getNumeroIn();
		this.item=instrucaoNormativa.getItem();
		this.subItem=instrucaoNormativa.getSubItem();
		this.ajuda=instrucaoNormativa.getAjuda();
		
	}

}
