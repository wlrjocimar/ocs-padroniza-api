package com.cenop4011.padroniza.dtos;

import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class TipoComportamentoDTO {
	


	private Integer codigoTipoComportamento;

	
	public TipoComportamentoDTO(ComportamentoResposta comportamentoResposta) {
		this.codigoTipoComportamento = comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento();
	}
	
	
}
