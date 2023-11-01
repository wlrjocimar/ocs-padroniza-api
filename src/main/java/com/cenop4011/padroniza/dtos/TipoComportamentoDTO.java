package com.cenop4011.padroniza.dtos;

import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class TipoComportamentoDTO {
	

    @NotNull(message = "Informe o codigo tipo de comportamento")
	private Integer codigoTipoComportamento;
    @NotNull(message = "Informe o codigo valor de comportamento")
	private Integer codigoValorComportamento;

	
	public TipoComportamentoDTO(ComportamentoResposta comportamentoResposta) {
		this.codigoTipoComportamento = comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento();
	}
	
	
}
