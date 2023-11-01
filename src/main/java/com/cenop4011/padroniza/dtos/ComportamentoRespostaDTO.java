package com.cenop4011.padroniza.dtos;

import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class ComportamentoRespostaDTO {
	
	@NotNull(message = "informe O codigo do comportamento")
	private Integer codigoTipoComportamento;
	@NotNull(message = "informe O codigo do valor do comportamento")
	private Integer codigoValorComportamento;
	
	
	
	public ComportamentoRespostaDTO() {
		// TODO Auto-generated constructor stub
	}

	public ComportamentoRespostaDTO(ComportamentoResposta comportamentoResposta) {
		super();
		this.codigoTipoComportamento = comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento();
	}
	
	
	

}
