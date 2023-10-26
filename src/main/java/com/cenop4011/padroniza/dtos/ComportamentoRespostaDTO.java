package com.cenop4011.padroniza.dtos;

import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class ComportamentoRespostaDTO {
	
	
	private Integer codigoTipoComportamento;
	
	
	
	public ComportamentoRespostaDTO() {
		// TODO Auto-generated constructor stub
	}

	public ComportamentoRespostaDTO(ComportamentoResposta comportamentoResposta) {
		super();
		this.codigoTipoComportamento = comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento();
	}
	
	
	

}
