package com.cenop4011.padroniza.dtos;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.enuns.TipoComportamento;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;

import lombok.Data;

@Data
public class ComportamentoRespostaDTO {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoComportamento tipoComportamento;

}
