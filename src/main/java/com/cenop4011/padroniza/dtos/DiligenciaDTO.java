package com.cenop4011.padroniza.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cenop4011.padroniza.models.Diligencia;

import lombok.Data;

@Data
public class DiligenciaDTO {
	
	
	private Integer id;
	
	@NotNull(message = "Informar codigodetalheOcorrencia")
	private Integer codigoDetalheOcorrencia;
    
	@NotBlank(message = "Informar observação")
	private String observacao;
	
	private String nomeDetalheOcorrencia;
	
	private Boolean ativo;
	
	
	private String ajuda;

	public DiligenciaDTO(Diligencia diligencia) {
		super();
		this.id=diligencia.getId();
		this.codigoDetalheOcorrencia = diligencia.getCodigoDetalheOcorrencia();
		this.observacao = diligencia.getObservacao();
		this.ativo = diligencia.getAtivo();
		this.nomeDetalheOcorrencia = diligencia.getNomeDetalheOcorrencia();
		this.ajuda =diligencia.getAjuda();
	}

	public DiligenciaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
}
