package com.cenop4011.padroniza.dtos;

import com.cenop4011.padroniza.models.ComportamentoResposta;

import lombok.Data;

@Data
public class ValorComportamentoRespostaDTO {
	

	
	


	private Integer id;
	
	
    private DiligenciaDTO diligenciaDTO;
    
    
    
    public ValorComportamentoRespostaDTO(ComportamentoResposta comportamentoResposta) {
		this.id = comportamentoResposta.getValorComportamentoResposta().getId();
		this.diligenciaDTO = new DiligenciaDTO(comportamentoResposta.getValorComportamentoResposta().getDiligencia());
	}



	public ValorComportamentoRespostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    

}
