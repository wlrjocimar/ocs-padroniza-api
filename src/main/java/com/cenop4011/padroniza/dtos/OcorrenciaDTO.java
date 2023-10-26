package com.cenop4011.padroniza.dtos;

import javax.persistence.Column;

import lombok.Data;

@Data
public class OcorrenciaDTO {
	
		@Column(name="CD_DETALHE_OCR")
		private Integer codigoDetalheOcorrencia;
		@Column(name="CD_AGRUP_OCR")
		private Integer codigoAgrupadorOcorrencia;
		@Column(name="CD_TIP_ATVD")
		private Integer codigoTipoAtividade;
		@Column(name="NM_AGRUP_OCR")
		private String nomeAgrupadorOcorrencia;
		@Column(name="NM_DETALHE_OCR")
		private String nomeDetalheOcorrencia;
		
		
	

}
