package com.cenop4011.padroniza.dtos;

import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class ComportamentoRespostaDTO {
	
	@NotNull(message = "informe O codigo do comportamento")
	private Integer codigoTipoComportamento;
	
	private Integer codigoValorComportamento; // somente faz sentido para comportamento do tipo diligencia
	
	
	
	
	
	
	
	
	private ValorComportamentoRespostaDTO valorComportamentoResposta;
	
	
	
	public ComportamentoRespostaDTO() {
		// TODO Auto-generated constructor stub
	}

	public ComportamentoRespostaDTO(ComportamentoResposta comportamentoResposta) {
		super();
		this.codigoTipoComportamento = comportamentoResposta.getTipoComportamento().getCodigoTipoComportamento();
		this.valorComportamentoResposta = new ValorComportamentoRespostaDTO(comportamentoResposta);
	}
	
	
	

}
